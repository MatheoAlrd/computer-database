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

	private ModelAndView mv = new ModelAndView("user");

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/registration")
	public ModelAndView showRegistrationForm() {
		UserDTO userDTO = new UserDTO();
		mv.addObject("user", userDTO);
		return this.getModelAndView();
	}

	@PostMapping("/registration")
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDTO userDTO) {

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
