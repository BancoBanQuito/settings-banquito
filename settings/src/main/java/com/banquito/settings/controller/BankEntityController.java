package com.banquito.settings.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.settings.model.BankEntity;
import com.banquito.settings.service.BankEntityService;

@RestController
@RequestMapping("/api/bank-entity")
public class BankEntityController {

	private final BankEntityService bankEntityService;

	public BankEntityController(BankEntityService bankEntityService) {
		this.bankEntityService = bankEntityService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object findAllBankEntity() {
		Iterable<BankEntity> bankEntity = this.bankEntityService.findAll();
		return ResponseEntity.ok(bankEntity);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> createBankEntity(@RequestBody BankEntity bankEntity) {
		this.bankEntityService.saveBankEntity(bankEntity);
		return ResponseEntity.ok("Bank's Information created successfully");
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBankEntity(@RequestBody BankEntity bankEntity) {
		this.bankEntityService.updateBankEntity(bankEntity);
		return ResponseEntity.ok("Bank's Information updated successfully");
	}
}