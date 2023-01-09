package com.banquito.settings.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.settings.service.BankEntityService;

@RestController
@RequestMapping("/api/bank-entity")
public class BankEntityController {

	private final BankEntityService bankEntityService;

	public BankEntityController(BankEntityService bankEntityService) {
		this.bankEntityService = bankEntityService;
	}

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public Object findAll() {
		return ResponseEntity.ok(this.bankEntityService.findAll());
	}
}
