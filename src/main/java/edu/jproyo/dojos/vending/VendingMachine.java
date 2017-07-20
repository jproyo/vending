/**
 * 
 */
package edu.jproyo.dojos.vending;

import edu.jproyo.dojos.vending.machine.VendingMachineBuilder;
import edu.jproyo.dojos.vending.model.OrderResult;
import edu.jproyo.dojos.vending.model.ProductOrder;
import edu.jproyo.dojos.vending.model.ProductRequest;
import edu.jproyo.dojos.vending.model.ResetStatus;

/**
 * The Interface VendingMachine.
 *
 * @author juan
 */
public interface VendingMachine {
	
	/**
	 * Select.
	 *
	 * @param request the request
	 * @return the product order
	 */
	public ProductOrder select(ProductRequest request);
	
	/**
	 * Cancel.
	 *
	 * @param order the order
	 * @return the order result
	 */
	public OrderResult cancel(ProductOrder order);
	
	/**
	 * Reset.
	 *
	 * @return the reset status
	 */
	public ResetStatus reset();
	
	
	/**
	 * Creates the.
	 *
	 * @return the vending machine
	 */
	default VendingMachine create(){
		return new VendingMachineBuilder().build();
	}

}
