package com.spring.bioMedical.Controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.spring.bioMedical.entity.User;
import com.spring.bioMedical.service.EmailService;
import com.spring.bioMedical.service.UserService;

/**
 * 
 * @author hermann chefouetmeka
 * @github hermann90
 *
 */
@Controller
public class RegisterController {
	
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserService userService;
	private EmailService emailService;
	
	@Autowired
	public RegisterController(
			UserService userService, EmailService emailService) {
		//this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		System.out.println("\n \n \n emailService ============================> "+emailService+"\n \n \n");

		this.userService = userService;
		this.emailService = emailService;
	}
	
	// Return registration form template
	@RequestMapping(value="/register", method = RequestMethod.GET)

	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
		System.out.println("\n \n \n a ============================> "+user+"\n \n \n");

		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {

		System.out.println("\n \n \n 1 ============================> "+"\n \n \n");

		// Lookup user in database by e-mail
		User userExists = null;
		try{
			userExists = userService.findByEmail(user.getEmail());
		}catch (Exception e){
			e.printStackTrace();
		}

		System.out.println("\n \n \n 2 ============================> "+userExists+"\n \n \n");
		
		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}
			
		if (bindingResult.hasErrors()) {
			System.out.println("\n \n \n dd ============================> "+bindingResult+"\n \n \n");

			modelAndView.setViewName("register");		
		} else { // new user so we create user and send confirmation e-mail
			System.out.println("\n \n \n dd1 ============================> "+bindingResult+"\n \n \n");

			// Disable user until they click on confirmation link in email

			user.setEnabled(0);
			user.setRole("ROLE_USER");
		      
			
		    // Generate random 36-character string token for confirmation link
		    user.setConfirmationToken(UUID.randomUUID().toString());
		        
		    userService.saveUser(user);
				
		//	String appUrl = request.getScheme() + "://" + request.getServerName();
			
		    String appUrl = "http://localhost:8082";
		    
		    
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n\n"
					+ appUrl + "/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("spring.email.auth@gmail.com");
			
			emailService.sendEmail(registrationEmail);
			
			modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
			modelAndView.setViewName("register");
		}
			
		return modelAndView;
	}
	
	// Process confirmation link
	@RequestMapping(value="/confirm", method = RequestMethod.GET)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, @RequestParam("token") String token) {
			
		User user = userService.findByConfirmationToken(token);
			
		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getConfirmationToken());
		}
			
		modelAndView.setViewName("confirm");
		return modelAndView;		
	}
	
	// Process confirmation link
	@RequestMapping(value="/confirm", method = RequestMethod.POST)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
				
		modelAndView.setViewName("confirm");
		
//		Zxcvbn passwordCheck = new Zxcvbn();
//
//		Strength strength = passwordCheck.measure(requestParams.get("password"));
//
//		if (strength.getScore() < 3) {
//			//modelAndView.addObject("errorMessage", "Your password is too weak.  Choose a stronger one.");
//			bindingResult.reject("password");
//
//			redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");
//
//			modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
//			System.out.println(requestParams.get("token"));
//			return modelAndView;
//		}
	
		// Find the user associated with the reset token
		User user = userService.findByConfirmationToken(requestParams.get("token"));

		// Set new password
	//	user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
		user.setPassword(requestParams.get("password"));

		// Set user to enabled
		user.setEnabled(1);
		
		// Save user
		userService.saveUser(user);
		
		modelAndView.addObject("successMessage", "Your password has been set!");
		return modelAndView;		
	}
}