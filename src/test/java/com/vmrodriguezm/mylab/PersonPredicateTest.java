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
public class PersonPredicateTest {

    @Autowired
    PersonService personService;

    List<Person> persons;

    private final static Integer AGE_20 = new Integer(20);
    private final static Integer AGE_40 = new Integer(40);

    @Before
    public void createPersons()
    {
        persons = new ArrayList<Person>();

        persons.add(personService.createNewPerson(LambdaFunctions.getNewPerson("1234E",
                "Jose Manuel", 15, personService.createNewAddress(LambdaFunctions.getNewAddress(
                        "A", 12, new Long(23456), "Mostoles", "Spain")), "male", LocalDate.now(),
                "JoseManuel@gmail.com")));
        persons.add(personService.createNewPerson(LambdaFunctions.getNewPerson("1235E", "Rodrigo",
                40, personService.createNewAddress(LambdaFunctions.getNewAddress("B", 12, new Long(
                        23454), "Amsterdam", "Holland")), "male", LocalDate.now(),
                "rodrigo@gmail.com")));
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

    /* numberOfPersonNamedJose */
    @Test
    public void numberOfPersonNamedJoseEmtpyCollection()
    {
        List<Person> emptyCollection = new ArrayList<Person>();
        Assert.assertEquals(
                0,
                personService.numberPersonNamedJose(emptyCollection,
                        LambdaFunctions.getPersonNamedJose()));
    }

    @Test
    public void numberOfPersonNamedJoseNoNamedJose()
    {
        // First of all get the persons named Jose and change the name
        List<Person> personsNoJose = persons.stream().filter(p -> !p.getName().contains("Jose"))
                .collect(Collectors.toList());
        Assert.assertEquals(
                0,
                personService.numberPersonNamedJose(personsNoJose,
                        LambdaFunctions.getPersonNamedJose()));
    }

    @Test
    public void numberOfPersonNamedJoseOK()
    {
        Assert.assertEquals(2,
                personService.numberPersonNamedJose(persons, LambdaFunctions.getPersonNamedJose()));
    }

    /* personUnder20 */
    @Test
    public void personUnder20EmptyCollection()
    {
        List<Person> emptyList = new ArrayList<Person>();

        Assert.assertEquals(0,
                personService.personUnder20(emptyList, LambdaFunctions.getPersonUnderAge(AGE_20))
                        .size());
    }

    @Test
    public void personUnder20NoOneUnder20()
    {

        // Change ages
        persons.forEach(p -> p.setAge(21));
        Assert.assertEquals(0,
                personService.personUnder20(persons, LambdaFunctions.getPersonUnderAge(AGE_20))
                        .size());
    }

    @Test
    public void personUnder20Ok()
    {
        Assert.assertEquals(1,
                personService.personUnder20(persons, LambdaFunctions.getPersonUnderAge(AGE_20))
                        .size());

    }

    /* personBetween20And40Age */
    @Test
    public void personBetween20And40AgeNoPersonBetween20And40()
    {
        persons.forEach(p -> p.setAge(50));
        Assert.assertEquals(
                0,
                personService.personBetween20And40Age(persons,
                        LambdaFunctions.getPersonBetweenAge(AGE_20, AGE_40)).size());
    }

    @Test
    public void personBetween20And40AgeOK()
    {
        Assert.assertEquals(
                3,
                personService.personBetween20And40Age(persons,
                        LambdaFunctions.getPersonBetweenAge(AGE_20, AGE_40)).size());
    }

    /* personWhoAre20orAre40 */

    @Test
    public void personWhoAre20orAre40NoOneIs20or40()
    {
        persons.forEach(p -> p.setAge(new Integer(50)));
        Assert.assertEquals(
                0,
                personService.personWhoAre20orAre40(persons,
                        LambdaFunctions.personWhoAreOneAgeOrAnother(AGE_20, AGE_40)).size());
    }

    @Test
    public void personWhoAre20orAre40Ok()
    {
        Assert.assertEquals(
                1,
                personService.personWhoAre20orAre40(persons,
                        LambdaFunctions.personWhoAreOneAgeOrAnother(AGE_20, AGE_40)).size());
    }

    /* personBetween20And40AndMale */

    @Test
    public void personBetween20And40AndMaleNoOneBetween20or40()
    {
        persons.forEach(p -> p.setAge(new Integer(50)));
        Assert.assertEquals(
                0,
                personService.personBetween20And40AndMale(persons,
                        LambdaFunctions.isBetween20And40AndMale()).size());
    }

    @Test
    public void personBetween20And40AndMaleNoMale()
    {
        persons.forEach(p -> p.setGender("Female"));
        Assert.assertEquals(
                0,
                personService.personBetween20And40AndMale(persons,
                        LambdaFunctions.isBetween20And40AndMale()).size());
    }

    @Test
    public void personBetween20And40AndMaleOk()
    {
        Assert.assertEquals(
                3,
                personService.personBetween20And40AndMale(persons,
                        LambdaFunctions.isBetween20And40AndMale()).size());
    }

    /* findPersonByDNI */
    @Test
    public void findPersonByDNINoPerson()
    {
        String DNI = "111111A";
        Assert.assertFalse(personService.findPersonByDNI(persons,
                LambdaFunctions.getPersonByDNI(DNI)).isPresent());
    }

    @Test
    public void findPersonByDNIOk()
    {
        String DNI = "1234E";
        Assert.assertTrue(personService.findPersonByDNI(persons,
                LambdaFunctions.getPersonByDNI(DNI)).isPresent());
        Assert.assertTrue(personService.findPersonByDNI(persons,
                LambdaFunctions.getPersonByDNI(DNI)).get() instanceof Person);
        Assert.assertTrue(personService
                .findPersonByDNI(persons, LambdaFunctions.getPersonByDNI(DNI)).get().getDNI()
                .equals(DNI));
    }

}
