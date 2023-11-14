package bg.softuni.grassstore.model.dto;

import jakarta.validation.constraints.NotBlank;

public class AddressDetailDTO {

    private Long id;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    private int number;

    @NotBlank
    private String country;

    public Long getId() {
        return id;
    }

    public AddressDetailDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDetailDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressDetailDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public AddressDetailDTO setNumber(int number) {
        this.number = number;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressDetailDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    @Override
    public String toString() {
        return  city + ", " + street + " " + number + ", " + country;
    }
}
