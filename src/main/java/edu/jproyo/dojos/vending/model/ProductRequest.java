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
 * The Class ProductRequest.
 */
public class ProductRequest {

	/** The type. */
	private ProductType type;

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public ProductType getType() {
		return type;
	}


	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(ProductType type) {
		this.type = type;
	}


	/**
	 * Validate.
	 */
	public void validate() {
		if(type == null) throw new IllegalStateException("We need to know about what product you want to choose");
	}


	/**
	 * Creates the.
	 *
	 * @return the builder
	 */
	public static Builder create() {
		return new Builder();
	}


	/**
	 * The Class Builder.
	 */
	public static class Builder {
		
		/** The target. */
		private ProductRequest target = new ProductRequest();

		/**
		 * Type.
		 *
		 * @param type the type
		 * @return the builder
		 */
		public Builder type(ProductType type) {
			this.target.setType(type);
			return this;
		}
		
		/**
		 * Builds the.
		 *
		 * @return the product request
		 */
		public ProductRequest build() {
			this.target.validate();
			return this.target;
		}

	}



}
