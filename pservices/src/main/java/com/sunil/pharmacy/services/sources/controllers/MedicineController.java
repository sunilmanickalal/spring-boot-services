package com.sunil.pharmacy.services.sources.controllers;

import com.sunil.pharmacy.services.sources.entities.Medicine;
import com.sunil.pharmacy.services.sources.repositories.MedicineRepository;
import com.sunil.pharmacy.services.sources.retry.RetryLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableRetry
@RestController
@Slf4j
class MedicineController {
    private MedicineRepository repository;

    public MedicineController(MedicineRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/medicine/list")
    public Collection<Medicine> medicineList() {
        log.info("Inside medicineList");
        return repository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/medicine/retry")
    @Retryable(value = {IOException.class}, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public String simpleRetry() throws Exception {
        log.info("inside retry method");
        return RetryLogic.ioExceptionThrown();
    }

    @Recover
    public String recoverFromIOException(IOException ioex) {
//        ResponseStatus
        log.info("recover method called and exception received is: " + ioex.getMessage());
        return "Recover Method Called";
    }

    @Recover
    public String recoverFromRemoteException(RemoteAccessException ioex) {
//        ResponseStatus
        log.info("recover method called and exception received is: " + ioex.getMessage());
        return "Recover Method Called";
    }

    @GetMapping("/medicine/properList")
    public Collection<Medicine> filteredMedicinesList() {
        log.info("Inside filteredMedicinesList");
        return repository.findAll().stream()
                .filter(this::isUseful)
                .collect(Collectors.toList());
    }

    @GetMapping("/medicine/{id}")
    public Optional<Medicine> getMedicine(@PathVariable Long id) {
        log.info("Inside getMedicine");
        return repository.findById(id);
    }

    @PostMapping("/medicine/add")
    public void addMedicine(@RequestBody Medicine medicineObject) {
        log.info("Inside addMedicine");
        repository.save(medicineObject);
    }

    @PutMapping("/medicine/update")
    public void updateMedicineById(@RequestBody Medicine medicineObject) {
        log.info("Inside updateMedicineById");
        repository.saveAndFlush(medicineObject);
    }

    private boolean isUseful(Medicine medicineObject) {
        log.info("Inside isUseful");
        return !medicineObject.getName().equals("Snickers") &&
                !medicineObject.getName().equals("lollipop") &&
                !medicineObject.getName().equals("bubble gum") &&
                !medicineObject.getName().equals("apple juice");
    }

    @DeleteMapping("/medicine/delete/id")
    public void deleteMedicineById(@PathVariable Long id) {
        log.info("Inside deleteMedicineById");
        repository.deleteById(id);
    }
}