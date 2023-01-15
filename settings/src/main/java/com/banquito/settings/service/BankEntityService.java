package com.banquito.settings.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.settings.model.BankEntity;
import com.banquito.settings.repository.BankEntityRepository;

@Service
public class BankEntityService {
	
	private final BankEntityRepository bankEntityRepository;

	public BankEntityService(BankEntityRepository bankEntityRepository) {
		this.bankEntityRepository = bankEntityRepository;
	}

	public Iterable<BankEntity> findAll() {
		return bankEntityRepository.findAll();
	}

	@Transactional
	public void saveBankEntity(BankEntity bankEntity) {
		this.bankEntityRepository.save(bankEntity);
	}

	@Transactional
	public void updateBankEntity(BankEntity bankEntity) {
		bankEntity.setInternationalBankCode(bankEntity.getInternationalBankCode());
		bankEntity.setName(bankEntity.getName());
		this.bankEntityRepository.save(bankEntity);
	}
}
