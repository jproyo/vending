/**
 * Copyright (C) 2015 mxHero Inc (mxhero@mxhero.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.jproyo.dojos.vending.machine.dataaccess.memory;

import java.util.HashMap;
import java.util.Map;

import edu.jproyo.dojos.vending.machine.dataaccess.MachineRepository;
import edu.jproyo.dojos.vending.model.Product;
import edu.jproyo.dojos.vending.model.ProductType;

/**
 * The Class OnMemoryRepository.
 */
public class OnMemoryRepository implements MachineRepository {
	
	/** The products. */
	private Map<ProductType, Product> products = new HashMap<>(); 

	/* (non-Javadoc)
	 * @see edu.jproyo.dojos.vending.machine.dataaccess.MachineRepository#getProduct(edu.jproyo.dojos.vending.model.ProductType)
	 */
	@Override
	public Product getProduct(ProductType type) {
		return getProducts().get(type);
	}

	/**
	 * Gets the products.
	 *
	 * @return the products
	 */
	public Map<ProductType, Product> getProducts() {
		return products;
	}

	/**
	 * Sets the products.
	 *
	 * @param products the products
	 */
	public void setProducts(Map<ProductType, Product> products) {
		this.products = products;
	}

}
