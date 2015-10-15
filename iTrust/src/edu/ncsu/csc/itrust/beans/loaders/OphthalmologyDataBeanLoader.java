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
		
		bean.setAcuityNumerator(rs.getInt("acuityNumerator"));
		bean.setAcuityDenominator(rs.getInt("acuityDenominator"));
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
		ps.setInt(2, bean.getAcuityNumerator());
		ps.setInt(3, bean.getAcuityDenominator());
		ps.setDouble(4, bean.getODSphere());
		ps.setDouble(5, bean.getOSSphere());
		ps.setDouble(6, bean.getODCylinder());
		ps.setDouble(7, bean.getOSCylinder());
		ps.setInt(8, bean.getODAxis());
		ps.setInt(9, bean.getOSAxis());
		ps.setDouble(10, bean.getODAdd());
		ps.setDouble(11, bean.getOSAdd());
		
		return ps;
	}

}
