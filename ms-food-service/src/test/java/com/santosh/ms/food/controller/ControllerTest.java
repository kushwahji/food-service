/**
 * 
 */
package com.santosh.ms.food.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.santosh.ms.food.BaseContextLoader;

/**
 * @author santosh.kushwah
 *
 */
public class ControllerTest extends BaseContextLoader {

	@Mock
	OrderController orderController;
	
	@Test
	void test(){
		Mockito.when(orderController.myOrder(Mockito.anyString())).thenReturn(null);
	}
}
