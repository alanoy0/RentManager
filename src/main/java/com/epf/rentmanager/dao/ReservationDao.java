package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

import exception.DaoException;

@Repository
@Scope("singleton")
public class ReservationDao {

	private ReservationDao() {

	}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String EDIT_RESERVATION_QUERY = "UPDATE Reservation SET client_id = ?, vehicle_id = ?, debut = ?, fin = ? WHERE id\n"
			+ "= ?;";
	
	public long create(Reservation reservation) throws DaoException {
		long ret = 0;
		if(isLessThan7Days(reservation)) {
			try (Connection conn = ConnectionManager.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY);) {

				pstmt.setInt(1, reservation.getIdClient());
				pstmt.setInt(2, reservation.getIdVehicle());
				pstmt.setString(3, reservation.getDateStart().toString());
				pstmt.setString(4, reservation.getDateEnd().toString());

				ret = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ret;
	}

	private boolean isLessThan7Days(Reservation reservation) {

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(Date.from(reservation.getDateStart().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		end.setTime(Date.from(reservation.getDateEnd().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		int days = end.get(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR);
		end.add(Calendar.DAY_OF_YEAR, -days);
		if (start.after(end)) {
			days = days - 1;
		}

		if (days < 8) {
			return true;
		} else {
			return false;
		}

	}

	public long delete(Reservation reservation) throws DaoException {
		long ret = 0;
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);) {
			pstmt.setInt(1, reservation.getId());
			pstmt.executeUpdate();
			ret = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;
	}

	public List<Reservation> findResaByClientId(int clientId) throws DaoException { /////// a modifier

		List<Reservation> reservationsByClientId = new ArrayList();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);) {

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getInt("client_id") == clientId) {
					int reservationId = rs.getInt("id");
					int reservationClientId = rs.getInt("client_id");
					int reservationVehicleId = rs.getInt("vehicle_id");
					LocalDate reservationStartDate = rs.getDate("debut").toLocalDate();
					LocalDate reservationEndDate = rs.getDate("fin").toLocalDate();
					Reservation reservation = new Reservation(reservationId, reservationClientId, reservationVehicleId,
							reservationStartDate, reservationEndDate);
					reservationsByClientId.add(reservation);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservationsByClientId;
	}

	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		List<Reservation> reservationsByVehicleId = new ArrayList();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);) {

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getInt("vehicle_id") == vehicleId) {
					int reservationId = rs.getInt("id");
					int reservationClientId = rs.getInt("client_id");
					int reservationVehicleId = rs.getInt("vehicle_id");
					LocalDate reservationStartDate = rs.getDate("debut").toLocalDate();
					LocalDate reservationEndDate = rs.getDate("fin").toLocalDate();
					Reservation reservation = new Reservation(reservationId, reservationClientId, reservationVehicleId,
							reservationStartDate, reservationEndDate);
					reservationsByVehicleId.add(reservation);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservationsByVehicleId;
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);) {

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int reservationId = rs.getInt("id");
				int reservationClientId = rs.getInt("client_id");
				int reservationVehicleId = rs.getInt("vehicle_id");
				LocalDate reservationStartDate = rs.getDate("debut").toLocalDate();
				LocalDate reservationEndDate = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(reservationId, reservationClientId, reservationVehicleId,
						reservationStartDate, reservationEndDate);
				reservations.add(reservation);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservations;
	}
	
	public List<Reservation> updateReservationListWhenClientDelete(Client client) {
		List<Reservation> reservationsWithClientId = new ArrayList();
		try {
			reservationsWithClientId = findResaByClientId(client.getId());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Reservation reservationToDelete : reservationsWithClientId) {
		try {
			delete(reservationToDelete);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	return reservationsWithClientId;
}
	

	public long edit(int id, Reservation reservation) throws DaoException, SQLException {
		long ret = 0;
		if(isLessThan7Days(reservation)) {
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(EDIT_RESERVATION_QUERY);) {


			pstmt.setInt(5, id);
			pstmt.setString(4, reservation.getDateEnd().toString());
			pstmt.setString(3, reservation.getDateStart().toString());
			pstmt.setInt(1, reservation.getIdClient());
			pstmt.setInt(2, reservation.getIdVehicle());

			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}}

		return ret;
	}
	}
