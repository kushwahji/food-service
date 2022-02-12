/**
 * 
 */
package com.santosh.ms.product.request;

import com.santosh.ms.product.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequestCategoryDto {
    private String categoryName;

    public ProductRequestCategoryDto(Category category) {
        this.categoryName = category.getCategoryName();
    }
}
