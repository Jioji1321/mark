#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import math

# https://www.liaoxuefeng.com/wiki/1016959663602400/1017105145133280
print(abs(100))
print(abs(-100))

print(max(1, 2))
print(min(1, 2))

# 数据类型转换
# int()函数可以将其他数据类型转换为整数
print(int('1234'))
print(float('3.1415'))
print(str(1234))
print(bool(1))
print(bool(''))

# 函数名其实就是指向一个函数对象的引用，
# 完全可以把函数名赋给一个变量，相当于给这个函数起了一个“别名”：
a = abs
print(a(-1))

# hex()可以将一个整数转换成十六进制表示的字符串

# 定义函数
# 在Python中，定义一个函数要使用def语句，依次写出函数名、括号、括号中的参数和冒号:，
# 然后，在缩进块中编写函数体，函数的返回值用return语句返回。


def my_abs(x):
    if x > 0:
        return x
    else:
        return -x


print(my_abs(1))
print(my_abs(-1))

# 空函数


def nop():
    pass


def testFunc(x):
    if x > 18:
        pass


# 参数检查
# 数据类型检查可以用内置函数isinstance()实现
def my_abs2(x):
    if not isinstance(x, (int, float)):
        raise TypeError('bad operand type!')
    if x >= 0:
        return x
    else:
        return -x


print(my_abs2(123))
print(my_abs2(123.345))
# print(my_abs2('123')) # TypeError: bad operand type!


# 函数返回多个值
def move(x, y, step, angle=0):
    nx = x + step * math.cos(angle)
    ny = y-step*math.sin(angle)
    return nx, ny


x, y = move(100, 100, 60, math.pi / 6)
print(x, y)

r = move(100, 100, 60, math.pi / 6)
print(r)

# 原来返回值是一个tuple！但是，在语法上，返回一个tuple可以省略括号，
# 而多个变量可以同时接收一个tuple，按位置赋给对应的值，
# 所以，Python的函数返回多值其实就是返回一个tuple，但写起来更方便。

# 定义函数时，需要确定函数名和参数个数；
# 如果有必要，可以先对参数的数据类型做检查；
# 函数体内部可以用return随时返回函数结果；
# 函数执行完毕也没有return语句时，自动return None。
# 函数可以同时返回多个值，但其实就是一个tuple。


# 函数的参数
# 定义函数的时候，我们把参数的名字和位置确定下来，函数的接口定义就完成了。
# 对于函数的调用者来说，只需要知道如何传递正确的参数，以及函数将返回什么样的值就够了，
# 函数内部的复杂逻辑被封装起来，调用者无需了解。

# Python的函数定义非常简单，但灵活度却非常大。除了正常定义的必选参数外，
# 还可以使用默认参数、可变参数和关键字参数，使得函数定义出来的接口，不但能处理复杂的参数，
# 还可以简化调用者的代码。

# 位置参数
# def power(x):  # 计算x的平方
#     return x * x

# 对于power(x)函数，参数x就是一个位置参数。
# print(power(2))

# def power(x, n):  # 计算x的n次方
#     s = 1
#     while n > 0:
#         n = n - 1
#         s = s * x
#     return s


# print(power(2, 3))

# 修改后的power(x, n)函数有两个参数：x和n，这两个参数都是位置参数，调用函数时，传入的两个值按照位置顺序依次赋给参数x和n。

# 默认参数


def power(x, n=2):  # 计算x的n次方
    s = 1
    while n > 0:
        n = n - 1
        s = s * x
    return s


print(power(5))

# 从上面的例子可以看出，默认参数可以简化函数的调用。设置默认参数时，有几点要注意：
# 一是必选参数在前，默认参数在后，否则Python的解释器会报错（思考一下为什么默认参数不能放在必选参数前面）；
# 二是如何设置默认参数。当函数有多个参数时，把变化大的参数放前面，变化小的参数放后面。变化小的参数就可以作为默认参数。
# def enroll(name,gender):
#     print('name:', name)
#     print('gender:',gender)

# print(enroll('Sarah','female'))


def enroll(name, gender, age=6, city='Beijing'):
    print('name:', name)
    print('gender:', gender)
    print('age:', age)
    print('city:', city)


x = enroll('Sarah', 'female')
print(x)
# name: Sarah
# gender: female
# age: 6
# city: Beijing
# None  <=  最后打印了自身的对象？

# 有多个默认参数时，调用的时候，既可以按顺序提供默认参数，比如调用enroll('Bob', 'M', 7)，意思是，除了name，gender这两个参数外，
# 最后1个参数应用在参数age上，city参数由于没有提供，仍然使用默认值。
# 也可以不按顺序提供部分默认参数。当不按顺序提供部分默认参数时，需要把参数名写上。比如调用enroll('Adam', 'M', city='Tianjin')，
# 意思是，city参数用传进去的值，其他默认参数继续使用默认值。

# 定义默认参数要牢记一点：默认参数必须指向不变对象！
# def add_end(L=[]):
#     L.append('END')
#     return L

# print(add_end()) #['END']
# print(add_end()) #['END', 'END']


def add_end(L=None):
    if L is None:
        L = []
    L.append('END')
    return L


print(add_end())  # ['END']
print(add_end())  # ['END', 'END']



# 可变参数