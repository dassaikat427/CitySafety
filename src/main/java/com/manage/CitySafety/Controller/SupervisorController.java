package com.manage.CitySafety.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manage.CitySafety.Entity.AdvancePayment;
import com.manage.CitySafety.Entity.Labour;
import com.manage.CitySafety.Entity.Supervisor;
import com.manage.CitySafety.Entity.SupervisorEntry;
import com.manage.CitySafety.Repository.AdvancePaymentRepository;
import com.manage.CitySafety.Repository.LabourRepository;
import com.manage.CitySafety.Repository.SupervisorEntryRepository;
import com.manage.CitySafety.Repository.SupervisorRepository;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.*;

@Controller
public class SupervisorController {
	
	@Autowired
	private SupervisorRepository supervisorRepo;
	
	@Autowired
	AdvancePaymentRepository advancePaymentRepo;

	@Autowired
	private SupervisorEntryRepository entryRepo;
	
	@Autowired
	private LabourRepository labourRepo;
	
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
		LocalDate today=LocalDate.now();
		LocalDate tenDaysAgo=today.minusDays(9);
	    List<SupervisorEntry> entries = entryRepo.findBySupervisorNameAndDateBetween(name,tenDaysAgo,today);
	    model.addAttribute("entries", entries);
	    model.addAttribute("supervisor", name);
	    model.addAttribute("labours", labourRepo.findAll());
List<SupervisorEntry> superv=entryRepo.findBySupervisorName(name);
	
System.out.println("supervisor "+name);
	    for(SupervisorEntry sup: superv)
	    	System.out.println(sup.getLabourName()+" , "+sup.getSupervisorName()+" , "+sup.getTotalCost());
	    
	    return "supervisor_details";
	}

	@PostMapping("/supervisor/{name}/add")
	public String addEntry(@PathVariable String name,
	                       @ModelAttribute SupervisorEntry entry, RedirectAttributes ra) {

	    //Supervisor supervisor = supervisorRepo.findByName(name);
	    entry.setSupervisorName(name);
	    

	    entryRepo.save(entry);
	    ra.addFlashAttribute("SuccessAddEntryMessage","Added Successfully");
	    
	    return "redirect:/supervisor/" + name;
	}
	@GetMapping("/supervisor/{name}/filterByDate")
	public String filterEntriesByDate(@PathVariable String name,
	                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	                                  @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	                                  Model model) {
		
	    List<SupervisorEntry> filteredEntries=new ArrayList<>();
	    if(startDate!=null && endDate !=null)
	    {
	    	filteredEntries=entryRepo.findBySupervisorNameAndDateBetween(name,startDate,endDate);
	    }
	    else if(startDate!=null)
	    {
	    	filteredEntries= entryRepo.findBySupervisorNameAndDate(name, startDate);
	    }
	    else
	    {
	    	entryRepo.findBySupervisorName(name);
	    }
	    
	    model.addAttribute("entries", filteredEntries);
	    model.addAttribute("supervisor", name);
	    model.addAttribute("labours",labourRepo.findAll());
	    model.addAttribute("startDate", startDate);
	    model.addAttribute("endDate", endDate);
	    return "supervisor_details";
	}
	
	@PostMapping("/addAdvance")
	public String addAdvance(@ModelAttribute AdvancePayment advancePayment,RedirectAttributes ra)
	{
		 
		advancePaymentRepo.save(advancePayment);
		ra.addFlashAttribute("SuccessAdvanceMessage", "Added Successfully");
		return "redirect:/getAdvance";
	}
	
	@GetMapping("/getAdvance")
	public String getAdvance(Model model)
	{
		model.addAttribute("Advance", advancePaymentRepo.findAll());
		return "manage_labour_advance";
	}
	
	@PostMapping("/addLabour")
	public String addLabour(@ModelAttribute Labour labour,RedirectAttributes ra)
	{
		 
		labourRepo.save(labour);
		
		ra.addFlashAttribute("SuccessLabourMessage", "Added Successfully");
		return "redirect:/supervisors";
	}
	
	@PostMapping("/getLabour")
	public String getLabour(Model model)
	{
		 
		
		model.addAttribute("labours", labourRepo.findAll());
		return "supervisor_details";
	}
	
	@GetMapping("/filterAdvance")
	public String filterDateAdvance(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, Model model)
	{
		List<AdvancePayment> advan=advancePaymentRepo.findByDateBetween(startDate, endDate);
		if(advan!=null)
		{
			model.addAttribute("Advance", advan);
			
		}
		else
		{
			model.addAttribute("nullresult","no result found");
		}
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		return "manage_labour_advance";
		
	}


}
