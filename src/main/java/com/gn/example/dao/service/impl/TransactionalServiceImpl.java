package com.gn.example.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gn.example.dao.TableOneDao;
import com.gn.example.dao.TableTwoDao;
import com.gn.example.dao.entity.TableOne;
import com.gn.example.dao.entity.TableTwo;
import com.gn.example.dao.service.TransactionalService;

@Service
public class TransactionalServiceImpl implements TransactionalService {

	@Autowired
	private TableOneDao tableOneDao;
	
	@Autowired
	private TableTwoDao tableTwoDao;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void persist(TableOne tableOne, TableTwo tableTwo) throws Exception {
		tableOneDao.save(tableOne);
		tableTwoDao.save(tableTwo);
	}

}
