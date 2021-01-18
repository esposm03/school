import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Famiglia {
    ArrayList<Persona> persone = new ArrayList<Persona>();

    public Famiglia(Element radice) {
        for (int i = 0; i < radice.getChildNodes().getLength(); i++) {
            persone.add(parsePersona(radice.getChildNodes().item(i)));
        }
    }

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        Element persona = DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse("file:///home/samuele/scuola/Java%202020-12-12%20XML/famiglia.min.xml")
            .getDocumentElement();

        // Ho inserito dei "test" qui, nel main.
        // Per abilitarli, bisogna passare `-ea` (enable assertions)
        // all'interprete, per esempio: `java -ea Famiglia.java`

        Famiglia fm = new Famiglia(persona);

        assert fm.ancoraVivo("Federico Esposito");

        assert fm.natoQualcuno(2003);
        assert !fm.natoQualcuno(2004);

        assert fm.numMorti(2021) == 2;
        assert fm.numMorti(2020) == 0;

        assert fm.consorte("Giovanni Esposito").equals("Stefania Toniolo");

        assert fm.genitori("Samuele Esposito").equals("Madre: 'Stefania Toniolo', padre: 'Giovanni Esposito'");

        assert fm.stranieri().length == 2;
        assert fm.stranieri()[0].equals("Samuele Esposito");

        assert fm.nazionalitaStranieri().length == 2;
        assert fm.nazionalitaStranieri()[0].equals("germania");

        assert fm.morti().size() == 2;
        assert fm.morti().get(0).nome.equals("Samuele Esposito");
        assert fm.morti().get(0).anni == 17;

        assert fm.persone().size() == 5;

        System.out.println("Finito");
    }

    static class Persona {
        String nome;
        String consorte;
        String nazionalita;

        int numFigli = 0;
        String madre;
        String padre;

        int annoNascita;
        int annoMorte = -1;

        @Override
        public String toString() {
            return "\nPersona [" + nome + " (" + annoNascita + "-" + annoMorte + "), consorte: " + consorte + ", figli: " + numFigli + "]";
        }

        public NomeEta toNomeEta() {
            return new NomeEta(nome, 2020-annoNascita);
        }
    }

    private static Persona parsePersona(Node radice) {
        Persona res = new Persona();

        for (int i = 0; i < radice.getChildNodes().getLength(); i++) {
            Node nodo = radice.getChildNodes().item(i);

            switch (nodo.getNodeName()) {
                case "nome":
                    res.nome = nodo.getTextContent();
                    break;
                case "consorte":
                    res.consorte = nodo.getTextContent();
                    break;
                case "annoNascita":
                    res.annoNascita = Integer.parseInt(nodo.getTextContent());
                    break;
                case "annoMorte":
                    res.annoMorte = Integer.parseInt(nodo.getTextContent());
                    break;
                case "figli":
                    // TODO
                    break;
                case "madre":
                    res.madre = nodo.getTextContent();
                    break;
                case "padre":
                    res.padre = nodo.getTextContent();
                    break;
                default:
                    System.err.println(nodo.getNodeName() + " non è un figlio di <persona> valido");
                    System.exit(1);
                    break;
            }
        }

        Node a = radice.getAttributes().getNamedItem("nazionalita");
        if (a == null) {
            res.nazionalita = null;
        } else {
            res.nazionalita = a.getTextContent();
        }

        return res;
    }

    /**
     * Restituisce se la persona è ancora viva 
     * @param nome il nome della persona
     * @return null se la persona non esiste, altrimenti true o false se la persona è viva o morta
     */
    public Boolean ancoraVivo(String nome) {
        for (Persona persona : persone) {
            if (persona.nome.equals(nome)) return persona.annoMorte == -1;
        }

        return null;
    }

    /**
     * Ritorna se è nato qualcuno nell'anno specificato
     * @param anno l'anno da controllare
     * @return true se almeno una persona è nata in questo anno, false altrimenti
     */
    public boolean natoQualcuno(int anno) {
        for (Persona persona : persone) {
            if (persona.annoNascita == anno) return true;
        }

        return false;
    }

    /**
     * Ritorna il numero di morti nell'anno specificato
     * @param anno l'anno da controllare
     * @return il numero di morti
     */
    public int numMorti(int anno) {
        int res = 0;

        for (Persona persona : persone) {
            if (persona.annoMorte == anno) res += 1;
        }

        return res;
    }

    /**
     * Ritorna il consorte della persona specificata
     * @param nome il nome della persona
     * @return il nome del consorte, oppure null se la persona non esiste o non ha un consorte
     */
    public String consorte(String nome) {
        for (Persona persona : persone) {
            if (persona.nome.equals(nome)) return persona.consorte;
        }

        return null;
    }

    /**
     * Ritorna i genitori della persona specificata
     * @param nome il nome della persona
     * @return il nome dei genitori, oppure null se la persona non esiste
     */
    public String genitori(String nome) {
        for (Persona persona : persone) {
            if (persona.nome.equals(nome))
                return "Madre: '" + persona.madre + "', padre: '" + persona.padre + "'";
        }

        return null;
    }

    /**
     * Ritorna i nominativi della coppia più prolifica,
     * oppure null se non esiste nessuna coppia
     * @return il nominativo
     */
    public String coppiaPiuProlifica() {
        if (persone.size() == 0) return null;

        Persona max = persone.get(0);

        for (Persona persona : persone) {
            if (persona.numFigli > max.numFigli) max = persona;
        }

        return max.nome + " " + max.consorte;
    }

    /**
     * Ritorna un elenco contenente i nomi di tutte le persone straniere
     * (ovvero, quelle in cui la nazionalità è specificata)
     * 
     * @return l'array di nomi
     */
    public String[] stranieri() {
        int count = 0;

        for (Persona persona : persone) {
            if (persona.nazionalita != null) count++;
        }

        String[] res = new String[count];
        int i = 0;

        for (Persona persona : persone) {
            if (persona.nazionalita != null) {
                res[i] = persona.nome;
                i++;
            }
        }

        return res;
    }

    /**
     * Ritorna un elenco contenente le nazionalità di tutte le persone straniere
     * (ovvero, quelle in cui la nazionalità è specificata)
     * 
     * @return l'array di nazionalità
     */
    public String[] nazionalitaStranieri() {
        int count = 0;

        for (Persona persona : persone) {
            if (persona.nazionalita != null) count++;
        }

        String[] res = new String[count];
        int i = 0;

        for (Persona persona : persone) {
            if (persona.nazionalita != null) {
                res[i] = persona.nazionalita;
                i++;
            }
        }

        return res;
    }

    /**
     * Ritorna una lista di morti, con nome ed età
     * 
     * @return la lista di `NomeEta`
     */
    public ArrayList<NomeEta> morti() {
        ArrayList<NomeEta> res = new ArrayList<NomeEta>();

        for (Persona persona : persone) {
            if (persona.annoMorte != -1) res.add(persona.toNomeEta());
        }

        return res;
    }

    /**
     * Ritorna la lista di tutte le persone della famiglia, con nomi ed età
     * 
     * @return la lista di `NomeEta`
     */
    public ArrayList<NomeEta> persone() {
        ArrayList<NomeEta> res = new ArrayList<NomeEta>();

        for (Persona persona : persone) {
            res.add(persona.toNomeEta());
        }

        return res;
    }
}
