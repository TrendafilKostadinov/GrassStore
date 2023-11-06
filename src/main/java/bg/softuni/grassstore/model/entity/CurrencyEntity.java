package bg.softuni.grassstore.model.entity;

import bg.softuni.grassstore.model.enums.CurrencyNames;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "currencies")
public class CurrencyEntity extends BaseEntity{

    @NotNull
    @Enumerated(EnumType.STRING)
    private CurrencyNames name;

    public CurrencyNames getName() {
        return name;
    }

    public void setName(CurrencyNames name) {
        this.name = name;
    }
}
