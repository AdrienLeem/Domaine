package Model;

import java.io.Serializable;

public abstract class Pion implements Serializable {
    private Integer x;
    private Integer y;

    public Pion() {
        this.x = -1;
        this.y = -1;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public boolean estPlace() {
        return this.x != -1 && this.y != -1;
    }
}
