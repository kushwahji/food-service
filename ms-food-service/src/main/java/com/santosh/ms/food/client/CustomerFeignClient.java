package com.santosh.ms.food.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.santosh.ms.food.response.CustomerResponse;



@FeignClient(name = "http://MS-CUSTOMER/customer/")
public interface CustomerFeignClient {

    @GetMapping("{phone}")
    CustomerResponse getCustomer(@RequestParam String phone);

}
