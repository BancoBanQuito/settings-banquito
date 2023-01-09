package com.banquito.settings.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.banquito.settings.model.Branch;

@Repository
public interface BranchRepository extends MongoRepository<Branch, String> {

    List<Branch> findByNameLike(String name);
    
}
