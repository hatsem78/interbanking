package com.interbanking.interbanking.model.service;

import com.interbanking.interbanking.model.entity.MemberCompanies;
import com.interbanking.interbanking.model.repository.IMemberCompaniesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
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
