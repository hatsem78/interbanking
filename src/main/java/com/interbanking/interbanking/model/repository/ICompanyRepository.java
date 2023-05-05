package com.interbanking.interbanking.model.repository;

import com.interbanking.interbanking.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepository extends JpaRepository<Company, Long> {
}
