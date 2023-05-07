package com.interbanking.interbanking.model.service;

import com.interbanking.interbanking.model.entity.Company;
import com.interbanking.interbanking.model.repository.ICompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional
public class CompanyServiceImpl implements ICompany {

    @Autowired
    private ICompanyRepository companyRepository;
    @Override
    public Optional<Company> findById(Long companyId) {
        return companyRepository.findById(companyId);
    }

    @Override
    public Optional<Company> findByCuit(String cuit) {
        return companyRepository.findByCuit(cuit);
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
