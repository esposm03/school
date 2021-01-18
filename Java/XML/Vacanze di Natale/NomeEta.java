public class NomeEta {
    String nome;
    int anni;

    public NomeEta(String nome, int anni) {
        this.nome = nome;
        this.anni = anni;
    }

    @Override
    public String toString() {
        return "NomeEta [anni=" + anni + ", nome=" + nome + "]";
    }
}
