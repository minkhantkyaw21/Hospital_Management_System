package com.practice.hms.doclogin.controller;

import com.practice.hms.doclogin.entity.Medicine;
import com.practice.hms.doclogin.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3")
@CrossOrigin(origins = "*")
public class MedicineController {
    @Autowired
    private MedicineRepository medicineRepository;
    @PostMapping("/medicines")
    public Medicine createMedicine(@RequestBody Medicine medicine) {
        return medicineRepository.save(medicine);
    }
    @GetMapping("/medicines")
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
    @GetMapping("/medicines/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) throws AttributeNotFoundException {
        Medicine medicine = medicineRepository.findById(id).orElseThrow(
                ()->
                new AttributeNotFoundException("Medicine not found with id"+id));

    return ResponseEntity.ok(medicine);
    }
    @PutMapping("/medicines/{id}")
    public  ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine medicineDetails) throws AttributeNotFoundException {
        Medicine medicine= medicineRepository.findById(id).orElseThrow(
                ()->new AttributeNotFoundException("Medicine not found with id"+id)
        );
        medicine.setDrugName(medicineDetails.getDrugName());
        medicine.setStock(medicineDetails.getStock());

        medicineRepository.save(medicine);
        return ResponseEntity.ok(medicine);
    }
    @DeleteMapping("/medicines/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteMedicine(@PathVariable Long id) throws AttributeNotFoundException {
        Medicine medicine = medicineRepository.findById(id).orElseThrow(
                ()->new AttributeNotFoundException("Medicine not found with id"+id)
        );
        medicineRepository.delete(medicine);
        Map<String,Boolean> response = new HashMap<String,Boolean>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
