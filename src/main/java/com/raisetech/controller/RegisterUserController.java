package com.raisetech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.raisetech.bean.UserForm;
import com.raisetech.response.ResponseMessage;
import com.raisetech.service.RegisterUserService;

@RestController
public class RegisterUserController {

	private final RegisterUserService registerUserService;

	public RegisterUserController(RegisterUserService registerUserService) {
		this.registerUserService = registerUserService;
	}

	@PostMapping("/users")
	public ResponseEntity signup(@RequestBody @Validated UserForm form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return new ResponseEntity(ResponseMessage.newMessage("failed"), HttpStatus.UNPROCESSABLE_ENTITY);
		}


		this.registerUserService.createUser(form.getName(), form.getBirthdate());

		return new ResponseEntity(ResponseMessage.newMessage("user registerd"), HttpStatus.CREATED);

	}

}