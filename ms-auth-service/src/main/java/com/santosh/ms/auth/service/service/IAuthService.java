/**
 * 
 */
package com.santosh.ms.auth.service.service;

import com.santosh.ms.auth.service.request.UserLoginDto;
import com.santosh.ms.auth.service.request.UserRegisterDto;

/**
 * @author santosh.kushwah
 * @since 11-02-2022
 */
public interface IAuthService {

	Object login(UserLoginDto requeut);

	Object register(UserRegisterDto request);

}
