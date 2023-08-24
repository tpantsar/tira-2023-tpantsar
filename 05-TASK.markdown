# Tehtävä 5

Älä tee tätä tehtävää ennenkuin olet tehnyt [tehtävän 4](04-TASK.markdown).

Tämän harjoituksen aiheena on toinen perustietorakenne, jono (*queue*).

Voit toteuttaa jonosta yhden tai kaksi toteutusta, jonon jonka sisäisenä tietorakenteena on joko:

* taulukko, jonka kokoa reallokoidaan tarvittaessa, ja/tai
* linkitetty lista, jonka elementti osoittaa aina seuraavaan elementtiin. Reallokointia ei tarvita mutta linkkien hallinta jonoon elementtien välillä vaatii enemmän koodia ja pienen apuluokan.

Voit siis toteuttaa molemmat erilaiset jonotietorakenteet. Jos molemmat toimivat oikeellisesti, saat tästä lisäpisteitä.

Tässä tehtävässä kirjoitat noin 100-230 riviä koodia, riippuen koodaustyylistä ja siitä toteutatko molemmat tavat, taulukko ja linkitetty lista. Taulukkopohjainen toteutus vaatii n. 130 riviä koodia, linkitetyn listan toteutus vie noin 100 riviä koodia. Toisaalta taulukko on tuttu jo edellisistä tehtävistä, linkitetyn listan toteutus solmuineen (node) ei.

> Miksi linkitettyä listaa (*linked list*) ei enää ole kurssilla omana pakollisena ohjelmointitehtävänään? Yksi syy on hyvin selitetty [tässä artikkelissa](https://arxiv.org/pdf/2306.06942.pdf). Toinen syy on se, että vastaava solmu-linkki -rakenne toteutetaan myöhemmin (07-tehtävä) tehokkaassa ja käytännössäkin hyödyllisessä tietorakenteessa, binäärisessä hakupuussa.
> 
> Linkitetty lista on pieni ja joskus hyödyllinenkin tietorakenne. Katso esimerkiksi [matopeli-konsolisovellus](https://github.com/anttijuu/snakeses) jossa sekä pelialue että mato on toteutettu linkitettynä listana. Kurssin teeman ollessa aikatehokkuus suurten tietomäärien käsittelyssä, linkitetty lista on kuitenkin usein hidas käsitellä (kts [demoja](https://github.com/anttijuu/listarraycomparison)), jonka takia sen roolia harjoituksissa on vähennetty. Linkitetty lista on kuitenkin tehokas jonotietorakenteessa, koska jonotietorakenteessa käsitellään *aina* jonon jompaa kumpaa päätä. Näiden operaatioiden aikakompleksisuusluokka on linkitetyllä listalla aina O(1), joten jonon toteutuksessa linkitetty lista on hyvä vaihtoehto. Taulukkototeutuksessa jonoon lisääminen *voi* olla O(n) kun taulukko reallokoidaan, muuten se on O(1). Valitse siis kummalla tavalla jonon toteutat - vai molemmilla.


## Lähteitä

* Kurssin luentokalvot.
* Liveluento (tallenne) ja sen vinkit ja esimerkit.
* Kirjallisuus.
* Jonon rajapintaluokka `oy.interact.tira.student.QueueInterface` ja sen dokumentaatio.

## Tavoite

Toteutat tässä harjoituksessa **jonotietorakenteen** ja käytät sitä realistisessa tilanteessa.

TIRA Coders antaa meille siis rekisterin koodareista joilla on erilaisia osaamisia, osa osaa tiettyjä ohjelmointikieliä ja osa toisia. Idea on että kun tiedämme että jossain projektissa tarvitaan vaikkapa Swift -ohjelmointikielen osaajia, voimme valita listalta tämän kielen ja saamme soittolistan kaikista Swift:iä osaavista koodareista. Voimme sitten soittolistalta yksi kerrallaan soittaa koodareille ja kysyä kuka alkaisi hommiin.

Tämä soittolista on esimerkki siitä, miten jonoa voidaan hyödyntää. Otetaan aina jonosta yksi koodari, soitetaan hänelle ja jos hän ei pääse hommiin tai tarvitsemme vielä lisää koodareita, otetaan jonon kärjestä seuraava koodari jolle soittaa.

Jonon toteutuksen **on täytettävä** seuraavat aikakompleksisuusvaatimukset:

  * `capacity()`: O(1).
  * `enqueue()`: O(1) paitsi kun/jos joudutaan reallokoimaan taulukkoratkaisussa: O(n).
  * `dequeue()`: O(1).
  * `element()`: O(1).
  * `size():` O(1).
  * `isEmpty():` O(1).
  * `clear()`: O(1).
  * `toString()`: O(n).

Metodin `clear()` toteutuksessa pinon kapasiteetiksi on tultava oletuskapasiteetti jos sisäisenä tietorakenteena on taulukko.

> Jos toteutuksena on linkitetty lista, kapasiteetti on periaatteessa rajaton (vain käytettävissä oleva RAM -muisti rajoittaa kapasiteettia). Tällöin voidaan palauttaa arvona vaikka `Integer.MAX_VALUE`.

Jonon ei tule käyttää enempää muistia kuin mitä sen maksimikapasiteetti milloinkin on. Esimerkiksi kahden (tai useamman) taulukon pitäminen *pysyvästi* muistissa (pinon jäsenmuuttujana) on virhe.

Jonoa toteuttaessasi, **varmista** ettet laita luokan jäsenmuuttujiksi sellaisia tietoelementtejä joita ei tarvita koko jono-olion elinajan. Jos tarvitset jotain muuttujaa ja sen arvoa vain yhdessä metodissa, muuttuja kuuluu metodin paikalliseksi muuttujaksi. Turhat jäsenmuuttujat kuluttavat turhaa muistia. Ne voivat myös aiheuttaa bugeja.

**Huomaa** että `toString()` -metodi tässä ja myöhemmissäkin harjoituksissa toteutetaan käyttäen Javan `StringBuilder` -luokkaa, **ei** `String` -oliota muokkaillen. Suuria määriä elementtejä käsitellessä `String`:n avulla merkkijonon muodostaminen on **satoja tai tuhansia kertoja hitaampaa** kuin `StringBuilder`in käyttäminen. Jos `toString()` käyttää `String`iä, siitä saa **miinuspisteen**. Lisätietoa tästä pino -harjoituksen README:ssä.

Kirjoita lopuksi **raporttiin** (`RAPORTTI.markdown` löytyy valmiiksi projektin juurihakemistosta) analyysisi tehtävästä, annettujen ohjeiden mukaisesti. Huomaa että myös raportti on arvosteltava tehtävä.


## Askel 1 - Ohjeet

**Toteuta** rajapinta `oy.interact.tira.util.QueueInterface` omaan luokkaasi `oy.interact.tira.student.QueueImplementation`. Älä muuta millään tavalla annettua rajapintaluokkaa.

> Jos toteutat sekä taulukko- että linkitetty lista -toteutukset, nimeä luokat vastaavasti vaikkapa `ArrayQueue` ja `LinkedListQueue`.

**Lue huolellisesti** rajapintaluokan dokumentaatio, joka kertoo miten rajapinnan tulee käyttäytyä. Toteuta jono näiden ohjeiden mukaisesti.

Aluksi ohjeet taulukkomuotoisen jonon toteutukseen. Jäljempänä linkitetyn listan käyttämisestä jonon toteutuksessa.

Huomaa, että luokan metodit toimivat yhdessä ja yhteistyössä, käsitellen jonon sisäistä taulukkoa (tai linkitettyä listaa) jota siellä käytetään elementtien muistissa pitämiseen. Jonoa ei siis kannata yrittää testata ennenkuin olet saanut toteutettua sen kaikki metodit.

Samalla tavalla kuin pino, jono on myös geneerinen tietorakenne.


### Taulukko jonon sisäisenä tietorakenteena 

Jos toteutat jonon taulukolla, voit jossain määrin soveltaa pinotehtävän ohjeita tässä tehtävässä. 

Huomaa kuitenkin pinon ja jonon **erot**: pinossa elementtejä lisätään aina taulukon loppuun ja otetaan aina sieltä lopusta pois. 

Jonossa sen sijaan elementtejä lisätään aina taulukkoon viimeksi lisätyn elementin jälkeen, ja poistetaan "alusta" sinne lisätty ensimmäinen elementti.

"lisätään jälkeen" ja "...poistetaan alusta" **ei** kuitenkaan tarkoita sitä että lisätään isompaan indeksiin tai poistetaan aina taulukon alusta, pienemmästä indeksistä.

Katsotaan miten jono käyttäytyy. Jonon (jossa kokonaislukuja) alkutilan pitäisi näyttää tältä, kun jono on tyhjä:

```console
value:  _ _ _ _ _ _ _ _ _ _
index:  0 1 2 3 4 5 6 7 8 9
        ^
        head tail -- head ja tail viittaavat molemmat indeksiin 0
size: 0
capacity == array.length == 10
```

Sitten kun jonoon on lisätty muutama elementti (vaikkapa 4,5 ja 6), se näyttää tältä:

```console
value:  4 5 6 _ _ _ _ _ _ _
index:  0 1 2 3 4 5 6 7 8 9
        ^     ^tail
        head -- head on 0 ja tail 3.
size: 3
capacity == array.length == 10
```

Mutta kun jonoon lisätään ja sieltä otetaan pois elementtejä kutsumalla `enqueue` ja `dequeue` -metodeja, jonon sisäisen taulukon tilanne voi olla jossain vaiheessa vaikkapa tämä:

```text
Esimerkki kokonaislukujonosta (Integer), taulukon koko 10:
value: [11,12,13,_,_,_,_,8,9,10]
index:  0   1  2 3 4 5 6 7 8  9
                 ^       ^
                 tail    head
size: 6
capacity == array.length == 10
```

Eli jonoon on lisätty numeroita 1:stä eteenpäin, sitten poistettu numeroita (1...7) jonka jälkeen on taas lisätty numerot 8...13. Tila ei ole koskaan ollut vielä loppumassa, joten reallokointia ei ole tarvinnut tehdä. Taulukossa on ollut tilaa alussa, vaikka lopussa paikat on täytetty. On siis jatkettu täyttämistä *alusta* jossa tilaa on, jolloin indeksi `tail` < `head`.

Nyt seuraava lisättävä elementti menee "jonon hännille" indeksiin 3. Kun taas jonon "kärjestä" pois otettava elementti on indeksissä 8. Indeksimuuttuja `tail` joka kertoo missä jonon häntä on (tai häntää seuraava paikka, uudelle elementille), voi olla siis arvoltaan pienempi kuin jonon kärki jonka indeksi on tässä esimerkissä 8.

Pinon `toString()` toteutus oli helppo - edetään vain indeksistä 0 indeksiin jossa on pinon viimeinen elementti. Nyt ei näin kuitenkaan voida tehdä. Jos yo. taulukko laitettaisiin sellaisenaan alusta indeksistä 0 loppuun asti merkkijonoon, tulisi tästä merkkijono:

"[11, 12, 13, null, null, null, null, 8, 9, 10]"

Vaikka tuosta hypitään yli null:t, järjestys on silti väärä: numero 8 pitäisi olla tällä hetkellä jonon kärjessä, eli sen pitäisi tulla stringiin ensimmäisenä. Vastaavasti numero 13 on jonon viimeinen elementti, joten sen pitäisi tulla viimeisenä. Tässä on kyseisen jonon oikeellinen `toString` -tuotos:

"[8, 9, 10, 11, 12, 13]"

Huomaa siis tämä kun toteutat näitä jonon algoritmeja taulukkomuotoisessa toteutuksessa.

**Taulukon reallokointi** tapahtuu samaan tyyliin kuin pino -harjoituksessa Nyt on vaan huomattava se, että **taulukon käsittely on erilaista jonossa kuin pinossa**. Pinossa oliot ovat taulukossa aina indeksistä 0 eteenpäin. Mutta jonossa, kun elementtejä lisätään ja otetaan jonosta pois, elementit voivat olla jonossa reallokoinnin tullessa tarpeen, vaikkapa alla kuvatulla tavalla.

Esimerkiksi, jos jonon kapasiteetti oli aluksi 10, ja se on ehditty täyttää seuraavalla tavalla:

```console
value:  6 1 0 9 9 1 8 2 9 2
index:  0 1 2 3 4 5 6 7 8 9
                    ^      
                    tail head (head ja tail molemmat indeksissä 6; taulukko on täynnä!)
```

Kun reallokointi on tehty ja uudessa taulukossa on nyt tilaa enemmän (20 elementille) taulukon pitäisi näyttää tältä:

```console
value:  8 2 9 2 6 1 0 9 9 1 
index:  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
        ^                    ^      
        head                 tail 
```
Ja uusi elementti voidaan lisätä nyt indeksiin `tail`.

> Huomaa myös että kun poistat elementin listalta, sen viemä muisti on vapautettava. Taulukkoa käytettäessä tämä tarkoittaa sitä että elementin indeksiin on sijoitettava null. Linkitettyä listaa käytettäessä on huolehdittava siitä että poistettuun solmuun ei ole enää viittauksia. Muuten tapahtuu **muistivuoto** (*memory leak*), olio on muistissa vaikkei sitä enää tarvita. Muisti vapautuu vasta kun taulukkoon laitetaan joku toinen olio tai koko jonotietorakenneolio häipyy muistista (tai sovellus sammuu).

### Toteutus linkitetyllä listalla

Kurssin luennoilla on esitelty linkitetyn listan perusidea ja sen algoritmit, joten jos et ole niitä vielä käynyt läpi, tee se ennen tämän toteutuksen aloittamista.

Linkitetyllä listalla toteutettuna tarvitset sisäisen solmu (*node*) luokan joka toimii apuluokkana jonon toteutuksessa:

```Java
public class LinkedListQueue<E> implements QueueInterface<E> {

   private class Node<T> {
      T data;
      Node<T> next;
      Node<T> previous;

      public Node(T data) {
         this.data = data;
      }
   }

   private Node<E> head;
   private Node<E> tail;
   private int size;
```
Sisäinen luokka (*inner class*) `Node` eli solmu on sekin geneerinen luokka. Se on esitelty `private` -suojauksella, koska apuluokkana se on näkyvissä *vain* `LinkedListQueue` -luokalle. Muut luokat sovelluksessa eivät näe sitä, eikä tarvitsekaan. Noudatetaan siis hyviä modulaarisen ohjelmoinnin kapseloinnin (*encapsulation*) ja tiedon kätkemisen (*information hiding*) periaatteita.

`Node` pitää sisällään:

1. varsinaisen tiedon eli luennolla mainitun satellittidatan; olion geneerisen tietotyypin joita linkitetylle listalle laitetaan. Tässä siis `T`.
2. viittauksen listalla olevaan seuraavaan solmuun (`next`), sekä
3. viittauksen listalla olevaan edelliseen solmuun (`previous`).

Tämä `Node`:n `T` on geneerinen template -parametri. Kätevintä olisi käyttää `Node`:n kanssa samaa tyyppikirjainta `E` kuin varsinaisen tietorakenteen `LinkedListQueue<E>`:n kanssa, mutta Java ei siitä pidä. Siksi, vaikka tietoelementti `T` on se sama kuin `E`, niille annetaan eri tyyppinimi.

Edellä esitetyn avulla jokainen linkitetyn listan solmu pitää sisällään tietoelementtinsä, ja tietää edellisen ja seuraavan noden listalla. Viimeisen noden `next` on tietysti `null`, ja ensimmäisen `previous` on myös `null`.

Sitten, tämä linkitetty lista pitää yllä seuraavia olioviittauksia:

* `tail` eli solmu on jonon viimeinen elementti. Kun jonoon lisätään uusi elementti `enqueue` -metodia kutsuen, se lisätään tämän `tail` -solmun perään, sen `next` elementiksi. Sen jälkeen `tail` viittaus laitetaan viittaamaan tähän uuteen viimeiseen elementtiin. Näin listan loppuun lisääminen on aina nopeaa (O(1)), kun ei tarvitse alusta asti hyppiä solmujen linkkejä pitkin viimeiseen elementtiin. Reallokointiakaan ei tarvita, toisin kuin taulukon tapauksessa.
* `head` eli jonon kärki. Kun halutaan ottaa jonosta seuraava olio käsittelyyn, kutsutaan `dequeue` joka palauttaa `head` -noden sisältämän elementin. Lisäksi tämä solmu on poistettava jonosta: `head`:iä seuraavasta nodesta tehdään uusi head.
* `size` -- aina kun jonosta poistetaan elementti, arvo pienenee yhdellä. Aina kun jonoon lisätään elementti, sen arvo kasvaa yhdellä.

Huolehdi siis metodien toteutuksissa aina siitä että jonoon lisätessä ja jonosta poistettaessa solmujen linkit edelliseen ja seuraavaan solmuuun ovat aina oikein. Muuten jono saattaa "katketa" keskeltä, kun jonkun solmun seuraava -linkki onkin `null` vaikka sitä pitäisi seurata joku toinen solmu. Tai seuraava (tai edelline) solmu on ei-null, vaikka *pitäisi* olla.

## Testaaminen yksikkötesteillä

Kun olet valmis testaamaan toteutustasi, **Luo** toteuttamasi olio `oy.interact.tira.factories.QueueFactory` -luokan eri metodeissa ja palauta olio kutsujalle. Testit ja muu annettu koodi saavat jonon toteutuksesi käsiinsä vain tämän tehdasluokan kautta, joten jos tämä unohtuu tehdä, testit epäonnistuvat. 

> Jos toteutit sekä taulukko- että linkitetty lista -toteutukset, vaihtele tehdasmetodeissa mitä toteutusta milloinkin testaat, allokoimalla jompaa kumpaa toteutusluokkaa vuorotellen ja/tai allokoimalla eri toteutuksia eri tehdasmetodeissa.

Jos sinulla ei ole tarvittavaa kahta muodostinta luokassasi, tee ne:

1. Parametriton versio luo jonon taulukon *oletuskapasiteetin* kokoiseksi jos toteutuksena on taulukko.
2. Parametrillinen versio (int) luo taulukon sen kokoiseksi kun parametri kertoo.

> Linkitetty lista -toteutus ei välitä kapasiteetti-parametrista.

Muistaa antaa kaikille jäsenmuuttujille järkevät arvot muodostimissa.

**Testaa** toteutustasi testiluokan `QueueTests` kautta. Testi löytyy hakemistosta `task_05_queue`.

Jos kaikki toimii, voit edetä seuraavaan askeleeseen.


## Askel 2 - Ohjeet

Tässä askeleessa jonotietorakennetta käytetään johonkin järkevään.

Käynnistä TIRA Coders. Lataa joku koodaritiedosto jossa on suhteellisen vähän koodareita, valikosta TIRA Coders > Import JSON Phonebook.

Kun tiedosto on ladattu, Valitse valikkokomento TIRA Coders > Create Call Queue. Näkyviin tulee valikko josta voit valita minkä ohjelmointikielen koodareita nyt haluttaisiin soitella läpi uusiin projekteihin koodailemaan. Kun olet valinnut kielen ja kuitannut sen napista, näkyviin tulee soittolista. Kun klikkaat painiketta Next coder (dequeue), otetaan jonotietorakenteesta seuraava koodari soittolistalta. Näet koodarin nimen ja puhelinnumeron. Eikun rimpauttelemaan, josko kaveri haluaisi mukaan projektiin! 

Klikkaile koko lista läpi jotta näet että toteutus toimii varmasti käyttöliittymänkin kautta, vaikka testit olivatkin menneet läpi. Ohjelma ei siis saa kaatua, kaikki koodarit tulisi käydä läpi, ja viimeisen jälkeen seuraavaa ei voi enää valita.

Näin toimii jonotietorakenne.

## Raportti

**Kirjaa** raporttiisi `RAPORTTI.markdown` mitä opit tehtävän tekemisestä, mikä oli vaikeaa, mikä helppoa, jne.

Jos toteutit tässä molemmat tietorakenteet, mainitse siitä raportissasi, jotta opettajat huomaavat testata ne ja antaa tehtävästä lisäpisteet!

Vaikket olisi toteuttanutkaan molempia vaihtoehtoisia toteutuksia jonosta (taulukko ja linkitetty lista), pohdi miten ne eroavat toisistaan:

* Missä asioissa linkitetty lista on parempi kuin taulukkopohjainen toteutus, muistin käytön (muistikompleksisuus) ja aikatehokkuuden (aikakompleksisuus) suhteen?
* Missä asioissa taulukkopohjainen toteutus päihittää linkitetyn listan, muistin käytön ja aikatehokkuuden suhteen?

Analysoi erityisesti sitä, onko toteutuksesi oikeasti sellainen että se vastaa tehtävän alussa esiteltyjä aikakompleksisuusvaatimuksia. 

> Mikä tahansa metodi jossa on *silmukka*, ei *voi* olla O(1) -- onko sinulla silmukoita metodeissa joissa vaatimus oli O(1)? Tai kutsutko tällaisesta metodista jotain *toista* metodia jonka O on muuta kuin O(1)? Jos näin on, aikakompleksisuusvaatimus ei täyty.

## Lopuksi

Kun olet valmis, varmista että sekä raportti että kaikki uusi ja muutettu koodi on paikallisessa git -repositoryssäsi ja myös etärepositoryssäsi (komennot `git commit`, tarvittaessa uusille tiedostoille `git add` sekä `git push`).

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.