package edu.jproyo.dojos.vending;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.jproyo.dojos.vending.model.OrderResult;
import edu.jproyo.dojos.vending.model.ProductOrder;
import edu.jproyo.dojos.vending.model.ProductRequest;
import edu.jproyo.dojos.vending.model.ProductType;

public class VendingMachineIntegrationTest {
	
	VendingMachine target;
	
	@Before
	public void setup(){
		target = VendingMachine.create();
	}

	@Test
	public void testSelect() {
		ProductOrder order = target.select(ProductRequest.create().type(ProductType.coke).build());
		assertNotNull(order);
		assertTrue(order.paymentPending());
		assertFalse(order.isBeingProccessed());
	}

	@Test
	public void testCancel() {
		ProductOrder order = target.select(ProductRequest.create().type(ProductType.coke).build());
		assertNotNull(order);
		OrderResult cancel = target.cancel(order);
		assertNotNull(cancel);
		assertTrue(cancel.wasCancelled());
	}

	@Test
	public void testReset() {
		fail("Not yet implemented");
	}

}
