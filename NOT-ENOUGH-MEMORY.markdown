# Muisti loppuu?

Joissakin tilanteissa kurssin tehtävien testiaineisto tarvitsee paljon kekomuistia (heap). Jos Java virtuaalikoneen varaama muisti ei riitä aineiston käsittelyyn, voi muisti loppua (out of memory virhetilanne).

Tietokoneessasi voi kuitenkin olla muistia tarpeeksi, sitä vaan ei ole Javan virtuaalikoneella tarpeeksi käytettävissä.

Tällöin voit varmistaa että muistia on tarpeeksi, alla kuvatuilla tavoilla.

Huomaa kuitenkin että **stack overflow** eli pinomuistin loppuminen on eri ongelma. Sen ratkomiseen tarvittavat ohjeet löydät tiedostosta [WHAT-STACKOVERFLOW.markdown](WHAT-STACKOVERFLOW.markdown). Tuossa ohjeessa kerrotaan myös siitä, mitä pino ja keko ovat.

Alla olevissa ohjeissa määritellään minimi- ja maksimimäärä kekomuistia Java -ohjelmalle. Kaikilla eri tavoilla tämä tehden käytetään seuraavia määreitä muistin koon antamiseksi:

* `-Xms4g` kertoo JVM:lle että kekomuistia (heap) sovellukselle pitää antaa vähintään 4Gt, ja
* `-Xmx16g` kertoo JVM:lle että kekomuistia allokoidaan maksimissaan 16Gt.

Jos koneessasi ei ole näitä määriä muistia, käytä pienempiä määriä. Jos taas muistia on enemmän, anna isompia numeroita. Kokeile mikä toimii omassa koneessasi ja mikä riittää tehtävän suorittamiseen. Lyhenteen g (giga) lisäksi voit kokeilla lyhennettä m (mega) ja antaa muistia vaikka sadoissa megatavuissa, jos se riittää.

Komentorivillä työskennellessä riittää muokata `pom.xml` -tiedostoa ja antaa java -komennolle oikeita parametreja. Tästä ohjeet alla.

VS Codessa testatessa ja ohjelmia suoritettaessa pitää lisäksi konfiguroida VS Codea. Tästäkin ohjeet alla, mutta lisää tietoa löytyy tämän linkin takaa:

https://code.visualstudio.com/docs/java/java-debugging#_configuration-options


## Suoritettaessa testejä komentoriviltä

Jos muisti loppuu suoritettaessa testejä komentoriviltä (`mvn test`), toimi näin.

Lisää projektin `pom.xml` -tiedostoon alla oleva elementti. 

**Huomaa** että tiedostossa on jo olemassa valmiina `build` -elementti, `plugins` -elementti ja ehkä tuo yksittäinen `plugin` myös joten **lisää vain tarvittavat elementit** alta **oikeaan kohtaan** tehtävän pom.xml -tiedostoa. **Olennainen** asia on tuossa `configuration` -elementin sisällä olevassa `argLine`  -elementissä.

```XML
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.22.2</version>
        <configuration>
          <argLine>-Xms4g -Xmx16g</argLine>
       </configuration>
      </plugin>
    </plugins>
```

## Suoritettaessa testejä VS Codesta käsin

Lisäksi kannattaa varmistaa, jos suoritat **testejä VS Codesta käsin**, että sen projektin asetustiedostossa on vastaava asia kuin komentoriviltä suoritettaessa (yllä). 

VS Coden projektikohtaiset asetukset löytyvät tehtävän eli projektin juurihakemistosta löytyy alihakemisto `.vscode` ja siellä on `settings.json` -niminen tiedosto (jos ei ole, luo se).

Asetustiedostoon tarvitaan seuraava JSON -elementti:

```JSON
{
	"java.test.config": {
	"-vmArgs": ["-Xms4g", "-Xmx16g"]
	}
}
```

Nyt kun suoritat testejä VS Codesta käsin, testejä varten varataan enemmän muistia.

## Jos harjoitustehtävässä on ohjelma

Jos harjoitustehtävässä tehdään suoritettava ohjelma, joka käännetään (`mvn package`) ja sitten suoritetaan (`java -jar ...`), lue ohjeet alta miten muisti määritellään komentoriviltä tai VS Codesta ohjelmaa suoritettaessa.

### Komentorivillä ohjelmaa suoritettaessa

Tämä koskee vain niitä tehtäviä joissa käännetään ohjelma komennolla `mvn package` ja sen jälkeen ohjelma voidaan ajaa komentoriviltä. Jos tehtävässä on vain testejä jotka ajetaan komennolla `mvn test`, tätä ohjetta tässä ei tarvita.

Normaalisti käännät ja suoritat Java -ohjelman näin:

```console
mvn package
java -jar target/ohjelman-jar-tiedosto.jar
```

Jos muisti ei riitä, voit määritellä minimi- ja maksimikoon JVM:lle annettavasta kekomuistista:

```command
java -Xms4g -Xmx16g -jar target/ohjelman-jar-tiedosto.jar
```

### VS Codesta ohjelmaa suoritettaessa

VS Codeen voidaan luoda tiedosto joka kertoo miten ohjelma suoritetaan, tiedostossa `launch.json` joka sijoitetaan myös hakemistoon `.vscode`, kuten myös tiedosto `settings.json`.

Jos `launch.json` -tiedostoa ei ole, voit luoda sen. Ohjeet löytyvät alla olevan linkin takaa:

https://code.visualstudio.com/docs/java/java-debugging#_configure

Esimerkki tiedoston sisällöstä alla. Olennaista on taas tuo `vmArgs` -asetus jossa voidaan kertoa minimi- ja maksimimuistin määrä (sekä pino -että kekomuistille):

```JSON
      {
         "type": "java",
         "name": "Launch TIRA Coders",
         "request": "launch",
         "mainClass": "oy.interact.tira.TIRACodersApp",
         "projectName": "coders",
         "vmArgs": [ "-Xss8m", "-Xmx16g" ] 
      }
```
`mainClass`:n nimi täytyy olla sen luokan nimi package -polkuineen, josta löytyy ohjelman `main(String [] args)` -metodi.

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.