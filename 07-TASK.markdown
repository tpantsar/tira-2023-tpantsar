# Tehtävä 7

Älä tee tätä tehtävää ennenkuin olet tehnyt [tehtävän 6](06-TASK.markdown).

Tämän harjoituksen aiheena on tietorakenne nimeltään binäärinen hakupuu (binary search tree eli BST). BST on edistynyt tietorakenne, joten huolehdi että olet ensin tutustunut tietorakenteeseen kurssin luentojen kautta sekä tarvittaessa perehtymällä tähän tietorakenteeseen liittyvään kirjallisuuteen.

Tähän mennessä TIRA Codersin käyttämä tietorakenne on pohjautunut taulukoihin. Olet käyttänyt tehtävästä 1 alkaen `PhoneBookArray` -luokkaa ja sen sisältämää `SimpleContainer` -luokkaa joka sisältää geneerisen taulukon. Tämä taulukko on käytännössä sisältänyt `Coder` -olioita. Toteuttamasi lajittelu- ja hakualgoritmit ovat käyttäneet tätä taulukkoa.

Taulukot ja silmukat vaikuttavat aikatehokuuteen. Jos vaikka etsimme taulukosta lineaarisesti tietoelementtiä, operaatio on O(n). Tai jos haluamme lisätä järjestettyyn taulukkoon uuden elementin ja se sijoittuu vaikka taulukon keskelle, haku voidaan tehdä kyllä O(log n) ajassa (puolitushaulla), mutta kun uusi elementti lisätään taulukon keskelle, kaikki sen jälkeen tulevat elementit pitää siirtää askel taulukossa eteenpäin. Tai sitten taulukko pitää lajitella. Tapauksesta riippuen tämä voi olla O(0) tai sitten pahimmillaan O(n) tai O(n log n). Mahdollisesti pitää vielä tehdä taulukon uudelleenallokointi jos tila loppuu kesken.

**Binääriset hakupuut** ja **hajautustaulut** (hash table, seuraavan harjoituksen aihe) pyrkivät molemmat välttämään tavallisten taulukoiden haittapuolet. Näin pyritään tietorakenteisiin joiden käyttäminen (elementtien lisääminen, haku, poistaminen jne) on nopeampaa kuin tavallisten taulukoiden kanssa. Esimerkiksi BST kun ei käytä taulukoita, mitään reallokointia ei tarvitse tehdä, ja vain käytettävissä oleva muisti on rajana sille kuinka monta oliota BST:hen voi lisätä.

> Koska Javassa taulukoiden indeksointi tehdään `int` -tietotyypillä joka on Javassa etumerkillinen 32-bittinen kokonaisluku (*signed 32 bit integer*), suurin indeksin arvo on siis 2^31-1 = 2 147 483 647. Useinmiten tämä riittää vallan mainiosti. Mutta jos taas ajatellaan *suuria* tietomääriä, tämä tarkoittaa sitä että Javan taulukoissa *ei voi enempää olioita pitää*. Määrä on aika suuri ja useinmiten JVM:stä loppuu muisti ennenkuin tuo määrä olioita tulee lisättyä taulukkon. Mutta jos ajatellaan jotain suurkonetta jossa ajetaan todella suuria data-analyysejä tai vastaavaa, silloin tietomäärät voivat olla isompiakin.
>
> Jos taulukot ei riitä, voidaan käyttää esimerkiksi binäärisiä hakupuita joita taulukoiden rajoitukset eivät koske. Toinen vaihtoehto on käyttää *useampia* taulukoita ja itse hoitaa indeksoinnit ja taulukoiden käsittelyalgoritmit siten että nämä useammat taulukot näyttävät ohjelmoijalle yhdeltä isolta taulukolta, joka indeksoidaan vaikkapa `long` -tietotyypin indekseillä.

Useissa ohjelmointikielissä löytyy tietorakenteita avain-arvoparien (*key-value pair*) ylläpitämiseen. Avain-arvopareilla voidaan tallentaa ja etsiä nopeasti tietoelementtejä, ja välttää siten taulukoiden käytössä tarvittavat silmukat. Usein nämä tietorakenteet on toteutettu jonkinlaisella puutietorakenteella tai hajautustaululla, tai näiden yhdistelmillä.

Esimerkkinä näistä tietorakenteista on vaikkapa [Swift -ohjelmointikielen Dictionary](https://developer.apple.com/documentation/swift/dictionary). Monissa ohjelmointikielissä vastaavaa tietorakennetta kutsutaan nimellä "map" koska se "mäppää" jonkun avaintiedon (key) johonkin arvoon (value). Esimerkkeinä [Javan Map -rajapinta ja sen toteutukset](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html) ja C++:n [std::map](https://en.cppreference.com/w/cpp/container/map) ja [std::unordered_map](https://en.cppreference.com/w/cpp/container/unordered_map).

Tässä harjoituksessa siirrytään hyödyntämään binääristä hakupuuta tietorakenteena, taulukon sijaan. Binäärinen hakupuu on tällainen avain-arvopareja sisältävä tietosäiliö joka rakentuu puun idealle.

Toteutat aluksi BST:n ja sen aputietorakenteen (puun solmu, tree node). Testaat sen toimintaa yksikkötesteillä ja analysoit toteutusta ja sen aikatehokkuutta, raportoiden tulokset. Sen jälkeen integroit toteutuksen TIRA Coders -sovellukseen. Voit sitten testata BST:n toiminnallisuutta vaihtamalla oletuksena olevan tietorakenteen TIRA Coders -sovelluksen valikosta taulukkopohjaisesta binääripuupohjaiseen toteutukseen.

Koodirivien määrä joka tässä tehtävässä kirjoitetaan, on noin 300 plus miinus jotain. Tässä tehtävässä asioita voi toteuttaa eri tavoin, ja toteutuksen valinnat vaikuttavat koodirivien määrään. Tehtävässä on myös useita valinnaisia (ei-pakollisia) asioita jotka kasvattavat koodin määrää, jos niitä haluat toteuttaa.

Valinnaiset tehtävät ovat siitäkin hyviä, että jos kurssin pisteet eivät muuten riitä kurssilta läpipääsyyn, valinnaiset tehtävät ovat tässä avuksi. Jos arvosanatavoitteesi on korkealla, kannattaa tehdä näitä valinnaisiä tehtäviä.


## Lähteitä

* Kurssin luentokalvot.
* Liveluento (tallenne) ja sen vinkit ja esimerkit.
* Kirjallisuus.
* `oy.interact.tira.util.TIRAKeyedOrderedContainer` -rajapinta (interface), joka sisältää myös `TIRAKeyedContainer` -rajapinnan.
* `oy.interact.tira.util.TIRAKeyedContainer` -rajapinta.
* `oy.interact.tira.factories.BSTFactory`, tehdasluokka joka instantioi toteutuksesi BST:stä, kunhan se on valmis.
* `oy.interact.tira.model.PhoneBookBST`, koodaripuhelinluettelo joka käyttää taulukon sijaan BST:tä.
* [Visitor](https://en.wikipedia.org/wiki/Visitor_pattern) -suunnittelumalli (design pattern) jolla voidaan lisätä toiminnallisuutta puuta läpikäytäessä.


## Tavoite

Toteuta ja testaa BST aputietorakenteineen ja algoritmeineen siten että BST toteuttaa `TIRAKeyedOrderedContainer` -rajapinnan. Testaa toteutusta tarvittaessa sitä korjaten siten, että toteutus läpäisee testit. Kun testit menevät läpi, voit kokeilla BST:n toimintaa TIRA Coders -sovelluksessa. Vertaa sen toiminnallisuutta ja aikatehokkuutta tähän asti käyttämääsi taulukkopohjaiseen ratkaisuun.

TIRA Coder -sovelluksessa luokka `PhoneBookBST` käyttää tietosäiliönään `TIRAKeyedOrderedContainer` -toteutusta.

Analysoi raportissasi toteutuksesi oikeellisuutta ja aikatehokkuutta.

Liveluennot (ja niiden tallenteet) sisältävät demoja siitä miten tämä tietorakenne koodataan, joten niistä on varmasti apua tehtävän tekemisessä. Tutustu niihin.

> Huomaa että puolitushaku eli *binary search* on ihan eri asia kuin binäärinen hakupuu. Älä siis vahingossakaan ala tässä toteuttamaan tai käyttämään puolitushakua. Puolitushakua käytetään taulukoiden kanssa, kun BST:n toteutuksessa ei käytetä taulukoita.

> Huomaa myös että tässä(kään) tehtävässä **ei saa käyttää** Javan tietosäiliöluokkia (`Collection` ja `Map` -rajapintojen toteutukset) tai algoritmeja luokissa `Arrays` ja `Collections`. Kaikki tietorakenteet ja algoritmit *toteutetaan itse* käyttäen Javan tavallisia taulukoita ja omia luokkia sekä Javan perustietotyyppejä int, Integer, String, ja niin edelleen. 

> Jos olit mukana kurssilla viime tai toissa vuonna, siellä BST:n yhteydessä käytettiin **hajautusfunktiota** (hash). Huomaa että tänä vuonna ne tulevat käyttöön vasta seuraavassa harjoituksessa. **Älä siis yritä** hyödyntää `hashCode()` -metodia toteuttaessasi BST:tä ja sen aputietorakennetta.


## Sananen järjestyksestä 

Sana "ordered" rajapinnan nimessä tarkoittaa sitä, että elementeillä tietorakenteessa on järjestys. Voidaan siis sanoa että joku elementti on puussa ensimmäinen, joku on toinen, jne. Tässä järjestys tarkoittaa nimenomaan in-order -järjestyksen mukaista järjestystä. Katso tarvittaessa tarkemmin luentomateriaalista mitä tämä tarkoittaa.

Alla kuva tilanteesta jossa sovellus on käynnistetty ja tietorakenteeksi on valittu BST:

![Esimerkki sovelluksen käyttöliittymästä](task-07-example.png)

Huomaa järjestys: 1. Bacon 2. Juustila 3. Lappalainen. In-order -järjestys tarkoittaa sitä että puussa elementit sijoittuvat näin:

![Puutietorakenne jossa koodarit ovat](task-07-example-tree.png)

Eli **ensimmäinen** elementti on **alin vasemmanpuoleisin solmu** (Bacon) puussa. Seuraava on tämän *parent* (Juustila), ja sitä seuraava tämän parentin **oikeanpuoleinen** lapsi (Lappalainen). Tietysti puussa voi olla paikkoja joissa tietyllä solmulla ei ole yhtään lasta, tai on vain vasen tai oikea lapsi, joten tilanteet vaihtelevat. Joka tapauksessa, järjestys tarkoittaa tässä **in-order** -läpikäyntijärjestystä. Tämän ansiosta koodarit voidaan esittää oikeassa järjestyksessä käyttöliittymässä -- ne listataan aina in-order -järjestyksessä! 

Tämä on tärkeä tietää kun toteutat esimerkiksi metodia `getIndex(int index)` joka hakee vaikkapa viidennen elementin puusta -- in-order -järjestyksessä! Tätä metodia kutsutaan kun käyttäjä klikkaa jotain riviä koodarilistalla. Klikkaus kertoo monennettako riviä klikattiin, ja puusta haetaan sitten oikea koodari kutsumalla BST:n `getIndex(int index)` -metodia ja koodarin tiedot voidaan sitten näyttää käyttöliittymässä.

Katso myös tämän ohjeen lopusta loppusanat järjestyksestä.


## BST:n toteutus - vaihtoehtoja

BST:n tulee toteuttaa annettu `TIRAKeyedOrderedContainer` -rajapinta siten että tietorakenne toteuttaa binäärisen hakupuun. Lue tarkkaan ennen toteutuksen aloittamista kyseisen rajapintaluokan dokumentaatio (lähdekoodin kommentit).

Koska tämä rajapinta laajentaa toista annettua rajapintaa, `TIRAKeyedContainer` se sisältää myös tämän rajapinnan esittelemät metodit, joten tutustu myös tarkkaan tämän rajapinnan koodin kommentteihin. Jos et ymmärrä jotain, kysy opettajilta.

Tehtävässä on myös **täysin valinnaisia** toteutettavia asioita, joista saa lisäpisteitä jos haluat paremman arvosanan tai pakollisten tehtävien pisteet eivät riitä kurssilta läpipääsyyn. Katso näistä lisää tämän ohjeen lopusta. Esimerkiksi olioiden poistaminen (`remove`) puusta on *valinnainen* tehtävä. Lue siis tämä ohje tarkkaan.

**HUOM:** seuraavien työssä *pakollisten* algoritmien toteuttaminen voidaan tehdä useammalla **vaihtoehtoisella** tavalla:

1. `Pair<K,V> getIndex(int index)` -- haetaan tietyssä indeksissä oleva avain-arvo -pari.
2. `int indexOf(K key)` -- haetaan tietyn avaimen indeksi eli järjestysnumero puussa.
3. `int findIndex(Predicate<V> searcher)` -- haetaan predikaatin mukaisen hakukriteerin täyttävä arvon indeksi eli järjestysnumero puusta.
4. `V find(Predicate<V> searcher)` -- haetaan predikaatin mukaisen hakukriteerin täyttävä arvo puusta.
5. `Pair<K, V>[] toArray()` -- käydään puu läpi in-order ja palautetaan taulukko jossa on kaikki puussa olevat avain-arvoparit omassa `Pair` -oliossaan.

Vaihtoehdot ovat:

**A** *Kaikki ym. metodit*: voidaan toteuttaa in-order -läpikäynnillä **rekursiivisesti** puun solmuja navigoiden, pitäen kirjaa (esimerkiksi puun solmu -olion metodien parametrina) monennessako puun solmussa ollaan menossa, ja käyttää tätä tietoa palauttamaan oikea key-value -pari tai indeksi. Aikakompleksisuus on O(n).

**B** *Kaikki ym. metodit*: voidaan toteuttaa rekursion sijaan **iteratiivisesti** pinotietorakenteen avulla. Myös tästä esitellään liveluennolla demo. Aikakompleksisuus tässäkin on O(n).

**C** *Kaikki ym. metodit*: voidaan toteuttaa myös **Visitor** -suunnittelumallilla, jossa visitor eli vierailija navigoi puussa ja pitää kirjaa navigointinsa tilasta. Esimerkiksi laskien monennessako solmussa ollaan ja lopetetaan sitten puussa navigointi kun ollaan oikeassa indeksissä tai löydettiin oikea olio. Visitor -luokka voi pitää sisällään myös puusta haetun tiedon (indeksi, avain-arvopari, tms.). Visitor on monipuolinen ja joustava tapa toteuttaa muitakin algoritmeja puussa, esim. metodit `find` ja `findIndex`. Projektissa on valmiina `Visitor` ja `Visitable` -rajapinnat joita voit hyödyntää toteutuksessa. Visitorinkin toteutuksesta on demo liveluennolla. Tämänkin aikakompleksisuusluokka on O(n).

Visitorin käyttö esimerkiksi `toArray()`:n toteutuksessa näyttäisi tältä (tämä koodi sijaitsee `TIRAKeyedOrderedContainer` -rajapinnan toteuttavassa luokassa, jossa `root` on ylin puun solmu eli node):

```Java
	public Pair<K, V>[] toArray() throws Exception {
		// No root, so return an empty array.
		if (null == root) {
			return (Pair<K, V>[]) new Pair[0];
		}
		// Create a visitor that visits all tree nodes in-order.
		// Visitor creates an array that can hold `size` elements.
		BSTToArrayVisitor<K, V> visitor = new BSTToArrayVisitor<>(size);
		// Ask the root node to accept the visitor to visit it (and all subtrees).
		root.accept(visitor);
		// After the visitation, return the array from the visitor, 
		// having all the key-value pairs from the tree.
		return visitor.getArray();
	}
```
Eli (tässä yksityiskohtiin menemättä): 

1. Algoritmi testaa onko juurisolmua olemassa. 
2. Jos ei ole, palautetaan tyhjä taulukko. 
3. Jos taas on, luodaan vierailija joka luo jäsenmuuttujakseen taulukon jonka koko on `size`. 
4. Sen jälkeen pyydetään juurisolmu hyväksymään vierailija (`accept`). 
5. Juurisolmu kutsuu sitten `accept`:n toteutuksessa vierailijan metodia `visit(this)`, antaen itsensä vierailijalle parametrina.
6. Vierailija vierailee in-order -järjestyksessä solmun vasemman ja/tai oikean alisolmun (kutsuen näiden `accept` -metodia, jolloin solmu taas kutsuu visitorin metodia `visit(this)`, ja siinä alisolmujen vierailujen *välissä* tässä tapauksessa laittaa solmun sisältämän avain-arvoparin omaan taulukkoonsa. Vierailija pitää myös kirjaa mihin indeksiin pari on laitettu, ja kasvattaa jäsenmuuttujana olevaa indeksiään jotta seuraavan solmun avainarvopari menee seuraavaan indeksiin. 
7. Kun vierailija on käynyt koko puun läpi, ohjelman suoritus palaa `root.accept` -metodista ja voidaan sitten palauttaa vierailijassa oleva taulukko `Pair` -olioita kutsujalle.

Tehdään siis eri tarkoituksiin erilaisia visitoreita jotka pitävät sitten jäsenmuuttujissaan tallessa tietoa joka halutaan puun läpikäynnistä talteen kutsujalle.

**D** *Metodit 1 ja 2* (muut tavalla A-C): voidaan toteuttaa hyödyntäen tietoa siitä **kuinka monta lapsisolmua** kullakin puun solmulla on. Tämän tavan aikakompleksisuusluokka on O(log n), eli **huomattavan paljon nopeampi** kuin muut yllämainitut tavat. Muistanet että esimerkiksi jos n on 5 000 000, O(n) tarkoittaa viittä miljoonaa operaatiota, mutta O(log n) on 23 operaatiota! Nopeusero on siis huomattava, isoilla aineistoilla!

Katso kuvaa alla:

![Esimerkkipuu](task-07-example-tree-2.png)

Huomaa puun solmujen yhteydessä näkyvät numerot. Numero kertoo, kuinka monta *lapsisolmua* puun kullakin solmulla on. Jos pidät kirjaa puuhun solmuja *lisätessä* (ja *poistaessa*, jos sen algoritmin toteutat) lasten lukumäärästä, voit hyödyntää tätä metodien `getIndex()` ja `indexOf()` toteutuksessa. 

Ajatellaampa esimerkiksi että puusta on haettava indeksissä 22 oleva olio. Koska puun juuresta nähdään että juurisolmun vasemman lapsisolmun lasten lukumäärä on 19, se tarkoittaa sitä että juuren vasemmalla ovat kaikki indekseissä 0...19 olevat lapset. Vasenta haaraa on siis turha lähteä tässä esimerkissä läpikäymään. Lähdetään siis heti juurisolmun oikeaan lapseen ja *säästetään se aika joka menisi puun vasemman haaran läpikäyntiin*. 

Sekin tiedetään että juurisolmun oikeanpuoleisen lapsen indeksi on 21, koska juuren vasemmassa on 0...19, juurisolmu itse on sitten indeksissä 20, joten oikean lapsen indekxi on 21. Eli hyödyntämällä tietoa lapsisolmujen lukumääristä voidaan paljon nopeammin löytää se oikeassa indeksissä oleva koodari. Tätä samaa ideaa jatketaan koko ajan puuta navigoitaessa, muistaen edellisten "tasojen" indeksit. Tämä algoritmi voidaan toteuttaa ei-rekursiivisesti silmukalla.

**Opettajan molempien toteutuksien nopeustesti** osoittaa että **in-order -läpikäynti on merkittävästi hitaampi** kuin **D** -toteutus, isoilla aineistoilla (taulukko alla). In-order -läpikäynti hidastaa *myös käyttöliittymää*, sillä Javan Swing:n taulukkonäkymä toimii siten että kun käyttäjä selaa listaa koodareista, se hakee aina näkyvälle listalle mahtuvat koodarit juuri tällä `getIndex(int index)` -metodillamme. Jos aineisto on suuri ja algoritmi on hidas, tämä näkyy käyttöliittymässä tökkimisenä.

Opettajan testin mukaan hitaammalla in-order -toteutuksella koodareiden selaaminen oli mahdotonta 1 000 000 koodarin aineistolla, kun taas toteutustavalla **D** selaaminen oli täysin sujuvaa miljoonan (ja kahden miljoonan) koodarin aineistollakin. Toteutuksella **D** 100 000 koodarin hakeminen yksi kerrallaan indeksin perusteella BST:stä kesti *yhteensä* 10 ms kun taas in-order -totetutus käytti siihen 40 (!) sekuntia. 

Jos toteutat siis jonkun näistä in-order -läpikäynneistä (A...C), huomaat sekä testien että TIRA Codersin käyttöliittymän hidastuvan merkittävästi aineiston koon kasvaessa isommaksi. Käytännössä alla olevan taulukon perusteella, raja on jossain 10 000 ja 50 000 elementin välillä, ainakin opettajan koneella.

> Huomaa myös että näillä isoilla aineistoilla sinun on todennäköisesti kasvatettava JVM:lle varattavaa kekomuistin määrää; muuten isot koodaritiedostot eivät mahdu muistiin! Tästä lisää [NOT-ENOUGH-MEMORY.markdown](NOT-ENOUGH-MEMORY.markdown) -ohjeessa. Esimerkiksi opettajan testeissä kahden miljoonan koodaritiedoston lataaminen vaati JVM:n heap -muistin määräksi 32Gt. Koneessasi ei tarvitse olla tätä määrää fyysistä RAM -muistia, koska käyttöjärjestelmän virtuaalimuisti huolehtii asioista (yleensä).

Taulukko: in-order ja lapsisolmujen lukumäärää hyödyntävien toteutusten nopeusero.

| N         | in-order getIndex(int index) koko aineistolle (ms) | D -toteutus getIndex(int index) koko aineistolle (ms) |
|-----------|----------------------------------------------------|-------------------------------------------------------|
| 100       | 0                                                  | 0                                                     |
| 1 000     | 4                                                  | 1                                                     |
| 5 000     | 37                                                 | 0                                                     |
| 10 000    | 130                                                | 1                                                     |
| 50 000    | 9488                                               | 5                                                     |
| 100 000   | 41777                                              | 10                                                    |
| 1 000 000 | ei suoritettu hitauden vuoksi                      | 157                                                   |


Vaihtoehdon **D** toteutus on kuitenkin aika haastava saada toimimaan oikein. Suosittelen ensin toteuttamaan tavallinen in-order -läpikäynti (vaikka Visitoreilla), ja jos haluat lisähaasteita, sitten kokeile miten saat tällaisen algoritmin **D** toimimaan. Koska tämä tapa on haasteellinen ja O(n) -algoritmit kelpaavat kurssisuoritukseksi, tästä saa sitten lisäpisteitä kurssin arvosteluun!


## Toteutuksen askeleet

**Luo uusi luokka** packageen `oy.interact.tira.student`, nimeltään vaikkapa `BinarySearchTreeContainer` tai vastaavaa:

```Java
public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {
```

Kuten yltä näet, luokan on toteutettava `TIRAKeyedOrderedContainer` -rajapinta, ja se sisältää avain-arvo -pareja (K eli key ja V eli value). Näistä avaimeen on lisäksi liitetty vaatimus että se toteuttaa `Comparable` -rajapinnan.

Tämä BST:n toteutuksen tulee olla geneerinen ja toimia minkä tahansa tietotyyppien kanssa, mutta TIRA Coder -sovelluksessa tuo Key tulee olemaan luokka `String` ja Value tulee olemaan `Coder`. Tietosäiliön "hakuavain" on siis merkkijono. 

Tämä merkkijono key on käytännössä `Coder` -olion eli koodarin oikea nimi *silloin kun lajittelujärjestys on nimen mukaan*. Kun taas lajittelujärjestys on koodarin lempinimi ("coder name"), silloin tuo avain on tämä merkkijono, koodarin lempparinimi. Avain siis määrittelee *järjestyksen* jossa koodarit ovat BST:ssä.

Taulukkopohjaisessa toteutuksessa kun lajittelujärjestys muuttui, käytettiin lajittelualgoritmia (quicksort, heapsort,...) taulukon järjestyksen muuttamiseksi. BST:n tapauksessa luodaan uusi puu *eri `Comparator` -toteutuksella*, johon koodarit vanhasta puusta siirretään.

Koska useammalla koodarilla voi olla sama nimi (näinhän todellisuudessakin on) tai lempinimi, BST:n pitää tapauksessamme antaa lisätä *duplikaatteja* avainarvoja puuhun. Näinhän kaikki tämän kaltaiset tietosäiliöt eivät aina toimi. Esimerkiksi C++:n `map`, `multimap` ja `set` vaativat että avain on aina uniikki. Sen sijaan [std::multiset](https://en.cppreference.com/w/cpp/container/multiset) sallii useita avaimia samalla arvolla. Tämä pätee myös omaan toteutukseemme. Javan standardikirjaston `Map` -rajapinta ja sen toteutukset eivät salli samoja avainten arvoja. Tämä voidaan kiertää siten että value ei olekaan yksittäinen olio vaan vaikkapa taulukko tai lista arvoja. Vaihtoehtoisesti voi käyttää vaikka [Apache commons -kirjaston MultiMap](https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/MultiMap.html) -luokkaa. Toki tässä et sitä käytä vaan toteutat tällaisen rakenteen itse BST:nä.

Joskus kuitenkin on tarve etsiä joku *tietty* koodari kaikkien saman nimisten koodareiden joukosta. Tämä voidaan tehdä käyttämällä hyväksi sitä että jokaisella koodarilla on uniikki `identiteetti`. Kun tutustut tarkemmin `Coder` -luokkaan, huomaat että sillä on ominaisuus `id`. Tämä id on uniikki `UUID`, satunnaisesti generoitu koodi joka on globaalisti uniikki -- on todella, todella harvinaista että olisi olemassa kaksi samaa UUID -arvoa eri koodareilla.

Koska BST:n lajittelujärjestys voi vaihtua, puun on tiedettävä miten puuhun laitettavia avaimia vertaillaan jotta tiedetään kuuluuko joku uusi avain jonkun alipuun vasempaan vai oikeaan haaraan. Siksi luokalla on oltava muodostin joka antaa BST:lle tämän `Comparator` -olion jolla vertailu tehdään:

```Java
	TreeNode<K, V> root;  // Root node of the tree, your private little helper class.
	int size;				 // Number of elements currently in the tree.

	private Comparator<K> comparator;  // The comparator used to determine if new node will go to left or right subtree.

	public BinarySearchTreeContainer(Comparator<K> comparator) {
		this.comparator = comparator;
	}
```

Vastaavasti kun BST sisältää tuon puun solmun juurioliona (`TreeNode`), `comparator` pitää antaa myös node -olioille muodostimen parametrina. Näin node pystyy käyttämään samaa vertailijaa kun puun solmuihin lisätään rekursiivisesti alisolmuja, tai puun solmuista haetaan rekursiivisesti jotain tiettyä solmua.

> Jos et käytä ollenkaan rekursiota puun solmujen kanssa, tätä vertailijaa ei tarvita puun solmussa. Iteratiivisessa toteutuksessa riittää että vertailija on BST-luokassa jäsenmuuttujana. Rekursiivinen läpikäynti on kuitenkin usein se yksinkertaisin toteutustapa.

Tämän jälkeen voit toteuttaa kaikki rajapinnoista perityt metodit, rajapintojen koodin kommenttien ja kurssin teorian perusteella. Huomaa että BST:tä on aika vaikeaa testata ennenkuin kaikki metodit on toteutettu.

Kun olet saanut BST:n toteutettua, ennen sen testaamista ja käyttöä toteuta vielä tehdasmetodi `oy.interact.tira.factories.BSTFactory.createBST(Comparator<K>)`.

> Tehdasluokka `BSTFactory` sisältää myös kaksi metodia joilla voidaan luoda valinnaisten tehtävien vierailijoita (visitor). Jos et toteuta näitä vierailijoita, jätä koodi sellaiseksi kuin se on. Valinnaisista tehtävistä lisää alempana.

Kun olet valmis, voit testata toteutuksesi oikeellisuutta kuten alempana luvussa BST:n testaaminen on kuvattu.


## BST:n testaaminen

Testaa ensin BST -toteutustasi testillä `GenericTests`. Se testaa BST:n toimintaa kutsumalla sen useimpia metodeja ja varmistamalla oikeellisuuden.

Testi `SimpleContainerPerformanceTests` testaa taulukkopohjaista toteutusta BST:n sijaan. Tämä testi on olemassa aikatehokkuuden *vertailuja* varten. Se kutsuu samankaltaisia metodeja, tallentaa näiden suoritusaikoja samanlaiseen taulukkoon kuin BST:tä testaavat testit. Voit siis tällä testillä saada vertailtavaa aineistoa BST:n testaamista ajatellen. *Huomaa* kuitenkin että varsinkin suurilla aineistoilla tämä testi on hidas. Kannattaa siis jättää testi pyörimään itsekseen ja palata myöhemmin katsomaan joko testi loppui. Jos koneesi on hidas, voit keskeyttää testien suorittamisen testien kestettyä liian pitkään (kymmeniä minuutteja tai tunteja).

Kopioi tämän testin tulostamat aikamittaukset tiedostosta `compare-simplecontainer.csv` johonkin taulukkolaskinsovellukseen. Tiedostossa jokaisen aineiston mittaukset ovat yhdellä rivillä, ja pilkulla erotetut sarakkeet sisältävät mittausaikoja eri toiminnallisuuksista.

Varsinainen BST:n toteutusta testaava testi on `BSTPerformanceTests`. Se tallentaa suoritusaikamittaukset tiedostoon `compare-bst.csv`. 

Alla yhden (n = 50 000) aineiston vertailuesimerkki sekä BST:n että taulukkopohjaisen suorituksen aikamittauksista (näkyy parhaiten markdown -tiedoston Preview -moodissa).

| Elements (n) | Add time | Add time/item | To sorted array time | Search time | Search time/item | get(index) time | get(index) time/item | Testfile | 
|--|--|--|--|--|--|--|--|--|
| BST:      50000 | 26 | 0.0005 | 2 | 29 | 0.0006 | 4 | 0.0001 | 50000-country-coders.json |
| Taulukko: 50000 | 10043 | 0.2009 | 34 | 31 | 0.0006 | 4 | 0.0001 | 50000-country-coders.json |

Tarkoituksena testeillä on paitsi varmistaa tietorakenteen toteutuksen *oikeellisuus*, myös aikamittauksia analysoimalla ja vertailemalla suoritusaikojen kasvua aineiston koon kasvuun (n) sekä myös taulukkopohjaiseen toteutukseen. Tutki onko oma BST:n toteutuksesi tarpeeksi nopea suhteessa siihen miten BST:n pitäisi käyttäytyä. Se miten BST:n pitäisi käyttäytyä ja mikä on sen eri metodien aikakompleksisuusluokka (ja siten nopeus) pitäisi selvitä kurssin luentomateriaalista ja kirjallisuudesta.

Muista raportoida havaintosi sekä analyysisi ja liittää näitä suoritusaikamittaukset myös raporttiisi.

## BST TIRA Codersissa

Tähän asti TIRA Coders on käyttänyt koodaripuhelinluettelona luokkaa `PhoneBookArray`. Kun puhelinluetteloksi vaihdetaan käyttöliittymässä BST (kts. ohjeet alla), puhelinluetteloksi vaihtuu `PhoneBookBST`. Tämä luo `BSTFactory.createBST(Comparator<K>)` -metodilla toteutuksesi BST:stä ja käyttää sitä ja sen algoritmeja jotka toteutit.

**Tutustu** luokan `PhoneBookBST` toteutukseen. Et muuta sen toteutusta, mutta on hyvä tietää miten se "istuu" käyttöliittymän ja oman BST-toteutuksesi välissä ja ohjaa toimintaa omalta osaltaan. Oma BST -toteutuksesi tässä luokassa on jäsenmuuttujassa nimeltään `phoneBook`. 

Kun olet testannut oikeellisuutta ja aikatehokkuutta, kokeile myös miten toteutuksesi toimii TIRA Codersin käyttöliittymän kautta:

1. Käynnistä sovellus.
2. Valitse valikosta **tietorakenteeksi BST**.
3. Lataa pieniä ja isoja coders-tiedostoja testitiedostoista.
4. Kokeile valita listalta koodari ja katso että oikean koodarin tiedot tulevat näkyville oikeaan laitaan.
5. Kun valitset koodarin, tarkista että koodarin kaverit tulevat oikein näkyville listalle oikeassa laidassa.
6. Kokeile **osittaista** ja **tarkkaa** hakua, etsien jotain tiettyä koodaria. Varmista että oikea koodari tulee löydettyä.

Varsinkin **isojen** aineistojen kanssa, **katso** miten sovelluksen loki-ikkunassa (ja konsolissa) tulostuu **kauanko missäkin operaatiossa kesti**. Kestikö missään operaatiossa yli 200 ms? Miten se vaikuttaa sovelluksen käyttäjäkokemukseen? Raportoi myös näistä havainnoista.

Kokeile myös vaihtaa koodareiden lajittelujärjestystä. Kuinka nopeasti tämä tapahtuu?


## Loppusanat järjestyksestä

Vaikka BST on hyvä ja nopea tietorakenne, BST:ssä on omat hankalatkin puolensa. Esimerkkinä tämä järjestys, joka tulee hyvin esille tämän tyyppisessä sovelluksessa kuin mitä TIRA Coders on. 

Ajatellaan vaikka lajittelua koodarin nimen mukaiseen järjestykseen (nousevassa järjestyksessä). Silloin koodarit siis laitetaan puuhun siten että vasemmalla alimmassa solmussa on koodari joka lajiteltaisiin nimen mukaisesti ensimmäiseksi. Viimeisenä on koodari jonka nimi aakkosissa on ihan lopussa, siellä ihan puun oikeanpuoleisimmassa alimmassa solmussa.

Entäs jos haluamme vaihtaa järjestyksen toiseksi, koodarin nimen mukaan *laskevaan* järjestykseen? Silloin puu on *luotava uudestaan*, uudella järjestyksellä. Vanhasta puusta lisätään sen jälkeen elementit uuteen puuhun. Operaatio sinänsä on suhteellisen nopea (mikä on sen aikakompleksisuusluokka aineistolle jonka koko on n?!). 

Yksi tapa siirtää elementit puusta toiseen, on ensin muodostaa BST:stä taulukko, jossa elementit ovat. Tähän on olemassa oman toteutuksemme `toArray()` -metodi. Kutsutaan sitä ja lisätään saadusta taulukosta sitten elementit uuteen puuhun jossa järjestys on päinvastainen. Toinen tapa on tehdä visitor joka navigoi puun läpi in-order ja sisältää uuden BST -olion johon vanhan puun solmut lisätään navigoitaessa vanhaa puuta.

**Asiassa on vaan yksi mutta**. Kun puuhun lisätään elementtejä jotka ovat lähtötaulukossa tai -puussa *jo valmiiksi järjestyksessä* (*tai käänteisessä* järjestyksessä), elementit menevät uuteen puuhun aina *samaan* vasempaan (tai oikeaan) haaraan. Näin puusta tulee käytännössä **linkitetty lista**. Luentomateriaalissa tällaista erittäin huonosti tasapainossa olevaan puuta nimettiin termillä *degenerate* eli epämuodostunut. 

 Tämä siksi, että linkitetty lista on tietorakenteena erittäin hidas, varsinkin puurakenteeseen verrattuna. Tämän huomaat kun debuggaat tehtävän testiä `GenericTests.testBSTAsLinkedList()`. Testi lisää jo valmiiksi järjestyksessä olevaa aineistoa puuhun. Kun katsot debuggerista, huomaat että aina jokaisen solmun *toinen* (joko vasen tai oikea) on koko puussa aina ei-null ja toinen on taas null. Linkitetty lista, siis.

 > Tästä siis ihan yleensäkin BST:n käyttöön liittyvä ohje: jos puuhun lisättävä aineisto on jo valmiiksi puun järjestyksen mukainen, se on **aika huono asia** puun tasapainon kannalta.

Emme voi siis yksinkertaisesti vain luoda uutta puuta, ja lisätä vanhasta järjestyksessä niitä elementtejä vanhasta puusta uuteen:

* Joko elementit on saatava puusta *satunnaisessa* järjestyksessä, tai
* uuden puun järjestys ei saa olla vanhan järjestyksen käänteinen järjestys.

Tästä syystä `PhoneBookBST.sort(CoderSortOrder order)` metodin toteutus (tutustu siihen!) on sitä mitä se on -- se ottaa vanhasta puusta elementit taulukkoon, ja jos uusi järjestys on *käänteinen* entiseen verrattuna, taulukko **sekoitetaan** (*shuffle*) satunnaiseen järjestykseen ja vasta sitten elementit lisätään uuteen puuhun. Tätä tehtävää koodatessa opettajalla meni pieni hetki ennenkuin hän ymmärsi, miksi lajittelunjärjestyksen vaihtaminen päinvastaiseksi teki yhtäkkiä tästä nopeasta tietorakenteesta erittäin hitaan...


## Raportti

**Kirjaa** raporttiisi `RAPORTTI.markdown` mitä opit tehtävän tekemisestä, mikä oli vaikeaa, mikä helppoa, jne.

Analysoi toteutuksesi oikeellisuutta ja aikatehokkuutta huomioiden seuraavat asiat:

* Kurssin luennot ja kirjallisuus BST:stä.
* Yksikkötestit ja niiden mittaukset.
* Vertailut taulukkopohjaiseen tietorakenteeseen, sen aikatehokkuusmittaukset.
* Havaintosi TIRA Codersin käyttöliittymästä.

Peilaa havaintojasi teoriaan. Esimerkiksi, mikä olisi BST:n syvyys milläkin testiaineistolla, jos puu saataisiin täytettyä tasapainoiseksi (balanced)? Mikä oli **sinun** toteutuksellasi puun **maksimisyvyys** (syvimmän haaran syvyys/korkeus) kullakin eri kokoisella aineistolla? Puun syyvyttä voi analysoida omilla toteutuksilla laskureita hyödyntäen, siten että aina uuden solmun puuhun lisäyksen jälkeen katsotaan kuinka syvälle se meni ja päivitetään toista laskuria jossa maksimisyvyys. Toinen vaihtoehto on toteuttaa mainittu valinnainen toteutus vierailijasta (visitor) joka käy erikseen puun täyttämisen jälkeen keräämässä BST:stä tämän ja muutakin tietoa analyysiä varten.

Kerro minkä toteutustavan (A...D) valitsit minkäkin algoritmin toteutuksessa. Miksi, ja miten tekisit sen jos saisit valita uudestaan?

**Analysoi** miten aineiston koon kasvu vaikutti eri BST:n algoritmien aikatehokkuuteen. Hyödynnä tässä testien tuottamaa `compare-bst.csv` -tiedostoa. Miten tämä aineiston koon kasvun vaikutus mitattuihin suoritusaikoihin vertautuu siihen, mitä tiedät BST:n *eri* algoritmien aikakompleksisuusluokista?

Hyödynnä analyysissäsi testien tuottamia **csv -tiedostoja**. Kaikki nämä nopeustestit käyttävät samoja JSON -tiedostoja joissa koodareiden tietoja. Testit tuottavat siis vertailukelpoista aineistoa; vertailet seuraavassa harjoituksessa BST:n aikatehokkuutta hajautustaulun aikatehokkuuteen. Vie tiedostojen sisältö taulukkolaskimeen, tee niistä graafeja ja analysoi mitä ne kertovat.

> Graafeja tehdessäsi kannattaa ehdottomasti valita aina aineiston määrä (n) x-akselille, ja **yksi** aikamittauskategoria (sarake) kerrallaan graafiin, esimerkiksi viivadiagrammiin. Näin voit lukea n:n kasvun vaikutusta graafista visuaalisesti *vasemmalta oikealle* n:n kasvaessa x-akselilla. Jos laitat useampia aikamittauksia yhtäaikaa samaan graafiin, ja toinen sarake sisältää huomattavan paljon pienempiä mittoja, tämä käyrä jää käytännössä x-akselin pintaan tai päälle, eikä siitä saa mitään informaatiota esille. Tee useampia graafeja joissa kussakin yksi mittaus. Huomioi myös se, että n ei kasva testiaineistossa tasaisesti, vaan välillä kasvu on isompaa. Esimerkiksi koodareiden määrä on pienimmästä tiedostosta alkaen 100, 1000, 5000, 10000, ja niin edelleen. Ota tämä siis huomioon kun tulkitset graafeja!

Muista luetella mitä valinnaisia tehtäviä toteutit.

## Valinnaiset tehtävät

Jos teet valinnaisia tehtäviä, **muista mainita toteuttamasi valinnaiset tehtävät raporttisi yhteydessä jotta valinnaiset toteutukset tarkistetaan ja saat niistä pisteet**.

Ensinnäkin, yllä mainittu algoritmi **D** joka käyttää puun solmujen lasten lukumääriä nopeaan indeksin hakemiseen ja indeksillä hakuun on valinnainen algoritmi. Kurssin harjoitustyössä riittää in-order -läpikäynti myös näissä indeksialgoritmeissa. Jos toteutat tämän algoritmin D, muista mainita se raportissasi.

**A** `V remove(K key)` -- TIRA Coders:n käyttöliittymä ei sisällä mahdollisuutta poistaa koodareita. Jos haluat oppia ja toteuttaa puusta solmun poistamisen algoritmin, toteuta se. Tätä algoritmia ei ole siis pakko toteuttaa eivätkä annetut testit sitä testaa. 

Algoritmi solmun poistamiseen puusta on esitelty kurssin luennoilla. Toteuta myös joko: 1. käyttöliittymäelementti joka mahdollistaa valitun koodarin poistamisen, tai 2. yksinkertainen pieni testiluokka main -metodeineen joka luo testipuun, laittaa sinne riittävästi testiaineistoa, ja poistaa puusta elementin (varmistaen että puu on oikeellinen), tai 3. yksinkertainen oma yksikkötestisi muiden yksikkötestien joukkoon, joka testaa testiaineistolla puusta poistamista ja sen oikeellisuutta. Jos tämä toimii, saat tehtävästä lisäpisteitä.

**B** TIRA Codersin valikossa on toiminto "Analyse the BST" jonka toteutus hyödyntää Visitor:ia. Visitor käy läpi puun solmut ja tallentaa siitä BST:n analyysiin liittyvää tietoa:

![Analyysiä](task-07-optional-bst-analysis-1.png)

Jos toteutat tämän **visitorin** ja BST:hen tuen sille, saat siitä lisäpisteitä. Huomaa että **vaikka et toteuttaisi** tätä visitoria, raportissa on analysoitava puutietorakenteen toteutusta, esimerkiksi kuinka syvä puusta syvimmillään milläkin aineistolla tuli, ja pohdittava mikä olisi optimaalinen syvyys tällä aineistolla. Tämä visitorin toteutus antaa näppärästi tämän tiedon. Mutta voit tietysti saada vastaavat tiedot muillakin tavoilla, esimerkiksi laskemalla joka kerta kun puuhun lisätään elementti, kuinka syvälle puuhun se sijoittui, ja oliko se nyt puun syvin kohta. Jos oli, tämän tiedon voi laittaa muistiin muuttujaan ja vaikka tulostaa näkyville.

Jos toteutat tämän vierailijan, muista myös toteuttaa tehdasmetodi `oy.interact.tira.factories.BSTFactory.createBSTAnalyzerVisitor()`. Tämän vierailijan toiminnallisuutta voi testata TIRA Codersin valikosta, joka kutsuu `TIRACodersApp.bstAnalysis()` -metodia.

**C** Toinen valinnainen vierailijatoteutus luo puusta [GraphViz:n](https://graphviz.org) dot -tiedoston. GraphViz:n avulla voit luoda (pienistä) puista graafin kuvatiedostona. Myös tälle vierailijalle on tehdasmetodi `BSTFactory` -luokassa, ja sen voi käynnistää TIRA Codersin valikosta, joka kutsuu `TIRACodersApp.exportDot()` -metodia. Tässä ohjeessa olevat kuvat puutietorakenteen sisällöstä on generoitu juuri tällaisella vierailijatoteutuksella ja muunnettu kuvaksi GraphViz:n komentorivityökalulla `dot`.

Mainitsin "pienistä" puista siksi, että kun puu sisältää tuhansia solmuja, graafin generointi kestää jo aika kauan, graafitiedostot ovat isoja, ja kuvatiedostona puun sisällöstä on jo aika vaikea saada selvää. Mutta jos haluat, kokeile tätä myös isommilla aineistoilla.


## Lopuksi

Kun olet valmis, varmista että sekä raportti että kaikki koodi on paikallisessa git -repositoryssäsi ja myös etärepositoryssäsi (komennot `git commit`, tarvittaessa uusille tiedostoille `git add` sekä `git push`).

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.