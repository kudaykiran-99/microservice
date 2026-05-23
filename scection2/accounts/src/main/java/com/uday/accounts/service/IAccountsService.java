package com.uday.accounts.service;


import com.uday.accounts.dtos.CustomerDto;

public interface IAccountsService {


    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);


    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);


}
