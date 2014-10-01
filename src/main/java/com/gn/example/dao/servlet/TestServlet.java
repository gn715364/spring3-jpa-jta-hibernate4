package com.gn.example.dao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.gn.example.dao.entity.TableOne;
import com.gn.example.dao.entity.TableTwo;
import com.gn.example.dao.service.TransactionalService;

@Component("testServlet")
public class TestServlet implements HttpRequestHandler {
	
	@Autowired
	private TransactionalService transactionalService;
	
	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		TableOne tableOne = new TableOne();
		tableOne.setValue("value1");
		
		TableTwo tableTwo = new TableTwo();
		tableTwo.setValue("value2");
		
		try {
			transactionalService.persist(tableOne, tableTwo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
