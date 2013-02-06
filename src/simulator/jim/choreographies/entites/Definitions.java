package simulator.jim.choreographies.entites;

import java.util.*;

public class Definitions {

	private Set<ImportItem> importItems = new HashSet<ImportItem>();
	private Choreography choreography = new Choreography();
	private Set<ItemDefinition> itemDefinitions = new HashSet<ItemDefinition>();
	private Set<Message> messages = new HashSet<Message>();
	
	public Set<ImportItem> getImportItems() {
		return importItems;
	}
	public void setImportItems(Set<ImportItem> importItems) {
		this.importItems = importItems;
	}
	public Choreography getChoreography() {
		return choreography;
	}
	public void setChoreography(Choreography choreography) {
		this.choreography = choreography;
	}
	public Set<ItemDefinition> getItemDefinitions() {
		return itemDefinitions;
	}
	public void addItemDefinition(ItemDefinition itemDefinition) {
		this.itemDefinitions.add(itemDefinition);
	}
	public Set<Message> getMessages() {
		return messages;
	}
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	public ItemDefinition searchSingleItemDefinition(String id) {
		if(itemDefinitions.isEmpty() || id == null) {
			return null;
		}
		Iterator<ItemDefinition> it = itemDefinitions.iterator();
		ItemDefinition def;
		while(it.hasNext()) {
			def = it.next();
			if(id.equals(def.getId())) {
				return def;
			}
		}
		return null;
	}
	
	public void searchAndAddToSingleMessage(ItemDefinition itemDefinition) {
		if(messages.isEmpty() || itemDefinition == null || itemDefinition.getId() == null) {
			return;
		}
		Iterator<Message> it = messages.iterator();
		Message m;
		while(it.hasNext()) {
			m = it.next();
			if(itemDefinition.getId().equals(m.getItemRefId())) {
				m.setItemRef(itemDefinition);
			}
		}
		return;
	}
}
