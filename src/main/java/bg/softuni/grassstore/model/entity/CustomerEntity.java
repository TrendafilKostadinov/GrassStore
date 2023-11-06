package bg.softuni.grassstore.model.entity;

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
    private String name;

    @NotBlank
    @ManyToOne
    private AddressEntity address;

    @NotBlank
    @Size(min = 2,max = 30)
    private String contactPerson;

    private String phone;

    @Email
    private String email;

    @NotBlank
    private String vatNumber;

    @NotNull
    @ManyToOne
    private UserEntity trader;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public UserEntity getTrader() {
        return trader;
    }

    public void setTrader(UserEntity trader) {
        this.trader = trader;
    }
}
