# Raportit tehtävistä

Kirjaa tähän tiedostoon **jokaiseen** tehtävään liittyvät omat raporttisi ja analyysisi. Muista että raportti on myös kurssilla **arvosteltava tehtävä**.

Voit sisällyttää raporttiin tekstimuotoisia taulukoita (tasaukset välilyönnein):

```
n     Fill     Search   Total
500   7        700      707
1000  9        288      297
```

Ja näihin liittyviä kuvatiedostoja:

![Esimerkkikuva](report-sample-image.png)

Nämä näkyvät sitten VS Coden Preview -näkymässä (tai oman repositorysi webbisivulla) oikein muotoiltuna. Käytä tässä dokumentissa olevia muotoiluja esimerkkinä kun kirjoitat raporttiasi. 

Huomaa että jos laitat kuvatiedostot vaikka omaan alihakemistoonsa, Markdown -muotoilussa on oltava suhteellinen polku tiedostoon, esimerkiksi `images/report-sample-image.png`. **Älä** käytä absoluuttisia polkuja `C:\Users\tippaleipa\kurssit\TIRA\kuva.png`, koska nämä eivät tietenkään toimi opettajan koneella. Ei kannata laittaa linkkiä etärepoosikaan, vaan nimenomaan paikalliseen tiedostoon.

Voit myös sisällyttää *lyhyitä* koodinpätkiä vaikkapa Java -formaatilla:

```Java
	@Override
	public int hashCode() {
		// Oma nerokas hajautufunktioni!
	}
```
Tarvittaessa käytä myös paremmin muotoiltuja taulukoita:

| n	| Fill	| Search	| Total |
|-----|--------|--------|-------|
| 500	 | 7	| 700	| 707 |
| 1000 |	9	| 288	| 297 | 

Alaluvut jokaisen tehtävän raportille löydät alta.


## 01-TASK

insertionSort-metodien tekeminen oli lopulta yksinkertaista, kun tajusi idean sen takana. Comparable-rajapinnan käyttö oli entuudestaan tuttua.
TIRA Codersin koodareiden lajittelu oli aluksi hieman haastavaa, kun piti miettiä miten nimien lajittelu tehdään käytännössä.
Päädyin käyttämään lopulta `String.charAt(i)` -metodia, jossa vertaillaan nimiä merkki merkiltä, aloittaen sukunimestä. Jos sukunimet olivat samat, verrattiin etunimiä keskenään.

* Lisäyslajittelu-algoritmin aikakompleksisuusluokka on `O(n^2)` eli neliöllinen
* reverse -algoritmin aikakompleksisuusluokka on `O(n)` eli lineaarinen, koska sen täytyy käsitellä jokainen elementti listassa tai tietorakenteessa, ja mahdollisesti vaihtaa niiden paikkaa keskenään.
* Valmiiksi nousevan järjestyksen omaava taulukko kannattaa **kääntää** (reverse), koska olioita/merkkijonoja ei tarvitse vertailla keskenään. Tarvitsee tehdä vain väliaikaisia muuttujia ja sijoitusoperaatioita.

## 02-TASK

Opin tehtävän myötä Predicate-luokan toiminnallisuudesta ja miten sitä voi käyttää hakufunktioissa apuna.
Myös se, miten Comparator ja Comparable -rajapinnat eroavat toisistaan, tuli tutuksi.
Aluksi oli vaikeaa hahmottaa, miten koodareiden koko nimen ja lempinimen saa palautettua CoderFullNameComparator ja CoderNameComparator -luokista.
Loppupeleissä, toiminnallisuus olikin melko simppeli ja sen avulla koodin rakenteeseen saa joustavuutta.
Lineaaristen hakufunktioiden get(), indexOf(), find() ja findIndex() toteutus oli melko suoraviivaista,
kun toteutettiin yksinkertainen for-silmukka ja käytettiin valmiita rajapintoja parametreille.

Täyttöajan kasvu suhteessa n:n kasvuun:

<img src="images/02_task_fillFunction.png" alt="Täyttöaika" width="500"/>

Hakuaikojen kasvu suhteessa n:n kasvuun:

<img src="images/02_task_searchFunction.png" alt="Hakuaika" width="500"/>

`SimpleContainer.add` -metodissa taulukko täytetään sen kapasiteettiin asti koodareiden sisältämillä tiedoilla, elementti kerrallaan. Jos taulukon elementti indeksissä n on null -> `array[index] == null`, niin se jätetään huomiotta. Taulukko täytetään uusilla elementeillä siihen asti, että taulukon elementit vastaavat parametrina annettuja elementtejä (`array[index].equals(element)`).

Taulukko reallokoidaan, jos `count` ylittää taulukon pituuden `array.length`.
Lopuksi, kun taulukkoon on lisätty uusia elementtejä, se ei ole enää järjestetty ja asetetaan `sorted = false`.

Kuvaajista nähdään, että taulukon **täyttöajat** suhteessa n:ään ovat tasaisempia, kuin **hakuajat** suhteessa n:ään.
Tämä johtuu siitä, että lineaarinen hakualgoritmi käy joissain tapauksissa koko taulukon läpi etsiäkseen tietyn elementin (esim. lopusta). Lisäysalgoritmi `add` puolestaan tarkistaa, esiintyykö lisättävä elementti jo entuudestaan taulukossa ja palautuu kutsujalle tarvittaessa.
Molemmissa tapauksissa suoritusaika taulukon koon funktiona noudattaa likimain aikakompleksisuusluokkaa `O(n^2)`.
Suoritusaika kasvaa siis lineaarisesti n:n toiseen potenssiin nähden. Kun otosmäärä tuplaantuu, suoritusaika **nelinkertaistuu!**

**TIRA Coders:**

LogView 10 000 koodarin lajittelusta Full name ja Coder name perusteella:

<img src="images/02_task_TiraCoders_logView.jpg" alt="Hakuaika" width="400"/>

Koodareiden nimien lajittelu n:n suhteen

| n	| Full name (ms) | Coder name (ms) |
|------|-------|--------|
| 1000	 | 20 | 3	|
| 5000	 | 500 | 150	|
| 10000 |	2500	| 700	|

Suoritusaikaan vaikuttaa mm. se, että samanlaisia lempinimiä on useammalla koodarilla, kun taas suku- ja etunimissä on enemmän eroavaisuuksia. Merkkejä täytyy siis käsitellä enemmän, kun tehdään lisäyslajittelua (insertion sort) koodareiden koko nimien perusteella.

Valmiiksi lajitellun taulukon kääntäminen päinvastaiseksi onnistuu nopeiten **reverse-algoritmin** avulla, koska olioita/merkkijonoja ei tarvitse vertailla keskenään. Reversessä vaihdetaan pelkästään kahden elementin paikkaa keskenään taulukossa.

* Toteutettuja hakualgoritmeja kutsutaan lineaarisiksi, koska niiden suoritusaika kasvaa suhteessa n:n kokoon nähden.
* Täyttö- ja hakualgoritmit vastaavat likimain aikakompleksisuusluokkaa `O(n^2)`, sillä lajittelun kesto kasvaa eksponentiaalisesti n:n koon kasvaessa -> 2^2 = 4, 10^2 = 100 jne.

## 03-TASK

## 04-TASK

## 05-TASK

## 06-TASK

## 07-TASK

## 08-TASK

## 09-TASK