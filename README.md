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

* Oppgave 1: Løste oppgaven ved å kopiere kompendiets Programkode 5.3a og oppdatere innlegging med foreldrepeker inkludert. Denne starter med p = rotnode og flytter p-peker nedover i søketreets 
hhv venstre eller høyre subtre etter hvorvidt innleggelsesnodens verdi er mindre eller større enn p's verdi fram til p blir 0, dvs vi har en ledig nodeplass til noden som skal legges inn.
Samtidig tar vi vare på p's forrige verdi i variabelen q som når løkken er ferdig inneholder den nye nodens forelder. Sjekker til slutt om p's forelder er null, dvs at p blir ny rot, 
eller om innleggelsesnodens verdi T er mindre eller større enn forelderens verdi. Hvis den er mindre blir innleggelsesnoden forelderens venstre barn, hvis større blir den forelderens høyre barn.

* Oppgave 2: Denne oppgaven ble løst ved å hente ut noden p fra søketreets rot og traversere ned ps høyre eller venstre subtre alt ettersom parameterverdien T var større, det samme som, eller mindre
enn ps verdi. Hvis den er lik som ps verdi øker antall funnet med 1. Duplikatverdier vil alltid ligge i høyre subtre slik at p blir p.høyre dersom dette skjer. Når vi har kommet til p=null, dvs traversert
siste bladnode, vil antall kopier av parameterverdien T være lik totalt antall duplikater i treet og variabelen antall returneres. 

* Oppgave 3: Kodet først metoden førstePostorden. Denne har jeg kopiert fra Programkode 5.1.7h med noen endringer slik at den ikke kaster unntak for tomt tre (skal håndteres før
metoden kalles). Løkken finner noden lengst til venstre i det binære søketreet, altså går man først så langt venstre man kan, så høyre, helt til man ikke lenger kan gå venstre eller høyre.
Metoden nestePostorden er kodet ved direkte logisk oversettelse av avsnitt 5.1.7's regler for å finne neste i postorden og har ved if-tester tatt for seg spesialtilfellene p er rot,
p er høyre barn, p er venstre enebarn og p er venstre barn med søsken. Dersom det siste er tilfelle kaller vi førstePostorden-metoden for ps høyre søsken der neste i postorden blir første i postorden
for subtreet med p.høyre som rotnode.

* Oppgave 4: Implementerte først den iterative postordentraverseringen. Denne starter i rotnoden og kaller førstePostorden() for denne noden dersom den ikke er null (tomt tre). Videre kalles nestePostorden()
i løkke så lenge p ikke er null, hvilket inntreffer når metoden nestePostorden kommer til rotnoden og p.forelder = null. Oppgaveutførelsen skjer før kallet på neste i postorden for å få med første i postorden
fra if-testen før løkka. Dette gjør også at vi ikke får med null-verdien som returneres når vi utfører det siste kallet på neste i postorden for den siste i postorden, altså rotnoden.

Den rekursive postorden-traverseringen er kodet ved direkte oversettelse av huskeregelen for postordentraversering: venstre, høyre, node. Dette gjør at metoden kaller seg selv med p.venstre så langt det går (p.venstre == null),
deretter p.høyre til dette ikke går lenger (p.høyre == null) og til slutt utføres oppgaven for noden som blir neste i postorden. SE OVER!!!!!