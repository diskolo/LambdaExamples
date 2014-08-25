package com.vmrodriguezm.mylab;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext-service-test.xml")
public class PersonFunctionTest {

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

    /* getEmailFromPersonsJose */
    @Test
    public void getEmailFromPersonsJoseEmptyCollection()
    {
        List<Person> emptyPersons = new ArrayList<Person>();
        Assert.assertEquals(
                0,
                personService.getEmailFromPersonsJose(emptyPersons,
                        LambdaFunctions.getPersonNamedJose(), LambdaFunctions.getEmailFromPerson())
                        .size());
    }

    @Test
    public void getEmailFromPersonsJoseNoJose()
    {
        persons.forEach(p -> p.setName("NO"));
        Assert.assertEquals(0, personService.getEmailFromPersonsJose(persons,
                LambdaFunctions.getPersonNamedJose(), LambdaFunctions.getEmailFromPerson()).size());

    }

    @Test
    public void getEmailFromPersonJoseOK()
    {
        List<String> emails = (List<String>) personService.getEmailFromPersonsJose(persons,
                LambdaFunctions.getPersonNamedJose(), LambdaFunctions.getEmailFromPerson());
        Assert.assertEquals(2, emails.size());
        Assert.assertTrue(emails.contains("JoseManuel@gmail.com"));
        Assert.assertTrue(emails.contains("JoseAntonio@gmail.com"));
    }

    /* getStringAddressFromPersonUnder20 */
    @Test
    public void getStringAddressFromPersonUnder20EmtpyCollection()
    {
        List<Person> emptyCollection = new ArrayList<Person>();
        Assert.assertEquals(
                0,
                personService.getStringAddressFromPersonUnder20(emptyCollection,
                        LambdaFunctions.getPersonUnderAge(20),
                        LambdaFunctions.getAddressFromPerson()).size());
    }

    @Test
    public void getStringAddressFromPersonUnder20NoOneUnder20()
    {
        persons.forEach(p -> p.setAge(50));
        Assert.assertEquals(
                0,
                personService.getStringAddressFromPersonUnder20(persons,
                        LambdaFunctions.getPersonUnderAge(20),
                        LambdaFunctions.getAddressFromPerson()).size());

    }

    @Test
    public void getStringAddressFromPersonUnder20OK()
    {
        List<String> addresses = (List<String>) personService.getStringAddressFromPersonUnder20(
                persons,
                LambdaFunctions.getPersonUnderAge(20), LambdaFunctions.getAddressFromPerson()
                );
        Assert.assertEquals(1, addresses.size());

        Assert.assertEquals("Street: A Number: 12", addresses.get(0).toString());
    }

}
