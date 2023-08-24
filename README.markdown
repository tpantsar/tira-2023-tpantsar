# TIRA-2023

Tämä repository (koodiarkisto tai tietovarasto), lyhyesti "repo", sisältää tehtävämateriaalin kurssille Tietorakenteet ja algoritmit (lyhyesti TIRA).

Sinä opiskelijana luot tästä repositorystä oman kopiosi eli forkin GitHubissa oman tunnuksesi alle. Sen jälkeen kloonaat (clone) tämän forkin omalle tietokoneellesi ja teet kurssin tehtävät omalla tietokoneellasi. Sitä mukaa kun saat tehtäviä tehtyä, toimitat tekemisesi forkin kautta opettajien arvioitavaksi.

> Etärepository kannattaa pitää aina mahdollisimman ajan tasalla. Näin jos sinulla on ongelmia, opettajat pääsevät välittömästi hakemaan koodisi etärepostasi, ja voivat ohjata sinua eteenpäin.

Miten tämä kaikki tapahtuu, kerrotaan tässä ja muissa repon ohjetiedostoissa. Lisäksi kurssimateriaali sisältää useita demovideoita joiden kautta näet miten tämä tehdään. 

Kaikki kurssin tehtävät ovat yksilösuorituksia, joten ryhmätyötä tässä kurssilla ei tehdä.

**TÄRKEÄ huomautus**: tehtävissä **ei saa käyttää** Javan Collection (tietosäiliö) luokkia eikä niihin liittyviä algoritmeja (esimerkiksi `Collections`, `Arrays`, `Set`, `ArrayList`, `LinkedList`, `Stack`, `Vector`, `Queue`, mikään `Map` -toteutus, `Arrays.sort`, `Collections.sort`. jne.) ellei *erityisesti* sanota että se on sallittua jossain *tietyssä* tehtävässä. 

On **sallittua** käyttää `String` -luokkaa ja kaikkia primitiivisiä (alkeis-)tietotyyppejä kuten int, long, short, double, float, char, näiden luokkaversiot (`Integer`, `Double`, jne.) ja "tavalliset" taulukot (esim. `String [] arrayOfStrings`). Niissä tehtävissä joissa pitää toteuttaa **hajautusfunktio** (tiivistefunktio, hash), **ei saa käyttää** Javan luokkien valmista `hashCode()` -metodia (esim. `String.hashCode()`), ellei taas erikseen toisin mainita, vaan tiivistefunktiot toteutetaan *itse*. Omia apuluokkia ja -algoritmeja on luonnollisesti hyväkin tehdä, tehtävästä riippuen.


## Työkalut

Kurssilla tarvittavien työkalut ja niiden asennusohjeet on kerrottu [TOOLS.markdown](TOOLS.markdown) ohjeessa. 

**Asenna työkalut ensin** ja jatka sitten ohjeiden lukemista eteenpäin. Katso myös Moodlesta löytyvät demovideot aiheesta.


## Miten pystytän työtilani

Ohjeessa [SETUP.markdown](SETUP.markdown) kerrotaan miten voit forkata ja kloonata kurssin alkuperäisen repon omaa työskentelyäasi varten. Tämän tehtyäsi työskentelet omalla koneellasi *paikallisen* (local) reposi kanssa, ja kun olet saanut tehtävää eteenpäin, työntää työsi omaan etärepositoryysi.

Käytät git:iä kurssilla siihen että:

1. lisäät uusia kooditiedostoja ja muutoksia olemassa olevaan koodiin omalla koneella olevaan paikalliseen git-repositoryysi,
1. työnnät (push) paikallisessa repossa olevaa koodia omaan etärepositooryysi,
1. josta opettajat voivat tarkastella edistymisetäsi, auttaa sinua ongelmien kanssa ja lopuksi hakea koodisi testattavaksi ja arvioitavaksi arvosanan antamista varten.

Seuraa siis ohjeita [SETUP.markdown](SETUP.markdown) -tiedostossa. Tämä pitää tehdä *vain kerran* kurssin alkaessa.


## Ajoitus ja määräajat

Taulukko alla kuvaa kurssin toteutuksen aikataulun syksyllä 2023. 

> Huomaa että jos se sinulle on mahdollista, voit edetä **nopeammin** kuin kurssin aikataulu esittää.

Jokainen kurssin tehtävä on omassa ohjetiedostossansa (`01-TASK.markdown`, jne. numeroituna) jossa on ohjeita kyseisen tehtävän toteuttamiseksi. Kun etenet tehtävän kanssa, lue nämä ohjeet *huolellisesti* ennen kuin aloitat työskentelyn. Etene tehtävät *järjestyksessä*.

Kurssin deadline:n tullessa, kurssin opettajat kloonaavat skripteillään repositorynne automaattista testausta varten. Noudata kurssin määräaikoja; tarkistamme lataamamme deadlinen aikaisen version ja annamme pisteet, miinuspisteet ja arvosanan deadlinen aikaisen version perusteella.

### Luennot

Kurssin **liveluennot** ovat lähinnä opiskelun tueksi ja demojen läpikäyntejä varten. Näiden aikataulutus löytyy Moodlen kalenterista. Varsinaiset luennot ovat katsottavissa videotallenteina.

Voit katsoa luennot videotallenteina omaan tahtiisi. Huomaa kuitenkin että kuhunkin harjoitukseen liittyvät luennot on syytä katsoa *ennen* kuin aloitat kyseisen harjoituksen tekemisen. Luennot antavat taustaa, esimerkkejä ja käsitteitä joita tarvitset tehtävien tekemisessä. Tavallisin syy tehdä turhaa työtä ja/tai menettää pisteitä tehtävistä on se, ettei ole oikealla tavalla sovellettu luennoilla ja muissa kurssimateriaaleissa esitettyjä asioita.


### Harjoitukset 

**Harjoitusten** aikataulutus on suunniteltu niin että *aloitat* harjoituksen alla mainitulla viikolla. Huomaa tehtävien ajoitus ja deadlinet, palauta työsi ajoissa. Älä jätä hommia viime tippaan, se on yleinen resepti lisästressille ja pisteiden menetykselle. Aloita töiden tekeminen ja viimeistele työsi mieluummin etupainoitteisesti.

**Deadlinet**: Kun kurssin aikainen välideadline tulee, opettajat ottavat reposi ja suorittavat tehtävien yksikkötestit. Mikäli yksikkötestit eivät mene läpi, kyseisestä tehtävästä tulee miinuspiste. Voit jatkaa tehtävän tekemistä deadlinen jälkeen,mutta miinuspiste pysyy. Huomaa että vaikka testit menisivät läpi, se ei tarkoita sitä että tehtävästä saa täydet pisteet. Lopullisessa arvostelussa, kurssin viimeisen deadlinen jälkeen, tehtävän toteutus käydään läpi, ja suoritetaan lopullinen arvostelu. Tehtävän arvostelu voi olla tällöin hylätty (tehtävä pitää tehdä hyväksyttävästi arvosanan saamiseksi), nolla tai tehtävän maksimipistemäärä tai jotain siltä väliltä.

> Valitettavasti opetuksen resurssien vähyyden vuoksi palautetta voidaan antaa vain siltä osin kuin hylätyn tehtävän korjaamiseen tarvittavaa tietoa pitää hylkäysilmoituksen yhteydessä antaa. Jos olet kiinnostunut arvosanasi perusteluista, voit kysyä vastuuopettajalta perustelut kun olet saanut kurssista arvosanan.

Hyödynnä harjoituksia ja muita tukimuotoja **ajoissa**. Deadlinen yhteydessä et saa palautetta tehtävästä. Jos haluat palautetta ja/tai varmistaa että pistemäärä on sinulle riittävä, tule kurssin tukisessiohin hakemaan palautetta ja kommentteja toteutuksestasi.

> Vihje: Voit pysyä kärryillä mitä koodissa on vielä tekemättä, poistamalla jokaisen `TODO` kommentin koodista kun olet saanut kyseisen kohdan tehtyä. Kun et löydä enää IDEn hakutyökalulla `TODO` kommentteja koodista, ja testit menevät läpi, olet tehnyt kaikki tehtävät. Huomaa kuitenkin että koodisi ei välttämättä vielä ole täydellistä eivätkä testit välttämättä huomaa kaikkia koodissa olevia ongelmia.

**Aikataulu ja deadlinet** selviävät alla olevasta taulukosta. Plussalla merkityt tehtävät ovat valinnaisia tehtäviä joista saa lisäpisteitä.


| Viikko|  Aihe ja luentovideo   | Tehtävä                             | Huomioitavaa             |
|------:|------------------------|-------------------------------------|--------------------------|
| 36    | A  Kurssin esittely    | Ohjelmoijan nimigeneraattori        | Ei arvosteltava tehtävä! | 
|       | B  Aiheen esittely     | [01-TASK](01-TASK.markdown)         | Ohjelmointi 2:sta tuttua:|
|       |                        |                                     | - lisäyslajittelu        |
| 37    | C  Aikakompleksisuus   | [01-TASK](01-TASK.markdown) jatkuu  |                          |
| 38    | D  Analyysi            | [02-TASK](02-TASK.markdown)         | Lajittelu asc/desc,      |
|       |                        |                                     | lineaarinen haku         |
| 39    | E  Intro: lajittelu    | [03-TASK](03-TASK.markdown)         | Puolitushaku; 2 versiota+|
| 40    | F  Pino                | [04-TASK](04-TASK.markdown)         | Pinotietorakenne         |
| 41    | G  Jono, Linkitetty lista | [05-TASK](05-TASK.markdown)      | Jono (linkitetty lista+) |
| 42    | H  Lajittelualgoritmit | [06-TASK](06-TASK.markdown)         | Quicksort, heapsort+,    |
| 43    |    Lajittelualgoritmit | [06-TASK](06-TASK.markdown) jatkuu  | merge sort+,...          |
| 44    | I  Binäärinen hakupuu  | [07-TASK](07-TASK.markdown)         | Sisältää valinnaisia+    |
| **44**| **Deadline 1**         | **Tehtävät 01-06 tehtynä**          | **Tentti 1**             |
| 45    |    Binäärinen hakupuu  | [07-TASK](07-TASK.markdown) jatkuu  |                          |
| 46    | J  Hajautustaulu       | [08-TASK](08-TASK.markdown)         |                          |
| 47    |    Hajautustaulu       | [08-TASK](08-TASK.markdown) jatkuu  |                          |
| 48    | K  Verkot (graphs)     | [09-TASK](09-TASK.markdown)         | Sisältää valinnaisia+    |
| 49    |    Verkot (graphs)     | [09-TASK](09-TASK.markdown)         | Sisältää valinnaisia+    |
| **49**| **Deadline 2**         | **Tehtävät 07-08 tehtynä**          |                          |
| 50    |    Verkot (graphs)     | [09-TASK](09-TASK.markdown)         | Sisältää valinnaisia+    |
| 51    |    Verkot (graphs)     | [09-TASK](09-TASK.markdown)         | Sisältää valinnaisia+    |
| 51    | L Suunnittelu, dynamic |                                     |                          |
| 2/2024| **Deadline**           | **Kaikki tehtynä ja etärepossa**    | **Tentti 2**             |


Tenttiin 1 sisältyy luentojen aiheet A...H, ja tenttiin 2 sisältyy aiheet I...L. Tenttikysymykset saattavat hyödyntää myös ohjelmointimateriaalien (tehtävät) aineistoa. Molemmat tentit ovat á 10 pistettä ja tehdään Moodlessa. Tarkemmin tenteistä ja niiden ajankohdasta tiedotetaan kurssin Moodle -sivuilla.

Deadline on aina kyseisen viikon maanantaina klo 16:00 EET. Opettajien skriptit hakevat reposi sisällön koneelle tuona ajankohtana, ja sen hetkinen versio arvioidaan.

## Arvostelu

Kurssi arvostellaan seuraavin periaattein:

1. Opettajat tarkistavat välideadlinen yhteydessä, onko tehtävät tehty riittävälle tasolle (testit menevät läpi) **deadlineen** mennessä. Jos näin ei ole, jokaisen tehtävän, joka ei ole hyväksyttävä, pisteistä **vähennetään 1 piste**. Näitä miinuksia voi halutessaan kompensoida tekemällä valinnaisia tehtäviä. Mikäli pisteet eivät riitä kurssin läpäisyyn, valinnaisia tehtäviä on tehtävä sen verran että pisteet riittävät läpipääsyyn. On myös mahdollista parantaa tehtäviä siten että niistä saa mahdollisista miinuksista huolimatta lisää pisteitä läpi pääsemiseksi.
1. Lopullinen tehtävän pisteytys tehdään lopullisen arvioinnin yhteydessä, ja siinä otetaan huomioon väli- ja lopullisen deadlinen ylityksistä saadut miinuspisteet.
1. Tentistä pitää saada vähintään 10 pistettä (max 20 pistettä).
1. Koodin laatu vaikuttaa arvosteluun. Katso lista alla.
1. Se että tehtävä läpäisee testit, ei vielä tarkoita hyväksyttyä tai erinomaista suoritusta (testit eivät voi huomata kaikkea).
1. Tehtävästä voi saada myös nolla pistettä, miinuspisteiden takia, tai jos toteutus ylittää hyväksymiskynnyksen mutta se ei osoita että tehtävän oppimistavoite olisi saavutettu.
1. Tehtävissä ei saa käyttää Javan tietosäiliöluokkia tai algoritmeja (lukuunottamatta 09 -tehtävää (verkkotietorakenne)). Omia tietosäiliöitä ja algoritmeja jotka on tehty edeltävissä harjoituksissa *saa* ja niitä *oletetaan* käytettävän hyödyksi. 

Huomaa että tehtävien toteutuksessa *ei riitä* että toteutuksesi toimii (on toiminnallisesti oikeellinen). Toteutuksesi täytyy toteuttaa *aikakompleksisuuden* suhteen *tehokas* tapa käsitellä *suuria* tietomääriä. Totetutus joka toimii, voi siis olla jopa hylätty, jos se ei toimi tarpeeksi nopeasti suurilla tietomäärillä. Tästä lisää kurssin luennoilla ja tehtävien ohjeissa.

Huomaa myös että tässä harjoitellaan *yleiskäyttöisten* tietorakenteiden ja algoritmien toteuttamista, muidenkin ohjelmoijien käyttöön. Koodin ei siis pidä tehdä mitään muuta kuin se mitä tietorakenteen tai algoritmin kuuluu tehdä. Esimerkkejä siitä mitä lopullisen palautuksen koodissa **ei pitäisi** olla:

* Tietorakenneluokassa on `main()` -metodi omia kokeiluja varten. Poista tämä ja tee oma luokka (`.java` -tiedosto) omia testejä varten sinne missä tietorakenneluokkasikin on (*ei* testikoodihakemistoon).
* Koodissa on varoituksia aiheuttavia kohtia kun sitä käännetään. Korjaa koodi varoitukset poistaen. Paikoin on mahdollista myös hiljentää varoitus.
* Poista opettajan koodiin laittamat TODO -kommentit kun koodi on toteutettu.
* Koodissa on kommentoitua koodia. Poista nämä kun niitä ei kerran tarvita. Poikkeuksena koodi joka on osa kommentteja, tai se että haluat jättää koodiin vaihtoehtoisen toteutuksen tai yritelmän jonka haluat jättää selityksen kera koodiin.
* Koodin joukossa on käyttämättömiä luokkia, metodeja, muuttujia, parametreja tai vakioita. Poista nämä.
* Koodi sisältää testikoodia ("kokeilempa miten tämä toimisi"), joka ei ole osa lopullisen tietorakenteen tai algoritmin toteutusta. Poista nämä.
* Koodi käyttää poikkeustenhallintaa (try/catch) algoritmin logiikan ohjaamiseen. Try/catch -lohkoja ja poikkeuksia tulee käyttää *vain* virhetilanteiden käsittelyyn.
* Tietorakenne tai algoritmi tulostaa konsoliin (System.out.println tms., jos ei *erikseen* sanota että näin tulee tehdä). *I/O on hidasta*, ja hidastaa koodisi suoritusta. Tällä on merkitystä varsinkin silloin kun testit mittaavat algoritmisi suoritusaikaa! Poista tulostukset jos erikseen tehtävässä niitä ei pyydetä tekemään.
* Tietorakenne tai algoritmi käyttää muistia enemmän kuin mitä on välttämättä tarpeen sen toteuttamiseksi. Pääsääntö on toteuttaa algoritmit "in place", esimerkiksi lajittelu (Quicksort, Heap sort). Tosin osa algoritmeista käyttää lisämuistia *suunnitellusti*, kuten Merge sort - tällöin lisämuistin käyttö on tietysti sallittua.
* Koodi on epäsiistiä, sisennykset pielessä, nimeämistyyli ei noudata Javan nimeämistyyliä tai muuttujien ja metodien nimet ovat käsittämättömiä tai haittaavat koodin ymmärtämistä. Käytä IDEn koodin formatointityökalua (VS Code: hiiren oikea näppäin koodieditorin alueella > Format Document). Luokkien nimet alkavat isoilla kirjaimilla (`FastHashTable`), metodien, muuttujien ja parametrien pienillä noudattaen `smallCamelCase` -nimeämistyyliä, vakiot nimetään `ISOILLA_KIRJAIMILLA_SNAKE_CASE`.
* Mikäli koodissa on käytetty virheitä piilottavia ratkaisuja (esimerkiksi try/catch piilottaa poikkeuksen), tämä johtaa joko **hylkäämiseen** tai minimissään siihen että tehtävästä saa **negatiivisia** pisteitä.

Yllämainitut asiat eivät välttämättä tarkoita hylättyä tehtävää, mutta ne voivat laskea pistemäärää.

Koodin rakenne on tärkeä asia sen luettavuuden kannalta. Jos koodi on rakenteeltaan sekavaa, sitä on vaikea lukea ja siten vaikeampi ymmärtää. Kirjasta *Clean Code* (Robert C. Martin): 

> ”Back in my days working in the Bell Labs Software Production Research organization (Production, indeed!) we had some back-of-the-envelope findings that suggested that consistent indentation style was one of the most statistically significant indicators of low bug density.”


**Arvostelutaulukko**

| Kurssisuorite       | Minimiläpäisypisteet     | Keskivertopisteet    | Maksimipisteet       | Lisäpisteet    |
|---------------------|------------------:|---------------------:|---------------------:|---------------:|
| 01-TASK (sort)      | 1                 | 2                    | 3                    |                |
| 02-TASK (search)    | 1                 | 2                    | 3                    |                |
| 03-TASK (binsearch) | 1                 | 2                    | 3                    | 1              |
| 04-TASK (stack)     | 1                 | 2                    | 3                    |                |
| 05-TASK (queue)     | 1                 | 2                    | 3                    | 3              |
| 06-TASK (fastsort)  | 1                 | 2                    | 3                    | 6              |
| 07-TASK (bst)       | 1                 | 3                    | 6                    | 6              |
| 08-TASK (hashtable) | 1                 | 3                    | 6                    |                |
| 09-TASK (graph)     | 0                 | 3                    | 6                    | 3              |
| *Tentti*            | 10                | 15                   | 20                   |                |
| **Yhteensä**        | 18                | 36                   | 56                   | 19             |


Mahdolliset desimaalit (Moodlen tenttipisteet) pyöristetään ylöspäin seuraavaan kokonaislukuun ennen arvostelun tekemistä, jos desimaali on >= 0.50, muuten alaspäin.

Arvosana määräytyy pisteistä seuraavan taulukon mukaisesti.

| Pisteet | < 18  | 18-24 | 25-29 | 30-34 | 35-39 | >= 40  |
|---------|-------|-------|-------|-------|-------|--------|
| Arvosana| Hyl.  |   1   |   2   |   3   |   4   |   5    |

Jos haluaa varmistaa läpipääsyn ja/tai hyvän arvosanan, kannattaa tehdä tehtävät hyvin, panostaa valinnaisiinkin tehtäviin ja valmistautua tentteihin huolella.


## Toisten työn kopiointi ja plagiointi

Kaikki koodi ja muut suoritukset tällä kurssilla on oltava sinun itsesi kirjoitettamaa koodia ja tekstiä. Koodin kopiointi toisilta ja internetistä on **kiellettyä**. Voitte työskennellä yhdessä opiskelijakaverin kanssa ja keskustella toistenne kanssa toteutuksista, mutta jokainen kirjoittaa **joka ikisen rivin koodistaan** ihan itse. Jokainen vastaa itse omasta koodistaan ja sen toiminnasta tai toimimattomuudesta. Muista että se kaverin ratkaisu voi olla väärä tai huono, jolloin sen käyttäminen huonontaa myös omaa arvosanaasi.

Voit käyttää kurssin kaikkea materiaalia (luentomateriaali, pseudokoodit, demot, esimerkit) inspiraationa omalle toteutuksellesi. Voit tietysti myös etsiä internetistä tietoa ja esimerkkejä, mutta muista että itse koodaat kaiken mitä tehtävien toteutukseesi laitat. Huomaa myös että kun otat mallia muualta, voit myös ottaa huonoa mallia huonoista esimerkeistä. Varmista siis että toteutuksesi vastaa sitä mitä kurssilla on opetettu.

Jos hyödynnät jotain tiettyä esimerkkiä koodissasi jonka itse kirjoitit, on hyvä käytäntö (jota tällä kurssilla on syytä noudattaa) viitata käyttämääsi lähteeseen laittamalla koodiin kommentti jossa linkki käyttämääsi lähteeseen. Näin jos opettaja huomaa toteutuksessasi puutteita tai ongelmia, voimme tarkistaa lähteesi ja katsoa onko toteutuksesi lähteesi mukainen, oletko tulkinnut lähdettä väärin vai onko kenties käyttämäsi lähde väärässä tai huono, tai että se ei sovellu tilanteeseen jossa sitä käytit.

Jos on syytä epäillä kopiointia tai plagiointia, opettajat noudattavat [Oulun yliopiston käytäntöjä (pdf)](https://www.oulu.fi/external/Ohje-vilppitapausten-ehkaisemiseen-ja-kasittelemiseen-Oulun-yliopistossa-2018.pdf) vilppien käsittelyssä. Minimirankaisu vilppitapausten käsittelyssä on kurssin suorituksen välitön keskeyttäminen hylätyllä arvosanalla. Jos tapaus on vakavampi, sen käsittely viedään tiedekunnan dekaanin päätettäväksi.

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.
