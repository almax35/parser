package alekseew.entity;

public class CsMoneyItem implements ItemInterface {
    private String name;
    private final double price;

    public CsMoneyItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "CsMoneyItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
}
