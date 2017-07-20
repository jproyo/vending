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
package edu.jproyo.dojos.vending.model;

import java.util.UUID;

/**
 * The Class Item.
 */
public class ItemResult {

	/** The id. */
	private String id = UUID.randomUUID().toString();
	
	/** The product. */
	private Product product;

	/** The change. */
	private Float change;
	
	/** The result. */
	private Result result = Result.delivered;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets the product.
	 *
	 * @param product the new product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemResult other = (ItemResult) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Sets the change.
	 *
	 * @param change the new change
	 */
	public void setChange(Float change) {
		this.change = change;
	}
	

	/**
	 * Gets the change.
	 *
	 * @return the change
	 */
	public Float getChange() {
		return change;
	}
	
	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}
	
	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(Result result) {
		this.result = result;
	}
	
	
	
	/**
	 * The Enum Result.
	 */
	public enum Result {
		
		/** The delivered. */
		delivered,
		
		/** The insufficient funds. */
		insufficientFunds,
		
		/** The no stock. */
		noStock

	}



	/**
	 * No stock.
	 *
	 * @param currentOrder the current order
	 * @return the item result
	 */
	public static ItemResult noStock(ProductOrder currentOrder) {
		ItemResult result = new ItemResult();
		result.setResult(Result.noStock);
		result.setChange(currentOrder.getImportPayed());
		result.setProduct(currentOrder.getProductRequested());
		return result;
	}

}
