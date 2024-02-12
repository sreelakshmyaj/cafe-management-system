package usermanager;

public class CurrentUser {
	private static CurrentUser instance;
	private User user;
	
	private CurrentUser() {}
	
	public static CurrentUser getInstance() {
		if (instance == null) {
			instance = new CurrentUser();
		}
		return instance;
	}
	
	public void setCurrentUser(User user) {
		this.user = user;
	}
	
	public User getCurrentUser() {
		return user;
	}
}
