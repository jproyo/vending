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

/**
 * The Class OrderResult.
 */
public class OrderResult {


	/** The status. */
	private OrderResultStatus status = OrderResultStatus.noop;

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public OrderResultStatus getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(OrderResultStatus status) {
		this.status = status;
	}

	/**
	 * Was cancelled.
	 *
	 * @return true, if successful
	 */
	public boolean wasCancelled() {
		return OrderResultStatus.cancelled.equals(getStatus());
	}
	
	/**
	 * The Enum OrderResultStatus.
	 */
	public enum OrderResultStatus {
		
		/** The cancelled. */
		cancelled,
		/** The already delivered. */
		alreadyDelivered,
		/** The could not be cancelled. */
		couldNotBeCancelled,
		/** The noop. */
		noop

	}

}
