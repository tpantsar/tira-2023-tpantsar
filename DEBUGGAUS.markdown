# Testauksesta, debuggaamisesta ja virheiden etsimisestä

Muista aina **lukea huolella** mitä testit sanovat kun ne epäonnistuvat. 

Testit aina ilmoittavat mitä 

* testi *odotti* tapahtuvan ja 
* mitä *oikeasti tapahtui*. 

Lue nämä viestit huolellisesti ja kysy itseltäsi: 

* **Miksi näin tapahtui?**. 
* Miksi se mitä *odotettiin* tapahtuvan, **ei tapahtunut?**

Tutki myös epäonnistuneiden *testien koodia* ja katso *miten* se kutsuu omaa koodiasi ja missä järjestyksessä. Aina siihen asti jossa asiat menivät pieleen. 

Huomaa, että ongelman *varsinainen syy* ei välttämättä ole siinä kohtaa missä testi epäonnistui. Voi olla että virhe tapahtui *aikaisemmin*, jossain toisessa metodissa, joka sössi asiat. 

Tätä ei vaan huomata ennenkuin pinon kanssa yritetään tehdä jotain muuta. Voi olla esimerkiksi että testi epäonnistuu kutsuttaessa pinon `pop()` -metodia, mutta virhe tehtiin jo aikaisemmin pinon `push()` -metodin kutsun yhteydessä.

Kun luokalla on useita metodeja joita kutsutaan eri järjestyksessä eri paikoista, muuttaen olion tilaa, voi olla että olion tila meni sekaisin paikassa A. Sitten myöhemmin, paikassa B, tämä tilan sekaisin meneminen vasta aiheuttaa ongelmia.

## Debuggaaminen

Mitä siis voi tehdä? Tietysti ensimmäinen asia on se, että varmistat että olet ymmärtänyt mitä luokan pitäisi tehdä, mitä kunkin metodin pitäisi tehdä ja miten kokonaisuutena olion pitäisi toimia. Voit myös debugata sitä miten luokkasi toimii, laittamalla pysäytyspisteitä (**breakpoints**) joko omaan koodiisi tai testikooodiin, tai molempiin. Nämä kannattaa laittaa tietysti paikkoihin jotka suoritetaan *ennen* kuin testi epäonnistuu.

Sitten käynnistä testit *debuggaamalla*. Kun koodin suoritus pysähtyy breakpointiin, *huolellisesti* tutki mikä on olion tila sillä hetkellä. Onko se se mitä sen pitäisi olla? Onko esimerkiksi pinon sisäisessä taulukossa se määrä olioita mitä siellä pitäisi olla? Ovatko ne siellä oikeassa järjestyksessä? Ovatko muut olion jäsenmuuttujat arvoltaan sitä mitä niiden pitäisi tällä hetkellä olla?

Jos esimerkiksi näet että testikoodi laittaa kolme oliota pinoon ennen pysäytyspistettä, ovatko ne kolme oliota pinon taulukossa ja oikeassa järjestyksessä? Muut pinon jäsenmuuttujat ovat yhtä mieltä siitä mikä on pinon tilanne?

Sitten kun tilannekuva on selvä, *hitaasti* askella yksi rivi kerrallaan koodia eteenpäin. Joka askeleen jälkeen mieti huolella: onko se mitä tapahtui sitä mitä *piti* tapahtua ja mitä *mielestäni* koodasin tapahtuvaksi? *Älä oleta* että koodisi toimii oikein vaan tutki *toimiiko se oikeasti oikein*!

Kerää tietoa siitä miten koodi toimii ja koko ajan tarkenna tietoasi siitä missä kohtaa asiat menevät pieleen ja missä kohtaa se pieleen meneminen oikeastaan alkaakaan. Toista tätä prosessia niin kauan että saat selville miksi asiat menevät pieleen.

Debuggauksen aikana pidä koko ajan huomiosi **debuggerin muuttujanäkymässä** jossa näet kaikkien muuttujien arvot. Vie hiiri koodissa olevien muuttujien päälle kohdassa jossa suoritus on menossa, ja tutki sillä tavalla mitä arvoja muuttujissa on.

## Virheiden korjaaminen

Kun löydät ongelman juurisyyn, joka aiheutti testin epäonnistumisen, korjaa virheesi. Ja testaa heti uudelleen auttoiko korjaus. Vai kävikö niin että korjaus synnytti uuden virheen kenties ihan eri paikkaan... :/

Pyri yksinkertaiseen koodiin. Monimutkainen koodi sisältää helposti enemmän virheitä kuin yksinkertainen. Älä ala värkkäilemään kaikenlaisia kokeiluja joka vaan tekee koodista isomman härdellin. 

Ennemmin **pysähdy** miettimään mitä tässä pitikään tehdä ja pyri tekemään tämä mahdollisimman yksinkertaisesti.

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2023.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2023, INTERACT Research Group.