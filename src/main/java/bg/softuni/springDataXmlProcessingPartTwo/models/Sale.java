package bg.softuni.springDataXmlProcessingPartTwo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    private Discount discount;
    private Car car;
    private Customer customer;

    @Enumerated
    @Column(name = "discount")
    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @ManyToOne
    @JoinColumn(name = "car_id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
