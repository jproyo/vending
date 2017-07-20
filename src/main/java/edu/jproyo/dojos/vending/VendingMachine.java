/**
 * 
 */
package edu.jproyo.dojos.vending;

import edu.jproyo.dojos.vending.model.OrderResult;
import edu.jproyo.dojos.vending.model.ProductOrder;
import edu.jproyo.dojos.vending.model.ProductRequest;
import edu.jproyo.dojos.vending.model.ResetStatus;

/**
 * @author juan
 *
 */
public interface VendingMachine {
	
	public ProductOrder select(ProductRequest request);
	
	public OrderResult cancel(ProductOrder order);
	
	public ResetStatus reset();

}
