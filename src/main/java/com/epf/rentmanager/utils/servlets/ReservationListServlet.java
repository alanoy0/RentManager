package com.epf.rentmanager.utils.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import exception.ServiceException;

@WebServlet("/rents")
public class ReservationListServlet extends HttpServlet{
	
	@Autowired
	ReservationService reservationService;
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
;	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			request.setAttribute("listRents", reservationService.findAllReservations());
		
			request.getRequestDispatcher("./WEB-INF/views/rents/list.jsp").forward(request, response);
			}
		catch(ServiceException e){
			e.printStackTrace();
			
		}
}
}
