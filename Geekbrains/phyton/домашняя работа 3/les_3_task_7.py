#7. В одномерном массиве целых чисел определить два наименьших элемента.
# Они могут быть как равны между собой (оба минимальны), так и различаться.

import random

a = [random.randint(1, 100) for _ in range(10) ]
print(a)

a_min_1 = a[0]
a_min_2 = a[0]

for item in a:
    if (item<a_min_1):
        a_min_2 = a_min_1
        a_min_1 = item
    elif (item< a_min_2):
        a_min_2 = item


print(a_min_1)
print(a_min_2)