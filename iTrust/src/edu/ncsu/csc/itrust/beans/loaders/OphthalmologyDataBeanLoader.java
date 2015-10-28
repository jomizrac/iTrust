package edu.ncsu.csc.itrust.beans.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.beans.OphthalmologyDataBean;

/**
 * A loader for OphthalmologyData Beans.
 * 
 * Loads in information to/from beans using ResultSets and PreparedStatements. Use the superclass to enforce consistency.
 * For details on the paradigm for a loader (and what its methods do), see {@link BeanLoader} 
 */
public class OphthalmologyDataBeanLoader implements BeanLoader<OphthalmologyDataBean> {
	
	public OphthalmologyDataBeanLoader() { }

	/** 
	 * Loads result set list from data bean
	 * @param rs
	 * @return list - ArrayList of OphthalmologyDataBean
	 */
	public List<OphthalmologyDataBean> loadList(ResultSet rs) throws SQLException {
		ArrayList<OphthalmologyDataBean> list = new ArrayList<OphthalmologyDataBean>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	/** 
	 * Loads single opthalmology data bean
	 * @return bean
	 * @param rs - result set
	 */
	public OphthalmologyDataBean loadSingle(ResultSet rs) throws SQLException {
		OphthalmologyDataBean bean = new OphthalmologyDataBean();
		
		bean.setODAcuityNumerator(rs.getInt("ODAcuityNumerator"));
		bean.setODAcuityDenominator(rs.getInt("ODAcuityDenominator"));
		bean.setOSAcuityNumerator(rs.getInt("OSAcuityNumerator"));
		bean.setOSAcuityDenominator(rs.getInt("OSAcuityDenominator"));
		bean.setODSphere(rs.getDouble("ODSphere"));
		bean.setOSSphere(rs.getDouble("OSSphere"));
		bean.setODCylinder(rs.getDouble("ODCylinder"));
		bean.setOSCylinder(rs.getDouble("OSCylinder"));
		bean.setODAxis(rs.getInt("ODAxis"));
		bean.setOSAxis(rs.getInt("OSAxis"));
		bean.setODAdd(rs.getDouble("ODAdd"));
		bean.setOSAdd(rs.getDouble("OSAdd"));
		bean.setId(rs.getLong("id"));
		bean.setVisitID(rs.getLong("visitID"));
		
		return bean;
	}

	/**
	 * Makes the prepared statement
	 * @return ps prepared statement
	 */
	public PreparedStatement loadParameters(PreparedStatement ps, OphthalmologyDataBean bean)
			throws SQLException {
		
		ps.setLong(1, bean.getVisitID());
		ps.setInt(2, bean.getODAcuityNumerator());
		ps.setInt(3, bean.getODAcuityDenominator());
		ps.setInt(4, bean.getOSAcuityNumerator());
		ps.setInt(5, bean.getOSAcuityDenominator());
		ps.setDouble(6, bean.getODSphere());
		ps.setDouble(7, bean.getOSSphere());
		ps.setDouble(8, bean.getODCylinder());
		ps.setDouble(9, bean.getOSCylinder());
		ps.setInt(10, bean.getODAxis());
		ps.setInt(11, bean.getOSAxis());
		ps.setDouble(12, bean.getODAdd());
		ps.setDouble(13, bean.getOSAdd());
		
		return ps;
	}

}
