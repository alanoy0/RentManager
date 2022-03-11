package com.epf.rentmanager.utils.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

import exception.ServiceException;


@WebServlet("/createVehicle")
public class CreateVehiclesServlet extends HttpServlet{

	@Autowired
	VehicleService vehicleService;
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	Vehicle newVehicle = new Vehicle();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("./WEB-INF/views/vehicles/create.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			try {
				
				newVehicle.setConstructor(request.getParameter("manufacturer"));
				newVehicle.setNbPlaces(Short.valueOf(request.getParameter("seats")));
				newVehicle.setId(vehicleService.findAll().get(vehicleService.findAll().size()-1).getId()+1);
				
				request.setAttribute("submitVehicle", vehicleService.create(newVehicle));				
				
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doGet(request,response);
		
	}
}