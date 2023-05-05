package com.interbanking.interbanking.model.service;

import com.interbanking.interbanking.model.entity.Company;
import com.interbanking.interbanking.model.repository.ICompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CompanyServiceImpl implements ICompany {

    @Autowired
    private ICompanyRepository companyRepository;
    @Override
    public Optional<Company> findById(Long companyId) {
        return companyRepository.findById(companyId);
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }
}
