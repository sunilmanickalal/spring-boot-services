package com.sunil.pharmacy.services.sources.repositories;

import com.sunil.pharmacy.services.sources.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}