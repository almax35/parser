package alekseew.entity;

public class BuffItem implements ItemInterface {
    private final String name;
    private final double buffPrice;
    private final double steamPrice;
    private final String steamHref;
    private final String imageHref;

    public String getName() {
        return name;
    }

    public double getBuffPrice() {
        return buffPrice;
    }

    public double getSteamPrice() {
        return steamPrice;
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
                ", steamHref='" + steamHref + '\'' +
                ", imageHref='" + imageHref + '\'' +
                '}';
    }

    public BuffItem(String name, double buffPrice, double steamPrice, String steamHref, String imageHref) {
        this.name = name;
        this.buffPrice = buffPrice;
        this.steamPrice = steamPrice;
        this.steamHref = steamHref;
        this.imageHref = imageHref;
    }
}
