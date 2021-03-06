#include <stdio.h>

int main() {
    int n;
    printf("Inserire la lunghezza della lista: ");
    scanf("%d", &n);

    int arr[n];

    for (int i = 0; i < n; i++) {
        printf("Inserire un numero: ");
        scanf("%d", &arr[i]);
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < arr[i]; j++) {
            printf("*");
        }
        printf("\n");
    }
}
