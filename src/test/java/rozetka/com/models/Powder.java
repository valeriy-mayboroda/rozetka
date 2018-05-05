package rozetka.com.models;

/**
 * Created by val on 05.05.2018.
 */

public class Powder {
    private String name;
    private int price;

    public Powder(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}

    @Override
    public String toString() {
        return name + " : " + price;
    }
}
