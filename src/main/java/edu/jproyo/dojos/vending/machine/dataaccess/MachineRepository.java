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
package edu.jproyo.dojos.vending.machine.dataaccess;

import edu.jproyo.dojos.vending.model.ItemResult;
import edu.jproyo.dojos.vending.model.Product;
import edu.jproyo.dojos.vending.model.ProductType;

/**
 * The Interface MachineRepository.
 */
public interface MachineRepository {

	/**
	 * Gets the product.
	 *
	 * @param type the type
	 * @return the product
	 */
	public Product getProduct(ProductType type);

	/**
	 * Product available.
	 *
	 * @param type the type
	 * @return true, if successful
	 */
	public boolean productAvailable(ProductType type);

	/**
	 * Update stock.
	 *
	 * @param item the item
	 * @return 
	 */
	public long updateStock(ItemResult item);

}
