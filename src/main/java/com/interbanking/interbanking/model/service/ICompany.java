package com.interbanking.interbanking.model.service;

import com.interbanking.interbanking.model.entity.Company;

import java.util.Optional;

public interface ICompany {
    Optional<Company> findById(Long companyId);
    Company save(Company company);

}
