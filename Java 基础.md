### 代码块
##### 普通代码块
在方法中写的代码块
##### 构造代码块
在类中定义的代码块
##### 静态代码块
使用 ***static*** 修饰的代码块
##### 同步代码块
使用 ***synchronized*** 修饰的代码块


```java
package com.learning.demo.mixed;

public class DaiMaKuaiDemo {
	
	public static void main(String[] args) {
		
	}

}

/**
 * 普通代码块、构造代码块、静态代码块、（同步代码块）
 * @author zhouzhiliwen
 *
 */
class DaiMaKuai{
	//静态代码块
	static {
		int i = 0;
	}
	
	private String name;
	
	//构造代码块
	{
		name = "xiaomign";
	}
	
	public void say(){
	    //普通代码块
		{
			int j = 0;
		}
		System.out.println("讲骚话");
	}
}

```

#### **注意：**
###### 构造代码块 
- 作用：初始化成员变量
- 注意的小知识点：
1. 变量初始化时，构造函数、构造代码块、初始化操作的执行顺序
2. 如果有构造函数的时候，则构造函数是最后执行的；
3. 如果没有构造函数，则构造代码块和初始化操作的执行顺序由代码的先后顺序决定

---

### 继承小结
1. 继承是发生在多个类之间
2. 继承使用关键字 extends
3. Java 是单继承（类），多实现（接口）
4. 被继承的类称为父类（超类），继承父类的类叫做子类（派生类）
5. 父类中的非私有属性和方法可以被子类继承
6. 用 protected 修饰的的属性和方法可以被子类继承
7. 构造方法不能够被继承
8. 创建对象时会调用构造方法，调用构造方法的不一定是创建的该类的对象。
9. 实例化子类对象，首先会调用父类的构造方法，如果父类中没有默认的构造方法（无参构造方法），那么子类必须使用 super 关键字显式调用父类的带参构造方法，而且 super 的语句必须在代码块中的第一行

---

### 重写
- 发生在具有继承关系的两个类之中
- 子类从父类继承过来的同名成员方法，根据子类的业务需求，进行相应的重新书写
- 使用@override注解之后，开启严格的重写检查
- 使用了@override注解之后，要求子类方法和父类方法除了方法体不同之外，其他必须一模一样（返回类型，权限操作符，参数列表）
- 子类抛出的异常不能超过父类抛出的异常（子类的异常不能大于父类的异常）
- 子类方法的访问权限等级不能小于父类方法的访问权限等级
- 用 private、final、static修饰的方法不能被重写

### 重载
- 发生在同一个类中，方法名相同，参数列表不同，与方法的返回值无关


### 重写和重载的区别
区别     | 重写                                     | 重载
---      | ---                                      | ---
有无继承 | 必须是继承extends中或者是实现implement中 | 必须是在同一个类内，没有继承关系
有无不同 | 方法声明必须一致，方法体可以不同         | 除了方法名一致，其他都不相同





---



### 多态
父类的引用指向子类的对象，称之为多态
接口的引用指向遵从接口的类对象，也是多态

使用接口作为方法的参数，可以传入的数据为遵从接口的类对象，或者遵从接口的子类对象

##### 多态使用注意事项
1. 多态情况下，父类的引用调用和子类同名的普通成员方法，使用的是子类的普通成员方法
2. 多态情况下，父类的引用调用和子类同名的普通成员变量，使用的是父类的普通成员变量
3. 多态情况下，父类的引用调用和子类同名的静态成员变量，使用的是父类的静态成员变量（不推荐使用，因为静态对象可以直接使用类名进行调用，和类对象没有关系）
4. 多态情况下，父类的引用不能够调用子类独有的成员变量和成员方法

---

### super 关键字
1. 可以使用 super 关键字调用父类中的属性
2. 可以使用 super 关键字调用父类中的方法

---

### final 关键字
1. 使用 final 声明一个变量，称为常量
2. 使用 final 声明一个方法，只能被子类继承（String 类），不能被子类重写
3. 使用 final 声明一个类，不能够被继承

---

### 抽象类
- 继承关系的时候，可以使用抽象类abstract来强制重写子类

1. 用abstract修饰的方法没有方法体，只有方法的声明。可以达到【必须重写】的作用

```java
格式
abstract public void Q();     <—  必须以分号结尾
```

2. 用abstract修饰的方法只能够存在于用abstract修饰的类（抽象类）中
3. 抽象类不能够有类对象。因为用abstract修饰的方法中没有方法体，使用类对象调用的话存在隐患
4. 如果一个abstract类中没有abstract方法，那么就不要用abstract修饰这个类。语法上允许，但是没有意义
- **总结**
- 如果一个类继承自用abstract修饰的抽象类，那么这个类必须完成其中所有使用abstract修饰的抽象方法

```java
abstract class Student{
	
	public abstract int getScore(int score);
	
}

class StudentImpl extends Student{

	@Override
	public int getScore(int score) {
		// TODO Auto-generated method stub
		return score;
	}
	
}

```

---

### 使用接口的注意事项
1. 接口中定义的成员方法都是abstract修饰的抽象方法，在实现接口的时候，必须重写所有接口中的抽象方法
2. 接口中定义的变量的缺省值是public static final修饰的，一旦在初始化的时候赋值之后就不能够进行改变
3. 接口遵从Java 单继承多实现的方式，一个类可以实现多个接口，用，隔开

- Java属于单继承（类）多实现（接口）的语言

- 接口中的成员变量的缺省属性为

```java
public static final
```

- 所以定义的时候必须提供初始化值且不能被改变

- 接口中的成员方法的缺省属性为abstract，即抽象类型

---

### 多态
- 父类的引用指向子类的对象，称之为多态
- 接口的引用指向遵从接口的类对象，也是多态

- 使用接口作为方法的参数，可以传入的数据为遵从接口的类对象，或者遵从接口的子类对象

- 多态使用注意事项
1. 多态情况下，父类的引用调用和子类同名的普通成员方法，使用的是子类的普通成员方法
2. 多态情况下，父类的引用调用和子类同名的普通成员变量，使用的是父类的普通成员变量
3. 多态情况下，父类的引用调用和子类同名的静态成员变量，使用的是父类的静态成员变量（不推荐使用，因为静态对象可以直接使用类名进行调用，和类对象没有关系）
4. 多态情况下，父类的引用不能够调用子类独有的成员变量和成员方法

---

### 异常处理
Throwable类是所有错误和异常的超类，包含了处理错误和异常的基本方法
- Error                  错误，无法处理，只能避免
- Exception         异常，需要进行对应处理

##### Throwable中常用的方法
构造方法 
1. Throwable()  
- 无参构造方法，在Throwable中保存的异常信息为null
2. Throwable(String message)
- 用message赋值给Throwable中的异常信息

##### 成员方法
1. String getMessage（）：得到Throwable中保存的异常信息字符串
2. String toString（）：返回Throwable中异常信息的概要
3. void printStackTrace（）：打印输出错误信息

##### 异常的处理方式
1. try - catch 捕获异常
2. throw & throws 抛出异常


##### 捕获异常：
1. 如果代码出现了异常，那么从异常位置开始，后面的代码将不会被执行
2. 如果使用了 try-catch 结构进行捕获异常，那么 JVM 会认为当前代码没有问题，其后的代码将会继续执行
3. 在 try-catch 结构中，可以使用多个 catch 块来捕获不同的异常
4. catch 异常的种类排布应该是小范围的在前，大范围的在后。否则会出现 unreachable code 的现象（大范围的异常已经捕获，所以不需要小范围的异常捕获，但是有风险，没有办法按照各种情况进行对应处理）



##### 抛出异常的关键字:   throw和throws
throw用来抛出异常，使用格式：

```
throw new 异常类型（message）；
```

一旦代码使用throw抛出异常，就需要在当前方法声明处用throws 来 标明可能抛出的异常类型

```
方法（参数） throws 异常类型
```



- 注意事项
1. 在代码中如果使用到 throw new 异常类型()抛出异常，则需要在方法声明的地方（方法名处）添加 throws 对应异常类型

```java
class A() throws NullPointerException {
	try{
		//……….
	}catch(NullPointerException ex){
		//……….
	}
}
```

2. 在同一个语句块/代码块中，只能抛出一个异常，因为从抛出异常的位置开始，之后的代码将不会被执行
3. 如果一个函数中，抛出了不同的异常，则需要在方法声明的位置添加相应的对应类型，用，隔开
4. 使用不同的代码块抛出不同的异常
5. 如果调用了一个有抛出异常的方法，那么在调用的时候，有两种选择：
	1. 继续抛出
	2. 捕获异常


##### 自定义异常：

```java
class 自定义异常类名(String message){
	super(message);
}
```



#####  try-catch- finally中如果含有 return 的情况
- . 在这里看到了try catch finally块中含有return语句时程序执行的几种情况，但其实总结的并不全，而且分析的比较含糊。但有一点是可以肯定的，finally块中的内容会先于try中的return语句执行，如果finall语句块中也有return语句的话，那么直接从finally中返回了，这也是不建议在finally中return的原因。下面来看这几种情况。


1. 情况一（try中有return，finally中没有return）：

``` java
public class TryTest{
	public static void main(String[] args){
		System.out.println(test());
	}
 
	private static int test(){
		int num = 10;
		try{
			System.out.println("try");
			return num += 80;
		}catch(Exception e){
			System.out.println("error");
		}finally{
			if (num > 20){
				System.out.println("num>20 : " + num);
			}
			System.out.println("finally");
		}
		return num;
	}
}
```

输出结果如下：
try
num>20 : 90
finally
90

分析：显然“return num += 80”被拆分成了“num = num+80”和“return num”两个语句，先执行try中的“num = num+80”语句，将其保存起来，在try中的”return num“执行前，先将finally中的语句执行完，而后再将90返回。



2. 情况二（try和finally中均有return）：

``` java
public class TryTest{
	public static void main(String[] args){
		System.out.println(test());
	}
 
	private static int test(){
		int num = 10;
		try{
			System.out.println("try");
			return num += 80;
		}catch(Exception e){
			System.out.println("error");
		}finally{
			if (num > 20){
				System.out.println("num>20 : " + num);
			}
			System.out.println("finally");
			num = 100;
			return num;
		}
	}
}
```

输出结果如下：

try
num>20 : 90
finally
100

分析：try中的return语句同样被拆分了，finally中的return语句先于try中的return语句执行，因而try中的return被”覆盖“掉了，不再执行。



3. 情况三（finally中改变返回值num）：

   ```java
   public class TryTest{
   	public static void main(String[] args){
   		System.out.println(test());
   	}
    
   	private static int test(){
   		int num = 10;
   		try{
   			System.out.println("try");
   			return num;
   		}catch(Exception e){
   			System.out.println("error");
   		}finally{
   			if (num > 20){
   				System.out.println("num>20 : " + num);
   			}
   			System.out.println("finally");
   			num = 100;
   		}
   		return num;
   	}
   }
   ```


输出结果如下：
try

finally

10

分析：虽然在finally中改变了返回值num，但因为finally中没有return该num的值，因此在执行完finally中的语句后，test（）函数会得到try中返回的num的值，而try中的num的值依然是程序进入finally代码块前保留下来的值，因此得到的返回值为10。



4. 但是我们来看下面的情况（将num的值包装在Num类中）：

   ``` java
   public class TryTest{
   	public static void main(String[] args){
   		System.out.println(test().num);
   	}
    
   	private static Num test(){
   		Num number = new Num();
   		try{
   			System.out.println("try");
   			return number;
   		}catch(Exception e){
   			System.out.println("error");
   		}finally{
   			if (number.num > 20){
   				System.out.println("number.num>20 : " + number.num);
   			}
   			System.out.println("finally");
   			number.num = 100;
   		}
   		return number;
   	}
   }
    
   class Num{
   	public int num = 10;
   }
   ```

   输出结果如下：



   try
   finally
   100

   从结果中可以看出，同样是在finally中改变了返回值num的值，在情况三中，并没有被try中的return返回（test（）方法得到的不是100），但在这里却被try中的return语句返回了。



对于含有return语句的情况，这里我们可以简单地总结如下：

```markdown
try语句在返回前，将其他所有的操作执行完，保留好要返回的值，而后转入执行finally中的语句，而后分为以下三种情况：

情况一：如果finally中有return语句，则会将try中的return语句”覆盖“掉，直接执行finally中的return语句，得到返回值，这样便无法得到try之前保留好的返回值。

情况二：如果finally中没有return语句，也没有改变要返回值，则执行完finally中的语句后，会接着执行try中的return语句，返回之前保留的值。

情况三：如果finally中没有return语句，但是改变了要返回的值，这里有点类似与引用传递和值传递的区别，分以下两种情况，：

    1）如果return的数据是基本数据类型或文本字符串，则在finally中对该基本数据的改变不起作用，try中的return语句依然会返回进入finally块之前保留的值。

    2）如果return的数据是引用数据类型，而在finally中对该引用数据类型的属性值的改变起作用，try中的return语句返回的就是在finally中改变后的该属性的值。
```



- throw 和 throws 的区别：

  1. throws 出现在方法声明上，而throw通常都出现在方法体内。

  2. throws 表示出现异常的一种可能性，并不一定会发生这些异常；throw则是抛出了异常，执行throw则一定抛出了某个异常对象。



  ##### 异常的分类

  1. 可查异常

     - 可查异常即必须进行处理的异常，要么try catch住,要么往外抛，谁调用，谁处理，比如 FileNotFoundException；如果不处理，编译器，就不让你通过

  2. 运行异常

     - 运行时异常RuntimeException指： 不是必须进行try catch的异常
     -  常见运行时异常: 
       1. 除数不能为0异常:ArithmeticException 
       2. 下标越界异常:ArrayIndexOutOfBoundsException 
       3. 空指针异常:NullPointerException

     - 在编写代码的时候，依然可以使用try catch throws进行处理，与可查异常不同之处在于，即便不进行try catch，也不会有编译错误 
     - Java之所以会设计运行时异常的原因之一，是因为下标越界，空指针这些运行时异常太过于普遍，如果都需要进行捕捉，代码的可读性就会变得很糟糕。

  3. 错误

     - 错误Error，指的是系统级别的异常，通常是内存用光了
     - 在默认设置下，一般java程序启动的时候，最大可以使用16m的内存
       如例不停的给StringBuffer追加字符，很快就把内存使用光了。抛出OutOfMemoryError
       与运行时异常一样，错误也是不要求强制捕捉的



  异常的分类

  ![异常的分类](/Users/zhouzhiliwen/Documents/电子书/学习笔记/img/异常的分类.png)



面试问题：运行时异常 RuntimeException，能否被捕捉？ 错误Error，能否被捕捉？运行时异常与非运行时异常的区别 ？

- RuntimeException如果使用try和catch异常可以被捕捉。
  错误Error 能够被捕捉
  运行时异常与非运行时异常的区别
  Throwable 是所有 Java 程序中错误处理的父类 ，有两种资类： Error 和 Exception 。
  Error ：表示由 JVM 所侦测到的无法预期的错误，由于这是属于 JVM 层次的严重错误 ，导致 JVM 无法继续执行，因此，这是不可捕捉到的，无法采取任何恢复的操作，顶多只能显示错误信息。
  Exception ：表示可恢复的例外，这是可捕捉到的。
  Java 提供了两类主要的异常 :runtime exception 和 checked exception 。 checked 异常也就是我们经常遇到的 IO 异常，以及 SQL 异常都是这种异常。 对于这种异常， JAVA 编译器强制要求我们必需对出现的这些异常进行 
  catch 。所以，面对这种异常不管我们是否愿意，只能自己去写一大堆 catch 块去处理可能的异常。
  但是另外一种异常： runtime exception ，也称运行时异常，我们可以不处理。当出现这样的异常时，总是由虚拟机 接管。比如：我们从来没有人去处理过 NullPointerException 异常，它就是运行时异常，并且这种异常还是最常见的异常之一。
  出现运行时异常后，系统会把异常一直往上层抛，一直遇到处理代码。如果没有处理块，到最上层，如果是多线程就由 Thread.run() 抛出 ，如果是单线程就被 main() 抛出 。抛出之后，如果是线程，这个线程也就退出了。如果是主程序抛出的异常，那么这整个程序也就退出了。运行时异常是 
  Exception 的子类，也有一般异常的特点，是可以被 Catch 
  块处理的。只不过往往我们不对他处理罢了。也就是说，你如果不对运行时异常进行处理，那么出现运行时异常之后，要么是线程中止，要么是主程序终止。
  如果不想终止，则必须扑捉所有的运行时异常，决不让这个处理线程退出。队列里面出现异常数据了，正常的处理应该是把异常数据舍弃，然后记录日志。不应该由于异常数据而影响下面对正常数据的处理。 
  在这个场景这样处理可能是一个比较好的应用，但并不代表在所有的场景你都应该如此。如果在其它场景，遇到了一些错误，如果退出程序比较好，这时你就可以不太理会运行时异常 
  ，或者是通过对异常的处理显式的控制程序退出。





---



### 排序算法

```java
// 冒泡排序
	public static void buddleSort(int[] arr) throws ArrayIllegalException {
        if (arr != null && arr.length != 0) {
            int temp = false;

            for(int i = 0; i < arr.length - 1; ++i) {
                for(int j = 0; j < arr.length - 1 - i; ++j) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }

            Helper.printArray(arr);
        } else {
            throw new ArrayIllegalException("输入的数组不合法！");
        }
    }

//选择排序
    public static void selectionSort(int[] arr) throws ArrayIllegalException {
        if (arr != null && arr.length != 0) {
            int temp = false;

            for(int i = 0; i < arr.length; ++i) {
                int index = i;

                for(int j = i + 1; j < arr.length; ++j) {
                    if (arr[j] < arr[index]) {
                        index = j;
                    }
                }

                if (index != i) {
                    int temp = arr[index];
                    arr[index] = arr[i];
                    arr[i] = temp;
                }
            }

            Helper.printArray(arr);
        } else {
            throw new ArrayIllegalException("输入的数组不合法！");
        }
    }

//插入排序
    public static void insertSort(int[] arr) throws ArrayIllegalException {
        if (arr != null && arr.length != 0) {
            for(int i = 1; i < arr.length; ++i) {
                int temp = arr[i];
                int j = false;

                int j;
                for(j = i - 1; j >= 0 && arr[j] > temp; --j) {
                    arr[j + 1] = arr[j];
                }

                if (arr[j + 1] != temp) {
                    arr[j + 1] = temp;
                }
            }

            Helper.printArray(arr);
        } else {
            throw new ArrayIllegalException("输入的数组不合法！");
        }
    }
```



#### 关于冒泡排序的问题

第一层循环是控制数组元素的比较次数
第二层循环循环条件为
```
for (int j = 0; j < arr.length - 1 - i; j++) 
```
依次递减，缩小比较的范围



#### 关于插入排序

插入排序的思路如下：
1. 外层循环控制循环次数
2. 内层循环控制比较的元素
3. 将要比较的值存入临时变量 temp
- 从后往前进行比较，如果发现前面的元素大于 temp，则将前面的元素后移一位；直到前面的元素小于 temp，则跳出循环
- 将 temp 和其原始下标的元素进行比较，如果不相等则将 temp 的值赋给其原始下标位置的元素

```java
public static void insertSort(int[] arr) {
	Helper.IsArrayLegal(arr);

	for (int i = 1; i < arr.length; i++) {
		int temp = arr[i];
		int j = 0;
		for(j = i - 1; j >= 0; j--) {
			if(arr[j] > temp) {
				arr[j+1] = arr[j];
			}else {
				break;
			}
		}
		if(temp != arr[j+1]) {
			arr[j+1] = temp;
		}
	}
	
	Helper.printArray(arr);
}
```

---

### 单例模式
分为饿汉和懒汉两种形式

饿汉式是立即加载的方式，无论是否会用到这个对象，都会加载。
如果在构造方法里写了性能消耗较大，占时较久的代码，比如建立与数据库的连接，那么就会在启动的时候感觉稍微有些卡顿。

懒汉式，是延迟加载的方式，只有使用的时候才会加载。 并且有线程安全的考量(鉴于同学们学习的进度，暂时不对线程的章节做展开)。
使用懒汉式，在启动的时候，会感觉到比饿汉式略快，因为并没有做对象的实例化。 但是在第一次调用的时候，会进行实例化操作，感觉上就略慢。

看业务需求，如果业务上允许有比较充分的启动和初始化时间，就使用饿汉式，否则就使用懒汉式



```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.learning.demo.mixed;

class HungryManSington {
    private static HungryManSington singleton;

    private HungryManSington() {
    }

    public static HungryManSington getInstance() {
        if (singleton == null) {
            singleton = new HungryManSington();
        }

        return singleton;
    }
}


//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.learning.demo.mixed;

class LazyManSington {
    private static LazyManSington singleton = new LazyManSington();

    private LazyManSington() {
    }

    public static LazyManSington getInstance() {
        return singleton;
    }
}

```



---

### static 关键字
##### static 静态变量：
用 static 关键字修饰的变量储存在内存的【数据区】中，而不是在【类对象】的【堆区】空间中
也就是说这个【静态成员变量】和【类对象】没有实际的“关系”，类对象对其只有使用权

用 static 修饰的【静态成员变量】可提供给多个类对象使用

static 的使用场景是存在大量重复数据的变量，可以大大节约内存的空间

如果一个方法使用 static 修饰，那么要调用这个方法的时候只需要 类名.方法名() 即可调用

用 static 修饰的成员变量是在类文件在内存中加载的时候就已经存在。所以，静态成员变量是早于类对象的生成而生成，晚于类对象的销毁而销毁。

用 static 修饰的静态变量值，无论使用哪一种方法修改变量的值，其他使用到这个变量的地方的值全部都改变（井里放块糖，有甜大家尝）

静态成员变量是一个【共享变量】

##### static 修饰的好处：
1. 方便调用，只需要类型就可以调用，不用创建对象；
2. 节省内存空间，不需要创建对象

缺点：
1. 变量占用内存的时间过长，如果不是经常使用到该变量的话，就会消耗资源，不如创建一个对象调用之后由 JVM 进行内存的回收

持久化问题：
可以使用 static 修饰的静态成员变量


##### 静态方法和静态成员变量使用时的注意事项：
1.使用 static 修饰的静态成员方法，不能够使用 this 关键字 —> 没有对象
——静态成员方法的调用有两种方式：
​	1.使用类对象调用（会报警告，should be called by a static way）
​	2.使用类名.方法名()进行调用
​	但是使用类名.方法名()进行调用的时候，是没有类对象的存在的。所以不能够使用 this 关键字，因为 this 关键字表示的是调用该方法的类对象。
​	同时，【静态成员变量】是早于类对象的创建而加载，晚于类对象的销毁而销毁。就算没有类对象的存在，静态成员方法依然存在。所以不能够使用 this 关键字

2.使用 static 修饰的静态成员方法，不能够调用普通的成员变量【非静态成员方法】 —-> 没有对象
——【非静态成员方法】是随着【类对象】的创建而保存在内存的【堆区】，但是在没有【类对象】之前，静态成员方法已经可以通过类名.方法名()进行调用，此时还没有【类对象】，也就没有【非静态成员变量】，所以静态成员方法是不可以调用【非静态成员方法】。

3.静态成员方法可以使用构造函数初始化一个属于自己的对象。 —> 案例：单例模式

类方法：指的是使用类名直接调用的方法 —> 静态成员方法
类变量：指的是使用类名直接可以使用的成员变量 —>静态成员变量 

---

### 关于重写

子类不能够覆盖父类的同名类方法
子类可以重写父类的成员方法

```java
public class Demo2 {
	
	public static void main(String[] args) {
		Father f = new Son();
		f.say(); // --> 123      子类不能够重写父类的类方法
		f.eat(); // --> 吃蔬菜   子类可以重写父类的成员方法
	}
	
}

class Father{
	public static void say() {
		System.out.println("123");
	}
	
	public void eat() {
		System.out.println("吃水果");
	}
}

class Son extends Father{
	public static void say() {
		System.out.println("456");
	}
	
	public void eat() {
		System.out.println("吃蔬菜");
	}
}

```

---

### 匿名内部类

匿名内部类

格式:
抽象类:

```
//这里创建的是一个抽象类的子类对象，隐含继承关系
抽象类类名 对象名 = new 抽象类类名() {
	@Override
	//抽象类中所有的抽象方法
};
```


接口：

```
接口类型 接口引用 = new 接口名() {
	//遵从接口的类，所有要实现的方法
	@Override
	// 完成接口中所有声明的方法
};
```


终极用法：内名内部类的匿名方法

```
new C() {
	@Override
	public void testC() {
		System.out.println("TestC 匿名内部类的匿名对象 success!");
	}
}.testC();
```

---

### 集合
Object 是 Java 所有类的直接父类或者间接父类，Object 类型的引用可以指向任何类型的对象【多态】

- 集合 Collection 的本质是一个接口

##### 接口
Collection 是集合中的总接口，规定了很多集合中的方法，需要【遵从】接口的实现类来完成
- —> List 接口，如果遵从 List 接口的实现，具有 List 接口的特征：有序可重复

- —> Set 接口，如果遵从 Set 接口的实现，具有 Set 接口的特征：无序不可重复

- 增：
  add(Object o)：添加一个元素到集合中，元素可以是任意类型
  addAll(Collection c) ： 添加另外一个集合到集合中

- 删：
  remove(Object o)：从集合中删除一个元素
  removeAll(Collection c)：从集合中删除另外一个集合
  clear()：清空整个集合

  retainAll(Collection c)：在集合中仅保留和另外一个集合的交集，其他元素全部删除

- 查：
  size()：返回集合的有效元素个数
  toArray()：把当前集合中的所有元素转成 Object 类型的数据返回



```java
package com.learning.demo.collection_study;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 集合的基本操作 增删查
 * @author zhouzhiliwen
 *
 */
public class Demo1 {
	public static void main(String[] args) {
		Collection c = new ArrayList();
		
		//增
		c.add("1");
		c.add("manman");
		c.add('A');
		c.add(true);
		c.add(2.0);
		c.add(2.0F);
		System.out.println(c);
		
		Collection c1 = new ArrayList();
		c1.add("Hello");
		c1.add("World");
		System.out.println(c1);  // [1, manman, A, true, 2.0, 2.0]
		
		c.addAll(c1);
		System.out.println(c); // [Hello, World]
		
		//删
//		c1.remove("Hello");
//		System.out.println(c1); //[World]
//		c.removeAll(c1);
//		System.out.println(c); // [1, manman, A, true, 2.0, 2.0, Hello
//		c.retainAll(c1);
//		System.out.println(c); //[World]
		
		
		//查
		System.out.println("Size: " + c.size());
		
		Object[] object = c.toArray();
		for (int i = 0; i < object.length; i++) {
			System.out.println(object[i]);
		}
	}
}

```






##### 集合的判断方法：
	- isEmpty()：判断集合是否为空
	- contain(Object o)：判断集合中是否包含指定的元素
	- containAll(Collection c)：判断集合中是否包含另外一个集合
	- equals(Object o)：判断两个集合时候相同

```java
package com.learning.demo.collection_study;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 集合的判断方法： 
 * isEmpty()：判断集合是否为空 
 * contain(Object o)：判断集合中是否包含指定的元素
 * containAll(Collection c)：判断集合中是否包含另外一个集合 
 * equals(Object o)：判断两个集合时候相同
 * 
 * @author zhouzhiliwen
 *
 */
public class Demo2 {
	public static void main(String[] args) {

		Collection c = new ArrayList();

		c.add("AKM");
		c.add("M4A1");
		c.add("98K");

		Collection c2 = new ArrayList();

		c2.add("AKM");
		c2.add("M4A1");
		c2.add("98K");
		
//		c2.clear();
//		System.out.println(c2.isEmpty());
		
		System.out.println(c.contains("98K"));
		
		System.out.println(c.containsAll(c2));
		
		System.out.println(c.equals(c2));

	}

}

```

- Object 类的 contains 方法比较的是元素的首地址
- Object 类的 equals 方法比较的也是元素的首地址
- 为了使这两个方法的使用符合业务逻辑，可以在对象类中对equals() 和 hashCode() 这两个方法进行重写。只要重写了一个就必须重写另一个
```java
    // 根据业务需要重写比较对象的方法 equals 和 hashcode 方法
	@Override
	public boolean equals(Object obj) {

		System.out.println("Person 类中重写的 equals 方法");
		Person person = (Person) obj;

		boolean ret = (this.getId() == person.getId()) && (this.getName().equals(person.getName()));

		return ret;

	}

	// 如果重写了 equals 方法，就必须重写 hashCode 方法
	@Override
	public int hashCode() {
		System.out.println("Person类中重写的 hashCode 方法");

		// super.hashCode()默认返回的是当前对象的首地址
		// 地址在内存中是不会重复的，是对象在内存中的唯一标识
		// 重写的要求：
		// 根据实际情况返回对象的【唯一值】，只要满足唯一性即可（id 等）
		return this.getId();
	}
```

- 注意：String是引用类型，所以String 类型的数据比较不能够使用 == 进行比较，而是需要使用 String 类的 equals 方法进行比较（已经经过重写）
    String 类型 == 比较的是元素的首地址

##### 迭代器
- Iterator()：获取当前集合的迭代器
- boolean hasNext()：判断是否有下一个元素
- Object next()：获取当前迭代器指向的元素，获取之后会指向下一个元素
- void remove()：删除当前迭代器通过 next() 获取到的元素
- 【注意】remove 方法需要和 next()方法配合使用，不能够单独使用，否则会有异常





---

### List接口
**特点：有序，可重复**

继承了父接口 Collection 中的基本方法

特有方法：
- 增加：
    - add(int index, Object o)		// 在集合的指定下标位置插入元素
	- addAll(int index, Collection c)		// 在集合的指定下标位置插入另外一个集合

- 获取：
	- Object get(int index)	//获取指定下标位置的元素
	- int indexOf(Object o)	//获取指定元素的下标位置
	- int lastIndexOf(Object o)		//获取指定元素最后一次出现的下标位置
	- List subList(int fromIndex, int toIndex)		//截取 List 集合，从 fromIndex 开始，到 toIndex 位置结束

- 修改：
	- set(int index, Object o)	//将指定下标位置的元素设为 o

- 迭代：
	- ListIterator ListIterator()	//获取迭代器


**在 Java 中，所有的区间范围都是要头不要尾。**
- List subList(2, 7);    —>   范围为下标2~6

- ※ ArrayList 默认的长度为10



- ListIterator ListIterator()	是 List 独有的迭代器
    - hasNext()
    - next()
    - remove()

- 特有方法：
    - add(Object o)		//在当前迭代器位置插入元素，其后元素依次后移
    - set(Object o)		//替换通过 next()方法获取到的元素
    - Int nextIndex()		//下一个元素的下标



###### ArrayList：

**底层是维护了一个 Object 类型的数组，表示 ArrayList 数据结构可以传入任意类型的数据**
**特征：**
- 查找快：
	- 因为 ArrayList 的底层实现是数组，可以使用数组的查找方式—下标机制来查找元素，如果找到了下标证明找到了这个元素

- 增加慢：
	- 因为在添加元素的时候，有可能导致 ArrayList 底层维护的数组元素个数不够，这时会去调用 grow(int minCapacity)方法，会新创建一个新的 Object 数组，并将原数组的元素进行一一拷贝，以达到对数组扩容的目的。这个拷贝的过程非常耗时。新数组的容量大约是原数组的1.5倍（涉及二进制位运算，原数组容量 >> 1   ==  原数组容量/2）（源代码：  int newCapacity = oldCapacity + (oldCapacity >> 1);）

- 删除慢：
	- 因为删除一个元素，会导致其后面的元素一次向前移动，设置到拷贝操作。如果数据量庞大，这个过程将会耗费很多的时间


- 特有方法：
    - ensureCapacity(int minCapacity)  —> 判断当前 ArrayList 里面保存的元素个数是否大于指定的 minCapacity 值

    - trimToSize() —> 截断操作，对底层维护的数组进行截断操作，让数组的容量等于当前数组中有效元素的个数


- ArrayList 构造方法：
    - ArrayList() 
	- —> 无参构造方法，如果调用该构造方法，则底层维护的 Object 类型数组的初始容量为10 
		
```
源代码中：
			private static final int DEFAULT_CAPACITY = 10;
```


- ArrayList(int capacity) —>
  - —>有参构造方法，传入的参数值为初始化时底层维护的 Object 类型的数组的初始容量



- 遍历 ArrayList 的三种方式：

  - 使用 for 循环

    ```java
    package collection;
     
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;
     
    import charactor.Hero;
     
    public class TestCollection {
     
        public static void main(String[] args) {
            List<Hero> heros = new ArrayList<Hero>();
     
            // 放5个Hero进入容器
            for (int i = 0; i < 5; i++) {
                heros.add(new Hero("hero name " + i));
            }
     
            // 第一种遍历 for循环
            System.out.println("--------for 循环-------");
            for (int i = 0; i < heros.size(); i++) {
                Hero h = heros.get(i);
                System.out.println(h);
            }
     
        }
     
    }
    ```

  - 使用增强型 for 循环

    - 使用增强型for循环可以非常方便的遍历ArrayList中的元素，这是很多开发人员的首选。

    - 不过增强型for循环也有不足：
      无法用来进行ArrayList的初始化
      无法得知当前是第几个元素了，当需要只打印单数元素的时候，就做不到了。 必须再自定下标变量


~~~java
```java
package collection;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import charactor.Hero;
 
public class TestCollection {
 
    public static void main(String[] args) {
        List<Hero> heros = new ArrayList<Hero>();
 
        // 放5个Hero进入容器
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero name " + i));
        }
 
        // 第三种，增强型for循环
        System.out.println("--------增强型for循环-------");
        for (Hero h : heros) {
            System.out.println(h);
        }
 
    }
 
}
```
~~~

  - 使用迭代器

    ```java
    package collection;
     
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;
     
    import charactor.Hero;
      
    public class TestCollection {
     
        public static void main(String[] args) {
            List<Hero> heros = new ArrayList<Hero>();
             
            //放5个Hero进入容器
            for (int i = 0; i < 5; i++) {
                heros.add(new Hero("hero name " +i));
            }
             
            //第二种遍历，使用迭代器
            System.out.println("--------使用while的iterator-------");
            Iterator<Hero> it= heros.iterator();
            //从最开始的位置判断"下一个"位置是否有数据
            //如果有就通过next取出来，并且把指针向下移动
            //直到"下一个"位置没有数据
            while(it.hasNext()){
                Hero h = it.next();
                System.out.println(h);
            }
            //迭代器的for写法
            System.out.println("--------使用for的iterator-------");
            for (Iterator<Hero> iterator = heros.iterator(); iterator.hasNext();) {
                Hero hero = (Hero) iterator.next();
                System.out.println(hero);
            }
             
        }
          
    }
    ```





###### LinkedList：
**底层维护的是一个双向链表**

**特征：增删快，查找慢**

- 特有方法：
    - getFirst()		//获取第一个元素
    - getLast()		//获取最后一个元素

    - addFirst(Object o)		//将元素添加到第一个位置
    - addLast(Object o)		//将元素添加到最后一个位置

    - removeFirst()		//删除第一个元素
    - removeLast()		//删除最后一个元素

**队列：先进先出 FIFO**
**栈：先进后出 FILO**



- 队列

  LinkedList 除了实现了List和Deque外，还实现了Queue接口(队列)。
  Queue是先进先出队列 FIFO，常用方法：

  - offer 在最后添加元素
  - poll 取出第一个元素
  - peek 查看第一个元素



```java
package collection;
  
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
  
import charactor.Hero;
  
public class TestCollection {
  
    public static void main(String[] args) {
        //和ArrayList一样，LinkedList也实现了List接口
        List ll =new LinkedList<Hero>();
          
        //所不同的是LinkedList还实现了Deque，进而又实现了Queue这个接口
        //Queue代表FIFO 先进先出的队列
        Queue<Hero> q= new LinkedList<Hero>();
          
        //加在队列的最后面
        System.out.print("初始化队列：\t");
        q.offer(new Hero("Hero1"));
        q.offer(new Hero("Hero2"));
        q.offer(new Hero("Hero3"));
        q.offer(new Hero("Hero4"));
          
        System.out.println(q);
        System.out.print("把第一个元素取poll()出来:\t");
        //取出第一个Hero，FIFO 先进先出
        Hero h = q.poll();
        System.out.println(h);
        System.out.print("取出第一个元素之后的队列:\t");
        System.out.println(q);
          
        //把第一个拿出来看一看，但是不取出来
        h=q.peek();
        System.out.print("查看peek()第一个元素:\t");
        System.out.println(h);
        System.out.print("查看并不会导致第一个元素被取出来:\t");
        System.out.println(q);
          
    }
       
}
```



- ArrayList 和 LinkedList 的区别：

  ArrayList 插入，删除数据慢
  LinkedList， 插入，删除数据快
  ArrayList是顺序结构，所以定位很快，指哪找哪。 就像电影院位置一样，有了电影票，一下就找到位置了。
  LinkedList 是链表结构，就像手里的一串佛珠，要找出第99个佛珠，必须得一个一个的数过去，所以定位慢





### Set 接口

**特点：无序，不可重复**

- 特有方法：
    - 之前在 Collection 中的接口方法，这里都适用
- 无序：添加顺序和展示顺序不同
- 不可重复：在 Set 集合中，不能够存在重复的元素。相同的元素只出现一次（可以用作去重）

> Set中的元素，没有顺序。 
> 严格的说，是没有按照元素的插入顺序排列
>
> HashSet的具体顺序，既不是按照插入顺序，也不是按照hashcode的顺序。



- —>HashSet
- —>TreeSet

1. HashSet 在添加元素的时候，每一次都会调用 hashCode() 方法
2. 重写 equals() 和 hashCode()方法之后，添加元素首先会调用 hashCode()方法，然后调用 equals()方法



##### HashSet

**【HashSet 的储存原理】**
**每次添加元素的时候都要调用 hashCode()方法，是为了计算当前元素在哈希表中的位置**

- HashSet 存储元素，首先要调用当前元素的 hashCode()方法，获取当前元素的 Hash 值，通过【移位】运算，计算当前元素在哈希表中应该存储的位置

    - 情况1：该位置没有元素  —> 直接插入
    - 情况2：该位置已经有其他元素  
        - —> HashSet就会调用该元素的 equals()方法，会和已经保存在哈希表中的元素进行比较
        - —> 如果是相同元素，那么将不会插入
    	- —> 如果是不同元素，那么将会插入
- 哈希表中的每一个“单元格”都是一个桶式结构，都可以保存多个元素，允许元素共存

```java
package com.learning.demo.collection_study;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * HashSet
 * @author zhouzhiliwen
 *
 */
public class Demo11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i = 0; i < 10; i++) {
			set.add(i);
		}
		
		System.out.println(set);
		
		set.add(4);
		
		System.out.println(set); //Set 中的元素是不可重复的
		
		/**
		 * Set遍历的两种方法
		 * 1. 迭代器
		 * 2. 增强型 for 循环
		 */
		
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()) {
			Integer i = it.next();
			System.out.println(i);
		}
		
		for (Integer i : set) {
			System.out.println(i);
		}
		
	}

}
```



- HashSet和HashMap的关系

> 通过观察HashSet的源代码
> 发现HashSet自身并没有独立的实现，而是在里面封装了一个Map.
>
> HashSet是作为Map的key而存在的
>
> 而value是一个命名为PRESENT的static的Object对象，因为是一个类属性，所以只会有一个。
>
> `private static final Object PRESENT = new Object();`

```java
package collection;
 
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
 
public class HashSet<E>
    extends AbstractSet<E>
    implements Set<E>, Cloneable, java.io.Serializable
{
    //HashSet里封装了一个HashMap
    private  HashMap<E,Object> map;
 
    private static final Object PRESENT = new Object();
 
    //HashSet的构造方法初始化这个HashMap
    public HashSet() {
        map = new HashMap<E,Object>();
    }
 
    //向HashSet中增加元素，其实就是把该元素作为key，增加到Map中
    //value是PRESENT，静态，final的对象，所有的HashSet都使用这么同一个对象
    public boolean add(E e) {
        return map.put(e, PRESENT)==null;
    }
 
    //HashSet的size就是map的size
    public int size() {
        return map.size();
    }
 
    //清空Set就是清空Map
    public void clear() {
        map.clear();
    }
     
    //迭代Set,就是把Map的键拿出来迭代
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }
 
}
```







##### TreeSet

- 【TreeSet 中添加自定义元素】
- 两种方式：
    1. 让自定义类遵从 Comparable 接口，实现 compareTo(Object o)方法
    2. 添加自定义比较器，这个比较器遵从 Comparable 接口，实现 int compare(Object o1, Object o2);



---



###  Map

Map 中存储的是键值对<K,V>的形式的数据

Map：双边队列是一个接口

 - HashMap<K,V>
 - TreeMap<K,V>



K: Key,键是唯一值，不可重复

V：Value，值可以重复，一个键(K)对应一个值(V)



增：

​	put(K key, V value) : 添加一个键值对

​	putAll(Map<? extends K, ? extends V> map) : 添加一个符合数据格式要求的 map 

删：

​	clear() : 清空所有的键值对

​	remove(Object key) : 根据指定的键删除对应的键值对

改：

​	put(K key, V value) : 当键存在是，这个操作相当于修改 K 值对应的 Value 值

查：

​	size() : 键值对的个数

​	get(Object key) : 通过指定的键获取对应的 Value 值

​	containsKey(Object key) : 判断指定的键是否存在于Map 中

​	containsValue(Object value) : 判断指定的 value 值是否存在



​	Set<K> keySet() : 返回 map 中所有的键(Key)的 Set 集合

​	Collection<V> values() : 返回 map 中所有的值(Value)对应的 Collection集合



```java
package com.learning.demo.collection_study;

import java.util.HashMap;
import java.util.Map;

/**
 * Map
 * 
 * @author zhouzhiliwen
 *
 */
public class Demo10 {
	public static void main(String[] args) {
		Map<String, MapObject> map = new HashMap<String, MapObject>();
		
		for(int i = 0; i < 10; i++) {
			map.put(i + "", new MapObject(i + ""));
		}
		
		System.out.println(map);
		
		System.out.println(map.get("2"));
		
		map.put("2", new MapObject("1000"));
		
		System.out.println(map.get("2"));
		
		map.put("10", new MapObject("1000"));
		
		System.out.println(map.keySet());
		
		System.out.println(map.values());
		
		map.clear();
		

	}

}

class MapObject {
	String str;

	public MapObject(String str) {
		super();
		this.str = str;
	}

	@Override
	public String toString() {
		return "MapObject [" + str + "]";
	}
}

```



---

### String 字符串

- 字符串即字符的组合，在Java中，字符串是一个类，所以我们见到的字符串都是对象 
  常见创建字符串手段： 

  1. 每当有一个字面值出现的时候，虚拟机就会创建一个字符串 

  2. 调用String的构造方法创建一个字符串对象 

  3. **通过+加号进行字符串拼接也会创建新的字符串对象**

     ``` java
     String str1 = "123";
     str1 = str1 + "456"; //不是同一个内存空间，str1的首地址已经改变。所以不是同一个对象
     ```


- 由于String 类是 final 修饰的，所以 String 类不能被继承

   


```
String str1 =“123”;
String str2 =“123”;

str1==str2        //True
```



- 使用直接赋值相同值的方式创建的变量 地址是同一个
- "123"存储在数据区中
- == 比较的是引用类型在内存中的地址，equals方法比较的是内容



##### 获取方法

- char charAt(int index)   ：获取指定下标位置的字符

- int length()  ：获取字符串的长度

- int indexOf(char ch)  ：获取某个字符在字符串中第一次出现的位置

- int lastIndexOf(char ch)  ：获取某个字符在字符串中最后一次出现的位置



##### 判断方法

- boolean endWith(String str)  ： 判断是否以指定字符串结尾
- boolean isEmpty()  ：判断是否为空串
- boolean contains(String str) ： 判断是否包含指定字符串
- boolean equals(String str) ： 判断字符串是否相等
- boolean equalsIgnoreCase(String str) ： 忽略大小写进行比较
- boolean startWith(String str) : 判断字符串是否由 str 子串开头
- boolean endWith(String str) : 判断字符串是否由 str 子串结尾



字符串的空串指的是“”而不是null    “”是字符串 null是地址

其他类型转换为字符串  在后面加个空串

```
int i = 1;
String str = i + ””;
```



##### 转换方法

- String(char[] arr)  ：将字符数组转换为字符串
- String(char[] arr, int offset, int length)
  - offset  偏移量 从哪一个位置开始截取字符
  - length  截取的长度

- static String valueOf (Object o)  ：将o的值转换为字符串
- static String valueOf (char[] arr, int offset, int length)

- char toCharArray(String str) ： 将字符串转换为字符数组



##### 其他方法

- String toUpperCase() - 全部转为大写
- String toLowerCase() - 全部转为小写
- String[] split(String str) - 切割字符串，将str子串作为标志(","等)，将字符串分割为小字符串，并将结果保存在字符串数组中
- String subString(int fromIndex, int toIndex) - 截取字符串，要头不要尾
- String subString(int fromIndex) - 从指定位置开始截取，到字符串的末尾
- String trim() - 去除空格



``` java
package com.learning.demo.string_stringbuffer_stringbuilder_study;

public class StringDemo {
	public static void main(String[] args) {
		String str1 = "the light";

		String str2 = new String(str1);

		// == 用于判断是否是同一个字符串对象
		System.out.println(str1 == str2);

		// 使用直接赋值相同值的方式创建的变量，地址是同一个，对象储存在数据区中
		String str3 = "the light";
		System.out.println(str1 == str3);

		String str4 = str1.toUpperCase();

		// ==用于判断是否是同一个字符串对象
		System.out.println(str1 == str2);

		System.out.println(str1.equals(str2));// 完全一样返回true

		System.out.println(str1.equals(str4));// 大小写不一样，返回false
		System.out.println(str1.equalsIgnoreCase(str4));// 忽略大小写的比较，返回true

		String start = "the";
		String end = "Ight";

		System.out.println(str1.startsWith(start));// 以...开始
		System.out.println(str1.endsWith(end));// 以...结束

	}

}

```



---

### StringBuffer

- append(String str)：在末尾追加内容 
- delete(int fromIndex, int toIndex)：删除指定范围内的字符
- insert(int index, String str)：在指定位置插入字符
- reverse()：反转
- length()：内容的长度
- capacity()：总空间



``` java
package com.learning.demo.string_stringbuffer_stringbuilder_study;

public class StringBufferDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "let there ";

		StringBuffer sb = new StringBuffer(str1); // 根据str1创建一个StringBuffer对象
		
        sb.append("be light"); // 在最后追加

		System.out.println(sb);

		sb.delete(4, 10);// 删除4-10之间的字符

		System.out.println(sb);

		sb.insert(4, "there ");// 在4这个位置插入 there

		System.out.println(sb);

		sb.reverse(); // 反转

		System.out.println(sb);
		
		System.out.println(sb.length()); //内容长度
        
        System.out.println(sb.capacity());//总空间
	}

}

```





- String - StringBuffer - StringBuilder

  - StringBuffer   线程安全
  - StringBuilder 线程不安全
  - StringBuilder（）默认容量为16个字符

---



### 接口中的默认方法（Java1.8之后）

```Java
interface DefaultMethod{
	public void say();
	
	default public void eat() {
		System.out.println("吃饭");
	}
}
```

这样每一个实现DefaultMethod接口的类都会自动继承eat()方法的实现



##### 同名方法冲突

```java

public class DefaultMethodDemo {
	public static void main(String[] args) {
		new DefaultMethodImpl().eat();
	}
}

interface DefaultMethodA {
	public void sayA();

	default public void eat() {
		System.out.println("A吃饭");
	}
}

interface DefaultMethodB extends DefaultMethodA {
	public void sayB();

	default public void eat() {
		System.out.println("B吃饭");
	}
}

class DefaultMethodImpl implements DefaultMethodB, DefaultMethodA {

	@Override
	public void sayA() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sayB() {
		// TODO Auto-generated method stub

	}

}
```
- 三条原则:

1. 类中的方法优先级最高

2. 如果无法依据第一条进行判断,那么子接口的优先级更高:函数签名相同时,优先选择拥有最具体实现的默认方法的接口,即如果B继承了A,那么B就比A更加具体

3. 最后,如果还是无法判断,继承了多个接口的类必须通过显示覆盖和调用期望的方法,显式地选择使用哪一个默认方法的实现

---
### UML 图
- 类图
![UML图-类图](/Users/zhouzhiliwen/Documents/电子书/学习笔记/img/UML图-类图.png)

- 接口图
![UML图-接口图](/Users/zhouzhiliwen/Documents/电子书/学习笔记/img/UML图-接口图.png)

- 继承关系图
![UML图-继承关系图](/Users/zhouzhiliwen/Documents/电子书/学习笔记/img/UML图-继承关系图.png)

- 实现关系图
![UML图-实现关系图](/Users/zhouzhiliwen/Documents/电子书/学习笔记/img/UML图-实现关系图.png)

---
### 包装类
- 所有的基本类型都有相对应的包装类类型

| 基本类型 | 包装类  | 占用内存（字节数，位数=字节数*8） |     类型     |
| :------: | :-----: | :-------------------------------: | :----------: |
|   byte   |  Byte   |                 1                 |    字节型    |
|  short   |  Short  |                 2                 |    短整型    |
|   int    | Integer |                 4                 |     整型     |
|   long   |  Long   |                 8                 |    长整型    |
|  float   |  Float  |                 4                 | 单精度浮点数 |
|  double  | Double  |                 8                 | 双精度浮点数 |
| boolean  | Boolean |              不确定               |    布尔型    |
|   char   |  Char   |                 2                 |    字符型    |




- 基本数据类型有八种：
	- 文本型：char 
	- 整数型：byte short int long 
	- 浮点型：float double 
	- 逻辑型：boolean 

* 基本类型储存在栈区中，所以他们的存取速度要比存放在堆区中的对应包装类的对象要快
* 所有基本类型（包括 void）的包装类都使用 final修饰，因此无法被继承，也无法对其中的方法进行重写

* 基本类型的优势：
数据存储相对简单，运算效率更高
* 包装类的优势：
自带方法丰富，集合的元素必须是对象类型。

```java
public class BaoZhuangLeiDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int i = 5;

		// 基本类型转换成封装类型
		Integer it = new Integer(i);

		// 封装类型转换成基本类型
		int i2 = it.intValue();

		// 自动转换就叫装箱
		Integer it2 = i;

		// 自动转换就叫拆箱
		int i3 = it;

		// int的最大值
		System.out.println(Integer.MAX_VALUE);
		
		// int的最小值
		System.out.println(Integer.MIN_VALUE);

	}

}
```

##### 数字和字符串转换
``` java
public static void main(String[] args) {

		/**
		 * 数字转字符串 
		 * 方法1： 使用String类的静态方法valueOf 
		 * 方法2： 先把基本类型装箱为对象，然后调用对象的toString
		 */
		int i = 5;

		// 方法1
		String str = String.valueOf(i);

		// 方法2
		Integer it = i;
		String str2 = it.toString();

		/**
		 * 字符串转数字 调用Integer的静态方法parseInt
		 */
		String str3 = "999";

		int i2 = Integer.parseInt(str);

}
```

---
### Math工具类
``` java
package com.learning.demo.mixed;

public class MathDemo {
	public static void main(String[] args) {
		float f1 = 5.4f;
		float f2 = 5.5f;
		// 5.4四舍五入即5
		System.out.println(Math.round(f1));
		// 5.5四舍五入即6
		System.out.println(Math.round(f2));

		// 得到一个0-1之间的随机浮点数（取不到1）
		System.out.println(Math.random());

		// 得到一个0-10之间的随机整数 （取不到10）
		System.out.println((int) (Math.random() * 10));
		// 开方
		System.out.println(Math.sqrt(9));
		// 次方（2的4次方）
		System.out.println(Math.pow(2, 4));

		// π
		System.out.println(Math.PI);

		// 自然常数
		System.out.println(Math.E);
	}
}

```

---

### 格式化输出

- 如果不使用格式化输出，就需要进行字符串连接，如果变量比较多，拼接就会显得繁琐
  使用格式化输出，就可以简洁明了

  %s 表示字符串
  %d 表示数字
  %n 表示换行



  ``` java
  package com.learning.demo.mixed;
  
  import java.util.Locale;
  
  public class GeShiHuaShuChuDemo {
  
  	public static void main(String[] args) {
  		String name = "盖伦";
  		int kill = 8;
  		String title = "超神";
  
  		// 直接使用+进行字符串连接，编码感觉会比较繁琐，并且维护性差,易读性差
  		String sentence = name + " 在进行了连续 " + kill + " 次击杀后，获得了 " + title + " 的称号";
  
  		System.out.println(sentence);
  
  		// 使用格式化输出
  		// %s表示字符串，%d表示数字,%n表示换行
  		String sentenceFormat = "%s 在进行了连续 %d 次击杀后，获得了 %s 的称号%n";
  		System.out.printf(sentenceFormat, name, kill, title);
  
  		// 使用printf格式化输出
  		System.out.printf(sentenceFormat, name, kill, title);
  		// 源码
  		// public PrintStream printf(String format, Object ... args) {
  		// return format(format, args);
  		// }
  
  		// 使用format格式化输出
  		System.out.format(sentenceFormat, name, kill, title);
  
  		/**
  		 * 换行符
  		 * 
  		 * 换行符就是另起一行 --- '\n' 换行（newline） 回车符就是回到一行的开头 --- '\r' 回车（return）
  		 * 在eclipse里敲一个回车，实际上是回车换行符 Java是跨平台的编程语言，同样的代码，可以在不同的平台使用，比如Windows,Linux,Mac
  		 * 然而在不同的操作系统，换行符是不一样的 （1）在DOS和Windows中，每行结尾是 “\r\n”； （2）Linux系统里，每行结尾只有 “\n”；
  		 * （3）Mac系统里，每行结尾是只有 "\r"。
  		 * 
  		 * 为了使得同一个java程序的换行符在所有的操作系统中都有一样的表现，使用%n，就可以做到平台无关的换行
  		 */
  		System.out.printf("这是换行符%n");
  		System.out.printf("这是换行符%n");
  
  		// 其他格式化
  		int year = 2020;
  		// 总长度，左对齐，补0，千位分隔符，小数点位数，本地化表达
  
  		// 直接打印数字
  		System.out.format("%d%n", year);
  		// 总长度是8,默认右对齐
  		System.out.format("%8d%n", year);
  		// 总长度是8,左对齐
  		System.out.format("%-8d%n", year);
  		// 总长度是8,不够补0
  		System.out.format("%08d%n", year);
  		// 千位分隔符
  		System.out.format("%,8d%n", year * 10000);
  
  		// 小数点位数
  		System.out.format("%.2f%n", Math.PI);
  
  		// 不同国家的千位分隔符
  		System.out.format(Locale.FRANCE, "%,.2f%n", Math.PI * 10000);
  		System.out.format(Locale.US, "%,.2f%n", Math.PI * 10000);
  		System.out.format(Locale.UK, "%,.2f%n", Math.PI * 10000);
  
  	}
  
  }
  ```

---

  ### Lambda 表达式

  也称为闭包，允许吧函数作为一个方法的参数，或者将代码看做数据。Lambda表达式用于简化 Java 中接口的匿名内部类，被称作函数接口式的概念。函数式接口就是一个具有一个方法的普通接口，这样的接口可以隐式的转换为lambda 表达式。

  适用于只有一个抽象方法的接口

  ``` java
  IEat ieat3 = () -> {System.out.println("吃火锅");};
  
  ieat3.eat();
  
  
  
  //只有一个方法的接口
  
  interface IEat{
  
  	void eat();
  
  }
  ```





  ``` java
  package com.learning.demo.mixed;
  
  public class LambdaDemo {
  
  	public static void main(String[] args) {
  
  		// 使用接口实现类的方式调用方法
  		/*
  		 * IEat ieat1 = new IEatImpl(); ieat1.eat();
  		 */
  
  		// 使用匿名内部类的方式调用方法
  		/*
  		 * IEat ieat2 = new IEat(){
  		 * 
  		 * @Override public void eat() { System.out.println("吃水果"); } }; ieat2.eat();
  		 */
  
  		// 使用 lambda 表达式的方式调用方法（无参数）
  		/*
  		 * IEat ieat3 = () -> {System.out.println("吃火锅");}; ieat3.eat();
  		 */
  
  		// 使用 lambda 表达式的方式调用方法（带参数）
  		IEat ieat3 = (String food) -> {
  			System.out.println("吃" + food);
  		};
  		ieat3.eat("牛肉");
  
  	}
  
  }
  
  // 只有一个方法的接口
  interface IEat {
  	void eat(String food);
  }
  
  // 接口的实现类
  /*
   * class IEatImpl implements IEat{
   * 
   * @Override public void eat() { System.out.println("吃米饭"); }
   * 
   * }
   */
  
  ```



---



  ### 值传递和引用传递

  ``` java
  package com.learning.demo.mixed;
  
  /**
   * 值传递和引用传递
   * 值传递：变量中存储的是基本数据类型的数据
   * 引用传递：变量中存储的是引用数据类型的数据
   * 
   * @author zhouzhiliwen
   *
   */
  
  public class ValueAndRefDemo {
  
  	public static void main(String[] args) {
  		// TODO Auto-generated method stub
  		
  		//值传递
  		int i = 10;
  		method1(i);
  		System.out.println("值传递，i = " + i);
  		
  		//引用传递
  		Duck duck = new Duck();
  		System.out.println("duck.i = " + duck.i);
  		method2(duck);
  		System.out.println("引用传递，i = " + duck.i);
  		
  		//String类型的传递
  		String name = "小明";
  		method3(name);
  		System.out.println("String 类型传递， name = " + name);
  		
  		//String 类型的传递2
  		Person person = new Person();
  		System.out.println("person.age = " + person.age);
  		method4(person);
  		System.out.println("对象中的 String 类型传递，person.age = " + person.age);
  		
  	}
  	
  	//值传递
  	public static void method1(int mx) {
  		mx = 20;
  	}
  	
  	//引用传递
  	public static void method2(Duck d) {
  		d.i = 20;
  	}
  	
  	//String 类型传递
  	/**
  	 * String类型的传递比较特殊，是因为他改变的是临时变量 nameStr 的引用地址，
  	 * 而字符串"小王"本来就是一个对象，将nameStr 的引用地址变成了"小王"的地址
  	 * 但是最后输出的仍然是"小明"的地址，所以没有发生改变。主要的原因就是字符串
  	 * 本来也是一个对象
  	 * 
  	 */
  	public static void method3(String nameStr) {
  		nameStr = "小王";
  	}
  	
  	
  	public static void method4(Person p) {
  		p.age = 20;
  	}
  
  }
  
  //引用类型传递
  class Duck{
  	int i = 10;
  }
  
  //String类型传递
  class Person{
  	int age = 10;
  }
  
  ```

---

### Arrays工具类：



- 使用二分法查找：

Arrays.binarySearch(int[] array, int value)



- 数组内容转成字符串：

Arrays.toString(int[] array)



- 数组排序：（底层是快速排序）

Arrays.sort(int[] array)



- 复制指定的数组：

Arrays.copyOf(int[] array, int length)

Arrays.copyOf(int[] array, int from, int to)



- 判断两个数组是否相等：

Arrays.equals(int[] array1, int[] array2)



- 使用指定元素填充数组：

Arrays.fill(int[] array, int fill);





---



### 面向对象



面向对象：以对象为单位，将一种事务转换为一个对象。Java 是一门纯面向对象的语言

面向过程：针对于过程，专注于实现的步骤C 和 Cpp 是面向过程的语言。



类：是一个拥有相同特性（属性）和行为（方法）的事物的集合

对象：是一个具体的实现，拥有特定的属性和方法



类：人类（抽象）

对象：一个叫做小明的人（具体）



---



### 二叉树

树是一种非常重用的非线性结构。

二叉树是每个节点最多只能有两个子树的有序树，子树被称为“左子树”和“右子树”

二叉树的排序规则：

1.选择第一个元素为根节点

2.之后如果元素大于根节点，则放在右子树；如果元素小于根节点，则放在左子树

3.最后按照中序排序进行输出，则可以得出排序的结果（左-根-右）



```java
package com.learning.demo.mixed;

/**
 * 二叉树：
 * 树是一种非常重用的非线性结构。
 * 二叉树是每个节点最多只能有两个子树的有序树，子树被称为“左子树”和“右子树”
 * 二叉树的排序规则：
 * 1.选择第一个元素为根节点
 * 2.之后如果元素大于根节点，则放在右子树；如果元素小于根节点，则放在左子树
 * 3.最后按照中序排序进行输出，则可以得出排序的结果（左-根-右）
 * 
 * 中序排序是已经排完大小关系的排序
 * 
 * @author zhouzhiliwen
 *
 */
public class BinaryTreeDemo {
	
	public static void main(String[] args) {
		
		BinaryTree bt = new BinaryTree();
		bt.add(8);
		bt.add(3);
		bt.add(2);
		bt.add(9);
		bt.add(6);
		bt.add(0);
		bt.add(1);
		
		bt.print();
		
	}
	
}

class BinaryTree{
	
	private Node root;
	
	//添加节点
	public void add(int data) {
		if(null == root) {
			root = new Node(data);
		}else {
			root.addNode(data);
		}
	}
	
	public void print() {
		root.printNode();
	}
	
	/**
	 * 提供节点的内部类
	 * @author zhouzhiliwen
	 *
	 */
	private class Node{
		private int data;
		private Node left;
		private Node right;
		
		public Node(int data) {
			this.data = data;
		}
		
		//根据元素和根节点的大小关系判断应该插入左子树（小于）还是右子树（大于）
		//添加节点
		public void addNode(int data) {
			if(this.data > data) {
				if(null == this.left) {
					this.left = new Node(data);
				}else {
					this.left.addNode(data);
				}
			}else {
				if(null == this.right) {
					this.right = new Node(data);
				}else {
					this.right.addNode(data);
				}
			}
		}
		
		public void printNode() {
			System.out.println("中序排序：");
			printNodeMiddle();
			System.out.println("Done.");
			System.out.println("前序排序：");
			printNodeFront();
			System.out.println("Done.");
			System.out.println("后序排序：");
			printNodeBack();
			System.out.println("Done.");
			
		}
		
		//按照中序遍历（左-根-右）的方法输出
		public void printNodeMiddle() {
			//判断左子树是否为空，如果不为空，继续判断
			if(null != this.left) {
				this.left.printNodeMiddle();
			}
			//输出根节点
			System.out.print(this.data + " -> ");
			//判断右子树是否为空，如果不为空，继续判断
			if(null != this.right) {
				this.right.printNodeMiddle();
			}
		}
		
		//按照前序遍历（根-左-右）的方法输出
		public void printNodeFront() {
			System.out.print(this.data + " -> ");
			if(null != this.left) {
				this.left.printNodeFront();
			}
			
			if(null != this.right) {
				this.right.printNodeFront();
			}
		}
		
		//按照后序遍历（左-右-根）的方法输出
		public void printNodeBack() {
			if(null != this.left) {
				this.left.printNodeBack();
			}
			if(null != this.right) {
				this.right.printNodeBack();
			}
			System.out.print(this.data + " -> ");
		}
		
	}

}

```





---

### System 和 Runtime 工具类

#### System 类

- 成员变量

  - 有三个，in（标准输入流，键盘），out（标准输出流，显示器），err（标准错误输出流）

- 成员方法

  - public static void arraycopy(Object src, int srcPos,Object dest,int destPos,int length)

    - 该方法是将数组拷贝，将一个数组中的内容复制到另一个数组的指定位置；由于该方法是 native 方法，所以性能上比循环高效

  - public static long currentTimeMills()

    - 该方法是返回当前计算机的时间，时间的表达方式是从1970-01-01 00：00：00到当前时间的毫秒数

  - public static void exit(int status)

    - 该方法的作用是退出程序,其中 status 的值为0表示正常退出，非0表示异常退出。

  - public static void gc()

    - 该方法的作用是请求系统进行垃圾回收，但是系统不会立即进行回收，还是会根据垃圾回收算法的实现进行出来

  - public static String getProperties(String key)

    - 该方法是获取系统中属性为 Key 的属性值

      java.version 

      java.home

      os.name

      os.version

      user.name

      user.home : 用户的主目录

      user.dir ： 用户的当前工作路径

```java
package com.learning.demo.system_api_study;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Demo1 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); //扫描器配合标准输入流使用
		sc.close();
		
		System.out.println("输入"); 
		
		System.err.println("错误");
		
		int[] arr = {1,2,3,4,5};
		
		int[] arr2 = new int[arr.length];
		
		System.arraycopy(arr, 0, arr2, 0, 3);// 原始数组，原始数组的起始位置，目标数组，目标数组的起始位置，复制的长度
		
		for(int i = 0; i < arr2.length; i++) {
			System.out.println(arr2[i]);
		}
		
		System.out.println(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String DateString = sdf.format(System.currentTimeMillis());
		System.out.println(DateString);
		
		
	}
}
```



#### Runtime 类

- exec()：执行某个应用程序





---



### MD5工具类

全称为（信息-摘要算法）

md5算法 SHA-1算法（哈希）

a-z A-Z 0-9 /* BASE64编码算法



``` java
package com.learning.demo.mixed;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class MD5Demo {

	public static void main(String[] args) {
		
		String password = "admin12345"; //明文存储
		String savePassword = "dIjjMbi2TleU2j+k6xCtXQ=="; //密文存储
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			//根据 MD5 计算摘要（该过程不可逆）
			byte[] bytes = md.digest(password.getBytes("UTF-8"));
			
			System.out.println(Arrays.toString(bytes));
			
			String md5Str = new String(bytes);
			System.out.println(md5Str);
			
			//a-z A-Z 0-9 /* BASE64编码算法
			//jdk1.8之后
			String enStr = Base64.getEncoder().encodeToString(bytes); //编码
			System.out.println(enStr);
			
			byte[] deStr = Base64.getDecoder().decode(enStr.getBytes()); //解码
			System.out.println(Arrays.toString(deStr));
			
			//jdk1.8之前，需要配置权限，在 properties 中配置访问 rt.jar 中的权限	
			
			
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

```





---



### 字符

``` java
		// Character常见方法
		System.out.println(Character.isLetter('a'));//判断是否为字母
        System.out.println(Character.isDigit('a')); //判断是否为数字
        System.out.println(Character.isWhitespace(' ')); //是否是空白
        System.out.println(Character.isUpperCase('a')); //是否是大写
        System.out.println(Character.isLowerCase('a')); //是否是小写
         
        System.out.println(Character.toUpperCase('a')); //转换为大写
        System.out.println(Character.toLowerCase('A')); //转换为小写
 
        //String a = 'a'; //不能够直接把一个字符转换成字符串
        String a2 = Character.toString('a'); //转换为字符串
```



---



### Date 类

``` java
package com.learning.demo.mixed;

import java.util.Date;

public class DateDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 当前时间
		Date d1 = new Date();
		System.out.println("当前时间:");
		System.out.println(d1);
		System.out.println();
		// 从1970年1月1日 早上8点0分0秒 开始经历的毫秒数
		Date d2 = new Date(5000);
		System.out.println("从1970年1月1日 早上8点0分0秒 开始经历了5秒的时间");
		System.out.println(d2);

		// 注意：是java.util.Date;
		// 而非 java.sql.Date，此类是给数据库访问的时候使用的
		Date now = new Date();
		// 打印当前时间
		System.out.println("当前时间:" + now.toString());
		
        // getTime() 得到一个long型的整数
		// 这个整数代表 1970.1.1 08:00:00:000，每经历一毫秒，增加1
		System.out.println("当前时间getTime()返回的值是：" + now.getTime());

		Date zero = new Date(0);
		System.out.println("用0作为构造方法，得到的日期是:" + zero);

		// 当前日期的毫秒数
		System.out.println("Date.getTime() \t\t\t返回值: " + now.getTime());
		
        // 通过System.currentTimeMillis()获取当前日期的毫秒数
		System.out.println("System.currentTimeMillis() \t返回值: " + System.currentTimeMillis());

	}

}

```



### SimpleDateFormat

``` java
package com.learning.demo.mixed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// y 代表年
		// M 代表月
		// d 代表日
		// H 代表24进制的小时
		// h 代表12进制的小时
		// m 代表分钟
		// s 代表秒
		// S 代表毫秒
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		Date d = new Date();
		String str = sdf.format(d);
		System.out.println("当前时间通过 yyyy-MM-dd HH:mm:ss SSS 格式化后的输出: " + str);

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = new Date();
		String str1 = sdf1.format(d1);
		System.out.println("当前时间通过 yyyy-MM-dd 格式化后的输出: " + str1);

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		String str2 = "2016/1/5 12:12:12";

		try {
			Date d2 = sdf2.parse(str2);
			System.out.printf("字符串 %s 通过格式  yyyy/MM/dd HH:mm:ss %n转换为日期对象: %s", str2, d2.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

```





### Calandar

``` java
package com.learning.demo.mixed;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalandarDemo {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		// 采用单例模式获取日历对象Calendar.getInstance();
		Calendar c = Calendar.getInstance();

		// 通过日历对象得到日期对象
		Date d = c.getTime();

		Date d2 = new Date(0);
		c.setTime(d2); // 把这个日历，调成日期 : 1970.1.1 08:00:00

		// 当前日期
		System.out.println("当前日期：\t" + format(c.getTime()));

		Date now = c.getTime();

		// 下个月的今天
		c.setTime(now);
		c.add(Calendar.MONTH, 1);
		System.out.println("下个月的今天:\t" + format(c.getTime()));

		// 去年的今天
		c.setTime(now);
		c.add(Calendar.YEAR, -1);
		System.out.println("去年的今天:\t" + format(c.getTime()));

		// 上个月的第三天
		c.setTime(now);
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DATE, 3);
		System.out.println("上个月的第三天:\t" + format(c.getTime()));

	}

	private static String format(Date time) {
		return sdf.format(time);
	}

}

```



---

### Collections工具类



- reverse ： 使List中的数据发生翻转
- shuffle : 混淆List中数据的顺序
- sort ： 对List中的数据进行排序
- swap : 交换两个数据的位置
- rotate : 把List中的数据，向右滚动指定单位的长度
- synchronizedList : 把非线程安全的List转换为线程安全的List。

```java
package com.learning.demo.collection_study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections 工具类
 * 
 * @author zhouzhiliwen
 *
 */
public class CollectionsDemo {
	public static void main(String[] args) {
		// 初始化集合numbers
		List<Integer> numbers = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			numbers.add(i);
		}

		System.out.println("集合中的数据:");
		System.out.println(numbers);

		Collections.reverse(numbers);

		System.out.println("翻转后集合中的数据:");
		System.out.println(numbers);

		Collections.shuffle(numbers);

		System.out.println("混淆后集合中的数据:");
		System.out.println(numbers);

		Collections.sort(numbers);
		System.out.println("排序后集合中的数据:");
		System.out.println(numbers);

		Collections.swap(numbers, 0, 5);
		System.out.println("交换0和5下标的数据后，集合中的数据:");
		System.out.println(numbers);

		Collections.rotate(numbers, 2);
		System.out.println("把集合向右滚动2个单位，标的数据后，集合中的数据:");
		System.out.println(numbers);

		System.out.println("把非线程安全的List转换为线程安全的List");
		List<Integer> synchronizedNumbers = (List<Integer>) Collections.synchronizedList(numbers);
	}
}

```







### 流



- 文件的输入输出流：
  - 比如读取文件的数据到程序中，站在程序的角度来看，就叫做输入流
    - 输入流： InputStream
    - 输出流：OutputStream



- 流的输入输出是针对程序而言的：

  ​	程序的写入（到硬盘中）：输出流 OutputStream

  ​	程序的读取（从硬盘中读取数据）：输入流 InputStream



#### 字节流

​	用于以字节的形式读取和写入数据

- InputStream字节输入流 
- OutputStream字节输出流 




**InputStream是字节输入流，同时也是抽象类，只提供方法声明，不提供方法的具体实现。**
**FileInputStream 是InputStream子类，以FileInputStream 为例进行文件读取**

``` java
public static void main(String[] args) {
        try {
            //准备文件lol.txt其中的内容是AB，对应的ASCII分别是65 66
            File f =new File("d:/lol.txt");
            //创建基于文件的输入流
            FileInputStream fis =new FileInputStream(f);
            //创建字节数组，其长度就是文件的长度
            byte[] all =new byte[(int) f.length()];
            //以字节流的形式读取文件所有内容
            fis.read(all);
            for (byte b : all) {
                //打印出来是65 66
                System.out.println(b);
            }
             
            //每次使用完流，都应该进行关闭
            fis.close();
              
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
```



**OutputStream是字节输出流，同时也是抽象类，只提供方法声明，不提供方法的具体实现。**
**FileOutputStream 是OutputStream子类，以FileOutputStream 为例向文件写出数据**

**注: 如果文件d:/lol2.txt不存在，写出操作会自动创建该文件。** 
**但是如果是文件 d:/xyz/lol2.txt，而目录xyz又不存在，会抛出异常**



``` java
public static void main(String[] args) {
    try {
        // 准备文件lol2.txt其中的内容是空的
        File f = new File("d:/lol2.txt");
        // 准备长度是2的字节数组，用88,89初始化，其对应的字符分别是X,Y
        byte data[] = { 88, 89 };

        // 创建基于文件的输出流
        FileOutputStream fos = new FileOutputStream(f);
        // 把数据写入到输出流
        fos.write(data);
        // 关闭输出流
        fos.close();

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

}
```



- 方法合集

``` java
package com.learning.demo.file_study;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File 类的常用操作
 * @author zhouzhiliwen
 * 
 */
public class Demo1 {

	public static void main(String[] args) throws IOException {
		//File 类的判断方法
		System.out.println("##############File 类的判断方法#################");
		File file1 = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test");
		boolean ret = false;
		
		ret = file1.isDirectory(); //判断是否为目录
		System.out.println("isDirectory(): " + ret);
		
		ret = file1.isFile(); //判断是否为文件
		System.out.println("isFile(): " + ret);
		
		ret = file1.exists(); //判断文件或文件夹是否存在
		System.out.println("exists(): " + ret);
		
		/**
		 * 相对路径：相对于当前文件而言，目标文件的路径
		 * 绝对路径：相对于硬盘而言，目标文件的路径
		 * 
		 */
		ret = file1.isAbsolute(); //判断当前路径是否为绝对路径
		System.out.println("isAbsolute(): " + ret);
		
		ret = file1.isHidden(); //判断当前文件是否为隐藏文件
		System.out.println(ret);
		
		
		//File类的操作方法
		System.out.println("##############File类的操作方法#################");
		
		File file2 = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test/test1.txt");
//		ret = file2.createNewFile(); //创建文件 
//		System.out.println("createNewFile(): " + ret);
		
		File file3 = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test/test2.bat");
		ret = file2.renameTo(file3); //重命名
		System.out.println("renameTo(): " + ret);
		
		// 移动文件或文件夹
		ret = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test/test2.bat")
				.renameTo(new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test/123/test2.bat"));
		System.out.println(ret);
		
		ret = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test/abc").mkdir(); //创建目录
		System.out.println("mkdir(): " + ret);
		
		/**
		 * mkdir() 和 mkdirs()的区别：
		 * mkdir():创建目录。如果当前指定的目录无效，则无法创建，并返回 false；
		 * mkdirs():创建目录。如果当前指定的目录无效，则创建所有目录并返回 true；
		 * 
		 */
		ret = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test/abc/def/ghi").mkdir();
		System.out.println("mkdir(): " + ret);
		
		ret = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test/abc/def/ghi").mkdirs();
		System.out.println("mkdirs(): " + ret);
			
		ret = file3.delete(); //删除指定的文件或文件夹。
		System.out.println(ret);
		
		ret = file1.delete(); //删除指定的文件或文件夹。如果是文件夹，则只能够删除非空的文件夹
		System.out.println(ret);
		
		ret = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test/abc/def/ghi").delete();
		System.out.println(ret);
		
		//在程序退出的时候删除指定文件或文件夹(可以用来清除程序运行时生成的临时文件)
		new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test/123/test2.bat").deleteOnExit();
		
		
		
		//File 类获取文件或文件夹属性的方法(都是假的，其实都是 String 类型的方法，只是将传入的路径字符串进行截断等字符串操作)
		System.out.println("##############File 类获取文件或文件夹属性的方法#################");
		String location = "/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test/abc/def/ghi";
		
		File file4 = new File(location);
		
		String str1 = file4.getPath(); //获取当前文件或文件夹的路径
		System.out.println(str1);
		
		String str2 = file4.getAbsolutePath(); //获取当前文件或文件夹的绝对路径
		System.out.println(str2);
		
		String str3 = file4.getName(); //获取当前文件或文件夹的名称
		System.out.println(str3);
		
		String str4 = file4.getParent(); //获取当前文件或文件夹的上级目录
		System.out.println(str4);
		
		//以下方法是获取文件或文件夹的真实信息
		File file5 = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/File_test/abc/Test.txt");
//		file5.createNewFile();
		
		long time = file5.lastModified();//获取文件或文件夹最后一次被修改的时间
//		System.out.println(time);
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
		System.out.println(sdf.format(date));
		
		long length = file5.length(); //获取文件的大小
		System.out.println(length);
		
		//
		System.out.println("##############系统对文件夹的操作#################");
		
		File[] list1 = File.listRoots(); //获取系统中的盘符
		for (File file : list1) {
			System.out.println(file);
		}
		
		String[] list2 = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java")
				.list(); //获取指定文件夹中的所有文件和文件夹的名称
		
		for (String string : list2) {
			System.out.println(string);
		}
		
		File[] list3 = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/")
				.listFiles(); //获取指定文件夹中的所有文件和文件夹的 File 对象
		
		for (File file : list3) {
			System.out.println(file);
//			file.delete();
		}
	}

}
```



#### 字符流

- Reader字符输入流 
- Writer字符输出流 
- 专门用于字符的形式读取和写入数据



- 使用字符流读取文件

  ``` java
  public static void main(String[] args) {
          // 准备文件lol.txt其中的内容是AB
          File f = new File("d:/lol.txt");
          // 创建基于文件的Reader
          try (FileReader fr = new FileReader(f)) {
              // 创建字符数组，其长度就是文件的长度
              char[] all = new char[(int) f.length()];
              // 以字符流的形式读取文件所有内容
              fr.read(all);
              for (char b : all) {
                  // 打印出来是A B
                  System.out.println(b);
              }
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
   
      }
  ```


- 使用字符流将字符串写入到文件中

  ``` java
  public static void main(String[] args) {
          // 准备文件lol2.txt
          File f = new File("d:/lol2.txt");
          // 创建基于文件的Writer
          try (FileWriter fr = new FileWriter(f)) {
              // 以字符流的形式把数据写入到文件中
              String data="abcdefg1234567890";
              char[] cs = data.toCharArray();
              fr.write(cs);
    
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
    
      }
  ```



---



#### 缓冲流

缓冲流没有读写能力，需要对应的字节流或字符流提供读写能力

使用缓冲

1. 可以提高速度

2. 可以保护硬盘



分为字节缓冲和字符缓冲



##### 使用缓存流读取数据

缓存字符输入流 BufferedReader 可以一次读取一行数据



``` java
public static void main(String[] args) {
        // 准备文件lol.txt其中的内容是
        // garen kill teemo
        // teemo revive after 1 minutes
        // teemo try to garen, but killed again
        File f = new File("d:/lol.txt");
        // 创建文件字符流
        // 缓存流必须建立在一个存在的流的基础上
        try (
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
            )
        {
            while (true) {
                // 一次读一行
                String line = br.readLine();
                if (null == line)
                    break;
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
    }
```



##### 使用缓存流写出数据

PrintWriter 缓存字符输出流， 可以一次写出一行数据

``` java
public static void main(String[] args) {
        // 向文件lol2.txt中写入三行语句
        File f = new File("d:/lol2.txt");
          
        try (
                // 创建文件字符流
                FileWriter fw = new FileWriter(f);
                // 缓存流必须建立在一个存在的流的基础上              
                PrintWriter pw = new PrintWriter(fw);              
        ) {
            pw.println("garen kill teemo");
            pw.println("teemo revive after 1 minutes");
            pw.println("teemo try to garen, but killed again");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   
    }
```



##### flush

有的时候，需要立即把数据写入到硬盘，而不是等缓存满了才写出去。 这时候就需要用到flush

``` java
public static void main(String[] args) {
        //向文件lol2.txt中写入三行语句
        File f =new File("d:/lol2.txt");
        //创建文件字符流
        //缓存流必须建立在一个存在的流的基础上
        try(FileWriter fr = new FileWriter(f);PrintWriter pw = new PrintWriter(fr);) {
            pw.println("garen kill teemo");
            //强制把缓存中的数据写入硬盘，无论缓存是否已满
                pw.flush();           
            pw.println("teemo revive after 1 minutes");
                pw.flush();
            pw.println("teemo try to garen, but killed again");
                pw.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
```



> 设计一个方法，用于移除Java文件中的注释
>
> public void removeComments(File javaFile)
>
>
>
> 比如，移出以//开头的注释行
>
>  
>
> File f = new File("d:/LOLFolder/LOL.exe");
>
> System.out.println("当前文件是：" +f);
>
> //文件是否存在
>
> System.out.println("判断是否存在："+f.exists());
>
> //是否是文件夹
>
> System.out.println("判断是否是文件夹："+f.isDirectory());
>
>  
>
> 注：
>
> 如果注释在后面，或者是/**/风格的注释，暂不用处理



```java
public class Practice {
    public static void main(String[] args) {
        File file = new File("d://io.txt");
        File file2 = new File("D://io2.txt");
        try(
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(file2);
            PrintWriter pw = new PrintWriter(fw)    
        ){
            while(true) {
                String line = br.readLine();
                if(null==line) {
                    break;
                }else if(line.startsWith("//")){
                    continue;
                }else{
                    pw.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
```





---



#### 数据流

DataInputStream & DataOutputStream

数据输入流 &数据输出流



##### 直接进行字符串的读写

使用数据流的writeUTF()和readUTF() 可以进行数据的格式化顺序读写
如本例，通过DataOutputStream 向文件顺序写出 布尔值，整数和字符串。 然后再通过DataInputStream 顺序读入这些数据。

注： 要用DataInputStream 读取一个文件，这个文件必须是由DataOutputStream 写出的，否则会出现EOFException，因为DataOutputStream 在写出的时候会做一些特殊标记，只有DataInputStream 才能成功的读取。



```java
package stream;
      
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
      
public class TestStream {
      
    public static void main(String[] args) {
        write();
        read();
    }
 
    private static void read() {
        File f =new File("d:/lol.txt");
        try (
                FileInputStream fis  = new FileInputStream(f);
                DataInputStream dis =new DataInputStream(fis);
        ){
            boolean b= dis.readBoolean();
            int i = dis.readInt();
            String str = dis.readUTF();
             
            System.out.println("读取到布尔值:"+b);
            System.out.println("读取到整数:"+i);
            System.out.println("读取到字符串:"+str);
 
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
 
    private static void write() {
        File f =new File("d:/lol.txt");
        try (
                FileOutputStream fos  = new FileOutputStream(f);
                DataOutputStream dos =new DataOutputStream(fos);
        ){
            dos.writeBoolean(true);
            dos.writeInt(300);
            dos.writeUTF("123 this is gareen");
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
}
```



---



#### 对象流

ObjectInputStream 对象输入流

ObjectOutputStream 对象输出流



对象流指的是可以直接把一个对象以流的形式传输给其他的介质，比如硬盘 

一个对象以流的形式进行传输，叫做序列化。 该对象所对应的类，必须是实现Serializable接口



- 序列化一个对象

  ```java
  package stream;
      
  import java.io.File;
  import java.io.FileInputStream;
  import java.io.FileOutputStream;
  import java.io.IOException;
  import java.io.ObjectInputStream;
  import java.io.ObjectOutputStream;
    
  import charactor.Hero;
      
  public class TestStream {
      
      public static void main(String[] args) {
          //创建一个Hero garen
          //要把Hero对象直接保存在文件上，务必让Hero类实现Serializable接口
          Hero h = new Hero();
          h.name = "garen";
          h.hp = 616;
            
          //准备一个文件用于保存该对象
          File f =new File("d:/garen.lol");
   
          try(
              //创建对象输出流
              FileOutputStream fos = new FileOutputStream(f);
              ObjectOutputStream oos =new ObjectOutputStream(fos);
              //创建对象输入流              
              FileInputStream fis = new FileInputStream(f);
              ObjectInputStream ois =new ObjectInputStream(fis);
          
              oos.writeObject(h);
              Hero h2 = (Hero) ois.readObject();
              System.out.println(h2.name);
              System.out.println(h2.hp);
                 
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          } catch (ClassNotFoundException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
              
      }
  }
  
  
  
  
  package charactor;
   
  import java.io.Serializable;
   
  public class Hero implements Serializable {
      //表示这个类当前的版本，如果有了变化，比如新设计了属性，就应该修改这个版本号
      private static final long serialVersionUID = 1L;
      public String name;
      public float hp;
   
  }
  ```



---



#### 控制台输入输出流

System.in 控制台输入流

System.out 控制台输出流



- System.in

  ```java
  package stream;
   
  import java.io.IOException;
  import java.io.InputStream;
   
  public class TestStream {
   
      public static void main(String[] args) {
          // 控制台输入
          try (InputStream is = System.in;) {
              while (true) {
                  // 敲入a,然后敲回车可以看到
                  // 97 13 10
                  // 97是a的ASCII码
                  // 13 10分别对应回车换行
                  int i = is.read();
                  System.out.println(i);
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }
  ```


- Scanner读取控制台上用户输入的字符串

  使用System.in.read虽然可以读取数据，但是很不方便
  使用Scanner就可以逐行读取了

  ```java
  package stream;
      
  import java.util.Scanner;
      
  public class TestStream {
      
      public static void main(String[] args) {
           
              Scanner s = new Scanner(System.in);
               
              while(true){
                  String line = s.nextLine();
                  System.out.println(line);
              }
           
      }
  }
  ```



常用方法：

nextLine() : 读取一行

nextInt()：读取整数



- 自动创建一个类

  > 自动创建有一个属性的类文件。
  > 通过控制台，获取类名，属性名称，属性类型，根据一个模板文件，自动创建这个类文件，并且为属性提供setter和getter
  >
  > ```java
  > public class @class@ {
  >     public @type@ @property@;
  >     
  >     public @class@() {
  >     }
  >     
  >     public void set@Uproperty@(@type@  @property@){
  >         this.@property@ = @property@;
  >     }
  >     
  >     public @type@  get@Uproperty@(){
  >         return this.@property@;
  >     }
  > }
  > ```
  >
  >



```java
package com.imooc.exception;
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
 
public class text {
     public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入类的名称:");
            String className = sc.nextLine()==null?"class":sc.nextLine();
            System.out.println("请输入属性的类型:");
            String propertyType = sc.nextLine()==null?"String":sc.nextLine();
            System.out.println("请输入类型的名称:");
            String propertyName = sc.nextLine()==null?"name":sc.nextLine();
            String path="D:\\JavaFilePractice\\"+className+".java";
            File f = new File(path);
            String s1 = create(f);
                s1 = s1.replaceAll("@class@", className).
                        replaceAll("@type@", propertyType).replaceAll("@property@", propertyName);
                System.out.println(s1);
            try(
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                ){
                bw.write(s1);
                System.out.printf("文件保存在:%s",f.getAbsolutePath());
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
          
        public static String create(File f) {
            if(!f.exists()) {
                f.getParentFile().mkdirs();
                try {
                    f.createNewFile();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
              
            String model ="public class @class@{\r\n"+
                    "\tpublic @type@ @property@;\n"+
                    "\tpublic @class@(){\r\n"+
                    "\n"+
                    "\t}\n"+
                    "\tpublic void set@property@(@type@ @property@){\n"+
                    "\t\tthis.@property@=@property@;\n"+
                    "\t}\n"+
                    "\tpublic @type@ get@property@(){\n"+
                    "\t\treturn this.@property@;\n"+
                    "\t}\n"+
                    "}\n";
            return model;
        }
}
```



---



### 多线程

https://www.cnblogs.com/yjd_hycf_space/p/7526608.html

#### 基本概念

- 首先要理解**进程(Processor)和线程(Thread)的区别** 

  - 进程：每个进程都有独立的代码和数据空间（进程上下文），进程间的切换会有较大的开销，一个进程包含1--n个线程。（进程是资源分配的最小单位）
    - 线程：同一类线程共享代码和数据空间，每个线程有独立的运行栈和程序计数器(PC)，线程切换开销小。（线程是cpu调度的最小单位）
  - 线程和进程一样分为五个阶段：**创建、就绪、运行、阻塞、终止。**
  - 多进程是指操作系统能同时运行多个任务（程序）。
  - 多线程是指在同一程序中有多个顺序流在执行。
- 创建一个线程的方法有三种
  1. 继承 Thread 类
  2. 实现 Runnable 接口
  3. 实现 Callable 接口（不常用）



1. 继承 Thread 类

   ```java
   package com.multithread.learning;  
    
   class Thread1 extends Thread{  
       private String name;  
       public Thread1(String name) {  
          this.name=name;  
       }  
       public void run() {  
           for (int i = 0; i < 5; i++) {  
               System.out.println(name + "运行  :  " + i);  
               try {  
                   sleep((int) Math.random() * 10);  
               } catch (InterruptedException e) {  
                   e.printStackTrace();  
               }  
           }  
            
       }  
   }  
   public class Main {  
     
       public static void main(String[] args) {  
           Thread1 mTh1=new Thread1("A");  
           Thread1 mTh2=new Thread1("B");  
           mTh1.start();  
           mTh2.start();  
     
       }  
     
   }  
   ```



- 注意：start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），什么时候运行是由操作系统决定的。

  从程序运行的结果可以发现，多线程程序是乱序执行。因此，只有乱序执行的代码才有必要设计为多线程。

  Thread.sleep()方法调用目的是不让当前线程独自霸占该进程所获取的CPU资源，以留出一定时间给其他线程执行的机会。

  实际上所有的多线程代码执行顺序都是不确定的，每次执行的结果都是随机的。

  但是start方法重复调用的话，会出现java.lang.IllegalThreadStateException异常。



2. 实现 Runnable 接口

```java
package com.multithread.runnable;  

class Thread2 implements Runnable{  
    private String name;  
  
    public Thread2(String name) {  
        this.name=name;  
    }  
  
    @Override  
    public void run() {  
          for (int i = 0; i < 5; i++) {  
                System.out.println(name + "运行  :  " + i);  
                try {  
                    Thread.sleep((int) Math.random() * 10);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
          
    }  
      
}  
public class Main {  
  
    public static void main(String[] args) {  
        new Thread(new Thread2("C")).start();  
        new Thread(new Thread2("D")).start();  
    }  
  
}  
```



- **说明：**

  **Thread2类通过实现Runnable接口，使得该类有了多线程类的特征。run（）方法是多线程程序的一个约定。所有的多线程代码都在run方法里面。Thread类实际上也是实现了Runnable接口的类。**

  在启动的多线程的时候，需要先通过Thread类的构造方法Thread(Runnable target) 构造出对象，然后调用Thread对象的start()方法来运行多线程代码。

  实际上所有的多线程代码都是通过运行Thread的start()方法来运行的。因此，不管是扩展Thread类还是实现Runnable接口来实现多线程，最终还是通过Thread的对象的API来控制线程的，熟悉Thread类的API是进行多线程编程的基础。



#### Thread 和 Runnable 的区别

如果一个类继承Thread，则不适合资源共享。但是如果实现了Runable接口的话，则很容易的实现资源共享。

**总结：**

**实现Runnable接口比继承Thread类所具有的优势：**

**1）：适合多个相同的程序代码的线程去处理同一个资源**

**2）：可以避免java中的单继承的限制**

**3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立**

**4）：线程池只能放入实现Runable或callable类线程，不能直接放入继承Thread的类**

 

**提醒一下大家：main方法其实也是一个线程。在java中所以的线程都是同时启动的，至于什么时候，哪个先执行，完全看谁先得到CPU的资源。**

**在java中，每次程序运行至少启动2个线程。一个是main线程，一个是垃圾收集线程。因为每当使用java命令执行一个类的时候，实际上都会启动一个JVM，每一个JVM实际上就是在操作系统中启动了一个进程。**



#### 线程状态转换

![线程状态转换](/Users/zhouzhiliwen/Documents/电子书/学习笔记/notebook/img/线程状态转换.png)



1. **新建状态（New）：**新创建了一个线程对象。

2. **就绪状态（Runnable）：**线程对象创建后，其他线程调用了该对象的start()方法。该状态的线程位于可运行线程池中，变得可运行，等待获取CPU的使用权。

3. **运行状态（Running）：**就绪状态的线程获取了CPU，执行程序代码。

4. **阻塞状态（Blocked）：阻塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。直到线程进入就绪状态，才有机会转到运行状态。阻塞的情况分三种：**

   1. **等待阻塞**：运行的线程执行wait()方法，JVM会把该线程放入等待池中。(wait会释放持有的锁)

   2. **同步阻塞**：运行的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线程放入锁池中。
   3. **其他阻塞**：运行的线程执行sleep()或join()方法，或者发出了I/O请求时，JVM会把该线程置为阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。（注意,sleep是不会释放持有的锁）

5. **死亡状态（Dead）：**线程执行完了或者因异常退出了run()方法，该线程结束生命周期。



#### 线程调度

1. **调整线程优先级：**Java线程有优先级，优先级高的线程会获得较多的运行机会。

   Java线程的优先级用整数表示，取值范围是1~10，Thread类有以下三个静态常量：

   `static int MAX_PRIORITY //线程可以具有的最高优先级，取值为10。`

   `static int MIN_PRIORITY //线程可以具有的最低优先级，取值为1。`

   `static int NORM_PRIORITY  //分配给线程的默认优先级，取值为5. `

    

   Thread类的`setPriority()`和`getPriority()`方法分别用来设置和获取线程的优先级。

    

   每个线程都有默认的优先级。主线程的默认优先级为`Thread.NORM_PRIORITY`。

   线程的优先级有**继承关系**，比如A线程中创建了B线程，那么B将和A具有相同的优先级。

   JVM提供了10个线程优先级，但与常见的操作系统都不能很好的映射。如果希望程序能移植到各个操作系统中，应该仅仅使用Thread类有以下三个**静态常量**作为优先级，这样能保证同样的优先级采用了同样的调度方式。

 

2. **线程睡眠：Thread.sleep(long millis)方法**，使线程转到阻塞状态。millis参数设定睡眠的时间，以毫秒为单位。当睡眠结束后，就转为就绪（Runnable）状态。sleep()平台移植性好。

 

3. **线程等待：Object类中的wait()方法**，导致当前的线程等待，直到其他线程调用此对象的 notify() 方法或 notifyAll() 唤醒方法。这个两个唤醒方法也是Object类中的方法，行为等价于调用 wait(0) 一样。

 

4. **线程让步：Thread.yield() 方法**，暂停当前正在执行的线程对象，把执行机会让给相同或者更高优先级的线程。

 

5. **线程加入：join()方法**，等待其他线程终止。在当前线程中调用另一个线程的join()方法，则当前线程转入阻塞状态，直到另一个进程运行结束，当前线程再由阻塞转为就绪状态。

 

6. **线程唤醒：Object类中的notify()方法**，唤醒在此对象监视器上等待的单个线程。如果所有线程都在此对象上等待，则会选择唤醒其中一个线程。选择是任意性的，并在对实现做出决定时发生。线程通过调用其中一个 wait 方法，在对象的监视器上等待。 直到当前的线程放弃此对象上的锁定，才能继续执行被唤醒的线程。被唤醒的线程将以常规方式与在该对象上主动同步的其他所有线程进行竞争；例如，唤醒的线程在作为锁定此对象的下一个线程方面没有可靠的特权或劣势。类似的方法还有一个notifyAll()，唤醒在此对象监视器上等待的所有线程。

 **注意：Thread中suspend()和resume()两个方法在JDK1.5中已经废除，不再介绍。因为有死锁倾向。**



#### 常用函数说明

1. **sleep(long millis)**: 在指定的毫秒数内让当前正在执行的线程休眠（暂停执行）

2. **join():**指等待t线程终止。

   1. **使用方式**

      join是Thread类的一个方法，启动线程后直接调用，即join()的作用是：“等待该线程终止”，这里需要理解的就是该线程是指的主线程等待子线程的终止。也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了才能执行。

   ```java
   Thread t = new AThread(); 
   t.start(); 
   t.join();  
   ```

   2. **为什么要使用**

      在很多情况下，主线程生成并起动了子线程，如果子线程里要进行大量的耗时的运算，主线程往往将于子线程之前结束，但是如果主线程处理完其他的事务后，需要用到子线程的处理结果，也就是主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法了。

      不使用 join()

      ```java
      package com.multithread.join;  
      class Thread1 extends Thread{  
          private String name;  
          public Thread1(String name) {  
              super(name);  
             this.name=name;  
          }  
          public void run() {  
              System.out.println(Thread.currentThread().getName() + " 线程运行开始!");  
              for (int i = 0; i < 5; i++) {  
                  System.out.println("子线程"+name + "运行 : " + i);  
                  try {  
                      sleep((int) Math.random() * 10);  
                  } catch (InterruptedException e) {  
                      e.printStackTrace();  
                  }  
              }  
              System.out.println(Thread.currentThread().getName() + " 线程运行结束!");  
          }  
      }  
        
      public class Main {  
        
          public static void main(String[] args) {  
              System.out.println(Thread.currentThread().getName()+"主线程运行开始!");  
              Thread1 mTh1=new Thread1("A");  
              Thread1 mTh2=new Thread1("B");  
              mTh1.start();  
              mTh2.start();  
              System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");  
        
          }  
        
      }  
      
      // 输出结果
      /*
      main主线程运行开始!
      main主线程运行结束!
      B 线程运行开始!
      子线程B运行 : 0
      A 线程运行开始!
      子线程A运行 : 0
      子线程B运行 : 1
      子线程A运行 : 1
      子线程A运行 : 2
      子线程A运行 : 3
      子线程A运行 : 4
      A 线程运行结束!
      子线程B运行 : 2
      子线程B运行 : 3
      子线程B运行 : 4
      B 线程运行结束!
      */
      // 发现主线程比子线程早结束
      ```



​		使用 join()

```java
public class Main {  
  
    public static void main(String[] args) {  
        System.out.println(Thread.currentThread().getName()+"主线程运行开始!");  
        Thread1 mTh1=new Thread1("A");  
        Thread1 mTh2=new Thread1("B");  
        mTh1.start();  
        mTh2.start();  
        try {  
            mTh1.join();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        try {  
            mTh2.join();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");  
  
    }  
  
}  

// 输出结果
/*
main主线程运行开始!
A 线程运行开始!
子线程A运行 : 0
B 线程运行开始!
子线程B运行 : 0
子线程A运行 : 1
子线程B运行 : 1
子线程A运行 : 2
子线程B运行 : 2
子线程A运行 : 3
子线程B运行 : 3
子线程A运行 : 4
子线程B运行 : 4
A 线程运行结束!
*/
// 主线程一定会等子线程都结束了才结束
```



3. **yield():暂停当前正在执行的线程对象，并执行其他线程。**

   Thread.yield()方法作用是：暂停当前正在执行的线程对象，并执行其他线程。

   ​         **yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。**因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。

    

   **结论：yield()从未导致线程转到等待/睡眠/阻塞状态**。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果。可看上面的图。

   ```java
   package com.multithread.yield;  
   class ThreadYield extends Thread{  
       public ThreadYield(String name) {  
           super(name);  
       }  
      
       @Override  
       public void run() {  
           for (int i = 1; i <= 50; i++) {  
               System.out.println("" + this.getName() + "-----" + i);  
               // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）  
               if (i ==30) {  
                   this.yield();  
               }  
           }  
         
   }  
   }  
     
   public class Main {  
     
       public static void main(String[] args) {  
             
           ThreadYield yt1 = new ThreadYield("张三");  
           ThreadYield yt2 = new ThreadYield("李四");  
           yt1.start();  
           yt2.start();  
       }  
     
   }  
   
   //运行结果：
   //第一种情况：李四（线程）当执行到30时会CPU时间让掉，这时张三（线程）抢到CPU时间并执行。
   //第二种情况：李四（线程）当执行到30时会CPU时间让掉，这时李四（线程）抢到CPU时间并执行。
   ```



​	**sleep()和yield()的区别**

​	sleep()使当前线程进入停滞状态，所以执行sleep()的线程在指定的时间内肯定不会被执行；yield()只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行。
​        sleep 方法使当前运行中的线程睡眼一段时间，进入不可运行状态，这段时间的长短是由程序设定的，yield 方法使当前线程让出 CPU 占有权，但让出的时间是不可设定的。实际上，yield()方法对应了如下操作：先检测当前是否有相同优先级的线程处于同可运行状态，如有，则把 CPU  的占有权交给此线程，否则，继续运行原来的线程。所以yield()方法称为“退让”，它把运行机会让给了同等优先级的其他线程
​       另外，sleep 方法允许较低优先级的线程获得运行机会，但 yield()  方法执行时，当前线程仍处在可运行状态，所以，不可能让出较低优先级的线程些时获得 CPU 占有权。在一个运行系统中，如果较高优先级的线程没有调用 sleep 方法，又没有受到 I\O 阻塞，那么，较低优先级线程只能等待所有较高优先级的线程运行结束，才有机会运行。 



4. **setPriority(): 更改线程的优先级。**

   ```java
   MIN_PRIORITY = 1
   NORM_PRIORITY = 5
   MAX_PRIORITY = 10
   ```

   用法：

   ```java
   Thread4 t1 = new Thread4("t1");
   Thread4 t2 = new Thread4("t2");
   t1.setPriority(Thread.MAX_PRIORITY);
   t2.setPriority(Thread.MIN_PRIORITY);
   ```


5. **interrupt():不要以为它是中断某个线程！它只是线线程发送一个中断信号，让线程在无限等待时（如死锁时）能抛出抛出，从而结束线程，但是如果你吃掉了这个异常，那么这个线程还是不会中断的！**



6. **wait()**

   **Obj.wait()，与Obj.notify()必须要与synchronized(Obj)一起使用，也就是wait,与notify是针对已经获取了Obj锁进行操作，从语法角度来说就是Obj.wait(),Obj.notify必须在synchronized(Obj){...}语句块内。**从功能上来说**wait就是说线程在获取对象锁后，主动释放对象锁，同时本线程休眠。直到有其它线程调用对象的notify()唤醒该线程，才能继续获取对象锁，并继续执行**。相应的**notify()就是对对象锁的唤醒操作**。但有一点需要注意的是**notify()调用后，并不是马上就释放对象锁的，而是在相应的synchronized(){}语句块执行结束，自动释放锁后，JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。**这样就提供了在线程间同步、唤醒的操作。Thread.sleep()与Object.wait()二者都可以暂停当前线程，释放CPU控制权，主要的区别在于Object.wait()在释放CPU同时，释放了对象锁的控制。



​	建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC。

```java
package com.multithread.wait;  
public class MyThreadPrinter2 implements Runnable {     
        
    private String name;     
    private Object prev;     
    private Object self;     
    
    private MyThreadPrinter2(String name, Object prev, Object self) {     
        this.name = name;     
        this.prev = prev;     
        this.self = self;     
    }     
    
    @Override    
    public void run() {     
        int count = 10;     
        while (count > 0) {     
            synchronized (prev) {     
                synchronized (self) {     
                    System.out.print(name);     
                    count--;    
                      
                    self.notify();     
                }     
                try {     
                    prev.wait();     
                } catch (InterruptedException e) {     
                    e.printStackTrace();     
                }     
            }     
    
        }     
    }     
    
    public static void main(String[] args) throws Exception {     
        Object a = new Object();     
        Object b = new Object();     
        Object c = new Object();     
        MyThreadPrinter2 pa = new MyThreadPrinter2("A", c, a);     
        MyThreadPrinter2 pb = new MyThreadPrinter2("B", a, b);     
        MyThreadPrinter2 pc = new MyThreadPrinter2("C", b, c);     
             
             
        new Thread(pa).start();  
        Thread.sleep(100);  //确保按顺序A、B、C执行  
        new Thread(pb).start();  
        Thread.sleep(100);    
        new Thread(pc).start();     
        Thread.sleep(100);    
        }     
}    
```



**整体思路:**

​	从大的方向上来讲，该问题为三线程间的同步唤醒操作，主要的目的就是ThreadA->ThreadB->ThreadC->ThreadA循环执行三个线程。为了控制线程执行的顺序，那么就必须要确定唤醒、等待的顺序，所以每一个线程必须同时持有两个对象锁，才能继续执行。一个对象锁是prev，就是前一个线程所持有的对象锁。还有一个就是自身对象锁。主要的思想就是，为了控制执行的顺序，必须要先持有prev锁，也就前一个线程要释放自身对象锁，再去申请自身对象锁，两者兼备时打印，之后首先调用self.notify()释放自身对象锁，唤醒下一个等待线程，再调用prev.wait()释放prev对象锁，终止当前线程，等待循环结束后再次被唤醒。运行上述代码，可以发现三个线程循环打印ABC，共10次。程序运行的主要过程就是A线程最先运行，持有C,A对象锁，后释放A,C锁，唤醒B。线程B等待A锁，再申请B锁，后打印B，再释放B，A锁，唤醒C，线程C等待B锁，再申请C锁，后打印C，再释放C,B锁，唤醒A。看起来似乎没什么问题，但如果你仔细想一下，就会发现有问题，就是初始条件，三个线程按照A,B,C的顺序来启动，按照前面的思考，A唤醒B，B唤醒C，C再唤醒A。但是这种假设依赖于JVM中线程调度、执行的顺序。



**wait和sleep区别**

**共同点**： 

1. 他们都是在多线程的环境下，都可以在程序的调用处阻塞指定的毫秒数，并返回。 
2. wait()和sleep()都可以通过interrupt()方法 打断线程的暂停状态 ，从而使线程立刻抛出InterruptedException。 
   如果线程A希望立即结束线程B，则可以对线程B对应的Thread实例调用interrupt方法。如果此刻线程B正在wait/sleep /join，则线程B会立刻抛出InterruptedException，在catch() {} 中直接return即可安全地结束线程。 
   需要注意的是，InterruptedException是线程自己从内部抛出的，并不是interrupt()方法抛出的。对某一线程调用 interrupt()时，如果该线程正在执行普通的代码，那么该线程根本就不会抛出InterruptedException。但是，一旦该线程进入到 wait()/sleep()/join()后，就会立刻抛出InterruptedException 。 



**不同点**： 

1. Thread类的方法：sleep(),yield()等 
   Object的方法：wait()和notify()等 

2. 每个对象都有一个锁来控制同步访问。Synchronized关键字可以和对象的锁交互，来实现线程的同步。 
   sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。 

3. wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用 

4. sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常



  所以sleep()和wait()方法的最大区别是：
  　　　　sleep()睡眠时，保持对象锁，仍然占有该锁；
  　　　　而wait()睡眠时，释放对象锁。

  但是wait()和sleep()都可以通过interrupt()方法打断线程的暂停状态，从而使线程立刻抛出InterruptedException（但不建议使用该方法）。



  sleep（）方法
  sleep()使当前线程进入停滞状态（阻塞当前线程），让出CUP的使用、目的是不让当前线程独自霸占该进程所获的CPU资源，以留一定时间给其他线程执行的机会;
  　　 sleep()是Thread类的Static(静态)的方法；因此他不能改变对象的机锁，所以当在一个Synchronized块中调用Sleep()方法是，线程虽然休眠了，但是对象的机锁并木有被释放，其他线程无法访问这个对象（即使睡着也持有对象锁）。
  　　在sleep()休眠时间期满后，该线程不一定会立即执行，这是因为其它线程可能正在运行而且没有被调度为放弃执行，除非此线程具有更高的优先级。 



  wait（）方法
  wait()方法是Object类里的方法；当一个线程执行到wait()方法时，它就进入到一个和该对象相关的等待池中，同时失去（释放）了对象的机锁（暂时失去机锁，wait(long timeout)超时时间到后还需要返还对象锁）；其他线程可以访问；
  　　wait()使用notify或者notifyAlll或者指定睡眠时间来唤醒当前等待池中的线程。
  　　wiat()必须放在synchronized block中，否则会在program runtime时扔出”java.lang.IllegalMonitorStateException“异常。



#### 常用名词

**主线程：**JVM调用程序main()所产生的线程。

**当前线程：**这个是容易混淆的概念。一般指通过Thread.currentThread()来获取的进程。

**后台线程：**指为其他线程提供服务的线程，也称为守护线程。JVM的垃圾回收线程就是一个后台线程。**用户线程和守护线程的区别在于，是否等待主线程依赖于主线程结束而结束**

**前台线程：**是指接受后台线程服务的线程，其实前台后台线程是联系在一起，就像傀儡和幕后操纵者一样的关系。傀儡是前台线程、幕后操纵者是后台线程。由前台线程创建的线程默认也是前台线程。可以通过isDaemon()和setDaemon()方法来判断和设置一个线程是否为后台线程。



**线程类的一些常用方法**

sleep(): 强迫一个线程睡眠Ｎ毫秒。

isAlive(): 判断一个线程是否存活。 　　

join(): 等待线程终止。 　　

activeCount(): 程序中活跃的线程数。 　　

enumerate(): 枚举程序中的线程。     

currentThread(): 得到当前线程。 　　

isDaemon(): 一个线程是否为守护线程。 　　

setDaemon(): 设置一个线程为守护线程。(用户线程和守护线程的区别在于，是否等待主线程依赖于主线程结束而结束) 　　

setName(): 为线程设置一个名称。 　　

wait(): 强迫一个线程等待。 　　

notify(): 通知一个线程继续运行。 　　

setPriority(): 设置一个线程的优先级。



#### 线程同步

1. synchronized关键字的作用域有二种： 
   1）是某个对象实例内，synchronized aMethod(){}可以防止多个线程同时访问这个对象的synchronized方法（如果一个对象有多个synchronized方法，只要一个线程访问了其中的一个synchronized方法，其它线程不能同时访问这个对象中任何一个synchronized方法）。这时，不同的对象实例的synchronized方法是不相干扰的。也就是说，其它线程照样可以同时访问相同类的另一个对象实例中的synchronized方法； 
   2）是某个类的范围，synchronized static aStaticMethod{}防止多个线程同时访问这个类中的synchronized static 方法。它可以对类的所有对象实例起作用。 

2. 除了方法前用synchronized关键字，synchronized关键字还可以用于方法中的某个区块中，表示只对这个区块的资源实行互斥访问。用法是: synchronized(this){/*区块*/}，它的作用域是当前对象； 

3. synchronized关键字是不能继承的，也就是说，基类的方法synchronized f(){} 在继承类中并不自动是synchronized f(){}，而是变成了f(){}。继承类需要你显式的指定它的某个方法为synchronized方法； 



Java对多线程的支持与同步机制深受大家的喜爱，似乎看起来使用了synchronized关键字就可以轻松地解决多线程共享数据同步问题。到底如何？――还得对synchronized关键字的作用进行深入了解才可定论。

总的说来，synchronized关键字可以作为函数的修饰符，也可作为函数内的语句，也就是平时说的同步方法和同步语句块。如果再细的分类，synchronized可作用于instance变量、object reference（对象引用）、static函数和class literals(类名称字面常量)身上。



在进一步阐述之前，我们需要明确几点：

A．无论synchronized关键字加在方法上还是对象上，它取得的锁都是对象，而不是把一段代码或函数当作锁――而且同步方法很可能还会被其他线程的对象访问。

B．每个对象只有一个锁（lock）与之相关联。

C．实现同步是要很大的系统开销作为代价的，甚至可能造成死锁，所以尽量避免无谓的同步控制。





























---

### 对象比较器

- 自定义对象的集合的比较需要用到比较器，实现 Comparable 接口，重写 CompareTo()方法

- 由于面向对象原则，修改关闭拓展开启，不想去修改已经完成的对象类，则需要使用到自定义比较器；如果需要自定义比较器，则需要实现 Comparator接口，重写 Compare()方法



```java
package com.learning.demo.collection_study;

import java.util.Comparator;
import java.util.TreeSet;

/**
 *  Set 比较器
 * @author zhouzhiliwen
 *
 */
public class Demo9 {
	public static void main(String[] args) {
		TreeSet set = new TreeSet();

		set.add(new Person2("zhangsan", 1));
		set.add(new Person2("lisi", 2));
		set.add(new Person2("wangwu", 3));

		System.out.println(set);
		
		
		TreeSet set1 = new TreeSet(new myCompare());

		set1.add(new Person3("zhangsan", 1));
		set1.add(new Person3("lisi", 2));
		set1.add(new Person3("wangwu", 3));

		System.out.println(set1);
	}
}

// 实现Comparable接口
class Person2 implements Comparable {
	private String name;
	private int id;

	public Person2() {
	}

	public Person2(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person1 [name=" + name + ", id=" + id + "]";
	}

	@Override
	public int compareTo(Object o) {
		Person2 p = (Person2) o;
		return this.id - p.getId();
	}
}

//自定义比较器
class Person3 {
	private String name;
	private int id;

	public Person3() {
	}

	public Person3(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person1 [name=" + name + ", id=" + id + "]";
	}
}

//自定义比较器 
class myCompare implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		Person3 p1 = (Person3) o1;
		Person3 p2 = (Person3) o2;
		return p2.getId() - p1.getId();
	}	
}
```



---

### 对象的克隆

- 将对象复制一份，称为对象的克隆
- Object 类中存在一个 clone()方法，是 protected 修饰的，如果某个类想要被克隆，则需要实现 Cloneable接口，该接口是一个标记接口，没有定义任何的方法



传统方法创建一个对象，需要使用 new 关键字，同时在内存中开辟一片区域，这个过程中需要去计算对象的大小等，会增加时间；如果需要创建大量相同的对象，则会大大的增加内存空间；

使用克隆的方法，避免了计算对象大小的步骤，减少了时间

```java
package com.learning.demo.clone_study;

public class CloneDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person("xiaoli",27);
		try {
			Person p2 = (Person) p1.clone();
			
			System.out.println(p1);
			System.out.println(p2); //两者的内存地址不相同
			
			System.out.println(p1.toCustomString());
			System.out.println(p2.toCustomString());//两者的内容是相同的
			
			System.out.println(p1 == p2); //false
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


/**
 * 对象要具备克隆的功能
 * 1. 实现 Cloneable 接口
 * 2. 重写 Object 类中的 Clone() 方法
 * @author zhouzhiliwen
 *
 */
class Person implements Cloneable{
	String name;
	int age;
	
	public Person() {}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	public String toCustomString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	//重写 clone() 方法
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
}

```

---



### 数据流







---



### Java 对象序列化和反序列化

(https://www.hollischuang.com/archives/1140)

#### 什么是对象的序列化

- Java允许我们在内存中创建可以复用的Java对象，但是理论上，只有 JVM 运行的时候这些对象才会存在，它们的生命周期不会大于JVM 的生命周期。为了达到持久化（在 JVM 停止之后这些指定的对象仍然存在，方便我们在未来重新读取被保存的对象）的效果，我们就需要用到对象的序列化。
- 在对对象进行序列化的时候，将会把对象的状态保存为一组字节，在未来再将这组字节组装成对象。**对象序列化保存的是对象的”状态”，即它的成员变量。由此可知，*<u>对象序列化不会关注类中的静态变量</u>*。**
- 除了在持久化对象的时候需要用到对象的持久化，在使用 RMI（远程方法调用）和网络中传输对象的时候，我们都需要用到对象的序列化。



#### 怎么序列化

```java
package com.learning.demo.Serializable_study;

import java.io.*;

public class SerializableDemo {

    public static void main(String[] args){

        Student stu = new Student();
        stu.setId(001);
        stu.setName("小明");
        stu.setGender("man");

        System.out.println(stu);  // => Student{name='小明', id=1, gender='man'}


        //将对象写入到磁盘(序列化)
        ObjectOutputStream oos = null;

        try {

            oos = new ObjectOutputStream(new FileOutputStream("/Users/zhouzhiliwen/Documents/java-workspace/Java/Demo/src/com/learning/demo/Serializable_study/output/tempFile"));
            oos.writeObject(stu);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //从文件中读取对象(反序列化)
        File file = new File("/Users/zhouzhiliwen/Documents/java-workspace/Java/Demo/src/com/learning/demo/Serializable_study/output/tempFile");
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(file));

            Student stud1 = (Student) ois.readObject();
            System.out.println(stud1); // => Student{name='小明', id=1, gender='null'}


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

```



#### 序列化和反序列化的相关知识

1. 只要一个类实现了 Serializable 接口，就可以被序列化（Serializable 是一个空接口，只起标志的作用）
2. 通过 `ObjectInputStream` 和 `ObjectOutputStream`对对象进行序列化和反序列化
3. 虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点是两个类的序列化 ID 是否一致（就是 `private static final long serialVersionUID`）
4. 序列化并不保存静态变量。
5. 要想将父类对象也序列化，就需要让父类也实现`Serializable` 接口。
6. `transient` 关键字的作用是控制变量的序列化，在变量声明前加上该关键字，可以阻止该变量被序列化到文件中，在被反序列化后，`transient` 变量的值被设为初始值，如 int 型的是 0，对象型的是 null。
7. 服务器端给客户端发送序列化对象数据，对象中有一些数据是敏感的，比如密码字符串等，希望对该密码字段在序列化时，进行加密，而客户端如果拥有解密的密钥，只有在客户端进行反序列化时，才可以对密码进行读取，这样可以一定程度保证序列化对象的数据安全。



#### Arraylist的序列化

- ArrayList 是实现了 Serializable 接口的，所以他可以进行序列化和反序列化

- 其底层Object数组`elementData`是用 `transient`修饰的，但是仍然能够被序列化

  - 是因为 ArrayList 中定义了两个方法：writeObject() 和 readObject()。

    > 在序列化过程中，如果被序列化的类中定义了writeObject 和 readObject 方法，虚拟机会试图调用对象类里的 writeObject 和 readObject 方法，进行用户自定义的序列化和反序列化。
    >
    > 如果没有这样的方法，则默认调用是 ObjectOutputStream 的 defaultWriteObject 方法以及 ObjectInputStream 的 defaultReadObject 方法。
    >
    > 用户自定义的 writeObject 和 readObject 方法可以允许用户控制序列化的过程，比如可以在序列化的过程中动态改变序列化的数值。

  ```java
  private void readObject(java.io.ObjectInputStream s)
          throws java.io.IOException, ClassNotFoundException {
          elementData = EMPTY_ELEMENTDATA;
  
          // Read in size, and any hidden stuff
          s.defaultReadObject();
  
          // Read in capacity
          s.readInt(); // ignored
  
          if (size > 0) {
              // be like clone(), allocate array based upon size not capacity
              ensureCapacityInternal(size);
  
              Object[] a = elementData;
              // Read in all elements in the proper order.
              for (int i=0; i<size; i++) {
                  a[i] = s.readObject();
              }
          }
      }
  
  private void writeObject(java.io.ObjectOutputStream s)
          throws java.io.IOException{
          // Write out element count, and any hidden stuff
          int expectedModCount = modCount;
          s.defaultWriteObject();
  
          // Write out size as capacity for behavioural compatibility with clone()
          s.writeInt(size);
  
          // Write out all elements in the proper order.
          for (int i=0; i<size; i++) {
              s.writeObject(elementData[i]);
          }
  
          if (modCount != expectedModCount) {
              throw new ConcurrentModificationException();
          }
      }
  ```


- **为什么要使用这种方式进行序列化？**

  - **why transient**

    ArrayList实际上是动态数组，每次在放满以后自动增长设定的长度值，如果数组自动增长长度设为100，而实际只放了一个元素，那就会序列化99个null元素。为了保证在序列化的时候不会将这么多null同时进行序列化，ArrayList把元素数组设置为transient。

  - **why writeObject and readObject**

    前面说过，为了防止一个包含大量空对象的数组被序列化，为了优化存储，所以，ArrayList使用`transient`来声明`elementData`。 但是，作为一个集合，在序列化过程中还必须保证其中的元素可以被持久化下来，所以，通过重写`writeObject` 和 `readObject`方法的方式把其中的元素保留下来。

    `writeObject`方法把`elementData`数组中的元素遍历的保存到输出流（ObjectOutputStream）中。

    `readObject`方法从输入流（ObjectInputStream）中读出对象并保存赋值到`elementData`数组中。

    - 如何自定义序列化和反序列化的策略？
      - 可以通过在类中添加 writeObject() 和 readObject() 方法来实现。
    - 如果一个类中含有writeObject() 和 readObject() 方法，那么这两个方法是如何被调用的呢？
      - 在使用ObjectOutputStream的writeObject方法和ObjectInputStream的readObject方法时，会通过反射的方式调用。

- 总结

  1. 如果一个类想被序列化，需要实现Serializable接口。否则将抛出`NotSerializableException`异常，这是因为，在序列化操作过程中会对类型进行检查，要求被序列化的类必须属于Enum、Array和Serializable类型其中的任何一种。

  2. 在变量声明前加上`transient`关键字，可以阻止该变量被序列化到文件中。

  3. 在类中增加`writeObject` 和 `readObject` 方法可以实现自定义序列化策略



---

### 网络编程 Socket

- 客户端与服务器之间通过 Socket 建立通信

- 客户端必须知道服务端应用程序的IP 地址（或网络名称）以及端口号

- TCP端口号是一个16位的值，用来指定特定的应用程序

- 0~1023的端口号是保留给HTTP、FTP、SMTP等已知的服务

- 客户端通过建立 Socket 来连接服务器

  ```java
  Socket socket = new Socket(String IPAddress, int port);
  ```

- 一旦建立了连接之后，客户端可以从 Socket取得底层串流

  ```java
  scoket.getInputStream();
  ```

- 建立BufferedReader连接InputStreamReader与来自Socket的输入串流以读取服务器的文本信息

- InputStreamReader可以转换字节为字符，主要是用来连接BufferedReader与底层的 Socket 输入串流

- 建立直接连接 Socket 输入串流的 PrinterWriter请求 print()或println()方法来送出 String 给服务端

- 服务器可以使用ServerSocket来等待用户对特定端口的请求

- 当ServerSocket接到请求时，他会做一个 Socket 连接来接受客户端的请求



#### 简单的 demo

```java
// 服务端
package com.learning.demo.Socket_study;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleChatServer {

    static String[] advices = { "run", "walk", "swim", "sleep",
                                "run", "walk", "swim", "sleep",
                                "run", "walk", "swim", "sleep",
                                "run", "walk", "swim", "sleep"
                                };

    public void go(){

        try {

            ServerSocket socket = new ServerSocket(4242);
            while(true){
                Socket socket1 = socket.accept();

                PrintWriter writer = new PrintWriter(socket1.getOutputStream());
                String advice = getAdvice();
                writer.print(advice);
                writer.close();
                System.out.println(advice);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAdvice(){
        int random = (int) (Math.random() * 100 / advices.length);
        return advices[random];
    }

    public static void main(String[] args){
        SingleChatServer server = new SingleChatServer();
        server.go();
    }
}


```



```java
// 客户端
package com.learning.demo.Socket_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SingleChatClient {

    public void go(){

        try {

            Socket socket = new Socket("127.0.0.1",4242);

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String advice = bufferedReader.readLine();

            System.out.println("Today you should " + advice);

            bufferedReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SingleChatClient client = new SingleChatClient();
        client.go();
    }
}


```



https://www.jianshu.com/p/cde27461c226

#### Socket 通信的基本原理

socket 通信是基于TCP/IP 网络层上的一种传送方式，我们通常把TCP和UDP称为传输层。

![网络分层模型示意图](/Users/zhouzhiliwen/Documents/电子书/学习笔记/notebook/img/网络分层模型示意图.png)





如上图，在七个层级关系中，我们将的socket属于传输层，其中UDP是一种面向无连接的传输层协议。UDP不关心对端是否真正收到了传送过去的数据。如果需要检查对端是否收到分组数据包，或者对端是否连接到网络，则需要在应用程序中实现。UDP常用在分组数据较少或多播、广播通信以及视频通信等多媒体领域。在这里我们不进行详细讨论，这里主要讲解的是基于TCP/IP协议下的socket通信。

socket是基于应用服务与TCP/IP通信之间的一个抽象，他将TCP/IP协议里面复杂的通信逻辑进行分装，对用户来说，只要通过一组简单的API就可以实现网络的连接。借用网络上一组socket通信图给大家进行详细讲解：

![Socket网络通信示意图](/Users/zhouzhiliwen/Documents/电子书/学习笔记/notebook/img/Socket网络通信示意图.png)



首先，服务端初始化ServerSocket，然后对指定的端口进行绑定，接着对端口及进行监听，通过调用accept方法阻塞，此时，如果客户端有一个socket连接到服务端，那么服务端通过监听和accept方法可以与客户端进行连接。



- 通常大家会用以下方法进行进行结束：

  socket.close() 或者调用socket.shutdownOutput()方法。调用这俩个方法，都会结束客户端socket。但是有本质的区别。

  socket.close() 将socket关闭连接，那边如果有服务端给客户端反馈信息，此时客户端是收不到的。

  而socket.shutdownOutput()是将输出流关闭，此时，如果服务端有信息返回，则客户端是可以正常接受的。



#### 能够进行多次通信：

```java
//服务端
package com.learning.demo.Socket_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiTransformServer {

    public static void main(String[] args){

        try {

            ServerSocket serverSocket = new ServerSocket(9999); //初始化服务端socket并且绑定9999端口
            Socket socket = serverSocket.accept(); //等待客户端的连接

            //获取输入流,并且指定统一的编码格式
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

            //读取一行数据
            String str;

            //通过while循环不断读取信息
            while ((str = bufferedReader.readLine()) != null) {

                //输出打印
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

//客户端
package com.learning.demo.Socket_study;

import java.io.*;
import java.net.Socket;

public class MultiTransformClient {

    public static void main(String[] args){

        //初始化一个socket           
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",9999);

            //通过socket获取字符流           
            BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //通过标准输入流获取字符流           
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
            while (true){
                String str = bufferedReader.readLine();
                bufferedWriter.write(str);
                bufferedWriter.write("\n"); //在每一行的末尾添加一个回车作为标识符
                bufferedWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}

```



#### 使用多线程：

```java
//服务端
package com.learning.demo.Socket_study;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

    public static void main(String[] args){

        try {

            ServerSocket serverSocket = new ServerSocket(9999); //初始化服务端 Socket 并绑定9999端口



            while(true){

                //等待客户端连接
                Socket socket = serverSocket.accept();

                //每当有一个客户端连接进来之后，就启动一个单独的线程进行处理
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BufferedReader reader = null;
                        //获取输入流，并且指定统一的编码格式
                        try {
                            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

                            //读取一行数据
                            String str;

                            //通过 while 循环不断读取信息
                            while((str = reader.readLine()) != null){
                                //输出打印
                                System.out.println("客户端说： " + str);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//客户端
package com.learning.demo.Socket_study;

import java.io.*;
import java.net.Socket;

public class MultiThreadClient {

    public static void main(String[] args){

        try {

            //初始化一个 socket
            Socket socket = new Socket("127.0.0.1", 9999);

            //通过 socket 获取字符流
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //通过标准输入流获取字符流
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

            while(true){

                String str = reader.readLine();
                writer.write(str);
                writer.write("\n");
                writer.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
```



#### 使用线程池

```java
//服务端
package com.learning.demo.Socket_study;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolServer {

    public static void main(String[] args){

        try {

            ServerSocket serverSocket = new ServerSocket(9999);

            //创建一个线程池
            ExecutorService executorService = Executors.newFixedThreadPool(100);

            while(true){
                Socket socket = serverSocket.accept();

                //lambda 表达式
                Runnable runnable = () -> {
                    try {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                        String str;

                        while((str = reader.readLine()) != null){
                            System.out.println("客户端说： " + str);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };

                executorService.submit(runnable);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//客户端和之前的一样
```



---

### 枚举

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.learning.demo.mixed;

public class EnumDemo {
    public EnumDemo() {
    }

    public static void main(String[] args) {
        Season[] var4;
        int var3 = (var4 = Season.values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            Season s = var4[var2];
            System.out.println(s);
        }

        int season = 4;
        switch(season) {
        case 0:
            System.out.println("春天");
            break;
        case 1:
            System.out.println("夏天");
            break;
        case 2:
            System.out.println("秋天");
            break;
        case 3:
            System.out.println("冬天");
            break;
        default:
            System.out.println("错误");
        }

    }
}

```



---







































---















 