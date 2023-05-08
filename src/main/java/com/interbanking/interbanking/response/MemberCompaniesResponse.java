package com.interbanking.interbanking.response;

import java.util.Date;
import java.util.stream.LongStream;

public class MemberCompaniesResponse {

    private String memberDate;
    private CompanyResponse company;

    public MemberCompaniesResponse(
            String memberDate,
            CompanyResponse company
    ) {
        this.memberDate = memberDate;
        this.company = company;

    }

    public String getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(String memberDate) {
        this.memberDate = memberDate;
    }

    public CompanyResponse getCompany() {
        return company;
    }

    public void setCompany(CompanyResponse company) {
        this.company = company;
    }
}
