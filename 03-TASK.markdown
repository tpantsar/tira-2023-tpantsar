# Tehtävä 3

Älä tee tätä tehtävää ennenkuin olet tehnyt [tehtävän 2](02-TASK.markdown).

> Tässä tehtävässä kirjoitat noin 15-20 riviä koodia, riippuen koodaustyylistä. Kirjoitat kaksi metodia joissa on tämä määrä koodia, mutta toinen on 99% sama kuin ensimmäinen metodi.

## Lähteitä

* Kurssin Luentokalvot.
* Liveluento (tallenne) ja sen vinkit ja esimerkit.
* Kirjallisuus.
* Javan [`Comparator` -rajapinta](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/util/Comparator.html).


## Yleistä

**Edellisessä tehtävässä** toteutit yksinkertaisen lisäyslajittelun vertailijaolion avulla. Lisäksi toteutit lineaarisen hakualgoritmin `SimpleContainer` -luokkaan. Analysoit myös taulukon täyttämisen, lajittelemisen ja lineaarisen hakualgoritmin aikatehokkuutta ja aikakompleksisuusluokkia.

Tässä harjoituksessa keskitytään haun nopeuttamiseen. Luennoilla on nähty miten lajitellusta aineistosta voidaan hakea tietoa nopeammin **puolitushaulla** (*binary search*).

## Tavoite

Toteutat tässä harjoituksessa puolitushakualgoritmeja `Algorithms` -luokkaan. Lisäksi muokkaat annettua koodia siten että `SimpleContainer` käyttääkin puolitushakua, jos aineisto on lajiteltu.

Toteuta tehtävä alla olevien ohjeiden mukaisesti:

1. Toteutat puolitushakualgoritmista erilaisia toteutuksia.
2. Muokkaat annettua koodia siten että tietosäiliöluokassa `SimpeContainer` käytetään lineaarisen haun sijaan (lajitellussa aineistossa) puolitushakua.

Testaat toteutustasi `task_03_binarysearch` -hakemistossa olevilla testeillä (askeleittain). Tästä ohjeet askeleiden ohessa.

Kirjoita lopuksi **raporttiin** (`RAPORTTI.markdown` löytyy valmiiksi projektin juurihakemistosta) analyysisi tehtävästä, annettujen ohjeiden mukaisesti. Huomaa että myös raportti on arvosteltava tehtävä.


## Askel 1 - Ohjeet

Tässä askeleessa toteuta tämä puolitushakualgoritmi `Algorithms` -luokkaan:

* `static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex)` - Tekee puolitushaun hakien arvoa `aValue` taulukosta `fromArray` indeksien `fromIndex` ja `toIndex` väliltä.

* HUOM 1: indeksit ovat `[fromIndex,toIndex)` -- haku tehdään siis `fromIndex..<toIndex`, ei `fromIndex...toIndex`.

Voit toteuttaa puolitushaun joko iteratiivisesti tai rekursiivisesti. Voit myös toteuttaa molemmat ja käyttää niistä kumpaa haluat. Jos molemmat toimivat, saat tästä lisäpisteen. Mainitse tästä raportissasi niin opettajat hoksaavat ottaa tämän huomioon tehtävän testauksessa ja arvostelussa.

> Huomaa: suurilla aineistoilla rekusiivisten algoritmien kutsu voi johtaa pinon ylivuotoon (*stack overflow*). Lisätietoa tästä ja miten ongelma voidaan hoitaa, löytyy dokumentista [WHAT_STACKOVERFLOW.markdown](WHAT_STACKOVERFLOW.markdown).

**Testaa** toteutustasi testikansiossa `task_03_binarysearch`, tässä vaiheessa **vain** testiluokalla `BinarySearchTests`. Se testaa suoraan metodia `Algorithms.binarySearch(T, T [], int, int)`. Huomaa että testi myös lajittelee aineiston omalla toteutuksellasi `Algorithms.insertionSort` -metodista. Eli jos puolitushaku ei näytä toimivan, varmista ensin että aineisto varmasti tulee lajitelluksi oikein, ja sen jälkeen keskity etsimään bugeja puolitushakualgoritmistasi.

Jos haku toimii, voit mennä eteenpäin seuraavaan askeleeseen.

## Askel 2 - Ohjeet

Tässä askeleessa toteuta toinen puolitushakualgoritmi `Algorithms` -luokkaan:

* `static <T> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex, Comparator<T> comparator)` - Tekee puolitushaun hakien arvoa `aValue` taulukosta `fromArray` indeksien `fromIndex` ja `toIndex` väliltä, käyttäen vertailussa `comparator` -oliota.

* HUOM 1: indeksit ovat `[fromIndex,toIndex)` -- haku tehdään siis `fromIndex..<toIndex`, ei `fromIndex...toIndex`.
* HUOM 2: tässä versiossa, jossa `comparator`, elementin `T` ei tarvitse toteuttaa `Comparable` -rajapintaa sillä `Comparator` hoitaa vertailun.

Voit toteuttaa puolitushaun joko iteratiivisesti tai rekursiivisesti. Voit myös toteuttaa molemmat ja käyttää niistä kumpaa haluat. Jos molemmat toimivat, saat tästä yhden lisäpisteen. Mainitse tästä raportissasi niin opettajat hoksaavat ottaa tämän huomioon tehtävän testauksessa ja arvostelussa. Huomaa myös että tämä algoritmi on muuten sama kuin edellinen, vain vertailu tehdään eri tavalla.

> Huomaa: suurilla aineistoilla rekusio voi johtaa **pinon ylivuotoon** (*stack overflow*). Lisätietoa tästä ja miten ongelma voidaan hoitaa, löytyy dokumentista [WHAT_STACKOVERFLOW.markdown](WHAT_STACKOVERFLOW.markdown).

Huomaa että nyt testauksen kohteena on `SimpleContainer` -luokka ja haku sieltä. Tämä edellyttää sitä, että:

1. Olet toteuttanut edellisessä  harjoituksessa `SimpleContainer.sort(Comparator<E>)` -metodin siten että se lajittelee taulukkonsa toteuttamallasi `Algorithms.insertionSort` -metodilla.
2. Muutat `SimpleContainer.indexOf` -metodia siten että jos tietosäiliön taulukko on lajiteltu, se ei käytäkään edellisessä tehtävässä toteuttamaasi lineaarista hakualgoritmia, vaan sen sijaan käyttää tässä harjoituksessa toteuttamaasi puolitushakualgoritmia.

**Kun** olet toteuttanut tämän askeleen puolitushakualgoritmin, **muuta** `SimpleContainer.indexOf(E, Comparator<E>)` -metodia siten, että **jos** taulukko on lajiteltu, **niin** kutsutaan sitä puolitushakualgoritmia (vertailijaolio ja muut tarpeelliset parametrit), **muuten** suoritetaan aikaisemmin toteuttamasi lineaarinen haku for -silmukalla.

**Testaa** nyt toteutustasi testikansiossa `task_03_binarysearch`, tässä vaiheessa testiluokilla `BinarySearchAscendingOrderTests` sekä `BinarySearchDescendingOrderTests`. Nämä testaavat vertailijaoliolla tätä toista puolitushakualgoritmia. Huomaa että isoilla aineistoilla testit kestävät **kauan** jotta saisimme aikatehokkuuden arviointia varten aineistoa. Tämä johtuu siitä että jokainen aineisto pitää lajitella ennen hakua. Varaudu siis odottamaan testien loppumista jonkin aikaa.

Testit testaavat suoraan metodia `Algorithms.binarySearch(T, T [], int, int, Comparator<T>)`. Huomaa että testi myös lajittelee aineiston omalla toteutuksellasi `Algorithms.insertionSort` -metodista. Eli jos puolitushaku ei näytä toimivan, varmista ensin että aineisto varmasti tulee lajitelluksi oikein, ja sen jälkeen keskity etsimään bugeja puolitushakualgoritmistasi.

**Huomaa** että testit tulostavat aikamittauksia konsoliin samalla tavalla kuin edellisen tehtävän lineaarisessa haussa ja sen testeissä. Tämä on **tärkeä asia** tämän tehtävän raportin kirjoittamista ajatellen! Kopioi siis tulokset talteen, ettei hitaita testejä tarvitsisi taas ajaa uudestaan...

Jos haut näyttävät toimivan testien perusteella, jatka eteenpäin.


## Askel 3 - TIRA Codersin koodareiden puolitushaku

Tämä vaihe ei enää edellytä uuden koodin kirjoittamista, vaan sisältää kokeiluja ja analyysiä.

1. Käynnistä TIRA Coders ja lataa iso, 50 000 koodarin tiedosto. Odota että se on ladattu ja lajiteltu. Jos koneesi on hidas, ja tämä kestää aivan liian kauan, kokeile 10 000 koodarin tiedostoa.
2. Selaa koodarilistaa alas, ja valitse sieltä ihan lopusta joku koodari, vaikkapa "Översti Luis Rowen". 
3. Etsi häntä hakutermillä "Översti", kirjoittamalla sukunimi hakukenttään ja paina enter.
4. Katso **TIRA Coders Log view**:stä mitä haku kesti. Esimerkiksi koneellani: **"PhoneBookArray: Search took 29 ms"**.
5. Laita muistiin paljonko haussa kesti. Vaikka säilyyhän se siinä loki-ikkunassa.
6. **Valitse rasti** Exact (last first) -ruutuun **ja** kirjoita nyt **koko** nimi **Översti Luis Rowen** hakukenttään **täsmälleen** kuten nimi on puhelinluettelossa. Paina enter.
7. Nyt loki-ikkunassa lukee **"PhoneBookArray: Fast search took nn ms"**, jossa nn tilalla on joku aika.

Toista näitä hakuja useammilla nimillä, ja pane merkille onko eri hakutavoissa eroja aikatehokkuuden suhteen.

Pohdi ja kokeile:

* Mikä on tässä nopeassa haussa keskimäärin suoritusaika suhteessa ensimmäiseen hakuun? 
* Kokeile hakea molemmilla tavoilla koodareita myös listan alusta. Mitä huomaat? 
* Kokeile ladata pieniä koodaritiedostoja ja kokeile molempia hakutapoja niillä, sekä **lopusta** listaa että **alusta** listaa.
* Kirjoita havainnoistasi ja analyysistäsi miksi näin käy, raportiisi.
* Miksi jompi kumpi haku on nopeampi, ja onko sillä väliä haetaanko aineiston alusta vai lopusta? Pohdi ja perustele.

Jotta ymmärtäisit paremmin, mitä tapahtuu, voit katsoa miten tarkka haku lähtee liikkeelle käyttöliittymästä alkaen:

1. `SearchPanel` kutsuu metodia `TIRACodersApp.getModel().indexOfItem(searchText, doSearchByKeyOnly);` jossa `boolean` parametri `doSearchByKeyOnly` on `true` kun tehdään tarkka haku.
2. Mennään siis `PhoneBookArray.indexOfItem` -metodiin, jossa -- jos tuo boolean parametri on `true` -- haetaan koodaria `SimpleContainer`:sta metodilla `phoneBook.indexOf(toFind, comparator);`.
3. Muistat että askeleessa 2 yllä toteutit sen, että jos container on lajiteltu, etsitään **puolitushaulla**.

Jos vaan olet toteuttanut aikaisemmat askeleet oikein, `PhoneBookArray` ja TIRA Codersin käyttöliittymä huolehtivat siitä että tietosäiliö on aina lajiteltu oikein -- jos vaan lajittelualgoritmisi ja `Comparator` -toteutuksesi ovat oikeellisia.


## Testaus ja analyysi

**Edellisessä** tehtävässä analysoit lineaarisen haun nopeuksia, esimerkiksi jotain tällaista saattoi tulostua konsoliin:

```console
Time performance tests of filling and searching the SimpleContainer
Fill time is in milliseconds, and search time in microseconds
n	Fill	Search	Total
500	7	700	707
1000	9	288	297
1500	4	394	398
2000	6	534	540
...
```

**Tässä** tehtävässä testit tulostavat samankaltaisia asioita:

```console
n	Fill	Sort	Search	Total
500	2	5	658	665
1000	5	3	19	27
1500	4	6	48	58
2000	7	13	47	67
2500	11	21	48	80
3000	14	37	35	86
...
```

**Vertaile** eroja eri osa-alueiden suoritusajoissa. Miten tämä tehtävä ja edellinen tehtävä eroavat toisistaan tulostusten suhteen? **Miksi**?

Mitä etua puolitushaku toi algoritmien nopeuksiin? Millä edellytyksillä puolitushakua voi ja kannattaa käyttää?

**Kopioi** tämän tehtävän tulostukset taulukkolaskinsovelluksen (MS Excel, Apple Numbers, Google Sheet,...) ja sijoita ne taulukkoon. **Piirrä** erikseen käyrät: 

* taulukon täyttöajan kasvusta suhteessa n:n kasvuun,
* taulukon lajitteluaikojen kasvusta suhteessa n:n kasvuun, sekä 
* taulukon hakuaikojen kasvusta suhteessa n:n kasvuun.

**Analysoi sekä koodia** toteuttamissasi hakualgoritmeissa, että tuottamiasi **käyriä**, ja pohdi **mikä** on **lineaarisen sekä puolitushakualgoritmien aikakompleksisuusluokka**. Perustele analyysisi kunnolla. Kirjaa analyysi raporttiin, ja sisällytä siihen oma datasi kuten `RAPORTTI.markdown` -tiedostossa on ohjeistettu, käyttäen taulukoita ja kuvia.

## Raportti

**Kirjaa** raporttiisi `RAPORTTI.markdown` mitä opit tehtävän tekemisestä, mikä oli vaikeaa, mikä helppoa, jne.

**Kirjoita** myös raporttiisi edellisen analyysikohdan listaamat asiat. Perustele johtopäätöksesi ja ole tarkka analyyseissäsi.

Viittaa suoritusaikataulukoihin ja laatimiisi käyriin (jotka ovat mukana raportissasi), sekä koodin aikakompleksisuusanalyyseihisi.


## Lopuksi

Kun olet valmis, varmista että sekä raportti että kaikki koodi on paikallisessa git -repositoryssäsi ja myös etärepositoryssäsi (komennot `git commit`, tarvittaessa uusille tiedostoille `git add` sekä `git push`).


## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.
