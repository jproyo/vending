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

import java.util.HashSet;
import java.util.Set;

import edu.jproyo.dojos.vending.model.ItemResult.Result;
import edu.jproyo.dojos.vending.model.OrderResult.OrderResultStatus;

/**
 * The Class ProductOrder.
 */
public class ProductOrder {
	

	/** The product requested. */
	private Product productRequested;
	
	/** The status. */
	private ProductOrderStatus status = ProductOrderStatus.noSelected;
	
	/** The import payed. */
	private Float importPayed = 0.0f;

	/** The accepted coins. */
	private Set<Float> acceptedCoins = new HashSet<>();
	
	/**
	 * Instantiates a new product order.
	 */
	public ProductOrder(){
		this.acceptedCoins.add(0.05f);
		this.acceptedCoins.add(0.10f);
		this.acceptedCoins.add(0.20f);
		this.acceptedCoins.add(0.50f);
		this.acceptedCoins.add(1.0f);
		this.acceptedCoins.add(2.0f);
	}
	
	/**
	 * Instantiates a new product order.
	 *
	 * @param requested the requested
	 */
	public ProductOrder(Product requested){
		this();
		this.productRequested = requested;
		if(!requested.isNotSelected()){
			this.status = ProductOrderStatus.insuffientFund;
		}
	}

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
	 * Payment pending.
	 *
	 * @return true, if successful
	 */
	public boolean paymentPending() {
		return !paymentReady();
	}
	
	/**
	 * Payment ready.
	 *
	 * @return true, if successful
	 */
	public boolean paymentReady() {
		return importPayed >= getProductRequested().getPrice();
	}
	
	/**
	 * Gets the product requested.
	 *
	 * @return the product requested
	 */
	public Product getProductRequested() {
		return productRequested;
	}

	/**
	 * Sets the product requested.
	 *
	 * @param productRequested the new product requested
	 */
	public void setProductRequested(Product productRequested) {
		this.productRequested = productRequested;
	}
	
	/**
	 * Gets the import payed.
	 *
	 * @return the import payed
	 */
	public Float getImportPayed() {
		return importPayed;
	}

	/**
	 * Sets the import payed.
	 *
	 * @param importPayed the new import payed
	 */
	public void setImportPayed(Float importPayed) {
		this.importPayed = importPayed;
	}

	/**
	 * Gets the change.
	 *
	 * @return the change
	 */
	public Float getChange() {
		return this.importPayed - getProductRequested().getPrice();
	}

	/**
	 * Adds the payment.
	 *
	 * @param coin the coin
	 */
	public void addPayment(Float coin){
		if(acceptedCoins.contains(coin)){
			importPayed += coin;
		}else{
			throw new IllegalStateException("Coin "+coin+"€ is not accepted for this payment");
		}
		if(paymentReady()){
			this.status = ProductOrderStatus.paymentReady;
		}
	}

	/**
	 * Cancel.
	 *
	 * @return the order result
	 */
	public OrderResult cancel() {
		OrderResult result = new OrderResult();
		switch(getStatus()){
		case delivered: 
			result.setStatus(OrderResultStatus.alreadyDelivered);
			break;
		case paymentReady:
		case insuffientFund:
		case noSelected:
			result.setStatus(OrderResultStatus.cancelled);
			result.setRefund(getImportPayed());
			break;
		}
		return result;
	}

	/**
	 * Dispatch.
	 *
	 * @return the item result
	 */
	public ItemResult dispatch() {
		ItemResult result = new ItemResult();
		result.setProduct(getProductRequested());
		if(paymentReady()){
			result.setResult(Result.delivered);
			result.setChange(getChange());
		}else{
			result.setResult(Result.insufficientFunds);
		}
		return result;
	}

	/**
	 * No selected.
	 *
	 * @return the product order
	 */
	public static ProductOrder noSelected() {
		ProductOrder productOrder = new ProductOrder();
		productOrder.setStatus(ProductOrderStatus.noSelected);
		return productOrder;
	}

	/**
	 * The Enum ProductOrderStatus.
	 */
	public enum ProductOrderStatus {
		
		/** The payment pending. */
		paymentReady,
		
		/** The delivered. */
		delivered,
		
		/** The insuffient fund. */
		insuffientFund,
		
		/** The no selected. */
		noSelected
	}
	

}
