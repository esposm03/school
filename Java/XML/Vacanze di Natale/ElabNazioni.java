import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.ArrayList;

public class ElabNazioni {

    enum Ripetente {
        No,
        UnaVolta,
        DueVolte,
    }

    static class Studente {
        String nome = "";
        String cognome = "";

        int aaaa;
        int mm;
        int gg;

        String linguaMadre;
        ArrayList<String> altreLingue = new ArrayList<String>();
        Ripetente ripetente;

        public String toString() {
            return nome + " " + cognome + " " + aaaa + "-" + mm + "-" + gg + ", conosce " + altreLingue;
        }
    }

    public static Studente parseStudente(Node radice) {
        Studente stud = new Studente();
        NamedNodeMap attributes = radice.getAttributes();

        // Ripetente
        if (attributes.getNamedItem("ripetente") == null) stud.ripetente = Ripetente.No;
        else if (attributes.getNamedItem("ripetente").getTextContent().equals("Una volta")) stud.ripetente = Ripetente.UnaVolta;
        else stud.ripetente = Ripetente.DueVolte;

        // Lingua madre e altre lingue
        stud.linguaMadre = attributes.getNamedItem("linguaNatia").getTextContent();
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.item(i).getNodeName().startsWith("linguaConosciuta")) {
                stud.altreLingue.add(attributes.item(i).getTextContent());
            }
        }

        // Nodi figli
        for (int i = 0; i < radice.getChildNodes().getLength(); i++) {
            Node nodo = radice.getChildNodes().item(i);

            switch (nodo.getNodeName()) {
                case "nome":
                    stud.nome += " " + nodo.getTextContent();
                    break;
                case "cognome":
                    stud.cognome += " " + nodo.getTextContent();
                    break;
                case "dataNascita":
                    parseData(nodo, stud);
                    break;
                default:
                    System.err.println(nodo.getNodeName() + " non è un figlio di <studente> valido");
                    break;
            }

            stud.nome = stud.nome.trim();
            stud.cognome = stud.cognome.trim();
        }

        return stud;
    }

    public static void parseData(Node radice, Studente stud) {
        for (int i = 0; i < radice.getChildNodes().getLength(); i++) {
            Node nodo = radice.getChildNodes().item(i);

            switch (nodo.getNodeName()) {
                case "gg":
                    stud.gg = Integer.parseInt(nodo.getTextContent());
                    break;
                case "mm":
                    stud.mm = Integer.parseInt(nodo.getTextContent());
                    break;
                case "aaaa":
                    stud.aaaa = Integer.parseInt(nodo.getTextContent());
                    break;
                default:
                    System.err.println(nodo.getNodeName() + " non è un figlio di <dataNascita> valido");
                    break;
            }
        }
    }

    static class Classe {
        String nome;
        ArrayList<String> aule = new ArrayList<String>();
        ArrayList<Studente> studenti = new ArrayList<Studente>();
    }

    public static Classe parseClasse(Node radice) {
        Classe ris = new Classe();

        for (int i = 0; i < radice.getChildNodes().getLength(); i++) {
            Node nodo = radice.getChildNodes().item(i);

            switch (nodo.getNodeName()) {
                case "nomeC":
                    ris.nome = nodo.getTextContent();
                    break;
                case "aula":
                    ris.aule.add(nodo.getTextContent());
                    break;
                case "studenti":
                    for (int j = 0; j < nodo.getChildNodes().getLength(); j++) {
                        ris.studenti.add(parseStudente(nodo.getChildNodes().item(j)));
                    }
                    break;
                default:
                    System.err.println(nodo.getNodeName() + " non è un figlio di <classe> valido");
                    System.exit(1);
                    break;
            }
        }

        return ris;
    }

    ArrayList<Classe> classi = new ArrayList<Classe>();

    public static void main(String[] args) throws Exception {
        // Ho inserito dei "test" qui, nel main.
        // Per abilitarli, bisogna passare `-ea` (enable assertions)
        // all'interprete, per esempio: `java -ea ElabNazioni.java`

        ElabNazioni e = new ElabNazioni();

        assert e.qualeAula("4IF").equals("[202]");
        assert e.qualeAula("3IG").equals("[102, 203]");

        assert e.elenco().length == 6;

        assert e.elenco("3IG").length == 3;

        assert e.qualeClasse("202").equals("4IF");
        assert e.qualeClasse("102").equals("3IG");

        assert e.natiNel(2003).size() == 5;
        assert e.natiNel(2002).size() == 1;

        assert e.nascitaStudente("Pazzan").equals("14/4/2002");
        assert e.nascitaStudente("Lacramioara").equals("19/7/2003");

        assert e.numStudentiL("italiano").size() == 3;
        assert e.numStudentiL("portoghese").size() == 1;

        assert e.numStudentiLNP("italiano").size() == 5;
        assert e.numStudentiLNP("inglese").size() == 3;

        assert e.numRipetenti()[0] == 2;
        assert e.numRipetenti()[1] == 1;

        assert e.numRipetentiIIvolte()[3] == 1;
        assert e.numRipetentiIIvolte()[2] == 1;

        assert e.ripetentiIIvolta().get(0).nominativo.equals("Larisa Lacramioara Dudan");
        assert e.ripetentiIIvolta().get(0).classe.equals("4IF");

        System.out.println("Terminato");
    }

    public ElabNazioni() throws Exception {
        Node radice = DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse("file:///home/samuele/scuola/Java%202020-12-12%20XML/classi.min.xml")
            .getDocumentElement();

        for (int i = 0; i < radice.getChildNodes().getLength(); i++) {
            classi.add(parseClasse(radice.getChildNodes().item(i)));
        }
    }

    String qualeAula(String miaClasse) {
        for (Classe classe : classi) {
            if (classe.nome.equals(miaClasse)) {
                return classe.aule.toString();
            }
        }

        assert false;
        return null;
    }

    String[] elenco() {
        int count = 0;

        for (Classe classe : classi) {
            for (int i = 0; i < classe.studenti.size(); i++) {
                count++;
            }
        }

        int i = 0;
        String[] ris = new String[count];

        for (Classe classe : classi) {
            for (Studente studente : classe.studenti) {
                ris[i] = studente.toString();
            }
        }

        return ris;
    }

    String[] elenco(String miaClasse) {
        int count = 0;

        for (Classe classe : classi) {
            if (classe.nome.equals(miaClasse)) {
                for (int i = 0; i < classe.studenti.size(); i++) {
                    count++;
                }
            }
        }

        int i = 0;
        String[] ris = new String[count];

        for (Classe classe : classi) {
            if (classe.nome.equals(miaClasse)) {
                for (Studente studente : classe.studenti) {
                    ris[i] = studente.toString();
                }
            }
        }

        return ris;
    }

    String qualeClasse(String miaAula) {
        for (Classe classe : classi) {
            if (classe.aule.contains(miaAula)) return classe.nome;
        }
        return null;
    }

    ArrayList<String> natiNel(int annoN) {
        ArrayList<String> res = new ArrayList<String>();

        for (Classe classe : classi) {
            for (Studente studente : classe.studenti) {
                if (studente.aaaa == annoN) res.add(studente.toString());
            }
        }

        return res;
    }

    String nascitaStudente(String cognomeS) {
        String res = "";

        for (Classe classe : classi) {
            for (Studente studente : classe.studenti) {
                if (studente.cognome.contains(cognomeS)) {
                    res += " " + studente.gg + "/" + studente.mm + "/" + studente.aaaa;
                }
            }
        }

        return res.trim();
    }

    ArrayList<String> numStudentiL(String linguaN) {
        ArrayList<String> res = new ArrayList<String>();

        for (Classe classe : classi) {
            for (Studente studente : classe.studenti) {
                if (studente.linguaMadre.equals(linguaN)) {
                    res.add(studente.toString());
                }
            }
        }

        return res;
    }

    ArrayList<String> numStudentiLNP(String linguaN) {
        ArrayList<String> res = new ArrayList<String>();

        for (Classe classe : classi) {
            for (Studente studente : classe.studenti) {
                if (studente.linguaMadre.equals(linguaN) || studente.altreLingue.contains(linguaN)) {
                    res.add(studente.toString());
                }
            }
        }

        return res;
    }

    int[] numRipetenti() {
        int[] res = new int[classi.size()];

        for (int i = 0; i < classi.size(); i++) {
            res[i] = 0;
            for (Studente studente : classi.get(i).studenti) {
                if (studente.ripetente != Ripetente.No) res[i] += 1;
            }
        }

        return res;
    }

    int[] numRipetentiIIvolte() {
        int[] res = new int[5];

        for (Classe classe : classi) {
            for (Studente studente : classe.studenti) {
                if (studente.ripetente == Ripetente.DueVolte) {
                    int index = Integer.parseInt(classe.nome.substring(0, 1));
                    res[index-1] += 1;
                }
            }
        }

        return res;
    }

    static class StudenteClasse {
        String classe;
        String nominativo;

        public StudenteClasse(String classe, String nominativo) {
            this.classe = classe;
            this.nominativo = nominativo;
        }

        public String toString() {
            return nominativo + ", classe " + classe;
        }

        public boolean equals(Object other) {
            if (other instanceof StudenteClasse) {
                return ((StudenteClasse) other).classe.equals(this.classe) &&
                    ((StudenteClasse) other).nominativo.equals(this.nominativo);
            }
            return false;
        }
    }

    ArrayList<StudenteClasse> ripetentiIIvolta() {
        ArrayList<StudenteClasse> res = new ArrayList<StudenteClasse>();

        for (Classe classe : classi) {
            for (Studente studente : classe.studenti) {
                if (studente.ripetente == Ripetente.DueVolte)
                    res.add(new StudenteClasse(classe.nome, studente.nome + " " + studente.cognome));
            }
        }

        return res;
    }

}
