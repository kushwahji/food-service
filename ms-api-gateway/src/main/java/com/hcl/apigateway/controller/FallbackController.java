/**
 * 
 */
package com.hcl.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author santosh.kushwah
 *
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {
	
	@GetMapping("/account")
    public String accountServiceFallback(){
        return "Account service is down......";
    }
	
	@GetMapping("/fund")
    public String fundServiceFallback(){
        return "Fund service is down......";
    }

}
