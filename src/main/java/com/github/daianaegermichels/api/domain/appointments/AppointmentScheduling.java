package com.github.daianaegermichels.api.domain.appointments;

import com.github.daianaegermichels.api.domain.patient.PatientRepository;
import com.github.daianaegermichels.api.domain.physician.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentScheduling {

    @Autowired
    private AppointmentsRepository repository;
    @Autowired
    private PhysicianRepository physicianRepository;
    @Autowired
    private PatientRepository patientRepository;
    public void toSchedule(AppointmentsData data) {
        var patient = patientRepository.findById(data.idPatient()).get();
        var physician = physicianRepository.findById(data.idPhysician()).get();
        var appointments = new Appointments(null, physician, patient, data.data());
        repository.save(appointments);
    }
}
