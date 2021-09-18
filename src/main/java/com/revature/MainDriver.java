package com.revature;

import com.revature.presentation.BankMenus;
import com.revature.presentation.BankMenusImpl;
import com.revature.repo.BankDAO;
import com.revature.repo.BankDAOImpl;
import com.revature.service.BankServices;
import com.revature.service.BankServicesImpl;
import com.revature.util.DBConnection;

public class MainDriver {

	public static void main(String[] args) {
		
		DBConnection connection = new DBConnection();
		BankDAO database = new BankDAOImpl(connection);
		BankServices service = new BankServicesImpl(database);
		BankMenus menu = new BankMenusImpl(service);
		
		menu.display();
		
	}
}