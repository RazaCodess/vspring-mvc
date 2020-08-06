package com.rab3tech.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3tech.dao.ProfileDTO;
import com.rab3tech.dao.ProfileDao;
import com.rab3tech.utils.Utils;

@Controller
public class CustomerController {

	@Autowired
	private ProfileDao profileDao;

	@GetMapping("/deleteProfile")
	protected String DeleteProfile(@RequestParam("username") String username) {

		profileDao.deleteByUsername(username);
		return "redirect:/profiles";
	}

	@GetMapping("/editProfile")
	protected String editProfile(@RequestParam("username") String username, Model model) {

		ProfileDTO profileDTO = profileDao.findByUsername(username);
		model.addAttribute("profileDTO", profileDTO);
		return "esignup";
	}

	@GetMapping("/filterProfile")
	protected String filterProfile(@RequestParam("filterText") String filterText, Model model) {

		List<ProfileDTO> profileDTOs = null;
		if (!"Select".equalsIgnoreCase(filterText)) {
			profileDTOs = profileDao.filterProfiles(filterText);
		} else {
			profileDTOs = profileDao.findAll();
		}

		model.addAttribute("listoptions", profileDao.findAllQualification());

		model.addAttribute("profileDTOs", profileDTOs);
		return "profiles";
	}

	@GetMapping("/loggedUser")
	protected String loggedUser(Model model) {
		Set<ProfileDTO> loggedUsers = ProfileDTO.loggedInUser();
		model.addAttribute("profileDTOs", loggedUsers);
		return "loggedUsers";
	}

	@GetMapping("/profiles")
	protected String profiles(Model model) {

		List<ProfileDTO> profileDTOs = profileDao.findAll();

		model.addAttribute("profileDTOs", profileDTOs);
		model.addAttribute("listoptions", profileDao.findAllQualification());
		return "profiles";
	}
	
	@GetMapping("/iprofiles")
	protected String iprofiles(Model model) {

		List<ProfileDTO> profileDTOs = profileDao.findAllWithPhoto();

		model.addAttribute("profileDTOs", profileDTOs);
		model.addAttribute("listoptions", profileDao.findAllQualification());
		return "iprofiles";
	}

	@GetMapping("/searchProfile")
	protected String searchProfile(@RequestParam("search") String search, Model model)
			throws ServletException, IOException {

		List<ProfileDTO> profileDTOs = profileDao.searchProfiles(search);

		model.addAttribute("profileDTOs", profileDTOs);

		model.addAttribute("listoptions", profileDao.findAllQualification());
		return "profiles";
	}

	@PostMapping("/signup")
	protected String signup(@ModelAttribute ProfileDTO profileDTO, Model model) {

		
		String password = Utils.generateRandomPassword(5);
		profileDTO.setPassword(password);
		profileDTO.setUsername(profileDTO.getUsername());

		
		profileDao.createSignup(profileDTO);
		model.addAttribute("hmmmm", "Hi , " + profileDTO.getName() + " , you have done signup successfully!!!!!!!!!!!");
		return "login";
	}
	
	@PostMapping("/isignup")
	protected String isignup(@ModelAttribute ProfileDTO profileDTO, Model model) {

	
		String password = Utils.generateRandomPassword(5);
		profileDTO.setPassword(password);
		profileDTO.setUsername(profileDTO.getEmail());

		profileDao.icreateSignup(profileDTO);
		model.addAttribute("hmmmm", "Hi , " + profileDTO.getName() + " , you have done signup successfully!!!!!!!!!!!");
		return "login";
	}

	@GetMapping("/sortProfile")
	protected String sortProfile(@RequestParam("sort") String sort, Model model) throws ServletException, IOException {

		List<ProfileDTO> profileDTOs = profileDao.sortProfiles(sort);

		model.addAttribute("profileDTOs", profileDTOs);
		model.addAttribute("listoptions", profileDao.findAllQualification());
		return "profiles";
	}

	@PostMapping("/usignup")
	protected String usignup(@ModelAttribute ProfileDTO profileDTO) {
		

		profileDao.updateSignup(profileDTO);
		return "redirect:/profiles";
	}

}
