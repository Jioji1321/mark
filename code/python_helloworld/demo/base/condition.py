#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# 条件判断 if, 注意 :
age = 14
print('ur age is ', age)
if age >= 18:
    print('adult')
else:
    print('youngster')

# 用elif 表示else if
age = 1
print('ur age is ', age)
if age >= 18:
    print('adult')
elif age > 10:
    print('youngster')
elif age > 8:
    print('children')
else:
    print('baby')

# if 判断条件可以简写,只要x是非零数值、非空字符串、非空list等，就判断为True，否则为False。
x = ()
if x:
    print('True')
else:
    print('False')



height = 1.83
weight = 70

bmi = weight / (height ** 2)
if bmi < 18.5:
    print('过轻')
elif bmi < 25.0:
    print('正常')
elif bmi < 28.0:
    print('过重')
elif bmi < 32.0:
    print('肥胖')
else:
    print('过度肥胖')