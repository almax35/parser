package entity;

public class BuffItem {
    private String name;
    private double buffPrice;
    private double steamPrice;
    private int buffQuantity;
    private String steamHref;
    private String imageHref;

    public String getName() {
        return name;
    }

    public double getBuffPrice() {
        return buffPrice;
    }

    public double getSteamPrice() {
        return steamPrice;
    }

    public int getBuffQuantity() {
        return buffQuantity;
    }

    public String getSteamHref() {
        return steamHref;
    }

    public String getImageHref() {
        return imageHref;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", buffPrice=" + buffPrice +
                ", steamPrice=" + steamPrice +
                ", buffQuantity=" + buffQuantity +
                ", steamHref='" + steamHref + '\'' +
                ", imageHref='" + imageHref + '\'' +
                '}';
    }

    public BuffItem(String name, double buffPrice, double steamPrice, int buffQuantity, String steamHref, String imageHref) {
        this.name = name;
        this.buffPrice = buffPrice;
        this.steamPrice = steamPrice;
        this.buffQuantity = buffQuantity;
        this.steamHref = steamHref;
        this.imageHref = imageHref;
    }
}
