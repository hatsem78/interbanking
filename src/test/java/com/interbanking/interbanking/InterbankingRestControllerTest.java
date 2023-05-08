package com.interbanking.interbanking;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.interbanking.interbanking.controller.InterbankingRestController;
import com.interbanking.interbanking.model.entity.Account;
import com.interbanking.interbanking.model.entity.Company;
import com.interbanking.interbanking.model.entity.EAccount;
import com.interbanking.interbanking.model.entity.Transfer;
import com.interbanking.interbanking.model.service.AccountServiceImpl;
import com.interbanking.interbanking.model.service.CompanyServiceImpl;
import com.interbanking.interbanking.model.service.MemberCompaniesServiceImpl;
import com.interbanking.interbanking.model.service.TransferServiceImpl;
import com.interbanking.interbanking.request.MemberCompanyRequest;
import com.interbanking.interbanking.response.ResponseRequest;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static com.interbanking.interbanking.utils.SiteLocale.getLastMonth;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class InterbankingRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private TransferServiceImpl transferService;

    @MockBean
    private CompanyServiceImpl companyService;

    @MockBean
    private AccountServiceImpl accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InterbankingRestController interbankingRestController;

    private MemberCompaniesServiceImpl memberCompaniesService;

    @Before
    public void setUp() throws Exception {

        this.interbankingRestController = Mockito.mock(InterbankingRestController.class);
        this.transferService = Mockito.mock(TransferServiceImpl.class);
        this.companyService = Mockito.mock(CompanyServiceImpl.class);
        this.accountService = Mockito.mock(AccountServiceImpl.class);
        this.memberCompaniesService = Mockito.mock(MemberCompaniesServiceImpl.class);


        mockMvc = MockMvcBuilders
                .standaloneSetup(
                        new InterbankingRestController(
                                transferService,
                                memberCompaniesService,
                                companyService
                        )
                )
                .build();
    }

    @Test
    public void whenFindAll_thenReturnCustomerListBadRequest() throws Exception {

        String month = getLastMonth(0).getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );

        mockMvc.perform(get("/api/companiesMadeTransfersLastMonth"))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.message", is("No record found for the last month: " + month)));
    }


    @Test
    public void add_member_company() throws Exception {
        MemberCompanyRequest customerDTOs = new MemberCompanyRequest(2L);

        ResponseRequest response = new ResponseRequest(
                101,
                "Add Member Company ok"
        );


        Company company = new Company(
                "20297453124",
                "prueba",
                new Date()
        );
        company.setId(1L);

        MemberCompanyRequest request = new MemberCompanyRequest(1L);

        doReturn(company)
                .when(companyService).save(company);

        doReturn(new ResponseEntity<ResponseRequest>(response, HttpStatus.OK))
                .when(interbankingRestController).addMemberCompany(request);

        when(companyService.findById(company.getId())).thenReturn(Optional.of(company));

        Optional<Company> found = companyService.findById(company.getId());

        String json = "{\n" +
                "\t\"companyId\": 1\n" +
                "\t\n" +
                "}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/add/MemberCompany")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.message", is("Add Member Company ok")));

    }


}
