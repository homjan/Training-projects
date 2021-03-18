#3. В массиве случайных целых чисел поменять местами минимальный и максимальный элементы.
import random

a = [random.randint(1, 100) for _ in range(10) ]
print(a)

a_min_y = a[0]
a_max_y = a[0]

a_min_x = 0
a_max_x = 0

scetchik = 0

for item in a:

    if (item<a_min_y):
        a_min_y = item
        a_min_x=scetchik

    if (item> a_max_y):
        a_max_y = item
        a_max_x = scetchik

    scetchik+=1

a[a_max_x] = a_min_y
a[a_min_x] = a_max_y

print( a)