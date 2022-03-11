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

@WebServlet("/deleteVehicle")
public class DeleteVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Vehicle vehicle = new Vehicle();
	@Autowired
	VehicleService vehicleService;
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("./WEB-INF/views/vehicles/delete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
				try {
					int idCar = Integer.parseInt(request.getParameter("idVehicle"));
					for (int i = 0; i < vehicleService.findAll().size(); i++) 
						 {
						if(vehicleService.findAll().get(i).getId() == idCar) {
							vehicle = vehicleService.findAll().get(i);
						}
						 }
					request.setAttribute("deleteVehicle", vehicleService.delete(vehicle));
					
					}
				 catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				doGet(request,response);
				}

}