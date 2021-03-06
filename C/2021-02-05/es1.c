#include <stdio.h>

float media1(int arr[10]) {
    if (arr[9] == 0) return 0.0;

    int somma = 0;
    int n = 0;

    for (int i = 0; i < 10; i++) {
        // Se due numeri hanno segno concorde, allora la loro moltiplicazione
        // avrà risultato positivo. 
        if (arr[i] * arr[9] > 0) {
            printf("Numero concorde: %d\n", arr[i]);
            somma += arr[i];
            n += 1;
        }
    }

    return ((float) somma) / n;
}

int main() {
    int arr[10];

    for (int i = 0; i < 10; i++) {
        printf("Inserire il numero %d: ", i);
        scanf("%d", &arr[i]);
    }

    printf("La media di tutti i numeri concordi a %d e' %f\n", arr[9], media1(arr));
}
