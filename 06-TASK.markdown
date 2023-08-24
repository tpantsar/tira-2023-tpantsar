# Tehtävä 6

Älä tee tätä tehtävää ennenkuin olet tehnyt [tehtävän 5](05-TASK.markdown).

Tämän harjoituksen aiheena ovat nopeat lajittelualgoritmit. Tehtävän testit hyödyntävät myös aiemmin toteuttamaasi yksinkertaista lajittelualgoritmia (tehtävä 1) sekä puolitushakua (tehtävä 3), joten näiden tulee myös toimia oikein.

Toteutit aikaisemmassa tehtävässä 1 yksinkertaisen lajittelualgoritmin. Toteutettu algoritmi toimii ihan riittävän hyvin pienillä aineistoilla, mutta jos kokeilet ladata TIRACodersiin isompia koodaritiedostoja, lajittelu kestääkin aika kauan. Kokeile tätä sovelluksessa.

> Mitä tarkoittaa kauan? Peukalosääntönä esimerkiksi käyttöliittymäsuunnittelussa, liian hidas tarkoittaa yleensä yli 200 ms odottelua. Tämän jälkeen käyttäjä alkaa jo ihmettelemään miksi ohjelma ei reagoi tai että onko se jumissa. Lajittelu (tai mikään muukaan operaatio) ei siis saisi kestää tätä pidempään. Toki sitten on olemassa tapoja ilmaista käyttäjälle että joku homma kestää, käyttämällä erilaisia käyttöliittymäelementtejä jotka ilmaisevat ohjelman puuhastelevan jotain. Mutta jos toteutetut algoritmit ovat nopeita, käyttäjän *ei tarvitse* odotella eikä ohjelman *tarvitse* näyttää tiimalaseja, pyöriviä ympyröitä, eteneviä palkkeja tai pyöriviä rantapalloja. Parempi siis alun perinkin toteuttaa nopea algoritmi.

Siksi tässä tehtävässä opetellaan toteuttamaan nopea lajittelualgoritmi. Voit itse päättää minkä nopean lajittelualgoritmin toteutat, kunhan se on nopea. Nopea lajittelualgoritmi on aikakompleksisuusluokaltaan parhaassa tapauksessa O(n log n).

Voit halutessasi toteuttaa *useamman* kuin yhden nopean lajittelualgoritmin. Saat näiden toteutuksesta **lisäpisteitä** jos toteutus toimii ja on tarpeeksi nopea. Tarkemmin näistä lisäpisteistä kurssin arvostelua käsittelevässä dokumentaatiossa. 

Kurssilla esiteltyjä lajittelualgoritmeja ovat esimerkiksi **pikalajittelu** (quicksort), **kekolajittelu** (heap sort) ja **lomituslajittelu** (mergesort). Valitse minkä toteutat ensin ja kun se toimii, voit harkita toteutatko näitä vielä lisää lisäpisteiden saamiseksi.

> Tässä tehtävässä kirjoitettavien rivien lukumäärä riippuu valitusta lajittelualgoritmista. Pikalajittelussa myös partitiointi- eli ositusalgoritmin valinta vaikuttaa myös koodirivien lukumäärään. Lyhimmillään selviää 15-20 rivillä koodia. Jos toteutat useamman lajittelualgoritmin, silloin koodirivien määrä tietysti nousee.

**Huomaa** että jos lajittelualgoritmisi hyödyntää **rekursiota** (kuten esimerkiksi quicksort tekee), on mahdollista että suurien tietoaineistojen lajittelussa algoritmi kuluttaa pinomuistia (stack) niin paljon rekursiivisia metodeja kutsuttaessa, että pinomuisti loppuu kesken. Tällöin tapahtuu ns. pinon ylivuoto eli **stack overflow**. Lue tästä lisää ohjeessa [WHAT-STACKOVERFLOW.markdown](WHAT-STACKOVERFLOW.markdown). Siellä ohjeet miten asiaan voi varautua ilman että ohjelman suoritus epäonnistuu.

Myös kekomuisti (heap) voi loppua kesken suurten aineistojen käsittelyssä. Katso siinä tilanteessa lisätietoa ja ohjeita dokumentista [NOT-ENOUGH-MEMORY.markdown](NOT-ENOUGH-MEMORY.markdown).

## Lähteitä

* Kurssin luentokalvot.
* Liveluento (tallenne) ja sen vinkit ja esimerkit.
* Demosovellus [SortSpectacle](https://github.com/anttijuu/sortspectacle) jossa useampikin lajittelualgoritmin toteutus Swift -kielellä.
* Kirjallisuus lajittelualgoritmeista.
* Englanninkielisessä Wikipediassa on mainituista lajittelualgoritmeista hyvät kuvaukset pseudokoodeineen.

## Tavoite

Toteuta yksi tai useampi nopea geneerinen lajittelualgoritmi `Algorithms.java` -tiedostoon. 

Jokaisesta algoritmista tehdään kaksi versiota, sellainen joka lajittelee `Comparable` -rajapintaa käyttäen ja toinen joka lajittelee `Comparator` -rajapintaa hyödyntäen. Algoritmit sinänsä ovat samanlaisia näissä kahdessa versiossa, vain vertailutapa vaihtelee.

Testaa algoritmien toimintaa. Analysoi niiden nopeutta suhteessa aiemmin toteuttamaasi hitaampaan algoritmiin. Kirjaa analyysisi tulokset raporttiin. Jos toteutit useamman algoritmin, vertaa niiden aikatehokkuutta toisiinsa tämän tehtävän testien avulla. Tarkemmat ohjeet tähän alempana.

## Askel 1 - Ohjeet

Toteuta `Algorithms.java` -tiedostoon uudet lajittelualgoritmit:

```Java
   public static <E extends Comparable<E>> void fastSort(E [] array) {
      /// ...
   }
   public static <E> void fastSort(E [] array, Comparator<E> comparator) {
      // ...
   }

   public static <E> void fastSort(E [] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      // ...
   }
```

Tässä `fastSort(E [] array)` lajittelee taulukon `Comparable` rajapinnan `compareTo` -metodia hyödyntäen, ja algoritmi `fastSort(E [] array, Comparator<E> comparator)` lajittelee taulukon käyttäen `Comparator` -rajapintaa. Kolmas versio lajittelee taulukon annettujen indeksien välillä.

> HUOM: indeksit ovat `[fromIndex,toIndex)` -- lajittelu tehdään siis `fromIndex..<toIndex`, ei `fromIndex...toIndex`.
> 
> Huomaa *uudelleenkäyttö*; voit toteuttaa metodin `fastSort(T [] array, Comparator<T> comparator)` kutsumalla metodia `fastSort(T [] array, 0, array.length, comparator)`.


Tarvitset myös omia apumetodeja, esimerkiksi quicksort tarvitsee ositus- eli partitiointialgoritmin. Heapsort tarvitsee omat apualgoritminsa, jotka näet luentomateriaalista. Toteuta nämä privaatteina staattisina metodeina samaiseen `Algorithms` -luokkaan, tarpeellisine parametreineen.

Jos aiot toteuttaa vain yhden lajittelualgoritmin, voit toteuttaa sen suoraan näihin `fastSort` -metodeihin. Jos haluat toteuttaa useamman, tee kustakin algoritmista oma uusi metodinsa omalla nimellään. Kutsu omaa toteutustasi näistä `fastSort` -metodeista ja vaihda mitä milloinkin käytät kommentoimalla toisten algoritmien kutsu:

```Java
   public static <E extends Comparable<E>> void fastSort(E [] array) {
      quickSort(array);
      // heapSort(array)
      // mergeSort(array);
   }
```

Näin voit helposti vaihtaa mitä algoritmia milloinkin haluat testata tai käyttää.

Voit koodailun aikana tehdä myös oman vaikkapa `oy.interact.tira.SortTestMain.java` -tiedoston, sinne `main` -metodin ja testailla lajittelualgoritmejasi sillä. Testeistä enemmän alla.

## Testaaminen nopealla yksikkötestillä

Hakemisto `task_06_fastsort` sisältää tehtävän testit.

**Aloita** testiluokasta `CodersFastSortBasicTests`. Se käsittelee pientä testiaineistoa (100 koodarin JSON -tiedosto) joten voit nopeasti varmistua nopean lajittelualgoritmisi toteutuksen *oikeellisuudesta*.

Voit suorittaa testin joko VS Codesta käsin tai komentoriviltä, projektin juurihakemistossa (jossa `pom.xml` -tiedosto on):

```console
 mvn -Dtest=CodersFastSortBasicTests test
 ```

Voit tietysti suorittaa yhtä testimetodia kerrallaan.

Testi sisältää seitsemän (7) testimetodia joista ensimmäinen käyttää lajittelussa algoritmia `fastSort(E [] array)`, osa testaa algoritmia `fastSort(E [] array, Comparator<E> comparator)` erilaisilla `Comparator` -olioilla. Lopuksi kaksi testiä testaa lajittelua nousevaan ja laskevaan järjestykseen siten että lajitellaan taulukoista vain indeksiväli 42...50. Voit katsoa testien tulostuksista ja testikoodista millä periaatteella lajittelu missäkin testissä tehdään.

Jos testit menevät läpi, näet vain tulostuksia. Muussa tapauksessa näet virheilmoituksia testien epäonnistumisesta punaisella värillä.


## Testaaminen isoilla aineistolla

Tässä vaiheessa suoritat testejä, jotka käsittelevät koodaritiedostoja (JSON), alkaen pienistä ja jatkaen isoihin tiedostoihin.

Testit tulostavat aikamittauksia joita käytät lopuksi algoritmien aikatehokkuuden analysointiin ja vertailuun.

Ensin suorita testit jotka käyttävät hidasta lajittelualgoritmiasi (ohjeet alla). Tästä saat vertailuaineistoa nopean lajittelualgoritmin analysointiin.

Sen jälkeen suoritat testit jotka testaavat nopeaa lajittelualgoritmiasi isoilla aineistoilla, alkaen pienistä.

### Yksinkertaisen lajittelualgoritmin testi

Tämä testi testaa saman aineiston käsittelyä hyödyntämällä aikaisemmin toteuttamaasi yksinkertaista (ja hidasta) lajittelualgoritmia `Algorithms.insertionSort`.

Testi löytyy testiluokasta `CodersSlowComparatorTests`. Suorita testi, ja varmista että tulos on oikeellinen (testi ei epäonnistu) ja kopioi talteen raporttiisi testin tulostama suoritusaika per aineiston koko.

> Huomaa, että koneesi tehoista riippuen tämä testi voi kestää suhteellisen kauan, useita minuutteja. Käynnistä testi vaikka kun lähdet lounaalle, pidät kahvitauon tai teet muita hommia. Opettajan koneella tämän testin suorittaminen kesti tätä kirjoittaessa viidenteen testiin mennessä 123977 ms eli 123 s eli noin kaksi minuuttia. Viimeinen 100 000 aineiston lajittelu veikin jo 732 sekuntia. Tähän testiin meni siis kaiken kaikkaan koneellani 856 sekuntia eli noin 14 minuuttia.
>
> Jos tutkit tehtävän testiluokan koodia, huomaat ettei testi testaa lajittelun aikatehokkuutta kaikista suurimmilla tiedostoilla. Hidas insertion sort -testi testaa vain kuutta ensimmäistä koodaritiedostoa, sillä tässäkin menee jo aika kauan aikaa.

### Nopean lajittelualgoritmin testi

Tämä testi testaa saman aineiston käsittelyä hyödyntämällä nyt toteuttamaasi nopeaa lajittelualgoritmia.

Testi löytyy testiluokasta `CodersFastComparatorTests`. Suorita testi, ja varmista että tulos on oikeellinen (testi ei epäonnistu) ja kopioi talteen raporttiisi testin tulostama suoritusaika.

Tämä testi käyttää kaikista suurintakin testiaineistoa (2 000 000 koodaria). Jos kekomuisti loppuu kesken, lue tehtävän alussa mainittu dokumentti siitä miten voit kasvattaa kekomuistia joka testille annetaan käyttöön. Jos tämäkään ei auta, tule hakemaan apua harjoituksiin niin katsotaan opettajien avustuksella miten testit saadaan toimimaan.

Vastaaasti, jos toteutit lajittelun rekursiivisella algoritmilla, pinomuisti voi loppua ja testi päättyy pinon ylivuotovirheeseen (*stack overflow*). Tästäkin löytyy ohjeet miten pinomuistia voi kasvattaa jotta saisit testit läpi myös isoilla aineistoilla.

Jos toteutit useamman kuin yhden nopean lajittelualgoritmin, tee tämä testi **vuorotellen** käyttäen kaikkia toteutuksiasi, vaihtamalla aina lajittelualgoritmi toiseksi. Raportoi lajittelualgoritmien eroista kuten alla ohjeistettu.

> Opettajan toteutuksessa on tätä kirjoittaessa toteutukset quicksortille kuten se luennoilla on opetettu, samoin toinen toteutus joka käyttää quicksortissa Hoaren partitiointialgoritmia, ja kolmantena toteutuksena on heapsort. Jos toteutat nämä tai jotain muita algoritmeja, on hauska verrata niiden nopeuseroja opettajan toteutuksiin.

Jos teit useita nopeita lajittelualgoritmeja, voit jättää myöhempiä tehtäviä varten nopeimman käyttöön `fastSort()` -metodeihin. Toki voit myöhemmissäkin tehtävissä vaihdella niiden välillä ja katsoa miten tulevissa tehtävissä eri algoritmit pärjäävät.


## Muutokset TIRA Codersiin

Kun nopea lajittelualgoritmisi testatusti toimii, voit ottaa sen käyttöön TIRA Coders -sovelluksessa. Voit ensin kerrata kuinka hitaasti lajittelu tapahtuu: käynnistä TIRA Coders, lataa suuriakin testiaineistotiedostoja, ja kokeile lajittelua.

Luokassa `SimpleContainer` on kaksi lajittelumetodia (`sort`), jotka kutsuvat omia `Algorithms` -luokan toteutuksiasi hitaasta lajittelualgoritmista, aikaisemmista tehtävistä. Voit nyt **muuttaa koodia** täällä siten että kutsutkin nopeita lajittelualgoritmejasi.

Sen jälkeen käynnistä TIRA Coders, lataa suuriakin testiaineistotiedostoja, ja kokeile uudestaan lajittelua. Onko nopeampaa?

## Raportti

**Kirjaa** raporttiisi `RAPORTTI.markdown` mitä opit tehtävän tekemisestä, mikä oli vaikeaa, mikä helppoa, jne.

**Analysoi** testien `CodersSlowComparatorTests` ja `CodersFastComparatorTests` avulla hitaan lisäyslajittelun ja toteuttamasi nopean (tai nopeiden) algoritmien nopeuseroja. Arvioi algoritmien aikakompleksisuusluokkaa testin tulostamien aikamittausten, koodin ja kurssin teorian perusteella. Raportoi näistä havaintosi ja näkemyksesi perustellen analyysisi tulokset.

**Sisällytä** raporttiin testien tulostamat aikamittaukset algoritmien suoritusnopeudesta omalla koneellasi. Arvioi algoritmien nopeuseroja toisiinsa, ja perustele miksi kukin on toistensa suhteen hitaampi tai nopeampi algoritmi.

Huomaa että hitaan ja nopean lajittelualgoritmin tulostukset tulostavan aineistoittain sekä lajittelun kokonaisajan, että lajittelun keston per taulukon koko (eli n). Hyödynnä tätä tietoa analyysissäsi.

Hyödynnä analyysissäsi taulukkolaskimia (Excel, Numbers, Google Sheet,...) ja laadi niiden avulla graafeja jotka havainnollistavat algoritmien välisiä eroja. Ota kuvaruutukaappauksia graafeista ja sisällytä ne raporttitiedostoosi. Esimerkki siitä miten tämä tehdään, löytyy [RAPORTTI.markdown](RAPORTTI.markdown) -tiedostosta. Älä toimita repositoryssäsi Word, Excel yms documentteja, ainoastaan kuvaruutukaappauksia ja tekstiä.

Analysoi myös sitä, mitä eroja löysit eri algoritmien aikatehokkuudesta pienien ja isojen aineiston välillä? Jos toteutit useamman nopean lajittelualgoritmin, suorita testiä nopean lajittelualgoritmin ison aineiston testiä jokaisen toteuttamasi algoritmin kanssa, ja raportoi myös algoritmien aikatehokkuuseroista, perustellen miksi joku algoritmi on mielestäsi nopeampi kuin toinen, tämän aineiston kanssa.

Huomaa että analyysi on myös arvosteltava tehtävä, ja puuttuvat tai heikot perustelut ja väärät analyysit vähentävät pisteitä.

## Lopuksi

Kun olet valmis, varmista että sekä raportti että kaikki koodi on paikallisessa git -repositoryssäsi ja myös etärepositoryssäsi (komennot `git commit`, tarvittaessa uusille tiedostoille `git add` sekä `git push`).

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.