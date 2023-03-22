package com.github.daianaegermichels.api.controller;

import com.github.daianaegermichels.api.domain.appointments.*;
import com.github.daianaegermichels.api.domain.physician.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AppointmentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentsData> appointmentsDataJson;

    @Autowired
    private JacksonTester<AppointmentsCancelData> appointmentsCancelDataJson;
    @Autowired
    private JacksonTester<AppointmentsDetails> appointmentsDetailsJson;

    @MockBean
    private AppointmentScheduling appointmentScheduling;


    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    @WithMockUser
    void toSchedule() throws Exception {
       var response = mvc.perform(post("/appointments")).andReturn().getResponse();

       assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 200 when information is valid")
    @WithMockUser
    void toScheduleOk() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;

        var appointmentsDataDetails = new AppointmentsDetails(null, 2l, 3l, data);
        when(appointmentScheduling.toSchedule(any())).thenReturn(appointmentsDataDetails);

        var response = mvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( appointmentsDataJson.write(
                                new AppointmentsData(2l, 3l, data, specialty)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonExpected = appointmentsDetailsJson.write(
                appointmentsDataDetails
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }

    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    @WithMockUser
    void cancel() throws Exception {
        var response = mvc.perform(delete("/appointments")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 204 when information is valid")
    @WithMockUser
    void cancelOk() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var reason = CancellationReason.PATIENT_GAVE_UP;

        var appointmentsDataDetails = new AppointmentsDetails(2l, 2l, 3l, data);
        when(appointmentScheduling.toSchedule(any())).thenReturn(appointmentsDataDetails);

        var response = mvc.perform(
                        delete("/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(appointmentsCancelDataJson.write(
                                        new AppointmentsCancelData(2l, reason)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

}
