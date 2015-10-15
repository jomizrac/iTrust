package edu.ncsu.csc.itrust.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ncsu.csc.itrust.DBUtil;
import edu.ncsu.csc.itrust.beans.OfficeVisitBean;
import edu.ncsu.csc.itrust.beans.OphthalmologyDataBean;
import edu.ncsu.csc.itrust.beans.loaders.OfficeVisitLoader;
import edu.ncsu.csc.itrust.beans.loaders.OphthalmologyDataBeanLoader;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.exception.DBException;

/**
 * Used for managing Ophthalmology Data.
 * 
 * DAO stands for Database Access Object. All DAOs are intended to be reflections of the database, that is,
 * one DAO per table in the database (most of the time). For more complex sets of queries, extra DAOs are
 * added. DAOs can assume that all data has been validated and is correct.
 * 
 * DAOs should never have setters or any other parameter to the constructor than a factory. All DAOs should be
 * accessed by DAOFactory (@see {@link DAOFactory}) and every DAO should have a factory - for obtaining JDBC
 * connections and/or accessing other DAOs.
 */
public class OphthalmologyDataDAO {
	private DAOFactory factory;
	private OphthalmologyDataBeanLoader loader = new OphthalmologyDataBeanLoader();
	
	public OphthalmologyDataDAO(DAOFactory factory) {
		this.factory = factory;
	}
	
	/**
	 * Get a list of Ophthalmology Data for a given office visit.
	 * @param visitID The id of the office visit to lookup.
	 * @return The list of Ophthalmology Data.
	 * @throws DBException
	 */
	public List<OphthalmologyDataBean> getList(long visitID) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement(
					"SELECT * FROM ovOphthalmologyData WHERE ovOphthalmologyData.visitID = ? ");
			ps.setLong(1, visitID);
			ResultSet rs = ps.executeQuery();
			List<OphthalmologyDataBean> loadlist = loader.loadList(rs);
			rs.close();
			return loadlist;
		} catch (SQLException e) {
			
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}
	
	/**
	 * Add a new Ophthalmology Data record.
	 * @param bean The ophthalmology data to add.
	 * @return The id of the newly added ophthalmology data record.
	 * @throws DBException
	 */
	public long add(OphthalmologyDataBean bean) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			String statement = "INSERT INTO ovOphthalmologyData " +
				"(visitID,acuityNumerator,acuityDenominator,ODSphere,OSSphere,ODCylinder,OSCylinder,ODAxis,OSAxis,ODAdd,OSAdd) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
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
	
	/**
	 * Edit an existing ophthalmology data record in the database.
	 * @param bean The ophthalmology data to modify.
	 * @return The id of the modified ophthalmology data.  This will be the same as the id given in the bean itself.
	 * @throws DBException
	 */
	public long edit(OphthalmologyDataBean bean) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			String statement = "UPDATE ovOphthalmologyData " +
				"SET visitID=?, acuityNumerator=?, acuityDenominator=?, ODSphere=?, OSSphere=?, ODCylinder=?, OSCylinder=?, ODAxis=?, OSAxis=?, ODAdd=?, OSAdd=? " +
				"WHERE id=?";
			ps = conn.prepareStatement(statement);
			loader.loadParameters(ps, bean);
			ps.setLong(12, bean.getId());
			ps.executeUpdate();
			return DBUtil.getLastInsert(conn);
		} catch (SQLException e) {
			
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}
	
	/**
	 * ONLY USE THIS IF WE PLAN TO GIVE DELETE FUNCTIONALITY.
	 * DELETE FUNCTIONALITY WOULD ONLY BE NEEDED IF WE KEEP THE HISTORY OF THE
	 * OPHTHALMOLOGY OFFICE VISIT AS A LIST THAT IS SHOWN.
	 * 
	 * Remove a patient instructions record from the database.
	 * @param patientInstructionsID The id of the record to delete.
	 * @throws DBException
	 */
//	public void remove(long ophthalmologyDataId) throws DBException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		try {
//			conn = factory.getConnection();
//			ps = conn.prepareStatement("DELETE FROM ovOphthalmologyData WHERE ID=? ");
//			ps.setLong(1, ophthalmologyDataId);
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			
//			throw new DBException(e);
//		} finally {
//			DBUtil.closeConnection(conn, ps);
//		}
//	}
	
	/**
	 * Get a list of all office visits by a given patient which has Ophthalmology Data.
	 * 
	 * @param pid The patient id to look up.
	 * @return A list of office visits.
	 * @throws DBException
	 */
	public List<OfficeVisitBean> getOfficeVisitsWithOpthData(long pid) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			OfficeVisitLoader ovloader = new OfficeVisitLoader();
			conn = factory.getConnection();
			ps = conn.prepareStatement(
					"SELECT * FROM officevisits WHERE " +
					"officevisits.ID in (SELECT visitID FROM ovOphthalmologyData) " +
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
