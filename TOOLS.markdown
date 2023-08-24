# TIRA-2023 Työkalut

Tässä ohjeessa kerrotaan ohjeet kurssilla käytettävien työkalujen asentamiseen.

Katso myös Moodlessa olevat **demovideot** jossa näytetään miten työkalut asennetaan ja otetaan käyttöön (käyttöönoton yksityiskohdat löydät ohjeesta [SETUP.markdown](SETUP.markdown)), miten työkaluja voidaan käyttää komentoriviltä ja miten Windows konfiguroidaan ohjelmistokehitystä varten. Jos nämä eivät ole sinulle tuttuja asioita, katso nämä videot Moodlesta.

## Työkalut

Tällä kurssilla tarvittavat työkalut ovat:

* [git](https://git-scm.com) versionhallintaa ja tehtävien toimittamista opettajille varten.
* [JDK version 20](https://jdk.java.net/20/) -- asenna versio 20 (tai uudempi) jos sinulla on vanhempi.
* [Maven](http://maven.apache.org) Harjoitustehtävien kääntämistä ja tehtävien suorittamista varten. Eclipse ja Visual Studio code osaavat tuoda Maven -projekteja näihin IDE -työkaluihin oikein.
* IDE (Integrated Development Environment) koodaamista ja debuggaamista varten. Opettajat käyttävät [Visual Studio Code](https://code.visualstudio.com), joten sitä voi suositella teillekin. Eclipse on toinen suosittu vaihtoehto.
* [Java extensions](https://code.visualstudio.com/docs/java/java-tutorial) to the VS Code. Hyödyllisiä laajennuksia VS Coden käyttöön Java -kielen kanssa. Asenna ainakin Extension Pack for Java, Language support for Java by Red Hat and Maven for Java extensions for VS Code. Koodin laatua parantaa lisäksi jos asentaa Sonarlint -laajennuksen joka varoittaa sellaisistakin huonoista koodauskäytännöistä mistä Java -kääntäjä ei varoita. Nämä asennetaan VS Coden Extensions -välilehdeltä.

Windowsiin uusi JDK kannattanee asentaa Oraclen asennusohjelman avulla, varsinkin jos tällaiset asiat eivät ole tuttuja.

Voit tarkistaa asentamasi Javan ja Mavenin versiot ja sen että ne toimivat, komentoriviltä. Esimerkki opettajan koneelta:

```console
> git --version
git version 2.32.1 (Apple Git-133)

> mvn --version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /opt/apache-maven-3.6.3
Java version: 20.0.1, vendor: Homebrew, runtime: /opt/homebrew/Cellar/openjdk/20.0.1/libexec/openjdk.jdk/Contents/Home
Default locale: fi_FI, platform encoding: UTF-8
OS name: "mac os x", version: "13.5", arch: "aarch64", family: "mac"

> java --version
openjdk 20.0.1 2023-04-18
OpenJDK Runtime Environment Homebrew (build 20.0.1)
OpenJDK 64-Bit Server VM Homebrew (build 20.0.1, mixed mode, sharing)

> javac --version
javac 20.0.1
```

Omalla koneellasi hakemistopolut ovat tietysti erilaiset, samoin versio voi olla uudempi, samoin ilmoitetut käyttöjärjestelmät jne. Pääasia että jotain muuta kuin virheitä tulostuu ja JDKn ja Mavenin käyttämien Javan versiot ovat samat.

**Varmista että** JDK:n `bin` ja Mavenin `bin` hakemistot ovat oikein tietokoneesi **PATH** ympäristömuuttujassa (environment variable), ja että **JAVA_HOME** ympäristömuuttuja osoittaa oikean asennetun JDK:n kotihakemistoon! 

Jos koodin kanssa on ongelmia, varmista myös että käyttämäsi IDE (VS Code tai joku muu) käyttää samaa JDK:ta kuin komentorivillä käytetään. Jos koneella on useampi versio JDK:sta tai IDE:ä on joskus käytetty vanhemman JDK:n kanssa, se voi "muistaa" sen. IDE:n asetuksista voit tarkistaa mikä JDK on käytössä. Tästä löytyy ohjeet kunkin IDEn ohjesivuilta. Tarvittaessa kysy opettajia apuun.

Harjoitukset ja harjoitustyöt sisältävät *yksikkötestejä* (unit tests). Tähän käytetään JUnit Jupiter versiota 5.7 tai uudempaa. Nämä komponentit asentuvat automaattisesti Mavenin tekemänä kun avaat näitä projekteja. Näitä ei siis tarvitse itse mitenkään erityisesti asentaa.

Suosittelemme myös että asennat jonkun kunnollisen terminaalisovelluksen (pääteohjelma), erityisesti Windowsissa. Windowsin (versio <=10) Command Prompt on oikeastaan aika huono työkalu ohjelmistokehitykseen.

Yksi vaihtoehto on Microsoftin [Windows Terminal](https://github.com/microsoft/terminal). [Muita terminaalisovelluksia on](https://dev.to/adnanmostafa/the-best-free-standalone-terminals-for-windows-2019-kmj) jotka on suunniteltu ohjelmistokehittäjän tarpeisiin ja ovat muutenkin mukavampia käyttää. macOS:n käyttäjille voi suositella esimerkiksi [iTerm2](https://iterm2.com) -ohjelmaa joka on hyvä vaihtoehto macOSssä valmiina olevalle macOS Terminal (Pääte) sovellukselle.

Kun olet asentanut ja varmistanut että nämä työkalut toimivat, jatka eteenpäin lukemaan miten valmistelet kurssin tehtävät käyttöösi: [SETUP.markdown](SETUP.markdown).

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.