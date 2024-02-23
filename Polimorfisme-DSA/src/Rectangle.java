public class Rectangle extends Figura {
    double c1;
    double c2;
    public Rectangle (double c1, double c2){
        this.c1 = c1;
        this.c2 = c2;
    }
    public double area (){
        return this.c1*this.c2;
    }
    public double getc1(){
        return this.c1;
    }
    public String toString() {
        return "Rectangle amb costats: " + this.c1 + " i " + this.c2 + ", té una àrea de " + this.area();
    }
}