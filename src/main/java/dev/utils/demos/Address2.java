package dev.utils.demos;

public class Address2 {
    private String city;
    private String createAt;

    public Address2(String city, String createAt) {
        this.city = city;
        this.createAt = createAt;
    }

    public Address2() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Address2{" +
                "city='" + city + '\'' +
                ", createAt='" + createAt + '\'' +
                '}';
    }
}
