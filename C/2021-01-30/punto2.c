#include <stdio.h>

void piano(float *superficie, float *volume) {
    int num_stanze;
    float h;

    printf("Inserire l'altezza del piano: ");
    scanf("%f", &h);
    printf("Inserire il numero di stanze nel piano: ");
    scanf("%d", &num_stanze);

    for (int i = 0; i < num_stanze; i++) {
        float b, l;

        printf("Base della stanza: ");
        scanf("%f", &b);
        printf("Lunghezza della stanza: ");
        scanf("%f", &l);

        *superficie += b * l;
        *volume += b * l * h;
    }
}

int main() {
    int num_piani;
    printf("Inserire il numero di piani: ");
    scanf("%d", &num_piani);

    float sup = 0;
    float vol = 0;

    for (int i = 0; i < num_piani; i++) {
        piano(&sup, &vol);
    }

    printf("Superficie: %f m^2, volume: %f m^3\n", sup/10000, vol/1000000);
}
