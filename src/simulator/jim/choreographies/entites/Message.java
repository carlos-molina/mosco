package simulator.jim.choreographies.entites;

public class Message {

	private String id;
	private String itemRefId;
	private ItemDefinition itemRef;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemRefId() {
		return itemRefId;
	}
	public void setItemRefId(String itemRefId) {
		this.itemRefId = itemRefId;
	}
	public ItemDefinition getItemRef() {
		return itemRef;
	}
	public void setItemRef(ItemDefinition itemRef) {
		this.itemRef = itemRef;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
