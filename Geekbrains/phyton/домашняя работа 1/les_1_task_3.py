#7. 3. Написать программу, которая генерирует в указанных пользователем границах:
#a. случайное целое число,
#b. случайное вещественное число,
#c. случайный символ.
#Для каждого из трех случаев пользователь задает свои границы диапазона. Например, если надо получить случайный символ от 'a' до 'f', то вводятся эти символы. Программа должна вывести на экран любой символ алфавита от 'a' до 'f' включительно.
import random
# Запрашиваем числа

a = int(input("Введите первое случайное целое число "))
b = int(input("Введите второе случайное целое число "))

c = float(input("Введите первое случайное вещественное число "))
d = float(input("Введите второе случайное вещественное число "))

e = input("Введите первый символ")
f = input("Введите первый символ")

#Считаем случайные числа
l = random.randint(a,b)
m = random.triangular(c,d)
n = chr(random.randint(ord(e), ord(f)))

print (f'случайное целое число = {l}')
print (f'случайное вещественное число = {m}')
print (f'Случайный символ = {n}')