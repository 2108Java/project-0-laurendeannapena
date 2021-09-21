package com.revature;

import org.apache.log4j.Logger;

import com.revature.presentation.BankMenus;
import com.revature.presentation.BankMenusImpl;
import com.revature.repo.BankDAO;
import com.revature.repo.BankDAOImpl;
import com.revature.service.BankServices;
import com.revature.service.BankServicesImpl;
import com.revature.util.DBConnection;

public class MainDriver {

	final static Logger loggy = Logger.getLogger(MainDriver.class);
	
	public static void main(String[] args) {
		
		DBConnection connection = new DBConnection();
		BankDAO database = new BankDAOImpl(connection);
		BankServices service = new BankServicesImpl(database);
		BankMenus menu = new BankMenusImpl(service);
		
		loggy.info("Bank application started.");
		menu.display();
		
	}
}