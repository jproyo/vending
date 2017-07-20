/**
 * 
 */
package edu.jproyo.dojos.vending;

import edu.jproyo.dojos.vending.machine.VendingMachineBuilder;
import edu.jproyo.dojos.vending.model.ItemResult;
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
	 * Insert coin.
	 *
	 * @param coin the coin
	 * @return the product order
	 */
	public ProductOrder insertCoin(Float coin);
	
	/**
	 * Dispatch.
	 *
	 * @return the product 
	 */
	public ItemResult dispatch();
	
	/**
	 * Cancel.
	 *
	 * @return the order result
	 */
	public OrderResult cancel();
	
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
	public static VendingMachine create(){
		return new VendingMachineBuilder().build();
	}

}
