
package dev.utils.demos;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.InvocationTargetException;

import dev.utils.StringUtils;

/**
 * BeanUtilsDemo
 */
class BeanUtilsTestDemo {
    public static void main(String[] args)
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Person person = Person.builder().id("123456789").name("zhang san").age(18).dept("cloud bu").build();

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
