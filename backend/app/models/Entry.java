package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name="entries")
public class Entry {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="size")
    private int size;

    @Column(name="color")
    private String color;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id")
    //@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
    //@JsonBackReference
    private Order order;

    public Entry() {
    }

    public Entry(int size, String color, Order order) {
        this.size = size;
        this.color = color;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        String result = String.format("Entry[id=%d, size=%d, color='%s', order_id]%n", id, size, color, order.getId());

        return result + "%n";
    }
}
