#include <stdio.h>

void stampa(int arr[], int l) {
    printf("[");
    for (int i = 0; i < l-1; i++) {
        printf("%d, ", arr[i]);
    }
    printf("%d]\n", arr[l-1]);
}

int main() {
    const int n = 10;

    int arr[n] = {2, 4, 3, 1, 0, 5, 2, 4, 0, 8};
    stampa(arr, n);

    int out[n];

    int out_i = 0;
    for (int i = 0; i < n; i++) {
        int gia_inserito = 0;
        for (int j = 0; j < i; j++) {
            if (arr[i] == arr[j]) gia_inserito = 1;
        }

        if ((!gia_inserito) && arr[i] != 0) {
            out[out_i] = arr[i];
            out_i++;
        }
    }

    stampa(out, out_i);
}
