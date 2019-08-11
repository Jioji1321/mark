#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# 如果列表元素可以按照某种算法推算出来，那我们是否可以在循环的过程中不断推算出后续的元素呢？这样就不必创建完整的list，从而节省大量的空间。
# 在Python中，这种一边循环一边计算的机制，称为生成器：generator

# 生成器的创建方法
# 1. 把一个列表生成式的[]改成()，就创建了一个generator：
L = [x * x for x in range(10)]
print(L)
g = (x * x for x in range(10))
print(g)

# 创建L和g的区别仅在于最外层的[]和()，L是一个list，而g是一个generator。
# 打印出generator的每一个元素,可以通过next()函数获得generator的下一个返回值
print(next(g))
print(next(g))
print(next(g))

# generator保存的是算法，每次调用next(g)，就计算出g的下一个元素的值，直到计算到最后一个元素，没有更多的元素时，抛出StopIteration的错误。

# 正确的方法是使用for循环，因为generator也是可迭代对象
for n in g:
    print(n)


# 我们创建了一个generator后，基本上永远不会调用next()，而是通过for循环来迭代它，并且不需要关心StopIteration的错误
# 斐波那契数列
def fab(max):
    n, a, b = 0, 0, 1
    while n < max:
        print(b)
        a, b = b, a + b  # ==>t = (b, a + b),t是一个tuple,a = t[0],b = t[1]
        n = n + 1
    print('done')


fab(5)


# fib函数实际上是定义了斐波拉契数列的推算规则，可以从第一个元素开始，推算出后续任意的元素，这种逻辑其实非常类似generator。
# 要把fib函数变成generator，只需要把print(b)改为yield b就可以了：
def fab_g(max):
    n, a, b = 0, 0, 1
    while n < max:
        yield b
        a, b = b, a + b  # ==>t = (b, a + b),t是一个tuple,a = t[0],b = t[1]
        n = n + 1
    return 'done'


f = fab_g(5)
print(f)


# generator和函数的执行流程不一样。函数是顺序执行，遇到return语句或者最后一行函数语句就返回。而变成generator的函数，在每次调用next()的时候执行，遇到yield语句返回，再次执行时从上次返回的yield语句处继续执行。
def odd():
    print('step 1')
    yield 1
    print('step 2')
    yield (3)
    print('step 3')
    yield (5)


# 调用该generator时，首先要生成一个generator对象，然后用next()函数不断获得下一个返回值：
o = odd()
print(next(o))
print(next(o))
print(next(o))
# print(next(o))


g = fab_g(6)
while True:
    try:
        x = next(g)
        print('g', x)
    except StopIteration as e:
        print('Generator return value: ', e.value)
        break


# 请注意区分普通函数和generator函数，普通函数调用直接返回结果：
# >>> r = abs(6)
# >>> r
# 6

# generator函数的“调用”实际返回一个generator对象：
# >>> g = fib(6)
# >>> g
# <generator object fib at 0x1022ef948>


# 杨辉三角定义如下：
#
#           1
#          / \
#         1   1
#        / \ / \
#       1   2   1
#      / \ / \ / \
#     1   3   3   1
#    / \ / \ / \ / \
#   1   4   6   4   1
#  / \ / \ / \ / \ / \
# 1   5   10  10  5   1
# 把每一行看做一个list，试写一个generator，不断输出下一行的list：
def triangles():
    L = [1]
    yield L
    while True:
        L = [1] + [L[x] + L[x + 1] for x in range(len(L) - 1)] + [1]
        yield L


n = 0
results = []
for t in triangles():
    print(t)
    results.append(t)
    n = n + 1
    if n == 10:
        break
if results == [
    [1],
    [1, 1],
    [1, 2, 1],
    [1, 3, 3, 1],
    [1, 4, 6, 4, 1],
    [1, 5, 10, 10, 5, 1],
    [1, 6, 15, 20, 15, 6, 1],
    [1, 7, 21, 35, 35, 21, 7, 1],
    [1, 8, 28, 56, 70, 56, 28, 8, 1],
    [1, 9, 36, 84, 126, 126, 84, 36, 9, 1]
]:
    print('测试通过!')
else:
    print('测试失败!')
