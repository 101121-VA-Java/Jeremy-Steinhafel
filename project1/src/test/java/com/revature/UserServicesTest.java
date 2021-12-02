package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.repositories.UserPostgres;
import com.revature.services.UserServices;
import com.revature.models.User;

@ExtendWith(MockitoExtension.class)

public class UserServicesTest {
	
	@Mock
	private UserPostgres up;
	
	@InjectMocks
	private UserServices us;
	
	@Test
	public void getEmployeeByIDTest() {
		Mockito.when(up.getByID(1)).thenReturn(new User());
//		Mockito.doReturn(User.class).when(2).getParameterType(int);
		
		User expected = new User();
		User actual = us.getEmployeeByID(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void updateUserByIDTest() {
		Mockito.when(up.update(new User())).thenReturn(true);
		
		boolean expected = true;
		boolean actual = us.updateUserByID(new User());
		assertEquals(expected, actual);
	}
	
	@Test
	public void getAllUsersTest() {
		Mockito.when(up.getAll()).thenReturn(new ArrayList<>());
				
		List<User> expected = new ArrayList<>();
		List<User> actual = us.getAllUsers();
		assertEquals(expected, actual);
	}

}
