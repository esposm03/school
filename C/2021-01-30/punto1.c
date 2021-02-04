#include <stdio.h>

int main() {
    float debito;
    float interesse;
    float importo_rata;

    printf("Inserire il debito iniziale: ");
    scanf("%f", &debito);

    printf("Inserire l'interesse: ");
    scanf("%f", &interesse);

    printf("Inserire l'importo delle rate: ");
    scanf("%f", &importo_rata);

    interesse /= 100;
    interesse /= 12;

    if (debito * interesse >= importo_rata) {
        printf("Attenzione, la rata annuale e' troppo bassa per estinguere il debito\n");
        return 1;
    }

    int i = 0;
    while (debito > 0) {
        debito += debito * interesse;
        debito -= importo_rata;
        i++;
    }

    if (i == 1) {
        printf("Per estinguere il debito e' necessaria una sola rata\n");
    } else {
        printf("Per estinguere il debito sono necessarie %d rate\n", i);
    }
}
