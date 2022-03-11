package com.epf.rentmanager.utils.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import exception.ServiceException;

@WebServlet("/deleteRent")
public class DeleteReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	@Autowired
	ReservationService reservationService;
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	Reservation reservation;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("./WEB-INF/views/rents/delete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
				try {
					int id = Integer.parseInt(request.getParameter("idRent"));
					for (int i = 0; i < reservationService.findAllReservations().size(); i++) 
						 {
						if(reservationService.findAllReservations().get(i).getId() == id) {
							reservation = reservationService.findAllReservations().get(i);
						}
						 }
					request.setAttribute("deleteRent", reservationService.deleteReservation(reservation));
					
					}
				 catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				doGet(request,response);
				}

}