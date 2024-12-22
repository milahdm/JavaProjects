import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<VendingMachine> vendingMachines;
    private ArrayList<Coin> coins;

    public Admin(String name) {
        super(name);
        this.vendingMachines = new ArrayList<>();
        this.coins = new ArrayList<>();
    }

    public void addItem(VendingMachine machine, Item item) {
        machine.addItem(item);
    }

    public void removeItem(VendingMachine machine, Item item) {
        machine.removeItem(item);
    }

    public void cashOut(VendingMachine machine) {
        // take money from machine
    }

    public double getBalance() {
        // getting balance of machine
        return 1.0; // only to get rid of error
    }

    public void addVendingMachine(VendingMachine machine) {
        vendingMachines.add(machine);
    }

    public void removeVendingMachine(VendingMachine machine) {
        vendingMachines.remove(machine);
    }
}