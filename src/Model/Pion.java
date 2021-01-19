package Model;

public abstract class Pion {
    private Integer x;
    private Integer y;

    public Pion() {
        this.x = null;
        this.y = null;
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
        return this.x != null && this.y != null;
    }
}
