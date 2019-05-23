package models;

//import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="date_added")
    private Date dateAdded;

    @OneToMany(mappedBy="order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JsonManagedReference
    private List<Entry> entries;

    public Order() {
    }

    public Order(String name, int age, List<Entry> entries) {
        this.name = name;
        this.age = age;
        this.entries = entries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<Entry> getEntries() { return entries; }

    public void setEntries(List<Entry> entries){ this.entries = entries; }

    @Override
    public String toString() {
        String result = String.format("Order[id=%d, name='%s', age=%d]%n", id, name, age);

        if (entries != null) {
            for (Entry entry : entries){
                result += String.format("Entry[id=%d, color='%s', size=%d]%n", entry.getId(), entry.getColor(), entry.getSize());
            }
        }

        return result + "%n";
    }
}
