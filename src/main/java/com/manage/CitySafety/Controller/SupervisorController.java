package com.manage.CitySafety.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.manage.CitySafety.Entity.Supervisor;
import com.manage.CitySafety.Entity.SupervisorEntry;
import com.manage.CitySafety.Repository.SupervisorEntryRepository;
import com.manage.CitySafety.Repository.SupervisorRepository;
import org.springframework.web.bind.annotation.*;

@Controller
public class SupervisorController {
	
	@Autowired
	private SupervisorRepository supervisorRepo;

	@Autowired
	private SupervisorEntryRepository entryRepo;
	
	@GetMapping("/supervisors")
	public String listSupervisors(Model model) {
		List<Supervisor> superv=supervisorRepo.findAll();
	    
	    for(Supervisor sup: superv)
	    	System.out.println(sup.getName());
	    model.addAttribute("supervisors", supervisorRepo.findAll());
	    return "supervisor_list";
	}

	@GetMapping("/supervisor/{name}")
	public String supervisorDetails(@PathVariable String name, Model model) {
	    List<SupervisorEntry> entries = entryRepo.findBySupervisorName(name);
	    model.addAttribute("entries", entries);
	    model.addAttribute("supervisor", name);
List<SupervisorEntry> superv=entryRepo.findBySupervisorName(name);
	
System.out.println("supervisor "+name);
	    for(SupervisorEntry sup: superv)
	    	System.out.println(sup.getLabourName()+" , "+sup.getSupervisorName()+" , "+sup.getTotalCost());
	    return "supervisor_details";
	}

	@PostMapping("/supervisor/{name}/add")
	public String addEntry(@PathVariable String name,
	                       @ModelAttribute SupervisorEntry entry) {

	    //Supervisor supervisor = supervisorRepo.findByName(name);
	    entry.setSupervisorName(name);
	    

	    entryRepo.save(entry);
	    return "redirect:/supervisor/" + name;
	}
	@GetMapping("/supervisor/{name}/filterByDate")
	public String filterEntriesByDate(@PathVariable String name,
	                                  @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
	                                  Model model) {
	    List<SupervisorEntry> filteredEntries = entryRepo.findBySupervisorNameAndDate(name, date);
	    model.addAttribute("entries", filteredEntries);
	    model.addAttribute("supervisor", name);
	    return "supervisor_details";
	}
	


}
