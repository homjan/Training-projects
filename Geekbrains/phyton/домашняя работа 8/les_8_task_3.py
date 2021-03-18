# 3. Написать программу, которая обходит не взвешенный ориентированный граф без петель,
# в котором все вершины связаны, по алгоритму поиска в глубину (Depth-First Search).
# Примечания:
# a. граф должен храниться в виде списка смежности;
# b. генерация графа выполняется в отдельной функции, которая принимает на вход число вершин.
import random

# Генерация графа
def graph_generator( vertex):
    vert = []
    graph = []
    for i in range(vertex):
        vert.append(i)

    for i in vert:
        vert_ch = random.choices(vert, k=random.randint(1, vertex))
        vert_ch = set(vert_ch)
        vert_ch.discard(i)
        graph.append( vert_ch)

    return graph

n = int(input('Введите число вершин: '))
start = int(input('Введите первую вершину: '))


visited = [False] * (n + 1)
prev = [None] * (n + 1)

#Обход графа
def dfs(start, visited, prev, g):
    visited[start] = True
    for u in g[start]:
        if not visited[u]:
            prev[u] = start
            dfs(u, visited, prev, g)
            print(u)


g= graph_generator(n)
dfs(start, visited, prev, g)
print(g)