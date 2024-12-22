import java.util.ArrayList;

// User class... methods are not final
public class User {
    private final String name;
    private ArrayList<Coin> coins;

    public User(String name) {
        this.name = name;
        coins = new ArrayList<>();
    }

    public void addCoin(Coin coin) {
        coins.add(coin);
    }

    public void removeCoin(Coin coin) {
        coins.remove(coin);
    }

    public void returnCoins(){
        // clears machine}
    }

    // most likely implemented in a different way
    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public String getName() {
        return name;
    }

    public void buyItem(VendingMachine machine, Item item)
    {
        // remove items from machine and remove money from users coin list if they exist
    }

    public double returnCoins(VendingMachine machine)
    {
        //
    }

    public void enterCoin(Coin coin)
    {
        //
    }

    public void selectProduct(int id)
    {}
}