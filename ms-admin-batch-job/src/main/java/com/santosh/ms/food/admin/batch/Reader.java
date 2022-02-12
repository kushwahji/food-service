package com.santosh.ms.food.admin.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import com.santosh.ms.food.admin.entity.Food;

public class Reader extends FlatFileItemReader<Food> {
	public Reader(Resource resource) {
		setResource(resource);

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "fid", "vendorId", "name", "description", "price" });
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);

		BeanWrapperFieldSetMapper<Food> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Food.class);

		DefaultLineMapper<Food> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		setLineMapper(defaultLineMapper);
	}
}
