package com.practice.hms.doclogin.controller;

import com.practice.hms.doclogin.entity.Appointment;
import com.practice.hms.doclogin.repository.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin(origins = "*")
public class AppointmentController {
    @Autowired
    private AppointmentsRepository appointmentsRepository;
    @PostMapping("/appointments")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentsRepository.save(appointment);
    }
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentsRepository.findAll();
    }
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteAppointment(@PathVariable Long id) throws AttributeNotFoundException {

        Appointment appointment= appointmentsRepository.findById(id).orElseThrow(
                ()->new AttributeNotFoundException("Appointment not found with id"+id));
        appointmentsRepository.delete(appointment);
        Map<String,Boolean> response = new HashMap<String,Boolean>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/appointments/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) throws AttributeNotFoundException {
        Appointment appointment = appointmentsRepository.findById(id).orElseThrow(
                ()->new AttributeNotFoundException("Appointment not found with id"+id)
        );
        appointment.setName(appointmentDetails.getName());
        appointment.setAge(appointmentDetails.getAge());
       appointment.setSymptoms(appointmentDetails.getSymptoms());
       appointment.setNumber(appointmentDetails.getNumber());

       Appointment updatedAppointment = appointmentsRepository.save(appointment);
       return ResponseEntity.ok(updatedAppointment);
    }
}
