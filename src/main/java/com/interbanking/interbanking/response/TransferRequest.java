package com.interbanking.interbanking.response;

import java.util.Date;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TransferRequest {

    private Long transferId;
    private Double amount;
    private String transferDate;
    private LongStream companyId;

    private Stream<String> companyName;

    public TransferRequest(
            Long transferId,
            Double amount,
            String transferDate,
            LongStream companyId,
            Stream<String> companyName
    ) {
        this.transferId = transferId;
        this.amount = amount;
        this.transferDate = transferDate;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public LongStream getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongStream companyId) {
        this.companyId = companyId;
    }

    public Stream<String> getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Stream<String> companyName) {
        this.companyName = companyName;
    }
}
