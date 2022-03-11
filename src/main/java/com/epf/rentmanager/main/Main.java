package com.epf.rentmanager.main;

import java.time.LocalDate;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import exception.DaoException;
import exception.ServiceException;

public class Main {
	
	static ClientService clientService;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Création d'un client
		LocalDate today = LocalDate.now();
		Client client = new Client(45,"MLIO","Hai","haimlio@email.com",today); 
		
		//	System.out.println(clientService.edit(5,client));
		//System.out.println(ClientService.getInstance().findById(5)); // on vérifie que le client est bien créé
		
		//Find all client 
		//System.out.println(clientService.findAll());
		
		//Delete Client
		//System.out.println(clientService.delete(client));
		
		//Vehicle 
		//Chercher un vehicle
		//System.out.println(VehicleService.findById(1));
		
		//Find all Vehicle
		//System.out.println(VehicleService.findAll());
		
		//Create a vehicle
		Vehicle vehicle = new Vehicle(5,"Peugot", (short) 3);
		//System.out.println(VehicleService.getInstance().create(vehicle));
		//System.out.println(vehicleService.findAll());
		
		//Delete vehicle
		//System.out.println(VehicleService.getInstance().delete(VehicleService.getInstance().findAll().get(2)));
		//System.out.println(vehicleService.findAll());
		
		
		//Reservation
		//System.out.println(VehicleService.findAllReservations());
		Reservation reservation = new Reservation(2, 1, 1, today, today);
		//System.out.println(VehicleService.getInstance().createReservation(reservation));

	}

}
