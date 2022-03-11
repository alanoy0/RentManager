package com.epf.rentmanager.utils.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import exception.ServiceException;

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {

	@Autowired
	ClientService clientService;
	
	
	private static final long serialVersionID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	

	Client newClient = new Client();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("./WEB-INF/views/users/create.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			newClient.setLastName(request.getParameter("last_name"));
			newClient.setFirstName(request.getParameter("first_name"));
			newClient.setEmail(request.getParameter("email"));
			newClient.setBirthDate(LocalDate.parse(request.getParameter("birthdate")));

			request.setAttribute("submitClient", clientService.create(newClient));

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);

	}

}
