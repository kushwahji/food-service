/**
 * 
 */
package com.santosh.ms.api.gateway.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {
	private String errorDetail;
	private Object errorMoreInfo;
}
