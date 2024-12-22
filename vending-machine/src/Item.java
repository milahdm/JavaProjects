// Item class that has 4 instance variables
// getters and setters
// as well as a separate addQuantity method
public class Item
{
    private int id;
    private double price;
    private int quantity;
    private String name;

    public Item(int id, double price, int quantity, String name)
    {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
    }

    public void addQuantity(int quantity)
    {
        this.quantity += quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
