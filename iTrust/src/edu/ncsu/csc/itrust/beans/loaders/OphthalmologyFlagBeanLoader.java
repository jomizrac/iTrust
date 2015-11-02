package edu.ncsu.csc.itrust.beans.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.beans.OphthalmologyFlagBean;
import edu.ncsu.csc.itrust.enums.OphthalmologyFlag;

public class OphthalmologyFlagBeanLoader implements BeanLoader<OphthalmologyFlagBean> {

	/** 
	 * Loads result set list from data bean
	 * @param rs
	 * @return list - ArrayList of OphthalmologyFlagBean
	 */
	public List<OphthalmologyFlagBean> loadList(ResultSet rs)
			throws SQLException {
		ArrayList<OphthalmologyFlagBean> list = new ArrayList<OphthalmologyFlagBean>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}
	
	/**
	 * Creates a bean from the result set
	 */
	public OphthalmologyFlagBean loadSingle(ResultSet rs) throws SQLException {
		OphthalmologyFlagBean bean = new OphthalmologyFlagBean();
		
		bean.setFid(rs.getLong("FID"));
		bean.setMid(rs.getLong("MID"));
		bean.setValue(OphthalmologyFlag.valueOf(rs.getString("flagType")));
		bean.setFlagged(true);
		
		return bean;
	}

	/**
	 * loadParameters
	 * Sets MID, FID (which is auto-incremented later by the table), and Value.
	 * Only true flags are inserted into the table
	 */
	public PreparedStatement loadParameters(PreparedStatement ps,
			OphthalmologyFlagBean bean) throws SQLException {
		int i = 1;
	    ps.setLong(i++, bean.getFid());
	    ps.setLong(i++, bean.getMid());
	    ps.setString(i++, bean.getValue().toString());
		return ps;
	}
}
