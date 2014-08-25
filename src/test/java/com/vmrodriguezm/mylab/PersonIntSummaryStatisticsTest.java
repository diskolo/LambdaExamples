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
public class PersonIntSummaryStatisticsTest {

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
                "Jose Antonio", 40, personService.createNewAddress(LambdaFunctions.getNewAddress(
                        "E", 44, new Long(23459), "Madrid", "Spain")), "male", LocalDate.now(),
                "JoseAntonio@gmail.com")));
    }

    /* getCount */
    @Test
    public void getCountEmptyCollection()
    {
        List<Person> emptyCollection = new ArrayList<Person>();
        Assert.assertEquals(0,personService.getCount(emptyCollection,LambdaFunctions.getAgePerson()));
    }

    @Test
    public void getCountOk()
    {
        Assert.assertEquals(5,personService.getCount(persons,LambdaFunctions.getAgePerson()));

    }

    /* getMaxAge */
    @Test
    public void getMaxAgeEmptyCollection()
    {
        List<Person> emptyCollection = new ArrayList<Person>();
        Assert.assertTrue(personService.getMaxAge(emptyCollection, LambdaFunctions.getAgePerson()) == Integer.MIN_VALUE);
    }

    @Test
    public void getMaxAgeOK()
    {
        Assert.assertTrue(personService.getMaxAge(persons, LambdaFunctions.getAgePerson()) == 56);
    }

    /* getMinAge */
    @Test
    public void getMinAgeEmptyCollection()
    {
        List<Person> emptyCollection = new ArrayList<Person>();
        Assert.assertTrue(personService.getMinAge(emptyCollection, LambdaFunctions.getAgePerson()) == Integer.MAX_VALUE);
    }

    @Test
    public void getMinAgeOK()
    {
        Assert.assertTrue(personService.getMinAge(persons, LambdaFunctions.getAgePerson()) == 15);
    }

    /* getSumAgeOfPersonJose */
    @Test
    public void getsumAgeOfPersonJoseNoJose()
    {
        persons.forEach(p -> p.setName("NoName"));
        Assert.assertTrue(personService.getSumAgeOfPersonJose(persons,
                LambdaFunctions.getPersonNamedJose(), LambdaFunctions.getAgePerson()) == 0);
    }

    @Test
    public void getSumAgeOfPersonJoseOK()
    {
        Assert.assertEquals(55,personService.getSumAgeOfPersonJose(persons,
                LambdaFunctions.getPersonNamedJose(), LambdaFunctions.getAgePerson()));
    }

    /* getAverageOfPersonJose */

    @Test
    public void getAverageOfPersonJoseNoJose()
    {
        List<Person> emptyCollection = new ArrayList<Person>();
        Assert.assertTrue(personService.getAverageOfPersonJose(emptyCollection,
                LambdaFunctions.getPersonNamedJose(), LambdaFunctions.getAgePerson()) == 0);
    }

    @Test
    public void getAverageOfPersonJoseOK()
    {
        Assert.assertTrue(personService.getAverageOfPersonJose(persons,LambdaFunctions.getPersonNamedJose(),LambdaFunctions.getAgePerson())==27.5);
    }

}
