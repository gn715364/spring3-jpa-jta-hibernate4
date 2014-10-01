package com.gn.example.dao.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.gn.example.dao.TableOneDao;
import com.gn.example.dao.entity.TableOne;

@Service
public class TableOneDaoImpl implements TableOneDao {

	private EntityManager entityManager;

	@PersistenceContext(unitName="PersistenceUnit1")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void save(TableOne tableOne) {
		entityManager.persist(tableOne);
	}

}
