// Il pattern che ho trovato è: ogni armadietto aperto ha una posizione
// che è un quadrato perfetto. Controllo questo nella funzione `num_aperti`

#include <stdio.h>
#include <math.h>

#define bool int
#define true 1
#define false 0

// Calcola il numero di armadietti aperti.
int num_aperti(const int arr[], int l) {
    int count = 0;
    for (int i = 0; i < l; i++) {
        if (arr[i]) count++;
    }
    return count;
}

// Ritorna `true` (corrispondente a 1) se gli armadietti che sono
// aperti sono tutti a una posizione che è un quadrato perfetto,
// `false` altrimenti.
bool quad_perfetti(const int arr[], int l) {
    for (int i = 0; i < l; i++) {
        // Se arr[i] è vera, e la sua radice quadrata è un
        // numero intero (quindi arr[i] è quadrato perfetto)
        if (arr[i] && floor(sqrt(arr[i])) != sqrt(arr[i])) return false;
    }
    return true;
}

// Stampa l'array di armadietti
void stampa(const int arr[], int l) {
    printf("[");
    for (int i = 0; i < l-1; i++) {
        printf("%d, ", arr[i]);
    }
    printf("%d]\n", arr[l-1]);
}

int main() {
    int n;
    printf("Inserire il numero di studenti e armadi: ");
    scanf("%d", &n);

    bool arr[n];

    for (int i = 0; i <= n; i++) {
        arr[i] = false;
    }

    for (int i = 1; i <= n; i++) {
        for (int pos = i; pos <= n; pos += i) {
            arr[pos-1] = !arr[pos-1];
        }
    }

    printf("Totale: %d\n", num_aperti(arr, n));
    printf("Tutti quadrati perfetti: %s\n", quad_perfetti(arr, n) ? "true" : "false");
}
