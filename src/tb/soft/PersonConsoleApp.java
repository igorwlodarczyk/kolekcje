package tb.soft;

import java.util.*;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie 
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonConsoleApp.java
 *          
 *   Autor: Paweł Rogaliński
 *    Data: październik 2018 r.
 */
public class PersonConsoleApp {

	private static final String GREETING_MESSAGE = 
			"Program Person - wersja konsolowa\n" + 
	        "Autor: Paweł Rogaliński\n" +
			"Data:  październik 2018 r.\n";

	private static final String MENU = 
			"    M E N U   G Ł Ó W N E  \n" +
			"1 - Podaj dane nowej osoby \n" +
			"2 - Usuń dane osoby        \n" +
			"3 - Modyfikuj dane osoby   \n" +
			"4 - Wczytaj dane z pliku   \n" +
			"5 - Zapisz dane do pliku   \n" +
			"6 - Wczytanie osob do kolekcji z przygotowanych plikow\n" +
			"7 - Wyswietlenie kolekcji  \n" +
			"8 - Zapis osoby do kolekcji\n" +
			"9 - Usuniecie osoby z kolekcji\n" +
			"0 - Zakończ program        \n";

	private static final String MENU_COLLECTION =
			"Jaka kolekcja?        \n" +
					"1 - HashSet   \n" +
					"2 - TreeSet   \n" +
					"3 - ArrayList \n" +
					"4 - LinkedList\n" +
					"5 - HashMap   \n" +
					"6 - TreeMap   \n";
	
	private static final String CHANGE_MENU = 
			"   Co zmienić?     \n" + 
	        "1 - Imię           \n" + 
			"2 - Nazwisko       \n" + 
	        "3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" +
	        "0 - Powrót do menu głównego\n";

	
	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
	 * prostych metod do realizacji dialogu z użytkownikiem
	 * w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new JOptionUserDialog();

	private static ArrayList<String> fileNames = new ArrayList<>();
	static PersonCollections collections = new PersonCollections();

	
	public static void main(String[] args) {
		// Utworzenie obiektu aplikacji konsolowej
		// oraz uruchomienie głównej pętli aplikacji.
		PersonConsoleApp application = new PersonConsoleApp();
		application.runMainLoop();
	} 

	
	/*
	 *  Referencja do obiektu, który zawiera dane aktualnej osoby.
	 */
	private Person currentPerson = null;
	
	
	/*
	 *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
	 *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
	 *         w której program się zatrzymuje aż do zakończenia
	 *         działania za pomocą metody System.exit(0); 
	 */
	public void runMainLoop() {
		UI.printMessage(GREETING_MESSAGE);

		while (true) {
			UI.clearConsole();


			try {
				switch (UI.enterInt(MENU + "==>> ")) {
				case 1:
					// utworzenie nowej osoby
					currentPerson = createNewPerson();
					showCurrentPerson();
					break;
				case 2:
					// usunięcie danych aktualnej osoby.
					currentPerson = null;
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					break;
				case 3:
					// zmiana danych dla aktualnej osoby
					if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
					changePersonData(currentPerson);
					break;
				case 4: {
					// odczyt danych z pliku tekstowego.
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					currentPerson = Person.readFromFile(file_name);
					UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
					showCurrentPerson();
				}
					break;
				case 5: {
					// zapis danych aktualnej osoby do pliku tekstowego 
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					Person.printToFile(file_name, currentPerson);
					UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);
				}
					break;
				case 6: {
					//Wczytanie parametrow startowych
					loadPersonsFromFile();
				}
					break;
				case 7: {
					//wyswietlanie kolekcji
					if(currentPerson != null){
						switch (UI.enterInt(MENU_COLLECTION + "==>> ")){
							case 1:
								showHashSet(currentPerson);
								break;
							case 2:
								showTreeSet(currentPerson);
								break;
							case 3:
								showArrayList(currentPerson);
								break;
							case 4:
								showLinkedList(currentPerson);
								break;
							case 5:
								showHashMap(currentPerson);
								break;
							case 6:
								showTreeMap(currentPerson);
								break;
						}
					}else UI.printMessage("Aktualna osoba nie moze byc nullem!");

				}
					break;
				case 8: {
					//Dodanie aktualnej osoby do kolekcji
					collections.addPerson(currentPerson);
				}
					break;
				case 9: {
					//Usuniecie aktualnej osoby z kolekcji
					collections.removePerson(currentPerson);
				}
					break;
				case 0:
					// zakończenie działania programu
					UI.printInfoMessage("\nProgram zakończył działanie!");
					System.exit(0);
				} // koniec instrukcji switch
			} catch (PersonException e) { 
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		} // koniec pętli while
	}

	public static void loadPersonsFromFile() throws PersonException {
		fileNames.add("plik1");
		fileNames.add("plik2");
		fileNames.add("plik3");
		Person person;
		for(String name : fileNames){
			person = Person.readFromFile(name);
			collections.addPerson(person);
		}
	}

	public static void showHashSet(Person currentPerson) throws PersonException {
		HashSet<Person> personHashSet = collections.getPersonHashSet();
		HashSet<PersonE> personHashSetE = collections.getPersonHashSetE();
		PersonE currentPerson2 = new PersonE(currentPerson);

		UI.printMessage("Wyswietlanie HashSetu zawierajacego klase Person\n");
		for(Person person : personHashSet){
			showPerson(person);
			if(person.equals(currentPerson)){
				UI.printMessage("Jest to ta sama osoba co aktualnie wczytana\n");
			}
		}

		UI.printMessage("Wyswietlanie HashSetu zawierajacego klase Person z funkcjami hashcode oraz equals\n");
		for(PersonE person2 : personHashSetE){
			showPerson(person2);
			if(person2.equals(currentPerson2)){
				UI.printMessage("Jest to ta sama osoba co aktualnie wczytana\n");
			}
		}
	}

	public static void showTreeSet(Person currentPerson) throws PersonException {
		TreeSet<Person> personTreeSet = collections.getPersonTreeSet();
		TreeSet<PersonE> personTreeSetE = collections.getPersonTreeSetE();
		PersonE currentPerson2 = new PersonE(currentPerson);

		UI.printMessage("Wyswietlanie TreeSetu zawierajacego klase Person\n");
		for(Person person : personTreeSet){
			showPerson(person);
			if(person.equals(currentPerson)){
				UI.printMessage("Jest to ta sama osoba co aktualnie wczytana\n");
			}
		}

		UI.printMessage("Wyswietlanie TreeSetu zawierajacego klase Person z funkcjami hashcode oraz equals\n");
		for(PersonE person2 : personTreeSetE){
			showPerson(person2);
			if(person2.equals(currentPerson2)){
				UI.printMessage("Jest to ta sama osoba co aktualnie wczytana\n");
			}
		}
	}

	public static void showArrayList(Person currentPerson) throws PersonException {
		ArrayList<Person> personTreeSet = collections.getPersonArrayList();
		ArrayList<PersonE> personTreeSetE = collections.getPersonArrayListE();
		PersonE currentPerson2 = new PersonE(currentPerson);

		UI.printMessage("Wyswietlanie ArrayListy zawierajacego klase Person\n");
		for(Person person : personTreeSet){
			showPerson(person);
			if(person.equals(currentPerson)){
				UI.printMessage("Jest to ta sama osoba co aktualnie wczytana\n");
			}
		}

		UI.printMessage("Wyswietlanie ArrayListy zawierajacego klase Person z funkcjami hashcode oraz equals\n");
		for(PersonE person2 : personTreeSetE){
			showPerson(person2);
			if(person2.equals(currentPerson2)){
				UI.printMessage("Jest to ta sama osoba co aktualnie wczytana\n");
			}
		}
	}

	public static void showLinkedList(Person currentPerson) throws PersonException {
		LinkedList<Person> personTreeSet = collections.getPersonLinkedList();
		LinkedList<PersonE> personTreeSetE = collections.getPersonLinkedListE();
		PersonE currentPerson2 = new PersonE(currentPerson);

		UI.printMessage("Wyswietlanie LinkedListy zawierajacego klase Person\n");
		for(Person person : personTreeSet){
			showPerson(person);
			if(person.equals(currentPerson)){
				UI.printMessage("Jest to ta sama osoba co aktualnie wczytana\n");
			}
		}

		UI.printMessage("Wyswietlanie LinkedListy zawierajacego klase Person z funkcjami hashcode oraz equals\n");
		for(PersonE person2 : personTreeSetE){
			showPerson(person2);
			if(person2.equals(currentPerson2)){
				UI.printMessage("Jest to ta sama osoba co aktualnie wczytana\n");
			}
		}
	}

	public static void showHashMap(Person currentPerson) throws PersonException {
		HashMap<Integer, PersonE> personHashMapE = collections.getPersonHashMapE();
		PersonE currentPerson2 = new PersonE(currentPerson);

		UI.printMessage("Wyswietlanie HashMapy zawierajacego klase Person z funkcjami hashcode oraz equals\n");
		for(PersonE person2 : personHashMapE.values()){
			showPerson(person2);
			if(person2.equals(currentPerson2)){
				UI.printMessage("Jest to ta sama osoba co aktualnie wczytana\n");
			}
		}
	}

	public static void showTreeMap(Person currentPerson) throws PersonException {
		TreeMap<Integer, PersonE> personTreeMapE = collections.getPersonTreeMapE();
		PersonE currentPerson2 = new PersonE(currentPerson);

		UI.printMessage("Wyswietlanie TreeMapy zawierajacego klase Person z funkcjami hashcode oraz equals\n");
		for(PersonE person2 : personTreeMapE.values()){
			showPerson(person2);
			if(person2.equals(currentPerson2)){
				UI.printMessage("Jest to ta sama osoba co aktualnie wczytana\n");
			}
		}
	}
	
	
	/*
	 *  Metoda wyświetla w oknie konsoli dane aktualnej osoby 
	 *  pamiętanej w zmiennej currentPerson.
	 */
	void showCurrentPerson() {
		showPerson(currentPerson);
	} 

	
	/* 
	 * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej 
	 * przez obiekt klasy Person
	 */ 
	static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();
		
		if (person != null) {
			sb.append("Aktualna osoba: \n")
			  .append("      Imię: ").append(person.getFirstName()).append("\n")
			  .append("  Nazwisko: ").append(person.getLastName()).append("\n")
			  .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
			  .append("Stanowisko: ").append(person.getJob()).append("\n");
		} else
			sb.append( "Brak danych osoby\n" );
		UI.printMessage( sb.toString() );
	}

	
	/* 
	 * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
	 * klasy Person i wypełnia atrybuty wczytanymi danymi.
	 * Walidacja poprawności danych odbywa się w konstruktorze i setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static Person createNewPerson(){
		String first_name = UI.enterString("Podaj imię: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		Person person;
		try { 
			// Utworzenie nowego obiektu klasy Person oraz
			// ustawienie wartości wszystkich atrybutów.
			person = new Person(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {    
			// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
			// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
			// poszczególnych atrybutów.
			// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}
	
	
	/* 
	 * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów 
	 * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
	 * Walidacja poprawności wczytanych danych odbywa się w setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static void changePersonData(Person person)
	{
		while (true) {
			UI.clearConsole();
			showPerson(person);

			try {		
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
				case 1:
					person.setFirstName(UI.enterString("Podaj imię: "));
					break;
				case 2:
					person.setLastName(UI.enterString("Podaj nazwisko: "));
					break;
				case 3:
					person.setBirthYear(UI.enterString("Podaj rok ur.: "));
					break;
				case 4:
					UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
					person.setJob(UI.enterString("Podaj stanowisko: "));
					break;
				case 0: return;
				}  // koniec instrukcji switch
			} catch (PersonException e) {     
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	
	
}  // koniec klasy PersonConsoleApp
