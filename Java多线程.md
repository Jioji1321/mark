# 多线程

https://www.cnblogs.com/yjd_hycf_space/p/7526608.html



## 基本概念

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



### Thread 和 Runnable 的区别

如果一个类继承Thread，则不适合资源共享。但是如果实现了Runable接口的话，则很容易的实现资源共享。

**总结：**

**实现Runnable接口比继承Thread类所具有的优势：**

**1）：适合多个相同的程序代码的线程去处理同一个资源**

**2）：可以避免java中的单继承的限制**

**3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立**

**4）：线程池只能放入实现Runable或callable类线程，不能直接放入继承Thread的类**

 

**提醒一下大家：main方法其实也是一个线程。在java中所以的线程都是同时启动的，至于什么时候，哪个先执行，完全看谁先得到CPU的资源。**

**在java中，每次程序运行至少启动2个线程。一个是main线程，一个是垃圾收集线程。因为每当使用java命令执行一个类的时候，实际上都会启动一个JVM，每一个JVM实际上就是在操作系统中启动了一个进程。**



### 线程状态转换

![线程状态转换](assets/%E7%BA%BF%E7%A8%8B%E7%8A%B6%E6%80%81%E8%BD%AC%E6%8D%A2.png)



1. **新建状态（New）：**新创建了一个线程对象。
2. **就绪状态（Runnable）：**线程对象创建后，其他线程调用了该对象的start()方法。该状态的线程位于可运行线程池中，变得可运行，等待获取CPU的使用权。
3. **运行状态（Running）：**就绪状态的线程获取了CPU，执行程序代码。
4. **阻塞状态（Blocked）：阻塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。直到线程进入就绪状态，才有机会转到运行状态。阻塞的情况分三种：**
   1. **等待阻塞**：运行的线程执行wait()方法，JVM会把该线程放入等待池中。(wait会释放持有的锁)
   2. **同步阻塞**：运行的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线程放入锁池中。
   3. **其他阻塞**：运行的线程执行sleep()或join()方法，或者发出了I/O请求时，JVM会把该线程置为阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。（注意,sleep是不会释放持有的锁）
5. **死亡状态（Dead）：**线程执行完了或者因异常退出了run()方法，该线程结束生命周期。



### 线程调度

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



### 常用函数说明

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



### 常用名词

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



### 线程同步

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





### 线程参数传递

有三种方式：

1. 通过构造函数传入
2. 通过变量和方法传入
3. 通过回调函数传入

其中前两种方法都是在初始化的阶段传入参数，第三种方法可以实现在线程运行的过程中传递参数



```java
package thread.website;

/**
 * @ClassName MyThread3
 * @Description TODO 线程参数传递：通过构造方法传递参数
 * @Author jioji
 * @Date 2019/08/01 0001 15:50
 * @Version 1.0
 **/
public class MyThread3 implements Runnable{
    private String name;

    public MyThread3(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Hello, " + name);
    }


    public static void main(String[] args) {
        MyThread3 r = new MyThread3("world");
        Thread t = new Thread(r);
        t.start();
    }
}
```



```java
package thread.website;

/**
 * @ClassName MyThread2
 * @Description TODO 线程参数传递：通过变量和方法传递参数
 * @Author jioji
 * @Date 2019/08/01 0001 15:45
 * @Version 1.0
 **/
public class MyThread2 implements Runnable{
    private String name;

    public void setName(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Hello, " + name);
    }

    public static void main(String[] args) {
        MyThread2 r = new MyThread2();
        r.setName("world");

        Thread t = new Thread(r);
        t.start();
    }
}
```



```java
package thread.website;

import java.util.Random;

/**
 * @ClassName MyThread
 * @Description TODO  线程中参数传递，使用回调函数传递数据
 * @Author jioji
 * @Date 2019/08/01 0001 15:34
 * @Version 1.0
 **/
public class MyThread1 extends Thread{

    private Work work;
    public MyThread1(Work work){
        this.work = work;
    }

    @Override
    public void run() {
        Random random = new Random();
        Data data = new Data();
        int i1 = random.nextInt(100);
        int i2 = random.nextInt(200);
        int i3 = random.nextInt(300);
        work.process(data,i1,i2,i3); //使用回调函数
        System.out.println(String.valueOf(i1) + "," + String.valueOf(i2) + "," + String.valueOf(i3));
    }

    public static void main(String[] args) {
        Thread t1 = new MyThread1(new Work());
        t1.start();
    }
}


class Data{
    public int data = 0;
}

class Work{
    public void process(Data data,Integer... numbers){
        for(int i: numbers){
            data.data += i;
        }
    }
}
```







### 多线程的状态

1. 新生态 New

   一个线程已经被实例化完成，但是还没有进行任何操作

2. 就绪态 Ready

   一个线程已经被开启，开始加入抢夺CPU时间片

3. 运行态 Run

   一个线程已经抢夺到CPU时间片，开始执行线程中的代码

4. 阻塞态 Interrupt

   一个线程由于某种原因，放弃已经抢占到的CPU时间片，并不再抢夺CPU时间片，此时线程处于挂起状态

5. 死亡态 Dead 

   一个线程已经被销毁



- 线程的休眠可以让一个线程从运行态转变为阻塞态，阻塞态转变为就绪态需要结束休眠的时间之后抢夺到CPU时间片，一旦抢夺到了CPU时间片，即可以转变为运行态



```java
package thread.video;

/**
 * @ClassName TestTread
 * @Description TODO
 * @Author jioji
 * @Date 2019/07/31 0031 21:49
 * @Version 1.0
 **/
public class TestThreadMain {

    public static void main(String[] args) {
        //threadCreate();
        //threadPriority();
        threadYield();
    }

    /**
     * 线程的礼让
     * 运行状态->就绪状态，释放当前获取到的CPU时间片
     * 将执行机会给相同优先级或者优先级更高的线程
     */
    public static void threadYield() {
        Runnable r = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);

                if (i == 3) {
                    Thread.yield();
                }
            }
        };

        Thread t1 = new Thread(r, "Thread-1");
        Thread t2 = new Thread(r, "Thread-2");
        Thread t3 = new Thread(r, "Thread-3");


        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 设置优先级
     * 设置优先级只是增加概率，不代表一定能够获取CPU时间片
     */
    public static void threadPriority() {
        MyThread t1 = new MyThread("Thread-1");
        MyThread t2 = new MyThread("Thread-2");

        t1.setPriority(10);
        t2.setPriority(1);

        t1.start();
        t2.start();
    }

    /**
     * 线程的创建
     */
    public static void threadCreate() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Thread-1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Thread-2");

        t1.start();
        t2.start();

    }
}


class MyThread extends Thread {
    public MyThread(String name) {
        this.setName(name);
    }

    public MyThread(Runnable r, String name) {
        super(r, name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
```













### 锁

多个资源同时访问一个临界资源的时候，由于相互抢占CPU时间片，会发生某一个线程在获取到资源后，还未执行完代码逻辑的情况下，就被另一个线程夺走CPU时间片并修改临界资源的情况。等到下一次该线程获取到CPU时间片继续执行代码时，临界资源已经发生变化，所以输出的结果并不是我们所期待的



```java
package thread.video;

/**
 * @ClassName SourceConflict
 * @Description TODO
 * @Author jioji
 * @Date 2019/07/31 0031 22:51
 * @Version 1.0
 **/
public class SourceConflict {
    public static void main(String[] args) {

        Runnable r = () -> {
            while (Ticket.restTicket > 0) {
                //System.out.println(Thread.currentThread().getName() + " 卖出了1张票，还剩余" + --Ticket.restTicket + "张票！");
                //相当于：
                Ticket.restTicket = Ticket.restTicket - 1;
                System.out.println(Thread.currentThread().getName() + " 卖出了1张票，还剩余" + Ticket.restTicket + "张票！");
            }
        };

        Thread t1 = new Thread(r, "Thread - 1");
        Thread t2 = new Thread(r, "Thread - 2");
        Thread t3 = new Thread(r, "Thread - 3");
        Thread t4 = new Thread(r, "Thread - 4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class Ticket {
    public static int restTicket = 100;
}


/**
Thread - 2 卖出了1张票，还剩余99张票！
Thread - 2 卖出了1张票，还剩余95张票！
Thread - 2 卖出了1张票，还剩余94张票！
Thread - 2 卖出了1张票，还剩余93张票！
Thread - 2 卖出了1张票，还剩余92张票！
Thread - 2 卖出了1张票，还剩余91张票！
Thread - 2 卖出了1张票，还剩余90张票！
Thread - 2 卖出了1张票，还剩余89张票！
Thread - 2 卖出了1张票，还剩余88张票！
Thread - 2 卖出了1张票，还剩余87张票！
Thread - 2 卖出了1张票，还剩余86张票！
Thread - 2 卖出了1张票，还剩余85张票！
Thread - 2 卖出了1张票，还剩余84张票！
Thread - 2 卖出了1张票，还剩余83张票！
Thread - 2 卖出了1张票，还剩余82张票！
Thread - 2 卖出了1张票，还剩余81张票！
Thread - 2 卖出了1张票，还剩余80张票！
Thread - 1 卖出了1张票，还剩余96张票！
Thread - 1 卖出了1张票，还剩余78张票！
Thread - 1 卖出了1张票，还剩余77张票！
Thread - 1 卖出了1张票，还剩余76张票！
Thread - 1 卖出了1张票，还剩余75张票！
Thread - 1 卖出了1张票，还剩余74张票！
Thread - 1 卖出了1张票，还剩余73张票！
Thread - 1 卖出了1张票，还剩余72张票！
Thread - 1 卖出了1张票，还剩余71张票！
Thread - 1 卖出了1张票，还剩余70张票！
Thread - 1 卖出了1张票，还剩余69张票！
Thread - 1 卖出了1张票，还剩余68张票！
Thread - 1 卖出了1张票，还剩余67张票！
Thread - 1 卖出了1张票，还剩余66张票！
Thread - 1 卖出了1张票，还剩余65张票！
Thread - 1 卖出了1张票，还剩余64张票！
Thread - 1 卖出了1张票，还剩余63张票！
Thread - 1 卖出了1张票，还剩余62张票！
Thread - 1 卖出了1张票，还剩余61张票！
Thread - 1 卖出了1张票，还剩余60张票！
Thread - 1 卖出了1张票，还剩余59张票！
Thread - 1 卖出了1张票，还剩余58张票！
Thread - 1 卖出了1张票，还剩余57张票！
Thread - 1 卖出了1张票，还剩余56张票！
Thread - 1 卖出了1张票，还剩余55张票！
Thread - 1 卖出了1张票，还剩余54张票！
Thread - 1 卖出了1张票，还剩余53张票！
Thread - 1 卖出了1张票，还剩余52张票！
Thread - 1 卖出了1张票，还剩余51张票！
Thread - 1 卖出了1张票，还剩余50张票！
Thread - 1 卖出了1张票，还剩余49张票！
Thread - 1 卖出了1张票，还剩余48张票！
Thread - 1 卖出了1张票，还剩余47张票！
Thread - 1 卖出了1张票，还剩余46张票！
Thread - 1 卖出了1张票，还剩余45张票！
Thread - 1 卖出了1张票，还剩余44张票！
Thread - 1 卖出了1张票，还剩余43张票！
Thread - 1 卖出了1张票，还剩余42张票！
Thread - 1 卖出了1张票，还剩余41张票！
Thread - 1 卖出了1张票，还剩余40张票！
Thread - 1 卖出了1张票，还剩余39张票！
Thread - 1 卖出了1张票，还剩余38张票！
Thread - 1 卖出了1张票，还剩余37张票！
Thread - 1 卖出了1张票，还剩余36张票！
Thread - 1 卖出了1张票，还剩余35张票！
Thread - 1 卖出了1张票，还剩余34张票！
Thread - 1 卖出了1张票，还剩余33张票！
Thread - 1 卖出了1张票，还剩余32张票！
Thread - 1 卖出了1张票，还剩余31张票！
Thread - 1 卖出了1张票，还剩余30张票！
Thread - 1 卖出了1张票，还剩余29张票！
Thread - 1 卖出了1张票，还剩余28张票！
Thread - 1 卖出了1张票，还剩余27张票！
Thread - 1 卖出了1张票，还剩余26张票！
Thread - 1 卖出了1张票，还剩余25张票！
Thread - 1 卖出了1张票，还剩余24张票！
Thread - 1 卖出了1张票，还剩余23张票！
Thread - 1 卖出了1张票，还剩余22张票！
Thread - 1 卖出了1张票，还剩余21张票！
Thread - 1 卖出了1张票，还剩余20张票！
Thread - 1 卖出了1张票，还剩余19张票！
Thread - 1 卖出了1张票，还剩余18张票！
Thread - 1 卖出了1张票，还剩余17张票！
Thread - 1 卖出了1张票，还剩余16张票！
Thread - 1 卖出了1张票，还剩余15张票！
Thread - 1 卖出了1张票，还剩余14张票！
Thread - 1 卖出了1张票，还剩余13张票！
Thread - 1 卖出了1张票，还剩余12张票！
Thread - 1 卖出了1张票，还剩余11张票！
Thread - 3 卖出了1张票，还剩余97张票！
Thread - 3 卖出了1张票，还剩余9张票！
Thread - 3 卖出了1张票，还剩余8张票！
Thread - 3 卖出了1张票，还剩余7张票！
Thread - 3 卖出了1张票，还剩余6张票！
Thread - 3 卖出了1张票，还剩余5张票！
Thread - 3 卖出了1张票，还剩余4张票！
Thread - 3 卖出了1张票，还剩余3张票！
Thread - 3 卖出了1张票，还剩余2张票！
Thread - 3 卖出了1张票，还剩余1张票！
Thread - 3 卖出了1张票，还剩余0张票！
Thread - 4 卖出了1张票，还剩余98张票！
Thread - 1 卖出了1张票，还剩余10张票！
Thread - 2 卖出了1张票，还剩余79张票！
*/
```



为了避免这样的情况发生，可以对线程方法中的执行部分加上锁，确保每一个线程一旦抢占到临界资源之后，在没有完成相关逻辑之前，其他线程无法获取到该临界资源。





#### 锁的类型



<https://blog.csdn.net/tyyj90/article/details/78236053>	





- 同步函数的锁是this
  - 同步函数用的是哪一个锁呢？函数需要被对象调用。那么函数都有一个所属对象引用。就是this。所以同步函数使用的锁是this。



- 静态同步函数的函数是类名.class
  - 如果同步函数被静态修饰后，使用的锁是什么呢？通过验证，发现不在是this。因为静态方法中也不可以定义this。静态进内存是，内存中没有本类对象，但是一定有该类对应的字节码文件对象。
    类名.class  该对象的类型是Class。静态的同步方法，使用的锁是该方法所在类的字节码文件对象。 类名.class  





#### 单例模型设计 - 懒汉模式的线程安全问题

饿汉式与懒汉式的区别
1.懒汉式是延时加载的形式
2.懒汉式由于是延时加载，所以在多线程中会出现安全问题
3.可以加同步函数或者同步代码块来解决，而同步函数每次都需要判断比较慢
4.这里采用同步代码块来解决安全问题
5.由于函数是静态的，所以锁是类名.class



饿汉：

```java
package thread.website;

/**
 * @ClassName SingleHungry
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/01 0001 10:13
 * @Version 1.0
 **/
public class SingleHungry {

    private static SingleHungry instance = new SingleHungry();

    private SingleHungry(){}

    public static SingleHungry getInstance(){
        return instance;
    }
}
```

懒汉：

```java
package thread.website;

/**
 * @ClassName SingleLazy
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/01 0001 10:16
 * @Version 1.0
 **/
public class SingleLazy {

    private static SingleLazy instance = null;

    private SingleLazy() {
    }

    public static SingleLazy getInstance() {
        if (instance == null) {
            synchronized (SingleLazy.class) {
                if (instance == null) {
                    instance = new SingleLazy();
                }
            }
        }
        return instance;
    }
}
```





### 停止线程



停止一个线程使用Thread.interrupt()方法，会先进行判断之后才可以完成线程的停止。

在Java中有三种方法可以终止正在运行的线程：

1. 使用退出标志，使线程正常退出，也就是当run方法完成后线程终止
2. 使用stop()方法强行终止线程（该方法已经废弃，不推荐使用）
3. 使用Thread.interrupt()方法中断线程



#### 判断线程是否是停止状态

1. this.interrupted() ： 测试当前线程是否已经中断（public static boolean interrupted()），执行后具有将状态标志清除为false的功能
2. this.isInterrupted() ： 测试线程是否已经中断 (public boolean isInterrupted())，不清除状态标志

以上两种方法都不能够停止线程



停止线程的步骤

1. 执行代码，执行完毕时调用interrupt()方法，将装填标志清除为false
2. 在run方法中，调用this.isInterrupted()判断中断标志，如果已经中断，则抛出异常或者return



##### 能停止的线程--异常法

```java
package thread_interrupt;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/01 0001 21:32
 * @Version 1.0
 **/
public class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            if (this.interrupted()) {
                System.out.println("已经是停止状态了，我要退出了");
                break;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("如果此代码是fot又继续运行，线程并未停止");
    }

    public static void main(String[] args) {
        try{
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(2000);

            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
/*
......
i=426125
i=426126
i=426127
end!
已经是停止状态了，我要退出了
如果此代码是fot又继续运行，线程并未停止
*/
```





1. 抛出异常，停止线程

```java
package thread_interrupt;

/**
 * @ClassName MyThreadInterrupt
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/01 0001 21:51
 * @Version 1.0
 **/
public class MyThreadInterrupt extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if (this.interrupted()) {
                    System.out.println("已经是停止状态了，我要退出了");
                    throw new InterruptedException();
                }
                System.out.println("i = " + (i + 1));
            }
            System.out.println("我在for下面");
        } catch (InterruptedException e) {
            System.err.println("进MyThreadInterrupt.java类run方法中的catch了！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            MyThreadInterrupt t = new MyThreadInterrupt();
            t.start();
            Thread.sleep(2000);
            t.interrupt();
        } catch (InterruptedException e) {
            System.err.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
/*
......
i = 430508
i = 430509
i = 430510
end!
已经是停止状态了，我要退出了
进MyThreadInterrupt.java类run方法中的catch了！
java.lang.InterruptedException
	at thread_interrupt.MyThreadInterrupt.run(MyThreadInterrupt.java:18)
*/
```



### 在sleep中停止进程

```java
package thread_interrupt;

/**
 * @ClassName ThreadSleepInterrupt
 * @Description TODO 先sleep再interrupt
 *                   在sleep状态下停止某一线程，会进入catch块，并且清除停止状态值，使之变为false
 * @Author jioji
 * @Date 2019/08/02 0002 9:13
 * @Version 1.0
 **/
public class ThreadSleepInterrupt extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.err.println("在休眠中被停止，进入catch : " + this.isInterrupted());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            ThreadSleepInterrupt t = new ThreadSleepInterrupt();
            t.start();
            Thread.sleep(200);
            t.interrupt();
        } catch (InterruptedException e) {
            System.err.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
/*
run begin
end
在休眠中被停止，进入catch : false
java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)
	at thread_interrupt.ThreadSleepInterrupt.run(ThreadSleepInterrupt.java:18)
*/
```



```java
package thread_interrupt;

/**
 * @ClassName ThreadInterruptSleep
 * @Description TODO 先interrupt在sleep
 * @Author jioji
 * @Date 2019/08/02 0002 9:30
 * @Version 1.0
 **/
public class ThreadInterruptSleep extends Thread{

    @Override
    public void run() {
        super.run();
        try{
            for (int i = 0; i < 100000; i++) {
                System.out.println("i = " + (i+1));
            }
            System.out.println("run begin");
            Thread.sleep(20000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.err.println("先停止再遇到sleep，进入catch!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadInterruptSleep t = new ThreadInterruptSleep();
        t.start();
        t.interrupt();
        System.out.println("end!");
    }
}

/*
end!
i = 1
i = 2
i = 3
......
i = 100000
run begin
先停止再遇到sleep，进入catch!
java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)
	at thread_interrupt.ThreadInterruptSleep.run(ThreadInterruptSleep.java:20)
*/
```



#### 使用暴力方法停止线程 stop()

使用暴力方法 stop() 停止线程会抛出 ThreadDead异常

使用 stop() 释放锁将会给数据造成不一致性的结果，如果出现这样的情况，程序处理的数据就有可能遭到破坏，最终导致程序执行的流程错误。



#### 使用return停止线程

将方法interrupt()和return结合使用也可以实现停止线程的效果

```java
package thread_interrupt;

/**
 * @ClassName UserReturnInterrupt
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 9:52
 * @Version 1.0
 **/
public class UserReturnInterrupt extends Thread {

    @Override
    public void run() {
        while(true){
            if(this.isInterrupted()){
                System.out.println("停止了");
                return;
            }
            System.out.println("timer = " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserReturnInterrupt t = new UserReturnInterrupt();
        t.start();
        Thread.sleep(2000);
        t.interrupt();
    }
}
/*
......
timer = 1564710952971
timer = 1564710952971
timer = 1564710952971
停止了
*/
```



#### 暂停线程

使用suspend()暂停线程，resume()恢复线程的执行

在使用suspend()和resume()方法时如果使用不当会造成公共的同步对象的独占，使得其他线程无法访问公共同步对象



#### yield()方法

yield()方法的作用是放弃当前的cpu资源，将他让给其他的任务去占用CPU执行时间，但是放弃的时间不确定，有可能刚刚放弃马上有获取CPU时间片





### 线程优先级(Priority)

高优先级的线程总是大部分先执行完，但不代表高优先级的线程全部先执行完。

当线程优先级的等级差距很大时，谁先执行完和代码的调用顺序无关。

线程的优先级去油一定的规则性，CPU尽量将执行资源让给优先级更高的线程





##### 优先级具有随机性

线程的优先级还具有“随机性”，也就是优先级较高的线程不一定每一次都先执行完





### 守护线程(daemon)

两种线程：用户线程和守护线程

当进程中不存在非守护线程了，则守护线程自动销毁（垃圾回收线程）

Daemon的作用就是为其他线程的运行提供便利服务

可以在`thread.start()`之前使用`thread.setDaemon(true)`将线程设置为守护线程





## 对象及变量的并发访问

synchronized同步方法

“非线程安全”会在多个线程对同一个对象中的实例变量进行并发访问的时候发生，产生的后果就是“脏读”，去到的数据其实是被更改过的；“线程安全”就是以获取实例变量的值是经过同步处理的，不会出现脏读的现象。





#### 方法内的变量为线程安全

如果是方法内部的私有变量，则不存在“非线程安全”问题





#### 实例变量非线程安全

如果多个线程共同访问1个对象中的实例变量，则有可能出现非线程安全问题

用线程访问的对象中如果有多个实例变量，则运行的结果有可能出现交叉的情况



```java
package concurrent_access;

/**
 * @ClassName HasSelfPrivateNum
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 14:45
 * @Version 1.0
 **/
public class HasSelfPrivateNum {

    private int num = 0;
    public void addI(String username){
        try{
            if(username.equals("a")){
                num = 100;
                System.out.println("a set over");
                Thread.sleep(1000);
            }else{
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(username + " num = "+ num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();
        ThreadA a = new ThreadA(numRef);
        a.start();
        ThreadB b = new ThreadB(numRef);
        b.start();
    }
}

class ThreadA extends Thread{
    private HasSelfPrivateNum numRef;

    public ThreadA(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("a");
    }
}

class ThreadB extends Thread{
    private HasSelfPrivateNum numRef;

    public ThreadB(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("b");
    }
}
/*
a set over
b set over
b num = 200
a num = 200
*/
```



以上是两个线程同时访问一个没有同步的方法，如果两个线程同时操作业务对象中的实例变量，则有可能出现非线程安全问题

解决方法：在`public void addI(String username)`方法前加关键字`synchronized`即可

```java
package concurrent_access;

/**
 * @ClassName HasSelfPrivateNum
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 14:45
 * @Version 1.0
 **/
public class HasSelfPrivateNum {

    private int num = 0;
    synchronized public void addI(String username){
        try{
            if(username.equals("a")){
                num = 100;
                System.out.println("a set over");
                Thread.sleep(1000);
            }else{
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(username + " num = "+ num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();
        ThreadA a = new ThreadA(numRef);
        a.start();
        ThreadB b = new ThreadB(numRef);
        b.start();
    }
}

class ThreadA extends Thread{
    private HasSelfPrivateNum numRef;

    public ThreadA(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("a");
    }
}

class ThreadB extends Thread{
    private HasSelfPrivateNum numRef;

    public ThreadB(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("b");
    }
}
/*
a set over
a num = 100
b set over
b num = 200
*/
```



两个线程访问同一个对象中的同步方法是一定是线程安全的





#### 多个对象对个锁

```java
package concurrent_access;

/**
 * @ClassName TwoObjectTwoLock
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 14:58
 * @Version 1.0
 **/
public class TwoObjectTwoLock {
    public static void main(String[] args) {
        HasSelfPrivateNum numRef1 = new HasSelfPrivateNum();
        HasSelfPrivateNum numRef2 = new HasSelfPrivateNum();
        ThreadA a = new ThreadA(numRef1);
        a.start();
        ThreadB b = new ThreadB(numRef2);
        b.start();
    }
}
/*
a set over
b set over
b num = 200
a num = 100
*/
```



关键字`synchronized`取得的锁都是对象锁，不是把一段代码或者方法（函数）当做锁。

需要确保所有线程访问同一个同步方法时获取到的锁都是同一个锁



同步：synchronized

异步：asynchronized



#### synchronized方法与锁对象

```java
package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName MyObject
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:12
 * @Version 1.0
 **/
public class MyObject {

    public void methodA(){
        try{
            System.out.println("begin methodA threadName="+Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName ThreadA
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:14
 * @Version 1.0
 **/
public class ThreadA extends Thread {
    private MyObject object;

    public ThreadA(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.methodA();
    }
}

package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName ThreadB
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:15
 * @Version 1.0
 **/
public class ThreadB extends Thread {
    private MyObject object;

    public ThreadB(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.methodA();
    }
}

package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName Main
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:16
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        MyObject object = new MyObject();
        ThreadA a = new ThreadA(object);
        a.setName("A");
        ThreadB b = new ThreadB(object);
        b.setName("B");
        a.start();
        b.start();
    }
}

/*
begin methodA threadName=B
begin methodA threadName=A
end
end
*/
```



```java
package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName MyObject
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:12
 * @Version 1.0
 **/
public class MyObject {

    public void methodA(){
        try{
            System.out.println("begin methodA threadName="+Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName ThreadA
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:14
 * @Version 1.0
 **/
public class ThreadA extends Thread {
    private MyObject object;

    synchronized public ThreadA(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.methodA();
    }
}

package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName ThreadB
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:15
 * @Version 1.0
 **/
public class ThreadB extends Thread {
    private MyObject object;

    public ThreadB(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.methodA();
    }
}

package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName Main
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:16
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        MyObject object = new MyObject();
        ThreadA a = new ThreadA(object);
        a.setName("A");
        ThreadB b = new ThreadB(object);
        b.setName("B");
        a.start();
        b.start();
    }
}

/*
begin methodA threadName=A
end
begin methodA threadName=B
end
*/
```



调用关键字`synchronized`声明的方法一定是排队运行的

只有共享资源的读写访问才需要同步化



```java
package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName MyObject
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:12
 * @Version 1.0
 **/
public class MyObject {

    synchronized public void methodA(){
        try{
            System.out.println("begin methodA threadName="+Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodB(){
        try{
            System.out.println("begin methodB threadName="+Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName ThreadB
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:15
 * @Version 1.0
 **/
public class ThreadB extends Thread {
    private MyObject object;

    public ThreadB(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.methodB();
    }
}

/*
begin methodA threadName=A
begin methodB threadName=B, begin time: 1564730873642
end
end time: 1564730878642
*/
```



```java
package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName MyObject
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:12
 * @Version 1.0
 **/
public class MyObject {

    synchronized public void methodA(){
        try{
            System.out.println("begin methodA threadName="+Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void methodB(){
        try{
            System.out.println("begin methodB threadName="+Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
begin methodA threadName=A
end time: 1564730947472
begin methodB threadName=B, begin time: 1564730947472
end
*/
```



结论：

1. A线程先持有object对象的Lock锁，B线程可以以异步的方式调用object对象中的非synchronized类型的方法
2. A线程现持有object对象的Lock锁，B线程如果在这是滴啊用object对象中的synchronized类型的方法需要等待，也就是同步



#### 脏读

```java
package concurrent_access.dirty_read;

/**
 * @ClassName PublicVar
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:56
 * @Version 1.0
 **/
public class PublicVar {

    public String username = "A";
    public String password = "AA";

    public void setValue(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method thread name = "
                    + Thread.currentThread().getName() + ", username = "
                    + username + ", password = " + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getValue(){
        System.out.println("setValue method thread name = "
                + Thread.currentThread().getName() + ", username = "
                + username + ", password = " + password);
    }
}

package concurrent_access.dirty_read;

/**
 * @ClassName ThreadA
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 16:00
 * @Version 1.0
 **/
public class ThreadA extends Thread {
    private PublicVar var;

    public ThreadA(PublicVar var) {
        this.var = var;
    }

    @Override
    public void run() {
        super.run();
        var.setValue("B","BB");
    }
}

package concurrent_access.dirty_read;

/**
 * @ClassName Main
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 16:01
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        try {
            PublicVar var = new PublicVar();
            ThreadA thread = new ThreadA(var);
            thread.start();
            Thread.sleep(2000);
            var.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
setValue method thread name = main, username = B, password = AA
setValue method thread name = Thread-0, username = B, password = BB
*/
```

由于getValue()不是同步方法，所以导致获取值时发生不同步的现象

脏读可以通过synchronized关键字解决



```java
package concurrent_access.dirty_read;

/**
 * @ClassName PublicVar
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:56
 * @Version 1.0
 **/
public class PublicVar {

    public String username = "A";
    public String password = "AA";

    synchronized public void setValue(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method thread name = "
                    + Thread.currentThread().getName() + ", username = "
                    + username + ", password = " + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void getValue(){
        System.out.println("setValue method thread name = "
                + Thread.currentThread().getName() + ", username = "
                + username + ", password = " + password);
    }
}
/*
setValue method thread name = Thread-0, username = B, password = BB
setValue method thread name = main, username = B, password = BB
*/
```





1. 当A线程调用anyObject对象加入synchronized关键字的X方法时，A线程就获取了X方法锁（获取了对象的锁），所以其他线程必须等A线程执行完毕才可以调用X方法，但B线程可以随意调用其他的非synchronized同步方法
2. 当A线程调用anyObject对象加入synchronized关键字的X方法时，A线程就获取了X方法锁（获取了对象的锁），所以其他线程必须等A线程执行完毕才可以调用X方法，而B线程如果调用声明了synchronized关键字的非X方法时，必须等A线程将X方法执行完，也就是释放对象锁之后才可以调用。这时A已经执行完了一个完整的任务，X方法中的变量已经同时被赋值，所以就不存在脏读的现象



#### synchronized锁重入

关键字synchronized拥有锁重入的功能，也就是在使用synchronized时，当一个线程得到一个对象锁之后，再次请求此对象锁时是可以再次得到该对象的锁的。

在一个synchronized方法/块的内部调用本类的其他synchronized方法/块时，是永远可以得到锁的

```java
package concurrent_access.syn_lock_in;

/**
 * @ClassName Service
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 16:14
 * @Version 1.0
 **/
public class Service {

    synchronized public void service1(){
        System.out.println("service1");
        service2();
    }

    synchronized public void service2(){
        System.out.println("service2");
        service3();
    }

    synchronized public void service3(){
        System.out.println("service3");
    }
}

package concurrent_access.syn_lock_in;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 16:16
 * @Version 1.0
 **/
public class MyThread extends Thread {
    @Override
    public void run() {
        Service service = new Service();
        service.service1();
    }
}

package concurrent_access.syn_lock_in;

/**
 * @ClassName Main
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 16:16
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}

/*
service1
service2
service3
*/
```



“可重入锁”的概念是自己可以再次获取自己的内部锁

如果不可锁重入，就会造成死锁

当存在父子类继承关系时，子类可以通过“可重入锁”调用父类的同步方法



#### 出现异常，锁自动释放

一旦线程出现异常，则获取的锁自动释放





#### 同步不具备继承性

同步不能继承，需要在子类中的方法加上synchronized关键字





### synchronized同步语句块

当两个并发线程访问同一个对象object中的synchronized(this)同步代码块时，一段时间内只能有一个线程被执行，另一个线程必须等待当前线程执行完这个代码块之后才能执行该代码块



当一个线程访问object的一个synchronized同步代码块时，另一个线程仍然可以访问该object对象中的非synchronized(this)代码块



synchronized(非this对象x)格式的写法是将x对象本身作为对象监视器，由此可以得到三条结论：

1. 当多个线程同时执行synchronized(x){}同步代码块呈同步效果
2. 当其他线程执行x对象中的synchronized同步方法呈同步效果
3. 当其他线程执行x对象中的synchronized(this)代码块时呈同步效果

但是需要注意：如果其他线程调用非synchronized方法时呈异步效果





### 静态同步synchronized方法和synchronized(class)代码块

关键字synchronized作用在static方法上，表示对当前*.java文件对应的Class类进行持锁



synchronized加到static方法上是对Class类进行加锁，synchronized加到非static静态方法是对对象进行加锁



Class锁可以对该类的所有实例对象起作用



同步synchronized(class)代码块效果和synchronized static一样



### 数据类型String的常量池特性

String的常量池介绍

https://www.cnblogs.com/niew/p/9597379.html







### volatile关键字

volatile关键字主要作用是使变量在多个线程中可见

volatile关键字是强制在公共堆栈中获取变量的值，而不是从线程私有数据栈中获取数据的值

使用volatile关键字增加了实例变量在多个线程中的可见性，但是最致命的缺点是不具有原子性



### synchronized和volatile比较

1. 关键字volatile是线程同步的轻量级实现，所以性能会比synchronized好；volatile只能修饰变量，synchronized可以修饰方法及代码块。
2. 多线程访问volatile不会发生阻塞，synchronized会发生阻塞
3. volatile能保证数据的可见性，但不能保证原子性；synchronized可以保证原子性，也可以间接保证可见性，因为它将私有内存和公共内存中的数据做同步
4. volatile是解决变量在多个线程之间的可见性，而synchronized解决的是多个线程之间访问资源的同步性



线程安全包含原子性和可见性两个方面，Java同步机制都是围绕这两个方面来确保线程安全的



### volatile非原子的特性

volatile关键字主要使用的场合是在多个线程中可以感知实例变量被修改了，并且可以获取最新的值使用，也就是用多线程读取共享变量是可以获得最新值使用



变量在内存中工作的过程

1. read和load阶段：从主存复制变量到当前线程工作内存
2. use和assign阶段：执行代码，改变共享变量值
3. store和write阶段：从工作内存数据刷新主存对应变量的值





## 线程之间的通信

### 等待/通知机制的实现

#### wait()

方法wait()的作用是使当前执行代码的线程进行等待，wait()方法是object的方法，该方法将当前线程放入“待执行队列”并在wait()所在的代码处停止执行，直到接收到通知或者被中断为止。

在调用wait()之前，必须先获得该对象的对象级别锁，即只能在同步方法或同步代码块中调用wait()方法

在执行wait()方法之后，当前线程释放锁，在从wait()返回前，线程和其他线程竞争重新获得锁

如果调用wait()时没有持有合适的锁,则抛出ILegalMonitorStateException,他是RuntimeException的子类,所以不需要try-catch捕获

#### notify()

方法notify()也要在同步方法或者同步代码块中使用，即在调用前，线程也需要获取该对象的对象级别锁

如果在调用notify()方法之前没有持有合适的锁，也会抛出ILegalMonitorStateException

该方法用来通知那些可能处于等待该对象对象锁的其他线程，如果有多个线程等待， 则由线程规划器随机挑选出一个呈wait状态的线程，对其发出通知notify，并使它等待获取该对象的对象锁

在执行notify()方法后，当前线程不会马上释放该对象锁，呈wait状态的线程也不能马上获得该对象锁，需要等到执行notify()方法的线程将程序执行完，也就是退出synchronized代码块后，当前线程才会释放锁，而呈wait状态所在的线程才可以获取该对象锁

当第一个获得了该对象锁的wait线程运行完毕之后，他才会释放对象锁，此时如果该对象没有再次使用notify()语句，则即使该对象已经空闲，其他wait状态等待的线程由于没有得到该对象的通知，还会继续阻塞在wait状态，直到这个对象发出一个notify或notifyAll()



wait使线程停止运行，notify使停止的线程继续运行









































