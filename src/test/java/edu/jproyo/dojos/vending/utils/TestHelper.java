package edu.jproyo.dojos.vending.utils;

import java.util.concurrent.atomic.AtomicLong;

import edu.jproyo.dojos.vending.machine.dataaccess.MachineRepository;
import edu.jproyo.dojos.vending.machine.dataaccess.memory.OnMemoryRepository;
import edu.jproyo.dojos.vending.model.Product;
import edu.jproyo.dojos.vending.model.ProductType;

public class TestHelper {

	public static MachineRepository loadMemoryDB() {
		OnMemoryRepository repo = new OnMemoryRepository();
		repo.getProducts().put(ProductType.coke, new Product(ProductType.coke, 1.5f));
		repo.getProducts().put(ProductType.sprite, new Product(ProductType.sprite, 1.45f));
		repo.getProducts().put(ProductType.water, new Product(ProductType.water, 0.9f));
		repo.getItems().put(ProductType.coke, new AtomicLong(50));
		repo.getItems().put(ProductType.sprite, new AtomicLong(50));
		repo.getItems().put(ProductType.water, new AtomicLong(50));
		return repo;
	}

}
