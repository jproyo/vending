package edu.jproyo.dojos.vending;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.jproyo.dojos.vending.machine.MachineImpl;
import edu.jproyo.dojos.vending.machine.VendingMachineBuilder;
import edu.jproyo.dojos.vending.machine.dataaccess.MachineRepository;
import edu.jproyo.dojos.vending.model.ItemResult;
import edu.jproyo.dojos.vending.model.ItemResult.Result;
import edu.jproyo.dojos.vending.model.OrderResult;
import edu.jproyo.dojos.vending.model.ProductOrder;
import edu.jproyo.dojos.vending.model.ProductRequest;
import edu.jproyo.dojos.vending.model.ProductType;
import edu.jproyo.dojos.vending.model.ResetStatus;
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
	}
	
	@Test
	public void testInsertCoins() {
		ProductOrder order = target.select(ProductRequest.create().type(ProductType.coke).build());
		assertNotNull(order);
		assertTrue(order.paymentPending());
		assertNotNull(target.insertCoin(0.20f));
		assertNotNull(target.insertCoin(0.20f));
		assertNotNull(target.insertCoin(0.20f));
		assertNotNull(target.insertCoin(0.20f));
		assertNotNull(target.insertCoin(0.20f));
		assertNotNull(target.insertCoin(0.20f));
		assertNotNull(target.insertCoin(0.20f));
		ProductOrder insertCoin = target.insertCoin(0.20f);
		assertNotNull(insertCoin);
		assertTrue(insertCoin.paymentReady());
	}
	
	@Test
	public void testInsertCoins_not_accepted() {
		ProductOrder order = target.select(ProductRequest.create().type(ProductType.coke).build());
		assertNotNull(order);
		assertTrue(order.paymentPending());
		assertNotNull(target.insertCoin(0.20f));
		assertNotNull(target.insertCoin(0.20f));
		assertNotNull(target.insertCoin(0.20f));
		try {
			target.insertCoin(0.23f);
			fail();
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testDispatch_ok() {
		ProductOrder order = target.select(ProductRequest.create().type(ProductType.coke).build());
		assertNotNull(order);
		assertTrue(order.paymentPending());
		assertNotNull(target.insertCoin(1f));
		assertNotNull(target.insertCoin(0.50f));
		ItemResult dispatch = target.dispatch();
		assertNotNull(dispatch);
		assertEquals(new Float(0.0f), dispatch.getChange());
		assertEquals(Result.delivered, dispatch.getResult());
	}
	
	@Test
	public void testDispatch_with_refund() {
		ProductOrder order = target.select(ProductRequest.create().type(ProductType.coke).build());
		assertNotNull(order);
		assertTrue(order.paymentPending());
		assertNotNull(target.insertCoin(1f));
		assertNotNull(target.insertCoin(0.50f));
		assertNotNull(target.insertCoin(0.50f));
		ItemResult dispatch = target.dispatch();
		assertNotNull(dispatch);
		assertEquals(new Float(0.5f), dispatch.getChange());
		assertEquals(Result.delivered, dispatch.getResult());
	
	}
	
	@Test
	public void testDispatch_insufficients_funds() {
		ProductOrder order = target.select(ProductRequest.create().type(ProductType.coke).build());
		assertNotNull(order);
		assertTrue(order.paymentPending());
		assertNotNull(target.insertCoin(1f));
		ItemResult dispatch = target.dispatch();
		assertNotNull(dispatch);
		assertEquals(Result.insufficientFunds, dispatch.getResult());
	}

	@Test
	public void testDispatch_insufficients_funds_after_pay_and_dispatch() {
		ProductOrder order = target.select(ProductRequest.create().type(ProductType.coke).build());
		assertNotNull(order);
		assertTrue(order.paymentPending());
		assertNotNull(target.insertCoin(1f));
		ItemResult dispatch = target.dispatch();
		assertNotNull(dispatch);
		assertEquals(Result.insufficientFunds, dispatch.getResult());
		assertNotNull(target.insertCoin(0.5f));
		ItemResult dispatch2 = target.dispatch();
		assertNotNull(dispatch2);
		assertEquals(new Float(0f), dispatch2.getChange());
		assertEquals(Result.delivered, dispatch2.getResult());
	}

	@Test
	public void testCancel() {
		ProductOrder order = target.select(ProductRequest.create().type(ProductType.coke).build());
		assertNotNull(order);
		assertTrue(order.paymentPending());
		OrderResult cancel = target.cancel();
		assertNotNull(cancel);
		assertTrue(cancel.wasCancelled());
	}
	
	@Test
	public void testCancel_with_refund() {
		ProductOrder order = target.select(ProductRequest.create().type(ProductType.coke).build());
		assertNotNull(order);
		assertTrue(order.paymentPending());
		target.insertCoin(0.5f);
		target.insertCoin(0.2f);
		OrderResult cancel = target.cancel();
		assertNotNull(cancel);
		assertTrue(cancel.wasCancelled());
		assertEquals(new Float(0.7f), cancel.getRefund());
	}
	
	@Test
	public void testCancel_already_delivered() {
		ProductOrder order = target.select(ProductRequest.create().type(ProductType.coke).build());
		assertNotNull(order);
		assertTrue(order.paymentPending());
		assertNotNull(target.insertCoin(1f));
		assertNotNull(target.insertCoin(0.5f));
		ItemResult dispatch = target.dispatch();
		assertNotNull(dispatch);
		assertEquals(Result.delivered, dispatch.getResult());
		try {
			target.cancel();
			fail();
		} catch (IllegalStateException e) {
		}
	}

	@Test
	public void testReset() {
		ResetStatus reset = target.reset();
		assertNotNull(reset);
		assertEquals(ResetStatus.reseted, reset);
	}

}
