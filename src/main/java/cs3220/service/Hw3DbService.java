package cs3220.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cs3220.model.*;

public class Hw3DbService {
	// login info is no longer available for security reasons plus the sql server no longer being active
	private String url = "";

	private String username = "";

	private String password = "";

	private Connection connection;

	public Hw3DbService() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ----Vaccine Related calls----//

	public List<VaccineListEntry> getVaccineEntries() {
		List<VaccineListEntry> vaccines = new ArrayList<VaccineListEntry>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from vaccines");
			while (rs.next()) {
				VaccineListEntry x = new VaccineListEntry();
				x.setId(rs.getInt("id"));
				x.setVaccineName(rs.getString("vaccine_name"));
				x.setDosesRequired(rs.getInt("doses_required"));
				x.setDaysBetweenDoses(rs.getInt("days_between_doses"));
				x.setDosesRecieved(rs.getInt("doses_recieved"));
				x.setDosesLeft(rs.getInt("doses_left"));
				vaccines.add(x);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vaccines;
	}

	public int addVaccine(String vaccineName, int dosesRequired, int daysBetween) {
		int id = 0;

		try {
			String sql = "insert into vaccines (vaccine_name, doses_required, days_between_doses, doses_recieved, doses_left) values (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, vaccineName);
			pstmt.setInt(2, dosesRequired);
			pstmt.setInt(3, daysBetween);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public VaccineListEntry getVaccine(int id) {
		VaccineListEntry x = new VaccineListEntry();
		try {
			String sql = "select * from vaccines where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				x.setId(rs.getInt("id"));
				x.setVaccineName(rs.getString("vaccine_name"));
				x.setDosesRequired(rs.getInt("doses_required"));
				x.setDaysBetweenDoses(rs.getInt("days_between_doses"));
				x.setDosesRecieved(rs.getInt("doses_recieved"));
				x.setDosesLeft(rs.getInt("doses_left"));
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return x;
	}

	public void updateVaccine(int id, String vaccineName, int dosesRequired, int daysBetweenDoses) {
		try {
			String sql = "update vaccines set vaccine_name = ?, doses_required = ?, days_between_doses = ? where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vaccineName);
			pstmt.setInt(2, dosesRequired);
			pstmt.setInt(3, daysBetweenDoses);
			pstmt.setInt(4, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addDoses(String vaccineName, int dosesRecieved) {
		try {
			String sql = "update vaccines set doses_recieved = doses_recieved + ?, doses_left = doses_left + ? where vaccine_name = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, dosesRecieved);
			pstmt.setInt(2, dosesRecieved);
			pstmt.setString(3, vaccineName);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// -----------------------------//

	public List<PatientEntryModel> getPatients() {
		List<PatientEntryModel> patient = new ArrayList<PatientEntryModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select p.id, p.patient, p.vaccine_name, p.first_dose, p.second_dose, v.doses_required, v.doses_left from patients p, vaccines v where v.vaccine_name = p.vaccine_name");
			while (rs.next()) {
				PatientEntryModel x = new PatientEntryModel();
				x.setId(rs.getInt("id"));
				x.setName(rs.getString("patient"));
				x.setVaccine(rs.getString("vaccine_name"));
				x.setFirstDose(rs.getString("first_dose"));
				x.setSecondDose(rs.getString("second_dose"));
				x.setVaccineDosesRequired(rs.getInt("doses_required"));
				x.setVaccineDosesLeft(rs.getInt("doses_left"));
				patient.add(x);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patient;

	}
	
	public int addPatient(String name, String vaccineName, String date, String secondDose, int dosesLeft) {
		int id = 0;

		try {
			String sql = "insert into patients (patient, vaccine_name, first_dose, second_dose) values (?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, name);
			pstmt.setString(2, vaccineName);
			pstmt.setString(3, date);
			pstmt.setString(4, "no");
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			updateDosesLeft(dosesLeft, vaccineName);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public void updateDosesLeft(int dosesLeft, String vaccineName) {
		try {
			String sql = "update vaccines set doses_left = ? where vaccine_name = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, dosesLeft);
			pstmt.setString(2, vaccineName);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void recievedVaccine(int id, String date, String vaccine, int doses) {
		try {
			recievedVaccineHelper(vaccine, doses);
			String sql = "update patients set second_dose = ? where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void recievedVaccineHelper(String vaccine, int doses) {
		try {
			String sql = "update vaccines set doses_left = ? where vaccine_name = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, doses);
			pstmt.setString(2, vaccine);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
