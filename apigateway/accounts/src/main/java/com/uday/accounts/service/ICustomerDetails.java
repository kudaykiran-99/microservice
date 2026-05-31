package com.uday.accounts.service;

import com.uday.accounts.dto.CustomerDetails;
import org.springframework.stereotype.Service;

@Service
public interface ICustomerDetails {

    public CustomerDetails getCustomerDetails(String mobileNumber, String correlationId);
}
