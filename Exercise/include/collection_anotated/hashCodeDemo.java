public class hashCodeDemo {

    // Hashcode method maps an object to an int which can be used to find the object in a kind
    // of data structure known as a hash table. The point of a hash code is that it can be computed
    // in constant time, so allow fast look-ups. Every object's hashcode method should return a consistent
    // harsh code that is not necessarily unique among all objects. *(does not remain consistent for different execution)
    // In Object class, public int hashcode(); ----> hashcode function.

    // The hashcode of an object should not change unless variable used in equal method is modified.
    // If two objects are equal by equals(Object) method, the hashcode for them must produce same integer output.
    // Although objects that are not equal, does not need to produce different hashcode but distinct hashcode means
    // better performance of hash tables.

//    // Hash table is like (bucket with object inside)
//    Index   1        2        3        4       6   ... (Hashcode)
//        [Object] [Object] [Object] [Object] [Object]
//     MULTIPLE ELEMENTS IN SINGLE HASHCODE BUCKET IS CONNECTED IN LINKED LIST (slow)
//    So if there are different elements in single hashcode table, hash-table operation will be slower.
//    If you override equals method, you should override hashcode as well!

//    A Recipe for implementing hashcode #####################################################
//    1. Initialize result with a constant non-zero value.
//    2. For each significant field f(e.g. compared in equals method),
//        compute an int hashcode c and add it to 31 * result.
//            * For boolean fields, c = (f ? 1 : 0)
//            * For byte, char, short, int fields, c = (int) f
//            * For long fields, c =(int) (f^ (f >>> 32))
//            * For float fields, c = Float.floatToIntBits(f)
//            * For double fields, c = (int) (Double.doubleToLongBits(f)) ^ (Double.doubleToLongBits(f) >>> 32)
//            * For reference fields, if equals calls equals on the field, c = f.hashcode()
//            * For array fields, c = Arrays.hashcode(f);
//    3. Return result.

    // Example of Overriding hashcode.
    static abstract class Person {
        public String name;
        public int age;
        public Person(String name, int age) {this.name = name; this.age = age;}
    }
    static class FoundPerson extends Person {
        public FoundPerson(String name, int age) { super(name,age); }

        @Override
        public boolean equals(Object other) {
            if(this == other) return true;
            if(!(other instanceof Person)) return false;
            return ((Person) other).name.equals(this.name)
                    && ((Person) other).age == (this.age);
        }
        @Override
        public int hashCode() {
            int result = 15; //Any non-zero.
            result = 31 * result + name.hashCode(); //For name fields (string)
            result = 31 * result + (int) age; //For age fields (int)
            return result;
        }

    }

}
