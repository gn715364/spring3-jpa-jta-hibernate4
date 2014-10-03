package com.gn.example.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.gn.example.dao.TableTwoDao;
import com.gn.example.dao.entity.TableTwo;

@Service
public class TableTwoDaoImpl implements TableTwoDao {

	private EntityManager entityManager;

	@PersistenceContext(unitName="PersistenceUnit2")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void save(TableTwo tableTwo) throws Exception {
		entityManager.persist(tableTwo);
		//throw new Exception("Force transaction rollback");
	}

}
