package bg.softuni.grassstore.model.dto;

import java.math.BigDecimal;

public class TraderSalesDTO {

    private String trader;

    private BigDecimal sales;

    private Integer numberOfOrders;

    public String getTrader() {
        return trader;
    }

    public TraderSalesDTO setTrader(String trader) {
        this.trader = trader;
        return this;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public TraderSalesDTO setSales(BigDecimal sales) {
        this.sales = sales;
        return this;
    }

    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    public TraderSalesDTO setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
        return this;
    }
}
