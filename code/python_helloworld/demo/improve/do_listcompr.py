#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os

# 列表生成式即List Comprehensions，是Python内置的非常简单却强大的可以用来创建list的生成式。
L = list(range(1, 22))
print(L)

L = []
for x in range(1, 11):
    L.append(x * x)

print(L)

# 写列表生成式时，把要生成的元素x * x放到前面，后面跟for循环，就可以把list创建出来，十分有用
L = [x * x for x in range(1, 11)]
print(L)

# for循环后面还可以加上if判断，这样我们就可以筛选出仅偶数的平方：
L = [x * x for x in range(1, 11) if x % 2 == 0]
print(L)

# 还可以使用两层循环，可以生成全排列
L = [m + n for m in 'ABC' for n in 'XYZ']
print(L)

L = [d for d in os.listdir('../')]
print(L)

# for循环可以同时使用两个甚至多个变量
d = {'x': 'A', 'y': 'B', 'z': 'C'}
for k, v in d.items():
    print(k, '=', v)

# 列表生成式也可以使用两个变量来生成list：
L = [k + '=' + v for k, v in d.items()]
print(L)

L = ['Hello', 'World', 'IBM', 'Apple']
L = [s.lower() for s in L]
print(L)



