package com.vmrodriguezm.mylab;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

/**
 * Static class where all Lambda functions are developed
 * 
 * @author Victor
 *
 */
public class LambdaFunctions {

    private final static Integer AGE_20 = new Integer(20);
    private final static Integer AGE_40 = new Integer(40);

    /* PREDICATES */

    /**
     * Person with Jose as part of his/her name
     * 
     * @return Predicate<Person>
     */
    public static Predicate<Person> getPersonNamedJose()
    {
        return p -> p.getName().contains("Jose");
    }

    /**
     * Person under an age
     * 
     * @param age the age to compare
     * @return Predicate<Person>
     */
    public static Predicate<Person> getPersonUnderAge(int age)
    {
        return p -> p.getAge().compareTo(age) < 0;
    }

    /**
     * Persons over age
     * 
     * @param age the age to compare
     * @return Predicate<T>
     */
    public static Predicate<Person> getPersonOverAge(int age)
    {
        return p -> p.getAge().compareTo(age) > 0;
    }

    /**
     * Compare to age ranges
     * 
     * @param underAge
     * @param overAge
     * @return Predicate<T>
     */
    public static Predicate<Person> getPersonBetweenAge(Integer underAge, Integer overAge)
    {
        return getPersonUnderAge(underAge).negate().and(getPersonOverAge(overAge).negate());
    }

    /**
     * Check if the age is the equals than the one in the parameter
     * 
     * @param age
     * @return true if the age is the same as the parameter, false if not
     */
    public static Predicate<Person> isAge(Integer age)
    {
        return p -> p.getAge().compareTo(age) == 0;
    }

    public static Predicate<Person> personWhoAreOneAgeOrAnother(Integer age1, Integer age2)
    {
        return isAge(age1).or(isAge(age2));
    }

    /**
     * Predicate to obtain one Person
     * 
     * @param DNI the PErson DNI
     * @return Predicate<Person>
     */
    public static Predicate<Person> getPersonByDNI(String DNI)
    {
        return p -> p.getDNI().equals(DNI);
    }

    /**
     * 
     * @param DNI
     * @return
     */
    public static Predicate<Person> getDNIPredicate(String DNI)
    {
        return p -> p.getDNI().equals(DNI);
    }

    public static Predicate<Person> isMale()
    {
        return p -> p.getGender().toUpperCase().equals("Male".toUpperCase());
    }

    /**
     * 
     * @param country
     * @return
     */
    public static Predicate<Person> isFromCountryPredicate(String country)
    {
        return person -> person.getAdress().getCountry().equals(country);
    }

    /**
     * 
     * @return true if the Person is Between 20 and 40 and male
     */
    public static Predicate<Person> isBetween20And40AndMale()
    {
        return getPersonBetweenAge(AGE_20, AGE_40).and(isMale());
    }
    
    public Predicate<Person>getFromCountryPredicate(String country)
    {
        return p->p.getAdress().getCountry().equalsIgnoreCase(country);
    }
    /* FUNCTIONS */

    /**
     * get email from person
     * 
     * @return Function<Person,String>
     */
    public static Function<Person, String> getEmailFromPerson()
    {
        return p -> p.getEmail();
    }

    public static Function<Person, Address> getAddressFromPerson()
    {
        return p -> p.getAdress();
    }

    public static ToIntFunction<Person> getAgePerson()
    {
        return Person::getAge;
    }

    public static Function<Address, String> getCountryFromAddress()
    {
        return address -> address.getCountry();
    }

    /* SUPPLIER */
    /**
     * Create a new person
     * 
     * @param DNI
     * @param name
     * @param age
     * @param adress
     * @param gender
     * @param registerDate
     * @param email
     * @return
     */
    public static Supplier<Person> getNewPerson(String DNI, String name, Integer age,
            Address adress,
            String gender, LocalDate registerDate, String email)
    {
        return () -> new Person(DNI, name, age, adress, gender, registerDate, email);
    }

    /**
     * Create new Address
     * 
     * @param street
     * @param number
     * @param zipCode
     * @param city
     * @param country
     * @return
     */
    public static Supplier<Address> getNewAddress(String street, int number, Long zipCode,
            String city, String country)
    {
        return () -> new Address(street, number, zipCode, city, country);
    }

    /* Comparator<T> */
    /**
     * compare ages Lambda expression
     * 
     * @return
     */
    public static Comparator<Person> getCompareByAge()
    {
        return (person1, person2) -> person1.getAge().compareTo(person2.getAge());
    }

}
