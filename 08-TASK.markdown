# Tehtävä 8

Älä tee tätä tehtävää ennenkuin olet tehnyt [tehtävän 7](07-TASK.markdown).

Tämän harjoituksen aiheena on tietorakenne nimeltään **hajautustaulu** (*hash table*). Hajautustaulu on edistynyt tietorakenne, joten huolehdi että olet ensin tutustunut tietorakenteeseen kurssin luentojen kautta sekä tarvittaessa perehtymällä tähän tietorakenteeseen liittyvään kirjallisuuteen.

Hajautustaulu sekä Binäärinen hakupuu pyrkivät molemmat välttämään tavallisten taulukoiden haittapuolet. Näin pyritään tietorakenteisiin joiden käyttäminen (elementtien lisääminen, haku, poistaminen jne) on nopeampaa kuin tavallisten taulukoiden kanssa. 

Hajautustaulu eroaa BST:stä siinä, että se *käyttää* taulukoita, mutta eri tavalla kuin "tavallinen" taulukko jossa yleensä pyritään täyttämään taulukkoa alusta eteenpäin, ja jossa taulukosta esimerkiksi etsitään tietoa silmukassa -- taas alusta alkaen kunnes etsittävä tieto löytyy tai taulukon data on käyty läpi.

BST:ssä avainarvo sai olla duplikaatti, koska koodareilla voi olla sama nimi. Lisäksi lajittelujärjestys oli BST:ssä tärkeää.

Tässä harjoituksessa teemme toteutuksen tästä hajatustaulusta siten, että avainarvot *eivät saa olla duplikaatteja*, eikä elementtien järjestyksellä tietosäiliössä ole merkitystä. Siksi hajautustaulu toteuttaakin rajapinnan `oy.interact.tira.util.TIRAKeyedContainer` (ei "ordered" -sanaa).

Tässä harjoituksessa hyödynnetään hajautustaulua tietorakenteena. Emme tosin korvaa BST:tä hajautustaululla, vaan käytämme sitä *uuteen käyttötarkoitukseen*. Koska olemme kiinnostuneita koodareista, meitä kiinnostaa myös *lähdekoodi*. Haluamme tietää:

1. Mitä eri ohjelmointikielten *avainsanoja* koodareidemme lähdekoodissa käytetään eniten, ja
2. Miten koodarimme nimevät *muuttujia* ja *funktioita* koodissa; minkälaista "sanastoa" koodarimme käyttävät.

Tähän tarkoitukseen hajautustaulu käy hyvin. Taulukko pitää kirjaa jokaisesta *uniikista* sanasta (K eli key) joka lähdekoodista löytyy, ja pitää kirjaa siitä, kuinka monta kertaa (V eli value) kyseistä sanaa koodissa käytetään.

Taulukko näyttää siis vaikkapa tältä (tämän projektin koodin malliratkaisun yleisimmät sanat tätä tehtävää kirjoitettaessa):

| Sana (K)  | Esiintymiset (V) |
|-----------|-----------------:|
| the       |       928        |
| import    |       744        |
| int       |       560        |
| if        |       519        |
| new       |       513        |
| array     |       509        |
| public    |       474        |

...ja niin edelleen. Tämä toiminta voidaan käynnistää TIRA Coder -sovelluksen valikosta "Count Code Words (hashtable)". Kun olet toteuttanut hajautustaulun, ja täydentänyt koodia muuallakin, näet 100 yleisimmän käytetyn sanan esiintymismäärät sekä graafisessa käyttöliittymässä että tulostettuna konsoliin:

![Esimerkki hajautustaulun käytöstä](task-08-codewords-count-sample.png)

Voit itse valita mistä hakemistosta sanoja lasketaan. Minkä ohjelmointikielten lähdekooditiedostoja laskuri käsittelee, näet metodista `CodeWordsCounter.countWordsinSourceCodeFiles()`.

Tosin harjoituksen **testit** laittavat hajautustauluun tuttuja `Coder` -olioita, mutta sehän toimii koska toteutat hajautustaulun geneerisenä tietorakenteena. Testit käyttävät avaimena koodarin id:tä, joten ne ovat uniikkeja ja kaikki toimii oikein. Tämä siksi että analyysissä ja raportissasi saat vertailukelpoista aineistoa BST -tehtävän suhteen ja voit verrata hajautustaulun ja BST:n aikatehokkuutta.


## Lähteitä

* Kurssin luentokalvot.
* Liveluento (tallenne) ja sen vinkit ja esimerkit.
* Kirjallisuus.
* `oy.interact.tira.util.TIRAKeyedContainer` -rajapinta.
* `oy.interact.tira.factories.HashTableFactory`, tehdasluokka joka instantioi toteutuksesi hajautustaulusta, kunhan se on valmis.
* `oy.interact.tira.student.CodeWordsCounter`, työkaluluokka, joka laskee käytettyjen sanojen määrää kooditiedostoista.
* [Javan Object.hashCode()](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#hashCode()), dokumentaatio Javan `hashCode()` -metodista. Lue myös ko. metodin alta löytyvän `equals(Object)` -metodin dokumentaation kohta "API Note".


## Tavoite

Toteuta ja testaa hajautustaulu algoritmeineen siten että se toteuttaa `TIRAKeyedContainer` -rajapinnan. Testaa toteutusta tarvittaessa sitä korjaten siten, että toteutus läpäisee testit. Tarkemmin tämän vaiheet on kuvattu alla luvussa Toteutuksen askeleet.

Jotta voit sijoittaa `Coder` -olioita hajautustauluun, on sinun uudelleenmääritteltävä (`@Override`) `Coder` -luokassa sen `Object` -luokasta perimä metodi `int hashCode()`.

Kun testit menevät läpi, voit kokeilla hajautustaulun toimintaa TIRA Coders -sovelluksessa koodin sanojen laskentaan, mutta tässä vaiheessa toteutat vielä hieman lisää toiminnallisutta `CodeWordsCounter` -luokkaan ennen kuin tämä toimii. Ohjeet tähän alempana. 

Vertaat myös hajautustaulun toiminnallisuutta ja aikatehokkuutta esimerkkinä valmiiksi annettuun taulukkopohjaiseen ratkaisuun, sekä aikaisemmin toteuttamaasi BST:hen.

Analysoi raportissasi toteutuksesi oikeellisuutta ja aikatehokkuutta.

Liveluennot (ja niiden tallenteet) sisältävät demoja siitä miten tämä tietorakenne koodataan, joten niistä on varmasti apua tehtävän tekemisessä. Tutustu niihin.

> Huomaa myös että tässä(kään) tehtävässä **ei saa käyttää** Javan tietosäiliöluokkia (`Collection` ja `Map` -rajapintojen toteutukset) tai algoritmeja luokissa `Arrays` ja `Collections`. Kaikki tietorakenteet ja algoritmit *toteutetaan itse* käyttäen Javan tavallisia taulukoita ja omia luokkia sekä Javan perustietotyyppejä int, Integer, String, ja niin edelleen. 
>
> Samoin **hajautusavain eli tiiviste (hash)** pitää laskea itse; **ei saa käyttää** (eli kutsua) Javan valmiita toteutuksia *minkään* luokan `hashCode()` -metodeista (ml. String, Integer, jne.). Tiiviste tulee laskea merkkitiedosta itse, **merkki merkiltä**, etsien  mahdollisimman aikatehokasta toteutusta joka sopii hyvin **nimenomaan** tälle aineistolle (koodareiden UUID:t).


## Toteutuksen askeleet

**Luo uusi luokka** packageen `oy.interact.tira.student`, nimeltään vaikkapa `HashTableContainer` tai vastaavaa:

```Java
public class HashTableContainer<K extends Comparable<K>,V> implements TIRAKeyedContainer<K,V> {
```

Kuten yltä näet, luokan on toteutettava `TIRAKeyedContainer` -rajapinta, ja se sisältää avain-arvo -pareja (K eli key ja V eli value). Näistä avaimeen on lisäksi liitetty vaatimus että se toteuttaa `Comparable` -rajapinnan.

Tämän toteutuksen tulee olla geneerinen ja toimia minkä tahansa tietotyyppien kanssa, kunhan K on tosiaan `Comparable`. Testeissä tietorakennetta testataan erilaisilla tietotyypeillä, ja TIRA Coder -sovelluksessa tuo Key tulee olemaan luokka `String` (ohjelmakoodin sanasto) ja Value tulee olemaan `Integer` (montako kertaa kukin sana esiintyi koodissa). 

Hajautustaulussa key:n on siis oltava uniikki. Tämä tarkoittaa sitä, että kun toteutat `add` -metodia, toteutuksen on varmistettava onko kyseinen avaimen arvo jo valmiiksi tietosäiliössä. Jos on, tietosäiliössä oleva arvo (value) *päivitetään*. Jos taas ei ole, avain-arvopari *lisätään* tietosäiliöön. Tähän käytetään avaimen `equals` -metodia.

Toteutuksen ollessa hajautustaulu, `HashTableContainer` laskee indeksin taulukkoon lisättäväville olioille *hajautusfunktion* avulla. Mistä hajautusfunktio saadaan? Jokainen avain (`K` eli key) on Javan olio, perii siis luokan `Object` jossa on määritelty metodi `hashCode()`. Aliluokat uudelleenmäärittelevät (`@Override`) tämän metodin siten että ne laskevat itse oliolle *hajautusavaimen* tämän *hajautusfunktion* avulla.

Hajautusfunktio palauttaa kokonaisluvun (`int`) joka voi olla arvoltaan positiivinen tai negatiivinen numero. Koska taulukon indeksin pitää olla aina `0 <= index < array.length`, hajautusfunktion palauttaman *kokonaisluvun arvo on sovitettava* taulukon indeksiksi, riippuen hajautustaulun taulukon tämänhetkisestä pituudesta. Miten tämä tehdään, on näytetty kurssin luennoilla. 

**Toteuta** `Coder` -luokalle **hajautusfunktio** ylikuormittaen se. Toteuta metodi **käyttämättä** (kutsumatta) mitään valmista Javan `hashCode()` metodia. Muista että `Coder` -luokan `hashCode()` -metodia voidaan käyttää muidenkin tietorakenteiden tai -algoritmien kuin hajautustaulun kanssa. Siksi `hashCode()`:n toteutus **ei saa** rajoittaa palautettavan kokonaisluvuna arvoa millään tavoin; sen pitää voida olla erittäin suuri tai pieni positiivinen *tai* negatiivinen kokonaisluku. Tämä luku rajataan taulukon kokoon **vasta** hajautustaulu -tietorakenteessa.

> Koodariluokan kanssa avaimena toimii siis `Coder.id`, koska se on uniikki. Hajautustaulu sisältää koodariolioita, joita voimme hakea sieltä koodarin id:n perusteella. Siitä tiiviste siis lasketaan; kts. Javan `hashCode()` ja `equals()` -metodien dokumentaatio `Object` -luokasta.

Kun olet saanut hajautustaulun toteutettua, ennen sen testaamista ja käyttöä toteuta vielä tehdasmetodi `oy.interact.tira.factories.HashTableFactory.createHashTable()`.

Kun olet valmis, voit **testata** toteutuksesi oikeellisuutta kuten alempana luvussa Hajautustaulun testaaminen on kuvattu.

**Kun testit menevät läpi**, voit viimeistellä koodin avainsanojen laskentaan liittyvän koodin alla olevien ohjeiden mukaisesti. Sen jälkeen voit käyttää TIRA Codersin valikkoa "Count Code Words (hashtable)", ja katsoa mitä sanoja *sinä* eniten käytät erinäisissä koodausprojekteissasi. Vaikka tässä projektissa. Ohjeet tähän alla.


### Koodin avainsanojen laskeminen

Kun olet testannut oikeellisuutta ja aikatehokkuutta, kokeile myös miten toteutuksesi toimii TIRA Codersin käyttöliittymän kautta.

Annetussa koodissa on valmiina toteutus jolla käydään läpi käyttäjän valitseman hakemiston ja sen alihakemistojen lähdekooditiedostot. Tehtäväsi on **täydentää** täät koodia siten että sanojen laskenta toimii. Tämä koodi löytyy luokasta `oy.interact.tira.student.CodeWordsCounter` ja sinä täydennät sen metodit `countWordsFrom(File)` sekä `topCodeWords(int)`. Katso metodien kommenteissa olevat ohjeet ja viimeistele toteutukset.

Kuten huomaat, luokka sisältää `TIRAKeyedContainer` -olion joka luodaan kutsumalla tehdasluokan metodia `HashTableFactory.createHashTable()` -- siellä luodaan olio sinun toteuttamastasi hajautustaulusta. Hajautustaulun avaimena (`K`, key) on `String`, eli kooditiedostosta löydetty sana. Arvona (`V` eli value) taas on `Integer` eli sanan esiintymismäärä kooditiedostoissa.

Idea on että metodissa `countWordsFrom(File)` luetaan merkki merkiltä lähdekooditiedoston merkkejä, pätkitään sieltä esille sanat, laitetaan sanan merkit taulukkoon josta saadaan sana merkkijonoksi. Sitten etsitään hajautustaulusta kyseinen sana. Jos se on siellä, lisätään sanan laskurin arvoa yhdellä. Jos sana ei ollut hajautustaulussa, lisätään se sinne laskurin arvolla 1.

Tämän jälkeen annettu koodi kutsuu metodia `topCodeWords(int)`. Tässä metodissa toteutat kommenttien avulla sen, että hajautustaulusta saadaan sana/laskuriparit taulukkoon. Sen jälkeen taulukko lajitellaan omalla nopealla lajittelualgoritmillasi sanan *esiintymislukumäärien* mukaiseen *laskevaan* järjestykseen. Sen jälkeen tästä taulukosta kopidaan toiseen taulukkoon pyydetty määrä näitä pareja, ja palautetaan se taulukko kutsujalle. 

Kun nämä on toteutettu:

1. Käynnistä sovellus.
2. Valitse valikosta "Count Code Words (hashtable)".
3. Selaa tietokoneella hakemistoon jossa joku koodausprojekti on.
4. Valitse hiirellä klikkaamalla hakemisto (single click) jonka *sisältämien* lähdekooditiedostojen sanoja haluat laskea.
5. Prosessoinnin jälkeen näet ikkunassa kooditiedostojen 100 yleisintä sanaa ja niiden esiintymislukumäärän.

Jos haluat testata tätä jonkun todella ison aineiston kanssa, kloonaa vaikka Linuxin kernelin lähdekoodi koneellesi Linus Torvaldsin repositorystä. Repositoryn koko alla kuvatulla tavalla kloonattuna on 1,78 Gt, joten tilaa se vähän vie -- **MUTTA -- älä vaan MISSÄÄN NIMESSÄ kloonaa tätä tämän TIRA -projektin hakemiston sisälle!!!! Vaan jonnekin ihan muualle, vaikka kotihakemistoosi temp -nimiseen hakemistoon jonka voit poistaa kun haluat!!!**. Et **todellakaan** halua että Linuxin kernelin lähdekoodi työnnetään **omaan etärepoosi** kun annat `git push` -komennon!

```console
git clone --depth 1 https://github.com/torvalds/linux
```

**Huomaa lisäksi** tuo parametri `--depth 1` -- ilman sitä *koko kernelin commit -historiakin* latautuu koneellesi ja se vie *aikaa* ja *levytilaa* paljon enemmän kuin tuo 1,78 Gt. 

Sen jälkeen valitse TIRA Codersilla valitse valikosta "Count Code Words (hashtable)", etsi koneeltasi ja valitse hiirellä tuo linux -niminen hakemisto, käynnistä koodin sanojen laskenta, ja odota että työ on tehty.

Varsinkin isojen aineistojen kanssa, katso miten sovelluksen loki-ikkunassa (ja konsolissa) tulostuu kauanko missäkin operaatiossa kesti. Raportoi myös näistä havainnoista.

Omalla koneellani *hajautustaulun täyttö* Linuxin kernelin koodia analysoitaessa kesti 15,668 sekuntia, johon ei ole laskettu mukaan tiedostojen käsittelyä. Käytännössä suoritusaika oli noin 20-30 sekuntia tiedostojen käsittely mukaan lukien. Näköjään avainsana `define` (C -kielen `#define` -makro lienee suurin osa tämän sanan esiintymismääristä) on selkeästi eniten käytetty sana Linuxin kernelin koodissa, 5 111 063 kertaa se sieltä löytyi (sillä versiolla jonka kloonasin).

> Huomaa että annettu koodi ja ohjeet laskevat kaikki merkit myös kommenteista. Jos haluat lisää mukavia koodaushetkiä, voit toteuttaa koodiin lisäominaisuuksia joilla ei lasketa koodin kommenteissa olevaa tekstiä mukaan. Huomioi se, että eri ohjelmointikielissä on mahdollisesti erilaisia kommenttimerkkejä, on olemassa rivikommentteja ja kommenttilohkoja, jne. joten tehtävä ei ole ihan yksinkertainen. Tästä *ei* saa lisäpisteitä kurssin toteutukseen, sillä tässä ei nyt ole kurssin aihealueen ytimessä olevasta asiasta kyse.


## Hajautustaulun oikeellisuuden testaus pienellä aineistolla

Testaa ensin toteutustasi testillä `GenericTests`. Se testaa hajautustaulun toimintaa kutsumalla sen useimpia metodeja ja varmistamalla oikeellisuuden.

Jos testi ei mene läpi, korjaa toteutustasi kunnes testi onnistuu.


## Hajautustaulun vertailuaineiston tuottaminen

Testi `SimpleKeyedTablePerformanceTests` testaa **taulukkopohjaista** toteutusta `SimpleKeyedContainer` **hajautustaulun sijaan**. Tämä `SimpleKeyedContainer` toteuttaa saman rajapinnan kuin hajautustaulu, mutta "tavalliseen tapaan" käyttää silmukoita elementtien etsimiseen yms. taulukosta. 

Tämä testi on olemassa aikatehokkuuden *vertailuja* varten. Se kutsuu samankaltaisia metodeja kuin hajautustaulu (ja BST:n testit), sekä tallentaa näiden suoritusaikoja samanlaiseen taulukkoon kuin hajautustaulua (sekä BST:tä) testaavat testit. Saat siis tällä testillä vertailtavaa aineistoa hajautustaulun ja BST:n välistä suorituskykyanalyysiä varten.

*Huomaa* kuitenkin että varsinkin suurilla aineistoilla tämä testi on hidas. Kannattaa siis jättää testi pyörimään itsekseen ja palata myöhemmin katsomaan joko testi loppui. Jos koneesi on hidas, voit keskeyttää testien suorittamisen testien kestettyä liian pitkään (kymmeniä minuutteja tai tunteja).

Kopioi tämän testin tulostamat aikamittaukset tiedostosta `compare-simple-keyed-container.csv` johonkin taulukkolaskinsovellukseen. Tiedostossa jokaisen aineiston mittaukset ovat yhdellä rivillä, ja pilkulla erotetut sarakkeet sisältävät mittausaikoja eri toiminnallisuuksista.

## Hajautustaulun testaaminen

Varsinainen hajautustaulun toteutusta suurilla aineistoilla testaava testi on `HashTablePerformanceTests`. Se tallentaa suoritusaikamittaukset tiedostoon `compare-hashtable.csv`. 

Tarkoituksena testeillä on paitsi varmistaa tietorakenteen toteutuksen *oikeellisuus*, myös aikamittauksia analysoimalla ja vertailemalla suoritusaikojen kasvua aineiston koon kasvuun (n) sekä myös taulukkopohjaisiin toteutuksiin. 

Tutki onko oma hajautustaulusi toteutus tarpeeksi nopea suhteessa siihen miten sen pitäisi käyttäytyä. Se miten hajautustaulun pitäisi käyttäytyä ja mikä on sen eri metodien aikakompleksisuusluokka (ja siten nopeus) pitäisi selvitä kurssin luentomateriaalista ja kirjallisuudesta.

Vertaile siis raportissasi tässä vaiheessa kurssia:

1. BST:n hitaan taulukkototeutuksen,
2. BST:n (nopea toteutus),
3. Tämän harjoituksen hitaan taulukon toteutuksen, sekä
4. Tämän harjoituksen hajautustaulun aikatehokkuuksia toisiinsa.

Hyödynnä analyysissäsi testien tuottamia csv -tiedostoja.

Muista raportoida havaintosi sekä analyysisi ja liittää näitä suoritusaikamittaukset myös raporttiisi.


## Raportti

**Kirjaa** raporttiisi `RAPORTTI.markdown` mitä opit tehtävän tekemisestä, mikä oli vaikeaa, mikä helppoa, jne.

Analysoi toteutuksesi oikeellisuutta ja aikatehokkuutta huomioiden seuraavat asiat:

* Kurssin luennot ja kirjallisuus hajautustauluista.
* Yksikkötestit ja niiden mittaukset.
* Vertailut taulukkopohjaiseen tietorakenteeseen, sen aikatehokkuusmittaukset.
* Havaintosi TIRA Codersin käyttöliittymästä kun analysoit koodin avainsanojen määriä.

**Vertaile** siis raportissasi tässä vaiheessa kurssia yhdistellen tietoja eri tietorakenteiden aikatehokkuudesta:

1. BST:n hitaan taulukkototeutuksen,
2. BST:n (nopea toteutus),
3. Tämän harjoituksen hitaan taulukon toteutuksen, sekä
4. Tämän harjoituksen hajautustaulun aikatehokkuuksia toisiinsa.

Varmista että "hidas" taulukkototeutus jolla kurssi aloitettiin, käyttää silti nopeaa toteuttamaasi lajittelualgoritmia, sekä toteuttamaasi nopeaa puolitushakua (kun tehdään tarkka haku). Näin vertailu on reilumpaa.

Hyödynnä analyysissäsi testien tuottamia **csv -tiedostoja**. Kaikki nämä nopeustestit käyttävät samoja JSON -tiedostoja joissa koodareiden tietoja. Testit tuottavat siis vertailukelpoista aineistoa. Vie tiedostojen sisältö taulukkolaskimeen, tee niistä graafeja ja analysoi mitä ne kertovat.

> Graafeja tehdessäsi kannattaa ehdottomasti valita aina aineiston määrä (n) x-akselille, ja **yksi** aikamittauskategoria (sarake) kerrallaan graafiin, esimerkiksi viivadiagrammiin. Näin voit lukea n:n kasvun vaikutusta graafista visuaalisesti *vasemmalta oikealle* n:n kasvaessa x-akselilla. Jos laitat useampia aikamittauksia yhtäaikaa samaan graafiin, ja toinen sarake sisältää huomattavan paljon pienempiä mittoja, tämä käyrä jää käytännössä x-akselin pintaan tai päälle, eikä siitä saa mitään informaatiota esille. Tee useampia graafeja joissa kussakin yksi mittaus. Huomioi myös se, että n ei kasva testiaineistossa tasaisesti, vaan välillä kasvu on isompaa. Esimerkiksi koodareiden määrä on pienimmästä tiedostosta alkaen 100, 1000, 5000, 10000, ja niin edelleen. Ota tämä siis huomioon kun tulkitset graafeja!

**Analysoi** miten aineiston koon kasvu vaikutti eri hajautustaulun algoritmien aikatehokkuuteen. Hyödynnä tässä testien tuottamaa csv -tiedostoa. Miten tämä aineiston koon kasvun vaikutus mitattuihin suoritusaikoihin vertautuu siihen, mitä tiedät hajautustaulun *eri* algoritmien aikakompleksisuusluokista?

Vertaa hajautustaulun aikatehokkuutta BST:n aikatehokkuuteen:

1. missä BST on nopeampi kuin hajautustaulu, ja päinvastoin? Miksi?
2. onko toinen tietorakenne nopeampi pienemmillä aineistoilla ja hitaampi suuremmilla kuin toinen; kumpi? Milloin ja miksi?


## Lopuksi

Jos kloonasit **Linuxin kernelin** koodin, **varmista** ettei se ole tämän repositorysi hakemistoissa, muuten tulee aika isoja ongelmia repositorysi koon kanssa...

Kun olet valmis, varmista että sekä raportti että kaikki koodi on paikallisessa git -repositoryssäsi ja myös etärepositoryssäsi (komennot `git commit`, tarvittaessa uusille tiedostoille `git add` sekä `git push`).


## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.