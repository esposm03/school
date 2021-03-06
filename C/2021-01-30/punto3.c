#include <stdio.h>

int main() {
    int a[5];
    int b[5];
    int r[10];
    int j = 0;

    for (int i = 0; i < 5; i++) {
        printf("Vettore 1, valore %d: ", i);
        scanf("%d", &a[i]);

        if (a[i] % 2 == 0) {
            r[j] = a[i];
            j++;
        }
    }

    for (int i = 0; i < 5; i++) {
        printf("Vettore 2, valore %d: ", i);
        scanf("%d", &b[i]);

        if (b[i] % 2 == 0) {
            r[j] = b[i];
            j++;
        }
    }

    printf("Vettore 3: [");
    for (int i = 0; i < j-1; i++) {
        printf("%d, ", r[i]);
    }
    printf("%d]\n", r[j-1]);
}
