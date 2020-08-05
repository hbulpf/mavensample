
package dev.utils.demos;

import dev.utils.BeanUtil;
import dev.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;

/**
 * BeanUtilsDemo
 */
class BeanUtilsTestDemo {
    public static void main(String[] args)
            throws ReflectiveOperationException {
        Person person = Person.builder().id("123456789").name("zhang san").age(18).dept("cloud bu").build();

//        beanCopyTest(person);

//        apacheBeanCopyFromDate2String();

//        surperSpringBeanUtilTest();

        apacheBeanCopyFromString2Date();
    }

    /**
     * 基于 springBeanUtils 改造的 Bean 工具
     *
     * @throws ReflectiveOperationException
     */
    private static void surperSpringBeanUtilTest() throws ReflectiveOperationException {
        //springBeanUtils
        Address1 addr3 = new Address1("beijing", new Date(1596470797498L));
        Address2 addr4 = new Address2();
        BeanUtil.copyBeanProperties(addr3, addr4);//addr3 是 target , addr2 是source
        System.out.println(addr3);
        System.out.println(addr4);
    }

    private static void apacheBeanCopyFromString2Date() throws InvocationTargetException, IllegalAccessException {
        Person p1 = new Person("12", "WangZhi", 19, "TEG", "20010804130907");
        PersonCopy p2 = new PersonCopy();
        CustomerDateConverter dateConverter = new CustomerDateConverter();
        ConvertUtils.register(dateConverter, java.util.Date.class);
        org.apache.commons.beanutils.BeanUtils.copyProperties(p2, p1);
        System.out.println(p1);
        System.out.println(p2);
    }

    /**
     * 从 Date 字段的Bean复制到 String 字段的Bean
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static void apacheBeanCopyFromDate2String() throws InvocationTargetException, IllegalAccessException {
        // apacheBeanUtils
        Address1 addr1 = new Address1("wuhan", new Date(1596470787498L));
        Address2 addr2 = new Address2();
        ConvertUtils.register(new SqlDateConverter(), String.class);
        //addr1 是 source , addr2 是 target
        org.apache.commons.beanutils.BeanUtils.copyProperties(addr2, addr1);
        System.out.println(addr1);
        System.out.println(addr2);

        StringUtils.printHr();

        //springBeanUtils
        Address1 addr3 = new Address1("beijing", new Date(1596470797498L));
        Address2 addr4 = new Address2();
        //addr3 是 target , addr2 是source
        org.springframework.beans.BeanUtils.copyProperties(addr3, addr4);
        System.out.println(addr3);
        System.out.println(addr4);
    }

    private static void beanCopyTest(Person person) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        StringUtils.printHr();
        cglibBeanCopier(person, 100);
        cglibBeanCopier(person, 1000);
        cglibBeanCopier(person, 10000);
        cglibBeanCopier(person, 100000);
        cglibBeanCopier(person, 1000000);

        StringUtils.printHr();
        apachePropertyUtils(person, 100);
        apachePropertyUtils(person, 1000);
        apachePropertyUtils(person, 10000);
        apachePropertyUtils(person, 100000);
        apachePropertyUtils(person, 1000000);

        StringUtils.printHr();
        apacheBeanUtils(person, 100);
        apacheBeanUtils(person, 1000);
        apacheBeanUtils(person, 10000);
        apacheBeanUtils(person, 100000);
        apacheBeanUtils(person, 1000000);

        StringUtils.printHr();
        springBeanUtils(person, 100);
        springBeanUtils(person, 1000);
        springBeanUtils(person, 10000);
        springBeanUtils(person, 100000);
        springBeanUtils(person, 1000000);
    }

    public static void cglibBeanCopier(Person person, int times) {
        StopTimer stopwatch = new StopTimer();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonCopy personCopy = new PersonCopy();
            BeanCopier copier = BeanCopier.create(Person.class, PersonCopy.class, false);
            copier.copy(person, personCopy, null);
        }
        stopwatch.stop();
        System.out.println("cglibBeanCopier cost :" + stopwatch.getTotalTimeMillis());
    }

    public static void apachePropertyUtils(Person person, int times)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        StopTimer stopwatch = new StopTimer();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonCopy personCopy = new PersonCopy();
            PropertyUtils.copyProperties(personCopy, person);
        }
        stopwatch.stop();
        System.out.println("apachePropertyUtils cost :" + stopwatch.getTotalTimeMillis());
    }

    public static void apacheBeanUtils(Person person, int times)
            throws InvocationTargetException, IllegalAccessException {
        StopTimer stopwatch = new StopTimer();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonCopy personCopy = new PersonCopy();
            BeanUtils.copyProperties(personCopy, person);
        }
        stopwatch.stop();
        System.out.println("apacheBeanUtils cost :" + stopwatch.getTotalTimeMillis());
    }

    public static void springBeanUtils(Person person, int times) {
        StopTimer stopwatch = new StopTimer();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonCopy personCopy = new PersonCopy();
            org.springframework.beans.BeanUtils.copyProperties(person, personCopy);
        }
        stopwatch.stop();
        System.out.println("springBeanUtils cost :" + stopwatch.getTotalTimeMillis());
    }
}
