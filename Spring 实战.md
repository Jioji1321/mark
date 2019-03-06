# DI 依赖注入
作用：解耦合

构造器注入： 通过构造方法注入


# AOP 面向切面编程
- 系统有多个组件构成，不同的组件实现不同的功能。这些组件除了实现自身特性核心的功能之外，还经常承担起额外的任务，比如日志、事务管理和安全等系统服务。这些系统服务就被称为横切关注点，因为他们会跨越系统的多个组件。
- 如果将这些系统服务分散到多个组件中去，代码会造成双重的复杂性：
    1. 如果你要针对组件完成相应的逻辑，则需要改变切入点的相关实现；
    2. 会造成组件代码的混乱，无法只实现相应的功能；

- AOP 编程的作用就是将这些系统服务模块化，使之与组件分离，从而让组件拥有更高的内聚性同时也只专注于核心功能的实现。确保 POJO（Plain Ordinary Java Object，简单 Java 对象）的简单性。

---

# bean装载到Spring 应用上下文的生命周期
1. Spring 对 bean 进行实例化
2. Spring 将值和bean 的引用注入到bean 对应的属性中
3. 如果 bean 实现了***BeanNameAware***接口，Spring 将bean的 ID 值传给 setBeanName()方法
4. 如果 bean 实现了***BeanFactoryAware***接口，Spring将调用 setBeanFactory()方法，将 BeanFactory 容器实例传入
5. 如果 bean 实现了***ApplicationContextAware***接口，Spring将调用 setApplicationContext()方法，将bean 所在的应用上下文引用传入
6. 如果 bean 实现了***BeanPostProcessor***接口，Spring 将调用它们的postProcessBeforeInitialization()方法
7. 如果 bean 实现了***InitilizingBean***接口，Spring 将调用它们的afterPropertiesSet()方法，类似的，如果bean 使用 init-method 声明初始化方法，该方法也会被调用
8. 如果 bean 实现了***BeanPostProcessor***接口，Spring 将调用它们的postProcessAfterInitialization()方法 
9. 此时，bean 已经准备就绪，可以被应用程序使用了，他们将一直驻留在应用上下文中，直到应用上下文被销毁。
10. 如果 bean 实现了***DisposableBean***接口，Spring 将调用他们的 destory()接口方法，同样，如果 bean 使用destory-method声明销毁方法，该方法也会被调用

---