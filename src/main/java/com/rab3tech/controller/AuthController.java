package com.rab3tech.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3tech.dao.ProfileDTO;
import com.rab3tech.dao.ProfileDao;

@Controller
public class AuthController {
	
	@Autowired
	private ProfileDao profileDao;
	
	@GetMapping ({"/", "/auth"})
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/load/image")
	public void findPhotoByUsername(@RequestParam String username, HttpServletResponse response) throws IOException {
		response.setContentType("image/png");
		byte[] photo = profileDao.findPhotoByUsername(username);
		ServletOutputStream outputStream = response.getOutputStream();
		if (photo!=null && photo.length>0)
		{
			outputStream.write(photo);
			outputStream.flush();
		}
			
		outputStream.close();
	}
	
	@PostMapping ("/fpassword")
	 public String forgotPasswordPost(@RequestParam ("usernameEmail") String usernameEmail, Model model)
    {
		String password = profileDao.findPasswordByUsernameOrEmail(usernameEmail);
		
		if(password.length() == 0)
		{
			model.addAttribute("message", "I am sorry, your username and email are not correct");
		}
		
		else {
			model.addAttribute("message", "your password is " +password);
		}
    	return "forgotPassword";
    } 
	
	
    @GetMapping ("/fpassword")
    public String forgotPassword()
    {
    	return "forgotPassword";
    }
	
	@GetMapping ("/logout")
	protected String Logout(HttpSession session, Model model) {
		
		
		if(session!=null)
		session.invalidate();
		
		model.addAttribute("hmmmm", "You have logged out successfully!!");
		  return "login";
	}	  
	
	
	
	@PostMapping ("/auth")
	public String ValidateUser(@RequestParam ("username") String username, @RequestParam ("password") String password,HttpSession session, Model model ) {
		
		
		
				
		ProfileDTO profileDTO=profileDao.authUser(username, password);
		if(profileDTO!=null) {
		   	
		    	
		   session.setAttribute("userData", profileDTO);
		   
		   return "dashboard";
	  }else {  
		 model.addAttribute("hmmmm", "Sorry , username and password are not correct");
		  return "login";
	  }
		
		
	}

}
