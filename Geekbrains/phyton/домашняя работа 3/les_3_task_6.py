#6. В одномерном массиве найти сумму элементов, находящихся между минимальным и максимальным элементами.
# Сами минимальный и максимальный элементы в сумму не включать.
import random

a = [random.randint(1, 100) for _ in range(10) ]
print(a)

a_min_y = a[0]
a_max_y = a[0]

a_min_x = 0
a_max_x = 0

scetchik = 0
c = 0
sum = 0

for item in a:

    if (item<a_min_y):
        a_min_y = item
        a_min_x=scetchik

    if (item> a_max_y):
        a_max_y = item
        a_max_x = scetchik

    scetchik+=1

if(a_min_x>a_max_x):
    c = a_max_x
    a_max_x = a_min_x
    a_min_x = c

for i in range(a_min_x+1, a_max_x):
    sum = sum + a[i]

print(a_min_y, a_min_x)
print(a_max_y, a_max_x)
print(sum)