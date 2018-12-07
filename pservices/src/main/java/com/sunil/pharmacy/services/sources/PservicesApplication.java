package com.sunil.pharmacy.services.sources;

import com.sunil.pharmacy.services.sources.entities.Medicine;
import com.sunil.pharmacy.services.sources.repositories.MedicineRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class PservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PservicesApplication.class, args);
	}

	@Bean
	ApplicationRunner init(MedicineRepository repository) {
		return args -> {
			Stream.of(	"Snickers",
						"lollipop",
						"bubble gum",
						"apple juice",
						"simvastatin",
						"synthyroid",
						"vicks"	).forEach(name -> {
				Medicine medicineObject = new Medicine();
				medicineObject.setName(name);
				repository.save(medicineObject);
			});
			repository.findAll().forEach(System.out::println);
		};
	}
}
