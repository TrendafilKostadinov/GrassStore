package bg.softuni.grassstore.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class CustomerEntity extends BaseEntity{

    @NotBlank
    @Size(min = 2,max = 50)
    @Column(unique = true)
    private String name;

    @NotNull
    @ManyToOne
    private AddressEntity address;

    @NotBlank
    @Size(min = 2,max = 30)
    private String contactPerson;

    private String phone;

    @NotBlank
    private String vatNumber;

    @NotNull
    @ManyToOne
    private UserEntity trader;

    public String getName() {
        return name;
    }

    public CustomerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public CustomerEntity setAddress(AddressEntity address) {
        this.address = address;
        return this;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public CustomerEntity setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public CustomerEntity setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
        return this;
    }

    public UserEntity getTrader() {
        return trader;
    }

    public CustomerEntity setTrader(UserEntity trader) {
        this.trader = trader;
        return this;
    }
}
