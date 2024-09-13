package com.ecommerce.lite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.lite.entities.Company;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, Integer>{

}
