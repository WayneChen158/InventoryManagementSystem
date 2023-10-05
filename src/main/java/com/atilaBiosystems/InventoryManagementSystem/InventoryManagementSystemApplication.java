package com.atilaBiosystems.InventoryManagementSystem;

import java.sql.SQLOutput;
import java.util.Date;

import com.atilaBiosystems.InventoryManagementSystem.entity.RawMaterials;
import com.atilaBiosystems.InventoryManagementSystem.repository.RawMaterialsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemApplication.class, args);
	}
}
