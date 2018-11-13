// COMPARABLE CLASS THAT EXTENDS COMPARABLE INTERFACE. USED AS A BASE CODE. 
public class Person implements Comparable<Person> {
	private String name;
	private int age;
	
	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}

	public int getAge() {
	    return this.age;
    }

    public String getName() {
	    return this.name;
    }

    @Override
    public String toString() {
        return this.name + " "+ this.age;
    }

    @Override
	public int compareTo(Person o) {
		return this.age - o.age; //IF equal, return 0, positive if older.
	}

}