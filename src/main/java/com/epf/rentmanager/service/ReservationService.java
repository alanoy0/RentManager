package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

import exception.DaoException;
import exception.ServiceException;

@Service
public class ReservationService {

	private ReservationDao reservationDao;

	private ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
	
		public long createReservation(Reservation reservation) throws ServiceException {
			
			try {
				return this.reservationDao.create(reservation);
			} catch (DaoException e) {
				e.printStackTrace();
			}
			return 0;
		}

		public long deleteReservation(Reservation reservation) throws ServiceException {

			try {
				return this.reservationDao.delete(reservation);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
		
		
		public List<Reservation> findAllReservations() throws ServiceException {
			try {
				return this.reservationDao.findAll();
			} catch (DaoException e) {
				e.printStackTrace();
			}
			return null;

		}
		
		public List<Reservation> findReservationsByVehicleId(int vehicleId) throws ServiceException {
			try {
				return this.reservationDao.findResaByVehicleId(vehicleId);
			} catch (DaoException e) {
				e.printStackTrace();
			}
			return null;

		}
		
		public List<Reservation> findReservationsByClientId(int clientId) throws ServiceException {
			try {
				return this.reservationDao.findResaByClientId(clientId);
			} catch (DaoException e) {
				e.printStackTrace();
			}
			return null;

		}
		
		public List<Reservation> updateReservationListWhenClientDelete(Client client) {
			return this.reservationDao.updateReservationListWhenClientDelete(client);
		}
		
		public Long edit(int id, Reservation reservation) throws DaoException {
			try {
		return this.reservationDao.edit(id, reservation);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


return null;
}
	
	

}
