#7. Определить, является ли год, который ввел пользователь, високосным или не високосным.
#Вводим год
e = int(input("Введите год: "))

if (e%400==0):
    print (f'{e} - Високосный год')
elif(e%100==0):
    print (f'{e} - Не високосный год')
elif(e%4==0):
    print(f'{e} - Високосный год')
else:
    print(f'{e} - Не високосный год')


