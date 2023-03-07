package com.github.daianaegermichels.api.domain.appointments;

import com.github.daianaegermichels.api.domain.patient.Patient;
import com.github.daianaegermichels.api.domain.physician.Physician;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "appointments")
@Entity(name = "Appointments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "physician_id")
    private Physician physician;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "patient_id")
    private Patient patient;

    private LocalDateTime data;

    @Column(name = "cancel_reason")
    @Enumerated(EnumType.STRING)
    private CancellationReason reason;

    public void cancel(CancellationReason reason) {
        this.reason = reason;
    }
}
