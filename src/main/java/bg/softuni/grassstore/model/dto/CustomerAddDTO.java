package bg.softuni.grassstore.model.dto;

import bg.softuni.grassstore.model.entity.AddressEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerAddDTO {

    @NotBlank
    @Size(min = 2,max = 50)
    private String name;

    @NotNull
    private Long address;

    @NotBlank
    @Size(min = 2,max = 30)
    private String contactPersonEmail;

    private String phone;

    @NotBlank
    private String vatNumber;

    private Long traderId;

    public String getName() {
        return name;
    }

    public CustomerAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getAddress() {
        return address;
    }

    public CustomerAddDTO setAddress(Long address) {
        this.address = address;
        return this;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public CustomerAddDTO setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerAddDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public CustomerAddDTO setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
        return this;
    }

    public Long getTraderId() {
        return traderId;
    }

    public CustomerAddDTO setTraderId(Long traderId) {
        this.traderId = traderId;
        return this;
    }
}
