package tb.soft;

public class PersonE extends Person{

    public PersonE(Person person) throws PersonException {
        super(person.getFirstName(), person.getLastName());
        birthYear = person.getBirthYear();
        job = person.getJob();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        PersonE person = (PersonE) obj;

        if (getBirthYear() != person.getBirthYear())
            return false;

        if (getFirstName() != null ? !getFirstName().equals(person.getFirstName()) : person.getFirstName() != null)
            return false;

        if (getLastName() != null ? !getLastName().equals(person.getLastName()) : person.getLastName() != null)
            return false;

        return getJob().equals(person.getJob());
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;

        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getBirthYear();
        result = 31 * result + getJob().hashCode();

        return result;
    }
}
