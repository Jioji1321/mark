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



