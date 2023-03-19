package com.github.daianaegermichels.api.domain.physician;

import com.github.daianaegermichels.api.domain.address.AddressData;
import com.github.daianaegermichels.api.domain.appointments.Appointments;
import com.github.daianaegermichels.api.domain.patient.Patient;
import com.github.daianaegermichels.api.domain.patient.PatientData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PhysicianRepositoryTest {

    @Autowired
    PhysicianRepository physicianRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Must return null when the only registered physician is not available on the date")
    void chooseFreeRandomPhysicianOnDate() {
        //given or arrange
        var nextMondayAtTen = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        //when or act
        var physician = registerPhysician("Physician Test", "physician@test.com", "123456", Specialty.CARDIOLOGY);
        var patient = registerPatient("Patient", "patient@test.com", "12345678911");
        registerAppointment(physician, patient, nextMondayAtTen);

        //then or assert
        var physicianAvailable = physicianRepository.chooseFreeRandomPhysicianOnDate(Specialty.CARDIOLOGY, nextMondayAtTen);
        assertThat(physicianAvailable).isNull();
    }

    @Test
    @DisplayName("Must return physician is available on the date")
    void chooseFreeRandomPhysicianOnDate2() {
        //given or arrange
        var nextMondayAtTen = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        //when or act
        var physician = registerPhysician("Physician Test", "physician@test.com", "123456", Specialty.CARDIOLOGY);

        //then or assert
        var physicianAvailable = physicianRepository.chooseFreeRandomPhysicianOnDate(Specialty.CARDIOLOGY, nextMondayAtTen);
        assertThat(physicianAvailable).isEqualTo(physician);
    }

    private Physician registerPhysician (String name, String email, String crm, Specialty specialty) {
        var physician = new Physician(physicianData(name, email, crm, specialty));
        em.persist(physician);
        return physician;
    }

    private PhysicianData physicianData(String name, String email, String crm, Specialty specialty) {
        return new PhysicianData(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "Street xpto",
                "neighborhood",
                "00000000",
                "City",
                "SD",
                null,
                null
        );
    }

    private Patient registerPatient (String name, String email, String cpf) {
        var patient = new Patient(patientData(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private PatientData patientData(String name, String email, String cpf) {
        return new PatientData(
                name,
                email,
                "61999999999",
                cpf,
                addressData()
        );
    }

    private void registerAppointment (Physician physician, Patient patient, LocalDateTime data) {
        em.persist(new Appointments(null, physician, patient, data, null));
    }
}