package com.interbanking.interbanking.model.service;

import com.interbanking.interbanking.model.entity.MemberCompanies;
import com.interbanking.interbanking.model.repository.IMemberCompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberCompaniesServiceImpl implements IMemberCompanies {

    @Autowired
    private IMemberCompaniesRepository memberCompaniesRepository;
    @Override
    public MemberCompanies save(MemberCompanies memberCompanies) {
        return memberCompaniesRepository.save(memberCompanies);
    }

    @Override
    public List<MemberCompanies> findByLastMonth(Date date) {
        return memberCompaniesRepository.findByLastMonth(date);
    }
}
