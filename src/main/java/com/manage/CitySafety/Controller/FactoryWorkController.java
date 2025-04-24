package com.manage.CitySafety.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manage.CitySafety.Entity.FactoryWork;
import com.manage.CitySafety.Entity.Supervisor;
import com.manage.CitySafety.Entity.SupervisorEntry;
import com.manage.CitySafety.Repository.FactoryWorkRepository;

@Controller
public class FactoryWorkController {

	@Autowired
	FactoryWorkRepository factoryWorkRepo;
	
	@GetMapping("/factoryworks")
	public String listFactoryWork(Model model) {
		List<FactoryWork> factWork=factoryWorkRepo.findAll();
	    
	    for(FactoryWork fact: factWork)
	    	System.out.println(fact.getImportMetarial());
	    model.addAttribute("factoryWorks", factoryWorkRepo.findAll());
	    return "factoryWork_list";
	}
	@PostMapping("/factorywork/add")
	public String addEntry(@ModelAttribute FactoryWork entry) {

	    //Supervisor supervisor = supervisorRepo.findByName(name);
	    
	    

		factoryWorkRepo.save(entry);
	    return "redirect:/factoryworks";
	}
	@GetMapping("/factorywork/filterByDate")
	public String filterEntriesByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
	                                  Model model) {
	    List<FactoryWork> filteredEntries = factoryWorkRepo.findByDate(date);
	    model.addAttribute("entries", filteredEntries);
	    return "supervisor_details";
	}
}
