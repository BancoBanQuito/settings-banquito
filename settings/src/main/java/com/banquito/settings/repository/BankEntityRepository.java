package com.banquito.settings.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.banquito.settings.model.BankEntity;

@Repository
public interface BankEntityRepository extends MongoRepository<BankEntity, String> {

}
