import java.util.Arrays;

public class ElabNazioniTest {
    public static void main(String[] args) {
        ElabNazioni e = new ElabNazioni("nazioni.min.xml");

        // Per i test ho avuto un'approccio un po' diverso dal solito,
        // usando la parola chiave `assert`. Per eseguire questi test,
        // bisogna passare l'opzione `-ea` in esecuzione, per esempio
        // `java -ea ElabNazioniTest`

        assert e.ricercaNazione("Berna").equals("Svizzera");

        int[] res = { 4, 5, 7, 2, 3 };
        assert Arrays.equals(e.quanteConfinanti(), res);

        assert e.nazioniContinente("Europa").size() == 4;

        assert e.diChiCitta("Maracaibo").equals("Venezuela");
        assert e.diChiCitta("Napoli").equals("Italia");
        assert e.diChiCitta("AK") == null;

        assert e.quanteNazioni("italiano") == 2;
        assert e.quanteNazioni("spagnolo") == 1;
        assert e.quanteNazioni("francese") == 0;

        System.out.println("Test passati");
    }
}
