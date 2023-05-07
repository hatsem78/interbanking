package com.interbanking.interbanking.controller;


import com.interbanking.interbanking.exceptionscustom.CompanyNotFoundException;
import com.interbanking.interbanking.exceptionscustom.EntityNotFoundException;
import com.interbanking.interbanking.model.entity.Company;
import com.interbanking.interbanking.model.entity.MemberCompanies;
import com.interbanking.interbanking.model.entity.Transfer;
import com.interbanking.interbanking.model.service.CompanyServiceImpl;
import com.interbanking.interbanking.model.service.MemberCompaniesServiceImpl;
import com.interbanking.interbanking.model.service.TransferServiceImpl;
import com.interbanking.interbanking.request.MemberCompanyRequest;
import com.interbanking.interbanking.response.MemberCompaniesResponse;
import com.interbanking.interbanking.response.MessageResponse;
import com.interbanking.interbanking.response.ResponseRequest;
import com.interbanking.interbanking.response.TransferRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static com.interbanking.interbanking.utils.SiteLocale.getLastMonth;

@Slf4j
@Validated
@RestController
@RequestMapping("/api")
public class InterbankingRestController {

    private final TransferServiceImpl transferService;

    private final MemberCompaniesServiceImpl memberCompaniesService;

    private final CompanyServiceImpl companyService;


    public InterbankingRestController(
            TransferServiceImpl transferService,
            MemberCompaniesServiceImpl memberCompaniesService,
            CompanyServiceImpl companyService
    ) {
        this.transferService = transferService;
        this.memberCompaniesService = memberCompaniesService;
        this.companyService = companyService;
    }

    @GetMapping(value = {"/index", "/", "/home"})
    public String index() {
        return "Welcome to the technical challenge Interbanking";
    }

    @GetMapping("/companiesMadeTransfersLastMonth")
    public ResponseEntity<List<TransferRequest>> companiesMadeTransfersLastMonth() throws EntityNotFoundException {

        log.info("Customer Controller - companies Made Transfers Last Month ;");

        ResponseRequest response = new ResponseRequest(
                300,
                "Customer relationship create ok"
        );


        String month = getLastMonth(0).getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );

        List<Transfer> responseEntity = transferService.findByLastMonth(getLastMonth(1).getTime());
        if (responseEntity.isEmpty()) {
            ResponseRequest documentResponse = new ResponseRequest(
                    400,
                    "No record found for the last month: " + month
            );
            ResponseEntity responseEntitys = new ResponseEntity<>(documentResponse, HttpStatus.BAD_REQUEST);

            return responseEntitys;
        }

        Stream<TransferRequest> transferStream = responseEntity.stream()
                .map(transfer -> {
                    TransferRequest transferRequest = new TransferRequest(
                            transfer.getId(),
                            transfer.getAmount(),
                            transfer.getTransferDate().toString(),
                            transfer.getCompanyId().stream().mapToLong(
                                    Company::getId
                            ),
                            transfer.getCompanyId().stream().map(
                                company-> { return company.getBusinessName();}
                            )
                    );
                    return transferRequest;
                });
        List<TransferRequest> transferRequestList = transferStream.collect(Collectors.toList());

        return new ResponseEntity<>(transferRequestList, HttpStatus.OK);
    }

    @GetMapping("/companiesJoinedLastMonth")
    public ResponseEntity<List<MemberCompaniesResponse>> companiesJoinedLastMonth() throws EntityNotFoundException {

        log.info("Customer Controller - companies that joined in the last month ;");

        String month = getLastMonth(0).getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );

        List<MemberCompanies> responseEntity = memberCompaniesService.findByLastMonth(getLastMonth(1).getTime());
        if (responseEntity.isEmpty()) {
            ResponseRequest documentResponse = new ResponseRequest(
                    400,
                    "No record found for the last month: " + month
            );
            ResponseEntity responseEntitys = new ResponseEntity<>(documentResponse, HttpStatus.BAD_REQUEST);

            return responseEntitys;
        }

        Stream<MemberCompaniesResponse> memberCompaniesResponseStream = responseEntity.stream()
                .map(memberCompanies -> {
                    MemberCompaniesResponse transferRequest = new MemberCompaniesResponse(
                            memberCompanies.getMemberDate().toString(),
                            memberCompanies.getCompany().stream().mapToLong(
                                    Company::getId
                            ),
                            String.valueOf(memberCompanies.getCompany().stream().map(
                                    company-> { return Stream.of(company.getBusinessName());}
                            ))
                    );
                    return transferRequest;
                }).peek(System.out::println);
        List<MemberCompaniesResponse> memberCompaniesResponses = memberCompaniesResponseStream.collect(Collectors.toList());

        return new ResponseEntity<>(memberCompaniesResponses, HttpStatus.OK);
    }

    @PostMapping("/add/MemberCompany")
    public ResponseEntity<?> addMemberCompany(
            @Valid @RequestBody MemberCompanyRequest company
    ) throws EntityNotFoundException {

        log.info("Customer Controller - add Member Company - Begin ;");

        ResponseRequest response = new ResponseRequest(
                101,
                "Add Member Company ok"
        );


        Set<Company> setCompany = Collections.singleton(companyService.findById(
                company.getCompanyId()).orElseThrow(() -> new CompanyNotFoundException("Error: Company is not found."))
        );

        MemberCompanies memberCompanies = new MemberCompanies(
                setCompany,
                new Date()
        );

        memberCompaniesService.save(memberCompanies);


        return  new ResponseEntity<>(response, HttpStatus.OK);

    }
}
