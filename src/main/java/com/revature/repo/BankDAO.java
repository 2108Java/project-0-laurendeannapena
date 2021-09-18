package com.revature.repo;

import com.revature.models.User;

public interface BankDAO {

	User selectUserByUsername(String username);

	boolean insertUser(User newUser);

}

