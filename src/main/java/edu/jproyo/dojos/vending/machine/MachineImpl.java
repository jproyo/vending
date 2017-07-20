package edu.jproyo.dojos.vending.machine;

import edu.jproyo.dojos.vending.VendingMachine;
import edu.jproyo.dojos.vending.model.OrderResult;
import edu.jproyo.dojos.vending.model.ProductOrder;
import edu.jproyo.dojos.vending.model.ProductRequest;
import edu.jproyo.dojos.vending.model.ResetStatus;

public class MachineImpl implements VendingMachine {

	@Override
	public ProductOrder select(ProductRequest request) {
		return new ProductOrder();
	}

	@Override
	public OrderResult cancel(ProductOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResetStatus reset() {
		// TODO Auto-generated method stub
		return null;
	}

}
