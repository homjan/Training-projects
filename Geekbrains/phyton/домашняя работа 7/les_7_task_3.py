# 3. Массив размером 2m + 1, где m — натуральное число, заполнен случайным образом.
# Найдите в массиве медиану. Медианой называется элемент ряда, делящий его на две равные части:
# в одной находятся элементы, которые не меньше медианы, в другой — не больше медианы.
# Примечание: задачу можно решить без сортировки исходного массива.
# Но если это слишком сложно, используйте метод сортировки,
# который не рассматривался на уроках (сортировка слиянием также недопустима).
import random

size = 5
array = []

for i in range(0, 2*size+1):
    array.append(random.randint(0,50))

# Нахождение медианы за среднее время O(n)

# Выберем индекс списка. Элемент с этим индексом называется опорным элементом (pivot).
# Разделим список на две группы:
# Элементы меньше или равные pivot, lesser_els
# Элементы строго большие, чем pivot, great_els
# Мы знаем, что одна из этих групп содержит медиану. Предположим, что мы ищем k-тый элемент:
# Если в lesser_els есть k или больше элементов, рекурсивно обходим список lesser_els в поисках k-того элемента.
# Если в lesser_els меньше, чем k элементтов, рекурсивно обходим список greater_els. Вместо поиска k мы ищем k-len(lesser_els).

def quickselect_median(l):
    if len(l) % 2 == 1:
        return quickselect(l, len(l) / 2)
    else:
        return 0.5 * (quickselect(l, len(l) / 2 - 1) +
                      quickselect(l, len(l) / 2))


def quickselect(l, k ):
    # Выбираем k-тый элемент в списке l (с нулевой базой)
    # :param l: список числовых данных
    # :param k: индекс
    # :return: k-тый элемент l

    if len(l) == 1:
        assert k == 0
        return l[0]

    pivot = random.choice(l)

    lows = [el for el in l if el < pivot]
    highs = [el for el in l if el > pivot]
    pivots = [el for el in l if el == pivot]

    if k < len(lows):
        return quickselect(lows, k)
    elif k < len(lows) + len(pivots):
        # Нам повезло и мы угадали медиану
        return pivots[0]
    else:
        return quickselect(highs, k - len(lows) - len(pivots))




print(array)
array_sort = quickselect_median(array)
print(array_sort)
