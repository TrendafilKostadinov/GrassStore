package bg.softuni.grassstore.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRateEntity extends BaseEntity {

    @NotNull
    private BigDecimal USDtoBGNRate;

    @NotNull
    private BigDecimal GBPtoBGNRate;

    public BigDecimal getUSDtoBGNRate() {
        return USDtoBGNRate;
    }

    public ExchangeRateEntity setUSDtoBGNRate(BigDecimal USDtoBGNRate) {
        this.USDtoBGNRate = USDtoBGNRate;
        return this;
    }

    public BigDecimal getGBPtoBGNRate() {
        return GBPtoBGNRate;
    }

    public ExchangeRateEntity setGBPtoBGNRate(BigDecimal GBPtoBGNRate) {
        this.GBPtoBGNRate = GBPtoBGNRate;
        return this;
    }
}
