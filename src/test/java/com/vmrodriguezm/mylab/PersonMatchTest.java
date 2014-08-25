package com.vmrodriguezm.mylab;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext-service-test.xml")
public class PersonMatchTest {

    @Autowired
    PersonService personService;

    List<Person> persons;

    @Before
    public void createPersons()
    {
        persons = new ArrayList<Person>();

        persons.add(personService.createNewPerson(LambdaFunctions.getNewPerson("1234E",
                "Jose Manuel", 15, personService.createNewAddress(LambdaFunctions.getNewAddress(
                        "A", 12, new Long(23456), "Mostoles", "Spain")), "male", LocalDate.now(),
                "JoseManuel@gmail.com")));
        persons.add(personService.createNewPerson(LambdaFunctions.getNewPerson("1235E", "Manuela",
                40, personService.createNewAddress(LambdaFunctions.getNewAddress("B", 12, new Long(
                        23454), "Amsterdam", "Holland")), "female", LocalDate.now(),
                "manuela@gmail.com")));
        persons.add(personService.createNewPerson(LambdaFunctions.getNewPerson("1236E", "Antonio",
                35, personService.createNewAddress(LambdaFunctions.getNewAddress("C", 25, new Long(
                        23458), "München", "Germany")), "male", LocalDate.now(),
                "antonio@gmail.com")));
        persons.add(personService.createNewPerson(LambdaFunctions.getNewPerson("1237E", "Cristina",
                56, personService.createNewAddress(LambdaFunctions.getNewAddress("D", 37, new Long(
                        23450), "Mostoles", "Spain")), "female", LocalDate.now(),
                "cristina@gmail.com")));
        persons.add(personService.createNewPerson(LambdaFunctions.getNewPerson("1238E",
                "Jose Antonio", 37, personService.createNewAddress(LambdaFunctions.getNewAddress(
                        "E", 44, new Long(23459), "Madrid", "Spain")), "male", LocalDate.now(),
                "JoseAntonio@gmail.com")));
    }

    /* isAllPersonUnder10 */
    @Test
    public void isAllPersonUnder10False()
    {
        Assert.assertFalse(personService.isAllPersonUnder10(persons));
    }

    @Test
    public void isAllPersonUnder10True()
    {
        persons.forEach(p -> p.setAge(new Integer(9)));
        Assert.assertTrue(personService.isAllPersonUnder10(persons));
    }

    /* isAllPersonOver50 */
    @Test
    public void isAllPersonOver50False()
    {
        Assert.assertFalse(personService.isAllPersonUnder10(persons));
    }

    @Test
    public void isAllPersonOver50True()
    {
        persons.forEach(p -> p.setAge(new Integer(51)));
        Assert.assertTrue(personService.isAllPersonOver50(persons));
    }

    /* isNonePersonFromFrance */
    @Test
    public void isNonePersonFromFranceTrue()
    {
        Assert.assertTrue(personService.isNonePersonFromCountry(persons,LambdaFunctions.isFromCountryPredicate("France")));
    }

    @Test
    public void isNonePersonFromFranceFalse()
    {
        persons.forEach(p -> p.getAdress().setCountry("France"));
        Assert.assertFalse(personService.isNonePersonFromCountry(persons,LambdaFunctions.isFromCountryPredicate("France")));
    }

    /* isAnyPersonFromSpain */
    @Test
    public void isAnyPersonFromSpainTrue()
    {
        Assert.assertTrue(personService.isAnyPersonFromCountry(persons,LambdaFunctions.isFromCountryPredicate("Spain")));
    }

    @Test
    public void isAnyPersonFromSpainFalse()
    {
        persons.forEach(p -> p.getAdress().setCountry("Holland"));
        Assert.assertFalse(personService.isAnyPersonFromCountry(persons,LambdaFunctions.isFromCountryPredicate("Spain")));

    }

}
