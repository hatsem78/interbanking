package com.interbanking.interbanking.model.service;

import com.interbanking.interbanking.model.entity.Company;

import java.util.List;
import java.util.Optional;

public interface ICompany {
    Optional<Company> findById(Long companyId);
    Optional<Company> findByCuit(String cuit);
    Company save(Company company);
    List<Company> findAll();
}
