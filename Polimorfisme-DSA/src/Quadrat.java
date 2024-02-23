public class Quadrat extends Rectangle{
    public Quadrat (double c1, double c2) {
        super(c1, c2);
    }
    public String toString(){
        return "Quadrat amb costat: " +this.getc1() + ", té una àrea de "+super.area();
    }
}
