package com.github.daianaegermichels.api.patient;

import com.github.daianaegermichels.api.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PatientDataUpdate(@NotNull
                                Long id,
                                String name,
                                String phone,
                                @Valid
                                AddressData address) {
}
