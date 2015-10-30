package edu.ncsu.csc.itrust.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ncsu.csc.itrust.DBUtil;
import edu.ncsu.csc.itrust.beans.OfficeVisitBean;
import edu.ncsu.csc.itrust.beans.SurgeryDataBean;
import edu.ncsu.csc.itrust.beans.loaders.OfficeVisitLoader;
import edu.ncsu.csc.itrust.beans.loaders.SurgeryDataBeanLoader;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.exception.DBException;

/**
 * Used for managing Surgery Data.
 * 
 * DAO stands for Database Access Object. All DAOs are intended to be reflections of the database, that is,
 * one DAO per table in the database (most of the time). For more complex sets of queries, extra DAOs are
 * added. DAOs can assume that all data has been validated and is correct.
 * 
 * DAOs should never have setters or any other parameter to the constructor than a factory. All DAOs should be
 * accessed by DAOFactory (@see {@link DAOFactory}) and every DAO should have a factory - for obtaining JDBC
 * connections and/or accessing other DAOs.
 */

public class SurgeryDataDAO {
	private DAOFactory factory;
	private SurgeryDataBeanLoader loader = new SurgeryDataBeanLoader();
	
	public SurgeryDataDAO(DAOFactory factory) {
		this.factory = factory;
	}
	
	public List<SurgeryDataBean> getList(long visitID) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement(
					"SELECT * FROM ovSurgeryData WHERE ovSurgeryData.visitID = ? ");
			ps.setLong(1, visitID);
			ResultSet rs = ps.executeQuery();
			List<SurgeryDataBean> loadlist = loader.loadList(rs);
			rs.close();
			return loadlist;
		} catch (SQLException e) {
			
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}
	
	public long add(SurgeryDataBean bean) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			String statement = "INSERT INTO ovSurgeryData " +
				"(visitID,surgeryID,surgeryNotes) VALUES (?,?,?)";
			ps = conn.prepareStatement(statement);
			ps = loader.loadParameters(ps, bean);
			ps.executeUpdate();
			return DBUtil.getLastInsert(conn);
		} catch (SQLException e) {
			
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}
	
	public long edit(SurgeryDataBean bean) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			String statement = "UPDATE ovSurgeryData " +
				"SET visitID=?, surgeryID=?, surgeryNotes=? " +
				"WHERE id=?";
			ps = conn.prepareStatement(statement);
			loader.loadParameters(ps, bean);
			ps.setLong(4, bean.getId()); //unsure of this statement
			ps.executeUpdate();
			return DBUtil.getLastInsert(conn);
		} catch (SQLException e) {
			
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}
	
	public List<OfficeVisitBean> getOfficeVisitsWithSurgeryData(long pid) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			OfficeVisitLoader ovloader = new OfficeVisitLoader();
			conn = factory.getConnection();
			ps = conn.prepareStatement(
					"SELECT * FROM officevisits WHERE " +
					"officevisits.ID in (SELECT visitID FROM ovSurgeryData) " +
					"AND officevisits.PatientID = ?");
			ps.setLong(1, pid);
			ResultSet rs = ps.executeQuery();
			List<OfficeVisitBean> loadlist = ovloader.loadList(rs);
			rs.close();
			return loadlist;
		} catch (SQLException e) { 
			throw new DBException(e); 
		} finally { 
			DBUtil.closeConnection(conn, ps); 
		}
	}
}
