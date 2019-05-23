package models;

import javax.persistence.*;

@Entity
@Table(name="colors")
public class Color {

    @Id
    @Column(name = "value")
    private String value;

    @Column(name = "text")
    private String text;

    public Color() {
    }

    public Color(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

