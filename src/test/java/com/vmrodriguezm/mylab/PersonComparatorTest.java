package com.vmrodriguezm.mylab;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
public class PersonComparatorTest {

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

    /* personsSortedByAge */
    @Test(expected = NullPointerException.class)
    public void personsSortedByAgeOneNull()
    {
        persons.add(personService.createNewPerson(LambdaFunctions.getNewPerson("12390E",
                "Null Age Person", null,
                personService.createNewAddress(LambdaFunctions.getNewAddress("A", 13, new Long(
                        28830), "San fernando de Henares", "Spain")), "male", LocalDate.now(),
                "noEmail@gmail.com")));
        persons = personService.personsSortedByAge(persons);
    }

    @Test
    public void personsSortedByAgeOK()
    {
        List<Person> personsSort = personService.personsSortedByAge(persons);
        Assert.assertTrue(personsSort.get(0).getAge().compareTo(new Integer(15)) == 0);
    }

    /* orderByAgePersonNamedJose */

    @Test(expected = NullPointerException.class)
    public void orderByAgePersonNamedJoseNullAge()
    {
        persons.add(personService.createNewPerson(LambdaFunctions.getNewPerson("12390E",
                "Jose Pedro", null,
                personService.createNewAddress(LambdaFunctions.getNewAddress("A", 13, new Long(
                        28830), "San fernando de Henares", "Spain")), "male", LocalDate.now(),
                "noEmail@gmail.com")));
        persons = personService.personsSortedByAge(persons);
    }

    @Test
    public void orderByAgePersonNamedJoseNoJose()
    {
        persons.forEach(p -> p.setName("NoName"));
        Assert.assertTrue(persons.stream().filter(LambdaFunctions.getPersonNamedJose())
                .sorted(Comparator.comparing(Person::getAge)).count() == 0);
    }

    @Test
    public void orderByAgePersonNamedJoseOK()
    {
        persons.stream().filter(LambdaFunctions.getPersonNamedJose())
                .sorted(Comparator.comparing(Person::getAge));
        Assert.assertTrue(persons.get(0).getDNI().equals("1234E"));
    }

}
