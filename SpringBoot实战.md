# SpringBoot 实战

## Spring

### 控制反转

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





### AOP

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



### Bean的 Scope(作用域)

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





#### Spring EL 和资源调用

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



#### Bean 的初始化和销毁

Spring 对 Bean 的生命周期配置：

1. Java 配置方式：使用@Bean 的initMethod 和 destoryMethod(相当于 xml 的 init-method 和 destory-method)
2. 注解方式：@PostConstruct 和@PreDestroy



#### 事件(Application Event)

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



### Spring高级话题

#### Spring Aware

使用 Spring 本身容器的功能资源时，Bean 需要意识到容器的存在才能够调用 Spring 提供的资源。

| BeanNameAware                  | 获取到容器中 Bean 的名称                          |
| ------------------------------ | ------------------------------------------------- |
| BeanFactory                    | 获取当前 bean factory，这样可以调用容器的服务     |
| ApplicationContextAware        | 当前的 ApplicationContext，这样可以调用容器的服务 |
| MessageSourceAware             | 获得 message source，这样可以获得文本信息         |
| ApplicationEventPublisherAware | 应用事件发布器，可以发布事件                      |
| ResourceLoaderAware            | 获取资源加载器，可以获取外部资源文件              |



demo6-Aware



#### 多线程

Spring 可以通过任务执行器(TaskExecutor)来实现多线程和并发操作。使用 ThreadPoolTaskExecutor 可实现一个基于线程池的 TaskExecutor。由于开发过程中一般都是异步的，所以要在配置类中通过@EnableAsync 开启对异步任务的支持，并通过在实际执行的 Bean 方法中使用@Async 注解来声明其是一个异步任务。



demo7-TaskExecutor



#### 计划任务

在配置类中注解@EnableScheduling 来开启对计划任务的支持，然后在要执行计划任务的方法上注解@Scheduled，声明这是一个计划任务。

Spring 通过@Scheduled 支持多种类型的计划任务，包含 cron、fixDelay、fixRate 等。

https://www.cnblogs.com/junrong624/p/4239517.html

http://www.bejson.com/othertools/cron/

demo8-Schedule



#### 条件注解

@Conditional 根据满足某一个特定条件创建一个特定的 Bean

实现 Conditin 接口，并重写 matches()方法来后早判断条件



demo9-Conditional



#### 组合注解和元注解

将多个注解组合成一个注解进行使用



demo10-multiAnnotation





### Enable*注解的工作原理

@EnableAspectJAutoProxy：开启对 AspectJ 自动代理的支持

@EnableAsync：开启异步方法的支持

@EnableScheduling：开启计划任务的支持

@EnableWebMvc：开启 Web MVC 的支持

@EnableConfigurationProperties：开启对ConfigurationProperties 注解配置 Bean 的支持

@EnableJpaRepositories：开启对 Spring JPA Respository 的支持

@EnableTransactionManagement：开启竹节式事务的支持

@EnableCaching：开启注解式的缓存支持



@Import 注解：导入配置类

1. 直接导入配置类
2. 依据条件选择配置类
3. 动态注册 Bean



### 测试

SpringJunit4ClassRunner 类，通过@ContextConfiguration 来配置 Application Context，通过@ActiveProfiles 确定活动的 profile。



demo11-Test





## Spring MVC

MVC 只存在三层架构的展现层：M 是数据模型，是包含数据的对象。有一个专门的类 Model，用来和 V 之间的数据交互、传值；V 指的是视图页面；C 是控制器



三层架构是整个应用的架构，有 Spring 负责管理的。Service(应用层)，DAO 层(数据访问层)



Spring MVC 的 ViewResolver，是 Spring MVC视图渲染的核心机制：Spring MVC 里有一个接口叫做 ViewResolver(自定义的 ViewResolver 都实现该接口)，实现这个接口要重写方法 resolveViewName()，这个方法的返回值是接口 View，而 View 的指责是使用 model、request、response 对象，并将渲染的视图(html、json、xml、pdf 等)返回给浏览器



### 常用注解

@Controller：表名这个类是 Spring MVC的 Controller。

@RequestMapping：用来映射 Web 请求

@ResponseBody：支持将返回值放在 response 体内，而不是返回一个页面。可以用于基于 Ajax 的程序，注解返回数据而不是页面。可以放在返回值前或者方法上。

@RequestBody：支持将返回值放在 response 体内，而不是在直接链接在地址后面。此注解放置在参数前。

@PathVariable：用来接收路径参数，如/news/001，可接受001作为参数，次注解放在参数前。

@RestController：组合注解，组合了@Controller 和 ResponseBody，当只开发一个和页面交互数据的控制就需要使用这个注解。



demo13-SpringMVC-Annotation



### Spring MVC 基本配置

Spring MVC 的定制配置需要我们的配置类继承一个 WebMvcConfigurerAdapter类，并在这个类中使用@EnableWebMvc 注解来开启对 Spring MVC 的配置支持。

#### 静态资源映射

程序的静态文件(js，css，图片)等需要直接访问，这时我们可以在配置里重写addResourceHandlers方法实现。

```java
package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.demo")
public class SpringMVCConfig extends WebMvcConfigurerAdapter{

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/classes/views");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/"); //addResourceLocations指的是文件放置的目录，addResourceHandler指的是对外暴露的访问路径
    }
}
```



#### 拦截器配置

拦截器(Interceptor)实现对每一个请求处理前后进行相关的业务处理，类似于 Servlet 的 Filter。

可让普通的 Bean 实现 HandlerInterceptor 接口或继承 HandlerIntercptorAdapter 类来实现自定义拦截器。

通过重写 WebMvcConfigurerAdapter 的 addIntercptors方法来注册自定义的拦截器。



#### @ControllerAdvice

通过这个注解，可以将对于控制器的全局设置放置在同一个位置，注解了@Controller 的类的方法可使用@ExceptionHandler\@InitBinder\@ModelAttribute注解到方法上，这对所有注解了@RequestMapping 的控制器内的方法有效。

@ExceptionHandler：用于全局处理控制器里的异常

@InitBinder：用来设置 WebDataBinder，WebDataBinder用来自动绑定前台请求参数到 Model 中

@ModelAttribute：本来的作用是绑定键值对到 Model 中。此处是让全局的@RequestMapping 都能获得在此处设置的键值对



#### 其他配置

1. 快捷的 ViewController

   在配置类中重写 addViewControllers 来简化没有业务处理只是简单的页面转向的配置：

   ```java
   @Override
   public void addViewCOntroller(ViewControllerRegistry registry){
     registry.addViewController("/index").setViewName("/index");
   }
   ```

​	

2. 路径匹配参数配置

   在 Spring MVC 中，路径参数如果带"."的话，"."后面的值将会被忽略。

   ```java
   @Override
       public void configurePathMatch(PathMatchConfigurer configurer) { //重写configurePathMatch方法可以不忽略"."后面的内容
           configurer.setUseSuffixPatternMatch(false);
       }
   ```

   

### Spring MVC 的高级配置

#### 文件上传配置

Spring MVC 通过一个配置 MultipartResolver 来上传文件。

在控制器中，使用 MultipartFile file 来接收文件，通过 MultipartFile[] files 接收多个文件上传。



#### 自定义HttpMessageConverter

HttpMessageConverter是用来处理 request 和 response 里的数据的。

