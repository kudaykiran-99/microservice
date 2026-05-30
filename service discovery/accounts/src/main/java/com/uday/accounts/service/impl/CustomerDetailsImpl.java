package com.uday.accounts.service.impl;

import com.uday.accounts.dto.*;
import com.uday.accounts.entity.Accounts;
import com.uday.accounts.entity.Customer;
import com.uday.accounts.exception.ResourceNotFoundException;
import com.uday.accounts.mapper.AccountsMapper;
import com.uday.accounts.mapper.CustomerMapper;
import com.uday.accounts.repository.AccountsRepository;
import com.uday.accounts.repository.CustomerRepository;
import com.uday.accounts.service.ICustomerDetails;
import com.uday.accounts.service.client.CardsFeignClient;
import com.uday.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerDetailsImpl implements ICustomerDetails {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    @Override
    public CustomerDetails getCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetails cd = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetails());
        cd.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
       // ResponseEntity<LoansDto> l = loansFeignClient.fetchLoanDetails(mobileNumber);
       // cd.setLoansDto(l.getBody());
        ResponseEntity<CardsDto> c = cardsFeignClient.fetchCardDetails(mobileNumber);
        cd.setCardsDto(c.getBody());

      return cd;
    }
}
