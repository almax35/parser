package alekseew.entity;

public class CsMarketItem implements ItemInterface{
    private String name;
    private double price;
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CsMarketItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }

    public CsMarketItem(String name, double price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public CsMarketItem() {
    }
}
