public class Person implements Identifiable {
    private final String name;
    private final int age;

    public Person(String name, int age) {

        if (name == null) {
            throw new NullPointerException();
        }

        if (age < 0) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.age = age;
    }

    public Person(Person person) {
        if (person == null) {
            throw new NullPointerException();
        } else {
            this.name = person.getName();
            this.age = person.getAge();
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean equals(Object object) {
        if (object instanceof Person) {
            Person person = (Person) object;
            if (person.getAge() == age && person.getName() == name) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return String.format("Person<name=%s, age=%d", name, age);
    }


}
