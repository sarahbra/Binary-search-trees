# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Beskrivelse av oppgaveløsning (4-8 linjer/setninger per oppgave)

Jeg har brukt git til å dokumentere arbeidet mitt. Jeg har 40 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

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
deretter p.høyre til dette ikke går lenger (p.høyre == null) og til slutt utføres oppgaven for noden som blir neste i postorden.

* Oppgave 5: Kopiert Programkode 5.1.6 d) i metoden serialized og endret metoden slik at den tar i bruk en hjelpequeue i stedet for en kø. Metoden starter med å opprette en Deque som rotnoden legges inn i og en 
ArrayList som skal ta vare på og returnere verdiene i søketreet i nivåorden.
Deretter går vi inn i en while-løkke som først fjerner en node fra køen (i første iterasjon blir dette rot), legger nodens verdi inn i arrayLista og legger først nodens venstre barn (hvis den finnes) og så nodens høyre barn (hvis den
finnes) i queuen. Dette blir i første iterasjon rotnodens venstre og høyre barn. I andre iterasjon tas rotnodens venstre barn ut av køen, den legges inn i arrayListen, og dens barn legges inn i queuen. I tredje iterasjon tas rotnodens høyre barn
ut, skrives til array, og dens venstre og høyre barn legges inn osv osv til bladnodene er lagt inn og vi for hver iterasjon bare tar ut fra queuen, men ikke legger til, og fortsetter til queuen er tom. Da returneres arrayLista med verdier i nivåorden.
Metoden deserialize() tar som parametere en generisk ArrayList med data og en Comparator. Comparatoren gjør at vi kan benytte klassens konstruktør for opprettelse av tre, og siden arrayet som tas inn ligger i nivåorden, trenger vi 
bare legge inn elementene i arrayet i rekkefølgen de står, hvilket er enklest gjort med en for-each-løkke.

* Oppgave 6: Jeg kodet fjern(T verdi) ved hjelp av to ulike hjelpemetoder: boolean fjern(Node) og Node finnNode(Verdi T), slik at fjern(T verdi) kaller fjern på noden p som returneres av finnNode. Dette fordi jeg synes metoden ble i det lengste 
laget for seg selv og fordi jeg tenkte metodene kunne være mer anvendelige for klassen separert. FinnNodes kode er veldig lik koden for antall(), men leter kun til den finner første instanse av verdi T i treet. Koden for metodene er videreutviklet 
fra en kopi av Programkode 5.2.8d. Fjern(Node)-metoden tar utgangspunkt i de tre tilfellene for nodefjerning: At noden som fjernes er rotnoden, at noden er en bladnode eller kun har ett barn, og at den har to barn. Dersom det siste er tilfelle
må man finne den neste noden r i inorden og kopiere verdien til den noden over i noden p som skal fjernes. Deretter fjernes r ved at r.forelder.venstre settes til r.høyre. I de andre tilfellene kan vi bare fjerne selve noden og flytte (eventuelt) barn til
plassen noden hadde og endre foreldrepeker underveis.
I fjernAlle-metoden lekte jeg først med tanken om å kun iterere over første noden med verdi = T sitt høyre subtre, men treet vil jo endre seg ettersom man fjerner fjerdier i treet utfra reglene for nodefjerning sl.a jeg i stedet bare kaller fjern(Verdi T)-metoden
fram til denne returnerer falskt i stedet samtidig som jeg øker antall for hver gang. Da leter metoden gjennom hele treet for hvert kall, men alle verdiene vil bli fjernet.
Den beste traverseringen for å nullstille et binært søketre er postorden. Dette fordi vi går løs på bladnodene før vi tar foreldernoden. Vi har allerede laget en metode for å traversere det binære søketreet i postorden og kan dermed kjøre inn fjern som oppgaveparameter til
postordenmetoden. Nullstill() kan dermed implementeres enklest med den enkle setningen "if(!tom()) postordenRecursive(this::fjern);". Her vil antall sammenlikninger være fler enn nødvendig ettersom vi alltid sletter bladnoder og mye av koden fra fjern blir overflødig, 
men postordensletting er i seg selv rask, så dette skal ikke ha mye å si for effektiviteten. 

Koden har 4 warnings. Disse er alle bruk av ikke-ASCII (norske) bokstaver i identifiers.


