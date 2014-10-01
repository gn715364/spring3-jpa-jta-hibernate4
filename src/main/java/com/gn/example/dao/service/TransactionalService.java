package com.gn.example.dao.service;

import com.gn.example.dao.entity.TableOne;
import com.gn.example.dao.entity.TableTwo;

public interface TransactionalService {

	void persist(TableOne tableOne, TableTwo tableTwo) throws Exception;
	
}
