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
package edu.jproyo.dojos.vending.machine;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import edu.jproyo.dojos.vending.VendingMachine;
import edu.jproyo.dojos.vending.machine.dataaccess.MachineRepository;
import edu.jproyo.dojos.vending.machine.dataaccess.memory.OnMemoryRepository;
import edu.jproyo.dojos.vending.model.ItemResult;
import edu.jproyo.dojos.vending.model.OrderResult;
import edu.jproyo.dojos.vending.model.ProductOrder;
import edu.jproyo.dojos.vending.model.ProductRequest;
import edu.jproyo.dojos.vending.model.ResetStatus;

/**
 * The Class MachineImpl.
 */
public class MachineImpl implements VendingMachine {

	/** The repository. */
	private MachineRepository repository;
	
	/** The current order. */
	private ProductOrder currentOrder;

	/* (non-Javadoc)
	 * @see edu.jproyo.dojos.vending.VendingMachine#select(edu.jproyo.dojos.vending.model.ProductRequest)
	 */
	@Override
	public ProductOrder select(ProductRequest request) {
		if(currentOrder != null) return currentOrder;
		currentOrder = Optional.ofNullable(repository().getProduct(request.getType()))
			.map(ProductOrder::new)
			.orElse(ProductOrder.noSelected());
		return currentOrder;
	}
	
	/* (non-Javadoc)
	 * @see edu.jproyo.dojos.vending.VendingMachine#insertCoin(java.lang.Float)
	 */
	@Override
	public ProductOrder insertCoin(Float coin) {
		currentOrder.addPayment(coin);
		return currentOrder;
	}
	
	/* (non-Javadoc)
	 * @see edu.jproyo.dojos.vending.VendingMachine#dispatch()
	 */
	@Override
	public ItemResult dispatch() {
		if(repository().productAvailable(currentOrder.getProductRequested().getType())){
			ItemResult item = currentOrder.dispatch();
			if(item != null){
				repository().updateStock(item);
			}
			return item;
		}else{
			return ItemResult.noStock(currentOrder);
		}
	}

	/* (non-Javadoc)
	 * @see edu.jproyo.dojos.vending.VendingMachine#cancel(edu.jproyo.dojos.vending.model.ProductOrder)
	 */
	@Override
	public OrderResult cancel(ProductOrder order) {
		return order.cancel();
	}

	/* (non-Javadoc)
	 * @see edu.jproyo.dojos.vending.VendingMachine#reset()
	 */
	@Override
	public ResetStatus reset() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<ResetStatus> submit = executor.submit(new Callable<ResetStatus>() {
			@Override
			public ResetStatus call() throws Exception {
				Thread.sleep(1500l);
				return ResetStatus.reseted;
			}
		});
		ResetStatus resetStatus = ResetStatus.couldNotBeReset;
		try {
			resetStatus = submit.get(5, TimeUnit.SECONDS);
			executor.shutdown();
			if(!executor.awaitTermination(5, TimeUnit.SECONDS)){
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		} catch (TimeoutException e) {
		}finally{
			currentOrder = null;
		}
		return resetStatus;
	}
	
	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(MachineRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * Repository.
	 *
	 * @return the machine repository
	 */
	public MachineRepository repository(){
		if(repository == null) repository = new OnMemoryRepository();
		return repository;
	}
	  
	/**
	 * Repository.
	 *
	 * @param repository the repository
	 * @return the machine impl
	 */
	public MachineImpl repository(MachineRepository repository){
		this.repository = repository;
		return this;
	}
	
	
	/**
	 * Creates the.
	 *
	 * @return the machine impl
	 */
	public static MachineImpl create(){
		return new MachineImpl();
	}

}
