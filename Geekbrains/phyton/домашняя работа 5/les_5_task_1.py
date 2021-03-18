#1. Пользователь вводит данные о количестве предприятий,
# их наименования и прибыль за четыре квартала для каждого предприятия.
# Программа должна определить среднюю прибыль (за год для всех предприятий)
# и отдельно вывести наименования предприятий, чья прибыль выше среднего и ниже среднего


from collections import namedtuple

#Создаем и заполняем namedtuple
Firm = namedtuple('Firm', 'name quart_1 quart_2 quart_3 quart_4 year')

firm_count = int(input('Введите число предприятий: '))
firms = [0 for _ in range(firm_count)]
profit_sum = 0

for i in range(firm_count):
    name = input(f'Введите название {i+1}-го предприятия: ')
    quarters = [float(j) for j in input('Введите через пробел прибыль в каждом квартале: ').split()]

# Считаем суммарную прибыль
    year = 0
    for quarter in quarters:
        year += quarter

    profit_sum += year
    firms[i] = Firm(name, *quarters, year)


if firm_count == 1:
    print(f'Передано 1 предприятие: {firms[0].name}. Eго годовая прибыль: {firms[0].year}')

else:
    #Считаем предприятия с прибылью меньше и больше среднего
    profit_average = profit_sum / firm_count

    profit_less = []
    profit_more = []

    for i in range(firm_count):

        if firms[i].year < profit_average:
            profit_less.append(firms[i])

        elif firms[i].year > profit_average:
            profit_more.append(firms[i])

#Выводим на экран
    print(f'\nСредняя годовая прибыль по предприятиям: {profit_average: .2f}')

    print(f'Предприятия, чья прибыль меньше {profit_average: .2f}:')
    for firm_1 in profit_less:
        print(f'Предприятие "{firm_1.name}" с прибылью {firm_1.year: .2f}')

    print(f'\nПредприятия, чья прибыль больше {profit_average: .2f}:')
    for firm_2 in profit_more:
        print(f'Предприятие "{firm_2.name}" с прибылью {firm_2.year: .2f}')