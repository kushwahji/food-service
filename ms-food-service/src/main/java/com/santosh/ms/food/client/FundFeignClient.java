package com.santosh.ms.food.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.santosh.ms.food.request.FundTransferDto;
import com.santosh.ms.food.response.FundTransferResponse;
							
@FeignClient(name = "http://MS-FUNDTRANSFER/fund")
public interface FundFeignClient {

    @PostMapping("/transfer")
    FundTransferResponse transfer(@RequestBody FundTransferDto transfer);
}
