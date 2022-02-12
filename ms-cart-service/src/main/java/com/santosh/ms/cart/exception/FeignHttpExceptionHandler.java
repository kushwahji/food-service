/**
 * 
 */
package com.santosh.ms.cart.exception;

import feign.Response;

/**
 * @author santosh.kushwah
 * @since 19-01-2022
 */
public interface FeignHttpExceptionHandler {
	 Exception handle(Response response);
}
