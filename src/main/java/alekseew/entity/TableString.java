package alekseew.entity;

import org.springframework.stereotype.Component;

@Component
public class TableString {
    private String name;
    private double buffPrice;
    private double steamPrice;
    private double csMarketPrice;
    private double csMoneyPrice;
    private String steamHref;
    private String imageHref;

    public TableString(String name, double buffPrice, double steamPrice, double csMarketPrice, double csMoneyPrice, String imageHref) {
        this.name = name;
        this.buffPrice = buffPrice;
        this.steamPrice = steamPrice;
        this.csMarketPrice = csMarketPrice;
        this.csMoneyPrice = csMoneyPrice;
        this.imageHref = imageHref;
    }

    public TableString(String name, double buffPrice, double steamPrice, double csMarketPrice, double csMoneyPrice, String steamHref, String imageHref) {
        this.name = name;
        this.buffPrice = buffPrice;
        this.steamPrice = steamPrice;
        this.csMarketPrice = csMarketPrice;
        this.csMoneyPrice = csMoneyPrice;
        this.steamHref = steamHref;
        this.imageHref = imageHref;
    }

    public TableString() {
    }

    public String getName() {
        return name;
    }

    public double getBuffPrice() {
        return buffPrice;
    }

    public double getSteamPrice() {
        return steamPrice;
    }

    public double getCsMarketPrice() {
        return csMarketPrice;
    }

    public double getCsMoneyPrice() {
        return csMoneyPrice;
    }

    public String getSteamHref() {
        return steamHref;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuffPrice(double buffPrice) {
        this.buffPrice = buffPrice;
    }

    public void setSteamPrice(double steamPrice) {
        this.steamPrice = steamPrice;
    }

    public void setCsMarketPrice(double csMarketPrice) {
        this.csMarketPrice = csMarketPrice;
    }

    public void setCsMoneyPrice(double csMoneyPrice) {
        this.csMoneyPrice = csMoneyPrice;
    }

    public void setSteamHref(String steamHref) {
        this.steamHref = steamHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }


    @Override
    public String toString() {
        return "TableString{" +
                "name='" + name + '\'' +
                ", buffPrice=" + buffPrice +
                ", steamPrice=" + steamPrice +
                ", csMarketPrice=" + csMarketPrice +
                ", csMoneyPrice=" + csMoneyPrice +
                ", steamHref='" + steamHref + '\'' +
                ", imageHref='" + imageHref + '\'' +
                '}';
    }
}
