import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXParseException;

public class ElabNazioni {

    private Element radice;

    /**
     * Costruttore completo
     * 
     * @param nomeFile nome del file xml
     */
    public ElabNazioni(String nomeFile) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder domParser = dbf.newDocumentBuilder();
            Document documento = domParser.parse(new File(nomeFile));
            radice = documento.getDocumentElement();
        } // try
        catch (SAXParseException e) {
            System.out.println("Errore di parsing: " + e.getMessage());
            System.exit(1);
        } // catch
        catch (FileNotFoundException e) {
            System.out.println("File " + nomeFile + " non trovato");
            System.exit(1);
        } // catch
        catch (Exception e) {
            e.printStackTrace();
        } // catch
    }// ElabNazioni

    /**
     * Ricerca la nazione che ha la capitale specificata, ritornando la prima che
     * soddisfa il requisito. Se nessuna nazione viene trovata, ritorna null
     * 
     * @param nomeCapitale nome della capitale
     * @return nome della nazione o `null`
     */
    public String ricercaNazione(String nomeCapitale) {
        for (int i = 0; i < radice.getChildNodes().getLength(); i++) {
            Node nazione = radice.getChildNodes().item(i);
            if (nazione.getFirstChild().getNextSibling().getTextContent().equals(nomeCapitale))
                return nazione.getFirstChild().getTextContent();
        }

        return null;
    }

    /**
     * Ritorna un'array contenente il numero di nazioni confinanti per ogni nazione.
     * Ogni elemento dell'array è una nazionea, e il suo valore è il numero di
     * confinanti della nazione stessa.
     * 
     * @return l'array di confinanti
     */
    public int[] quanteConfinanti() {
        int[] arr = new int[radice.getChildNodes().getLength()];

        for (int i = 0; i < radice.getChildNodes().getLength(); i++) {
            Node nazione = radice.getChildNodes().item(i);
            Node confinanti = nazione.getFirstChild().getNextSibling().getNextSibling();

            if (confinanti.getNodeName().equals("confinanti")) {
                arr[i] = confinanti.getChildNodes().getLength();
            }
        }

        return arr;
    }

    /**
     * Ritorna una lista di nazioni appartenenti al continente specificato.
     * 
     * @param cont il continente da controllare
     * @return la lista delle nazioni
     */
    public LinkedList<String> nazioniContinente(String cont) {
        LinkedList<String> res = new LinkedList<String>();

        for (int i = 0; i < radice.getChildNodes().getLength(); i++) {
            Node nazione = radice.getChildNodes().item(i);
            Node continente = nazione.getLastChild();

            if (continente.getTextContent().equals(cont)) {
                res.add(nazione.getFirstChild().getTextContent());
            }
        }

        return res;
    }

    /**
     * Dato un nome di `citta`, ritorna il nome della nazione che la contiene tra le
     * sue città importanti. Se la città non viene trovata, ritorna `null`
     * 
     * @param citta la citta da cercare
     * @return la nazione trovata
     */
    public String diChiCitta(String citta) {
        for (int i = 0; i < radice.getChildNodes().getLength(); i++) {
            Node nazione = radice.getChildNodes().item(i);
            Node cittaImportanti = nazione.getLastChild().getPreviousSibling();

            if (!cittaImportanti.getNodeName().equals("cittaImportanti"))
                continue;

            for (int j = 0; j < cittaImportanti.getChildNodes().getLength(); j++) {
                Node cittaNodo = cittaImportanti.getChildNodes().item(j);

                if (cittaNodo.getTextContent().equals(citta)) {
                    return nazione.getFirstChild().getTextContent();
                }
            }
        }

        return null;
    }

    /**
     * Ritorna la quantità di nazioni in cui la prima
     * 
     * @param linguaP
     * @return
     */
    public int quanteNazioni(String linguaP) {
        int res = 0;

        for (int i = 0; i < radice.getChildNodes().getLength(); i++) {
            Node nazione = radice.getChildNodes().item(i);
            Node lingua = nazione.getFirstChild().getAttributes().getNamedItem("lingua");

            if (lingua.getTextContent().equals(linguaP))
                res++;
        }

        return res;
    }
}// ElabNazioni
