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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import exception.DaoException;
import exception.ServiceException;

@WebServlet("/editRent")
public class EditReservationServlet extends HttpServlet {

	@Autowired
	ReservationService reservationService;
	@Autowired
	VehicleService vehicleService;
	@Autowired
	ClientService clientService;
	private static final long serialVersionID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	Reservation reservationInfoToEdit = new Reservation();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("listVehicles", vehicleService.findAll());
			request.setAttribute("listClients", clientService.findAll());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("./WEB-INF/views/rents/edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		reservationInfoToEdit.setIdClient(Integer.parseInt(request.getParameter("clientId")));
		reservationInfoToEdit.setIdVehicle(Integer.parseInt(request.getParameter("vehicleId")));
		reservationInfoToEdit.setDateStart(LocalDate.parse(request.getParameter("start")));
		reservationInfoToEdit.setDateEnd(LocalDate.parse(request.getParameter("end")));

		try {
			request.setAttribute("editVehicle", reservationService.edit(id, reservationInfoToEdit));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);

	}

}
