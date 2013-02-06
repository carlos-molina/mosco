package ncl.b1037041.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ncl.b1037041.LTL.entites.BPMNChoreography;
import ncl.b1037041.LTL.entites.ChoreographyMessageInfo;
import ncl.b1037041.LTL.entites.LTLDefinition;
import ncl.b1037041.LTL.entites.LTLInstance;
import ncl.b1037041.LTL.entites.StatisticsLTLUsage;
import ncl.b1037041.access.entites.User;
import ncl.b1037041.db.tool.DataBaseUtil;
import ncl.b1037041.exception.AlertException;

public class ImplLTLDao implements InterfaceLTLDao {

	@Override
	public void addLTLForlumaPrototype(String description, String formula, String nickname) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "insert into ltl_formula_definition (description, formula, nickname) "
				+ "values ('"+description+"', '"+formula+"', '"+nickname+"')";
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
	}

	@Override
	public List<LTLDefinition> getAllLTLDefinition() {
		List<LTLDefinition> results = new ArrayList<LTLDefinition>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from ltl_formula_definition";
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			LTLDefinition definition = null;
			while(rs.next()) {
				definition = new LTLDefinition();
				definition.setId(rs.getInt("id"));
				definition.setFormula(rs.getString("formula"));
				definition.setDescription(rs.getString("description"));
				definition.setNickname(rs.getString("nickname"));
				results.add(definition);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				DataBaseUtil.closeResultSet(rs);
			}
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
		return results;
	}

	@Override
	public void removeLTLDefinition(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "delete from ltl_formula_definition where id = " + id;
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}
	}

	@Override
	public List<BPMNChoreography> getAllBPMN() {
		List<BPMNChoreography> results = new ArrayList<BPMNChoreography>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from bpmn_choreography";
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			BPMNChoreography bpmn = null;
			while(rs.next()) {
				bpmn = new BPMNChoreography();
				bpmn.setId(rs.getInt("id"));
				bpmn.setName(rs.getString("name"));
				bpmn.setFilePath(rs.getString("file_path"));
				bpmn.setImagePath(rs.getString("image_path"));
				results.add(bpmn);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				DataBaseUtil.closeResultSet(rs);
			}
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
		return results;
	}

	@Override
	public BPMNChoreography getBPMN(int id) {
		BPMNChoreography bpmn = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from bpmn_choreography where id = " + id;
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				bpmn = new BPMNChoreography();
				bpmn.setId(rs.getInt("id"));
				bpmn.setName(rs.getString("name"));
				bpmn.setFilePath(rs.getString("file_path"));
				bpmn.setImagePath(rs.getString("image_path"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				DataBaseUtil.closeResultSet(rs);
			}
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
		return bpmn;
	}

	@Override
	public void addDefinition2BPMN(int chorId, int ltlId) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "insert into ltl_formula_instance (choreography_id, definition_id) "
				+ "values ("+chorId+", "+ltlId+")";
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
	}

	@Override
	public void addBPMNMessageInfo(int chorId, String message,
			String ltlSymbol, String boolMessage) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "insert into bpmn_choreography_message_info "
				+ "(choreography_id, message, ltl_symbol, bool_message) "
				+ "values ("+chorId+", '"+message+"', '"+ltlSymbol+"', '"+boolMessage+"')";
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
	}

	@Override
	public void deleteAllInfoOfBPMN(int chorId) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "delete from bpmn_choreography_message_info where choreography_id = " + chorId;
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}
	}

	@Override
	public List<ChoreographyMessageInfo> getMessageInfo(int chorId) {
		List<ChoreographyMessageInfo> results = new ArrayList<ChoreographyMessageInfo>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select i.id id, i.message message, i.ltl_symbol symbol, i.bool_message bool, "
				+ "b.id chor_id, b.name chor_name, b.file_path file, b.image_path image "
				+ "from bpmn_choreography_message_info i left outer join bpmn_choreography b "
				+ "on i.choreography_id = b.id where b.id = " + chorId;
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			ChoreographyMessageInfo info = null;
			while(rs.next()) {
				info = new ChoreographyMessageInfo();
				info.setId(rs.getInt("id"));
				info.setMessage(rs.getString("message"));
				info.setLtlSymbol(rs.getString("symbol"));
				info.setBoolMessage(rs.getString("bool"));
				
				BPMNChoreography bpmn = new BPMNChoreography();
				bpmn.setId(rs.getInt("chor_id"));
				bpmn.setName(rs.getString("chor_name"));
				bpmn.setFilePath(rs.getString("file"));
				bpmn.setImagePath(rs.getString("image"));
				
				info.setBpmn(bpmn);
				
				results.add(info);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				DataBaseUtil.closeResultSet(rs);
			}
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
		return results;
	}

	@Override
	public List<LTLInstance> getLTLInstances(int chorId) {
		List<LTLInstance> results = new ArrayList<LTLInstance>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select i.id id, i.specific_description s_d, i.specific_formula s_f, i.is_setup setup, "
				+ "c.id chor_id, c.name chor_name, c.file_path file, c.image_path image, "
				+ "d.id desc_id, d.formula formula, d.description description, d.nickname nickname "
				+ "from ltl_formula_instance i "
				+ "inner join bpmn_choreography c on i.choreography_id = c.id "
				+ "inner join ltl_formula_definition d on i.definition_id = d.id "
				+ "where i.choreography_id = " + chorId;
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			LTLInstance instance = null;
			while(rs.next()) {
				instance = new LTLInstance();
				instance.setId(rs.getInt("id"));
				instance.setSpecificFormula(rs.getString("s_f"));
				instance.setSpecificDescription(rs.getString("s_d"));
				instance.setIsSetup(rs.getInt("setup"));
				
				BPMNChoreography bpmn = new BPMNChoreography();
				bpmn.setId(rs.getInt("chor_id"));
				bpmn.setName(rs.getString("chor_name"));
				bpmn.setFilePath(rs.getString("file"));
				bpmn.setImagePath(rs.getString("image"));
				
				instance.setBpmn(bpmn);
				
				LTLDefinition definition = new LTLDefinition();
				definition.setId(rs.getInt("desc_id"));
				definition.setFormula(rs.getString("formula"));
				definition.setDescription(rs.getString("description"));
				definition.setNickname(rs.getString("nickname"));
				
				instance.setDefinition(definition);
				
				results.add(instance);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				DataBaseUtil.closeResultSet(rs);
			}
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
		return results;
	}

	@Override
	public void updateLTLInstance(int instanceId, String s_formula,
			String s_description) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "update ltl_formula_instance set "
				 + "specific_formula = '"+s_formula+"', "
				 + "specific_description = '"+s_description+"', "
				 + "is_setup = 1 "
				 + "where id = " + instanceId;
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}
	}

	@Override
	public List<LTLInstance> getConfigedInstances(int chorId) {
		List<LTLInstance> results = new ArrayList<LTLInstance>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select i.id id, i.specific_description s_d, i.specific_formula s_f, i.is_setup setup, "
				+ "c.id chor_id, c.name chor_name, c.file_path file, c.image_path image, "
				+ "d.id desc_id, d.formula formula, d.description description, d.nickname nickname "
				+ "from ltl_formula_instance i "
				+ "inner join bpmn_choreography c on i.choreography_id = c.id "
				+ "inner join ltl_formula_definition d on i.definition_id = d.id "
				+ "where i.choreography_id = " + chorId + " "
				+ "and i.is_setup = 1 order by id";
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			LTLInstance instance = null;
			while(rs.next()) {
				instance = new LTLInstance();
				instance.setId(rs.getInt("id"));
				instance.setSpecificFormula(rs.getString("s_f"));
				instance.setSpecificDescription(rs.getString("s_d"));
				instance.setIsSetup(rs.getInt("setup"));
				
				BPMNChoreography bpmn = new BPMNChoreography();
				bpmn.setId(rs.getInt("chor_id"));
				bpmn.setName(rs.getString("chor_name"));
				bpmn.setFilePath(rs.getString("file"));
				bpmn.setImagePath(rs.getString("image"));
				
				instance.setBpmn(bpmn);
				
				LTLDefinition definition = new LTLDefinition();
				definition.setId(rs.getInt("desc_id"));
				definition.setFormula(rs.getString("formula"));
				definition.setDescription(rs.getString("description"));
				definition.setNickname(rs.getString("nickname"));
				
				instance.setDefinition(definition);
				
				results.add(instance);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				DataBaseUtil.closeResultSet(rs);
			}
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
		return results;
	}

	@Override
	public void addBPMN(BPMNChoreography chor) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "insert into bpmn_choreography (name, file_path, image_path) "
				+ "values ('"+chor.getName()+"', '"+chor.getFilePath()+ "', '"+chor.getImagePath()+"')";
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
	}

	@Override
	public void updateBPMNName(int chorId, String newName) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "update bpmn_choreography set "
				 + "name = '"+newName+"' "
				 + "where id = " + chorId;
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}
	}

	@Override
	public void deleteBPMN(int chorId) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "delete from bpmn_choreography where id = " + chorId;
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}
	}

	@Override
	public List<StatisticsLTLUsage> getLTLUsageStatistics() {
		List<StatisticsLTLUsage> results = new ArrayList<StatisticsLTLUsage>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select d.id, d.nickname, a.num from "
				+ "(select i.definition_id did, count(i.definition_id) num from "
				+ " ltl_formula_instance i group by i.definition_id order by num desc limit 5) a "
				+ " inner join ltl_formula_definition d on a.did = d.id ";
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			StatisticsLTLUsage usage = null;
			while(rs.next()) {
				usage = new StatisticsLTLUsage();
				usage.setId(rs.getInt("id"));
				usage.setNickname(rs.getString("nickname"));
				usage.setNum(rs.getInt("num"));
				results.add(usage);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				DataBaseUtil.closeResultSet(rs);
			}
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
		return results;
	}

	@Override
	public List<LTLInstance> getNonConfigedInstances(int chorId) {
		List<LTLInstance> results = new ArrayList<LTLInstance>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select i.id id, i.specific_description s_d, i.specific_formula s_f, i.is_setup setup, "
				+ "c.id chor_id, c.name chor_name, c.file_path file, c.image_path image, "
				+ "d.id desc_id, d.formula formula, d.description description, d.nickname nickname "
				+ "from ltl_formula_instance i "
				+ "inner join bpmn_choreography c on i.choreography_id = c.id "
				+ "inner join ltl_formula_definition d on i.definition_id = d.id "
				+ "where i.choreography_id = " + chorId + " "
				+ "and i.is_setup = 0";
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			LTLInstance instance = null;
			while(rs.next()) {
				instance = new LTLInstance();
				instance.setId(rs.getInt("id"));
				instance.setSpecificFormula(rs.getString("s_f"));
				instance.setSpecificDescription(rs.getString("s_d"));
				instance.setIsSetup(rs.getInt("setup"));
				
				BPMNChoreography bpmn = new BPMNChoreography();
				bpmn.setId(rs.getInt("chor_id"));
				bpmn.setName(rs.getString("chor_name"));
				bpmn.setFilePath(rs.getString("file"));
				bpmn.setImagePath(rs.getString("image"));
				
				instance.setBpmn(bpmn);
				
				LTLDefinition definition = new LTLDefinition();
				definition.setId(rs.getInt("desc_id"));
				definition.setFormula(rs.getString("formula"));
				definition.setDescription(rs.getString("description"));
				definition.setNickname(rs.getString("nickname"));
				
				instance.setDefinition(definition);
				
				results.add(instance);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				DataBaseUtil.closeResultSet(rs);
			}
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
		return results;
	}

	@Override
	public void deleteLTLInstance(int instanceId) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "delete from ltl_formula_instance where id = " + instanceId;
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}
	}

	@Override
	public void deleteAllLTLInstance(int chorId) {
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "delete from ltl_formula_instance where choreography_id = " + chorId;
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}
	}

	@Override
	public ChoreographyMessageInfo getInfoByMessage(int chorId, String message) {
		ChoreographyMessageInfo info = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from bpmn_choreography_message_info " +
				"where choreography_id = " + chorId + " and message = '" + message + "'";
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				info = new ChoreographyMessageInfo();
				info.setId(rs.getInt("id"));
				info.setMessage(rs.getString("message"));
				info.setLtlSymbol(rs.getString("ltl_symbol"));
				info.setBoolMessage(rs.getString("bool_message"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				DataBaseUtil.closeResultSet(rs);
			}
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
		return info;
	}

	@Override
	public User getLoginUser(String username, String password)
			throws AlertException {
		User user = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from b2p_users where username = '" + username + "' and password = '" + password + "'";
		try {
			connection = DataBaseUtil.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();		
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getInt("user_type"));
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				DataBaseUtil.closeResultSet(rs);
			}
			if(ps != null) {
				DataBaseUtil.closeStatement(ps);				
			}
			if(connection != null) {
				DataBaseUtil.closeConnection(connection);
			}
		}	
		if(user == null) {
			throw new AlertException("Wrong username or password.");
		}
		return user;
	}
}
