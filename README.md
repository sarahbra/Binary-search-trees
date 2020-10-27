# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Krav til innlevering

Se oblig-tekst for alle krav, og husk spesielt på følgende:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* git bundle er levert inn
* Hovedklassen ligger i denne path'en i git: src/no/oslomet/cs/algdat/Eksamen/EksamenSBinTre.java
* Ingen debug-utskrifter
* Alle testene i test-programmet kjører og gir null feil (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet


# Beskrivelse av oppgaveløsning (4-8 linjer/setninger per oppgave)

Vi har brukt git til å dokumentere arbeidet vårt. Jeg har 16 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

* Oppgave 0: Sjekket først at løsningene vi allerede har fått utlevert kjører som de skal (dvs at SBinTre's konstruktør, metodene antall(), toStringPostOrder() og inneholder() 
- i den grad dette kan testes, løkkene kjører ikke for tomme trær - gjør som de skal). All utdelt kode fungerte som ventet for referansetypene Character, String, Integer osv.

* Oppgave 1: Løste oppgaven ved å kopiere kompendiets kode 5.3a og oppdatere innlegging med foreldrepeker inkludert. Denne starter med p = rotnode og flytter p-peker nedover i søketreets 
hhv venstre eller høyre subtre etter hvorvidt innleggelsesnodens verdi er mindre eller større enn p's verdi fram til p blir 0, dvs vi har en ledig nodeplass til noden som skal legges inn.
Samtidig tar vi vare på p's forrige verdi i variabelen q som når løkken er ferdig inneholder den nye nodens forelder. Sjekker til slutt om p's forelder er null, dvs at p blir ny rot, 
eller om innleggelsesnodens verdi T er mindre eller større enn forelderens verdi. Hvis den er mindre blir innleggelsesnoden forelderens venstre barn, hvis større blir den forelderens høyre barn.

* Oppgave 2: Denne oppgaven ble løst ved å hente ut noden p fra søketreets rot og traversere ned ps høyre eller venstre subtre alt ettersom parameterverdien T var større, det samme som, eller mindre
enn ps verdi. Hvis den er lik som ps verdi øker antall funnet med 1. Duplikatverdier vil alltid ligge i høyre subtre slik at p blir p.høyre dersom dette skjer. Når vi har kommet til p=null, dvs traversert
siste bladnode, vil antall kopier av parameterverdien T være lik totalt antall duplikater i treet og variabelen antall returneres. 