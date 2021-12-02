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
	public void creatNewReimbursementTest() {
		Mockito.when(rp.add(new Reimbursement())).thenReturn(new Reimbursement());

		Reimbursement expected = new Reimbursement();
		Reimbursement actual = rs.createNewReimbursement(0, 0, 0, null);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getPendingByAuthorIDTest() {
//		Mockito.when(rp.getByAuthorID(1).thenReturn(new Reimbursement());

		Reimbursement expected = new Reimbursement();
		Reimbursement actual = rs.createNewReimbursement(0, 0, 0, null);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getResolvedByAuthorIDTest() {
		Mockito.when(rp.add(new Reimbursement())).thenReturn(new Reimbursement());

		Reimbursement expected = new Reimbursement();
		Reimbursement actual = rs.createNewReimbursement(0, 0, 0, null);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getAllPendingRequestsTest() {
		Mockito.when(rp.add(new Reimbursement())).thenReturn(new Reimbursement());

		Reimbursement expected = new Reimbursement();
		Reimbursement actual = rs.createNewReimbursement(0, 0, 0, null);
		assertEquals(expected, actual);
	}
	
	@Test
	public void approveByReimbIDTest() {
		Mockito.when(rp.add(new Reimbursement())).thenReturn(new Reimbursement());

		Reimbursement expected = new Reimbursement();
		Reimbursement actual = rs.createNewReimbursement(0, 0, 0, null);
		assertEquals(expected, actual);
	}
	
	@Test
	public void denyByReimbIDTest() {
		Mockito.when(rp.add(new Reimbursement())).thenReturn(new Reimbursement());

		Reimbursement expected = new Reimbursement();
		Reimbursement actual = rs.createNewReimbursement(0, 0, 0, null);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getAllByAuthorIDTest() {
		Mockito.when(rp.add(new Reimbursement())).thenReturn(new Reimbursement());

		Reimbursement expected = new Reimbursement();
		Reimbursement actual = rs.createNewReimbursement(0, 0, 0, null);
		assertEquals(expected, actual);
	}


}
