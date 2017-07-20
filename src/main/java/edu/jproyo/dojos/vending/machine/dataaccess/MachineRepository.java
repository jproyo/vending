package edu.jproyo.dojos.vending.machine.dataaccess;

import edu.jproyo.dojos.vending.model.Product;
import edu.jproyo.dojos.vending.model.ProductType;

public interface MachineRepository {

	public Product getProduct(ProductType type);

}
