package tb.soft;

import java.util.*;

public class PersonCollections {
    // Set
    private static HashSet<Person> personHashSet;
    private static HashSet<PersonE> personHashSetE;

    private static TreeSet<Person> personTreeSet;
    private static TreeSet<PersonE> personTreeSetE;
    // List
    private static ArrayList<Person> personArrayList;
    private static ArrayList<PersonE> personArrayListE;

    private static LinkedList<Person> personLinkedList;
    private static LinkedList<PersonE> personLinkedListE;
    // Map

    private static HashMap<Integer, PersonE> personHashMapE;

    private static TreeMap<Integer, PersonE> personTreeMapE;

    public PersonCollections(){
        personHashSet = new HashSet<>();
        personHashSetE = new HashSet<>();
        personTreeSet = new TreeSet<>();
        personTreeSetE = new TreeSet<>();
        personArrayList = new ArrayList<>();
        personArrayListE = new ArrayList<>();
        personLinkedList = new LinkedList<>();
        personLinkedListE = new LinkedList<>();
        personHashMapE = new HashMap<>();
        personTreeMapE = new TreeMap<>();
    }

    public static void addPerson(Person person) throws PersonException {
        PersonE person2 = new PersonE(person);

        personHashSet.add(person);
        personHashSetE.add(person2);
        personTreeSet.add(person);
        personTreeSetE.add(person2);
        personArrayList.add(person);
        personArrayListE.add(person2);
        personLinkedList.add(person);
        personLinkedListE.add(person2);

        personHashMapE.put(person2.hashCode(), person2);

        personTreeMapE.put(person2.hashCode(), person2);
    }

    public static void removePerson(Person person) throws PersonException {
        PersonE person2 = new PersonE(person);

        personHashSet.remove(person);
        personHashSetE.remove(person2);
        personTreeSet.remove(person);
        personTreeSetE.remove(person2);
        personArrayList.remove(person);
        personArrayListE.remove(person2);
        personLinkedList.remove(person);
        personLinkedListE.remove(person2);
        personHashMapE.remove(person2.hashCode(), person2);
        personTreeMapE.remove(person2.hashCode(), person2);
    }

    public static HashSet<Person> getPersonHashSet(){
        return personHashSet;
    }

    public static HashSet<PersonE> getPersonHashSetE(){
        return personHashSetE;
    }

    public static TreeSet<Person> getPersonTreeSet(){
        return personTreeSet;
    }

    public static TreeSet<PersonE> getPersonTreeSetE(){
        return personTreeSetE;
    }

    public static ArrayList<Person> getPersonArrayList(){
        return personArrayList;
    }

    public static ArrayList<PersonE> getPersonArrayListE(){
        return personArrayListE;
    }

    public static LinkedList<Person> getPersonLinkedList(){
        return personLinkedList;
    }

    public static LinkedList<PersonE> getPersonLinkedListE(){
        return personLinkedListE;
    }

    public static HashMap<Integer, PersonE> getPersonHashMapE(){
        return personHashMapE;
    }

    public static TreeMap<Integer, PersonE> getPersonTreeMapE(){
        return personTreeMapE;
    }


}
