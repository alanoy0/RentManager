package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.model.Client;

import exception.DaoException;
import exception.ServiceException;

@Service
public class ClientService {

	private ClientDao clientDao;

	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public long delete(Client client) throws ServiceException {

		try {
			return this.clientDao.delete(client);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.reservationService.updateReservationListWhenClientDelete(client);
		return 0;
	}

	public long create(Client client) throws ServiceException {
		// TODO: créer un client
		try {
			return this.clientDao.create(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public Client findById(int id) throws ServiceException {
		// TODO: récupérer un client par son id
		try {
			return this.clientDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Client> findAll() throws ServiceException {

		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public Long edit(int id, Client client) throws DaoException {
		return this.clientDao.edit(id, client);

	}

}
