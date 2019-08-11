#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# https://www.liaoxuefeng.com/wiki/1016959663602400/1017104324028448


# dict 字典，在其他语言中也叫作map，使用键值对的方式存储数据(key-value)，查找速度极快
d = {'Michael': 95,'Bob': 90,'Tony': 89}
print(d['Michael'])

# 将数据放入dict的方法，除了初始化时指定之外，还可以通过key放入
d['Adam'] = 97
print(d['Adam'])


# 如果同时对同一个key值存放数据，新值将会替换旧值

# 如果key值不存在，就会报错
# 为了避免key值不存在报错，有两种方法
# 1. 通过in判断
print('Tomas' in d)

# 2. 通过dict提供的get()方法，如果key不存在可以返回None，或者自定义的value
print(d.get('Tomas'))
print(d.get('Tomas'),-1)

# 删除一个key，使用pop(key)方法，对应的value值也会从dict中删除
print(d)
d.pop('Michael')
print(d)

# dict内部存放的顺序和key放入的顺序是没有关系的。

# 和list比较，dict有以下几个特点：
# 1. 查找和插入的速度极快，不会随着key的增加而变慢；
# 2. 需要占用大量的内存，内存浪费多。
# 而list相反：
# 1. 查找和插入的时间随着元素的增加而增加；
# 2. 占用空间小，浪费内存很少。
# 所以，dict是用空间来换取时间的一种方法。

# dict可以用在需要高速查找的很多地方，在Python代码中几乎无处不在，
# 正确使用dict非常重要，需要牢记的第一条就是dict的key必须是不可变对象。

# 这是因为dict根据key来计算value的存储位置，如果每次计算相同的key得出的结果不同，那dict内部就完全混乱了。
# 这个通过key计算位置的算法称为哈希算法（Hash）。
# 要保证hash的正确性，作为key的对象就不能变。
# 在Python中，字符串、整数等都是不可变的，因此，可以放心地作为key。而list是可变的，就不能作为key


# set 和 dict类似，也是一个key的集合，但不存储value。
# 由于key不能重复，所以在set中没有重复的元素

# 创建一个set，需要提供一个list作为输入集合
s = set([1,2,3,4])
print(s)

# 重复元素被过滤
s = set([1,1,2,2,3,3,4,5,6])
print(s)

# 通过add(key)的方法可以添加元素到set中，可以重复添加但是没有效果
s.add(7)
print(s)
s.add(7)
print(s)

# 通过remove(key)方法可以删除元素
s.remove(2)
print(s)

# set可以看成数学意义上的无序和无重复元素的集合，因此，两个set可以做数学意义上的交集、并集等操作：
s1 = set([1,2,3])
s2 = set([1,3,4])
print(s1 & s2)
print(s1 | s2)

# set和dict的唯一区别仅在于没有存储对应的value，但是，set的原理和dict一样，所以，同样不可以放入可变对象，
# 因为无法判断两个可变对象是否相等，也就无法保证set内部“不会有重复元素”。试试把list放入set，看看是否会报错。
# l1 = [1,2,3,4]
# l2 = [2,3,4,5]
# s = set([l1,l2]) #TypeError: unhashable type: 'list'
# print(s)

t1 = (1,2,3)
t2 = (1,2,[3,4])
print(set(t1))
# print(set(t2)) # TypeError: unhashable type: 'list'

# 再议不可变对象
# 字符串是不可变对象
a = 'abc'
print(a.replace('a','A'))
print(a)

# 要始终牢记的是，a是变量，而'abc'才是字符串对象！
# 有些时候，我们经常说，对象a的内容是'abc'，但其实是指，a本身是一个变量，它指向的对象的内容才是'abc'：
# 当我们调用a.replace('a', 'A')时，实际上调用方法replace是作用在字符串对象'abc'上的，而这个方法虽然名字叫replace，
# 但却没有改变字符串'abc'的内容。相反，replace方法创建了一个新字符串'Abc'并返回，
# 如果我们用变量b指向该新字符串，就容易理解了，变量a仍指向原有的字符串'abc'，但变量b却指向新字符串'Abc'了：

