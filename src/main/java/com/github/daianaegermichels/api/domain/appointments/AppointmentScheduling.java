package com.github.daianaegermichels.api.domain.appointments;

import com.github.daianaegermichels.api.domain.ValidationException;
import com.github.daianaegermichels.api.domain.appointments.validations.canceller.ValidateAppointmentsCanceller;
import com.github.daianaegermichels.api.domain.appointments.validations.scheduling.ValidateAppointmentsScheduling;
import com.github.daianaegermichels.api.domain.patient.PatientRepository;
import com.github.daianaegermichels.api.domain.physician.Physician;
import com.github.daianaegermichels.api.domain.physician.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentScheduling {

    @Autowired
    private AppointmentsRepository repository;
    @Autowired
    private PhysicianRepository physicianRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidateAppointmentsScheduling> validators;

    @Autowired
    private List<ValidateAppointmentsCanceller> validatorCancel;

    public AppointmentsDetails toSchedule(AppointmentsData data) {
        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("The patient ID entered does not exist!");
        }
        if (data.idPhysician() != null && !physicianRepository.existsById(data.idPhysician())) {
            throw new ValidationException("The physician ID entered does not exist!");
        }

        validators.forEach(v -> v.validate(data));

        var patient = patientRepository.findById(data.idPatient()).get();
        var physician = choosePhysician(data);
        var appointments = new Appointments(null, physician, patient, data.data(), null);
        var save = repository.save(appointments);
        return new AppointmentsDetails(save.getId(),save.getPhysician().getId(), save.getPatient().getId(), save.getData());
    }

    private Physician choosePhysician(AppointmentsData data) {
        if (data.idPhysician() != null) {
            return physicianRepository.getReferenceById(data.idPhysician());
        }

        if (data.specialty() == null) {
            throw new ValidationException("Specialty is mandatory when the doctor is not informed!");
        }

        return physicianRepository.chooseFreeRandomPhysicianOnDate(data.specialty(), data.data());
    }

    public void cancel(AppointmentsCancelData data) {
            if (!repository.existsById(data.idAppointment())) {
                throw new ValidationException("The appointment ID entered does not exist!");
            }

            validatorCancel.forEach(v -> v.validate(data));

            var appointment = repository.getReferenceById(data.idAppointment());
            appointment.cancel(data.reason());
    }
}
