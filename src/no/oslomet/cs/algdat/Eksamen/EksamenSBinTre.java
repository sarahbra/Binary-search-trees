package no.oslomet.cs.algdat.Eksamen;


import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public final boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q=null;                  // p starter i roten
        int cmp = 0;                             // hjelpevariabel compare

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p et nivå lenger ned i søketreet
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi,q);                   // oppretter en ny node

        // p.forelder er fortsatt lik q, men for logikkens skyld refererer jeg til den som p's forelder.

        if (p.forelder == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) p.forelder.venstre = p;         // venstre barn til forelder
        else p.forelder.høyre = p;                        // høyre barn til forelder

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging
    }


    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    /**
     * Finner antall instanser av T verdi i det binære søketreet. Returnerer 0
     * @param verdi
     * @return
     */
    public int antall(T verdi) {
        if (verdi == null) return 0;            // et binært søketre inneholder ikke nullverdier!

        Node<T> p = rot;
        int antall = 0;                         // tellevariabel for antall instanser av T verdi
        int cmp = 0;                            // hjelpevariabel compare

        while(p != null) {
            cmp = comp.compare(verdi,p.verdi);
            if(cmp==0) {                        // funnet. Hvis ps verdi har duplikater vil disse alltid ligge i ps høyre subtre
                antall++;
                p = p.høyre;
            }
            else if(cmp<0) p = p.venstre;       // verdi < p.verdi - må ligge i p's venstre subtre hvis det finnes i søketre
            else p = p.høyre;                   // verdi > p.verdi - må ligge i p's høyre subtre hvis det finnes i søketre
        }
        return antall;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    /**
     * Returnerer neste node i postorden i subtre med node p som rotnode.
     * @param p
     * @param <T>
     * @return node neste i postorden i subtre til p
     */

    // Kopiert fra forelesningsnotater Programkode 5.1.7 h) med visse endringer ettersom metoden ikke skal
    // kaste unntak for tomt tre. Dette håndteres lenger opp i systemet (før metoden kalles).

    private static <T> Node<T> førstePostorden(Node<T> p) {
        while(true) {
            if(p.venstre != null) p = p.venstre;                   // går lengst til venstre
            else if(p.høyre != null) p = p.høyre;                  // høyre for å lete i høyere nivå (lenger ned)
            else return p;                                         // noden lengst til venstre i ps subtre
        }
    }

    /**
     * Returner neste node i postorden til node p der p er hvor som helst i søketreet.
     * @param p
     * @param <T>
     * @return node neste i postorden til node p
     */
    private static <T> Node<T> nestePostorden(Node<T> p) {
        if(p.forelder == null) return null;                         // rotnoden, siste i postorden
        else if(p.forelder.høyre == p) return p.forelder;           // neste i postorden etter høyre barn er forelder
        else {                                                      // p = venstre barn
            if(p.forelder.høyre == null) return p.forelder;         // forelder er neste i postorden når p er enebarn
            else return førstePostorden(p.forelder.høyre);          // ellers første i postorden i treet med p.forelder.høyre som subtre
        }
    }

    /**
     * Traverserer binært søketre i postorden og utfører parameteroppgave på nodenes verdier
     * @param oppgave
     */

    public void postorden(Oppgave<? super T> oppgave) {
        // starter i rotnoden og finner første i postorden i binærtreet
        Node<T> p = rot;
        if (p != null) {
            p = førstePostorden(p);
        }

        // utfører oppgave for hver neste i postorden til nestePostorden() returnerer null

        while(p!=null) {
            oppgave.utførOppgave(p.verdi);
            p = nestePostorden(p);
        }
    }

    /**
     * Traverserer hele binære søketreet rekursivt og utfører oppgave på alle nodenes verdier.
     * @param oppgave
     */

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    /**
     * Utfører oppgave på binærtreet i postorden rekursivt fra parameternoden p.
     * @param p
     * @param oppgave
     */
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        // Huskeregel sier venstre, høyre, node og oppgavekallet utføres etter de rekursive kallene
        if(p.venstre!=null) postordenRecursive(p.venstre, oppgave);
        if(p.høyre!=null) postordenRecursive(p.høyre,oppgave);

        oppgave.utførOppgave(p.verdi);
    }

    /**
     * Returnerer arraylist av elementene fra det binære søketreet i nivåorden.
     * @return ArrayList<T> nivåorden
     */

    public ArrayList<T> serialize() {
        ArrayList<T> nivåorden = new ArrayList<>();
        Queue<Node<T>> kø = new ArrayDeque<>();
        kø.add(rot);                            // legger inn roten

        while (!kø.isEmpty()) {                  // slutter nok køen er tom
            Node<T> p = kø.remove();             // tar ut fra køen
            nivåorden.add(p.verdi);              // legger ps verdi i ArrayList

            // legger til verdier i køen i nivåorden
            if (p.venstre != null) kø.add(p.venstre);
            if (p.høyre != null) kø.add(p.høyre);
        }
        return nivåorden;
    }

    /**
     * Oppretter og returnerer binært søketre fra ArrayList
     * @param data ArrayList med verdier i nivåorden
     * @param c Comparator
     * @param <K>
     * @return EksamenSBinTre tre
     */
    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        EksamenSBinTre<K> tre = new EksamenSBinTre(c);        // oppretter nytt tre

        for (K elem : data) tre.leggInn(elem);                // legger inn verdier etter arrayrekkefølge (nivåorden)

        return tre;                                           // returnerer tre
    }

} // ObligSBinTre
