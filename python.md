





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


enroll('Sarah', 'female')
# x = enroll('Sarah', 'female')
# print(x)
# name: Sarah
# gender: female
# age: 6
# city: Beijing
# None  <=
# 前四个输出 就是执行函数的时候输出的 最后一个None值 是执行函数之后的返回值 print(返回值)
# 所以就是输出了一个None

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
print(add_end())  # ['END']


# 可变参数
# 可变参数就是传入的参数个数是可变的，可以是1个、2个到任意个，还可以是0个。
def calc(numbers):
    sum = 0
    for n in numbers:
        sum = sum + n * n
    return sum


# 调用函数的时候，需要将传入的参数组装成一个list或者tuple
print(calc([1, 2, 3, 4]))
print(calc((1, 3, 5, 7)))


# 利用可变参数
def calc2(*numbers):
    sum = 0
    for n in numbers:
        sum = sum + n * n
    return sum


print(calc2(1, 2, 3))
print(calc2(1, 3, 5, 7))


# 定义可变参数和定义一个list或tuple参数相比，仅仅在参数前面加了一个*号。
# 在函数内部，参数numbers接收到的是一个tuple，因此，函数代码完全不变。
# 但是，调用该函数时，可以传入任意个参数，包括0个参数：
print(calc2())
print(calc2(1, 2))


# 如果已经有一个list或者tuple，可以用以下方法
nums = [1, 2, 3]
print(calc2(nums[0], nums[1], nums[2]))


# 可以在list或者tuple前面加上一个*，将其变成可变参数传进去
print(calc2(*nums))
# *nums表示把nums这个list的所有元素作为可变参数传进去。


# 关键字参数
# 关键字参数允许传入0个或者任意个含参数名的参数，这些关键字参数在函数内部自动组装为一个dict
def person(name, age, **kw):
    print('name:', name, ', age:', age, ', other:', kw)


person('Michael', 30)


# 也可以传入任意个数的关键字参数
person('Bob', 35, cuty='Beijing')
person('Adam', 45, gender='M', job='Engineer')


# 关键字参数有什么用？它可以扩展函数的功能。比如，在person函数里，我们保证能接收到name和age这两个参数，
# 但是，如果调用者愿意提供更多的参数，我们也能收到。试想你正在做一个用户注册的功能，除了用户名和年龄是必填项外，
# 其他都是可选项，利用关键字参数来定义这个函数就能满足注册的需求。

# 和可变参数类似，也可以先组装出一个dict，然后，把该dict转换为关键字参数传进去：
extra = {'city': 'Beijing', 'job': 'Engineer'}
person('Jack', 24, **extra)


# **extra表示把extra这个dict的所有key-value用关键字参数传入到函数的**kw参数，kw将获得一个dict，
# 注意kw获得的dict是extra的一份拷贝，对kw的改动不会影响到函数外的extra。


# 命名关键字参数
# 对于关键字参数，函数的调用者可以传入任意不受限制的关键字参数，至于到底传入了哪些，就需要在函数内部通过kw检查
def person2(name, age, **kw):
    if 'city' in kw:
        # 有city参数
        pass
    if 'job' in kw:
        # 有job参数
        pass
    print('name:', name, ', age:', age, ', other:', kw)


person2('Jack', 24, city='Beijing', addr='Chaoyang', zipCode=123456)

# 如果要限制关键字参数的名字，就可以用命名关键字参数


def person3(name, age, *, city, job):
    print(name, age, city, job)


person3('Jack', 24, city='Beijing', job='Engineer')


# 如果函数定义中已经有一个可变参数，后面跟着的命名关键字参数就不在需要一个特殊分割符*了
def person4(name, age, *args, city, job):
    print(name, age, args, city, job)


# 命名关键字参数必须传入参数名，这和位置参数不同，如果没有传入参数名，调用会报错
# person('Jack', 24, 'Beijing', 'Engineer')
# Traceback (most recent call last):
#   File "<stdin>", line 1, in <module>
# TypeError: person() takes 2 positional arguments but 4 were given


# 由于调用时缺少参数名city和job，Python解释器把这4个参数均视为位置参数，但person()函数仅接受2个位置参数。

# 命名关键字参数可以有缺省值，从而简化调用：
def person5(name, age, *, city='Beijing', job):
    print(name, age, city, job)


person5('Jack', 24, job='Engineer')

# 使用命名关键字参数时，要特别注意，如果没有可变参数，就必须加一个*作为特殊分隔符。如果缺少*，Python解释器将无法识别位置参数和命名关键字参数：


# 参数组合
# 在Python中定义函数，可以用必选参数、默认参数、可变参数、关键字参数和命名关键字参数，这5种参数都可以组合使用。但是请注意，参数定义的顺序必须是：必选参数、默认参数、可变参数、命名关键字参数和关键字参数。
def f1(a, b, c=0, *args, **kw):
    print('a =', a, 'b =', b, 'c =', c, 'args =', args, 'kw =', kw)


def f2(a, b, c=0, *, d, **kw):
    print('a =', a, 'b =', b, 'c =', c, 'd =', d, 'kw =', kw)


f1(1, 2)
f1(1, 2, c=3)
f1(1, 2, 3, 'a', 'b')
f1(1, 2, 3, 'a', 'b', x=99)
f2(1, 2, d=99, ext=None)


# 通过一个tuple和dict，你也可以调用上述函数：
args = (1, 2, 3, 4)
kw = {'d': 99, 'x': '#'}
f1(*args, **kw)


# 对于任意函数，都可以通过类似func(*args, **kw)的形式调用它，无论它的参数是如何定义的。


# 递归函数
# https://www.liaoxuefeng.com/wiki/1016959663602400/1017268131039072
# 在函数内部，可以调用其他函数。如果一个函数在内部调用自身本身，这个函数就是递归函数。
def fact(x):
    if x == 1:
        return 1
    else:
        return x * fact(x - 1)


print(fact(1))
print(fact(5))
print(fact(100))


# ===> fact(5)
# ===> 5 * fact(4)
# ===> 5 * (4 * fact(3))
# ===> 5 * (4 * (3 * fact(2)))
# ===> 5 * (4 * (3 * (2 * fact(1))))
# ===> 5 * (4 * (3 * (2 * 1)))
# ===> 5 * (4 * (3 * 2))
# ===> 5 * (4 * 6)
# ===> 5 * 24
# ===> 120


# 递归函数的优点是定义简单，逻辑清晰。理论上，所有的递归函数都可以写成循环的方式，但循环的逻辑不如递归清晰。

# 使用递归函数需要注意防止栈溢出。在计算机中，函数调用是通过栈（stack）这种数据结构实现的，每当进入一个函数调用，
# 栈就会加一层栈帧，每当函数返回，栈就会减一层栈帧。由于栈的大小不是无限的，所以，递归调用的次数过多，会导致栈溢出。

# 解决递归调用栈溢出的方法是通过尾递归优化，事实上尾递归和循环的效果是一样的，所以，把循环看成是一种特殊的尾递归函数也是可以的。
# 尾递归是指，在函数返回的时候，调用自身本身，并且，return语句不能包含表达式。这样，编译器或者解释器就可以把尾递归做优化，使递归本身无论调用多少次，都只占用一个栈帧，不会出现栈溢出的情况。

def fact1(n):
    return fact_iter(n, 1)


def fact_iter(num, product):
    if num == 1:
        return product
    return fact_iter(num - 1, num * product)


print(fact1(5))
# ===> fact_iter(5, 1)
# ===> fact_iter(4, 5)
# ===> fact_iter(3, 20)
# ===> fact_iter(2, 60)
# ===> fact_iter(1, 120)
# ===> 120


# 使用递归函数的优点是逻辑简单清晰，缺点是过深的调用会导致栈溢出。
# 针对尾递归优化的语言可以通过尾递归防止栈溢出。尾递归事实上和循环是等价的，没有循环语句的编程语言只能通过尾递归实现循环。
# Python标准的解释器没有针对尾递归做优化，任何递归函数都存在栈溢出的问题。


def moveHanNuo(n, a, b, c):
    if n == 1:
        print(a, '-->', c)
    else:
        moveHanNuo(n-1, a, c, b)
        moveHanNuo(1, a, b, c)
        moveHanNuo(n-1, b, a, c)


moveHanNuo(3, 'A', 'B', 'C')
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





## 常用函数

```python
list() ： 变为一个list
range() : 范围
int() ： 转换为int类型
float() ： 转换为float类型
str() ： 转换为字符串
iter() : 把list、dict、str等Iterable变成Iterator可以使用iter()函数
upper(): 转大写
lower(): 转小写
```



# 切片

```python
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
```





# 迭代

```python
#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# https://www.liaoxuefeng.com/wiki/1016959663602400/1017316949097888
# 迭代

from collections import Iterable

# Python的for循环抽象程度要高于C的for循环，因为Python的for循环不仅可以用在list或tuple上，还可以作用在其他可迭代对象上。

# list这种数据类型虽然有下标，但很多其他数据类型是没有下标的，
# 但是，只要是可迭代对象，无论有无下标，都可以迭代，比如dict就可以迭代
d = {'a': 1, 'b': 2, 'c': 3}
for key in d:
    print(key)

# 默认情况下，dict迭代的是key。如果要迭代value，可以用for value in d.values()，
# 如果要同时迭代key和value，可以用for k, v in d.items()。
for ch in 'ABC':
    print(ch)


# 所以，当我们使用for循环时，只要作用于一个可迭代对象，for循环就可以正常运行，而我们不太关心该对象究竟是list还是其他数据类型。
# 那么，如何判断一个对象是可迭代对象呢？方法是通过collections模块的Iterable类型判断：
print(isinstance('abc', Iterable))
print(isinstance([1, 2, 3], Iterable))
print(isinstance(123, Iterable))


# Python内置的enumerate函数可以把一个list变成索引-元素对，这样就可以在for循环中同时迭代索引和元素本身：
for i,value in enumerate(['A','B','C']):
    print(i,value)


for x, y in [(1, 1), (2, 4), (3, 9)]:
    print(x, y)
```





# 列表生成式

```python
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
```





# 生成器

```python
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
```





# 迭代器

```python
# -*- coding: utf-8 -*-
# @Time    : 2019/08/04 0004 20:32
# @Author  : Jioji
# @File    : do_iterator.py

from collections.abc import Iterator
from collections.abc import Iterable

# 可以直接作用于for循环的数据类型有以下几种：
# 一类是集合数据类型，如list、tuple、dict、set、str等；
# 一类是generator，包括生成器和带yield的generator function。
# 这些可以直接作用于for循环的对象统称为可迭代对象：Iterable。
# 可以使用isinstance()判断一个对象是否是Iterable对象：
print(isinstance([], Iterable))
print(isinstance({}, Iterable))
print(isinstance('abc', Iterable))
print(isinstance((x for x in range(10)), Iterable))
print(isinstance(100, Iterable))

# 而生成器不但可以作用于for循环，还可以被next()函数不断调用并返回下一个值，直到最后抛出StopIteration错误表示无法继续返回下一个值了。
# 可以被next()函数调用并不断返回下一个值的对象称为迭代器：Iterator。
# 可以使用isinstance()判断一个对象是否是Iterator对象：
print(isinstance([], Iterator))
print(isinstance({}, Iterator))
print(isinstance('abc', Iterator))
print(isinstance((x for x in range(10)), Iterator))
print(isinstance(100, Iterator))

# 生成器都是Iterator对象，但list、dict、str虽然是Iterable，却不是Iterator。
# 把list、dict、str等Iterable变成Iterator可以使用iter()函数：
print(isinstance(iter([]), Iterator))
print(isinstance(iter({}), Iterator))

# Python的Iterator对象表示的是一个数据流，Iterator对象可以被next()函数调用并不断返回下一个数据，直到没有数据时抛出StopIteration错误。
# 可以把这个数据流看做是一个有序序列，但我们却不能提前知道序列的长度，只能不断通过next()函数实现按需计算下一个数据，所以Iterator的计算是惰性的，只有在需要返回下一个数据时它才会计算。
# Iterator甚至可以表示一个无限大的数据流，例如全体自然数。而使用list是永远不可能存储全体自然数的。

# 凡是可作用于for循环的对象都是Iterable类型；
# 凡是可作用于next()函数的对象都是Iterator类型，它们表示一个惰性计算的序列；
# 集合数据类型如list、dict、str等是Iterable但不是Iterator，不过可以通过iter()函数获得一个Iterator对象。
# Python的for循环本质上就是通过不断调用next()函数实现的，例如：

for x in [1, 2, 3, 4, 5]:
    pass
# 实际上完全等价于：
# 首先获得Iterator对象:
it = iter([1, 2, 3, 4, 5])
# 循环:
while True:
    try:
        # 获得下一个值:
        x = next(it)
    except StopIteration:
        # 遇到StopIteration就退出循环
        break
```

