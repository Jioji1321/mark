#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# 取一个list或tuple的部分元素是非常常见的操作。
L = ['Michael', 'Tony', 'Dick', 'Jack', 'Tom', 'Fancy']

# 取前3个元素
# 1
print([L[0], L[1], L[2]])


# 2
def getFirstThreeObject(r, n):
    temp = []
    for i in range(n):
        temp.append(r[i])
    return temp


print(getFirstThreeObject(L, 3))


# 对这种经常取指定索引范围的操作，用循环十分繁琐，
# 因此，Python提供了切片（Slice）操作符，能大大简化这种操作。
# 3
print(L[0:3])
# L[0:3]表示，从索引0开始取，直到索引3为止，但不包括索引3。即索引0，1，2，正好是3个元素。
# 如果第一个索引是0，还可以省略：
print(L[:3])


# 从索引1开始，取出2个元素出来
print(L[1:3])


# 倒数切片
# 记住倒数第一个元素的索引是-1。
print(L[-2:])
print(L[-2:-1])


L = list(range(100))
print(L)

print(L[:30])  # 从0到30
print(L[-30:])  # 从最后到倒数第30个数
print(L[10:20])  # 前11~20个数
print(L[:10:2])  # 从0到10每两个取1个
print(L[::5])  # 所有数每五个取一个
print(L[:])  # 复制一个


# tuple也是一种list，唯一区别是tuple不可变。因此，tuple也可以用切片操作，只是操作的结果仍是tuple：
str = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
print(str[:3])
print(str[::2])