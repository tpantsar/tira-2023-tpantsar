# Lunttilappu git -komentoihin

Sinun tueksesi. Lisää git -dokumentaatiota ja tutoriaaleja löydät [täältä](https://git-scm.com/doc) ja Unix/Linux/macOS -koneilla komentoriviltä `man git` ja vaikkapa commit -komennosta ohjeita saat `man git-commit`. Tiedätkö muuten kuka on git:n alkuperäinen kehittäjä?

| Komento            | Esimerkki                                                | Kuvaus                                     |
|--------------------|----------------------------------------------------------|--------------------------------------------|
| git config *1)*    | git config --global user.name "John Doe"                 | Kertoo git:lle kuka tekee töitä koneella.  |
|                    | git config --global user.email johndoe@example.com       | Mikä on sinun sähköpostisi.                |
|            *2)*    | git config --global core.editor *some editor*            | Editori jolla kommentoit tekemisiäsi.      |
| git clone          | git clone git@github.com:username/some-repo-here.git     | Etärepon koodaus omalle koneellesi.        |
| git status         | git status                                               | Muuttuneet tiedostot koneellasi.           |
| git diff           | git diff                                                 | Muutokset joita on tehty koneellasi.       |
| git log            | git log, git log SomeFile.java                           | Näe commitit ja commit-viestit.            |
| git add *3)*       | git add SomeFile.java                                    | Lisää tiedostoja seuraavaan commitiin.     |
| git commit *4)*    | git commit -am"What was done here."                      | Vie muutokset paikalliseen repoon.         |
| git push           | git push                                                 | Vie muutokset etärepositoryysi.            |
| git pull *5)*      | git pull                                                 | Hakee muutokset etärepositorystä.          |

Huomautukset:

1. Nimi ja sähköpostiosoite liitetään commit -tietoihin; kuka ne teki. Nämä näkyvät myös etärepositoryssä. Jos haluat käyttää kurssin töissä eri sähköpostiosoitetta kuin muuten omalla koneellasi toisten repositoryjen kanssa, anna komento  `git config` *ilman* `--global` optiota *kurssin repositoryn hakemistossa*. Näin sähköpostiosoite/nimi liittyy vain tähän repositoryyn. Sähköpostiosoite on sama kuin etärepon käyttäjätunnuksella.
2. Jos unohdat antaa `-m` option `git commit` komennon kanssa, git avaa oletuseditorin johon kirjoitat commit -viestin. Oletusarvona voi olla joku eksoottinen editori jota et ehkä osaa käyttää (kuten vim). Kannattaa siis valita tähän joku editori jota osaat tai opettelet käyttämään. Katso ohjeita täältä: [git-scm.com](https://git-scm.com/book/en/v2/Getting-Started-First-Time-Git-Setup).
3. Huomaa että kun luot uusia tiedostoja, ne pitää erikseen lisätä versionhallintaan `git add <filename>` -komennolla. Komento `git commit -am` *ei* lisää uusia tiedostoja versionhallintaan. Tällöin ne eivät päädy etärepoosi eivätkä opettajille, eikä koodisi siis käänny muilla kuin omalla koneellasi jolla tuo tiedosto *ainoastaan* on.
4. `git commit` *ilman optiota* `-a` vie paikalliseen repoon vain ne tiedostot jotka on aiemmin lisätty commitiin komennolla `git add <file>`. Eli helpoin tapa lisätä muuttuneet, jo versionhallinnassa olevien tiedostot commitiin on käyttää komentoa `git commit -am`. 
5. Jos käytät vain yhtä tietokonetta kurssilla, etkä editoi tiedostoja webbiselaimessa etärepoon suoraan, sinun ei pitäisi joutua hakemaan muuutoksia etärepostasi `git pull` -komennolla. Jos taas *teet töitä useammalla koneella*, sinun pitää käyttää myös `git pull` -komentoa. Helpointa tämä on kun muistat *aina* 1) kun aloitat työskentelyn koneella, teet *heti ensimmäiseksi komennon* `git pull`. ja 2) *aina kun lopetat* työskentelyn koneella, kommitoit muutokset ja teet `git push`. *Ihan joka kerta*. Jos unohdat tämän, jollain koneella olevat muutokset tai uusi koodi ei ole etärepossa eikä siten siirry toiselle koneellesi. Joudut ehkä ratkomaan vaikeitakin *merge conflicteja* jos muutoksia on siellä sun täällä. Jos näin käy, kysy opettajia apuun.

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.