package usermanager;

public class User {
	private int id;
	private String username;
	private String name;
	private int phone;
	private String role;
	
	public User(int id, String username, String name, int phone, String role) {
		this.id = id;
		this.role = role;
		this.username = username;
		this.name = name;
		this.phone = phone;
	}
	
	public int getId() {
		return this.id;
	}
}
