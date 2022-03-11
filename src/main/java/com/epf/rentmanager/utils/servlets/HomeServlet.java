package com.epf.rentmanager.utils.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import exception.ServiceException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionID = 1L;
	
	@Autowired
	ClientService clientService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	VehicleService vehicleService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("listUsers", clientService.findAll().size());
			request.setAttribute("listVehicles", vehicleService.findAll().size());
			request.setAttribute("listReservations", reservationService.findAllReservations().size());
			request.getRequestDispatcher("./WEB-INF/views/home.jsp").forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}