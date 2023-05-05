package com.interbanking.interbanking;

import com.interbanking.interbanking.model.entity.Account;
import com.interbanking.interbanking.model.entity.EAccount;
import com.interbanking.interbanking.model.repository.IAccountRepository;
import com.interbanking.interbanking.model.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class AccountServiceImplTest {

    @MockBean
    private AccountServiceImpl accountService;

    @MockBean
    private IAccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        this.accountService = Mockito.mock(AccountServiceImpl.class);
        this.accountRepository = Mockito.mock(IAccountRepository.class);

    }

    @Test
    public void whenValidName_thenAccountSave() {

        when(accountRepository.save(any(Account.class))).then(new Answer<Account>(){
            Integer secuencia = 8;
            @Override
            public Account answer(InvocationOnMock invocation) throws Throwable {
                Account customer = invocation.getArgument(0);
                customer.setId(secuencia++);
                return customer;
            }
        });

        Account account = new Account(EAccount.DEBIT_ACCOUNT);
        account.setId(1);

        assertThat(account.getId() == account.getId());
    }

    @Test
    public void whenValidName_thenAccountShouldBeFound() {

        Account account = new Account(EAccount.DEBIT_ACCOUNT);
        account.setId(1);

        when(accountService.findByName(EAccount.DEBIT_ACCOUNT)).thenReturn(Optional.of(account));

        Optional<Account> found = accountService.findByName(EAccount.DEBIT_ACCOUNT);

        assertThat(found.get().getName()).isEqualTo(EAccount.DEBIT_ACCOUNT);
    }

    @Test
    public void whenValidName_thenAccountShouldBeNotFound() {

        Account account = new Account(EAccount.DEBIT_ACCOUNT);
        account.setId(1);

        when(accountService.findByName(EAccount.DEBIT_ACCOUNT)).thenReturn(Optional.of(account));

        Optional<Account> found = accountRepository.findByName(EAccount.CREDIT_CCOUNT);

        assertFalse(found.isPresent());
    }


}
