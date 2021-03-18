#9. Найти максимальный элемент среди минимальных элементов столбцов матрицы.
import random
#Генерируем матрицу
matrix = [[random.randint(1, 10) for _ in range(5)] for _  in range(7)]
for line in matrix:
    for item in line:
        print(f'{item:>4}', end= '')

    print()

print()

min_column = [0]*len(matrix[0])
#Получаем первую строчку
for j in range(len(min_column)):
    min_column[j] = matrix[0][j]

# Находим минимальные значения и заодно выводим матрицу
for line in matrix:
    sum_line = 0

    for i, item in enumerate(line):
        if(min_column[i]>item):
               min_column[i] = item

        print(f'{item:>4}', end='')

    print()

print('-'* (len(matrix)*5))
#выводим минимумы столбцов

for s in min_column:
    print(f'{s:>5}', end='')

# считаем максимум

max = min_column[0]
for y in min_column:
    if (y > max):
        max = y

#выводим максимум
print()
print("Максимум минимумов столбцов ", max)

