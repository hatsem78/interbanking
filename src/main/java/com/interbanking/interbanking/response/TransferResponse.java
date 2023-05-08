package com.interbanking.interbanking.response;

public class TransferResponse {

    private Long transferId;
    private Double amount;
    private String transferDate;
    private CompanyResponse company;

    public TransferResponse(
            Long transferId,
            Double amount,
            String transferDate,
            CompanyResponse company
    ) {
        this.transferId = transferId;
        this.amount = amount;
        this.transferDate = transferDate;
        this.company = company;
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
    public CompanyResponse getCompany() {
        return company;
    }
    public void setCompany(CompanyResponse company) {
        this.company = company;
    }
}
