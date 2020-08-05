package dev.utils.demos;

import java.util.Date;

public class Address1 {

    private String city;

    public Address1(String city, Date createAt) {
        this.city = city;
        this.createAt = createAt;
    }

    public Address1() {

    }

    public String getCity() {
        return city;
    }

    public Date getCreateAt() {
        return createAt;
    }

    private Date createAt;

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Address1{" +
                "city='" + city + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
