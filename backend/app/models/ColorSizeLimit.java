package models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "colorsizelimit")
public class ColorSizeLimit {

    @EmbeddedId
    private ColorSizeLimitIdentity colorSizeLimitId;

    @Column(name="`limit`", nullable = false)
    private int limit;

    public ColorSizeLimit() {
    }

    public ColorSizeLimit(ColorSizeLimitIdentity colorSizeLimitId, int limit) {
        this.colorSizeLimitId = colorSizeLimitId;
        this.limit = limit;
    }

    public ColorSizeLimitIdentity getColorSizeLimitId() {
        return colorSizeLimitId;
    }

    public void setColorSizeLimitId(ColorSizeLimitIdentity colorSizeLimitId) {
        this.colorSizeLimitId = colorSizeLimitId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void decrementLimit() {
        if (limit <= 0) throw new IllegalStateException("Osiągnięto limit dla tego połączenia.");
        this.limit = this.limit -1;
    }
}
