package com.github.daianaegermichels.api.physician;

import com.github.daianaegermichels.api.address.AddressData;
import jakarta.validation.constraints.NotNull;

public record PhysicianDataUpdate(@NotNull
                                  Long id,
                                  String name,
                                  String phone,
                                  AddressData address) {
}
