package com.stackroute.keepnote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.UserDAO;
import com.stackroute.keepnote.exception.UserAlreadyExistException;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;

/*
 * Service classes are used here to implement additional business logic/validation 
 * This class has to be annotated with @Service annotation.
 * @Service - It is a specialization of the component annotation. It doesn�t currently 
 * provide any additional behavior over the @Component annotation, but it�s a good idea 
 * to use @Service over @Component in service-layer classes because it specifies intent 
 * better. Additionally, tool support and additional behavior might rely on it in the 
 * future.
 * */
@Service
public class UserServiceImpl implements UserService {

	/*
	 * Autowiring should be implemented for the userDAO. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword.
	 */
	@Autowired
	UserDAO userDAOImpl;

	public UserServiceImpl(UserDAO userDAOImpl) {
		super();
		this.userDAOImpl = userDAOImpl;
	}
	/*
	 * This method should be used to save a new user.
	 */

	public boolean registerUser(User user) throws UserAlreadyExistException {

		try {
			return userDAOImpl.registerUser(user);
		} catch (Exception e) {
			throw new UserAlreadyExistException("UserAlreadyExistException");
		}
	}
	/*
	 * This method should be used to update a existing user.
	 */
	public User updateUser(User user, String userId) throws Exception {

		userDAOImpl.updateUser(user);
		User userVo = getUserById(userId);
		if(null == userVo) {
			throw new Exception();
		}
		return userVo;  
	}
	/*
	 * This method should be used to get a user by userId.
	 */
	public User getUserById(String UserId) throws UserNotFoundException {

		User user =  userDAOImpl.getUserById(UserId);
		if(null == user) {
			throw new UserNotFoundException("UserNotFoundException");
		}else {
			return user;
		}
	}
	/*
	 * This method should be used to validate a user using userId and password.
	 */
	public boolean validateUser(String userId, String password) throws UserNotFoundException {

		if(!userDAOImpl.validateUser(userId, password)) {
			throw new UserNotFoundException("UserNotFoundException");
		}
		return true;
	}
	/* This method should be used to delete an existing user. */
	public boolean deleteUser(String UserId) {

		return userDAOImpl.deleteUser(UserId);
	}
}
