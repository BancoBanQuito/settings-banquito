package com.banquito.settings.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.settings.model.BankEntity;

@Repository
public interface BankEntityRepository extends CrudRepository<BankEntity, String> {

}
