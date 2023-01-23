package com.banquito.settings.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.settings.model.Branch;

@Repository
public interface BranchRepository extends CrudRepository<Branch, String> {

    List<Branch> findAllByOrderByLocation();
    List<Branch> findByNameLike(String name);
    Branch findByName(String name);
    Boolean existsByName(String name);
    Boolean existsByAddress(String address);
    void deleteByName(String name);
    
}
