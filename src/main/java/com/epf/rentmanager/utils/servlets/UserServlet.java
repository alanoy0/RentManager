package com.epf.rentmanager.utils.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.service.ClientService;

import exception.ServiceException;

@WebServlet("/users")
public class UserServlet extends HttpServlet{
private static final long serialVersionUID = 1L;

@Autowired
ClientService clientService;
private static final long serialVersionID = 1L;

@Override
public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			request.setAttribute("listUsers", this.clientService.findAll());
		
			request.getRequestDispatcher("./WEB-INF/views/users/list.jsp").forward(request, response);
		}
		catch(ServiceException e){
			e.printStackTrace();
			
		}
					
}
}
