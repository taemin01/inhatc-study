#include <stdio.h>

int findMax(int numbers[], int size) {
    int max = numbers[0];
    
    for(int i = 1; i < size; i++) {
        if(numbers[i] > max) {
            max = numbers[i];
        }
    }

    return max;
}

int main() {
    int numbers[] = {3, 5, 7, 2, 8};
    int size = sizeof(numbers) / sizeof(numbers[0]);
    
    int maxNumber = findMax(numbers, size);
    
    printf("최대값: %d\n", maxNumber);

    return 0;
}
