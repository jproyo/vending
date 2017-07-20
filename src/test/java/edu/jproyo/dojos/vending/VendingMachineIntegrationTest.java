package edu.jproyo.dojos.vending;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.jproyo.dojos.vending.machine.MachineImpl;
import edu.jproyo.dojos.vending.machine.VendingMachineBuilder;
import edu.jproyo.dojos.vending.machine.dataaccess.MachineRepository;
import edu.jproyo.dojos.vending.model.OrderResult;
import edu.jproyo.dojos.vending.model.ProductOrder;
import edu.jproyo.dojos.vending.model.ProductRequest;
import edu.jproyo.dojos.vending.model.ProductType;
import edu.jproyo.dojos.vending.model.ResetResult;
import edu.jproyo.dojos.vending.utils.TestHelper;

public class VendingMachineIntegrationTest {
	
	VendingMachine target;
	
	@Before
	public void setup(){
		MachineRepository repository = TestHelper.loadMemoryDB();
		target = new VendingMachineBuilder().machine(MachineImpl.create().repository(repository)).build();
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
		assertTrue(order.paymentPending());
		OrderResult cancel = target.cancel(order);
		assertNotNull(cancel);
		assertTrue(cancel.wasCancelled());
	}

	@Test
	public void testReset() {
		ResetResult reset = target.reset();
		assertNotNull(reset);
		assertTrue(reset.reseting());
		try {
			Thread.sleep(2000l);
		} catch (InterruptedException e) {
			fail();
		}
		assertTrue(reset.reseted());
	}

}
