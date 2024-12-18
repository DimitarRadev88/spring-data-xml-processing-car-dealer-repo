package bg.softuni.springDataXmlProcessingPartTwo.models;

import java.math.BigDecimal;

public enum Discount {
    PERCENT0("0.0"),
    PERCENT5("0.5"),
    PERCENT10("0.1"),
    PERCENT15("0.15"),
    PERCENT20("0.2"),
    PERCENT30("0.3"),
    PERCENT40("0.4"),
    PERCENT50("0.5");

    private final BigDecimal percent;

    public BigDecimal getPercent() {
        return percent;
    }

    Discount(String percent) {
        this.percent = new BigDecimal(percent);
    }

    public BigDecimal apply(BigDecimal price) {
        return price.subtract(price.multiply(percent));
    }

}
