package com.vmrodriguezm.mylab;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext-service-test.xml")
public class PersonSupplierTest {

    @Autowired
    PersonService personService;

    /* createNewAdress */
    @Test
    public void createNewAddress()
    {
        String street = "Gabriel Garcia Marquez";
        Long zipCode = new Long(28830);
        int number = 11;
        String city = "San Fernando de Henares";
        String country = "Spain";

        Address address = personService.createNewAddress(LambdaFunctions.getNewAddress(street,
                number, zipCode, city, country));

        Assert.assertTrue(address.getCity().equals(city));
        Assert.assertTrue(address.getZipCode().compareTo(zipCode) == 0);
        Assert.assertTrue(address.getNumber() == number);
        Assert.assertTrue(address.getCountry().equals(country));
        Assert.assertTrue(address.getStreet().equals(street));

    }

    /* createNewPerson */
    @Test
    public void createNewPerson()
    {
        String street = "Gabriel Garcia Marquez";
        Long zipCode = new Long(28830);
        int number = 11;
        String city = "San Fernando de Henares";
        String country = "Spain";

        String name = "Victor Manuel";
        String DNI = "1234E";
        int age = 29;
        String gender = "male";
        LocalDate registerDate = LocalDate.now();
        String email = "victorrodriguezmoreno@gmail.com";

        Person person = personService.createNewPerson(LambdaFunctions.getNewPerson(DNI, name, age,
                personService.createNewAddress(LambdaFunctions.getNewAddress(street, number,
                        zipCode, city, country)), gender, registerDate, email));

        Assert.assertNotNull(person.getAdress());
        Assert.assertTrue(person.getName().compareTo(name) == 0);
        Assert.assertTrue(person.getDNI().compareTo(DNI) == 0);
        Assert.assertTrue(person.getAge() == 29);
        Assert.assertTrue(person.getGender().compareTo(gender) == 0);
        Assert.assertNotNull(person.getRegisterDate());
        Assert.assertTrue(person.getEmail().compareTo(email) == 0);

    }

}
