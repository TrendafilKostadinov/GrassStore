package bg.softuni.grassstore.model.dto;

public class CustomerDetailDTO {

    private Long id;

    private String name;

    private String trader;

    private String contactPerson;

    public Long getId() {
        return id;
    }

    public CustomerDetailDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerDetailDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getTrader() {
        return trader;
    }

    public CustomerDetailDTO setTrader(String trader) {
        this.trader = trader;
        return this;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public CustomerDetailDTO setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    @Override
    public String toString() {
        return name + " - " + contactPerson;
    }
}
