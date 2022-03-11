package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

import exception.DaoException;

@Repository
@Scope("singleton")
public class ClientDao {private static ClientDao instance = null;

private ClientDao() {
}

private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
private static final String COUNT_CLIENTS = "SELECT COUNT(*) AS count FROM Client;";
private static final String DELETE_CLIENTS_QUERY = "DELETE FROM Client WHERE id = ?;";
private static final String EDIT_CLIENT_QUERY = "UPDATE Client SET nom = ?, prenom = ?, email = ?, naissance = ? WHERE id\n"
		+ "= ?;";

public int count() throws DaoException {
	int n = 0;

	try {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(COUNT_CLIENTS);
		// pstmt.setInt(1,id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();

		n = rs.getInt("count");
		conn.close();

	} catch (SQLException e) {
		e.printStackTrace();

	}

	return n;
}

public long create(Client client) throws DaoException {
	long ret = 0;
	if(client.isAdult() && emailAlreadyExist(client)==false && client.lengthIsMoreThan3(client.getLastName()) && client.lengthIsMoreThan3(client.getFirstName())) {
	try {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(CREATE_CLIENT_QUERY);


		pstmt.setString(1, client.getLastName());
		pstmt.setString(2, client.getFirstName());
		pstmt.setString(3, client.getEmail());
		pstmt.setString(4, client.getBirthDate().toString());

		ret = pstmt.executeUpdate();
		


	} catch (SQLException e) {
		e.printStackTrace();
		throw new DaoException();
	}}
	return ret;
}

public long edit(int id, Client client) throws DaoException {
	long ret = 0;

	try {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(EDIT_CLIENT_QUERY);
		
		
		pstmt.setString(3, client.getEmail());			
		pstmt.setString(1, client.getLastName());
		pstmt.setString(2, client.getFirstName());
		pstmt.setString(4, client.getBirthDate().toString());
		pstmt.setInt(5, id);

	

		ret = pstmt.executeUpdate();
		return ret;

	} catch (SQLException e) {
		e.printStackTrace();
		throw new DaoException();
	}

}

public long delete(Client client) throws DaoException {
	long ret = 0;

	try {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENTS_QUERY);

		pstmt.setInt(1, client.getId());
		ret = pstmt.executeLargeUpdate();

	} catch (SQLException e) {
		throw new DaoException();
	}
	return ret;

}

public Client findById(int id) throws DaoException {

	try {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);
		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();
		rs.next();

		String clientName = rs.getString("nom");
		String clientSurname = rs.getString("prenom");
		String clientEmail = rs.getString("email");
		LocalDate clientNaissance = rs.getDate("naissance").toLocalDate();

		Client client = new Client(id, clientName, clientSurname, clientEmail, clientNaissance);

		return client;

	} catch (SQLException e) {
		e.printStackTrace();
		throw new DaoException();
	}
}

public List<Client> findAll() throws DaoException {// notifie à la classe appelante attention ceci jette une
													// exception

	List<Client> listClients = new ArrayList<>();
	try {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENTS_QUERY);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int clientid = rs.getInt("id");
			String clientLastname = rs.getString("nom");
			String clientFirstname = rs.getString("prenom");
			String clientEmail = rs.getString("email");
			LocalDate clientNaissance = rs.getDate("naissance").toLocalDate();

			Client client = new Client(clientid, clientLastname, clientFirstname, clientEmail, clientNaissance);

			listClients.add(client);
		}
		return listClients;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

private boolean emailAlreadyExist(Client client) {

	boolean emailAlreadyExist = false;

	List<Client> listClient = new ArrayList();
	try {
		listClient = findAll();
	} catch (DaoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	for(Client clientToTest : listClient) {
		if(client.getEmail().equals(clientToTest.getEmail()))
		{
			emailAlreadyExist = true;
			break;
		}
	}
	return emailAlreadyExist;
}



}
