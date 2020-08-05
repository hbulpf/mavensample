package dev.utils.demos;

public class Address2 {
    private String city;

    public Address2(String city, String createAt) {
        this.city = city;
        this.createAt = createAt;
    }

    public Address2() {

    }

    public String getCity() {
        return city;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    private String createAt;

    @Override
    public String toString() {
        return "Address2{" +
                "city='" + city + '\'' +
                ", createAt='" + createAt + '\'' +
                '}';
    }
}
