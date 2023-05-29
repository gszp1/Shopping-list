# Shopping-list
Napisz program umożliwiający przygotowanie listy zakupów. Praca z programem odbywa się w trybie tekstowym (bez interfejsu graficznego). Program wczytuje z pliku tekstowego listę możliwych do zakupienia produktów, które podzielone są na kategorie. Przykładowo w pliku mogą być zapisane następujące produkty:

    Spożywcze
        Chleb
        Masło
        Mleko
        Żółty ser
    Chemia
        Mydło
        Płyn do mycia naczyń
    Motoryzacja
        Odświeżacz powietrza
        Płyn do spryskiwaczy

Zaproponuj odpowiedni format danych, tak aby był on prosty i wygodny dla użytkownika. Plik z listą produktów jest przygotowywany przez użytkownika przy pomocy edytora tekstowego. Nie jest on modyfikowany przez program.

Podczas uruchamiania program wczytuje listę produktów. Program ma umożliwiać:

    dodanie produktu do listy zakupów zgodnie z poniższym scenariuszem:
        program wyświetla dostępne kategorie
        użytkownik wybiera kategorię
        program wyświetla dostępne produkty z danej kategorii
        użytkownik wybiera produkt z danej kategorii
    wyświetlenie wszystkich produktów z listy zakupów
    wyświetlenie wszystkich produktów z listy zakupów z danej kategorii (użytkownik wybiera kategorię)
    usunięcie wszystkich produktów z listy zakupów
    usunięcie wszystkich produktów z listy zakupów z danej kategorii (użytkownik wybiera kategorię)
    usunięcie produktu z listy zakupów (użytkownik wybiera kategorię, następnie produkt)
    zapis listy zakupów na dysku

Przy kolejnym uruchomieniu program wczytuje do edycji ostatnio zapisaną listę zakupów (program nie umożliwia jednoczesnego zapisania kilku odrębnych list zakupów).

W zadaniu można dla uproszczenia pominąć kategorie produktów. Tak uproszczone zadanie oceniane jest na ocenę 3.

Rozbuduj program "Lista zakupów" z zadania 4.

Program na ocenę 3 ma umożliwiać:

    obsługę za pomocą GUI
    edycję listy możliwych do zakupienia produktów
    uwzględnienie liczności/ilości danego produktu
        należy przechowywać w liście produktów dodatkową informację o tym w jakich jednostkach mierzy się liczność/ilość danego produktu (np. sztuki, kilogramy, metry, litry)
        należy uwzględnić to czy liczność/ilość danego produktu jest liczbą całkowitą czy też zmiennoprzecinkową

Dodatkowo na ocenę 4 program ma umożliwiać:

    odczyt i edycję tej samej listy zakupów przez dwóch użytkowników siedzących przy różnych komputerach
        można przyjąć dla uproszczenia że użytkownicy nie edytują listy zakupów w tym samym czasie

Dodatkowo na ocenę 5 program ma umożliwiać:

    korzystanie z programu przez wielu użytkowników siedzących przy różnych komputerach
    posiadanie przez każdego użytkownika więcej niż jednej listy zakupów (np. codzienna, tygodniowa)
    przydzielenie dostępu innemu/innym użytkownikom do jednej lub kilku posiadanych przez danego użytkownika list zakupów, dostęp może być tylko do odczytu lub do odczytu i edycji
        należy zaimplementować i przetestować synchronizację zmian wprowadzanych przez różnych użytkowników (np. jeden użytkownik usuwa produkt z listy zakupów podczas gdy drugi użytkownik w tym samym momencie zwiększa liczność/ilość danego produktu)
