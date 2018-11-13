import java.util.*;

// COVER Collections.sort(), Comparable, Comparator, inner Comparator.
public class collection_algorithms {

	public static void main(String[] args) {

		//Collections.sort(List<T> list) has
		//METHOD SIGNATURE = public static <T extends Comparable<? super T> void sort(List<T> list)
		//Comparable Interface has a method - public int compareTo(T o) to override.
        //List is not sorted (descending not ascending)
		List<Person> people = new ArrayList<>();
        people.add(new Person("Oldest", 30));
        people.add(new Person("Middle", 20));
		people.add(new Person("Youngest", 10));
        System.out.println("Before sorting:\n" + people);
		Collections.sort(people); //Will sorted by how comparable class Person is defined. (compareTo)
        System.out.println("After sorting:\n" + people);


        //Java.util.Comparator<T> is another way of defining comparison standards.
        //Comparator is an interface with two methods, int compare (T o1, o2) and boolean equals(Object obj)
        class PersonComparator implements Comparator<Person> {
            public int compare(Person a, Person b) {
                if (a.getAge() > b.getAge()) {
                    return 1;
                } else if (b.getAge() > a.getAge()) {
                    return -1;
                } else {
                    return a.getName().compareTo(b.getName());
                }
            }
        }
        Collections.sort(people, new PersonComparator());

        // Using an instance of an anonymous inner class to define comparator directly.
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
    }
}