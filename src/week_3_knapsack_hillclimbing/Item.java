
package week_3_knapsack_hillclimbing;

// Each item has these following variables:
    // name = Label of item
    // value = Value of item
    // weight = Weight of item
    // isSelected = Item is selected? True or False
public class Item{
    
    private String name;
    private int value; // v_i value in knapsack file
    private int weight; // w_i value in knapsack file
    private int isSelected; // x_i value in knapsack file

    public Item(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }
    
    // Return name of the item
    public String getName() {
        return name;
    }
    
    // Return value of the item
    public int getValue() {
        return value;
    }
    
    // Return weight of the item
    public int getWeight() {
        return weight;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public int getIsSelected() {
        return isSelected;
    }
}
