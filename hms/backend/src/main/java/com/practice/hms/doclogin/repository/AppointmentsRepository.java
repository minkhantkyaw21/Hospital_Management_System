package com.practice.hms.doclogin.repository;

import com.practice.hms.doclogin.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment,Long> {
}
