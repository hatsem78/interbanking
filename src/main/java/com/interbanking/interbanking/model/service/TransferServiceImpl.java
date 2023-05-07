package com.interbanking.interbanking.model.service;

import com.interbanking.interbanking.model.entity.Transfer;
import com.interbanking.interbanking.model.repository.ITransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class TransferServiceImpl implements ITransfer {

    @Autowired
    private ITransferRepository transferRepository;

    @Override
    public Transfer save(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public Optional<Transfer> findByDateToCompanyId(Date date, Long companyId) {
        return transferRepository.findByTransferDateAndCompanyId(date, companyId);
    }

    @Override
    public Optional<Transfer> findByDate(Date date) {
        return transferRepository.findByTransferDate(date);
    }

    @Override
    public List<Transfer> findByLastMonth(Date date) {
        return transferRepository.findByLastMonth(date);
    }

    @Override
    public List<Transfer> findByAll() {
        return transferRepository.findAll();
    }


}
