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

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import exception.ServiceException;

@WebServlet("/createRent")
public class CreateReservationServlet extends HttpServlet{
	
	@Autowired
	ReservationService reservationService;
	@Autowired
	VehicleService vehicleService;
	@Autowired
	ClientService clientService;
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	Reservation newReservation = new Reservation();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("listVehicles", vehicleService.findAll());
			request.setAttribute("listClients", clientService.findAll());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("./WEB-INF/views/rents/create.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			try {
				newReservation.setIdClient(Integer.parseInt(request.getParameter("clientId")));
				newReservation.setIdVehicle(Integer.parseInt(request.getParameter("vehicleId")));
				newReservation.setDateStart(LocalDate.parse(request.getParameter("begin")));
				newReservation.setDateEnd(LocalDate.parse(request.getParameter("end")));
				
				request.setAttribute("submitResa", reservationService.createReservation(newReservation));
				
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doGet(request,response);
}
}
