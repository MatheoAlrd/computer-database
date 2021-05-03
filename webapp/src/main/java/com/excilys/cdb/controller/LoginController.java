package com.excilys.cdb.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.dto.UserDTO;
import com.excilys.cdb.exception.UserAlreadyExistException;
import com.excilys.cdb.service.UserService;

@Controller
@RequestMapping("/user")
public class LoginController {

	UserService userService;

	private ModelAndView mv = new ModelAndView("login");

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/registration")
	public ModelAndView showRegistrationForm() {

		return this.getModelAndView();
	}

	@PostMapping("/registration")
	public ModelAndView registerUserAccount(String firstName, String lastName, String password, String role) {
		
		UserDTO userDTO = new UserDTO(firstName, lastName, password, role);

		try {
			this.userService.registerNewUserAccount(userDTO);
		} catch (UserAlreadyExistException uaeEx) {
			mv.addObject("message", "An account for that username/email already exists.");
		}
		return this.getModelAndView();
	}

	public ModelAndView getModelAndView() {
		return this.mv;
	}

}
