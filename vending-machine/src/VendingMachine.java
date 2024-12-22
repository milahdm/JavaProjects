import java.util.ArrayList;

// vending machine class with 2 instance vars
// getters and setters for bot
public class VendingMachine {
    private ArrayList<Item> items;
    private double balance;

    public VendingMachine(ArrayList<Item> items) {
        this.items = items;
        this.balance = 0.0;
    }

    public ArrayList<Item> getItems() {
        return items;
    }


    public double getBalance() {
        return balance;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void insertCoin(Coin coin) {
        balance += coin.getValue();
    }

    public void removeCoin(Coin coin) {
        balance -= coin.getValue();
    }

    public void buyItem(Item item, ArrayList<Coin> coinsInserted) {
        //
    }

}

