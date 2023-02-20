package com.github.daianaegermichels.api.physician;

import com.github.daianaegermichels.api.address.AddressData;

public record PhysicianData(String name, String email, String crm, Specialty specialty, AddressData address) {
}
