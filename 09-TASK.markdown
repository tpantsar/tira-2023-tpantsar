# Tehtävä 9

Tämän harjoituksen aiheena on **verkko -tietorakenne** (*graph*). Verkko on edistynyt tietorakenne, joten huolehdi että olet ensin tutustunut tietorakenteeseen kurssin luentojen kautta sekä tarvittaessa perehtymällä tähän tietorakenteeseen liittyvään kirjallisuuteen.

Huomaa että verkon tietorakenteiden ja algoritmien toteutuksesta on olemassa kurssilla aiemmin tehdyt **demovideot** joissa verkon algoritmeja tutkitaan C++:lla tehdystä esimerkistä. Voit hyödyntää näitä videoita kun toteutat tätä tehtävää. Löydät linkin demovideoihin kurssin Moodlesta.

Demovideot olettavat että **olet katsonut ensin** asiaan liittyvät **luentovideot**. Peruskäsitteitä ei siis demovideoilla selitetä. C++ -demon arkkitehtuuri eroaa myös jossain määrin tämän tehtävän rakenteesta, joten ota se huomioon videoita katsellessasi. Esimerkiksi Djikstran algoritmi toteutetaan tässä tehtävässä metodiksi `Graph` -luokkaan (ei omaksi luokakseen), ja Javan toteutushan tietysti yksityiskohdiltaan eroaa C++ -toteutuksesta kielten ja kirjastojen erojen vuoksi. Pääasia on yleiset käsitteet ja algoritmit mitä tästä demosta saat irti. Näistä enemmän yksityiskohtaisemmissa ohjeissa alla.

Huomautuksia demovideoista:

* Ensimmäisellä videolla näkyy luokka `AdjacencyList`. Käytännössä se on graafi eli verkko, ja nimesinkin sen myöhemmin (toinen video) uudestaan `Graph` -nimiseksi luokaksi. Se on se mitä löydät demon C++ -lähdekoodista GitHubissa (linkki alla).
* Videoilla voidaan viitata kaksi vuotta sitten toteutetun kurssin detaljeihin. Ignooraa nämä asiat ja seuraa nykyisen kurssitoteutuksen ohjeita. 
* Tänä vuonna **Primin algoritmia ei tarvitse toteuttaa**, eli voit skipata sen videon jos et halua oppia Primin algoritmin toteutusta.
* Olin videoiden tallennusaikaan aika pahassa yskässä joten pahoittelut etukäteen runsaasta köhimisestä.

Huomaa myös että tässä harjoituksessa **saa poikkeuksellisesti käyttää** Javan tietosäiliöluokkia ja algoritmeja joita tähän asti ei ole saanut käyttää:

* `Stack` -- pinotietorakenne. Toki voit käyttää myös itse toteuttamaasi pinotietorakennetta, se toimii tässä ihan yhtä hyvin jos tehtävän testit menivät läpi.
* `Queue` -- jonotietorakenne. Toki voit käyttää myös itse toteuttamaasi jonoakin, jos tehtävän testit menivät läpi.
* `PriorityQueue` -- tätä tarvitaan Dijkstran algoritmin toteutuksessa.
* `Set` -- kun tarvitaan tietosäiliötä jossa elementtien järjestyksellä ei ole väliä, mutta joka huolehtii siitä että oliot ovat säiliössä vain kerran.
* Rajapinta `List` ja sen toteutus `ArrayList` -- taulukkotietorakenne, kun tarvitaan sellaista. Demokoodissa käytetään C++:n `std::vector` -luokkaa, Javassa on myös `Vector` mutta `ArrayList` on parempi vaihtoehto koska emme tee tässä rinnakkaista ohjelmointia jolloin `ArrayList` on kevyempi ja nopeampi toteutus.
* `Map` -- rajapinta dictionary -tyyppiseen avain-arvo -tietorakenteeseen. Verkon solmut ja solmujen reunat tallennetaan tällaiseen tietorakenteeseen.
* `Hashtable` sekä `HashMap`, jotka ovat Javan `Map` -rajapinnan toteutuksia. Periaatteessa voisit tässä käyttää omaa hajautustaulun toteutustasi, mutta se ei toteuta Javan `Map` -rajapintaa joten tämä aiheuttaisi aika paljon muutoksia koodiin. En siis suosittele *oman* hajautustaulun toteutusta tähän.

Nämä kaikki rajapinnat ja luokat löytyvät Javan packagesta `java.util`.

Verkkotietorakenteen luokat ovat TIRA Codersin packagessa `oy.interact.tira.student.graph`. Täydennät ja muokkaat siellä olevien luokkien koodia ohjeiden mukaisesti.


## Lähteitä

* Kurssin luentokalvot.
* Demovideot (linkki Moodlessa).
* Kirjallisuus.
* [C++ -demon lähdekoodi](https://github.com/anttijuu/graphs)

> Kun hyödynnät C++ -demon lähdekoodia toteutuksessasi, pyri *ymmärtämään* miten koodi toimii ja miksi, peilaten luennon teoriaan ja verkkotietorakenteeseen sekä sen algoritmeihin käsitteenä,  ja *vasta sitten* toteuta koodista Java -toteutuksesi.


## Tavoite

Toteuta ja testaa verkkotietorakenne luokkineen ja algoritmeineen. Testaa toteutusta tarvittaessa sitä korjaten siten, että toteutus läpäisee testit. Tarkemmin tämän vaiheet on kuvattu alla luvussa Toteutuksen askeleet.

Testit testaavat sekä suunnattuja (directed) että suuntaamattomia (undirected) verkkoja. TIRA Coders -sovelluksen `Coder` -verkko on suunnattu. Eli jos koodari "Pertti" on mielestään koodarin "Tiina" ystävä, "Tiina" ei välttämättä pidä "Pertti" -koodaria omana ystävänään.

Kun testit menevät läpi, voit kokeilla tietorakenteen toimintaa TIRA Coders -sovelluksessa. Koska koodareilla on ystävyyssuhteita (tai ammatillisia suhteita), voimme luoda verkon koodareista sekä heidän välisistään suhteista. Tätä voidaan hyödyntää siten että kun uutta projektia ollaan perustamassa, voidaan katsoa vähän kuka ketäkin tuntee ja etsiä sitten tiimiin sellaisia koodareita jotka jo jollakin tapaa tuntevat toisensa. Tai jos haluamme tietyn gurukoodarin mukaan projektiin, voidaan katsoa kuka jo projektiin kiinnitetyistä tuntee *jotakin kautta* gurun ja voimme sitä kautta sitten häneen ottaa yhteyttä.

Näinhän sosiaalisia verkostoja yleensäkin voidaan mallintaa, samaan tapaan kuin esimerkiksi Facebook, X (tunnettu ennen nimellä Twitter) ja muut tekevät! Voit olla ehkä tietoinen käsitteestä [Six degrees of Kevin Bacon](https://en.wikipedia.org/wiki/Six_Degrees_of_Kevin_Bacon). Kevin Bacon on yksi testikoodareistamme, ja voitkin Kevinin avulla tutkia eri JSON -aineistojen kanssa kuinka pitkä polku Keviniin on mistä tahansa muusta koodarista aineistossa.

Alla esimerkki miten TIRA Codersissa toteutus näkyy:

![TIRA Coders graph frame](task-09-graph-sample.png)

Kuvasta näkee että koodareiden muodostamaa verkkoa voi algoritmisesti analysoida, esimerkiksi:

* Onko verkko **yhtenäinen** (*connected*, kaikista solmuista pääsee toisiin (ainakin lähtösolmusta)) vai **ei-yhtenäinen** (*disconnected*, ainakaan kyseisestä lähtösolmusta ei pääse kaikkiin muihin solmuihin verkossa).
* Onko verkossa **syklejä**.
* Mikä on kahden solmun välinen **lyhin polku** Dijkstran algoritmilla haettuna, lähtien 1. solmusta.
* **Valinnaisena** toteutuksena voi toteuttaa napin "Export to dot file" takana puuttuvan toiminnallisuuden, jossa verkosta luodaan visuaalinen graafi (lisää tästä ohjeen lopussa).

Analysoi ja raportoi toteutuksesi oikeellisuutta, verkkoalgoritmien aikakompleksisuutta jne. peilaten luentojen teoriaan raportissasi.


## Toteutuksen askeleet

### 1. Valmiina annettuun koodiin tutustuminen

Osa verkon toteutukseen tarvittavista yksinkertaisista apuluokista on *annettu valmiina*. Tutustu näihin **huolellisesti**, ja **vertaa** luokkia niihin käsitteisiin joita verkoista luennoilla ja kirjallisuudessa on käyty läpi:

* `Vertex<T>` -- verkon solmu, geneerinen luokka joka voi sisältää mitä vain sovelluskohtaista tietoa.
* `Edge<T>` -- verkon reuna, geneerinen luokka joka määrittelee kahden solmun välisen reunan. Huomaa että reuna sisältää sekä lähtö- että määränpääsolmun viittauksen, että reunan painon.
* `Visit<T>` -- apuluokka jota käytetään Dijkstran algoritmin toteutuksessa, määrittelee polun jota pitkin kuljetaan etsiessä lyhintä polkua lähtösolmusta määränpääsolmuun.

Varsinainen itse toteutettava koodi on enimmäkseen luokassa `Graph`. Luokan koodissa on valmiina metodien esittelyt, toteutus poistettuna. Tehtävänäsi on:

1. Tutustua tarkkaan verkkotietorakenteen perusteisiin (luennot, kirjallisuus, demovideot).
2. Toteuttaa `Graph` -luokan metodit siten että tehtävän testit menevät läpi.
3. Viimeistellä TIRA Codersin toiminnallisuus niin että voit sekä analysoida `Coders` -aineistojen verkkojen ominaisuuksia että etsiä lyhimpiä polkuja kahden koodarin välillä hyödyntäen Dijkstran hakualgoritmia.

Lue huolellisesti `Graph` -luokan metodien kommenteissa olevat kuvaukset siitä mitä algoritmin tulisi tehdä. Kuvaukset eivät luonnollisesti ole tyhjentäviä, vaan sinun täytyy ensin tuntea luentojen ja kirjallisuuden perusteella verkkotietorakenteen ja sen algoritmien perusteet. Hyödynnä myös aiemmin mainittuja C++ -toteutuksen demovideoita.

### 2. Toteutus

**Valitse ensin** tietorakenne johon solmut ja reunat tallennetaan `Graph` -luokassa jäsenmuuttujaan.

Kätevintä tässä työssä (tähän "kätevyyteen" palaat tehtävän raporttiosuudessa) on käyttää reunuslistaa (edge list) ja tallentaa solmu ja sen reunat avain-arvopareina valmiiseen Javan tietosäiliöluokkaan joka tukee avain-arvopareja. Näitä ovat `Hashtable` ja `HashMap`. Ne kaikki toteuttavat rajapinnan `Map`, joten kannattaa tehdä jäsenmuuttujaksi `Map` joka sisältää avaimena solmun `Vertex<T>` ja arvona listan reunoja `List<Edge<T>>`.

Kun jäsenmuuttujan esittelyssä sen tyyppi on `Map`, voit sitten `Graph` -luokan *muodostimessa* luoda jäsenmuuttujaan jonkun yllämainituista kolmesta toteutuksesta, esimerkiksi:

```Java
   this.edgeList = new Hashtable<Vertex<T>, List<Edge<T>>>();
```

Huomaa siis että nyt avain-arvoparit ovat siis:

1. avaimena on vertex eli verkon solmu, ja
2. arvona on lista reunoja.

Pari vinkkiä joilla pääset alkuun sen suhteen miten `Map` -rajapintaa tässä käytetään:

1. Saat `Map`:stä kaikki "entry" eli avain-arvoparit kutsumalla sen metodia `entrySet()`. Voit sitten käydä läpi kaikki solmut (vertex) ja niiden reunat (edge) for -silmukassa.
2. Saat `Map`:stä kaikki solmut (vertex) ilman reunoja kutsumalla sen `keySet()` -metodia.
3. Voit lisätä uusia avainarvopareja kutsumalla `Map`:n metodia `put(key, value)`.
4. Kun haluat jonkun tietyn avaimen (vertex) arvot (taulukon reunoja), kutsu `Map`:n metodia `get(vertex)` ja saat taulukon reunoja.

> Metodi `Graph.toString()` on valmiina toteutettuna mutta kommentoituna koodissa. Voit ottaa kommentit pois ja katsoa sopiiko se yhteen oman koodisi kanssa. Jos `Map` -jäsenmuuttujasi nimi on eri kuin koodissa, muuta jompi kumpi nimi niin että koodi toimii. Tässä on samalla esimerkki siitä miten reunuslistaa käydään läpi silmukassa, sitä siellä sun täällä tarvitaan. Metodi on hyödyllinen myös debuggausta varten; jos sinulla on ongelmia ja luulet että tietosisältö ei ole sitä mitä pitää, voit tehdä koodia joka tulostaa `Graph` -olion konsoliin. Voit sitten katsoa näyttääkö siltä että asiat on oikein vai väärin. Testit tekevät tätä, joten voit sieltä katsoa miltä verkko "näyttää".

### Map:n toteutuksen valinnasta

Huomaa että tässä sovelluksessa `Map` -rajapinnan toteutus `TreeMap` **ei ole käyttökelpoinen**. `Hashtable` ja `HashMap` käyttävät `Coder` -olioiden tunnistamisen ja vertailuun `hashCode()` ja `equals()` -metodeja. Se sopii meille mainiosti, sillä näin me tunnistamme ja erotamme toisistaan erilliset koodarit. Sen sijaan, `TreeMap` käyttää `compareTo()` -metodia. Kun katsot [luokan dokumentaatiota](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/TreeMap.html), se sanoo:

> "Note that the **ordering** maintained by a tree map, like any sorted map, and whether or not an explicit comparator is provided, **must be consistent with equals** if this sorted map is to correctly implement the Map interface. (See Comparable or Comparator for a precise definition of consistent with equals.)"

Ja meillähän tilanne oli se että aikaisempien kurssilla esiteltyjen vaatimusten vuoksi `Coder.equals` ja `Coder.compareTo` eivät vastaa toisiaan koska koodareiden joukossa on *saman nimisiä koodareita* eikä koodarin nimi voi siksi olla uniikki.

### Perusmetodien toteutus

**Toteuta seuraavaksi** `Graph`:n **perusmetodit** joita tarvitaan ensin, verteksin luominen, reunan lisääminen, jne. Näissä kaikkien toteutuksissa tarvitset `Map` -luokkaa, joten kannattaa katsoa miten sen avulla saadaan näitä avain-arvopareja hallittua. Asian pitäis olla suurin piirtein käsitetasolla tutun näköistä hommaa, sillä olet toteuttanut jo kaksi avain-arvopareja käsittelevää tietosäiliöluokkaa aiemmissa harjoituksissa.

Alla lista näistä perusmetodeista joiden tarkempi kuvaus löytyy kunkin metodin koodin kommenteista.

* `createVertexFor(T element)` -- luodaan uusi solmu verkkoon (tyhjällä reunuslistalla), lisätään se `Map`:iin ja palautetaan luotu solmu kutsujalle.
* `getVertices()` -- palauttaa joukon verkkoon lisätyistä solmuista.
* `addEdge(Edge.EdgeType type, Vertex<T> source, Vertex<T> destination, double weight)` -- lisää annetun reunatyypin mukaisen reunan lähtösolmusta kohdesolmuun annetulla reunan painolla.
* `addDirectedEdge(Vertex<T> source, Vertex<T> destination, double weight)` -- lisää suunnatun reunan lähtösolmusta kohdesolmuun tietyllä reunan painolla.
* `getEdges(Vertex<T> source)` -- antaa listan kaikista reunoista jotka lähtevät annetusta lähtösolmusta.
* `getVertexFor(T element)` -- etsii ja palauttaa solmun jossa kyseinen elementti on. Jos tällaista ei löydy, palauttaa null:n.

> **Huomaa** että verkko on todella usein joko suuntaamaton tai suunnattu. Tarkoittaa sitä että *kaikki* reunat verkossa ovat jompaa kumpaa lajia. Koodariverkko on suunnattu, mutta usea testeissä käytetty verkko on suuntaamaton.

Tämän jälkeen pitäisi olla mahdollista suorittaa alla olevassa luvussa mainittu `BasicGraphTests` -testi. Jos se testi ei mene läpi, toteutuksessa lienee jotain vikaa. Korjaa mahdolliset virheet ja jatka eteenpäin.

### Leveys- ja syvyyshakujen toteutus

**Seuraavaksi** voit jatkaa toteuttamaan leveys- ja syvyyshaut (breadth-first-search sekä depth-first search). Näitä testataan testillä `SearchGraphTests`. Testi sisältää erikseen metodit molemmille hauille, sekä suunnatuille että suuntaamattomille graafeille. Voit siis toteuttaa ensi toisen ja sitten toisen haun ja testata niitä testimetodeilla erikseen. Kun olet saanut kaikki testattua, testaa vielä koko testiluokalla kaikki haut.

Tämä testiluokka testaa metodeja:

* `Graph.breadthFirstSearch(Vertex<T> from, Vertex<T> target)` -- tehdään leveyshaku alkaen solmusta `from`. Jos solmu `target` on eri kuin `null`, haku pysäytetään kun kyseinen solmu kohdataan verkossa ensimmäisen kerran. Algoritmi palauttaa listan solmuja joiden kautta haku eteni, siinä järjestyksessä kun ne kohdattiin. Myös lähtö- ja mahdollinen määränpääsolmu on mukana tällä listalla.
* `Graph.depthFirstSearch(Vertex<T> from, Vertex<T> target)` -- tehdään syvyyshaku alkaen solmusta `from`. Jos solmu `target` on eri kuin `null`, haku pysäytetään kun kyseinen solmu kohdataan verkossa ensimmäisen kerran. Algoritmi palauttaa listan solmuja joiden kautta haku eteni, siinä järjestyksessä kun ne kohdattiin. Myös lähtö- ja mahdollinen määränpääsolmu on mukana tällä listalla.

Ennen kuin jatkat, ja jos teit muutoksia muihinkin kuin leveyshakualgoritmeihin esimerkiksi löytäessäsi mahdollisia bugeja, tee **regressiotestausta** eli suorita uudelleen myös `BasicGraphTests`. Näin huomaat jos joku muutos koodiin rikkoi aiemmin toimineen metodin.

> **Huomaa** että molemmat hakualgoritmit suorittavat haun lähtösolmusta. Algoritmi lopettaa kun se on käynyt läpi kaikki *löytämänsä* solmut (tai jos `target != null` ja kohdesolmu löytyi). Eli algoritmin ei tarvitse tarkistaa löytyikö varmasti *kaikki* verkon solmut ja jatkaa etsintää jostain toisesta paikkaa jos kaikkia ei löydetty. Onhan mahdollista että verkko ei ole yhteydellinen (*connected*) eli että jokaisesta solmusta *ei pääse* kaikkiin muihin solmuihin. Se onko verkko connected tai disconnected, tutkitaan toisessa algoritmissa.

### Verkon analyysimetodit

Tässä toteutetaan alla listatut metodit liittyen verkon analysointiin; sisältääkö verkko erillisiä komponentteja tai syklejä, alkaen tietystä solmusta. Huomaa taas lukea koodin kommentit joissa tarkempia ohjeita toteutukseen. 

* `Graph.disconnectedVertices(Vertex<T> toStartFrom)` -- metodi lähtee annetusta solmusta, tekee leveyshaun ja palauttaa ne solmut joissa leveyshaku *ei käynyt*. Jos kaikissa solmuissa käytiin, palautettu lista on tyhjä. Jos listalla on jotain, verkko on (ainakin tästä lähtösolmusta aloitettuna) disconnected eli kaikista solmuista ei pääse jokaiseen verkon solmuun. Huomaa että voi olla joku toinen solmu josta *pääsee* kaikkiin toisiin solmuihin. Eli vain yhdestä solmusta lähtien tulos ei ole vielä täysin varma, ainakaan suunnatuissa verkoissa. Mieti miksi!?
* `Graph.isDisconnected(Vertex<T> toStartFrom)` -- metodi palauttaa `true` jos verkko tästä solmusta lähtien erillinen eli lähtösolmusta ei pääse kaikkiin muihin solmuihin. Hyödynnä edellistä metodia tämän toteutuksessa.
* `Graph.hasCycles(EdgeType edgeType, Vertex<T> fromVertex)` -- metodi palauttaa `true` jos verkossa on syklejä. Tämänkään algoritmin ei tarvitse toimia täysin oikein verkossa jossa on erillisiä komponentteja. Eli algoritmin toteutuksessa riittää se, että tutkitaan onko syklejä *lähtien annetusta solmusta*, niihin solmuihin joihin siitä pääsee. Metodissa voit aluksi tarkistaa ensin onko verkko disconnected alkaen annetusta solmusta (tai ensimmäisestä solmusta), ja jos on, algoritmi voi palauttaa suoraan `false`. Muuten tutki onko annetusta solmusta lähtien syklejä. Huomaa että algoritmi syklien etsimiseen suunnatusta ja suuntaamattomasta verkosta on eri (kts. luennot). Siksi parametri `EdgeType`.

### Dijkstran lyhimmän lopun algoritmin toteutus

Toteuta Dijkstran lyhimmän polun hakualgoritmi. Tässä haetaan lyhintä polkua *reunojen painojen mukaan*, lähtösolmusta kohdesolmuun. Tässä lyhin polku ei siis tarkoita reunojen lukumäärää solmujen välillä, vaan pienintä reunojen painojen summaa lähtö- ja kohdesolmun välillä. Esimerkiksi rautatieasemien etäisyyttä kilometreinä.

Varmista luentomateriaalista että olet ymmärtänyt algoritmin toimintaperiaatteen, ja lisäksi saat vinkkejä toteutukseen kurssin C++ -demosta.

* Toteuta `Graph.DijkstraResult<T> shortestPathDijkstra(Vertex<T> start, Vertex<T> end)`, jossa parametreina lähtö- ja kohdesolmu.
* Kuten luennoista ja demoista näet, tarvitset tässä **apualgoritmeja**. Näistä tarkemmin luentomateriaalissa ja koodissa olevien toteuttamattomien `Graph`:n metodien yhteydessä olevissa kommenteissa.
* Lisäksi käytät kurssilla ensimmäistä kertaa prioriteettijonoa `PriorityQueue`. Prioriteettijonon tarkoitus on pitää sisällään tähän mennessä lyhimpiä polkuja lähdesolmusta, etsittäessä lyhimpiä polkuja kohteeseen. Prioriteettijonoa käyttäessäsi sen tarkoitus on järjestää solmut jonoon järjestykseen, niiden etäisyyden (reunojen painon) mukaan toisistaan, siten että lyhin etäisyys on prioriteettijonossa aina kärjessä. 

Metodi palauttaa `DijkstraResult` -tyyppisen luokan, jossa jäsenmuuttujat kertovat haun tuloksesta:

* `pathFound` -- löytyikö polku lähtösolmusta kohdesolmuun vai ei.
* `path` -- lista jossa lyhin polku lähtösolmusta kohdesolmuun. On `null` jos polkua ei löytynyt.
* `steps` -- kuinka monta askelta (reunaa) polulla on.
* `totalWeight` -- mikä on yhteenlaskettu polun reunojen painojen summa.


Kun olet saanut toteutuksen testikuntoon, testaa sitä `DijkstraSearchTests` -testillä.

### 3. Yhteenveto oikeellisuustesteistä ja lisää koodaamista

Seuraavat testit auttavat toteutuksen oikeellisuuden testaamista, eri vaiheissa toteutuksesi etenemistä:

* `BasicGraphTests` -- testataan yksinkertaisia pieniä suunnattuja ja suuntaamattomia verkkoja ja tutkitaan onko verkkoon lisätyt solmut ja reunat sitä mitä pitäisi. Voit siis suorittaa tätä testiä aika aikaisessa vaiheessa toteutusta (solmuja ja reunoja voidaan lisätä tietorakenteeseen).
* `SearchGraphTests` -- kun olet toteuttanut leveys- ja/tai syvyyshaun, voit käyttää tämän testin testifunktioita testaamaan näiden hakujen oikeellisuutta.
* `DisconnectednessTests` -- kun olet toteuttanut algoritmin jolla testataan onko verkko yhtenäinen (`Graph.disconnectedVertices()`; onko se connected vai disconnected), voit testata tällä testillä sen algoritmin oikeellisuutta.
* `CyclesTests` -- kun olet totettanut metodin `Graph.hasCycles`, voit kokeilla toimiiko se, tätä testiä suorittamalla.
* `DijkstraSearchTests` -- kun olet toteuttanut Dijkstran lyhimmän polun hakualgoritmin, voit testata tällä testillä sen oikeellisuutta. Testi testaa tätä erilaisilla verkoilla, mukaanlukien C++ -demojen Suomen junaverkostoaineistolla.

Suorituskykytestit kannattaa suorittaa vasta ihan lopuksi, ne löytyvät `GraphPerformanceTests` -testistä. Niistä enemmän alempana. Kun olet suorittanut suorituskykytestit, huomaat että tarvitaan vielä **pieni lisäys koodiin** jotta tehokkuus on riittävä.

Mutta ensin voit kokeilla miten homma toimii TIRA Codersissa...

### Koodariverkostojen tutkiminen TIRA Codersissa

Kun olet testannut toteutuksen oikeellisuutta, kokeile myös miten toteutuksesi toimii TIRA Codersin käyttöliittymän kautta.

Käyttöliittymässä `TIRACodersApp` ja luokassa `PhoneBookBase` on jo valmiiksi toteutettuna koodi joka luo `Graph` -luokan ja sijoittaa suoritushetkellä muistiin ladatut koodarit verkkotietorakenteeseen.

**Tutustu** koodiin `PhoneBookBase.createGraph()` ja tutki millä logiikalla koodarit ja heidän ystävyyssuhteensa luodaan verkkotietorakenteeseen.

Kokeile sen toimintaa valitsemalla valikosta komento "Graph Coders". Näkyviin avautuu uusi ikkuna (toteutus luokassa `CoderGraphFrame`) jossa painikkeita ja valintalistoja.

Voit valita kaksi eri koodaria listalta, ja etsiä toteuttamallasi Dijkstran lyhimmän polun hakualgoritmilla lyhimmän polun koodareiden ystäväverkostoja pitkin ensimmäisestä koodarista toiseen. Tunteeko joku esimerkiksi Kevin Baconin!? 

Lisäksi voit painikkeen "Graph Properties" avulla saada tietoa verkosta. Tämä tutkii toteuttamillasi algoritmeillä onko verkossa syklejä alkaen valitusta 1. koodarista, tai onko verkko tästä valitusta koodarista tutkien yhtenäinen vai ei.

Painikkeen "Export to dot file" takana on **valinnainen** lisätehtävä. Jos haluat, voit tehdä lisäpisteiden saamiseksi tämän tehtävän joka on esitelty tarkemmin alla valinnaisten tehtävien osiossa.


## Aikatehokkuuden testaus

Aikatehokkuutta testaa testi `GraphPerformanceTests`. Se tulostaa konsoliin tietoa etenemisestä sekä aikamittauksista. Voit siitä saada jotain tietoa verkon eri algoritmien aikatehokkuuksista.

Testi varmistaa myös sen, että aineistossa oleva, verkkoon laitettujen verteksien ja reunojen lukumäärä pitää paikkaansa tiedostosta luetun testidatan suhteen.

Kun suoritat testin, huomaat että testi tallentaa tiedostoon `compare-graph.csv` tietoa eri verkon algoritmien aikatehokkuuksista. Esimerkki opettajan eräästä testiajosta, *vain alkupäästä* aineistoa:

| Vertice count | Edge count | Fill time (ms) | Fill time/V+E (ms) | BFS time (ms) | DFS time (ms) | Dijkstra time (ms) | Testfile                 |
|--------------:|-----------:|---------------:|-------------------:|--------------:|--------------:|-------------------:|-------------------------:|
|            10 |         40 |              1 |             0.0200 |             0 |             0 |                  2 |   10-village-coders.json |
|           100 |        532 |              3 |             0.0047 |             1 |             0 |                  5 |     100-city-coders.json |

Jossa V on verteksien lukumäärä ja E on reunojen lukumäärä graafissa.

**Analysoi** testattujen **algoritmien aikatehokkuutta** peilaten mittauksiasi luennoilla käsiteltyihin eri verkkoalgoritmien aikakompleksisuusluokkiiin sekä aineiston kokoihin.

Yllä ohjeistettiin tekemään jäsenmuuttuja jossa solmut ja reunalistat ovat, tietotyyppinä rajapinta `Map`. Valitsit jonkun kahdesta (`Hashtable` tai `HashMap`) konkreettiseksi tietotyypiksi jonne solmut ja reunalistat tallennetaan.  Nyt kun olet tehnyt aikatehokkuusmittauksia valintasi kanssa, **vaihda** toteutus toiseksi. 

Jos vaikka aiemmin käytit `Hashtable` -toteutusta, valitse nyt sen tilalle `HashMap`. **Vertaa** tämän toisen toteutuksen aikatehokkuutta ensimmäiseen valintaasi. Kumpi oli nopeampi, vai oliko merkittävää eroa ollenkaan?

### Verkon täytön hitaus ja sen korjaaminen

Huomaat todennäköisesti että **aineiston koon kasvaessa** verkkoon solmujen ja reunojen lisääminen **hidastuu merkittävästi**. Opettajan koneella aineisto jossa on 50 000 koodaria (vertex) ja yhteensä 280 174 ystävyyssuhdetta (edge), verkkotietorakenteen täyttäminen (*eräällä* `Map` -toteutuksella joka oli nopein) näillä kesti 416 813 millisekuntia, joka on noin **seitsemän minuuttia**. Hitaammalla koneella tämä kestää vielä kauemmin.

Tähän on olemassa **ratkaisu**! Kun tämän toteuttaa, 50 000 aineiston lisääminen verkkoon kestää enää noin 100 millisekuntia! Nopeus riippuu tietysti koneesta, tämä opettajan koneella. Mutta tällä korjauksella oman toteutuksesi nopeus pitäisi parantua samassa suhteessa. Toteutus on vielä helppo, joten kannattaa toteuttaa se!

Ensin analyysiä mistä hitaus johtuu? Keksitkö itse, kun tutkit koodia joka lisää solmuja ja reunoja verkkoon??

Kuten huomaat aikatehokkuutta mittaavasta testikoodista ja `PhoneBookBase.createGraph()` -metodin toteutuksesta, solmuja ja reunoja verkkoon lisätessä kutsutaan usein metodia `Graph.getVertexFor(T)`. Ainoa tapa toteuttaa tämä `Map` -tietorakenteen kanssa, on **ensin** hakea verkon solmut (`Map`:n K eli key) kutsumalla metodia `Map.keySet()` ja **sitten** *silmukassa* käydä läpi `Set` -tietosäiliö, katsoa onko tämä se etsittävä vertex jossa on se haettava olio T, ja palauttaa vertex jo se oli. 

Nyt kun analysoit miten verkkoon olioita lisätään, se menee näin (kts esimerkiksi `PhoneBookBase.createGraph()` -metodi tai `GraphPerformanceTests.createGraph` -metodi):

1. Lisätään kaikki koodarit vertekseiksi eli solmuiksi verkkoon, metodilla `createVertexFor(coder)`. Tämä on O(V) koska yhden solmun lisääminen on O(1).
2. Toisessa peräkkäisessä silmukassa, jokaiselle koodarille, kutsutaan ensin `getVertexFor(coder)` että saadaan se koodariolio sieltä verkosta,  ja sen jälkeen jokaiselle koodarin ystävälle luodaan tilapäinen koodariolio pelkällä koodarin UUID:llä ja haetaan senkin verkkotietorakenteesta metodilla `getVertexFor(new Coder(friendID))`. Tämän jälkeen voidaan lisätä verkkoon reuna koodarin ja hänen ystävänsä kanssa.

Nyt ainoa tapa saada tuolla metodissa `getVertexFor()` se koodari on kutsua `Map.keySet()` ja sen jälkeen **silmukassa** käydä läpi kaikki vertexit ja palauttaa se oikea haettava vertex jossa tuo koodariolio on.

Eli meillä on silmukoita:

```
   for kaikille koodareille coder
	   **hae vertex** koodarille coder
		hae koodarin frendit
		for kaikille koodarin frendeille
		   **hae vertex** koodarikaverille, friendCoder
			lisää reuna coder -> friendCoder
		end for
	for
```

Mutta -- nuo kaikki kutsut "hae vertex ..." sisältävät **myös** for -silmukan siellä `getVertexFor()` -metodissa! Koska se on *ainoa* tapa tehdä asia; löytää se oikea vertex `Map`:stä. Javan `Set` tai edes `HashSet` ei tarjoa *mitään* nopeaa metodia jolla saisi tietyn yhden olion ulos setistä vaikka hajautusavaimella! Eli `Set`:stä kun haetaan tiettyä olioita, se vaatii *aina* silmukan!

Asiasta on hyvä kommentti myös [Stack overflow:ssa](https://stackoverflow.com/a/18380755/10557366):

> "To answer the precise question "Why doesn't Set provide an operation to get an element that equals another element?", the answer would be: because the designers of the collection framework were not very forward looking. They didn't anticipate your very legitimate use case, naively tried to "model the mathematical set abstraction" (from the javadoc) and simply forgot to add the useful get() method."

Käytännössä tuossa on siis silmukoita seuraavasti:

```Java
   for kaikille koodareille coder
	   for -silmukassa, hae vertex koodarille coder
		hae koodarin frendit
		for kaikille koodarin frendeille
		   for hae vertex koodarikaverille, friendCoder
			lisää reuna coder -> friendCoder
		end for
	for
```
Eli useampiakin silmukoita, ei kahdessa tasossa kuten näyttäisi, vaan *kolmessa* tasossa, eli aikakompleksisuus on tässä jo todella huono (O(n^3)). Ja sehän näkee koodin suoritusajoistakin!

Onneksi tämän ongelman voi ratkaista itse, tavalla joka huimasti nopeuttaa metodin `getVertexFor()` -toteutusta:

1. Tee `Graph` -luokkaan toinen jäsenmuuttuja, hajautustaulu, jossa avaimena on `Integer` ja arvona on `Vertex<T>`. Nimeä tämä jäsenmuuttuja vaikka nimellä `vertices`.
2. Kun lisäät metodissa `createVertexFor(T element)` elementin siihen alkuperäiseen reunuslistaan, lisää luotu vertex **myös** tähän uuteen hajautustauluun `vertices`, avaimena lisättävän elementin (ei vertexin) hash ja arvona tämä vertex (jossa elementti on sisällä).
3. Muuta metodin `getVertexFor(T element)` toteutusta siten ettet haekaan verteksiä reunuslistan `keySet()`:iä kutsumalla ja for -silmukassa sitä läpikäymällä, vaan hae se `vertices` -hajautustaulusta elementin `hashCode()` -metodia kutsumalla ja käyttämällä sitä hajautusavainta avaimena hajautustauluun. 

Näin saadaan vältettyä aika monta silmukan pyörittelyä joita `Set`:n käytössä muuten ei voida välttää.

Käytännössä kun tuo **hae koodari** oli O(n) -operaatio, siitä on tehty nyt hajautustaulun avulla O(1) -operaatio. Hintana tälle on lisääntynyt muistikompleksisuus -- verteksien kuluttama muisti tuplaantuu eli sovelluksen *muistikompleksisuus* kasvoi.

**Kokeile** miten muunneltu toteutuksesi läpäisee testit ja paljonko verkkoon lisäämisen aika väheni. Varmista myös regressiotestaamalla että *kaikki* tehtävän testit edelleen menevät läpi. Raportoi tulokset omassa tehtävään liittyvässä raportissasi. 


## Valinnaiset toteutukset

**Valinnaisena toteutuksena** tässä harjoituksessa (josta siis saa lisäpisteitä jos se toimii), on toteuttaa metodi `Graph.toDotBFS(Vertex<T> from, String outputFileName)`. Metodi luo [GraphViz](https://graphviz.org) -graafin sovelluksessa ladattuna olevasta verkosta, tallentaen sen annettuun tiedostoon.

Sen jälkeen (kun olet asentanut GraphViz:n konellesi) voit käyttää annettua komentorivityökalua `graph-dot.sh` (Windowsissa kopioi komento ja suorita se komentorivillä) ja luoda [SVG](https://en.wikipedia.org/wiki/SVG) -tiedoston luodusta GraphViz -tiedostosta. Tiedoston sisällön tulee näyttää tältä (kolmen koodarin verkon esimerkki exportattuna malliratkaisusta):

```dot
digraph "CoderFriends" {
   node [shape=circle, style="rounded,filled"]
   beautify=true
   overlap="scale"
   "-2093654294" [ label="" tooltip="Bacon Kevin (R∀ndom Package)"  color="antiquewhite" root=true ]
      "-2093654294" -> "460269229" [color=gray, tooltip="Bacon Kevin (R∀ndom Package) -> Juustila Antti (∫inal ⟐verflow)"]
      "-2093654294" -> "-256213089" [color=gray, tooltip="Bacon Kevin (R∀ndom Package) -> Lappalainen Jouni (∫inal Re¢ursion)"]
   "460269229" [ label="" tooltip="Juustila Antti (∫inal ⟐verflow)"  ]
      "460269229" -> "-2093654294" [color=gray, tooltip="Juustila Antti (∫inal ⟐verflow) -> Bacon Kevin (R∀ndom Package)"]
      "460269229" -> "-256213089" [color=gray, tooltip="Juustila Antti (∫inal ⟐verflow) -> Lappalainen Jouni (∫inal Re¢ursion)"]
   "-256213089" [ label="" tooltip="Lappalainen Jouni (∫inal Re¢ursion)"  ]
      "-256213089" -> "-2093654294" [color=gray, tooltip="Lappalainen Jouni (∫inal Re¢ursion) -> Bacon Kevin (R∀ndom Package)"]
      "-256213089" -> "460269229" [color=gray, tooltip="Lappalainen Jouni (∫inal Re¢ursion) -> Juustila Antti (∫inal ⟐verflow)"]
}
```

Numerot ovat kunkin verteksin eli solmun sisältämän koodarin *tiiviste* (hash; saadaan verteksin `hashCode()` -kutsulla), ja nimet saadaan kutsumalla verteksin `toString()` -metodia. Kun avaat svg -tiedoston ohjelmalla joka osaa niitä näyttää, liikuttamalla hiirtä solmujen ja reunojen päälle, näet tooltip:ssä solmun koodarin nimen ja reunalla molempien koodareiden nimet. Useat selaimet osaavat näyttää svg-tiedostoja, mutta svg -editoritkin pystyvät graafin näyttämään.

 Näin voit visuaalisesti tarkastella minkälainen graafi syntyy esimerkisi 103 koodarin aineistosta (luettu tiedostosta `100-city-coders.json` ja lisätty annetun koodin toimesta kolme testikoodaria):

![Aineistosta luotu graafi](task-09-dot-graph-sample.png)

Keskellä koodari Jouni Lappalainen. Oma mallitoteutukseni korostaa tämän lähtösolmun eri värillä (`color="antiquewhite"`). Jos lähtösolmusta edeten kaikkia verkon solmuja ei käydä läpi (verkko on tästä solmusta lähtien disconnected), *jatketaan* verkon läpikäyntiä joistakin ei-käydystä solmusta, jolloin se taas korostetaan (solmu n. kello 10 kohdalla ulkoreunalla).

Käytännössä tämän voi toteuttaa vaikka custom leveyshakuna jossa solmuissa vieraillen tallennetaan solmut ja reunat GraphViz -tiedostoon, varmistaen aina että jos kaikkia solmuja ei vieläkään ole käyty läpi, jatketaan jostain ei-vieraillusta solmusta.

> Huomaa että jos generoit graafin todella isosta aineistosta, tiedoston generointi kestää kauan, siitä graafin generointi myös kestää kauan, ja graafin avaaminen jollakin sovelluksella on hidasta ja se vie paljon muistia. Eli suurilla aineistoilla varaudu odottamaan ja varaudu siihen että generointi ei onnistu.


## Raportti

**Kirjaa** raporttiisi `RAPORTTI.markdown` mitä opit tehtävän tekemisestä, mikä oli vaikeaa, mikä helppoa, jne.

Analysoi toteutuksesi oikeellisuutta ja aikatehokkuutta testien avulla.

**Arvioi** aikatehokkuustestin tulostusten perusteella, ovatko testatut verkot mielestäsi harvoja (*sparse*) vai tiheitä (*dense*)? 

Onko valittu reunuslista `Map`:llä toteutettuna mielestäsi tämän tyyppisissä verkoissa/sovelluksissa kuin TIRA Coders, oikea ratkaisu verkon sisäiseksi tietorakenteeksi, vai olisiko *matriisi* ollut tietorakenteena parempi?

Huomasit performancetestillä, että verkon täyttäminen testiaineistosta tulee hitaaksi isoilla aineistoilla. Analysoi koodiasi ja perustele miksi näin käy. Miksi ohjattu "korjaus" paransi aikatehokkuutta?

Kokeilit eri `Map` -toteutusten (`Hashtable`, `HashMap`) aikatehokkuuksia testatessasi verkon algoritmien aikatehokkuutta. Raportoi mikä näistä oli nopein toteutus testiesi perusteella? Olivatko nopeuserot suuria? Oliko joku algoritmeista nopeampi yhdellä toteutuksella mutta sitten joku toinen algoritmi olikin hitaampi?

Käytit erilaisia aputietorakenteita eri verkkoalgoritmien toteutukseen. Jos kokeilit vaihtaa niitä sopiviin toisenlaisiin Javan tietorakenteisiin (esim. `ArrayList` vs. `Vector`), oliko niillä suoritusaikaeroja? 

Sisällytä raporttiisi toteutusten aikatehokkuusvertailujen tulostuksia ja niihin liittyviä graafeja taulukkolaskinohjelmasta. Tässä hyödynnä testin tallentamaa tiedostoa `compare-graph.csv`.

Arvioi eri toteuttamiesi algoritmien aikatehokkuutta suhteessa luentojen ja kirjallisuuden teoriaan. Perustele analyysisi toteutuksesi koodin perusteella.

Tässä tehtävässä on aiempina vuosina ollut aika yleistä mainita että "verkon aikatehokkuus on O(n)". Tässä on useampikin **virhe**:

1. Verkon *eri* algoritmien aikatehokkuudet ovat *erilaisia*. Verkon täyttämisen big-O on *eri* kuin vaikka Dijkstran hakualgoritmin big-O. Arvioi siis *kunkin* algoritmin aikatehokkuutta *erikseen*.
2. Verkkojen tapauksessa ei ole yhtä n:ää joka määrittelee aikakompleksisuuden. On olemassa V ja E, joista aikakompleksisuus määräytyy. Kertaa luennot eri algoritmien osalta ja pohdi ovatko toteutustesi aikakompleksisuudet samanlaisia.

Älä siis toista tätä analyysivirhettä omassa raportissasi.


## Lopuksi

Kun olet valmis, varmista että sekä raportti että kaikki koodi on paikallisessa git -repositoryssäsi ja myös etärepositoryssäsi (komennot `git commit`, tarvittaessa uusille tiedostoille `git add` sekä `git push`).


## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.