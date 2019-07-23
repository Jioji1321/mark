# 交互式输入输出

```python
name = input('请输入：')
print('hello,',name)
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

```

