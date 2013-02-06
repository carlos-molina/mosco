package ncl.b1037041.manager;

import java.util.List;

import ncl.b1037041.LTL.entites.BPMNChoreography;
import ncl.b1037041.LTL.entites.ChoreographyMessageInfo;
import ncl.b1037041.LTL.entites.LTLDefinition;
import ncl.b1037041.LTL.entites.LTLInstance;
import ncl.b1037041.LTL.entites.StatisticsLTLUsage;
import ncl.b1037041.access.entites.User;
import ncl.b1037041.exception.AlertException;

public interface InterfaceLTLManager {

	public void addLTLForlumaPrototype(String description, String formula, String nickname) throws AlertException;
	
	public List<LTLDefinition> getAllLTLDefinition();
	
	public void removeLTLDefinition(int id);
	
	public List<BPMNChoreography> getAllBPMN();
	
	public BPMNChoreography getBPMN(int id);
	
	public void addDefinition2BPMN(int chorId, int ltlId);
	
	public void addBPMNMessageInfo(int chorId, String message, String ltlSymbol, String boolMessage);
	
	public void deleteAllInfoOfBPMN(int chorId);
	
	public List<ChoreographyMessageInfo> getMessageInfo(int chorId);
	
	public List<LTLInstance> getLTLInstances(int chorId);
	
	public List<LTLInstance> getConfigedInstances(int chorId);
	
	public List<LTLInstance> getNonConfigedInstances(int chorId);
	
	public void updateLTLInstance(int instanceId, String s_formula, String s_description);
	
	public void deleteLTLInstance(int instanceId);

	public void addBPMN(BPMNChoreography chor) throws AlertException;
	
	public void updateBPMNName(int chorId, String newName) throws AlertException;
	
	public void deleteBPMN(int chorId);
	
	public List<StatisticsLTLUsage> getLTLUsageStatistics();
	
	public ChoreographyMessageInfo getInfoByMessage(int chorId, String message);
	
	
	public String getTranslatedLTLFormula(String formula) throws AlertException;
	
	public User getLoginUser(String username, String password) throws AlertException;
}
