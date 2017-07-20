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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * The Class ResetStatus.
 */
public class ResetResult {

	/** The result. */
	private Future<ResetStatus> result;

	/**
	 * Instantiates a new reset result.
	 *
	 * @param result the result
	 */
	public ResetResult(Future<ResetStatus> result) {
		this.result = result;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public Future<ResetStatus> getResult() {
		return result;
	}
	
	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(Future<ResetStatus> result) {
		this.result = result;
	}
	
	/**
	 * Reseting.
	 *
	 * @return true, if successful
	 */
	public boolean reseting() {
		return !getResult().isDone();
	}

	/**
	 * Reseted.
	 *
	 * @return true, if successful
	 */
	public boolean reseted() {
		if(getResult().isDone()){
			try {
				return ResetStatus.reseted.equals(getResult().get());
			} catch (InterruptedException e) {
			} catch (ExecutionException e) {
			}
		}
		return false;
	}

}
