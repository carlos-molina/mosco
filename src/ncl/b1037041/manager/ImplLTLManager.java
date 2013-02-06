package ncl.b1037041.manager;

import generator.jim.ltl.creator.InterfaceLTLCreator;
import generator.jim.ltl.creator.ImplLTLCreator;
import generator.jim.tools.LTLSymbolGenerator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ncl.b1037041.LTL.entites.BPMNChoreography;
import ncl.b1037041.LTL.entites.ChoreographyMessageInfo;
import ncl.b1037041.LTL.entites.LTLDefinition;
import ncl.b1037041.LTL.entites.LTLInstance;
import ncl.b1037041.LTL.entites.StatisticsLTLUsage;
import ncl.b1037041.access.entites.User;
import ncl.b1037041.dao.*;
import ncl.b1037041.exception.AlertException;

public class ImplLTLManager implements InterfaceLTLManager {
	
	private Pattern variablePattern = Pattern.compile("@V\\d+@");
	private Pattern p_file = Pattern.compile("^.+\\.bpmn$", Pattern.CASE_INSENSITIVE);
	private Pattern p_image = Pattern.compile("^.+\\.(jepg|jpg|bpm|gif)$", Pattern.CASE_INSENSITIVE);
	
	private InterfaceLTLDao ltlDao = new ImplLTLDao();

	@Override
	public void addLTLForlumaPrototype(String description, String formula, String nickname) throws AlertException {
		if(!this.checkLTLInput(description, formula)) {
			throw new AlertException("Variables (@V\\d+@) have to be contained.");
		}
		if(!this.checkNickname(nickname)) {
			throw new AlertException("Invalid nickname.");
		}
		try {
			LTLSymbolGenerator g = new LTLSymbolGenerator();
			String s = new String(formula);
			Matcher matcher = variablePattern.matcher(s);
			while(matcher.find()) {
				String group = matcher.group();
				s = s.replaceAll(group, g.getNextSymbol());
				
			}
			System.out.println(this.getTranslatedLTLFormula(s));
		} catch(AlertException e) {
			throw e;
		}
		this.ltlDao.addLTLForlumaPrototype(description.trim(), formula.trim(), nickname.trim());
	}
	
	private boolean checkLTLInput(String description, String formula) {
		boolean result = false;
		if(description == null || formula == null) {
			return result;
		} 
		else {
			Matcher matcher = variablePattern.matcher(description);
			while(matcher.find()) {
				result = !result;
				break;
			}
			if(!result) {
				return result;
			}
			else {
				matcher = variablePattern.matcher(formula);
				while(matcher.find()) {
					result = !result;
					break;
				}
				return !result;
			}
		}
	}
	
	private boolean checkNickname(String nickname) {
		if(nickname == null || nickname.trim().equals("") || nickname.trim().length() > 32) {
			return false;
		}
		return true;
	}

	@Override
	public List<LTLDefinition> getAllLTLDefinition() {
		return this.ltlDao.getAllLTLDefinition();
	}

	@Override
	public void removeLTLDefinition(int id) {
		this.ltlDao.removeLTLDefinition(id);
	}

	@Override
	public List<BPMNChoreography> getAllBPMN() {
		return this.ltlDao.getAllBPMN();
	}

	@Override
	public BPMNChoreography getBPMN(int id) {
		return this.ltlDao.getBPMN(id);
	}

	@Override
	public void addDefinition2BPMN(int chorId, int ltlId) {
		this.ltlDao.addDefinition2BPMN(chorId, ltlId);
	}

	@Override
	public void addBPMNMessageInfo(int chorId, String message,
			String ltlSymbol, String boolMessage) {
		this.ltlDao.addBPMNMessageInfo(chorId, message, ltlSymbol, boolMessage);
	}

	@Override
	public void deleteAllInfoOfBPMN(int chorId) {
		this.ltlDao.deleteAllInfoOfBPMN(chorId);
	}

	@Override
	public List<ChoreographyMessageInfo> getMessageInfo(int chorId) {
		return this.ltlDao.getMessageInfo(chorId);
	}

	@Override
	public List<LTLInstance> getLTLInstances(int chorId) {
		return this.ltlDao.getLTLInstances(chorId);
	}

	@Override
	public void updateLTLInstance(int instanceId, String s_formula,
			String s_description) {
		this.ltlDao.updateLTLInstance(instanceId, s_formula, s_description);
	}

	@Override
	public List<LTLInstance> getConfigedInstances(int chorId) {
		return this.ltlDao.getConfigedInstances(chorId);
	}

	@Override
	public void addBPMN(BPMNChoreography chor) throws AlertException {
		if(!this.checkBPMNFiles(chor)) {
			throw new AlertException("file:(bpmn); image:(jpg|jepg|bpm|gif).");
		}
		this.ltlDao.addBPMN(chor);
	}

	private boolean checkBPMNFiles(BPMNChoreography chor) {
		Matcher m_file = p_file.matcher(chor.getFilePath());
		if(m_file.matches()) {
			Matcher m_image = p_image.matcher(chor.getImagePath());
			if(m_image.matches()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public void updateBPMNName(int chorId, String newName) throws AlertException {
		if(!this.checkBPMNNewName(newName)) {
			throw new AlertException("Invalid new name.");
		}
		this.ltlDao.updateBPMNName(chorId, newName.trim());
	}
	
	private boolean checkBPMNNewName(String name) {
		if(name == null || name.trim().equals("")) {
			return false;
		}
		return true;
	}

	@Override
	public void deleteBPMN(int chorId) {
		this.ltlDao.deleteAllInfoOfBPMN(chorId);
		this.ltlDao.deleteAllLTLInstance(chorId);
		this.ltlDao.deleteBPMN(chorId);
	}

	@Override
	public List<StatisticsLTLUsage> getLTLUsageStatistics() {
		return this.ltlDao.getLTLUsageStatistics();
	}

	@Override
	public List<LTLInstance> getNonConfigedInstances(int chorId) {
		return this.ltlDao.getNonConfigedInstances(chorId);
	}

	@Override
	public void deleteLTLInstance(int instanceId) {
		this.ltlDao.deleteLTLInstance(instanceId);
	}
	

	@Override
	public String getTranslatedLTLFormula(String formula) throws AlertException {
		String never = null;
		InterfaceLTLCreator ltlCreator = new ImplLTLCreator(formula);
		try {
			never = ltlCreator.create();
		} catch(AlertException e) {
			throw e;
		}
		return never;
	}

	@Override
	public ChoreographyMessageInfo getInfoByMessage(int chorId, String message) {
		return this.ltlDao.getInfoByMessage(chorId, message);
	}

	@Override
	public User getLoginUser(String username, String password)
			throws AlertException {
		return this.ltlDao.getLoginUser(username, password);
	}
}
