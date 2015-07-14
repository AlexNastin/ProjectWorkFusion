package com.workfusion.project.dao;

import com.workfusion.project.domain.Value;

public interface IValueDAO {

	Value getValue(int idValue);

	int updateValue(Value value);

	int insertValue(Value value);

}
