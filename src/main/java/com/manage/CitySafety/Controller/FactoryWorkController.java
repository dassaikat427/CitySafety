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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manage.CitySafety.Entity.FactoryLabour;
import com.manage.CitySafety.Entity.FactoryWork;
import com.manage.CitySafety.Entity.Supervisor;
import com.manage.CitySafety.Entity.SupervisorEntry;
import com.manage.CitySafety.Repository.FactoryLabourRepository;
import com.manage.CitySafety.Repository.FactoryWorkRepository;

import jakarta.websocket.server.PathParam;

@Controller
public class FactoryWorkController {

	@Autowired
	FactoryWorkRepository factoryWorkRepo;
	
	@Autowired
	FactoryLabourRepository factoryLabour; 
	
	@GetMapping("/factoryworks")
	public String listFactoryWork(Model model) {
		List<FactoryWork> factWork=factoryWorkRepo.findAll();
	    
	    for(FactoryWork fact: factWork)
	    	System.out.println(fact.getImportMetarial());
	    model.addAttribute("factoryWorks", factoryWorkRepo.findAll());
	    model.addAttribute("labours", factoryLabour.findAll());
	    return "factoryWork_list";
	}
	@PostMapping("/factorywork/add")
	public String addEntry(@ModelAttribute FactoryWork entry, RedirectAttributes ra) {

	    //Supervisor supervisor = supervisorRepo.findByName(name);
	   
		ra.addFlashAttribute("SuccessEntryMessage", "Added Successfully");
		factoryWorkRepo.save(entry);
	    return "redirect:/factoryworks";
	}
	@GetMapping("/factorywork/filterByDate")
	public String filterEntriesByDate(@RequestParam LocalDate startDate,@RequestParam LocalDate endDate,
	                                  Model model) {
	    List<FactoryWork> filteredEntries = factoryWorkRepo.findByDateBetween(startDate,endDate);
	    model.addAttribute("factoryWorks", filteredEntries);
	    model.addAttribute("labours", factoryLabour.findAll());
	    
	    return "factoryWork_list";
	}
	@PostMapping("/factorywork/addFactoryLabour")
	public String addFactoryLabour(@ModelAttribute FactoryLabour fact,RedirectAttributes ra,Model model) {

	    if(factoryLabour.existsByfactoryLabourName(fact.getFactoryLabourName()))
	    {
	    	model.addAttribute("ExistFactoryLabourMessage", "Labour name already present.....");
	    }
	    else {
	    	factoryLabour.save(fact);
	    	
		    model.addAttribute("SuccessFactoryLabourMessage", "Added Labour Successfully");
		    model.addAttribute("labours", factoryLabour.findAll());
		    model.addAttribute("factoryWorks", factoryWorkRepo.findAll());
	    }
	   
	    	
	    
	    
	    
	    return "factoryWork_list";
	}
	
}
