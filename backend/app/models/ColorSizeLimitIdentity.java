package models;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ColorSizeLimitIdentity implements Serializable {

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private int size;

    public ColorSizeLimitIdentity() {
    }

    public ColorSizeLimitIdentity(String color, int size) {
        this.color = color;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorSizeLimitIdentity that = (ColorSizeLimitIdentity) o;

        if (!color.equals(that.color)) return false;
        return size == that.size;
    }

    @Override
    public int hashCode() {
        int result = color.hashCode();
        result = 31 * result + size;
        return result;
    }
}
