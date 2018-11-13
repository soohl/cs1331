import java.util.*;

// COVER Lambda, method references.
public class lambda {

    public static void main(String[] args) {

        // Using comparable class definition.
        List<Person> people = new ArrayList<>();
        people.add(new Person("Oldest", 30));
        people.add(new Person("Middle", 20));
        people.add(new Person("Youngest", 10));
        Collections.sort(people);

        // Using anonymous inner class definition.
        Collections.sort(people, new Comparator<Person>() {
            public int compare(Person a, Person b) {
                if (a.getAge() > b.getAge()) {
                    return 1;
                } else if (b.getAge() > a.getAge()) {
                    return -1;
                } else {
                    return a.getName().compareTo(b.getName());
                }
            }
        });

        // Using a lambda expression #######################
        // Any interface with a single abstract method is a functional interface.
        // Comparator interface has compareTo(T o1, T o2) abstract method.
        // Lambda expression is a synthetic shortcut for defining functional interface.
        // General Syntax is (T1 p1, ... , Tn Pn) -> {method_body}
        // Where T1...Tn = types, and p1...pn = parameter name.
        // Since Collections.sort(List<T>, Comparator<T>), we can change to
        // Collections.sort(List<T>, (T1 p1, ... , Tn pn) -> {method_body}) in lambda form.\

//        static interface Bar{
//            int compareTo(Top a, Top b);
//        }
//        static void foor(Bar b) {...}
//        foo((Top a, Top b) -> {...})

        Collections.sort(people, (Person a, Person b) -> {
            if (a.getAge() > b.getAge()) {
                return 1;
            } else if (b.getAge() > a.getAge()) {
                return -1;
            } else {
                return a.getName().compareTo(b.getName());
            }
        });

        // If a method already exists that fits the specification for a parameter that could
        // take a lambda expression as an argument, you can use method reference instead.
        // Foo has single abstract method that takes an object and return void.
        // We have one method that takes instance of an object implementing functional interface.
        // We can use method reference instead of lambda.

//        public interface Foo{
//            void bar(Object o);
//        }
//        void doo(Foo f) {
//            f.bar("baz");
//        }
//        doo(System.out::println);
//        doo(x -> System.out.println(x)) //equivalent lambda expression.

        // 3 Kinds of different method references
//        1. Class::instanceMethod - like (x,y) -> x.instanceMethod(y);
//        e.g. Comparator<Top> byName = Comparator.comparing(Top::getName);
//        2. Class::staticMethod - like (x -> Class.staticMethod(x));
//        e.g. someList.removeIf(Objects::isNull);
//        3. object::instanceMethod - like x -> object.instanceMethod(x);
//        e.g. someList.forEach(System.out::println);

        // Examples of method references.
        List<Person> spurbury = new ArrayList<>();
        spurbury.add(new Person("Grady", 10));
        spurbury.add(new Person("Ursula", 20));
        spurbury.add(new Person("Rando", 30));
        spurbury.add(null);

        // Class::staticMethod method reference
        spurbury.removeIf(Objects::isNull);
        System.out.println("\nSpurbury officers with nulls removed:");
        System.out.println(spurbury);

        List<Person> superTroopers = new ArrayList<>();
        // object::instanceMethod method reference
        superTroopers.removeIf(spurbury::contains);
        System.out.println("\nJust super troopers:");
        System.out.println(superTroopers);
    }

}
