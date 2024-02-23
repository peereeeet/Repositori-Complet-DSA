public class Triangle extends Figura {
    private double b;
    private double h;
    public Triangle(double b, double h) {
        this.b=b;
        this.h=h;
    }
    public double area() {
        return (this.b*this.h)/2;
    }
    public String toString() {
        return "Triangle amb base: "+this.b+" i altura: "+this.h+", amb area: "+this.area();
    }
}
