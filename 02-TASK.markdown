# Tehtävä 2

Älä tee tätä tehtävää ennenkuin olet tehnyt [tehtävän 1](01-TASK.markdown).

> Tässä tehtävässä kirjoitat yhteensä noin 55 riviä koodia, riippuen koodaustyylistä.

## Lähteitä

* Kurssin Luentokalvot.
* Liveluento (tallenne) ja sen vinkit ja esimerkit.
* Kirjallisuus.
* Javan [`Comparator` -rajapinta](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/util/Comparator.html).
* Javan [`Predicate` -rajapinta](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/util/function/Predicate.html).
* Javan [`Object` -luokka](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/lang/Object.html).

Tutustu tarkkaan `Comparator` (askeleet 1-2) ja `Predicate` (askel 3) -luokkien rajapintoihin.

## Yleistä

**Huomaa** että tässä, kuten kurssin muissakaan tehtävissä (graafitehtävää 09 lukuunottamatta), **ei saa käyttää** Javan `Arrays`, `System` tai `Collections` -luokkien algoritmeja, tai *mitään* Javan tietosäiliöluokkia (`ArrayList`, `Vector`, `List`, `Collection` tai näiden rajapintojen toteutukset, jne). Tässä tehtävässä käytetään Javan funktionaalisen ohjelmoinnin rajapintaa `java.util.function.Predicate`, mutta ei muuta packagesta `java.util.function`.

**Edellisessä tehtävässä** toteutit yksinkertaisen lisäyslajittelun luonnolliseen järjestykseen. 

Tässä harjoituksessa täydennetään ratkaisua siten että on mahdollista **joustavasti lajitella** koodareita myös nimen mukaan laskevaan järjestykseen, mutta myös **koodarin lempinimen** mukaan nousevaan tai laskevaan järjestykseen. Tämä tapa lajitella on todellisissa sovelluksissa todella yleinen, joten on hyödyllistä oppia miten tämä voidaan toteuttaa joustavasti säiliötietorakenteiden käsittelyyn.

Lisäksi, analysoimme ja toteutamme tässä lajitellun aineiston **hakualgoritmeja**, erityisesti keskittyen lineaariseen hakuun käyttäen hyväksi *funktio-olioita* (Javan `Predicate`). Näin voidaan tehdä joustavia hakuja määritellen hakukriteerit aina tarpeen mukaan.

## Tavoite

Toteuta tehtävä alla olevien ohjeiden mukaisesti:

1. Aluksi toteutat geneeriset lisäyslajittelualgoritmit *vertailuoliolla*, joita testataan yksinkertaisilla Javan tietotyypeillä.
2. Toteutat lineaarisen hakualgoritmin funktio-oliolla.
3. Sitten toteutat ne osuudet joilla koodaritkin saadaan käyttöliittymässä lajiteltua eri kriteereillä, ja haettua koodareita lineaarisella haulla aineistosta.

**Testaat** toteutustasi `task_02_comparable_search` -hakemistossa olevilla testeillä (askeleittain):

1. testiluokalla `ComparableSortArrayRangeTests` testaa uusia lajittelualgortimeja, sekä
2. testiluokalla `LinearFindTests` testaa lineaarisia hakualgoritmien toteutusta.

Vasta sitten, kun testit menevät läpi, etene kokeilemaan miten lajittelu ja haku toimii TIRA Coders:ssa, alempana löytyvien ohjeiden mukaisesti.

Kirjoita lopuksi **raporttiin** (`RAPORTTI.markdown` löytyy valmiiksi projektin juurihakemistosta) analyysisi tehtävästä, annettujen ohjeiden mukaisesti. Huomaa että myös raportti on arvosteltava tehtävä.

## Askel 1 - Ohjeet

Tässä askeleessa:

* toteutat kaksi uutta lajittelualgoritmia luokkaan `oy.interact.tira.student.Algorithms`. Algoritmit ovat tutut *lisäyslajittelualgoritmit*, mutta nyt käytät olioiden vertailussa `Comparable` -rajapinnan sijaan `Comparator` -rajapintaa.

Ero `Comparable` -rajapinnan käyttöön on siinä, että kun `Comparable`:n kanssa taulukon elementtejä vertaillaan näin...:

```Java
	if (array[index1].compareTo(array[index2]) < 0) { ...
```
...niin `Comparator` -rajapinnan kanssa toimitaankin näin:

```Java
   if (comparator.compare(array[index], array[index2] < 0)) { ...
```

`Comparator` -rajapinnan idea on siis siinä, että `Comparable` vertailee taulukon elementtejä vain yhdellä tavalla, joka on toteutettu taulukon sisältämään olion luokkaan (tässä `Coder` -luokkaan ja sen `compareTo` -metodiin), kun taas `Comparator` on *riippumaton* olion luokasta ja voi vertailla olioita "ihan miten haluaa", eli miten se `Comparator` -rajapinta onkaan milloinkin toteutettu. Voimme siis tehdä *useita* `Comparator` -olioita jotka kaikki vertailevat eri tavoin, ja käyttää eri tilanteissa eri comparator -olioita!

Muuta eroa edellisen tehtävän lisäyslajittelualgoritmiin tässä tehtävässä ei olekaan!

**Toteuta** taulukon geneerinen lisäyslajittelu `Algorithms` metodeihin:

* `void insertionSort(T [] array, Comparator<T> comparator)` - lajittelee koko taulukon järjestykseen jonka määrää comparator -olio.
* `void insertionSort(T [] array, int fromIndex, int toIndex, Comparator<T> comparator)` - lajittelee taulukon comparator -olion määrittelemään järjestykseen annettujen indeksien välillä.

> HUOM: indeksit ovat `[fromIndex,toIndex)` -- lajittelu tehdään siis `fromIndex..<toIndex`, ei `fromIndex...toIndex`.
> 
> Huomaa *uudelleenkäyttö*; voit toteuttaa metodin `insertionSort(T [] array, Comparator<T> comparator)` kutsumalla metodia `insertionSort(T [] array, 0, array.length, comparator)`.

**Testaa** toteutuksiasi testiluokalla `ComparableSortArrayRangeTests` joka löytyy `task_02_comparable_search` -hakemistosta. Älä vielä suorita toista testiä, koska sen testaamaa toteutusta ei ole vielä tehty.

Missä nuo `Comparator` -oliot sitten luodaan ja miltä ne näyttävät tässä tapauksessa? Ne luodaan testiluokassa `ComparableSortArrayRangeTests`. Voit käydä sieltä katsomassa miten niitä olioita luodaan ja käytetään. Seuraavassa askeleessa teet itse omia `Comparator` -toteutuksia vertailemaan `Coder` -luokan olioita, mutta ensin testaa että toteutuksesi läpäisee tämän testin!

Kun testit menevät läpi, jatka seuraavaan askeleeseen alla.


## Askel 2 - TIRA Codersin koodareiden lajittelu

Kun lajittelu comparator -oliolla testien mukaan toimii, yhdistetään toimiva lajittelu TIRA Coders -sovellukseen! Tässä askeleessa:

* toteutat kaksi `Comparator` -luokkaa pakettiin `oy.interact.tira.student`,
* täydennät metodin `oy.interact.tira.model.CoderSortOrder.getComparator()` toteutusta,
* täydennät metodin `oy.interact.tira.util.SimpleContainer.sort(Comparator<E>)` toteutusta.
* täydennät metodin `oy.interact.tira.util.SimpleContainer.reverse()` toteutusta.

Kun olet valmis, geneeriset lajittelualgoritmisi osaavat lajitella myös `Coder` -olioita sisältäviä taulukoita eri järjestyksiin.

Toteutit edellisessä harjoituksessa luokkaan `oy.interact.tira.model.Coder` koodareiden vertailun toteuttamalla luokkaan rajapinnan `Comparable` edellyttämän `compareTo`  -metodin. Näin saimme aikaiseksi sen että koodarit voidaan lajitella nimen mukaan nousevaan järjestykseen.

Haluaisimme kuitenkin vaihtaa lajittelujärjestystä päinvastaiseksi, ja lajitella koodareita myös koodareiden lempinimen ("coder name") mukaisessa järjestyksessä.

**Tehtävänäsi** on toteuttaa *kaksi* `Comparator` -rajapinnan toteutusta:

1. Ensimmäinen vertaa koodareita suku- ja etunimen mukaan luonnollisessa järjestyksessä, hyödyntäen `Coder` -luokan jukisia metodeja.
2. Toinen vertaa koodareita "coder name":n mukaan eli koodarin lempinimen mukaan, luonnollisessa järjestyksessä. 

**Tutustu ensin** Javan `Comparator` -rajapintaan (linkki dokumentaatioon yllä Lähteet -luvussa).

> Huomaa, että `Comparator` -rajapinta sisältää metodin `reversed()` joka palauttaa "käänteisen" vertailijaolion. Voit siis toteuttaa vain luonnollisen järjestyksen vertailijat, joka vertaa koodareita nimen mukaan nousevaan järjestykseen. Kun haluat lajitella laskevassa järjestyksessä, luot luonnollisen järjestyksen vertailun `Comparator` -oliosi ja kutsut sen `reversed()` metodia -- jolla saat laskevan järjestykseen vertailijan helposti!

Askeleet:

1. **Luo uusi luokka**, vaikkapa nimeltään `oy.interact.tira.student.CoderFullNameComparator`, joka toteuttaa (`implements`) rajapinnan `Comparator`.
2. Katso tarvittaessa mallia syntaksista, miten luokka toteuttaa rajapinnan vaikkapa `Coder` -luokasta. Käytä nyt vaan `Comparator` -rajapintaa `Comparable`:n sijaan.
3. **Ylikuormita** (`@Override`) rajapinnan `Comparator` **metodi** `compare` siten että se palauttaa koodareiden koko nimen vertailun lopputuloksen kutsujalle. `Coder` -luokassa on kaikki tätä varten valmiina, kutsut vain sen metodia. Tässä käytä `String` -luokan `compareTo` metodia, koska koodarien nimethän ovat merkkijonoja. Toteuta vertailu nimenomaan niin päin että *ensimmäisen parametrin* `Coder` -oliota verrataan *toisen parametrin* `Coder` -olioon. Tällä *on oikeasti väliä* miten päin vertailu tehdään! Tällä tavalla vertailussa järjestykseksi tulee luonnollinen järjestys (*natural order*).
4. Tee askeleet 1-3 myös toiseen toteutukseen, vaikkapa nimeltään `oy.interact.tira.student.CoderNameComparator`, jonka toteutat samalla tavalla. Mutta nyt vertailetkin koodarinimiä ("coder name"), et "oikeaa" henkilön nimiä.

**Muuta** sitten `oy.interact.tira.model.CoderSortOrder.getComparator()` -toteutusta siten että se luo ja palauttaa kutsujalle oikeanlaisen `Comparator<Coder>` -toteutuksesi, riippuen siitä mikä on valittu lajittelujärjestys. Tähän on kätevä käyttää vaikkapa `switch-case` -rakennetta:

```Java
	switch (this) {
		case FULLNAME_ASCENDING:
		   // return new coder full name comparator object!
	}
```
Ja sama kaikille kolmelle muullekin järjestykselle.

Kun lajittelujärjestys joka halutaan, on "descending" eli laskeva (ei-luonnollinen; Ö...A), luo ensin comparator -oliosi joka vertailee ascending eli nousevaan järjestykseen ja sitten kutsu sen `reversed` -metodia jolloin järjestys jonka comparator saa aikaan, muuttuukin nousevasta laskevaksi! Ei tarvitse tehdä erikseen toista `Comparator` -toteutusta joka vertaileekin eri suuntaan!

**Viimeiset kaksi askelta** mitä tarvitaan, ennen testausta, on se, että:

1. **täydennä** metodin `SimpleContainer.sort(Comparator<E>)` toteutusta. Lisää sinne kutsu sitä toteuttamaasi `Algorithms.insertionSort` -metodiin joka ottaa vastaan taulukon, laiteltavan indeksivälin **sekä** parametrina tulevan `Comparator` -olion.
2. **täydennä** metodin `SimpleContainer.reverse()` toteutusta siten että lisäät sinne kutsun toteuttamaasi  `Algorithms.reverse()` -metodiin joka ottaa vastaan taulukon ja indeksivälin.

Muista myös lajittelun jälkeen asettaa `SimpleContainer.sorted` -arvoksi `true`. Huomaa että metodi `add` asettaa arvoksi `false`, sillä lisäämisen jälkeen järjestys ei enää ole lajittelun mukainen (paitsi tuurilla).

Jos kaikki on mennyt ohjeiden mukaisesti, nyt lajittelu käyttöliittymässä tarjotuilla tavoilla toimii! Huraa!

Jos jotain on pielessä, mieti miksi, ja tarvittaessa kysy apua opettajilta ohjaussessioissa.

Huomaa että (myös) tähän osuuteen liittyy **analysointi- ja raportointitehtävä** (kts. kohta Raportti alla).


## Askel 3 - lineaarinen haku funktio-oliolla

Tässä vaiheessa sovelluksessa toimii siis lajittelu  usealla eri tavalla, toteutettuna yksinkertaisella lisäyslajittelualgoritmilla.

Seuraavaksi toteutat yksinkertaisen lineaarisen hakualgoritmin ja muita elementtien etsimiseen liittyviä algoritmeja.

Tämän avulla saamme sovelluksessa toimimaan hakukentän, ja lisäksi myös koodariystävien nimet jotka nyt eivät näy (kun valitset listalta koodarin, oikealla oleva ystävälista on tyhjä), saadaan haettua tietorakenteesta ja näytettyä käyttöliittymässä.

Ennen kuin aloitat, lue yllä Lähteissä mainitusta `Predicate` -luokasta lisätietoa.

Tässä **toteutat seuraavia** `SimpleContainer` -luokan **metodeja**:

* `E get(E element)` joka hakee taulukosta elementin joka on sama (`equals` palauttaa `true`) kuin parametrina annettua elementti.
* `int indexOf(E element, Comparator<E>)` joka etsii tietyn elementin indeksin taulukosta käyttäen comparator -oliota,
* `int findIndex(Predicate<E>)` joka etsii predikaatin mukaisen hakukriteerin avulla tietyn elementin indeksin taulukosta, sekä
* `E find(Predicate<E>)` joka etsii predikaatin mukaisen hakukriteerin avulla tietyn elementin taulukosta.

Näiden avulla saamme haettua lineaarisella haulla tietosäiliöstä elementtejä ja elementtien indeksejä. Nämä ovat tarpeen jotta voimme näyttää sovelluksessa koodareihin liittyviä tietoja sekä etsiä koodareita yksinkertaisilla hakualgoritmeilla. Nämä toimivat suhteellisen pienillä tietoaineistoilla. Myöhemmissä harjoituksissa teemme nopeampia hakualgoritmeja ja parannamme sovelluksen aikatehokkuutta myös isojen aineistojen kanssa.

**Ensin** metodi `E get(E element)`. Tässä teet yksinkertaisen for -silmukan joka käy läpi `SimpleContainer`:n taulukkoa. Jos taulukossa tulee vastaan elementti, joka on sama (`equals` palauttaa `true`) kuin parametrina annettu, palauta kyseinen elementti kutsujalle. Muista että taulukon alussa on olioita indeksiin count-1 asti, ja sen jälkeen vain null:eja. Niitä nullejahan *ei kannata* yrittää käsitellä.

**Toiseksi**, toteuta metodi `int indexOf(E element, Comparator<E>)`. Tässä **et vielä käytä `Comparator` -parametria**, sen vuoro tulee vasta seuraavassa tehtävässä. Sen sijaan käyt taas for -silmukassa läpi lineaarisesti taulukkoa ja samalla tavalla kuin get -metodissa, etsit `element` -parametria **mutta tällä kertaa** `compareTo` -metodilla, taulukosta. Oikea elementti löytyy siis kun `compareTo` palauttaa nollan. Nyt vaan palautat sen indeksin josta elementti löytyi, sen sijaan että palauttaisit itse elementin. 

**Olennaista taustatietoa**

> Miksi tässä käytetään `compareTo` -metodia ja edellisessä `equals` -metodia? Lyhyesti sanottuna, tämä johtuu siitä mikä todetaan `Coder` -luokan dokumentaatiossakin. Koska tiedämme että usealla koodarilla voi olla sama nimi, koodariolioiden nimi ei määrittele koodariolion identiteettiä, koska nimi ei ole yksikäsitteinen. Siksi jokaisella koodarilla on uniikki `id` joka saadaan Javan `UUID` -tietotyypistä. Tämä `id` erottaa koodarit toisistaan, vaikka kahdella koodarilla olisikin sama nimi. Siksi `get` vertailee koodareiden identiteettiä (`id`). Sen sijaan `indexOf` -metodi liittyy siihen **missä järjestyksessä** eli missä indeksissä koodarit ovat tietosäiliössä. Siksi tässä metodissa käytetäänkin vertailuun `compareTo` -metodia joka nimenomaan liittyy olioiden väliseen vertailuun.

**Kolmanneksi**, toteuta metodi `int findIndex(Predicate<E>)`. Tässä toteutuksena myös yksinkertainen for -silmukka, jossa käytät *predikaattia* testaamaan (*test*) onko taulukossa oleva elementti predikaatin mukainen. Tämä on yksinkertaista: `Predicate` -rajapinnassa on metodi `test` jolle annat taulukossa olevan elementin parametrina. Jos elementti vastaa predikaatin ehtoa, `test` palauttaa true. Silloin tässä taulukon indeksissä oleva olio täyttää predikaatin ehdon ja voit palauttaa kyseisen taulukon indeksin kutsujalle. Predikaatti saattaa tuntua vähän ihmeelliseltä käsitteeltä, mutta sen käyttäminen on aika yksinkertaista.

Missä tätä `findIndex`:iä sitten oikein kutsutaan ja miten tuollainen predikaatti oikein luodaan? 

Kun käyttäjä kirjoittaa hakukenttään tekstiä ja painaa enter, `SearchPanel` ottaa sen tekstin tekstikentästä ja kutsuu `PhoneBookArray`:n metodia `int indexOfItem(String toSearch, boolean exactKeySearch)`. Täällä luodaan predikaatti jolla etsintä tehdään:

```Java
...
phoneBook.findIndex(coder -> (coder.toString().toLowerCase().contains(toSearch.toLowerCase())));
...
```

Vaikkei tuossa näy sanaa `Predicate`, siinä luodaan Javan ns lambda -syntaksia käyttäen predikaattiolio joka "sisältää" testin jolla etsintä tehdään. Tässä tapauksessa se tarkoittaa sitä, että:

1. kutsutaan taulukossa olevien `Coder` -olioiden `toString` -metodia. 
2. Kun katsot mitä `Coder.toString` tekee, on se että se yhdistää koodarin etu-, suku- ja koodarinimen yhdeksi merkkijonoksi. 
3. sitten `toLowerCase` muuttaa kaikki merkkijonon merkit pieniksi kirjaimiksi.
4. etsittävä merkkijono `toSearch` muutetaan myös pieniksi merkeiksi. Tämä siksi että haku toimii vaikka etsittäisiin merkkijonoa "antti" ja koodarin nimi olisi "Antti".
5. sitten katsotaan sisältääkö (`contains`) tämä `Coder.toString`:n palauttama merkkijono tuota `toSearch` -merkkijonoa. Jos kyllä, `Predicate.test` palauttaa true.

Näin predikaatteja käytetään. **Sinun** ei tarvitse siis tässä tuollaisia predikaatteja osata tehdä, vain käyttää jo valmista predikaattia tuolla `indexOf` -metodin toteutuksessa eli käytännössä kutsua sen `test` -metodia.

Kannattaa kuitenkin tutkia tätä koodia tarkkaan, sillä funktio-oliot ovat aika yleisiä moderneissa ohjelmointikielissä, ja niillä koodi saadaan joustavasti tekemään eri asioita.

**Neljänneksi**, toteuta metodi `E find(Predicate<E>)`. Tässä on ihan sama for -silmukka ja predikaatin käyttö kuin edellisessäkin metodissa, mutta tällä kertaa et palauta indeksiä jossa predikaatin mukainen elementti oli, vaan palautat sen elementin taulukosta.

Kokeiluista ja testaamisesta alla.

## Testaus ja analyysi

Nyt jos kaikki toimii, olet valmis testaamaan ja analysoimaan lineaarisia hakualgoritmeja ja niiden suoritusaikaa testillä `LinearFindTests`.

* Testimetodi `testLinearSearchPredicateWithStrings()` testaa yksinkertaisesti että haku predikaatilla toimii `find` sekä `findIndex` -metodeilla. Testi ei mene läpi jos algoritmit eivät toimi.

Jos testi menee läpi, **kokeile** mitä **uusia asioita** voit tehdä TIRA Coders -sovelluksessa:

1. Nyt **hakukenttä** toimii, voit hakea sillä koodareita tekstihauilla. Huomaa että voit hakea millä tahansa sanalla joka sisältyy jonkun koodarin oikeaan nimeen tai koodarinimeen; ensimmäinen hakukriteeriä vastaava koodari tulee listalla nyt valituksi. Huomaa kuitenkin että "Exact (last first)" -rastia ei kannata valita, sillä *se haku ei vielä toimi*.
2. Kun valitset listalta koodareita, oikealle koodarin tietojen **listalle ilmestyy nyt koodarikavereiden nimet**; aikaisemmin ne eivät siellä näkyneet. Tämä johtuu siitä että näitä erilaisia hakualgoritmeja ei vielä oltu toteutettu.

Kun olet varmistanut että algoritmit toimivat, **testaa** miten algoritmit selviytyvät kasvavista tietomääristä, testillä `LinearFindTests.testTimePerformanceOfLinearSearch()`.

Tämä testi luo `SimpleContainer` -tietosäiliöitä joiden koko kasvaa välillä 500...32000 aina 500 elementin verran. **Testi tulostaa konsoliin** aikamittauksia:

```console
Time performance tests of filling and searching the SimpleContainer
Fill time is in milliseconds, and search time in microseconds
n	Fill	Search	Total
500	7	700	707
1000	9	288	297
1500	4	394	398
2000	6	534	540
2500	9	446	455
3000	12	188	200
3500	19	335	354
....
```
Kun testit ovat päättyneet, **kopioi** tulostus taulukkolaskinsovelluksen (MS Excel, Apple Numbers, Google Sheet,...) ja sijoita ne taulukkoon. **Tee erikseen** käyrät: 

* taulukon **täyttöajan** kasvusta suhteessa n:n kasvuun, ja 
* taulukon **hakuaikojen** kasvusta suhteessa n:n kasvuun.

**Analysoi** sekä **koodia** `SimpleContainer.add` -metodissa ja toteuttamissasi hakualgoritmeissa, että tuottamiasi **käyriä**, ja pohdi **mikä** on **add** -algoritmin ja **hakualgoritmien aikakompleksisuusluokka**. Perustele analyysisi kunnolla. Kirjaa analyysi raporttiin, ja sisällytä siihen oma datasi kuten `RAPORTTI.markdown` -tiedostossa on ohjeistettu, käyttäen taulukoita ja kuvia.

## Raportti

**Kirjaa** raporttiisi (`RAPORTTI.markdown` joka löytyy projektin juurihakemistosta), mitä opit tehtävän tekemisestä, mikä oli vaikeaa, mikä helppoa, jne.

Kokeile ja pohdi raportissasi myös seuraavia asioita:

1. Lataat TIRA Coders -sovellukseen iso, esimerkiksi 10 000 koodaria sisältävän tiedosto.
2. Valitset usealla eri tavalla eri lajittelutapoja; sekä koodarin koko nimen lajittelua että koodarin lempinimen lajittelua sekaisin. 
3. Katso **TIRA Coders Log View**:tä ja huomaat että joskus lajittelu on paljon nopeampaa ja joskus taas ei.

Mistä erot suoritusajassa johtuvat kahdessa eri tilanteessa, nopeassa ja hitaassa? Mikä on näiden kahden eri algoritmin aikakompleksisuusluokat ja niiden ero toisiinsa?

Ihan yleisesti ajateltuna, jos sinulla on aineisto valmiiksi lajiteltuna taulukossa, ja alkioiden järjestys pitää vaihtaa päinvastaiseksi, mitä algoritmia käytät, lajittelua vai reverse -algoritmia? Miksi?

Apua analyysiisi voisi antaa `PhoneBookArray.sort(CoderSortOrder order)` -metodin algoritmi, jota voit tutkia.

**Kirjoita** myös raporttiisi:

* miksi toteutettuja hakualgoritmeja kutsutaan *lineaarisiksi*? 
* Mikä niiden aikakompleksisuusluokka on big-O -notaatiolla esiteltynä?

## Lopuksi

Kun olet valmis, varmista että sekä raportti että kaikki koodi on paikallisessa git -repositoryssäsi ja myös etärepositoryssäsi (komennot `git commit`, tarvittaessa uusille tiedostoille `git add` sekä `git push`).

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.