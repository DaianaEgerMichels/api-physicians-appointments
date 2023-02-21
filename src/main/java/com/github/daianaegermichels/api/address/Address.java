package com.github.daianaegermichels.api.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String district;
    @Column(name = "zip_code")
    private String zipCode;
    private String city;
    private String state;
    private String complement;
    private String number;

    public Address(AddressData address) {
        this.street = address.street();
        this.district = address.district();
        this.zipCode = address.zipCode();
        this.city = address.city();
        this.state = address.state();
        this.complement = address.complement();
        this.number = address.number();
    }

    public void updateInformation(AddressData address) {
        if (address.street() != null) {
            this.street = address.street();
        }
        if (address.district() != null) {
            this.district = address.district();
        }
        if (address.zipCode() != null) {
            this.zipCode = address.zipCode();
        }
        if (address.city() != null) {
            this.city = address.city();
        }
        if (address.state() != null) {
            this.state = address.state();
        }
        if (address.complement() != null) {
            this.complement = address.complement();
        }
        if (address.number() != null) {
            this.number = address.number();
        }
    }
}
