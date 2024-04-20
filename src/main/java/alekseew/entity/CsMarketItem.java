package alekseew.entity;

public class CsMarketItem implements ItemInterface {
    private String name;
    private final double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "CsMarketItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public CsMarketItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
