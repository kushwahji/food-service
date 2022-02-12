/**
 * 
 */
package com.santosh.ms.food.product.exception;

import feign.Response;

/**
 * @author santosh.kushwah
 * @since 19-01-2022
 */
public interface FeignHttpExceptionHandler {
	 Exception handle(Response response);
}
