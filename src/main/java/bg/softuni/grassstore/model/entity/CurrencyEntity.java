package bg.softuni.grassstore.model.entity;

import bg.softuni.grassstore.model.enums.CurrencyNames;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "currencies")
public class CurrencyEntity extends BaseEntity{

    private CurrencyNames name;

    public CurrencyNames getName() {
        return name;
    }

    public void setName(CurrencyNames name) {
        this.name = name;
    }
}
