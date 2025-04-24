package com.manage.CitySafety.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manage.CitySafety.Entity.Supervisor;
import com.manage.CitySafety.Entity.SupervisorEntry;
import com.manage.CitySafety.Repository.SupervisorRepository;
import com.manage.CitySafety.config.PasswordUtil;

@Controller
public class AuthController {
	
	@Autowired
	SupervisorRepository superRepo;
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@PostMapping("/addSupervisor")
	public String addSupervisor(@RequestParam String name, @RequestParam String password,RedirectAttributes ra) {
		Supervisor exist=superRepo.findByName(name);
		if(exist!=null)
		{
			ra.addFlashAttribute("error","Supervisor with this name already exist......");
			return "redirect:/supervisors";
		}
		String encrypt=PasswordUtil.encode(password);
		Supervisor sv=new Supervisor();
		sv.setName(name);
		sv.setPassword(encrypt);
		superRepo.save(sv);
		ra.addFlashAttribute("SuccessMessage","Added successfully....");
	    return "redirect:/supervisors";
	}
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestParam String name,
	                            @RequestParam String password,
	                            RedirectAttributes model) {
		System.out.println("sn "+name);
	    Supervisor supervisor = superRepo.findByName(name);
	    if (supervisor != null) {
	        supervisor.setPassword(password);
	        superRepo.save(supervisor);
	        model.addFlashAttribute("SuccessMessage", "Password reset successfully for " + name);
	    } else {
	        model.addFlashAttribute("error", "Supervisor not found");
	    }
	    model.addAttribute("supervisors", superRepo.findAll());
	    return "supervisor_list";
	}
}
