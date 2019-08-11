#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# for循环
names = ['Mike','Micheal','Kobe']
for x in names:
    print(x)

# Python提供一个range()函数，可以生成一个整数序列，
# 再通过list()函数可以转换为list。
L = list(range(5))
print(L) # [0, 1, 2, 3, 4]

# while循环
sum = 0
n = 99
while n > 0:
    sum = sum + n
    n = n - 2
print(sum)

# break,可以提前退出循环
n = 1
while n <= 10:
    if n > 5:
        break
    print(n)
    n = n + 1
print('END')


# continue，退出本次循环
n = 0
while n < 10:
    n = n + 1
    if n % 2 == 0: # 如果n是偶数，执行continue语句
        continue # continue语句会直接继续下一轮循环，后续的print()语句不会执行
    print(n)




