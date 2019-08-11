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

