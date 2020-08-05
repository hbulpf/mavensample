package dev.utils.demos;

/**
 * Person
 */
public class Person {
    private String id;

    private String name;

    private int age;

    private String dept;

    private String birthday;

    public Person(String id, String name, int age, String dept) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dept = dept;
    }

    public Person() {
    }

    public Person(String id, String name, int age, String dept, String birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dept = dept;
        this.birthday = birthday;
    }

    public static Person builder() {
        return new Person();
    }

    public String getId() {
        return id;
    }

    public Person setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public String getDept() {
        return dept;
    }

    public Person setDept(String dept) {
        this.dept = dept;
        return this;
    }

    public Person createPerson() {
        return new Person(id, name, age, dept);
    }

    public Person id(String i) {
        this.id = i;
        return this;
    }

    public Person name(String n) {
        this.name = n;
        return this;
    }

    public Person age(int age) {
        this.age = age;
        return this;
    }

    public Person dept(String d) {
        this.dept = d;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Person build() {
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dept='" + dept + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
