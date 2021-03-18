# 2. Написать два алгоритма нахождения i-го по счёту простого числа.
# Функция нахождения простого числа должна принимать на вход натуральное и возвращать соответствующее простое число.
# Проанализировать скорость и сложность алгоритмов.
# Первый — с помощью алгоритма «Решето Эратосфена».
# Примечание. Алгоритм «Решето Эратосфена» разбирался на одном из прошлых уроков.
# Используйте этот код и попробуйте его улучшить/оптимизировать под задачу.
# Второй — без использования «Решета Эратосфена».

import math
import cProfile
from timeit import timeit

#Воспользуемся теоремой о простых числах, которая говорит нам, что поведения k-го простого числа p
#определяется как p ~ k*ln k
#Умножим на 2 для надежности: p = 2*k*ln(k)

def Erastofen(k):
    p = int(2*math.log(k)*k)
    sieve = [i for i in range (p)]
    sieve[1] = 0

    for i in range (p):

        if sieve[i]!=0:
            j= i*2

            while j<p:
                sieve[j]=0
                j+=i

    result = [i for i in sieve if i!=0]


    return result[k-1]# -1 т.к. массив заполняется с 0



def search_prime(n):
    count = 1
    number = 1
    prime = [2]

    if n == 1:
        return 2

    while count != n:
        number += 2

        for num in prime:
            if number % num == 0:
                break
        else:
            count += 1
            prime.append(number)

    return number

# print(search_prime(500))
# print(Erastofen(500))

def test_1(): # Cчитаем Решение с помощью цикла
    n=10000 #считаем 100 элементов
    test_11 = Erastofen(n)

def test_2(): # Cчитаем Решение с помощью рекурсии
    n=10000 #считаем 100 элементов
    test_22 = search_prime(n)

#print(timeit('test_1()', globals={'test_1': test_1}, number=100))
# n= 10
#0.0011619209638719088
# n= 100
# 0.028003974665395687
# n= 1000
# 0.4785941875961205
# n= 10000
# n = 7.5631450861721765

#cProfile.run('test_1()')
#n = 100000
# 8 function calls in 1.333 seconds
#
# Ordered by: standard name
#
# ncalls tottime percall cumtime percall filename: lineno(function)
# 1 0.000 0.000 1.333 1.333 < string >: 1( < module >)
# 1 1.069 1.069 1.312 1.312 les_4_task_2.py: 17(Erastofen)
# 1 0.174 0.174 0.174 0.174 les_4_task_2.py: 19( < listcomp >)
# 1 0.069 0.069 0.069 0.069 les_4_task_2.py: 31( < listcomp >)
# 1 0.021 0.021 1.333 1.333 les_4_task_2.py: 61(test_1)
# 1 0.000 0.000 1.333 1.333 {built - in method builtins.exec}
# 1 0.000 0.000 0.000 0.000 {built - in method math.log}
# 1 0.000 0.000 0.000 0.000 {method 'disable' of '_lsprof.Profiler' objects}
#

# Алгоритм работает приблизительно за O(n*ln(n)). (В реальности O(n*ln(ln(n)))
# Т.к. при увеличении количества элементов в 10 раз на каждый запуск
#  время выполнения увеличивалось приблизительно в 20 раз.

print(timeit('test_2()', globals={'test_2': test_2}, number=100))
# n= 10
#0.0006279224909147173
# n= 100
# 0.034310257155875
# n= 1000
# 3.171250542317902
# n= 10000
# 359.2339087475295

#cProfile.run('test_2()')
#n = 100000

# 100004 function calls in 324.716 seconds
# Ordered by: standard name
#
#    ncalls  tottime  percall  cumtime  percall filename:lineno(function)
#         1    0.000    0.000  324.716  324.716 <string>:1(<module>)
#         1  324.650  324.650  324.715  324.715 les_4_task_2.py:38(search_prime)
#         1    0.001    0.001  324.716  324.716 les_4_task_2.py:65(test_2)
#         1    0.000    0.000  324.716  324.716 {built-in method builtins.exec}
#     99999    0.065    0.000    0.065    0.000 {method 'append' of 'list' objects}
#         1    0.000    0.000    0.000    0.000 {method 'disable' of '_lsprof.Profiler' objects}

# Алгоритм работает приблизительно за O(n^2).
# Т.к. при увеличении количества элементов в 10 раз на каждый запуск
#  время выполнения увеличивалось приблизительно в 100 раз.
#  Однако, при малых n (n<100) он работает быстрее решета эрастофена

