package com.banquito.settings.repository;

import org.springframework.data.repository.CrudRepository;

import com.banquito.settings.model.BankEntity;

public interface BankEntityRepository extends CrudRepository<BankEntity, String>{

}