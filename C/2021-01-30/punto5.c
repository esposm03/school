#include <stdio.h>

void stampa(int arr[], int l) {
    printf("[");
    for (int i = 0; i < l-1; i++) {
        printf("%d, ", arr[i]);
    }
    printf("%d]\n", arr[l-1]);
}

int main() {
    int n = 5;
    printf("Inserire la lunghezza del vettore: ");
    scanf("%d", &n);

    int arr[n];

    for (int i = 0; i < n; i++) {
        printf("Inserire l'elemento numero %d: ", i);
        scanf("%d", &arr[i]);
    }

    stampa(arr, n);

    int primo = arr[0];
    arr[0] = arr[n-1];

    for (int i = 0; i < n-1; i++) {
        arr[i] = arr[i+1];
    }

    arr[n-1] = primo;

    stampa(arr, n);

    int ultimo = arr[n-1];
    arr[n-1] = arr[0];

    for (int i = n-1; i > 0; i--) {
        arr[i] = arr[i-1];
    }

    arr[0] = ultimo;

    stampa(arr, n);
}
