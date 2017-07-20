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

import edu.jproyo.dojos.vending.model.ProductOrder.ProductOrderStatus;

/**
 * The Class ProductOrder.
 */
public class ProductOrder {
	


	/** The product requested. */
	private Product productRequested;
	
	/** The status. */
	private ProductOrderStatus status = ProductOrderStatus.paymentPending;

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public ProductOrderStatus getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(ProductOrderStatus status) {
		this.status = status;
	}
	
	/**
	 * Checks if is being proccessed.
	 *
	 * @return true, if is being proccessed
	 */
	public boolean isBeingProccessed() {
		return ProductOrderStatus.processing.equals(status);
	}

	/**
	 * Payment pending.
	 *
	 * @return true, if successful
	 */
	public boolean paymentPending() {
		return ProductOrderStatus.paymentPending.equals(status);
	}
	
	/**
	 * The Enum ProductOrderStatus.
	 */
	public enum ProductOrderStatus {
		
		/** The payment pending. */
		paymentPending,
		
		/** The processing. */
		processing,
		
		/** The delivered. */
		delivered,
		
		/** The insuffient fund. */
		insuffientFund
	}
	

}
