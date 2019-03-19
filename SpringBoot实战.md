# SpringBoot 实战

## 控制反转

控制反转（IOC）和和依赖注入 (DI) 在 Spring 环境下是等同的概念，控制反转是通过依赖注入实现的。

所谓依赖注入指的是容器负责创建对象和维护对象间的依赖关系，而不是通过对象本身负责自己的创建和解决自己的依赖。

依赖注入的主要目的是为了解耦。

Spring IOC 容器(ApplicationContext，应用上下文)负责创建 Bean，并通过容器将功能类 Bean 注入到需要的 Bean 中。

无论是 xml 配置、注解配置还是 Java 配置，都称为配置元数据(描述数据的数据)。元数据本身不具备任何可执行的能力，只能通过外界代码来对这些元数据行解析后进行一些有意义的操作。

### 声明 Bean 的注解

- @Component ：组件，没有明确的角色
- @Service ：在业务逻辑层(Service层)使用
- @Repository ：在数据访问层(Dao 层)使用
- @Controller： 在展示层(MVC->Spring MVC)使用

注入 Bean 的注解：

- @Autowired：Spring提供的注解
- @Inject：JSR-330提供的注解
- @Resource：JSR-250提供的注解

这三个注解可以注解在 set 方法或者属性上。

Service层：

```java
package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class FunctionService {

    public String sayHello(String word){
        return "Hello, " + word + "!";
    }
}
```

```java
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseFunctionService {

    @Autowired
    private FunctionService functionService;

    public String SayHello(String word){
        return functionService.sayHello(word);
    }
}
```

java配置类：

```java
package com.example.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo")
public class DiConfig {
}
```

测试类：

```java
package com.example.demo;

import com.example.demo.service.FunctionService;
import com.example.demo.service.UseFunctionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TestFunctionService extends DemoApplicationTests{

    @Autowired
    private UseFunctionService useFunctionService;

    @Test
    public void testFunctionService(){
        System.out.println(useFunctionService.SayHello("world"));
    }
}
```





#### Java配置

Java 配置是通过@Configuration 和@Bean实现

- @Configuration ：声明当前类是一个配置类，相当于一个 Spring 配置的 xml 文件
- @Bean：注解在方法上，声明当前方法的返回为一个 Bean

原则：全局配置使用 Java 配置(数据库相关配置、MVC 相关配置)，业务 Bean 的配置使用注解配置(@Service、@Component、@Repository、@Controller)





## AOP

AOP：面向切面编程

Spring 的 AOP 的存在目的是为了解耦。AOP 可以让一组类共享相同的行为。

Spring 支持 AspectJ 的注解式编程

1. 使用@Aspect声明是一个切面
2. 使用@After、@Before、@Around 定义建言(advice)，可以直接将拦截规则(切点)作为参数
3. 其中@After、@Before、@Around参数的拦截规则为切点(Pointcut)，为了使切点复用，可使用@PointCut 专门定义拦截规则，然后在@After、@Before、@Around的参数中调用
4. 其中符合条件的每一个被拦截处为连接点(JoinPoint)



```java
package com.example.demo.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
```



```java
package com.example.demo.service;

import com.example.demo.aop.Action;
import org.springframework.stereotype.Service;

@Service
public class DemoAnnotationService {

    @Action(name="注解式拦截的 add 操作")
    public void add(){}
}
```



```java
package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class DemoMethodService {
    public void add(){}
}
```



```java
package com.example.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.example.demo.*")
@EnableAspectJAutoProxy
public class AopConfig {}
```



```java
package com.example.demo.service;

import com.example.demo.aop.Action;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.example.demo.aop.Action)")
    public void annotationPointCut(){}

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);

        System.out.println("注解式拦截" + action.name());
    }

    @Before("execution(* com.example.demo.service.DemoMethodService.*(..))") //*号后面一定要有一个空格
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则式拦截" + method.getName());
    }
}

```



```java
package com.example.demo;

import com.example.demo.config.AopConfig;
import com.example.demo.service.DemoAnnotationService;
import com.example.demo.service.DemoMethodService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args){
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

        DemoAnnotationService annotationService = context.getBean(DemoAnnotationService.class);

        DemoMethodService methodService = context.getBean(DemoMethodService.class);

        annotationService.add();

        methodService.add();

        context.close();

    }
}

```



## Bean的 Scope(作用域)

@Scope 有以下几种

1. Singleton：一个 Spring 容器中只有一个 Bean 实例，为 Spring 的默认配置，全容器共享一个实例
2. Prototype：每次调用新建一个 Bean 实例
3. Request：Web 项目中，给每一个 http request 新建一个 Bean 实例
4. Session：Web 项目中，给每一个 http session 新建一个 Bean 实例
5. GlobalSession



```java
package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class DemoSingletonService {}
```



```java
package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class DemoPrototypeService {}
```



```java
package com.example.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo")
public class ScopeConfig {}
```



```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);

		DemoSingletonService singletonService1 = context.getBean(DemoSingletonService.class);
		DemoSingletonService singletonService2 = context.getBean(DemoSingletonService.class);

		DemoPrototypeService prototypeService1 = context.getBean(DemoPrototypeService.class);
		DemoPrototypeService prototypeService2 = context.getBean(DemoPrototypeService.class);

		System.out.println("singleton 对象是否相等？ " + (singletonService1 == singletonService2));

		System.out.println("prototype 对象是否相等？ " + (prototypeService1 == prototypeService2));

		context.close();

	}
}
```





### Spring EL 和资源调用

Spring 主要在注解@Value 的参数中使用表达式

1. 注入普通字符
2. 注入操作系统属性
3. 注入表达式运算结果
4. 注入其他 Bean 的属性
5. 注入文件内容
6. 注入网址内容
7. 注入属性文件

注入配置配件需要使用@PropertySource 指定文件地址，若使用@Value 注入，则需要篇日志一个 PropertySourcesPlaceholderConfigurer 的 Bean。



```java
package com.example.demo;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;


@Configuration
@ComponentScan("com.example.demo")
@PropertySource("classpath:test.properties") //注入配置文件
public class ELConfig {

    @Value("I Love You") //注入普通字符串
    private String normal;

    @Value("#{systemProperties['os.name']}") //注入操作系统属性
    private String osName;

    @Value("#{T(java.lang.Math).random() * 100}") //注入表达式结果
    private double randomNumber;

    @Value("#{bookService.name}") //注入其他 Bean 属性
    private String fromAnother;

    @Value("classpath:test.txt") //注入文件资源
    private Resource testFile;

    @Value("http://www.baidu.com") //注入网址资源
    private Resource url;

    @Value("${book.name}") //注入配置文件
    private String bookName;

    @Autowired
    private Environment environment;

    public static PropertySourcesPlaceholderConfigurer propertyConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource(){
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(fromAnother);
            System.out.println(IOUtils.toString(testFile.getInputStream()));
            System.out.println(IOUtils.toString(url.getInputStream()));
            System.out.println(bookName);
            System.out.println(environment.getProperty("book.author"));
            System.out.println();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
```



### Bean 的初始化和销毁

Spring 对 Bean 的生命周期配置：

1. Java 配置方式：使用@Bean 的initMethod 和 destoryMethod(相当于 xml 的 init-method 和 destory-method)
2. 注解方式：@PostConstruct 和@PreDestroy



### 事件(Application Event)

Spring 的事件为 Bean 和 Bean 之间的消息通信提供了支持。当一个 Bean 处理完一个任务之后，希望另外一个 Bean知道并能做相应的处理，这是就需要让另外一个 Bean 监听当前 Bean 所发送的事件。

Spring 的事件需要遵循以下流程：

1. 自定义事件，继承 ApplicationEvent；
2. 定义事件监听器，实现 ApplicationListener；
3. 使用容器发布事件。



```java
package com.example.demo;

import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private String msg;

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
```



```java
package com.example.demo;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        String msg = demoEvent.getMsg();
        System.out.println(msg);
    }
}
```



```java
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoPublisher {

    @Autowired
    private ApplicationContext applicationContext; //注入 ApplicationContext 来发布事件

    public void publish(String msg){
        applicationContext.publishEvent(new DemoEvent(this, msg)); //发布事件
    }
}
```



```java
package com.example.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo")
public class EventConfig {
}
```



```java
package com.example.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);

		DemoPublisher publisher = context.getBean(DemoPublisher.class);

		publisher.publish("Hello World");

		context.close();
	}
}
```



## Spring高级话题

### Spring Aware

使用 Spring 本身容器的功能资源时，Bean 需要意识到容器的存在才能够调用 Spring 提供的资源。

| BeanNameAware                  | 获取到容器中 Bean 的名称                          |
| ------------------------------ | ------------------------------------------------- |
| BeanFactory                    | 获取当前 bean factory，这样可以调用容器的服务     |
| ApplicationContextAware        | 当前的 ApplicationContext，这样可以调用容器的服务 |
| MessageSourceAware             | 获得 message source，这样可以获得文本信息         |
| ApplicationEventPublisherAware | 应用事件发布器，可以发布事件                      |
| ResourceLoaderAware            | 获取资源加载器，可以获取外部资源文件              |



demo6-Aware



### 多线程

Spring 可以通过任务执行器(TaskExecutor)来实现多线程和并发操作。使用 ThreadPoolTaskExecutor 可实现一个基于线程池的 TaskExecutor。由于开发过程中一般都是异步的，所以要在配置类中通过@EnableAsync 开启对异步任务的支持，并通过在实际执行的 Bean 方法中使用@Async 注解来声明其是一个异步任务。



demo7-TaskExecutor



### 计划任务

在配置类中注解@EnableScheduling 来开启对计划任务的支持，然后在要执行计划任务的方法上注解@Scheduled，声明这是一个计划任务。

Spring 通过@Scheduled 支持多种类型的计划任务，包含 cron、fixDelay、fixRate 等。

https://www.cnblogs.com/junrong624/p/4239517.html

http://www.bejson.com/othertools/cron/

demo8-Schedule



### 条件注解

