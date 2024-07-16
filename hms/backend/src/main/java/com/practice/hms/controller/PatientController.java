package com.practice.hms.controller;

import com.practice.hms.entity.Patient;
import com.practice.hms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient) {

        return patientRepository.save(patient);
    }
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) throws AttributeNotFoundException {
        Patient patient=patientRepository.findById(id).orElseThrow(
                ()->new AttributeNotFoundException("Patient not found with id"+id));
    return ResponseEntity.ok(patient);
    }
//    @GetMapping()
//    public ResponseEntity<List<Patient>> getAllPatientsByName(@RequestParam String name) {
//
//    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePatient(@PathVariable Long id) throws AttributeNotFoundException {
        Patient patient=patientRepository.findById(id).orElseThrow(
                ()->new AttributeNotFoundException("Patient not found with id"+id)
        );
        patientRepository.delete(patient);
        Map<String,Boolean> response = new HashMap<String,Boolean>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails)
            throws AttributeNotFoundException {
        Patient patient=patientRepository.findById(id).orElseThrow(
                ()->new AttributeNotFoundException("Patient not found with id"+id));
        patient.setAge(patientDetails.getAge());
        patient.setName(patientDetails.getName());
        patient.setBlood(patientDetails.getBlood());
        patient.setPrescription(patientDetails.getPrescription());
        patient.setFees(patientDetails.getFees());
        patient.setDose(patientDetails.getDose());
        patient.setUrgency(patientDetails.getUrgency());
        patient.setId(patientDetails.getId());

        Patient updatedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatient);
    }
}
