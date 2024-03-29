# Raportit tehtävistä

Kirjaa tähän tiedostoon **jokaiseen** tehtävään liittyvät omat raporttisi ja analyysisi. Muista että raportti on myös
kurssilla **arvosteltava tehtävä**.

Voit sisällyttää raporttiin tekstimuotoisia taulukoita (tasaukset välilyönnein):

```
n     Fill     Search   Total
500   7        700      707
1000  9        288      297
```

Ja näihin liittyviä kuvatiedostoja:

![Esimerkkikuva](report-sample-image.png)

Nämä näkyvät sitten VS Coden Preview -näkymässä (tai oman repositorysi webbisivulla) oikein muotoiltuna. Käytä tässä
dokumentissa olevia muotoiluja esimerkkinä kun kirjoitat raporttiasi.

Huomaa että jos laitat kuvatiedostot vaikka omaan alihakemistoonsa, Markdown -muotoilussa on oltava suhteellinen polku
tiedostoon, esimerkiksi `images/report-sample-image.png`. **Älä** käytä absoluuttisia
polkuja `C:\Users\tippaleipa\kurssit\TIRA\kuva.png`, koska nämä eivät tietenkään toimi opettajan koneella. Ei kannata
laittaa linkkiä etärepoosikaan, vaan nimenomaan paikalliseen tiedostoon.

Voit myös sisällyttää *lyhyitä* koodinpätkiä vaikkapa Java -formaatilla:

```Java
    @Override
public int hashCode(){
        // Oma nerokas hajautufunktioni!
        }
```

Tarvittaessa käytä myös paremmin muotoiltuja taulukoita:

| n	   | Fill	 | Search	 |  Total |
|------|-------|---------|--------|
| 500	 |  7	   | 700	    | 707    |
| 1000 | 	9	   | 288	    | 297    | 

Alaluvut jokaisen tehtävän raportille löydät alta.

## 01-TASK

insertionSort-metodien tekeminen oli lopulta yksinkertaista, kun tajusi idean sen takana. Comparable-rajapinnan käyttö
oli entuudestaan tuttua.
TIRA Codersin koodareiden lajittelu oli aluksi hieman haastavaa, kun piti miettiä miten nimien lajittelu tehdään
käytännössä.
Päädyin käyttämään lopulta `String.charAt(i)` -metodia, jossa vertaillaan nimiä merkki merkiltä, aloittaen sukunimestä.
Jos sukunimet olivat samat, verrattiin etunimiä keskenään.

* Lisäyslajittelu-algoritmin aikakompleksisuusluokka on `O(n^2)` eli neliöllinen, koska se käsittelee elementtejä
  kahdessa for-silmukassa.
* reverse -algoritmin aikakompleksisuusluokka on `O(n)` eli lineaarinen, koska sen täytyy käsitellä jokainen elementti
  listassa tai tietorakenteessa, ja mahdollisesti vaihtaa niiden paikkaa keskenään.
* Valmiiksi nousevan järjestyksen omaava taulukko kannattaa **kääntää** (reverse), koska olioita/merkkijonoja ei
  tarvitse vertailla keskenään. Tarvitsee tehdä vain väliaikaisia muuttujia ja sijoitusoperaatioita.

## 02-TASK

Opin tehtävän myötä Predicate-luokan toiminnallisuudesta ja miten sitä voi käyttää hakufunktioissa apuna.
Myös se, miten Comparator ja Comparable -rajapinnat eroavat toisistaan, tuli tutuksi.
Aluksi oli vaikeaa hahmottaa, miten koodareiden koko nimen ja lempinimen saa palautettua CoderFullNameComparator ja
CoderNameComparator -luokista.
Loppupeleissä, toiminnallisuus olikin melko simppeli ja sen avulla koodin rakenteeseen saa joustavuutta.
Lineaaristen hakufunktioiden get(), indexOf(), find() ja findIndex() toteutus oli melko suoraviivaista,
kun toteutettiin yksinkertainen for-silmukka ja käytettiin valmiita rajapintoja parametreille.

> Täyttöajan kasvu suhteessa n:n kasvuun:

<img src="images/02_task_fillFunction.png" alt="Täyttöaika" width="2252"/>

> Hakuaikojen kasvu suhteessa n:n kasvuun:

<img src="images/02_task_searchFunction.png" alt="Hakuaika" width="2235"/>

`SimpleContainer.add` -metodissa taulukko täytetään sen kapasiteettiin asti koodareiden sisältämillä tiedoilla,
elementti kerrallaan. Jos taulukon elementti indeksissä n on null -> `array[index] == null`, niin se jätetään huomiotta.
Taulukko täytetään uusilla elementeillä siihen asti, että taulukon elementit vastaavat parametrina annettuja
elementtejä (`array[index].equals(element)`).

Taulukko reallokoidaan, jos `count` ylittää taulukon pituuden `array.length`.
Lopuksi, kun taulukkoon on lisätty uusia elementtejä, se ei ole enää järjestetty ja asetetaan `sorted = false`.

Kuvaajista nähdään, että taulukon **täyttöajat** suhteessa n:ään ovat tasaisempia, kuin **hakuajat** suhteessa n:ään.
Tämä johtuu siitä, että lineaarinen hakualgoritmi käy joissain tapauksissa koko taulukon läpi etsiäkseen tietyn
elementin (esim. lopusta). Lisäysalgoritmi `add` puolestaan tarkistaa, esiintyykö lisättävä elementti jo entuudestaan
taulukossa ja palautuu kutsujalle tarvittaessa.
Molemmissa tapauksissa suoritusaika taulukon koon funktiona noudattaa likimain aikakompleksisuusluokkaa `O(n^2)`.
Suoritusaika kasvaa siis lineaarisesti n:n toiseen potenssiin nähden, kun elementtejä käsitellään kahdessa silmukassa.
Kun otosmäärä tuplaantuu, suoritusaika *
*nelinkertaistuu!**

**TIRA Coders:**

LogView 10 000 koodarin lajittelusta Full name ja Coder name perusteella:

<img src="images/02_task_TiraCoders_logView.jpg" alt="Hakuaika" width="487"/>

Koodareiden nimien lajittelu n:n suhteen

| n	    | Full name (ms) | Coder name (ms) |
|-------|----------------|-----------------|
| 1000	 |  20            | 3	              |
| 5000	 |  500           | 150	            |
| 10000 | 	2500	         | 700	            |

Suoritusaikaan vaikuttaa mm. se, että samanlaisia lempinimiä on useammalla koodarilla, kun taas suku- ja etunimissä on
enemmän eroavaisuuksia. Merkkejä täytyy siis käsitellä enemmän, kun tehdään lisäyslajittelua (insertion sort)
koodareiden koko nimien perusteella.

Valmiiksi lajitellun taulukon kääntäminen päinvastaiseksi onnistuu nopeiten **reverse-algoritmin** avulla, koska
olioita/merkkijonoja ei tarvitse vertailla keskenään. Reversessä vaihdetaan pelkästään kahden elementin paikkaa
keskenään taulukossa.

* Toteutettuja hakualgoritmeja kutsutaan lineaarisiksi, koska niiden suoritusaika kasvaa suhteessa n:n kokoon nähden.
* Täyttö- ja hakualgoritmit vastaavat likimain aikakompleksisuusluokkaa `O(n^2)`, sillä lajittelun kesto kasvaa
  eksponentiaalisesti n:n koon kasvaessa -> 2^2 = 4, 10^2 = 100 jne.

## 03-TASK

**------------------- HOX!!! TOTEUTIN PUOLITUSHAUN SEKÄ ITERATIIVISESTI, ETTÄ REKURSIIVISESTI -------------------**

Binäärisen puolitushakualgoritmin toteutuksessa piti jonkin aikaa miettiä,
miten metodin binarySearch saa palauttamaan arvon -1, kun muuttujaa aValue ei löydy fromArray -taulukosta.
Ratkaisuna tähän oli tarkistaa, jos fromIndex on suurempaa tai yhtä
suurta kuin fromArray -taulukon pituus fromArray.length.
Myös aikakompleksisuusluokkien määrittämisessä piti käyttää jonkin verran ajatus- ja tutkimustyötä.

Tehtävässä oli suhteellisen helppoa toteuttaa itse koodauspuoli, jossa debuggerin käyttö auttoi merkittävästi.
Rekursiivisen puolitushaun `public static <T> int binarySearchRecursive(T, T[], int, int, Comparator<T>)` käyttäminen
aiheutti pinon ylivuodon, joten päätin toteuttaa myös iteratiivisen version
`public static <T> int binarySearchIterative(T, T[], int, int, Comparator<T>)` ja käytin sitä tässä harjoituksessa.

Kuvat binäärisen puolitushakualgoritmin suoritusajoista **nousevan ja laskevan** järjestyksen mukaisesti:

<img src="images/03_task_binary_search_ascending.png" alt="Binary search ascending" width="1627"/>

<img src="images/03_task_binary_search_descending.png" alt="Binary search descending" width="1817"/>

Binäärinen hakualgoritmi (puolitushaku) noudattaa aikakompleksisuusluokkaa `O(log n)`,
koska syötteen (input) määrä puolittuu jokaisen kierroksen jälkeen.
Puolitushakua käyttämällä suoritusaikoja saatiin jopa **puolet lyhyemmäksi** lineaariseen
hakualgoritmiin verrattuna, jonka aikakompleksisuus on `O(n^2)`.

Puolitushakua kannattaa käyttää aina silloin, kun taulukko on valmiiksi lajiteltu, nousevaan tai laskevaan
järjestykseen. Se on lineaariseen hakualgoritmiin verrattuna huomattavasti nopeampi, koska kaikkia taulukon elementtejä
ei tarvitse käydä läpi.

Puolitushaku toimii etenkin suurempien tietomäärien kanssa nopeammin, koska se puolittaa
hakualueen jokaisella iteraatiolla tai rekursiolla, kunnes elementti löytyy (tai on löytymättä).

**Tira Coders:**

Koodareiden haku käyttöliittymästä sukunimen perusteella kesti sitä kauemmin, mitä alempanana listassa ne olivat.
Esimerkiksi listan lopusta hakemalla sukunimeä **Översti**, haku kesti 26 ms,
kun taas listan alusta nimellä **Aallonen**, 0 ms.

Koodarin nopea haku koko nimellä "Exact (last first)" kesti sekin vain 0 ms riippumatta siitä, mistä kohtaa listaa nimeä
haettiin -> "PhoneBookArray: Fast search took 0 ms".

Koko nimellä hakeminen toimii vain silloin, kun taulukko on lajiteltu nousevaan tai laskevaan järjestykseen suku- ja
etunimen perusteella.

## 04-TASK

Opin pinotietorakenteen toteutuksessa sen, miten IDE:t tarkistavat syntaksivirheitä lähdekoodista, esim.
edellätoteutetun
`ParenthesisChecker.java` mukaisesti. Koodieditorit varoittavat automaattisesti, jos koodista puuttuu avaavia tai
sulkevia
sulkuja tai jos ne ovat väärässä järjestyksessä.

`StackImplementation.java` aikakompleksisuusvaatimuuksia piti jonkin verran korjata esim. `clear()` -metodin kohdalla.
Lisäksi, haastavaa oli myös toteuttaa lainausmerkkien, rivinumeroiden ja sarakenumeroiden käsittely `checkParentheses`
-metodissa.
Ymmärsin periaatteen kuitenkin hyvin ja sulkujen tarkistaminen onnistui hyvin myös omien JSON ja Java -tiedostojen
kanssa.

Aikakompleksisuusvaatimukset pinotietorakenteen toteutuksessa täyttyvät,
koska vain `push()` ja `toString` -metodit käyttävät silmukoita hyväkseen.
Jos indeksi, johon viimeisin elementti on lisätty, ylittää pinon kapasiteetin `capacity`, niin tehdään reallokointi
`reallocateArray`. Reallokoinnissa luodaan uusi taulukko-olio, tuplataan sen kapasiteetti vanhaan taulukkoon nähden ja
lisätään vanhan taulukon elementit uuden taulukon alkuun for-silmukassa:
```Java
for (int i = 0; i <= currentIndex; i++) {
    newArray[i] = itemArray[i];
}
```
Tämän vuoksi `push()` -metodi on aikakompleksisuusluokaltaan `O(n)`, kun tehdään reallokointi.

Myös `toString()` noudattaa aikakompleksisuusluokkaa `O(n)`,
koska for-silmukassa erotetaan taulukon elementit pilkulla toisistaan:
```Java
for (int i = 0; i <= currentIndex; i++) {
    builder.append(itemArray[i]);
    if (i < currentIndex) {
        builder.append(", ");
    }
}
```

Muut pinotietorakenteen metodit ovat aikakompleksisuusluokaltaan `O(1)`, koska niissä tehdään vain yksinkertaisia
sijoitus- ja vertailuoperaatioita.

Kokeilin, miten TIRACodersApp toimii, kun lainausmerkki puuttuu JSON-tiedostosta.
Nähtävästi algoritmi osaa tunnistaa rivinumeron oikein,
mutta sarakkeita laskiessa se väittää virheen olevan sarakkeessa 1587.
Algoritmi myös tunnistaa virhetyypin oikein -> "Too few closing parentheses".
Eli tässä tapauksessa lainausmerkkejä on liian vähän:

<img src="images/04_task_faultyQuotationMarks.png" alt="Check json file 1" width="2190"/>

Toisaalta, kun lainausmerkkejä on tiedostossa liikaa, tässä tapauksessa algoritmi väittää JSON-tiedoston olevan
oikeellinen
-> "JSON file test-village-coders.json is valid".
Eli algoritmi ei ole oikeellinen, koska se ei tuota toivottua tulosta siihen, mitä pitäisi. Se antaa jokseenkin oikeita
tuloksia riippuen siitä,
missä kohtaa tekstiä ja kuinka paljon lainausmerkkejä puuttuu tai on liikaa.
Algoritmin toteutusta pitäisi muuttaa niin, että otetaan huomioon myös lainausmerkkien välissä olevat lainausmerkit.

<img src="images/04_task_faultyQuotationMarks_okMessage.png" alt="Check json file 2" width="2514"/>

## 05-TASK

**------------------- HOX!!! TOTEUTIN JONOTIETORAKENTEESTA SEKÄ TAULUKKOVERSION, ETTÄ LINKITETYN LISTAN -------------------**

Mielestäni taulukkoversion toteutus jonotietorakenteista oli helpompi ja yksinkertaisempi, verrattuna linkitettyyn
listaan. Haastetta toi mm. linkitetyn listan solmujen hallinta.

Linkitetyn listan etuna on se, että reallokointia ei tarvitse tehdä, kun uusia elementtejä lisätään listaan. Ainoana
rajoitteena on siis tietokoneen käyttämän RAM-muistin määrä. Koodissa linkitetyn listan maksimikooksi on määritelty
Integer.MAX_VALUE, eli suurin mahdollinen kokonaislukumuuttujan arvo. Linkitetty lista huolehtii siis itse muistin
lisäämisestä tai vähentämisestä.

Linkitetty lista häviää aikakompleksisuudeltaan ja tehokkuudeltaan selvästi taulukkototeutukseen verrattuna, isojen
tietorakenteiden kanssa.
Syynä on se, että solmujen välisten yhteyksien ylläpitäminen kuluttaa paljon keskusmuistia, koska niiden välillä
tallennetaan osoittimia (pointer) päästäkseen seuraavaan solmuun. Vaikka nykyisissä tietokoneissa keskusmuistia on jo
riittävästi, vastaan tulee kuitenkin suoritusaika, kun käsitellään suuria tietomääriä linkitetyn listan avulla.
Artikkelissa "RIP Linked List, Sonntag & Colnet (2023), https://arxiv.org/pdf/2306.06942.pdf" mainitaan myös, että
linkitetyn listan hitaus perustuu siihen, että esim. viitatessa satunnaiseen listamuotoisen testidatan elementtiin (
solmuun), se vaatii paljon muistia. Listan solmut täytyy siis käsitellä järjestyksessä, joko alusta tai lopusta lähtien,
kunnes löydetään haluttu elementti. Testi perustuu Bjarne Stroustrupin muunneltuun suorituskykytestiin, jossa mitataan
aikakompleksisuutta elementtien lisäämisessä suureen tietorakenteeseen (taulukkoon). Kappaleen 4. kuvaajasta **(Fig. 9)
** nähdään, kuinka linkitetyn listan (LinkedList) aikakompleksisuus on selvästi heikompi, kuin taulukkototeutuksen (
ArrayBlock).

Kun taulukkototeutuksessa pusketaan elementtejä listaan enqueue-metodia käyttäen,
reallokointi-metodia `reallocateArray()` kutsutaan jos taulukon sisältämien elementtien määrä `count` saavuttaa tai
ylittää sen kapasiteetin.

**Taulukkototeutus, edut ja haitat:**

(+) Kuluttaa vähemmän muistia.

(+) Yksinkertaisempi toteuttaa.

(+) Satunnaiseen elementtiin viittaaminen on nopeaa, koska elementit on tallennettu vierekkäin jonoon,
aikakompleksisuus `O(1)`.

(-) Reallokointi voi olla aikaavievä operaatio, jos taulukon kapasiteetti ylittyy. Tämä riippuu taulukon koosta n,
jolloin aikakompleksisuus on `O(n)`.

**Linkitetty lista, edut ja haitat:**

(+) Dynaaminen koko, kätevämpi pienten tietorakenteiden kanssa. Ei tarvitse reallokointia.

(+) Ei varaa ylimääräistä muistia käyttöönsä, toisin kuin reallokointi taulukkototeutuksessa.

(+) Elementtien lisääminen ja poistaminen nopeaa, aikakompleksisuus `O(1)`.

(-) Viittaus satunnaisiin elementteihin hidasta, koska lista täytyy käydä läpi solmujen kautta.
Aikakompleksisuus `O(k)`, jossa `k` vastaa haetun elementin sijaintia joko listan alusta tai lopusta luettuna.

## 06-TASK

**------------------- HOX!!! TOTEUTIN QUICKSORTIN SEKÄ ITERATIIVISESTI, ETTÄ REKURSIIVISESTI -------------------**

> `Algorithms.fastSort` -metodeissa on funktiokutsut molempiin quicksort-toteutuksiin, joista toinen 
> on kommentoitu pois.

Rekursiivisen ja iteratiivisen quicksort -algoritmien toteutuksessa oli hieman haasteellista pitää muistin käyttö
sellaisessa koossa, että ohjelma ei kaadu kesken lajittelun. Myös partitiointimetodin toteutusta piti miettiä jonkin
aikaa, koska pivot-elementin valitseminen meni välillä väärin.
Välillä algoritmi yritti viitata array-taulukon ulkopuolelle, koska `toIndex` oli liian suuri.

Analysoin ja tein kaaviot sekä hitaasta, että nopeista lajittelualgoritmeista
 ja `CodersFastComparatorTests` -testien perusteella.
Taulukoissa on kuvaajat aineiston koon `Count` sekä lajittelun kokonaisajan `Sort (ms)`,
että lajittelun keston per taulukon koko `Sort (ms/element)` suhteen.

> Tässä käytettiin hidasta Insertion sort -lajittelualgoritmia `CodersSlowComparatorTests` -testissä:

<img src="images/06_task_CodersSlowComparatorTests_sort.png" alt="CodersSlowComparatorTests" width="1763"/>

> Näissä käytettiin nopeita quicksort -lajittelualgoritmeja `CodersFastComparatorTests` -testissä.
> Sekä rekursiivinen, että iteratiivinen toteutus.
> Lajittelun kokonaiskesto on likimain sama riippumatta siitä, kumpaa toteutusta käytettiin.

<img src="images/06_task_CodersFastComparatorTests_sort.png" alt="CodersFastComparatorTests" width="1610"/>

Insertion sort -lajittelussa kesti jopa 9 minuuttia, 100 000 elementin kohdalla.
Quicksort -algoritmi puolestaan selviytyi 2 000 000 elementin kokoisesta aineistosta vain hieman yli 11 sekunnissa.

* Insertion sort -algoritmin aikakompleksisuusluokka on `O(n^2)` eli neliöllinen,
  koska se käsittelee elementtejä kahdessa for-silmukassa.
  Jokainen taulukon elementti täytyy käydä erikseen läpi siitä lähtien, kun taulukossa vaihdetaan elementtien paikkaa
  keskenään `swap` -metodissa.
  Tämä tekee lisäyslajittelusta äärimmäisen hitaan, suurien aineistojen kohdalla.
* Quicksort -algoritmin aikakompleksisuusluokka on `O(n*log n)`,
  koska aineisto jaetaan suunnilleen samankokoisiin osiin jokaisella rekursiotasolla.
  Jos taulukon järjestettävät alkiot ovat jo valmiiksi järjestyksessä, quicksort vaatii aikakompleksisuudeltaan `O(n^2)`
  lajittelua (worst-case scenario).

Rekursiivisen ja iteratiivisen quicksort -toteutuksen **aikaerot** eivät juuri poikkea toisistaan.
Toteutusten olennainen ero on siinä, että rekursiivinen algoritmi kutsuu itseään useita kertoja,
kun taas iteratiivisessa toteutuksessa pysytään while-silmukassa ja kutsutaan partitiointimetodia sen sisällä.
Iteratiivisessa toteutuksessa hyödynnetään erillistä pinotietorakennetta (tehtävän task_04 `StackImplementation`
-luokassa), jossa käsiteltävän taulukon indeksejä pidetään muistissa.
Tämän ansiosta kutsupinoa (call stack) ei tarvita taulukoiden ylläpitämiseen, toisin kuin rekursiivisessa toteutuksessa.

TIRA Coders App nopeutui huomattavasti, kun lajittelu vaihdettiin nopeaan quicksort-algoritmiin.
Kokonaiskesto esim. 50 000 elementin json-tiedoston lajittelulle nopeutui n. **4,5 -kertaisesti**.

> Hidas Insertion sort -algoritmi Tira Coders Appissa. Aineistona 10 000 ja 50 000 elementin json-tiedostot:

<img src="images/01_task_insertionSort_TIRACodersApp.png" alt="TIRA Coders Insertion sort" width="991"/>

> Nopea quicksort -algoritmi Tira Coders Appissa. Aineistona 10 000 ja 50 000 elementin json-tiedostot:

<img src="images/06_task_quickSort_TIRACodersApp.png" alt="TIRA Coders Quicksort" width="1127"/>

## 07-TASK

Ylivoimaisesti vaikeinta BST:n toteutuksessa oli getIndex(int index) metodin toteuttaminen, joka vei eniten aikaa.
Sain toimimaan lapsisolmujen lukumäärän laskemisen, mutta sen avulla koodareiden hakua listasta en.
Binäärisen hakupuun toimintaperiaate on loppupeleissä melko yksinkertainen, mutta sen opettelu ja sisäistäminen vei
yllättävän paljon aikaa.

Ihmettelin myös, miksi lajittelujärjestyksen vaihtaminen ei toiminut sen jälkeen, kun olin toteuttanut BST:n.
Syynä oli yksinkertaisesti se, että käytin Comparablen `compareTo` -metodia comparator-rajapinnan sijaan.
Ratkaisuna oli käyttää luokan jäsenmuuttujaa `private Comparator<K> comparator` hyväksi, joka annetaan myös bst-luokan
muodostimelle parametriksi. Tämän jälkeen `comparator.compare()` -vertailua käyttämällä lajittelujärjestyksen
vaihtaminen alkoi toimimaan. 

Päätin olla hyödyntämättä `TreeNode` -luokkaa ja tein BST:lle oman Node-luokan, joka sisältää `add` ja `bstSearch`
-metodit.

Toteutin työssä vaadittavat algoritmit A-metodilla, eli rekursiivisella in-order läpikäynnillä. Tämän
aikakompleksisuusluokka on O(n). Rekursiivinen toteutus on vaihtoehdoista yksinkertaisin, joten valitsin sen.
`getIndex()` ja `indexOf()` -metodien toteutuksessa yritin hyödyntää lapsisolmujen lukumäärää vaihtoehdon D) mukaan, mutta
se osoittautui haasteelliseksi. Tämän avulla aikakompleksisuuden olisi saanut jopa O(log n) tasolle.
Analysoin aikamittauksia in-order toteutuksella 100 000 koodariin asti, koska tätä suuremmat aineistot olivat liian
hitaita.

Laskin binääripuiden syvyyden aineiston koon perusteella:

| Elements (n)	 | Depth  |
|---------------|--------|
| 100	          | 12     |	   
| 1 000         | 21     |
| 5 000         | 28     |
| 10 000        | 29     |
| 50 000        | 39     |
| 100 000       | 37     |
| 1 000 000     | 50     |

> **BSTPerformanceTests** aikamittauksia ja kuvaajia in-order toteutukselle. Huomataan, että taulukkototeutukseen
> verrattuna lisäysaika (Add time)
> ja lajittelu (To sorted array time) olivat nopeampia. Vastaavasti, binäärinen hakupuu oli erittäin hidas, kun puusta
> haettiin
> avain-arvo paria tietyllä indeksillä -> `getIndex(index)`. 100 000 aineiston kohdalla haku kesti noin 78 sekuntia!
> Tämä johtuu siitä, että nykyinen toteutus käy pahimmassa tapauksessa koko puun läpi, ennen kuin se löytää indeksiä
> vastaavan
> elementin. Eli silloin, kun etsitään esim. puun viimeistä indeksiä.

<img src="images/07_task_BSTPerformanceTests_graphs.png" alt="BST Graphs" width="2347"/>

> **SimpleContainerPerformanceTests** aikamittauksia ja kuvaajia. Binäärinen hakupuu ja taulukkototeutus olivat
> suunnilleen yhtä nopeita, kun aineistosta haettiin jotain arvoa (value) tietyllä
> avaimella (key) -> Search time (ms)

<img src="images/07_task_SimpleContainerPerformanceTests_graphs.png" alt="SimpleContainer Graphs" width="2446"/>

Testatessa TIRA Coders Appia huomasin, että BST:n in-order toteutuksella koodareiden selaaminen ja haku oli sujuvaa 100
000 aineistoon saakka. Kaikki operaatiot kestivät alle 200 ms ja ei vaikuttanut juuri käyttäjäkokemukseen.

Kun koodareita yrittää selata 1 000 000 koodarin aineistolla, käyttöliittymä tökkii jatkuvasti.
JSON-tiedoston lukeminen (1 000 000 koodaria) ja osittainen haku koodarin sukunimen perusteella listan lopusta,
esimerkiksi "Westerholm", tulostaa seuraavanlaiset aikamittaukset:

> 1 000 000 koodarin aineiston lataaminen ja osittainen haku "Westerholm":

PhoneBookBST: Reading JSON with 1000000 items took 12023 ms

PhoneBookBST: JSON to Coders array took 1110 ms

PhoneBookBST: Add to container with 999998 items took 2146 ms

PhoneBookBST: Search took 645 ms

PhoneBookBST: Get coder by index took 13 ms

PhoneBookBST: Getting friend names took 451 ms

> 2 000 000 koodarin aineiston lataaminen ja osittainen haku "Åström":

PhoneBookBST: Reading JSON with 2000000 items took 25004 ms

PhoneBookBST: JSON to Coders array took 6335 ms

PhoneBookBST: Add to container with 1999987 items took 4626 ms

PhoneBookBST: Search took 1528 ms

PhoneBookBST: Get coder by index took 31 ms

PhoneBookBST: Getting friend names took 109 ms

Tällaisilla vasteajoilla käyttökokemus ei ole enää miellyttävä, vaan vaatii nopeampien algoritmien toteutusta.
Sain toteutettua lapsisolmujen lukumäärän laskemisen `childCount` jokaiselle solmulle,
ja sitä hyödyntämällä hakuajan aikakompleksisuuden saisi jopa O(log n) tasolle. Tästä huolimatta,
päätin olla toteuttamatta tätä tapaa. `getIndex(int index)` -metodini on siis toteutettu in-order,
joka käy rekursiivisesti läpi binääristä hakupuuta ja samalla pitää kirjaa, missä indeksissä ollaan
menossa -> `currentIndex`.
Sitten, kun `currentIndex == index` -ehto toteutuu, niin metodi palauttaa uuden avain-arvo parin kutsujalle.

## 08-TASK

Linkitetyn listan solmujen hallinta osoittautui melko haasteelliseksi, kun koodista laskettiin sanoja ja päivitettiin
tietyn sanan esiintymisarvoa: CodeWordsCounter-luokassa.
Koodissani oli esimerkiksi bugi, että törmäyksen (collision) sattuessa päivitin vahingossa koko linkitetyn listan
tietystä indeksistä uudelleen `itemArray[hashIndex] = new Node<>(new Pair<>(key, value));` jolloin next-viittaukset
poistuivat kokonaan ja taulukon koko (`size`) jäi suuremmaksi, mitä siellä oikeasti oli elementtejä -> ja ratkaisuna
olikin yksinkertaisesti viitata vain solmun data-elementtiin ja korvata se uudella avain-arvo parilla
`itemArray[hashIndex].data = new Pair<>(key, value);` jolloin linkitetyn listan viittaukset seuraaviin solmuihin
säilyivät.

Toinen kysymysmerkki, jota ihmettelin jonkin aikaa, liittyi koodin avainsanojen esiintymismäärän lajitteluun.
`CodeWordsCounter.topCodeWords` -metodissa muodostetaan sanoista taulukko, jonka jälkeen se pitää lajitella laskevaan
järjestykseen. Tein oman `CodeWordsComparator` -luokan, jossa vertaillaan sanoja ja niiden esiintymislukumääriä
pareina -> `Pair<String, Integer>`. Sen jälkeen nopealle lajittelualgoritmille annettiin lajittelematon taulukko ja
vertailija päinvastaisella järjestyksellä -> `Algorithms.fastSort(wordsArray, wordsComparator.reversed())` ja rajattiin
yleisimmät sanat `topCount` mukaisesti, esim. top 100 sanaa.

**TIRA Coders Appin mukaan käytetyimmät sanat omassa tira-projektissani, src-hakemistossa:**

<img src="images/08_task_topWords_hashTable.png" alt="Top code words hashTable" width="743"/>

**Hajautustaulun nopeustestien tuloksia (HashTablePerformanceTests):**

<img src="images/08_task_HashTablePerformanceTests.png" alt="HashTablePerformanceTests" width="2364"/>

**Taulukkolajittelun nopeustestien tuloksia (SimpleKeyedTablePerformanceTests):**

> Huomaa, että taulukon asteikko loppuu jo 100 000 aineistoon saakka, koska taulukkopohjainen toteutus hidastui
> merkittävästi tämän rajapyykin jälkeen. Ylempänä HashTablePerformanceTests-graafissa on mittaukset 2 000 000
> aineistoon saakka.

<img src="images/08_task_SimpleKeyedTablePerformanceTests.png" alt="SimpleKeyedTablePerformanceTests" width="2373"/>

SimpleKeyedTablePerformanceTests suoritus hidastui merkittävästi jo 100 000 aineiston jälkeen.
Hakuaika (Search time) 100 000 json-elementin joukosta kesti jo 100 sekuntia, joka on äärimmäisen hidasta.
Hajautustaulun haku 100 000 json-elementin kohdalla kesti vain 20 ms, ja 2 000 000 elementin kohdalla 619 ms.
Toisaalta, taulukkototeutuksessa lisäysaika (Add time) ja lajittelu (To array and sorting) olivat hieman nopeampia
hajautustauluun verrattuna, linkitetyn listan käytön vuoksi. Hajautustaulussa törmäyksiä (chaining) tapahtuu melko
paljon, jolloin samaan indeksiin linkitetään uusi solmu. Tämän vuoksi hajautustaulun muuttaminen normaaliksi taulukoksi
kestää hieman kauemmin.

BST:n in-order haku (78 sekuntia) on oletettavasti hitaampi, kuin hajautustaulun haku (20 ms) 100 000 aineistolla.
Toisaalta, jos BST:n tekee D-toteutuksella hyödyntäen lapsisolmujen lukumäärää, hakuajat olisivat likimain yhtä nopeita.
Toisaalta, BST pärjää hajautustaulua nopeammin taulukon muodostamisessa ja sen lajittelussa (To array and sorting).
100 000 aineiston kohdalla hajautustaululta kului siihen 1401 ms, kun taas binäärinen hakupuu käytti vain 2 ms!
Tämä johtuu esimerkiksi siitä, että toteutin hajautustaulun linkitettynä listana, jolloin solmujen välinen hallinta
kuluttaa muistia ja hidastaa lajittelunopeutta.

Hajautustaulun, joka hyödyntää linkitettyä listaa, aikakompleksisuus on keskimäärin `O(1 + n/m)`, missä
`n/m` on taulukon täyttöaste (fill factor). Taulukon täyttöaste kuvaa sitä, kuinka suuri osa hajautustaulun indekseistä
saa olla käytössä taulukon kapasiteettiin nähden (yleensä 65% - 75%). Eli mitä suurempi täyttöaste, sen hitaammaksi
hajautustaulu menee, koska linkitettyjen solmujen määrä kasvaa.

Hajautustaulu (myös linkitetty) on nopeampi normaaliin taulukkototeutukseen verrattuna, kun aineiston koko on suuri. Jos
lisättävien elementtien määrä tiedetään etukäteen, hajautustaulun koko voidaan määrittää yhtä suureksi ja reallokointia
ei tarvita. Vastaavasti, hajautustaulun aikatehokkuus ei ole niin hyvä pienen aineistokoon kanssa. Reallokointi hidastaa
taulukkoa ja kuluttaa muistia, koska kaikille vanhan taulukon elementeille pitää laskea indeksit (tiivisteet) uudelleen,
uuteen taulukkoon lisätessä.

## 09-TASK

**------------------- HOX!!! TOTEUTIN GRAPHVIZ -GRAAFIN MALLINNUKSEN -> Graph.toDotBFS()  -------------------**

Mielestäni vaikeinta tässä harjoituksessa oli leveys- ja syvyyshaun, sekä syklien tunnistus -metodien toteuttaminen.
Yleisesti metodien toteuttamisessa ja graafi-tietorakenteen ymmärtämisessä auttoivat paljon demovideot, luentokalvojen
esimerkit sekä C++:lla toteutettu demo-repository junaverkostoista.
Metodien toteuttamisessa huomasin sen, että ne olisi voinut tehdä usealla eri tavalla, riippuen toteutettavasta
rajapinnasta.
Valitsin solmu-reunuslistojen tietotyypeiksi Hashtable ja HashMap, koska ne olivat yksinkertaisia toteuttaa.

`private Hashtable<Vertex<T>, List<Edge<T>>> edgeList = null;`

`private HashMap<Integer, Vertex<T>> vertices = new HashMap<>();`

Kun tarkastelee testistä `GraphPerformanceTests` saatua dataa, huomataan että solmujen ja reunojen lukumäärän ero
suhteessa toisiinsa on melko pieni. Esimerkiksi 5 000 koodarin tiedostossa solmuja (Vertice count) on 5 000, ja
reunoja (Edge count) on 27 602. 100 000 solmun kohdalla reunoja on vastaavasti 557 681. Jokaisella graafin solmulla on
siis **keskimäärin** 4-6 reunaa (eli koodarin kaverit), joka on suhteellisen vähän. Tämän vuoksi graafi-tietorakenteessa
kannattaa käyttää reunuslistaa, koska matriisia käyttäessä suurin osa sen soluista jäisi käyttämättä. Testatut verkot
ovat siis melko harvoja (sparse), ja alla olevassa taulukossa on kuvattu matriisin täyttöaste 10 - 100 000 aineiston
kohdalla: `täyttöaste = Edge count / Vertice count^2`

Matriisin täyttöaste jäisi siis alle puoleen, jokaisen aineiston kohdalla!

<img src="images/09_task_matrixFillStats.png" alt="Matrix fill statistics" width="459">

Jos vertaillaan eroja reunuslistan `Map` ja matriisin välillä, viitaten luentokalvojen dioihin "Comparing the options",
pieni määrä reunoja sopii reunuslistalle (adjacency list), kun taas suuremmat määrät matriiseille.
Toisaalta, testeissä käsitellään koodaritiedostoja 100 000 aineistokokoon saakka, joka sisältää siis 100 000 solmua.
Matriisin koko olisi tällöin 100 000^2, ja luentoesimerkkiin viitaten jokainen 8 tavun solu yhteenlaskettuna veisi siis
100 000^2 * 8 tavua = 80 000 000 000 tavua = **80 Gigatavua!**
Matriisi-tietorakenne kuluttaisi siis aivan liikaa muistia suhteessa `Map` -reunuslistaan.

Kun koodareita haettiin aluksi graafista `getVertexFor()` -metodilla, se oli todella hidasta ja sen myötä täytön nopeus.
Siinä käytettiin silmukoita kolmessa tasossa, jolloin aikakompleksisuus on todella huono `O(n^3)`. Eli silmukoissa
haettiin ensin koodarit, sitten etsittiin niiden kaverit, ja jokaiselle koodarin kaverille solmut ja lisättiin ne
reunuslistaan.
Hajautustaulun käyttö `getVertexFor()` -metodissa korjasi tilanteen niin, että haluttu solmu löytyi nopeasti solmun
elementin tiivisteellä, jolloin aikatehokkuus parani huomattavasti, `O(1)` tasolle (tai `O(n)` huonoimmassa tapauksessa
törmäysten takia).

**----- AIKAKOMPLEKSISUUS -----**

> GraphPerformanceTests kuvaajat, kun reunuslista on `edgeList = Hashtable` ja hajautustaulu on `vertices = HashMap`

<img src="images/09_task_graphPerformanceTests_edgeList_Hashtable.png" alt="GraphPerformanceTests 1" width="1212">

> GraphPerformanceTests kuvaajat, kun reunuslista on `edgeList = HashMap` ja hajautustaulu on `vertices = Hashtable`.
> Taulukossa näkyy täyttöaste sille, jos tietorakenteena käytettäisiin **matriisia** reunuslistan sijaan.

<img src="images/09_task_graphPerformanceTests_edgeList_HashMap.png" alt="GraphPerformanceTests 2" width="1298">

En huomannut eri Map-toteutusten välillä kovinkaan suurta eroa, paitsi leveys- ja syvyyshaun nopeuksissa. Kun `edgeList`
tietotyyppi oli Hashtable, se pärjäsi hieman paremmin HashMapiin verrattuna.
Esimerkiksi 100 000 aineiston kohdalla Hashtablella kesti 515 sekuntia (BFS) ja 550 sekuntia (DFS).
HashMap -toteutuksella puolestaan kesti 592 sekuntia (BFS) ja 614 sekuntia (DFS).
Täyttöajat (Fill time) ja Dijkstran hakualgoritmi olivat kutakuinkin yhtä nopeita molemmilla toteutuksilla.

BFS ja DFS -hakujen aikakompleksisuus on `O(V + E)`, missä V = solmujen lukumäärä ja E = reunojen lukumäärä graafissa.
Aikatehokkuus perustuu siihen, että huonoimmassa tapauksessa leveys- ja syvyyshaut käyvät läpi kaikki solmut ja reunat
graafissa, jolloin aikakompleksisuus on niiden yhteenlaskettu määrä.
Mittaustulosten perusteella leveys- ja syvyyshaut olivat likimain yhtä nopeita, paitsi leveyshaku oli joidenkin
aineistojen kohdalla nopeampi.

Dijkstra-algoritmin aikakompleksisuus riippuu monesta tekijästä.
Nyt kun graafi on harva (sparse), ja dijkstra käyttää `PriorityQueue` -jonoa lajittelemaan polut pienimmän painon
mukaiseen järjestykseen, aikakompleksisuus on `O((V + E) * log V)`.
Aikatehokkuus perustuu siihen, että dijkstra hakee ensin syvyyshaulla (DFS) lyhimmän polun alkusolmusta
määränpääsolmuun, jonka aikatehokkuus on siis `O(V + E)`. Sitten jokaiselle solmulle etsitään hajautustaulun avulla sen
reunat, joka on logaritminen `O(log V)` -operaatio. Näiden solmujen reunoja `PriorityQueue` sitten vertailee
keskenään `DistanceComparator` -luokassa ja priorisoi niistä sen, jolla on lyhin polku aloitussolmuun.

Nyt kun graafin täyttö on korjattu hitaasta `O(n^3)` -toteutuksesta nopeampaan, hajautustaulua hyödyntävään
toteutukseen, aikakompleksisuus on paljon parempi.
Graafin täytössä on siis yksi `createVertexFor()` ja kaksi `getVertexFor()` -metodia
for-silmukoissa, `GraphPerformanceTests.createGraph` -toteutuksessa.
Nyt siis `getVertexFor()` on saatu hajautustaulun avulla `O(1)`-operaatioksi, ja solmun lisääminen `createVertexFor()`
taulukkoon on myös `O(1)`-operaatio, koska siinä lisätään reunaksi tyhjä taulukko `ArrayList`. Kun koodareiden
kavereille haetaan vertex ja lisätään niille suunnattu reuna, operaatio on `O(E)`.
Verkon täyttö riippuu siis lisättävien solmujen `V`, ja niiden reunojen `E` lukumäärästä, jolloin aikakompleksisuus
verkon täytölle on `O(V + E)`.