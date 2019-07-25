# 交互式输入输出

```python
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

```





# 字符串和编码

```python
#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# 为了保证python解释器读取源代码的时候按照UTF-8的格式读取，加上以上两行：
# 第一行注释是为了告诉Linux/OS X系统，这是一个Python可执行程序，Windows系统会忽略这个注释；
# 第二行注释是为了告诉Python解释器，按照UTF-8编码读取源代码，否则，你在源代码中写的中文输出可能会有乱码。
# 申明了UTF-8编码并不意味着你的.py文件就是UTF-8编码的，必须并且要确保文本编辑器正在使用UTF-8 without BOM编码

print('包含中文的字符串')

# 对于单个字符的编码，使用ord()获取字符的整数表示，chr()将编码转换为对应的字符
print(ord('A'))
print(ord('中'))
print(chr(66))
print(chr(25991))

# python字符串类型是str,在内存中保存为Unicode，如果要在网络上传输或者保存在磁盘中，
# 就需要把str变为字节为单位的bytes
# python对bytes类型的数据用带b为前缀的单引号或者双引号表示
x = b'ABC'

# 要注意区分'ABC'和b'ABC'，前者是str，后者虽然内容显示得和前者一样，
# 但bytes的每个字符都只占用一个字节。

# 以Unicode表示的str通过encode()方法可以编码为指定的bytes
print('ABC'.encode('ascii'))
# print('中文'.encode('ascii')) # 报错，UnicodeEncodeError: 'ascii' codec can't encode characters in position 0-1: ordinal not in range(128)

# 纯英文的str可以用ASCII编码为bytes，内容是一样的，含有中文的str可以用UTF-8编码为bytes。
# 含有中文的str无法用ASCII编码，因为中文编码的范围超过了ASCII编码的范围，Python会报错
# 在bytes中，无法显示为ASCII字符的字节，用\x##显示。

# 要把bytes变为str，就需要用decode()方法
print(b'ABC'.decode('ascii'))

# 如果bytes中包含无法解码的字节，decode()方法会报错
# 如果bytes中只有一小部分无效的字节，可以传入errors='ignore'忽略错误的字节

# 要计算str包含多少个字符，可以用len()函数：
print(len('ABC'))
print(len('中文'))

# len()函数计算的是str的字符数，如果换成bytes，len()函数就计算字节数
print(len('ABC'))
print(len('ABC'.encode('ascii')))
print(len('ABC'.encode('utf-8')))

print(len('中文'))
# print(len('中文'.encode('ascii')))
print(len('中文'.encode('utf-8')))

# 1个中文字符经过UTF-8编码后通常会占用3个字节，而1个英文字符只占用1个字节


# 格式化
print('Hello, %s' %'world')
print('Hi, %s, you have $%d' %('Mike',10000000))

# %运算符就是用来格式化字符串的。在字符串内部，%s表示用字符串替换，%d表示用整数替换，
# 有几个%?占位符，后面就跟几个变量或者值，顺序要对应好。如果只有一个%?，括号可以省略。
# 占位符	替换内容
# %d	   整数
# %f	   浮点数
# %s	   字符串
# %x	   十六进制整数

# 格式化整数和浮点数还可以指定是否补0和整数与小数的位数
print('%2d-%02d' %(3,1))
print('%.2f' %3.1415)

# %s 会将所有类型都转换为字符串
print('Age: %s, Gender: %s' %(25,'man'))

# 用 %% 来转义 %
print('growth rate: %d%%' %(7))

# format()
print('Hello, {0}, 成绩提升了 {1:.1f}%'.format('小明', 17.125))

s1 = 72
s2 = 85
r = (s2 - s1)/s1
print('%2.1f' % r)
```



# list 和 tuple

```python
# list
# list是一种有序的集合，可以随时添加和删除其中的元素
classmates = ['mike','bob','sarah','chander','tom','jerry']
print(classmates)

# 用len()可以获取list元素的个数
print(len(classmates))

print(classmates[1])

# 当索引超出了范围时，Python会报一个IndexError错误，所以，
# 要确保索引不要越界，记得最后一个元素的索引是len(classmates) - 1

# 如果要取最后一个元素，除了计算索引位置外，还可以用-1做索引，直接获取最后一个元素
print(classmates[-1])
print(classmates[-3])

# list是一个可变的有序表，所以，可以往list中追加元素到末尾：
classmates.append('black')
print(classmates)

# 也可以把元素插入到指定的位置
classmates.insert(2,'tony')
print(classmates)

# pop()用于删除list末尾的元素
classmates.pop()
print(classmates)

# pop(i)用于删除指定位置的元素
classmates.pop(3)
print(classmates)

# 要把某个元素替换成别的元素，可以直接赋值给对应的索引位置：
classmates[1] = 'dick'
print(classmates)

# list中的元素可以为不同类型
L = ['A',23,'Michael Jordan',True]
print(L)

# list中可以包含另外一个list
s = [['Python','Ruby'],['C++','Java'],['Mysql','Oracle']]
print(s)
print(s[1][1])

# list中如果一个元素也没有，则为空的list，长度为0
p = []
print(p)
print(len(p))

# tuple
# tuple 叫做元组，一旦初始化就不可修改
# list 使用的是[],tuple 使用的是()
classmates = ('Mike','Tony','Jerry')

# 现在，classmates这个tuple不能变了，它也没有append()，insert()这样的方法。其他获取元素的方法和list是一样的，
# 你可以正常地使用classmates[0]，classmates[-1]，但不能赋值成另外的元素

# 不可变的tuple有什么意义？因为tuple不可变，所以代码更安全。如果可能，能用tuple代替list就尽量用tuple

# 要定义一个只有一个元素的tuple，需要按照以下格式
t = (1,)

# tuple所谓的“不变”是说，tuple的每个元素，指向永远不变。即指向'a'，就不能改成指向'b'，
# 指向一个list，就不能改成指向其他对象，但指向的这个list本身是可变的！
t = (1,2,['Mike','Peter'])
t[2][0] = 'Kuzma'
print(t) # (1, 2, ['Kuzma', 'Peter'])

# 空tuple，长度为0
T = ()
print(len(T))


```



# 条件判断

```python
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
```



# 循环

```python
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
```



# dict（字典）和set

```python
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
```





# 函数

```python
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
```





## 外部函数引用

extern_function.py：

```python
#!/usr/bin/env python3
# -*- coding: utf-8 -*-

def my_abs(x):
    if x > 0:
        return x
    else:
        return -x
```



main_function.py：

```python
#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from extern_function import my_abs

print(my_abs(-100))
print(my_abs(99))
```



