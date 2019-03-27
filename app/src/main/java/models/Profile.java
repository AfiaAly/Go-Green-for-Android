package models;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class Profile {

    /*@Getter @Setter*/ private String username;
    /*@Getter @Setter*/ private String country;
    /*@Getter @Setter*/ private String distance;
    /*@Getter @Setter*/ private int income;
    /*@Getter @Setter*/ private int carefficiency;
    /*@Getter @Setter*/ private int electricitybill;

    public Profile(String username, String country, String distance, int income, int carefficiency, int electricitybill) {
        this.username = username;
        this.country = country;
        this.distance = distance;
        this.income = income;
        this.carefficiency = carefficiency;
        this.electricitybill = electricitybill;
    }

    public Profile() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistance() {
        return distance;
    }


    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getCarefficiency() {
        return carefficiency;
    }

    public void setCarefficiency(int carefficiency) {
        this.carefficiency = carefficiency;
    }

    public int getElectricitybill() {
        return electricitybill;
    }

    public void setElectricitybill(int electricitybill) {
        this.electricitybill = electricitybill;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "username='" + username + '\'' +
                ", country='" + country + '\'' +
                ", distance='" + distance + '\'' +
                ", income=" + income +
                ", carefficiency=" + carefficiency +
                ", electricitybill=" + electricitybill +
                '}';
    }
}
