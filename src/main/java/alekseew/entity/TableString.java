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

    public Resale findMarketsWithMaxPercent() {
        return new Resale(getName(),(findMax()/findMin()-1)*100,findMarketWithPrice(findMin()),findMarketWithPrice(findMax()));
    }

    private double findMin() {
        double min = Double.MAX_VALUE;
        if (min>this.buffPrice && this.buffPrice!=0){
            min=this.buffPrice;
        }
        if (min>this.steamPrice && this.steamPrice!=0){
            min=this.steamPrice;
        }
        if (min>this.csMoneyPrice && this.csMoneyPrice!=0){
            min=this.csMoneyPrice;
        }
        if (min>this.csMarketPrice && this.csMarketPrice!=0){
            min=this.csMarketPrice;
        }
        return min;
    }
    private double findMax() {
        double max = Double.MIN_VALUE;
        max = Math.max(max, this.buffPrice);
        max = Math.max(max, this.steamPrice);
        max = Math.max(max, this.csMoneyPrice);
        max = Math.max(max, this.csMarketPrice);
        return max;
    }
    private String findMarketWithPrice(double price){
        if (this.buffPrice==price){
            return "Buff";
        }
        if (this.steamPrice==price){
            return "Steam";
        }
        if (this.csMoneyPrice==price){
            return "CsMoney";
        }
        if (this.csMarketPrice==price){
            return "CsMarket";
        }
        return null;
    }
}
