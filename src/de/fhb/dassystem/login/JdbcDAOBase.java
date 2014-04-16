package de.fhb.dassystem.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class JdbcDAOBase {
	protected String	driverName;

	protected String	dbURL, dbUser, dbPassword;

	public JdbcDAOBase(String driverName, String dbURL, String dbUser, String dbPassword) {
		this.driverName = driverName;
		this.dbURL = dbURL;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
	}

	public JdbcDAOBase() {
		// muss ausgetauscht werden
		this("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/DAS-SYSTEM", "root", "");
	}

	protected Connection getConnection() throws DataAccessException {
		try {
			Class.forName(driverName);
			return DriverManager.getConnection(dbURL, dbUser, dbPassword);
		} catch (SQLException sqlex) {
			throw new DataAccessException("DB-Connection nicht verfuegbar", sqlex);
		} catch (Exception ex) {
			throw new DataAccessException("Fehler bei DB-Zugriff", ex);
		}
	}

	protected List transformSet(ResultSet rs) throws SQLException {
		List voList = new LinkedList();
		Object vo;
		while (rs.next()) {
			vo = map2VO(rs);
			voList.add(vo);
		}
		return voList;
	}

	protected Object transformSetToVO(ResultSet rs) throws SQLException, DataAccessException {
		List voList;
		voList = transformSet(rs);
		if (voList.size() != 1)
			throw new DataAccessException("Falsche Anzahl an ValueObjects - Anzahl != 1");
		return voList.get(0);
	}

	protected abstract Object map2VO(ResultSet rs) throws SQLException;
}
