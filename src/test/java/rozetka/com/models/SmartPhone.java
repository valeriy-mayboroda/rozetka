package rozetka.com.models;

/**
 * Created by val on 07.05.2018.
 */

public class SmartPhone {
    private String name;
    private int price;

    public SmartPhone() {}

    public SmartPhone(String name, int price) {
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
