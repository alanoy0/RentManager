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
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import exception.DaoException;
import exception.ServiceException;

@WebServlet("/editVehicle")
public class EditVehicleServlet extends HttpServlet {

	@Autowired
	VehicleService vehicleService;
	private static final long serialVersionID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	Vehicle vehicleInfoToEdit = new Vehicle();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("./WEB-INF/views/vehicles/edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		vehicleInfoToEdit.setConstructor(request.getParameter("constructor"));
		vehicleInfoToEdit.setNbPlaces(Short.valueOf(request.getParameter("seats")));

		try {
			request.setAttribute("editVehicle", vehicleService.edit(id, vehicleInfoToEdit));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);

	}

}
