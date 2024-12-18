package bg.softuni.springDataXmlProcessingPartTwo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    private String make;
    private String model;
    private Long travelledDistance;
    private List<Sale> sales;
    private List<Part> parts;

    public Car() {
    }

    @Column(name = "make")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "travelled_distance")
    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    @OneToMany(mappedBy = "car")
    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "parts_cars", joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
