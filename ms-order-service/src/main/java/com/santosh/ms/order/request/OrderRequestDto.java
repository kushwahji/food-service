/**
 * 
 */
package com.santosh.ms.order.request;

import java.util.List;

import com.santosh.ms.order.client.response.ItemDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 13-01-2022
 */
@Data
@NoArgsConstructor
public class OrderRequestDto {
	private String phone;
	private long accountNumber;
	private List<ItemDto> items;
}
