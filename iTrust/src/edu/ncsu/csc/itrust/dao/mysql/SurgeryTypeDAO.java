package edu.ncsu.csc.itrust.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ncsu.csc.itrust.DBUtil;
import edu.ncsu.csc.itrust.beans.SurgeryTypeBean;
import edu.ncsu.csc.itrust.beans.loaders.SurgeryTypeBeanLoader;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.exception.DBException;

public class SurgeryTypeDAO {
	private DAOFactory factory;
	private SurgeryTypeBeanLoader loader = new SurgeryTypeBeanLoader();
	
	public SurgeryTypeDAO(DAOFactory factory) {
		this.factory = factory;
	}
	
	public List<SurgeryTypeBean> getSurgeryTypes() throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement(
					"SELECT * FROM surgerytypes");
			ResultSet rs = ps.executeQuery();
			List<SurgeryTypeBean> loadlist = loader.loadList(rs);
			rs.close();
			return loadlist;
		} catch (SQLException e) { 
			throw new DBException(e); 
		} finally { 
			DBUtil.closeConnection(conn, ps); 
		}
	}
}
