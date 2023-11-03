public class Vertex {
    private int x;
    private int y;
    private int radius;

    public Vertex(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public boolean contains(int px, int py) {
        // Calcola la distanza tra il punto (px, py) e il centro dell'ovale
        double distance = Math.sqrt((px - x) * (px - x) + (py - y) * (py - y));
        // Se la distanza è minore del raggio dell'ovale,
        // allora il punto si trova all'interno dell'ovale
        return distance < radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
    
}