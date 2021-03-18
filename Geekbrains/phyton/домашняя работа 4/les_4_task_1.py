# 1. Проанализировать скорость и сложность одного любого алгоритма из разработанных в рамках домашнего задания первых трех уроков.
# Примечание. Идеальным решением будет:
# a. выбрать хорошую задачу, которую имеет смысл оценивать,
# b. написать 3 варианта кода (один у вас уже есть),
# c. проанализировать 3 варианта и выбрать оптимальный,
# d. результаты анализа вставить в виде комментариев в файл с кодом (не забудьте указать, для каких N вы проводили замеры),
# e. написать общий вывод: какой из трёх вариантов лучше и почему.

#Задача
#4.  Найти сумму n элементов следующего ряда чисел: 1, -0.5, 0.25, -0.125,… Количество элементов (n) вводится с клавиатуры.

from timeit import timeit
import cProfile

s = "цикл 1000 раз"
#Тестовая функция
def test_sum(func):
     lst = [1, 0.5, 0.75, 0.625, 0.6875]
     for i, item in enumerate(lst):
         assert item==func(i)
         print(f'Test {i} OK')

#Решение с помощью цикла
def sum_element(num):
    sum = 0.0
    element = 1.0

    if (num > 0):
        while num > 0:
            sum = sum + element
            element /= (-2)
            num -= 1

    return sum


#Решение с помощью рекурсии
def element_rec(num):
    element = 1.0
    if(num<2):
        element = 1
    else:
         element = element_rec(num-1)/(-2)
    return element

def sum_element_rec(num):
    sum = 0.0

    while num > 0:
         sum = sum + element_rec(num)
         num -= 1
    return sum

#Решение с помощью рекурсии и словаря
def element_rec_dic(num):
    element_dic = {0: 0., 1 : 1.}

    def _element_rec_dic(num):
         if num in element_dic:
            return element_dic[num]

         element_dic[num] = _element_rec_dic(num-1)/(-2)
         return element_dic[num]

    return _element_rec_dic(num)

def sum_element_rec_dic(num):
    sum = 0.0

    while num > 0:
         sum = sum + element_rec_dic(num)
         num -= 1
    return sum
##################################################################
# Запускаем различные варианты задачи
def test_1(): # Cчитаем Решение с помощью цикла
    n=100 #считаем 100 элементов
    test_11 = sum_element(n)


#print(timeit('test_1()', globals={'test_1': test_1}, number=10000))
#считаем n=10 элементов
#0.017654594327801462
#считаем 100 элементов
#0.15436620454212535
#считаем 1000 элементов
#1.6211801016804614

#Используем cProfile

#cProfile.run('test_1()')
#считаем n=10000000 элементов
# 5 function calls in 1.628 seconds
#
# Ordered by: standard name
#
# ncalls tottime percall cumtime percall filename: lineno(function)
# 1 0.000 0.000 1.627 1.627 < string >: 1( < module >)
# 1 1.627 1.627 1.627 1.627 les_4_task_1.py: 24(sum_element)
# 1 0.000 0.000 1.627 1.627 les_4_task_1.py: 76(test_1)
# 1 0.000 0.000 1.628 1.628 {built - in method builtins.exec}
# 1 0.000 0.000 0.000 0.000 {method 'disable' of '_lsprof.Profiler' objects}

# Алгоритм работает приблизительно за O(n). Т.к. при увеличении количества элементов в 10 раз на каждый запуск
#  время выполнения увеличивалось приблизительно в 10 раз.

def test_2(): # Cчитаем Решение с помощью рекурсии
    n=100 #считаем 100 элементов
    test_22 = sum_element_rec(n)

    # print(timeit('test_2()', globals={'test_2': test_2}, number=10000))
    # считаем n=10 элементов
    # 0.1534204338105106
    # считаем n=20 элементов
    # 0.39554551626610585
    # считаем n=50 элементов
    # 2.4777392440738493
    # считаем n=100 элементов
    # 10.740470683439868


#cProfile.run('test_2()')
#считаем n=500 элементов

#   125255 function calls (505 primitive calls) in 0.049 seconds
#
#    Ordered by: standard name
#
#    ncalls  tottime  percall  cumtime  percall filename:lineno(function)
#         1    0.000    0.000    0.049    0.049 <string>:1(<module>)
# 125250/500    0.048    0.000    0.048    0.000 les_4_task_1.py:38(element_rec)
#         1    0.000    0.000    0.049    0.049 les_4_task_1.py:46(sum_element_rec)
#         1    0.000    0.000    0.049    0.049 les_4_task_1.py:80(test_2)
#         1    0.000    0.000    0.049    0.049 {built-in method builtins.exec}
#         1    0.000    0.000    0.000    0.000 {method 'disable' of '_lsprof.Profiler' objects}
#
#
#
# Process finished with exit code 0

# Алгоритм работает приблизительно за O(n^2). Т.к. при увеличении количества элементов в 10 раз на каждый запуск
#  время выполнения увеличилось c 0.15 до 10.74 c



def test_3(): # Cчитаем Решение с помощью рекурсии и словаря
    n=500 #считаем 100 элементов
    test_33 = sum_element_rec_dic(n)

#print(timeit('test_3()', globals={'test_3': test_3}, number=10000))
#считаем n=10 элементов
#0.2347815972484634
#считаем n=20 элементов
# 0.8287694686174237
#считаем n=50 элементов
# 4.727339124896162
#считаем n=100 элементов
# 14.270470683439868

cProfile.run('test_3()')

#считаем n=500 элементов
#    125755 function calls (1005 primitive calls) in 0.095 seconds
#
#    Ordered by: standard name
#
#    ncalls  tottime  percall  cumtime  percall filename:lineno(function)
#         1    0.000    0.000    0.095    0.095 <string>:1(<module>)
#       500    0.001    0.000    0.094    0.000 les_4_task_1.py:55(element_rec_dic)
# 125250/500    0.093    0.000    0.093    0.000 les_4_task_1.py:58(_element_rec_dic)
#         1    0.001    0.001    0.095    0.095 les_4_task_1.py:67(sum_element_rec_dic)
#         1    0.000    0.000    0.095    0.095 les_4_task_1.py:84(test_3)
#         1    0.000    0.000    0.095    0.095 {built-in method builtins.exec}
#         1    0.000    0.000    0.000    0.000 {method 'disable' of '_lsprof.Profiler' objects}
#
#
#
# Process finished with exit code 0
# Алгоритм работает приблизительно за O(n^2). Т.к. при увеличении количества элементов в 10 раз на каждый запуск
#  время выполнения увеличилось c 0.25 до 14.27 c.

#Вывод. Исходный алгоритм не нуждался в модификации и работал достаточно быстро.
# Методы основаннеые на рекурсии работают черезвычайно медленно и в повседневной практике их следует избегать.