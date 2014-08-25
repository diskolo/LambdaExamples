package com.vmrodriguezm.mylab;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public interface PersonService {

    /* Predicate<T> */
    public long numberPersonNamedJose(Collection<Person> persons, Predicate<Person> predicate);

    public Collection<Person> personUnder20(Collection<Person> persons, Predicate<Person> predicate);

    public Collection<Person> personBetween20And40AndMale(Collection<Person> persons,
            Predicate<Person> predicate);

    public Collection<Person> personWhoAre20orAre40(Collection<Person> persons,
            Predicate<Person> predicate);

    public Collection<Person> personBetween20And40Age(Collection<Person> persons,
            Predicate<Person> predicate);

    public Optional<Person> findPersonByDNI(Collection<Person> persons, Predicate<Person> predicate);

    /* Function<T,R> */

    public Collection<String> getEmailFromPersonsJose(Collection<Person> persons,
            Predicate<Person> predicate,
            Function<Person, String> function);

    public Collection<String> getStringAddressFromPersonUnder20(Collection<Person> persons,
            Predicate<Person> predicate,
            Function<Person, Address> function);

    /* Supplier<T> */

    public Person createNewPerson(Supplier<Person> supplier);

    public Address createNewAddress(Supplier<Address> supplier);

    /* Consumer<T> */

    public void publicEmailFromPersonJose(Collection<Person> persons, Predicate<Person> predicate,
            Function<Person, String> function, Consumer<String> consumer);

    /* Comparator and sorted */

    public List<Person> personsSortedByAge(List<Person> persons);

    public Collection<Person> orderByAgePersonNamedJose(Predicate<Person> predicate,
            Collection<Person> persons);

    /* Match */

    public boolean isAllPersonUnder10(Collection<Person> persons);

    public boolean isAllPersonOver50(Collection<Person> persons);

    public boolean isNonePersonFromCountry(Collection<Person> persons,Predicate<Person>predicate);

    public boolean isAnyPersonFromCountry(Collection<Person> persons,Predicate<Person>predicate);

    /* Use of IntSummaryStatistics() */

    public long getCount(Collection<Person> persons, ToIntFunction<Person>ageFunction);

    public int getMaxAge(Collection<Person> persons, ToIntFunction<Person> ageFunction);

    public int getMinAge(Collection<Person> persons, ToIntFunction<Person> ageFunction);

    public long getSumAgeOfPersonJose(Collection<Person> persons, Predicate<Person> predicate,
            ToIntFunction<Person> ageFunction);

    public double getAverageOfPersonJose(Collection<Person> persons, Predicate<Person> predicate,
            ToIntFunction<Person> ageFunction);

}
