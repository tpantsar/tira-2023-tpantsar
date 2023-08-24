# Tehtävä 1

TIRA Coders -sovelluksen koodarilistaus on järjestämätön.  Tämä tekee koodarilistan selaamisesta ja koodareiden etsimisestä hankalaa. Siksi tässä tehtävässä toteutetaan yksinkertainen lajittelualgoritmi.

> Huomaa että jos algoritmi tai ohjeet eivät muuta kerro, lajittelu toteutetaan aina ns. **luonnolliseen järjestykseen** (*natural order*). Tämä tarkoittaa nousevaa järjestystä. Numeerisilla tietotyypeillä numeroiden arvot siis *kasvavat* taulukossa edetessä, ja merkkijonotaulukoissa tämä tarkoittaa aakkosjärjestystä A...Ö.
> 
> Jos sinun on hankala siirtyä edes takaisin kooditiedostojen ja tämän ohjeen välillä VS Codessa, avaa tämä ohje etäreposi ohjesivu nettiselaimella GitHubissa. Näin voit selaimessa lukea ohjeita ja tehdä koodaushommat VS Codessa.
> 
> Tehtävässä kirjoitat yhteensä noin 35-45 riviä koodia, riippuen koodaustyylistä.

## Lähteitä

* Yksinkertainen lajittelualgoritmi on käyty läpi Ohjelmointi 2 -kurssilla (pakollinen edeltäjäkurssi).
* Kurssin Luentokalvot.
* Liveluento (tallenne) ja sen vinkit ja esimerkit.
* Kurssikirjallisuus.
* [Lisäyslajittelu (wikipedia)](https://en.wikipedia.org/wiki/Insertion_sort).
* Javan [`Comparable` -rajapinta](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/lang/Comparable.html).
* Javan [`Object` -luokka](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/lang/Object.html).


## Yleistä

Ensimmäisen tehtävän yhteydessä tässä alla ohjeita joita kannattaa noudattaa läpi koko kurssin.

**Katso ensin** kurssin liveluento tai sen tallenne, joka esittelee TIRA Coders -sovelluksen rakenteen, tärkeät luokat ja mihin kohtaa koodia teet tehtävään liittyvät omat osuutesi tässä harjoituksessa.

**Huomaa** myös että tässä, kuten kurssin muissakaan tehtävissä (graafitehtävää 09 lukuunottamatta), **ei saa käyttää** Javan `Arrays`, `System` tai `Collections` -luokkien algoritmeja, tai *mitään* Javan tietosäiliöluokkia (`ArrayList`, `Vector`, `List`, `Collection` tai näiden rajapintojen toteutukset, jne). Myös Javan funktionaalisen ohjelmoinnin algoritmeja ei saa käyttää.

> **Kannattaa** heti alussa opetella käyttämään komentoja `git commit`, tarvittaessa `git add` sekä `git push` *usein*. Näin työsi on jatkuvasti tallessa etärepositoryssä, voit palata takaisin vanhempaan versioon tarvittaessa, ja ennen kaikkea jos tarvitset apua opettajilta, etärepossasi on mahdollisimman tuore koodi jota katsomalla opettajat voivat auttaa sinua ongelmissasi. Muista myös aina apua pyytäessäsi (sähköpostilla tai Moodlessa) laittaa mukaan URL etärepoosi, tämä nopeuttaa huomattavasti avun tarjoamista.

**TÄRKEÄÄ** 

Tässä tehtävässä toteutetaan lajittelualgoritmi. Lajittelussa lajiteltavia asioita aina **vertaillaan** jotta ne voidaan laittaa oikeaan järjestykseen. Vertailua toki tehdään myös muuallakin kuin lajitteluissa; se on erittäin yleinen operaatio, myös tämän kurssin muissa koodaustehtävissä.

Olet ehkä oppinut vertailemaan muuttujien arvoja operaattoreilla `<, <=, >, >=` sekä `==` ja `!=`. Esimerkiksi:

```C
	int a = 10;
	int b = 20;

	if (a < b) {
		printf("Muuttuja a on pienempi kuin b");
	}
	if (a == b) {
		printf("Muuttujat a ja b ovat yhtä suuret");
	}
	if (a != b) {
		printf("Muuttujat a ja b ovat eri suuret");
	}
```

Javan *luokkien* kanssa näitä **vertailuoperaattoreita ei voi eikä saa käyttää**. Ne toimivat vain perustietotyyppien kuten kokonaisluvut, reaaliluvut sekä merkkitieto, kanssa. 

Koska Javan *geneeriset* luokat ja metodit toimivat *vain luokkien*, eivät perustietotyyppien (int, double, char...) kanssa, se tarkoittaa sitä että geneeristen algoritmien ja luokkien toteutuksessa vertailuja ei noilla operaattoreilla tehdä. Koska toteutat algoritmit tässä tehtävässä geneerisinä, tämä on tärkeä asia.

**Miten vertailut sitten Javassa pitää toteuttaa?**

Olioiden yhtä- tai erisuuruutta vertailtaessa käytetään `equals` -metodia (jonka *oletustoteutus* periytyy kaikille luokille `Object` -luokasta). Tämä `equals` -metodi pitää itse omille luokille toteuttaa (ylikuormittaa, *override*) täsmälleen samalla rajapinnalla kuin se on `Object` -luokassa esitelty. Kuhunkin luokkaan tehdään sitten luokalle *omanlaisensa* yhtäsuuruuden vertailu, kun halutaan tietää ovatko luokan oliot `equal` eli "samanarvoisia". Esimerkiksi henkilö -olioita vertailtaessa tämä voisi tarkoittaa vaikka henkilötunnuksen vertailua. Nimi ei ole kovin hyvä vaihtoehto tähän, sillä kahdella *eri* henkilöllä voi olla *sama* nimi.

Kun taas halutaan verrata järjestystä, vertailtavan luokan täytyy Javassa toteuttaa `Comparable` -rajapinta, ja järjestysvertailu (`<, <=, >, >=`) tehdään kutsumalla rajapinnan määrittelemää `compareTo` -metodia:

```Java
   Integer a = 10; // Integer on luokka, ei perustietotyyppi int.
	Integer b = 20;

   if (a.compareTo(b) < 0) {
		print("Muuttuja a on pienempi kuin b");
	}
	if (a.equals(b)) {
		printf("Muuttujat a ja b ovat yhtä suuret");
	}
	// Tai (mutta ei aina):
	if (a.compareTo(b) == 0) {
		printf("Muuttujat a ja b ovat yhtä suuret");
	}
	if (!a.equals(b)) { // Huomaa huutomerkki - NOT equals
		printf("Muuttujat a ja b ovat eri suuret");
	}
	// Tai (mutta ei aina):
	if (a.compareTo(b) != 0) {
		printf("Muuttujat a ja b ovat yhtä suuret");
	}
```

Aivan liian yleinen virhe kurssin tehtävissä on se, että olioiden vertailu tehdään väärin käyttämällä vertailuoperaattoreita. Se menee läpi kääntäjästä, ohjelma toimii, mutta toimii väärin. Koska silloin ei vertailla sitä mitä pitäisi. Vertailuoperaattoreilla verrataan onko *olio* sama tai olio, esimerkiksi:

```Java
   Coder one = new Coder("Tiina");
	Coder another = new Coder("Pertti");
	Coder third = one;
	if (one == another) { // false, different objects
		// ...
	}
	if (one == third) { // true, one and third refer to the _same_ object, coder Tiina!
		// ...
	}
```

*Joskus* tietysti olion viittauksien vertaaminen on ihan paikallaan, mutta *vain* silloin kun nimenomaan halutaan katsoa viittaako joku muuttuja `x` samaan olioon kuin toinen muuttuja `y`.

Toinen yleinen virhe on se, että vertailumetodi `equals` joko unohdetaan toteuttaa tai se toteutetaan väärin, jolloin koodi ei toimi oikein. Tai `compareTo` -metodi toteutetaan väärin, jolloin tiedot esimerkiksi lajitellaan väärään järjestykseen. Ole siis näiden kanssa tarkkana.

Nyt sitten vihdoin ensimmäiseen kurssin harjoitustehtävään...


## Tavoite

TIRA Coders appin koodarilista on *järjestämätön*.  Tämä tekee koodarilistan selaamisesta ja koodareiden etsimisestä hankalaa, varsinkin kun koodareita on paljon.

Toteutat alempana olevien ohjeiden mukaisesti yksinkertaisen geneerisen taulukon **lisäyslajittelualgoritmin** (*insertion sort*) jolla koodarit saadaan nimen (sukunimi, etunimi) mukaiseen nousevaan (*natural*) järjestykseen.

Toteuta myös taulukon **järjestyksen kääntäminen** (*reverse*) päinvastaiseen järjestykseen. Sekä lajittelu että järjestyksen kääntäminen tehdään **"in-place"**, eli käyttäen vain pieniä apumuuttujia. Jos käytät lajitteluun tai kääntämiseen aputaulukoita, **et saa tehtävästä pisteitä**!

Hyödynnä alussa mainittuja lähteitä. Toteuta tehtävä alla olevien ohjeiden mukaisesti:

1. Aluksi toteutat geneeriset algoritmit joita testataan yksinkertaisilla Javan tietotyypeillä.
2. Sitten toteutat ne osuudet joilla koodaritkin saadaan käyttöliittymässä lajiteltua.

Kirjoita lopuksi **raporttiin** (`RAPORTTI.markdown` löytyy valmiiksi projektin juurihakemistosta) analyysisi tehtävästä, annettujen ohjeiden mukaisesti. Huomaa että myös raportti on myös **arvosteltava tehtävä**.

Kun olet tehnyt tehtävät alla olevien ohjeiden mukaisesti, **testaa** toteutustasi testeillä `src/test/java/oy/interact/tira/task_01_insertionsort ` -hakemistossa.

Hakemisto sisältää **neljä testiä**. Voit ajaa ne kaikki tai yksi kerrallaan joko VS Codesta käsin...:

![Testien ajaminen](task-01-vscode-tests.png)

...tai komentoriviltä yksi kerrallaan:

```console
mvn -Dtest=InsertionSortArrayTests test
```

Vasta sitten, kun testit menevät läpi, etene kokeilemaan miten lajittelu toimii TIRA Coders:ssa, alempana löytyvien ohjeiden mukaisesti.

## Askel 1 - Ohjeet

Tässä askeleessa:

* toteutat kaksi lajittelualgoritmia luokkaan `oy.interact.tira.student.Algorithms`.

> Huomaa että tässä vaiheessa sovelluksessa toimii vain taulukkopohjainen coder-puhelinluettelo (valikossa "Simple array phonebook"), joka on valmiiksi annettu toteutus. Toteutuksesta puuttuu pieniä palasia joita täydennät tehtävien aikana.

**Toteuta** taulukon geneerinen lisäyslajittelu `Algorithms` -luokan metodeihin:

* `public static <T extends Comparable<T>> void insertionSort(T [] array)` - lajittelee koko taulukon luonnolliseen järjestykseen. Voit olettaa ettei taulukossa ole `null`:eja.
* `public static <T extends Comparable<T>> void insertionSort(T [] array, int fromIndex, int toIndex)` - lajittelee taulukon luonnolliseen järjestykseen *annettujen indeksien välillä*. Voit olettaa ettei taulukossa ole annetulla indeksivälillä `null`:eja.

> **HUOM**: indeksit ovat `[fromIndex,toIndex)` -- lajittelu tehdään siis `fromIndex..<toIndex`, ei `fromIndex...toIndex`.
>
> Huomaa *uudelleenkäyttö*; voit toteuttaa metodin `insertionSort(T [] array)` kutsumalla metodia `insertionSort(T [] array, 0, array.length)`. Sama pätee myös järjestyksen kääntämisalgoritmeihin alla.

**Huomaa** miten algoritmin eli metodin rajapinnassa esiintyy Javan rajapinta `Comparable`. Näin algoritmi "vaatii" että tietotyyppi `T` joita taulukossa on, **toteuttaa** `Comparable` -rajapinnan. Algoritmia voidaan kutsua vain taulukoilla jotka sisältävät `Comparable` -rajapinnan toteuttavia olioita. Javan kääntäjä tietää tämän ja antaa käännösvirheen, jos luokka ei rajapintaa toteuta.

Voit jo tässä vaiheessa testata lajittelualgoritmiasi, koska sitä varten on omat erilliset testit (`InsertionSortArray...` -testit). Testit luovat `Integer` ja `String` -taulukoita. Molemmat luokat *toteuttavat* `Comparable` -rajapinnan joten algoritmin vaatimus toteutuu.

Toteuta myös taulukon **järjestyksen vaihtaminen** päinvastaiseksi `Algorithms` -luokan metodeihin:

* `void reverse(T [] array)` - kääntää koko taulukon alkioiden järjestyksen päinvastaiseksi.
* `void reverse(T [] array, int fromIndex, int toIndex)` - kääntää alkioiden järjestyksen päinvastaiseksi indeksien välillä.

> HUOM: indeksit ovat `[fromIndex,toIndex)` -- kääntäminen tehdään siis `fromIndex..<toIndex`, ei `fromIndex...toIndex`.

Koska järjestyksen vaihtaminen päinvastaiseksi ei vaadi olioiden vertailua, näissä algoritmien rajapinnoissa *ei esiinny* vaatimusta että `T`:n pitäisi toteuttaa `Comparable` -rajapinta.

**Testaa** toteutuksiasi (lajittelu ja kääntäminen) annetuilla yksikkötesteillä `task_01_insertionsort` -hakemistossa. Miten tämä tehdään, kuvattiin yllä.

Älä etene seuraavaan osuuteen alla ennenkuin testit menevät läpi. Testien suorittamisesta on kurssilla ohjeistus muuallakin. Etsi tai kysy, jos et löydä.

Testit luovat satunnaisia taulukoita `Integer` ja `String` -olioita, ja lajittelevat niitä algoritmillasi. Testit vertailevat lajiteltuja taulukoita siihen mitä tuloksena pitäisi olla. Jos tulos ei ole odotettu, testi epäonnistuu. Reverse -testit eivät vaadi lajiteltua aineistoa, joten lajittelu- ja reverse -testit eivät ole riippuvaisia toisistaan.

Jos testi epäonnistuu, analysoi mikä meni pieleen ja miksi, lukien tarkkaan testien tulostuksia. Jos et ymmärrä mitä sieltä tulee ulos, kysy apua opettajilta.

> Huom. Koska useissa algoritmeissa on tarve vaihtaa taulukossa olevien kahden elementin paikkaa, on hyödyllistä tehdä myös geneerinen `swap` -algoritmi: `public static <T> void swap(T[] array, int first, int second)`, joka vaihtaa indekseissä olevien olioiden paikkaa taulukossa. Voit kutsua tätä lajittelumetodeista ja muista taulukkoja käsittelevistä algoritmeista. Samaa asiaa ei kannata sinne tänne toteuttaa montaa kertaa!


## Askel 2 - TIRA Codersin koodareiden lajittelu

Tarvitset tässä ja kaikissa muissakin kurssin tehtävissä **testiaineiston** jolla testata tietorakenteiden ja algoritmien toimintaa. Koska testiaineisto on todella laaja, se ei ole mukana tässä repositoryssä koska tiedostokoko on liian suuri GitHubiin ladattavaksi.

**Hae testiaineisto** [TIRACoders.zip](https://drive.google.com/file/d/1-GU4HVON_txNmrq_7HCRe4tHweod5PSO/view?usp=share_link) tästä linkistä koneellesi. **Pura** haettu .zip -tiedosto tähän **samaan** hakemistoon missä repository on (hakemisto jossa nämä ohjetiedostot ja projektin `pom.xml` -tiedosto ovat). Sinun pitäisi nähdä nämä testiaineistotiedostot (`.json` -päätteiset tiedostot) myös VS Codessa, kuten tässä kuvassa näkyy:

![Testiaineisto näkyy VS Codessa](task-01-test-data-files.png)

> **Älä muokkaa testiaineistoa** millään tavoin. Jos aineisto menee sekaisin, koodi ei välttämättä enää toimi tai testit eivät mene läpi. Jos haluat, voit *kopioida* jonkun pienen aineiston *toiselle* tiedostonnimelle ja testailla sen kanssa omia juttujasi (vaikka lisätä sinne omat koodaritietosi). **Älä** laita testiaineistoa git:iin, sillä tiedostot ovat liian suuria sinne. 

**Kokeile** aluksi miten TIRA Coders toimii. Avaa sovellus ja huomaat että testikoodarit eivät ole aakkosjärjestyksessä. Lataa tiedostosta (valikko TIRA Coders > Import JSON phonebook) testikoodareita sisältäviä puhelinluetteloita (*pieniä*, isojen käsittely on vielä todella hidasta!) ja näet että nekin ovat missä sattuu järjestyksessä.

> Testiaineiston tulee siis löytyä projektin juurihakemistosta. Voit myös *kopioida* ne sellaiseen paikkaan josta vähemmillä klikkauksilla voit niitä avata TIRA Codersin käyttöliittymästä avata. Oletushakemisto, jonka sovellus ensimmäisen kerran avaa, on käyttäjän kotihakemisto, joten kopioi tiedostot vaikka sinne tai kotihakemiston uuteen alihakemistoon. Kun tiedosto on avattu kerran, sen jälkeen ohjelma käyttää samaa hakemistoa oletushakemistona, kunnes sovellus sammutetaan. **Älä** kuitenkaan *siirrä* tiedostoja pois tämän projektin päähakemistosta, sillä testit odottavat löytävänsä tiedostot sieltä.

Tässä askeleessa:

* täydennät luokan `oy.interact.tira.model.Coder` toteutusta,
* täydennät metodin `oy.interact.tira.util.SimpleContainer.sort()` toteutusta.

Testit edellä testasivat miten algoritmisi toimivat Javan tietotyyppien kanssa. Mutta tarkoituksenahan oli pystyä lajittelemaan *koodareita*, ei pelkästään kokonaislukuja tai merkkijonoja, kuten testit tekivät.

Toteutat koodareiden lajitteluun liittyvät asiat tässä osuudessa tehtävää. Kun olet valmis, geneeriset lajittelualgoritmisi osaavat lajitella myös `Coder` -olioita sisältäviä taulukoita luonnolliseen järjestykseen. Näet sitten käyttöliittymässä miten tämä tapahtuu!

Tutustu **ensin** luokkaan `oy.interact.tira.model.Coder` huolellisesti; sitä käytetään kaikissa muissakin kurssin harjoituksissa:

* Miltä luokka yleisesti näyttää; metodit, jäsenmuuttujat, jne.
* Huomaa miten `Coder` toteuttaa rajapinnan `Comparable`.
* Pane merkille kommenteissa esitetty huomautus siitä miten luokan toteutus (comparable, equals) poikkeaa yleisestä normista. Perustelut tälle on selitetty kurssin liveluennoilla (ja sen tallenteella).
* Rajapinnan `Comparable` ainoa metodi on `compareTo`, joka on **toteuttamatta**. 
* Luokassa on myös yhtäsuuruutta vertaileva metodi `equals`.
* Huomaa `@Override` näiden metodien yhteydessä. Ne kertovat kääntäjälle että tarkoitus on nimenomaan **ylikuormittaa** yläluokasta tai rajapinnasta peritty metodi. Tuo `@Override` on silloin hyvä sinne aina laittaa -- jos vahingossa muutat metodin rajapintaa erilaiseksi, kääntäjä ilmoittaa siitä virheellä. Jos nimittäin rajapinta onkin erilainen, **et enää ylikuormitakaan** perittyä metodia, eikä Java kutsu sitä silloin kuin pitäisi! Tämä tietysti johtaa kaikenlaisiin ongelmiin. Käytä siis **aina** tuota `@Override`:a kun tarkoitus on ylikuormittaa peritty metodi.

**Tutustu** `Comparable` -rajapinnan dokumentaatioon (linkki yllä Lähteitä -luvussa). Pohdi mitä se vertailu tarkoittaa kun ei verratakaan kokonaislukuja, yksittäisiä merkkijonoja, vaan `Coder` -olioita?

Huomaa että `Comparable.compareTo()` -metodin tarkoitus on vertailla olioita siten että niiden järjestykseksi tulee ns. luonnollinen järjestys (*natural order*). Mitä se tarkoittaa `Coder` -olioiden suhteen, kun haluamme lajitella koodarit järjestykseen sukunimen ja etunimen mukaisesti?

**Toteuta** `Coder.compareTo()` siten että lajittelu *luonnolliseen* järjestykseen (järjestyksessä sukunimi - etunimi) tapahtuu *oikein*.

Miten koodareiden lajittelu käyttöliittymän lajittelujärjestyslistan valinnasta lähtien oikein tapahtuu?:

1. Käynnistä TIRA Coders sovellus (IDE:n debuggerilla tai komentoriviltä). Pane merkille että koodarit ovat sekalaisessa järjestyksessä. Voit kokeilla avata koodaritiedostoja valikosta ja nähdä että tiedot ovat tosiaan epäjärjestyksessä.
2. Valitse lajittelujärjestys käyttöliittymän listalta.
3. Tämä käyttöliittymän valinta käsitellään `DataControlPanel.actionPerformed()` -metodissa (luokka on packagessa `oy.interact.tira.view`).
4. Tällä saadaan aikaiseksi **lajittelujärjestysolio** `CoderSortOrder`.
5. Lajittelujärjestysolio annetaan lajittelua varten puhelinluettelo-oliolle: `TIRACodersApp.getModel().sort(order);`. Tässä vaiheessa kurssia puhelinluettelo-olio on valmiina annettu toteutus yksinkertaisesta taulukkopohjaisesta tietorakenteesta luokassa `oy.interact.tira.model.PhoneBookArray`.
6. `oy.interact.tira.model.PhoneBookArray.sort()` käynnistää lajittelun pistettyään lajittelujärjestyksen muistiin omaan jäsenmuuttujaansa. 
7. `PhoneBookArray.sort()` kutsuu metodia `currentSortOrder.getComparator();` joka **palauttaa tällä hetkellä aina `null`in**. 
8. Koska `currentSortOrder.getComparator()` palauttaa `null`, `PhoneBookArray.sort`kutsuu `phoneBook.sort()` -algoritmia joka lajittelee tietosäiliön **luonnolliseen järjestykseen** (katso kommentti koodissa alla):

```Java
public class PhoneBookArray extends PhoneBookBase {
...
	public void sort(CoderSortOrder order) {
...
		Comparator<Coder> comparator = currentSortOrder.getComparator();
		if (null != comparator) {
...
		} else {
			System.out.println("Comparators not yet implemented, sorting in natural order by full name");
			long start = System.currentTimeMillis();
			phoneBook.sort();        // <<<<<<<<<<<<<<< Lajitellaan luonnolliseen järjestykseen!
			addMeasurement("Sorting", System.currentTimeMillis() - start);
		}
```
Tuo olio `phoneBook` on luokka joka toteuttaa rajapinnan `TIRAContainer`. Tämä rajapinnan toteuttava luokka on `SimpleContainer` (packagessa `oy.interact.tira.util`). Se on yksinkertainen taulukkoa hyödyntävä tietorakenne, jonka koodin siis olet saanut valmiina. Tai lähes.

**Tutustu** luokkaan `SimpleContainer` ja sen rajapintaluokkaan `TIRAContainer` ja erityisesti rajapintaluokan dokumentaatioon. Toteutusluokka pitää sisällään geneerisen taulukon `private E [] array`, jonne `Coder` -olioita laitetaan. Huomaa että tämä tietorakenne, kuten myös myöhemmin toteutettava tehokkaampi tietorakenne, ei anna lisätä duplikaatteja olioita. Eli jos samaa koodarioliota yritetään lisätä `SimpleContainer`iin uudestaan, jo taulukossa oleva *korvataan* uudella. Onko olio sama vai eri, testataan `.equals` -metodilla.

Tämä taulukko on aluksi tyhjä eli täynnä null -olioita. Kun koodariolioita lisätään taulukkoon, se tapahtuu aina edellisen perään, `null`:n tilalle. Jäsenmuuttuja `count` kertoo montako koodaria taulukossa on ja mikä on siis se indeksi johon seuraava sitten lisätään. Kun taulukon tila loppuu kesken, sitä kasvatetaan, kopioimalla uuteen isompaan taulukkokon entisen pienemmän koodarit. 

Näin taulukossa on aina null -olioitakin, koska taulukkoa kannattaa kasvattaa aina reilusti isommaksi. Uudelleenallokointi on nimittäin **hidasta** koska se vaatii RAM -muistin allokointia ja tietojen siirto vanhan ja uuden taulukon välillä. Huomaat tämän jos yrität ladata suuria puhelinluettelotiedostoja tähän tietorakenteeseen. `SimpleContainer` on toteutettu siten että oikeat (ei-null) oliot ovat aina taulukon alussa, eli indeksien `0..<count` välissä. `null`it ovat sitten lopussa. Tämä helpottaa ja nopeuttaa useita algoritmeja, koska taulukkoa käsitellään aina välillä `0..<count`.

Nyt **täydennät** luokan `SimpleContainer` **toteutusta** siten että se käyttää lajittelussa juuri toteuttamaasi lisäyslajittelualgoritmia! Näin koodarit saadaan käyttöliittymäänkin lajiteltuna!

**Lisää** `SimpleContainer.sort()` -metodiin (jossa ei ole parametreja) **kutsu toteuttamaasi lisäyslajittelualgoritmiin**, antaen oikeat parametrit lajittelualgoritmille. Erityisesti huomioi tässä se, että koko taulukkoa `array` ei voi lajitella lajittelualgoritmilla joka olettaa että taulukossa on vain muuta kuin `null`:eja. Ja että kun on niin, että taulukon ei-null -arvot ovat kaikki välillä `0..<count`, ei ole edes järkeä lajitella myöhemmin olevia `null` -olioita. Eli kutsu oikeaa lajittelualgoritmiasi.

Käynnistä TIRA Coders uudestaan jos se on käynnissä, ja **kokeile** että toteutuksesi toimii, valitsemalla lajittelujärjestys sovelluksen käyttöliittymästä. Huomaa, että se minkä lajittelutavan valitset valikosta, ei vaikuta millään tavalla lajittelujärjestykseen, joka on aina luonnollinen järjestys eli A...Ö. Toteutat vasta seuraavassa tehtävässä eri järjestyksiin lajittelun. 

Varmista siis vain että koodarit tulevat lajitelluksi aina nousevaan järjestykseen. Valikkokomento "Clear phonebook" poistaa kaikki koodarit ja lisää alkuperäiset kolme testikoodaria jotka eivät ole aakkosjärjestyksessä. Voit siis tämän avulla aina kokeilla lajittelua uudestaan.

> Kun lataat nyt uusia koodari -JSON -tiedostoja valikon kautta, huomaat että ne ovat jo valmiiksi oikeassa järjestyksessä. Tämä johtuu siitä, että `PhoneBookArray.loadPhoneBook` joka lataa tiedostoissa olevat koodareiden tiedot tietorakenteeseen, kutsuu loppujen lopuksi `phoneBook.sort()` -metodia jonka olet nyt toteuttanut. Kätevää!

Jos ohjelma ja erityisesti lajittelu ei toimi oikein, korjaa virheet. Jos muutat lajittelu- tai kääntämisalgoritmejasi, muista myös ajaa yksikkötestit aina uudestaan! 

Lataa eri kokoisia pieniä koodaritiedostoja. Varmista että koko lista, ensimmäistä ja viimeistä koodaria myöten, ovat oikeassa nousevassa aakkosjärjestyksessä sukunimen ja sitten etunimen mukaan.

**Huomaa** myös että jos lataat suurimpia tiedostoja joissa on kymmeniä tai satoja tuhansia koodareita, ohjemasi toimii **todella hitaasti**. Esimerkiksi opettajan koneella tiedoston `100000-europe-coders.json` lataaminen muistiin ja lajittelu kesti tällä tietorakenteella ja tässä tehtävässä tehdyllä lajittelualgoritmilla 91,8 sekuntia.

Tämä johtuu siitä että käytämme tässä vaiheessa kurssia vielä *hidasta* tietorakennetta ja *hidasta* lajittelualgoritmia. Nämä asiat korjataan myöhemmissä tehtävissä, nopeammilla tietorakenteilla ja lajittelualgoritmeilla. Nyt meillä on joku vertailupohja mihin yksinkertaisilla asioilla aikatehokkuuden suhteen pääsee -- ja mihin ei.

Jos ohjelmasi ei vaan tunnu saavan hommaa päätökseen, voit lopettaa sen suorituksen kesken halutessasi. Kunhan varmistat testeillä että lajittelu kuitenkin toimii.

Missä sitä `reverse()` -algoritmia nyt sitten tarvittiin? Jos katsot `PhoneBookArray.sort()`:n toteutusta, näet että tässä on tiettyä logiikkaa:

```Java
	if (oldOrder.isReversed(order)) {
		long start = System.currentTimeMillis();
		phoneBook.reverse(); // Not yet implemented, though!
```

Eli jos järjestys on edelliseen järjestykseen verrattuna käänteinen, ei kutsutakaan lajittelualgoritmia vaan sitä joka kääntää järjestyksen toisin päin. Miksi? Kun olet katsonut aikakompleksisuusluennot, **arvioi** mikä on sekä toteuttamasi lajittelualgoritmin aikakompleksisuusluokka, että kääntämisalgoritmin aikakompleksisuusluokka? Tätä koodia tosin *ei vielä tässä vaiheessa harjoitusta kutsuta*, koska nuo järjestysoliot ovat vielä luomatta. Seuraavassa tehtävässä, kun ne toteutetaan, voit sitten tarkastella näiden algoritmien nopeuseroja käytännössäkin.


## Raportti

**Kirjaa** raporttiisi (`RAPORTTI.markdown` joka löytyy projektin juurihakemistosta), **mitä opit** tehtävän tekemisestä, mikä oli vaikeaa, mikä helppoa, jne.

Pohdi raportissasi myös seuraavia asioita:

* Mikä on toteuttamasi lajittelualgortmin aikakompleksisuusluokka?
* Mikä on toteuttamasi reverse -algoritmin aikakompleksisuusluokka?
* Jos taulukko on jo valmiiksi järjestyksessä nousevaan järjestykseen, ja se aiotaan lajitella laskevaan järjestykseen, kannattaako taulukko lajitella vai kääntää sen järjestys? Miksi, perustele?


## Lopuksi

Kun olet valmis, varmista että sekä raportti että kaikki koodi on paikallisessa git -repositoryssäsi ja myös etärepositoryssäsi (komennot `git commit`, tarvittaessa uusille tiedostoille `git add` sekä `git push`).

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.