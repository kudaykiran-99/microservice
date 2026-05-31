package com.uday.accounts.service.client;

import com.uday.accounts.dto.CardsDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")  // cards connect to eureka server and fetch the cards data
public interface CardsFeignClient {

    @GetMapping("/api/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("udaybank-correlation-id") String correlationid, @RequestParam
                                                     String mobileNumber);
}
