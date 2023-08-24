# TIRA-2023 Buuttaus

Tässä ohjeessa neuvotaan miten:

1. luot itsellesi tilin GitHub -palveluun ja
1. miten forkkaat (importtaat) ja kloonaat kurssin projektin omaan yksityiseen GitHub -repositoryysi ja
1. saat sen omalle koneellesi työskentelyä varten.

Ohjeessa oletetaan että olet jo asentanut [kurssin työkalut](TOOLS.markdown) koneellesi.

Työkalujen **asennus- ja käyttöönottodemon** näet Moodlessa olevan videolinkin kautta.

> Forkattava repository löytyy GitLabistä. Voit importata sen GitHubiin alla olevien ohjeiden mukaisesti. Jos sinulla sattuu olemaan GitLab -tili, voit forkata repositoryn vaihtoehtoisesti suoraan sinne.

## GitHub 

Tarvitset **tilin** [GitHubiin](https://github.com). Kaikki harjoitus- ja harjoitustyömateriaali toimitetaan tätä kautta ja palautat myös omat suorituksesi opettajille arvioitavaksi tätä kautta. 

> Voit käyttää myös vaikkapa GitLab:iä, pääasia on että opettajilla on pääsy *privaattiin*, ei-julkiseen repositoryysi kyseisessä palvelussa. Käytännössä tämä tarkoittaa että opettajilla pitää olla tunnus kyseiseen palveluun, eikä palvelu ole maksullinen. 

Ensimmäisenä askeleena, *importtaa* opettajien osoittama projekti GitHubiin kuten demovideossa (Moodle) näytetään ja alla olevassa askeleittain etenevässä ohjeessa neuvotaan.

Ohjeita git:n käytöstä löydät demovideoiden lisäksi muistakin kurssin ohjeista. Yksinkertainen lista git:n komennoista on mukana tässä repossa ohjeessa [GIT-CHEAT-SHEET.markdown](GIT-CHEAT-SHEET.markdown).

Kurssilla neuvotaan myös miten **ilmoitat opettajille forkkaamasi repositoryn SSH URL:n**. Toimita reposi osoite ohjeistetulla tavalla opettajille. 

> Ilman tätä URLia opettajat eivät tiedä mistä työsi löytyy eivätkä voi hakea työtäsi arviointia varten.

## Miten pystytät työympäristösi

Ohjeissa oletetaan että luet tätä ohjetta opettajien repositoryn webbisivulta.

Ensimmäinen askel on importata kurssin repo GitHubiin ja kloonata se omalle koneellesi. Tämä on tehtävä *vain kerran*, olettaen että työskentelet vain yhdellä tietokoneella.

> Jos teet töitä useammalla koneella, kysy lisäohjeita opettajilta! Varsinkin jos sinulla ei ole kokemusta git:n käytöstä useammalla koneella.

Kun olet tehnyt alla olevat askeleet, tietokoneellasi pitäisi olla klooni omassa etärepossa olevasta forkistasi. Paljon uusia sanoja, ehkä, mutta opit tämän nopeasti.

1. Luo itsellesi omalla koneellasi **ssh -avain**  jonka viet GitHubiin demovideolla näkyviä ohjeita noudattaen.
2. Kun olet kirjautuneena GitHub -tilillesi, valitse GitHub.com -nettisivun oikeasta yläkulmasta **plus -napin** takaa komento **Import repository**. 
3. Kopioi tekstikenttään (kts. kuva alla) **Your old repository's clone URL** *GitLabissä* olevan opettajien repositoryn URL: `https://gitlab.com/tira-oulu/tira-2023-student`.
4. Varmista että **Owner** -kentässä on oma GitHubin tilisi. 
5. Anna tälle **uudelle repositoryllesi nimi**, esimerkki alla kuvassa.
6. **Muista valita Private** repositoryn näkyvyydeksi! Jos tämä menee väärin, muuta tämä mahdollisimman pikaisesti repositorysi Settings -välilehdeltä privaatiksi.

![Kuva GitHubin import repository](setup-import-repo-to-github.png)

7. Paina nappia **Begin import**. Tässä voi kestää hetken, mutta jossain vaiheessa sinulla on nyt oma uusi privaatti repository josta voit aloittaa työskentelyn.
8. Mene projektisi asetuksiin ja **Lisää kurssin opettajat** oman repositorysi jäseniksi (*Collaborator*). Opettajien pitää päästä repositoryysi kun ohjaamme työtäsi, autamme ongelmissasi ja tarkastamme ja arvostelemme suorituksesi. **Kenelläkään muulla** ei pitäisi olla pääsyä repositoryysi kuin sinulla ja opettajilla. Opettajien GitHub -tunnukset on listattu Moodlessa Ohjelmointitehtävät -välilehdellä.
9. Omalla tietokoneellasi, **avaa komentorivi-ikkuna** (terminal window).
10. **Mene hakemistoon** koneellasi jonne haluat **paikallisen kloonin** omasta etärepositorystäsi. Valitse hakemistopolku jossa **ei ole välilyöntejä tai mitään erikoismerkkejä kuten äöü&*! ja niin edelleen**. Useat ohjelmistokehitystyökalut sekoavat jos ohjelmointiin liittyviä tiedostoja on paikoissa joissa on näitä erikoismerkkejä. Kaikki mitä kurssilla teet, sijoittuu tähän hakemistoon. Älä vahingossakaan sotke tätä hakemistoa poistamalla tai siirtämällä sieltä/sinne mitään ylimääräistä.
11. **Kloonaa** oma privaatti forkkisi GitHubistä tähän työhakemistoon käyttämällä ssh -URLia omaan GitHub:n etärepositoryysi: `git clone <ssh-url-to-your-remote-private-forked-repository>`. Jos teit ssh-avaimen siten että se on suojattu salasanalla, tässä vaiheessa sinulta kysytään tätä salasanaa. Huomaa että tämä ei ole salasana GitHubiin, tietokoneeseesi tai muualle, se on salasana vain tuon ssh -avaimen käyttöön. Laita tuohon väkästen väliin vain se GitHub:sta saamasi ssh -muotoinen URL ilman noita väkäsiä "< >".
12. Tämän jälkeen sinulla pitäisi olla tietokoneellasi **paikallinen repo** (local repository), kopio GitHub:ssa olevasta privaatista forkistasi. Harjoitusten edetessä käytät git:iä päivittämään etärepostoryä GitHubissa joten se pysyy ajan tasalla tekemisistäsi. Samoin opettajat.


Pääteikkunassa, listaa mitä tiedostoja koneellesi latautui (`dir` komento Windowsissa, `ls` Unixeissa/Linuxeissa/macOSssa). Selaile hakemistoja ja tiedostoja ja tutki mitä koneellesi sait.

> Jälleen kerran: *varo* ettet vahingossa poista tai siirrä mitään paikasta toiseen. Jos hävität jotain jota et ole vielä vienyt etärepoosi, eikä paikallisessakaan repossa ole siitä versiota, olet menettänyt työsi. Älä missään nimessä hävitä kloonisi `.git` -alihakemistoa koneeltasi, se sisältää kaikki paikalliset versiot ja tiedot etärepositorystä. Jos täältä jotain häviää, joudut luomaan yhteyden etärepoon käsin joka voi olla vaikeaa. Jos joudut jostain syystä siirtämään paikallisen hakemiston toiseen paikkaan, varmista että myös `.git` -hakemisto siirtyy mukana!

Muista myös että git on versionhallintajärjestelmä. Jos vahingossa vaikkapa poistat jonkun lähdekooditiedoston, ja ehdit päivittää etärepositorynkin, voit silti saada sen poistetun tiedoston talteen **vanhemmasta versiosta** lähdekoodiasi. Jos saat asiat solmuun, **kysy apua opettajilta**.

Nyt koneellasi on siis kopio kaikesta mitä kurssilla tehdään. Noudata ohjeita kuhunkin harjoitukseen liittyen kun etenet harjoituksesta toiseen. Kysy tarvittaessa lisäohjeita opettajilta.

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.