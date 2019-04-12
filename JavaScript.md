# JavaScript

## 检测类型

- 检测一个变量是否是基本类型：使用 typeof 操作符(字符串、数值、布尔值还是 undefined)
- 如果变量值是一个变量或者 null，使用 typeof 操作符返回的是"object"
- 检测一个值为什么类型的值：使用 instanceof 操作符
  - `result = variable instanceof constructor`
  - `alert(person instanceof Object)`



## 执行环境及作用域

- 在函数的作用域中定义的变量都属于局部变量，作用域外无法访问

```javascript
var color = "blue";

function changeColor(){
  var anotherColor = "red";
  
  function swapColor(){
    var tempColor = anotherColor;
    anotherColor = color;
  	color = tempColor;
    //这里可以访问 color,anotherColor和tempColor
  }
  //这里可以访问 color 和 anotherColor，但不可以访问 tempColor
  swapColor();
}
```



## 延长作用域链

当执行流进入以下任何一个语句时，作用域链将会得到加长：

1. try-catch 语句的 catch 块
2. with 语句



## 没有块级作用域

在 if 和 for 中定义的变量将会添加到当前的执行环境中



### 声明变量

如果初始化变量时没有使用 var 声明，该变量将会自动添加到全局环境

```javascript
function add(num1,num2){
  var sum = num1 + num2; //如果换成 sum = num1 + num2; ，则不会出错
  return sum;
}
var ret = add(1,2);
alert(sum); //错误，sum 不是有效的变量
```



### 查询标识符

```javascript
var color = "red";
function getColor(){
  return color;
}
alert(getColor()); //"red"
```

如果局部变量中存在同名标识符，则不会使用位于父环境中的标识符

```javascript
var color = "red";
function getColor(){
  var color = "blue";
  return color;
}
alert(getColor()); //"blue"
```

任何位于 `var color = "blue";`声明语句之后的代码，如果不使用 `window.color`都无法访问全局 color 变量



## 垃圾回收

### 标记清除

最常用，当变量进入环境（在函数中声明变量）时，就将这个变量标记为"进入环境"；当变量离开环境时，则将其标记为"离开环境"。

### 引用计数

不常用。当变量被声明且将一个引用变量赋给该变量时，则这个值的引用次数就是1，如果同一个值又被赋给了另外一个变量，则该变量的引用次数就加1；相反，如果包含对这个值引用的变量有趣的了另外一个值，则这个值的引用次数减1。



## 小结

JavaScript 变量可以用来保存两种类型的值：基本类型值和引用类型值。

基本类型值：Undefined，Null，Boolean，Number，String

基本类型值和引用类型值具有以下特点：

1. 基本类型值在内存中占据固定大小的空间，因此被保存在栈内存中
2. 从一个变量想另一个变量复制基本类型的值，会创建这个值的一个副本
3. 引用类型的值是对象，保存在堆内存中
4. 包含引用类型值的变量实际上包含的不是对象本身，而是一个指向该对象的指针
5. 从一个变量向另一个变量复制引用类型的值，复制的是指针，因此两个变量最终都指向同一个对象





## 引用类型

Object，Array，



### Array

检测数组使用 Array.isArray(value)或者 value instanceof Array



#### 转换方法

toString()：返回由数组中每个值的字符串形式拼接而成的一个以逗号分隔的**字符串**

toLocaleString()

valueOf()：还是数组形式

join()：接受一个参数，即用作分割符的字符串



#### 栈方法

pop()：从数组末尾溢出最后一项，减少数组的 length 数，然后返回**移除的项**

push()：接收任意数量的参数，将他们逐个添加到数组末尾，并返回**修改后数组的长度**



#### 队列方法

shift()：移除数组中的第一个项并返回改值，同时将数组长度减一

unshift()：从数组前端添加任意个项并返回新数组的长度

同时使用 unshift()和 pop()方法，可以从相反的方向来模拟队列，即在数组的前端添加项，从数组末端移除项

```javascript
var colors = new Array();
var count = colors.unshift("red","green"); //["red","green"]
alert(count); //2

count = colors.unshift("black"); //["black","red","green"]
alert(count); //3

var item = colors.pop(); //["black","red"]
alert(item); //"green"
alert(colors.length); //2
```



#### 重排序方法

sort()：默认情况按照升序排列数组项(最小最前，最大最后)，调用每个数组项的 toString()

reverse()：反转数组项的数据

返回值是经过排序之后的数组

比较函数

```javascript
function compare(value1,value2){
  if(value1 < value2){
    return -1;
  }else if(value1 > value2){
    return 1;
  }else{
    return 0;
  }
}

var values = [0,1,3,5,2];
values.sort(compare);
alert(values);

//比较函数的改进写法
function compare(value1,value2){
  return value1 - value2;
}
```



#### 操作方法

concat()

slice()

splice()



## Date 类型



## RegExp类型

正则表达式

`var expression =  / pattern / flags;`

g：表示全局(global)模式，即模式将被运用于所有字符串

i：表示不区别大小写模式

m：表示多行模式

```javascript
var pattern1 = /at/g; //匹配字符串中所有“at”的实例
var pattern2 = /[bc]at/i; //匹配第一个“bat”或“cat”的实例，不区分大小写
var pattern3 = /.at/gi; //匹配所有以“at”结尾的3个字符的组合，不区分大小写
```



### 实例方法

exec()：接受一个参数，即正则表达式的字符串，返回的是一个包含第一个匹配项信息的数组，如果没有匹配则返回 null

```javascript
var text = "mom and dad and baby";
var pattern = "/mom( and dad( and baby)?)?/gi";

var matches = pattern.exec(text);
```

对于 exec()方法而言，无论是否添加全局 g 标志，每次只会返回一个匹配项



test()：接受一个字符串参数，在模式与该参数匹配的情况下返回 true，否则返回 false。

```javascript
var text = "000-000-0000"
```

