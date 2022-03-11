package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

import exception.DaoException;

@Repository
@Scope("singleton")
public class VehicleDao {

	private static VehicleDao instance = null;

	private VehicleDao() {
	}

	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String EDIT_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur = ?, nb_places = ? WHERE id\n"
			+ "= ?;";
	public long create(Vehicle vehicle) throws DaoException {
		long ret = 0;
		if(enougthSeats(vehicle)) {
			try (Connection conn = ConnectionManager.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY);) {

				pstmt.setString(1, vehicle.getConstructor());
				pstmt.setInt(2, vehicle.getNbPlaces());

				ret = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ret;
	}

	private boolean enougthSeats(Vehicle vehicle) {
		if (vehicle.getNbPlaces() > 1 && vehicle.getNbPlaces() < 10) {
			return true;
		} else {
			return false;
		}
	}
	public long delete(Vehicle vehicle) throws DaoException {
		long ret = 0;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY);) {
			pstmt.setInt(1, vehicle.getId());
			pstmt.executeUpdate();
			ret = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;
	}

	public Optional<Vehicle> findById(int id) throws DaoException {
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLE_QUERY);) {

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			rs.next();

			String vehicleConstructor = rs.getString("constructeur");
			short vehicleNbPlaces = rs.getShort("nb_places");

			Vehicle vehicle = new Vehicle(id, vehicleConstructor, vehicleNbPlaces);
			return Optional.of(vehicle);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<Vehicle> findById(long id) throws DaoException {
		return null;
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLES_QUERY);) {

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int vehicleID = rs.getInt("id");
				String vehicleConstructor = rs.getString("constructeur");
				short vehicleNb_Places = rs.getShort("nb_places");

				Vehicle vehicle = new Vehicle(vehicleID, vehicleConstructor, vehicleNb_Places);
				vehicles.add(vehicle);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vehicles;
	}
	
	public long edit(int id, Vehicle vehicle) throws DaoException, SQLException {
		long ret = 0;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(EDIT_VEHICLE_QUERY);) {

			//Client clientToEdit = findById(id);

			pstmt.setInt(3, id);
			pstmt.setString(1, vehicle.getConstructor());
			pstmt.setShort(2, vehicle.getNbPlaces());

			

			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

}
