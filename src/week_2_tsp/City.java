package week_2_tsp;

public class City {

   // Each city has these following variables:
    // name = Label of city
    // x_coord = Coordinate of x
    // y_coord = Coordinate of y
    
    private String name;
    private float x_coord;
    private float y_coord;

    public City(String name, float x_coord, float y_coord) {
        this.name = name;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }
    
    // Return name of this city
    public String getName() {
        return name;
    }
    
    // Return X Coordiate of this city
    public float getX_coord() {
        return x_coord;
    }
    
    // Return Y Coordiate of this city
    public float getY_coord() {
        return y_coord;
    }
    
    
}
