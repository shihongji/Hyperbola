package hongji.bola.model;

public class Session {
	private String name;
	private String server;
	private ContactsGroup rootGroup;

	public String getName() {
		return name;
	}

	public String getServer() {
		return server;
	}

	public ContactsGroup getRoot() {
		return rootGroup;
	}
	
	public void setSessionDescription(String name, String server) {
		this.name = name;
		this.server = server;
	}
	
	public Session() {
		rootGroup = new ContactsGroup(null, "Root");
	}

}
