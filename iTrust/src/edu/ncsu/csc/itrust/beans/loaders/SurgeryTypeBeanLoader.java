package edu.ncsu.csc.itrust.beans.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.beans.SurgeryTypeBean;

public class SurgeryTypeBeanLoader implements BeanLoader<SurgeryTypeBean> {

	public List<SurgeryTypeBean> loadList(ResultSet rs) throws SQLException {
		List<SurgeryTypeBean> list = new ArrayList<SurgeryTypeBean>();
		while (rs.next())
			list.add(loadSingle(rs));
		return list;
	}

	public PreparedStatement loadParameters(PreparedStatement ps, SurgeryTypeBean surgeryType) throws SQLException {
		ps.setString(1, surgeryType.getSurgeryName());
		ps.setLong(2, surgeryType.getSurgeryID());
		return ps;
	}

	public SurgeryTypeBean loadSingle(ResultSet rs) throws SQLException {
		SurgeryTypeBean surgeryType = new SurgeryTypeBean();
		surgeryType.setSurgeryName(rs.getString("SurgeryName"));
		surgeryType.setSurgeryID(rs.getLong("SurgeryId"));
		return surgeryType;
	}

}