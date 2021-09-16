package com.revature;

import com.revature.presentation.Menu;
import com.revature.presentation.MenuImpl;
import com.revature.repo.BankDAO;
import com.revature.repo.BankDAOImpl;
import com.revature.service.BankServices;
import com.revature.service.BankServicesImpl;

public class MainDriver {

	public static void main(String[] args) {
	
		BankDAO database = new BankDAOImpl();
		BankServices service = new BankServicesImpl(database);
		Menu menu = new MenuImpl(service);
		
		menu.loginOrRegister();
		
	}

}
