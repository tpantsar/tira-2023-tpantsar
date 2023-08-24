# Pino, keko ja pinon ylivuoto

Sovelluksilla (eli prosesseilla) on käytettävissä yleensä kahdenlaista muistia: **pino** (stack) ja **keko** (heap).

Molemmat muistit sijaitsevat tietokoneen työ- eli päämuistissa eli RAM:ssa. Yleensä tietokoneissa on tätä muistia 4-8 Gt, tosin isommat 16 Gt muistit ovat yleistyneet. Ohjelmoijan tietokoneessa muistia olisi hyvä olla ainakin tuo 16 Gt.

## Pino

Pinomuisti on aina **vakiokokoinen**, ja se varataan sovelluksen käyttöön kun sovellus ladataan muistiin. Pinomuistia varataan Windowsissa sovellukselle (prosessi, suoritettava ohjelma) oletusarvioisesti yhden (1) megatavun verran, jos muuta ei määritellä. Tämä on niinsanottu pinon oletuskoko (*default stack size*). Unix -pohjaiset käyttöjärjestelmät (mukaanlukien Linuxit ja macOS) varaavat pinomuistia tyypillisesti 8Mt jokaiselle prosessille.

Pieni pinomuisti on siitä hyvä, että mitä enemmän prosesseja tietokoneessa on käynnissä, sitä vähemmän muistia kuluu oletusarvoisesti tälle pinomuistille, jota prosessi ei aina sitä oletusmäärää tarvitse. Jos tietokoneessa on käynnissä 20 prosessia, Windowsissa nämä kuluttavat 20 Mt muistia kun taas *nix -koneissa ne kuluttavat sitä yhteensä 160 Mt.

Java -sovelluksille pinomuistia annetaan oletusarvoisesti aina 1 Mt, oli kyse mistä käyttöjärjestelmästä tahansa.

Toisaalta, iso pinomuistin koko on siitä hyvä, että silloin sovelluksella on enemmän muistia käytettävissä funktioiden paikallisille muuttujille ja funktiokutsupinolle. Näitä pidetään pinomuistissa.

Jos pinomuistia on vähän, sovellus voi kaatua **pinon ylivuotoon** (stack overflow). Pinon ylivuoto lopettaa sovelluksen suorittamisen välittömästi, eli "kaataa" sovelluksen. Pino voi vuotaa yli myös jos sovellus käyttää paljon pinomuistia: koodissa on paljon ja/tai isoja paikallisia muuttujia jotka vievät paljon muistia, tai funktiokutsuhierarkiat ovat syviä, esimerkiksi rekursion käytön vuoksi.

Tässä C -kielinen esimerkki tilanteesta, jossa sovelluksessa on liikaa muuttujia, joka eivät mahdu pinomuistiin:

```C
#include <stdio.h>

void recursive(int repeats);

int main() {
   recursive(20);
   return 0;
}

void recursive(int repeats) {
   printf("Repeats left: %d\n", repeats);
   long array[100000];

   if (--repeats >= 0) {
      recursive(repeats);
   } else {
      printf("At the bottom!\n");
   }
}
```

Tässä pääohjelma kutsuu rekursiivista funktiota joka kutsuu itseään 20 kertaa. Ohjelman suorittaminen johtaa ohjelman kaatumiseen (ohjelma käännetty tiedostoon `overflow`):

```console
./overflow
Repeats left: 20
Repeats left: 19
Repeats left: 18
Repeats left: 17
Repeats left: 16
Repeats left: 15
Repeats left: 14
Repeats left: 13
Repeats left: 12
Repeats left: 11
[1]    8500 segmentation fault  ./overflow
```

Koska jokainen funktion kutsu kuluttaa pinomuistia 100000 * 8 tavua = 800000. Kun funktio kutsuu itseään 20 kertaa, pinomuistia on käytetty jo 16 000 000 tavua, siis 16Mt -- testikoneen 8 Mt pinon oletuskoko olisi ylitetty jo kaksinkertaisesti. Ohjelma siis kaatuu tuossa kymmenennen funktiokutsun jälkeen, pinomuistin loppuessa.

Syvät rekursiiviset funktiokutsut kuluttavat paljon muistia vaikka isoja muuttujia funktioissa ei varattaisikaan. Jos toteutat lajittelun Quicksort -algoritmilla (tai millä tahansa muulla rekursiivisella algoritmilla), ja lajittelu tehdään todella isolle aineistolle, tämä johtaa erittäin syviin rekursiivisiin funktiokutsuihin ja saattaa johtaa pinon ylivuotoon.

Tästä syystä Python -kielessä on kiinteä raja funktiokutsupinon koolle: 1000 kutsua. Jos Python -koodissa rekursio menee syvemmälle, sovellus kaatuu. On mahdollista kasvattaa tätä numeroa, mutta sitten ongelmia voi taas tulla pinomuistin loppuessa...

Jos pinomuistia kerta on rajoitetusti ja/tai vähän, voiko asialle tehdä jotain? Kyllä: käytetään muuttujien pitämiseen muistissa **kekomuistia** aina jos muuttujat ovat isoja, tai pieniäkin muuttujia, jos niitä on paljon.

## Keko

Kekomuistia on prosesseille käytettävissä periaatteessa niin paljon kuin fyysistä muistia riittää, mukaanlukien virtuaalimuisti. Yleensä siis muistin varaus kekomuistista ei epäonnistu moderneilla tietokoneilla. Käyttöjärjestelmä voi siirtää (sivuttaa, *paging*) RAM-muistissa olevaa dataa (ohjelmakoodi ja data) levylle vapauttaakseen RAM -muistia sitä tarvitsevien sovellusten käyttöön. Tämä toki hidastaa konetta, jonka käyttäjä voi huomata.

Sulautettujen ohjelmistojen tapauksessa, levylle sivuttamista ei ole käytettävissä, ehkä koska mitään tallennusvälinettä laitteessa ei edes ole tai tallennustilaa riittää vain ohjelmakoodille ja muulle tarpeelliselle datalle. Näissä muistin varaus voi siis oikeasti päätyä virhetilanteeseen kun vapaata muistia ei ole varattavaksi. On siis hyvä ohjelmointikäytäntö aina varata muistia vasta kun sitä tarvitaan ja vapauttaa se heti kun sitä ei enää tarvita, jotta vapaata muistia olisi käytettävissä sitä tarvitseville prosesseille. Ja hyvä käytäntö on myös varautua tilanteisiin joissa muisti (tai muu resurssi) ei olekaan käytettävissä.

Yllä oleva pinon täyttävä taulukko olisi siis hyvä varata kekomuistista, jotta ei tapahtuisi pinon ylivuotoa. Se tehdään C -kielellä näin:

```C
#include <stdio.h>
#include <stdlib.h>

void recursive(int repeats);

int main() {
   recursive(20);
   return 0;
}

void recursive(int repeats) {
   printf("Repeats left: %d\n", repeats);
   long *array = malloc(sizeof(long) * 100000);

   if (--repeats >= 0) {
      recursive(repeats);
   } else {
      printf("At the bottom!\n");
   }
   free(array);
}
```

Nyt pinon ylivuotoa ei tapahdu, ja kekomuistia riittää tarpeeksi tälle datalle. Koska funktiokutsupinokaan ei kasva liian suureksi, pinon ylivuotoa ei enää tapahdu:

```console
./overflow
Repeats left: 20
Repeats left: 19
Repeats left: 18
Repeats left: 17
Repeats left: 16
Repeats left: 15
Repeats left: 14
Repeats left: 13
Repeats left: 12
Repeats left: 11
Repeats left: 10
Repeats left: 9
Repeats left: 8
Repeats left: 7
Repeats left: 6
Repeats left: 5
Repeats left: 4
Repeats left: 3
Repeats left: 2
Repeats left: 1
Repeats left: 0
At the bottom!
```

Tämä esimerkki on tehty C -kielellä, mutta pinon ja keon käyttämisen periaatteet ovat ihan samanlaisia myös Javassa ja monessa muussakin ohjelmointikielessä. Erona Javassa ja C++:ssa tähän esimerkkiin on se, että kekomuistia varataan näissä kielissä `new` operaattorilla, ei `malloc` -funktiolla.

Tässä (ja myös Javaa ja C++:aa käyttäessä) vain tuo osoitinmuuttuja (tai viittaus) on pinomuistissa, mutta varsinainen taulukko on kekomuistissa.

C/C++:ssa muistin vapauttamisesta pitää huolehtia itse (jos ei käytä C++:n älykkäitä pointtereita). Jos ohjelmoija unohtaa kutsua C:n free -funktiota (tai C++:ssa `delete [] array`), muisti jää vapauttamatta ja tapahtuu **muistivuoto** (*memory leak*) joka on vakava ohjelmointivirhe.

 Javassa virtuaalikoneen roskienkerääjä (garbage collector) huolehtii siitä, että oliot, joihin ei enää löydy viittauksia, poistetaan muistista aina silloin tällöin. Jos haluaa varmistaa että viittauksia olioon ei ole, voi sijoittaa olion viittaukseen null:n...:

 ```Java
   MyVeryLargeClass largeObject = new MyVeryLargeClass();
   //...
   largeObject = null;
```

...ikäänkuin vinkkinä roskienkerääjälle että oliota ei enää tarvita. Virtuaalikoneen pitäisi kyllä huomata jos olioon ei enää viitata. Toki jäsenmuuttujana luokassa oleva olio kannattaa laittaa null:ksi heti kun oliota ei enää tarvita, jos se toinen olio, jonka sisällä jäsenmuuttuja-olio on, jatkaa elämäänsä vielä tovin.

Java tosiaan huolehtii roskienkeruun avulla muistin vapauttamisesta, kun taas esimerkiksi Swift käyttää viittausten laskentaa (*reference counting*) saman tavoitteen saavuttamiseksi.

Eli ratkaisuna pinomuistin loppumiseen on laittaa dataa kekomuistiin. Entäpä sitten nämä **syvät rekursiiviset funktiokutsut**, noitahan ei saa kekomuistiin millään?

Tästä alla.

## Rekursion aiheuttama pinon ylivuoto Javassa

Jos käytät rekursiota hyödyntäviä algoritmeja koodissasi, ja käsiteltävät aineistot ovat isoja ja johtavat syviin rekursioihin datan määrän takia, ohjelma voi kaatua pinon ylivuotoon.

Javan oletuskoko pinolle on 1 Mt. Tämä ei aina riitä. Voit ensin varmistaa että et käytä isoja muuttujia siten että ne kuormittavat pinoa. Jos koodista ei löydy isoja esimerkiksi taulukoita:

```Java
int veryLargeArray[100000];
// or
char veryLargeCharArray[100000];
```
tämä ei luultavasti ole syynä pinon ylivuotoon.

Jos tällaisia on, varaa nämä taulukot kekomuistista.

Jos syynä pinon ylivuotoon ovat rekursiiviset algoritmit, on hyvä tietää että monista rekursiivisista algoritmeista on olemassa myös **ei-rekursiivinen** versio. Tämä koskee vaikkapa binääristä hakua (*binary search*) tai pikalajittelua (*quick sort*). Molemmat voidaan toteuttaa ei-rekursiivisesti. Haku voidaan tehdä silmukoilla ja quicksortin ei-rekursiivinen versio [Lampsort](https://bertrandmeyer.com/2014/12/07/lampsort/) voi olla hyvä vaihtoehto. Tai sitten vaihtaa vaikkapa kekolajitteluun (heapsort) ja varmistaa että tekee sen ei-rekursiivisesti.

Jos kuitenkin haluat käyttää rekursiota, voit **kasvattaa** sovellukselle varattavan **pinon kokoa**. Esimerkiksi yhdestä megatavusta neljään tai vaikkapa kahdeksaan.

Hankalaksi asian tekee että Java -sovelluksen voi käynnistää usealla eri tavalla, varsinkin kun sitä ollaan vielä koodaamassa. Sovellus voidaan käynnistää joko:

1. komentoriviltä: `java -jar ...` kun tuo .jar -tiedosto on ensin kääännetty vaikkapa `mvn package` -komennolla.
2. IDE:n debuggerista Run/Debug -komennolla.
3. IDE:n testipenkistä käsin.
4. Komentoriviltä testaten, esimerkiksi `mvn test`.

Kun pinon kokoa halutaan kasvattaa, se pitää tehdä erikseen kaikille näille neljälle eri tavalla jolla ohjelma voidaan käynnistää. Koska pinon kokoa ei kasvateta ohjelmakoodista käsin vaan sovellusta käynnistettäessä.

Näistä ohjeet alla. Käytä näitä jos algoritmisi tuottaa pinon ylivuodon rekursion vuoksi.

### Komentoriviltä käynnistettäessä

Tämä on helppo ratkaista.

Pinon koko voidaan määritellä tässä tilanteessa `java` -komennon parametreilla:

```console
java -Xss8m -jar target/booksandwords-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Tässä parametri `-Xss8m` kasvattaa pinon koon oletusarvosta 1Mt niinkin isoksi kuin 8Mt. Samalla tavalla (mutta eri parametreilla) voidaan määritellä myös kekomuistille minimi- ja maksimikoot, jos näin halutaan.

Jos koit pinon ylivuodon, kokeile kasvattaa pinon kokoa vähitellen (2, 4,...), kunnes se riittää. Tai jos koodisi on pinomuistin käytön osalta rohmu, korjaa koodia.

### Testien ajaminen komentoriviltä

Kun käynnistät testejä komentoriviltä (`mvn test`), voit lisätä pinon kokoa kertomalla sen projektin `pom.xml` -tiedostoa muokkaamalla. Lisää alla olevan esimerkin mukainen konfiguraatio tiedostossa jo olevan `<build>` -rakenteen sisällä olevaan `maven-surefire-plugin` -pluginin konfiguraatioon:

```XML
<plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-surefire-plugin</artifactId>
   <configuration>
      <argLine>-Xss8m</argLine>
   </configuration>
   <version>2.22.2</version>
</plugin>
```
Olennaista tuossa on tuo `configuration` elementin sisällä oleva `argLine` elementti ja sen sisältö.

Tee muutokset *varovasti* ja oikeaan paikkaan, ettet sotke koko `pom.xml` -tiedostoa!

### Sovellus tai testit käynnistetään VS Codesta käsin

Kun **testaat** sovellusta VS Codesta käsin, lisää seuraava asetus VS Coden workspace settings:iin, osioon `java.test.config`:

```JSON
"-vmArgs" : ["-Xss8m"]
```

Löydät tämän asetuksen kun valitset VS Coden ikkunan vasemmasta alakulmasta olevan hammasrattaan, ja valitset valikon Settings...ja sitten etsi asetusta nimellä `"java.test.config"` -- tämän jälkeen editoi tämä asetus siten että se sisältää yllä olevan `vmArgs` -asetuksen.

Kun taas haluat **suorittaa** tehtävässä mahdollisesti olevan main -funktion kautta ohjelman, pinon koko konfiguroidaan eri paikassa. Tätä varten luodaan tiedosto `launch.json`, johon määritellään myös samainen `vmArgs` asetus jossa tarpeelliset parametrit (viimeinen rivi alla):

```JSON
      {
         "type": "java",
         "name": "Launch TIRA Coders",
         "request": "launch",
         "mainClass": "oy.interact.tira.TIRACodersApp",
         "projectName": "coders",
         "vmArgs": ["-Xss8m"]
      }
```

Vastaava asia voidaan tehdä myös kekomuistin koon määrittelemiseksi. Tästä ohjeita oppaassa [NOT-ENOUGH-MEMORY.markdown](NOT-ENOUGH-MEMORY.markdown).

Jos teet kaikki nämä muutokset, voit suorittaa ja testata ohjelmaa näillä neljällä tavalla eikä pinon ylivuoto pitäisi enää vaivata.


# Lopuksi

Pinon ylivuoto voi tapahtua myös koodissasi olevan **bugin** takia. Jostain syystä esimerkiksi rekursiota ei pysäytä mikään, ja se etenee aina vaan syvemmälle ja syvemmälle ja päättyy lopulta pinon ylivuotoon. Tämä on tietysti ohjelmointivirhe, joka on korjattava aina koodissa eikä se korjaannu kasvattamalla pinon kokoa, koska tässä tilanteessa algoritmille ei riitä *mikään*.

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.