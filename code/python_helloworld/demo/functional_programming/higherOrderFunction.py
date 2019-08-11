# -*- coding: utf-8 -*-
# @Time    : 2019/08/08 0008 15:18
# @Author  : Jioji
# @File    : higherOrderFunction.py

from functools import reduce

# 函数本身也可以赋值给变量，即：变量可以指向函数
f = abs
print(f(-10))


# 函数名其实就是指向函数的变量！对于abs()这个函数，完全可以把函数名abs看成变量，它指向一个可以计算绝对值的函数
# 既然变量可以指向函数，函数的参数能接收变量，那么一个函数就可以接收另一个函数作为参数，这种函数就称之为高阶函数。
def add(x, y, f):
    return f(x) + f(y)


print(add(-5, -6, abs))


# x = -5
# y = 6
# f = abs
# f(x) + f(y) ==> abs(-5) + abs(6) ==> 11
# return 11


# map/reduce
# Python内建了map()和reduce()函数
# map()函数接收两个参数，一个是函数，一个是Iterable，map将传入的函数依次作用到序列的每个元素，并把结果作为新的Iterator返回
def f(x):
    return x * x


# map()传入的第一个参数是f，即函数对象本身。由于结果r是一个Iterator，Iterator是惰性序列，因此通过list()函数让它把整个序列都计算出来并返回一个list。
ret = map(f, [1, 2, 3, 4, 5])  # => 返回的是一个map对象，需要用list()转换
print(ret)
print(list(ret))

# 将结果转换为字符串输出
print(list(map(str, [1, 2, 3, 4, 5, 6, 7, 8, 9])))


# reduce把一个函数作用在一个序列[x1, x2, x3, ...]上，这个函数必须接收两个参数，reduce把结果继续和序列的下一个元素做累积计算，其效果就是：
# reduce(f, [x1, x2, x3, x4]) = f(f(f(x1, x2), x3), x4)
def fn(x, y):
    return x * 10 + y


ret = reduce(fn, [1, 3, 5, 7, 9])
print(ret)

# 如果考虑到字符串str也是一个序列，对上面的例子稍加改动，配合map()，我们就可以写出把str转换为int的函数：
str = '1234567'


def char2num(s):
    digits = {'0': 0, '1': 1, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9}
    return digits[s]


ret = reduce(fn, map(char2num, str))
print(ret)


# 利用map()函数，把用户输入的不规范的英文名字，变为首字母大写，其他小写的规范名字。输入：['adam', 'LISA', 'barT']，输出：['Adam', 'Lisa', 'Bart']
def normalize(name):
    return name[0].upper() + name[1:].lower()


# 测试:
L1 = ['adam', 'LISA', 'barT']
L2 = list(map(normalize, L1))
print(L2)


# Python提供的sum()函数可以接受一个list并求和，请编写一个prod()函数，可以接受一个list并利用reduce()求积：
def prod(L):
    def multiply(x, y):
        return x * y

    return reduce(multiply, L)


print('3 * 5 * 7 * 9 =', prod([3, 5, 7, 9]))
if prod([3, 5, 7, 9]) == 945:
    print('测试成功!')
else:
    print('测试失败!')


def str2float(s):
    s_1 = s[:s.index('.')]

    s_2 = s[s.index('.') + 1:]

    def fn(x, y):
        return x * 10 + y

    def char2num(h):
        digits = {'0': 0, '1': 1, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9}
        return digits[h]

    s1 = reduce(fn, map(char2num, s_1))
    s2 = reduce(fn, map(char2num, s_2))

    return s1 + s2 / 10 ** len(s_2)


print('str2float(\'123.456\') =', str2float('123.456'))
if abs(str2float('123.456') - 123.456) < 0.00001:
    print('测试成功!')
else:
    print('测试失败!')
