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

Täyttöajan kasvu suhteessa n:n kasvuun:

<img src="images/02_task_fillFunction.png" alt="Täyttöaika" width="500"/>

Hakuaikojen kasvu suhteessa n:n kasvuun:

<img src="images/02_task_searchFunction.png" alt="Hakuaika" width="500"/>

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

<img src="images/02_task_TiraCoders_logView.jpg" alt="Hakuaika" width="400"/>

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

Binäärisen puolitushakualgoritmin toteutuksessa piti jonkin aikaa miettiä, miten metodin binarySearch saa palauttamaan
arvon -1,
kun muuttujaa aValue ei löydy fromArray -taulukosta. Ratkaisuna tähän oli tarkistaa, jos fromIndex on suurempaa tai yhtä
suurta kuin fromArray -taulukon pituus fromArray.length.
Myös aikakompleksisuusluokkien määrittämisessä piti käyttää jonkin verran ajatus- ja tutkimustyötä.

Tehtävässä oli suhteellisen helppoa toteuttaa itse koodauspuoli, jossa debuggerin käyttö auttoi merkittävästi.
Rekursiivisen puolitushaun `public static <T> int binarySearchRecursive(T, T[], int, int, Comparator<T>)` käyttäminen aiheutti pinon ylivuodon,
joten päätin toteuttaa myös iteratiivisen version `public static <T> int binarySearchIterative(T, T[], int, int, Comparator<T>)` ja käytin sitä tässä harjoituksessa.

Kuvat binäärisen puolitushakualgoritmin suoritusajoista **nousevan ja laskevan** järjestyksen mukaisesti:

<img src="images/binary_search_ascending.png" alt="Binary search ascending" width="1500"/>

<img src="images/binary_search_descending.png" alt="Binary search descending" width="1500"/>

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
Esimerkiksi listan lopusta hakemalla sukunimeä **Översti**, haku kesti 26 ms, kun taas listan alusta nimellä **Aallonen**, 0 ms.

Koodarin nopea haku koko nimellä "Exact (last first)" kesti sekin vain 0 ms riippumatta siitä, mistä kohtaa listaa nimeä
haettiin -> "PhoneBookArray: Fast search took 0 ms".

Koko nimellä hakeminen toimii vain silloin, kun taulukko on lajiteltu nousevaan tai laskevaan järjestykseen suku- ja
etunimen perusteella.


## 04-TASK


Opin pinotietorakenteen toteutuksessa sen, miten IDE:t tarkistavat syntaksivirheitä lähdekoodista, esim. edellätoteutetun
`ParenthesisChecker.java` mukaisesti. Koodieditorit varoittavat automaattisesti, jos koodista puuttuu avaavia tai sulkevia
sulkuja tai jos ne ovat väärässä järjestyksessä.

`StackImplementation.java` aikakompleksisuusvaatimuuksia piti jonkin verran korjata esim. `clear()` -metodin kohdalla.
Lisäksi, haastavaa oli myös toteuttaa lainausmerkkien, rivinumeroiden ja sarakenumeroiden käsittely `checkParentheses` -metodissa.
Ymmärsin periaatteen kuitenkin hyvin ja sulkujen tarkistaminen onnistui hyvin myös omien JSON ja Java -tiedostojen kanssa.

Aikakompleksisuusvaatimukset pinotietorakenteen toteutuksessa täyttyvät, koska vain `push()` ja `toString` -metodit käyttävät
silmukoita hyväkseen. Jos indeksi, johon viimeisin elementti on lisätty, ylittää pinon kapasiteetin `capacity`, niin tehdään reallokointi
`reallocateArray`. Reallokoinnissa luodaan uusi taulukko-olio, tuplataan sen kapasiteetti vanhaan taulukkoon nähden ja
lisätään vanhan taulukon elementit uuden taulukon alkuun for-silmukassa:
```Java
for (int i = 0; i <= currentIndex; i++) {
    newArray[i] = itemArray[i];
}
```
Tämän vuoksi `push()` -metodi on aikakompleksisuusluokaltaan `O(n)`, kun tehdään reallokointi.

Myös `toString()` noudattaa aikakompleksisuusluokkaa `O(n)`, koska for-silmukassa erotetaan taulukon elementit pilkulla toisistaan:
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
Nähtävästi algoritmi osaa tunnistaa rivinumeron oikein, mutta sarakkeita laskiessa se väittää virheen olevan sarakkeessa 1587.
Algoritmi myös tunnistaa virhetyypin oikein -> "Too few closing parentheses".
Eli tässä tapauksessa lainausmerkkejä on liian vähän:

<img src="images/04_task_faultyQuotationMarks.png" alt="Check json file 1" width="2000"/>

Toisaalta, kun lainausmerkkejä on tiedostossa liikaa, tässä tapauksessa algoritmi väittää JSON-tiedoston olevan oikeellinen
-> "JSON file test-village-coders.json is valid".
Eli algoritmi ei ole oikeellinen, koska se ei tuota toivottua tulosta siihen, mitä pitäisi. Se antaa jokseenkin oikeita tuloksia riippuen siitä,
missä kohtaa tekstiä ja kuinka paljon lainausmerkkejä puuttuu tai on liikaa. 
Algoritmin toteutusta pitäisi muuttaa niin, että otetaan huomioon myös lainausmerkkien välissä olevat lainausmerkit.

<img src="images/04_task_faultyQuotationMarks_okMessage.png" alt="Check json file 2" width="2000"/>


## 05-TASK


**------------------- HOX!!! TOTEUTIN JONOTIETORAKENTEESTA SEKÄ TAULUKKOVERSION, ETTÄ LINKITETYN LISTAN -------------------**

Mielestäni taulukkoversion toteutus jonotietorakenteista oli helpompi ja yksinkertaisempi, verrattuna linkitettyyn listaan. Haastetta toi mm. linkitetyn listan solmujen hallinta.

Linkitetyn listan etuna on se, että reallokointia ei tarvitse tehdä, kun uusia elementtejä lisätään listaan. Ainoana rajoitteena on siis tietokoneen käyttämän RAM-muistin määrä. Koodissa linkitetyn listan maksimikooksi on määritelty Integer.MAX_VALUE, eli suurin mahdollinen kokonaislukumuuttujan arvo. Linkitetty lista huolehtii siis itse muistin lisäämisestä tai vähentämisestä.

Linkitetty lista häviää aikakompleksisuudeltaan ja tehokkuudeltaan selvästi taulukkototeutukseen verrattuna, isojen tietorakenteiden kanssa.
Syynä on se, että solmujen välisten yhteyksien ylläpitäminen kuluttaa paljon keskusmuistia, koska niiden välillä tallennetaan osoittimia (pointer) päästäkseen seuraavaan solmuun. Vaikka nykyisissä tietokoneissa keskusmuistia on jo riittävästi, vastaan tulee kuitenkin suoritusaika, kun käsitellään suuria tietomääriä linkitetyn listan avulla.
Artikkelissa "RIP Linked List, Sonntag & Colnet (2023), https://arxiv.org/pdf/2306.06942.pdf" mainitaan myös, että linkitetyn listan hitaus perustuu siihen, että esim. viitatessa satunnaiseen listamuotoisen testidatan elementtiin (solmuun), se vaatii paljon muistia. Listan solmut täytyy siis käsitellä järjestyksessä, joko alusta tai lopusta lähtien, kunnes löydetään haluttu elementti. Testi perustuu Bjarne Stroustrupin muunneltuun suorituskykytestiin, jossa mitataan aikakompleksisuutta elementtien lisäämisessä suureen tietorakenteeseen (taulukkoon). Kappaleen 4. kuvaajasta **(Fig. 9)** nähdään, kuinka linkitetyn listan (LinkedList) aikakompleksisuus on selvästi heikompi, kuin taulukkototeutuksen (ArrayBlock).

Kun taulukkototeutuksessa pusketaan elementtejä listaan enqueue-metodia käyttäen, reallokointi-metodia `reallocateArray()` kutsutaan jos taulukon sisältämien elementtien määrä `count` saavuttaa tai ylittää sen kapasiteetin.

**Taulukkototeutus, edut ja haitat:**

(+) Kuluttaa vähemmän muistia.

(+) Yksinkertaisempi toteuttaa.

(+) Satunnaiseen elementtiin viittaaminen on nopeaa, koska elementit on tallennettu vierekkäin jonoon, aikakompleksisuus `O(1)`.

(-) Reallokointi voi olla aikaavievä operaatio, jos taulukon kapasiteetti ylittyy. Tämä riippuu taulukon koosta n, jolloin aikakompleksisuus on
`O(n)`. 

**Linkitetty lista, edut ja haitat:**

(+) Dynaaminen koko, kätevämpi pienten tietorakenteiden kanssa. Ei tarvitse reallokointia.

(+) Ei varaa ylimääräistä muistia käyttöönsä, toisin kuin reallokointi taulukkototeutuksessa.

(+) Elementtien lisääminen ja poistaminen nopeaa, aikakompleksisuus `O(1)`.

(-) Viittaus satunnaisiin elementteihin hidasta, koska lista täytyy käydä läpi solmujen kautta. Aikakompleksisuus `O(k)`, jossa `k` vastaa haetun elementin sijaintia joko listan alusta tai lopusta luettuna.


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

<img src="images/CodersSlowComparatorTests_sort.png" alt="CodersSlowComparatorTests" width="1500"/>

> Näissä käytettiin nopeita quicksort -lajittelualgoritmeja `CodersFastComparatorTests` -testissä.
> Sekä rekursiivinen, että iteratiivinen toteutus.
> Lajittelun kokonaiskesto on likimain sama riippumatta siitä, kumpaa toteutusta käytettiin.

<img src="images/CodersFastComparatorTests_sort.png" alt="CodersFastComparatorTests" width="1500"/>

Insertion sort -lajittelussa kesti jopa 9 minuuttia, 100 000 elementin kohdalla.
Quicksort -algoritmi puolestaan selviytyi 2 000 000 elementin kokoisesta aineistosta vain hieman yli 11 sekunnissa.
* Insertion sort -algoritmin aikakompleksisuusluokka on `O(n^2)` eli neliöllinen,
koska se käsittelee elementtejä kahdessa for-silmukassa.
Jokainen taulukon elementti täytyy käydä erikseen läpi siitä lähtien, kun taulukossa vaihdetaan elementtien paikkaa keskenään `swap` -metodissa.
Tämä tekee lisäyslajittelusta äärimmäisen hitaan, suurien aineistojen kohdalla.
* Quicksort -algoritmin aikakompleksisuusluokka on `O(n*log n)`,
koska aineisto jaetaan suunnilleen samankokoisiin osiin jokaisella rekursiotasolla.
Jos taulukon järjestettävät alkiot ovat jo valmiiksi järjestyksessä, quicksort vaatii aikakompleksisuudeltaan `O(n^2)` lajittelua (worst-case scenario).

Rekursiivisen ja iteratiivisen quicksort -toteutuksen **aikaerot** eivät juuri poikkea toisistaan.
Toteutusten olennainen ero on siinä, että rekursiivinen algoritmi kutsuu itseään useita kertoja,
kun taas iteratiivisessa toteutuksessa pysytään while-silmukassa ja kutsutaan partitiointimetodia sen sisällä.
Iteratiivisessa toteutuksessa hyödynnetään erillistä pinotietorakennetta (tehtävän task_04 `StackImplementation` -luokassa),
jossa käsiteltävän taulukon indeksejä pidetään muistissa. Tämän ansiosta kutsupinoa (call stack) ei tarvita taulukoiden ylläpitämiseen,
toisin kuin rekursiivisessa toteutuksessa.

TIRA Coders App nopeutui huomattavasti, kun lajittelu vaihdettiin nopeaan quicksort-algoritmiin.
Kokonaiskesto esim. 50 000 elementin json-tiedoston lajittelulle nopeutui n. **4,5 -kertaisesti**.

> Hidas Insertion sort -algoritmi Tira Coders Appissa. Aineistona 10 000 ja 50 000 elementin json-tiedostot:

<img src="images/insertionSort_TIRACodersApp.png" alt="TIRA Coders Insertion sort" width="1500"/>

> Nopea quicksort -algoritmi Tira Coders Appissa. Aineistona 10 000 ja 50 000 elementin json-tiedostot:

<img src="images/quickSort_TIRACodersApp.png" alt="TIRA Coders Quicksort" width="1500"/>


## 07-TASK





## 08-TASK





## 09-TASK