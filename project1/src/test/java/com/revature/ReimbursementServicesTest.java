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

import com.revature.repositories.ReimbursementPostgres;
import com.revature.services.ReimbursementServices;
import com.revature.models.Reimbursement;

@ExtendWith(MockitoExtension.class)
public class ReimbursementServicesTest {

	@Mock
	private ReimbursementPostgres rp;
	
	@InjectMocks
	private ReimbursementServices rs;
	
	@Test
	public void getEmployeeByIDTest() {
//		Mockito.when(up.getByID(1)).thenReturn(new User());
////		Mockito.doReturn(User.class).when(2).getParameterType(int);
//		
//		User expected = new User();
//		User actual = us.getEmployeeByID(1);
//		assertEquals(expected, actual);
	}

}
