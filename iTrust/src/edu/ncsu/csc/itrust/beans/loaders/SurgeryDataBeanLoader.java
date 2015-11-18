package edu.ncsu.csc.itrust.beans.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.beans.SurgeryDataBean;

/**
 * A loader for SurgeryData Beans.
 * 
 * Loads in information to/from beans using ResultSets and PreparedStatements. Use the superclass to enforce consistency.
 * For details on the paradigm for a loader (and what its methods do), see {@link BeanLoader} 
 */
public class SurgeryDataBeanLoader implements BeanLoader<SurgeryDataBean> {
	
	public SurgeryDataBeanLoader() { }
	
	public List<SurgeryDataBean> loadList(ResultSet rs) throws SQLException {
		ArrayList<SurgeryDataBean> list = new ArrayList<SurgeryDataBean>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	@Override
	public SurgeryDataBean loadSingle(ResultSet rs) throws SQLException {
		SurgeryDataBean bean = new SurgeryDataBean();
		
		bean.setId(rs.getLong("id"));
		bean.setVisitID(rs.getLong("visitID"));
		bean.setSurgeryID(rs.getLong("surgeryID"));
		bean.setSurgeryNotes(rs.getString("surgeryNotes"));
		
		return bean;
	}

	@Override
	public PreparedStatement loadParameters(PreparedStatement ps, SurgeryDataBean bean) throws SQLException {

		ps.setLong(1, bean.getVisitID());
		ps.setLong(2, bean.getSurgeryID());
		ps.setString(3, bean.getSurgeryNotes());
		
		return ps;
	}
	
	

}
