#include <stdio.h>

int main() {
    int v[10], i = 0;
    int r[10];

    while (i < 10) {
        printf("Inserire il numero %d: ", i);
        scanf("%d", &v[i]);

        if (v[i] > 0 && v[i] < 1024) i++;
    }

    for (int i = 0; i < 10; i++) {
        r[i] = v[i] % 3;
    }

    for (int i = 0; i < 10; i++) {
        printf("Resto della divisione %d / 3: %d\n", v[i], r[i]);
    }
}
