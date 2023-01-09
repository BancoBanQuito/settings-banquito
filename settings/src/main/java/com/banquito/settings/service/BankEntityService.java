package com.banquito.settings.service;

import java.util.List;

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

	public List<BankEntity> findAll() {
		return bankEntityRepository.findAll();
	}

	@Transactional
	public void updateBranch(String code, BankEntity bankEntity) {
		this.bankEntityRepository.save(bankEntity);
	}
}
