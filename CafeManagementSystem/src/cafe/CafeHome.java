package cafe;

import javax.swing.*;

import usermanager.CurrentUser;

public class CafeHome extends JFrame {
	WelcomePage wPage = new WelcomePage();
	HomePage hPage = new HomePage();
	public CafeHome() {
		if(CurrentUser.getInstance().getCurrentUser() != null) {
			hPage.setVisible(true);
			wPage.setVisible(false);
		} else {
			wPage.setVisible(true);
			hPage.setVisible(false);
		}
	}
}
