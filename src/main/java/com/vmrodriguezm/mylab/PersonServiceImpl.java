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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public long numberPersonNamedJose(Collection<Person> persons, Predicate<Person> predicate) {
        return persons.stream().filter(predicate).count();
    }

    @Override
    public Collection<Person> personUnder20(Collection<Person> persons, Predicate<Person> predicate) {
        // TODO Auto-generated method stub
        return persons.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Collection<Person> personBetween20And40AndMale(Collection<Person> persons,
            Predicate<Person> predicate) {
        // TODO Auto-generated method stub
        return persons.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Collection<Person> personWhoAre20orAre40(Collection<Person> persons,
            Predicate<Person> predicate) {
        // TODO Auto-generated method stub
        return persons.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Collection<Person> personBetween20And40Age(Collection<Person> persons,
            Predicate<Person> predicate) {
        // TODO Auto-generated method stub
        return persons.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Person createNewPerson(Supplier<Person> supplier) {
        // TODO Auto-generated method stub
        return supplier.get();
    }

    @Override
    public void publicEmailFromPersonJose(Collection<Person> persons, Predicate<Person> predicate,
            Function<Person, String> function, Consumer<String> consumer) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<Person> orderByAgePersonNamedJose(Predicate<Person> predicate,
            Collection<Person> persons) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAllPersonUnder10(Collection<Person> persons) {
        // TODO Auto-generated method stub
        return persons.stream().allMatch(LambdaFunctions.getPersonUnderAge(new Integer(10)));
    }

    @Override
    public boolean isAllPersonOver50(Collection<Person> persons) {
        // TODO Auto-generated method stub
        return persons.stream().allMatch(LambdaFunctions.getPersonOverAge(new Integer(50)));
    }

    @Override
    public boolean isNonePersonFromCountry(Collection<Person> persons,Predicate<Person>predicate) {
        // TODO Auto-generated method stub
        
       return persons.stream().noneMatch(predicate);
        
       }

    @Override
    public boolean isAnyPersonFromCountry(Collection<Person> persons,Predicate<Person>predicate) {
        // TODO Auto-generated method stub
        return persons.stream().anyMatch(predicate);
    }

    @Override
    public long getCount(Collection<Person> persons, ToIntFunction<Person> agePerson) {
        // TODO Auto-generated method stub
        return persons.stream().collect(Collectors.summarizingInt(agePerson)).getCount();
    }

    @Override
    public int getMaxAge(Collection<Person> persons, ToIntFunction<Person> ageFunction) {
        // TODO Auto-generated method stub
        return persons.stream()
                .collect(Collectors.summarizingInt((ToIntFunction<Person>) ageFunction)).getMax();
    }

    @Override
    public int getMinAge(Collection<Person> persons, ToIntFunction<Person> ageFunction) {
        // TODO Auto-generated method stub
        return persons.stream()
                .collect(Collectors.summarizingInt((ToIntFunction<Person>) ageFunction)).getMin();
    }

    @Override
    public long getSumAgeOfPersonJose(Collection<Person> persons, Predicate<Person> predicate,
            ToIntFunction<Person> ageFunction) {
        // TODO Auto-generated method stub
        return persons.stream().filter(predicate).collect(Collectors.summarizingInt(ageFunction))
                .getSum();
    }

    @Override
    public double getAverageOfPersonJose(Collection<Person> persons, Predicate<Person> predicate,
            ToIntFunction<Person> ageFunction) {
        // TODO Auto-generated method stub
        return persons.stream().filter(predicate).collect(Collectors.summarizingInt(ageFunction))
                .getAverage();
    }

    @Override
    public Address createNewAddress(Supplier<Address> supplier) {
        // TODO Auto-generated method stub
        return supplier.get();
    }

    @Override
    public Collection<String> getEmailFromPersonsJose(Collection<Person> persons,
            Predicate<Person> predicate,
            Function<Person, String> function) {
        // TODO Auto-generated method stub
        return persons.stream().filter(predicate).map(function).collect(Collectors.toList());
    }

    @Override
    public Collection<String> getStringAddressFromPersonUnder20(Collection<Person> persons,
            Predicate<Person> predicate, Function<Person, Address> function) {
        // TODO Auto-generated method stub
        return persons.stream().filter(predicate).map(function).map(Address::toString)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> findPersonByDNI(Collection<Person> persons, Predicate<Person> predicate) {
        // TODO Auto-generated method stub
        return persons.stream().filter(predicate).findFirst();
    }

    @Override
    public List<Person> personsSortedByAge(List<Person> persons) {
        // TODO Auto-generated method stub
        return persons.stream().sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());
    }

}
