package com.github.daianaegermichels.api.domain.appointments;

import com.github.daianaegermichels.api.domain.ValidationException;
import com.github.daianaegermichels.api.domain.patient.PatientRepository;
import com.github.daianaegermichels.api.domain.physician.Physician;
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
        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("The patient ID entered does not exist!");
        }
        if (data.idPhysician() != null && !physicianRepository.existsById(data.idPhysician())) {
            throw new ValidationException("The physician ID entered does not exist!");
        }

        var patient = patientRepository.findById(data.idPatient()).get();
        var physician = choosePhysician(data);
        var appointments = new Appointments(null, physician, patient, data.data());
        repository.save(appointments);
    }

    private Physician choosePhysician(AppointmentsData data) {
        if (data.idPhysician() != null) {
            return physicianRepository.getReferenceById(data.idPhysician());
        }

        if (data.specialty() == null) {
            throw new ValidationException("Specialty is mandatory when the doctor is not informed");
        }

        return physicianRepository.chooseFreeRandomPhysicianOnDate(data.specialty(), data.data());
    }
}
