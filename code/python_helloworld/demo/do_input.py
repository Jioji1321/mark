#!/usr/bin/env python3
# -*- coding: utf-8 -*-

name = input('请输入：')
print('hello,',name)

# birth = input('请输入你的出生年份：')
# if birth < 2000:
#     print('00前')
# else:
#     print('00后')

# 以上报错，原因是input的返回数据类型是str，str不能够和整数直接比较
# 必须先把str转换为整数，才能进行比较
# python提供了int()方法来完成这个过程
s = input('请输入你的出生年份：')
birth = int(s)
if birth < 2000:
    print('00前')
else:
    print('00后')
