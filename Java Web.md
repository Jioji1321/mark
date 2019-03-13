# Servlet

servlet 用于接收和响应用户的请求。

## 一个简单的 Servlet

```java
package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("Hello World");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet " + this.getServletName() + " is started....");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " is stopped....");
    }
}

```



web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <display-name>Hello World Application</display-name>

    <!--声明 Servlet-->
    <servlet>
        <servlet-name>helloServlet</servlet-name>
        <servlet-class>servlet.HelloServlet</servlet-class>
        <load-on-startup>1</load-on-startup> <!-- 在程序启动的时候立刻启动 Servlet -->
    </servlet>

    <!--将 Servlet 映射到 url-->
    <servlet-mapping>
        <servlet-name>helloServlet</servlet-name>
        <url-pattern>/greeting</url-pattern>
    </servlet-mapping>


</web-app>
```



## 使用 HttpServletRequest

HttpServletRequest 最大的作用，是从客户端发送的请求中获取参数



### 常用方法

getParameter()：获取返回的参数的第一个值；如果返回的参数有多个，则获取第一个参数

getParameterValues()：获取返回的参数的数组；如果返回的参数只有一个，则返回只有一个参数的数组

getParameterMap()：返回一个包含了所有参数值对的Map<String, String[]>

getParameterNames()：返回所有可用参数的枚举



不要在含有post变量的请求上使用以下方法：

getContentType()：返回请求的 MIME（多功能互联网拓展）内容类型

getContentLength()：返回请求正文的长度

getContentLengthLong()：返回请求正文的长度（内容长度超过2GB 的请求）

getCharacterEncoding()：返回请求内容的字符编码



getInputStream()：返回一个ServletInputStream 对象（如果是二进制格式的数据，就只能够用这个方法）

getReader()：返回一个BufferedReader 对象（如果是基于字符编码的数据，用这个方法是最简单的）

不能在同一个 post 请求上同时使用这两个方法



getequestURL()：返回客户端用于创建请求的完整 URL，包括协议（http，https），服务器名称、端口号和服务器路径，但不包含查询字符串（http://127.0.0.1:8080/helloworld/application/index.jsp?item=book的请求 => http://127.0.0.1:8080/helloworld/application/index.jsp）

getRequestURI()：返回URL中的服务器路径部分（http://127.0.0.1:8080/helloworld/application/index.jsp?item=book的请求 => /application/index.jsp）

getServletPath()：返回匹配 Servlet映射的路径部分（http://127.0.0.1:8080/helloworld/greeting => /greeting）

getHeader(String str)：返回指定名称的头数据。str 为属性名（getHeader("contenttype") => Content-Type头，如果有多个相同名称的属性，则该方法只会返回第一个属性值；可以用 getHeaders()来返回所有值的枚举）

getHeaderName()：返回请求中所有头数据的名称的枚举（用来遍历所有可用头数据的好方式）

getIntHeader()

getDateHeader()



getSession()

getCookie()



## 使用 HttpServletResponse

	### 编写响应正文

getOutputStream()：返回一个 ServletOutputStream（如果要发送一个二进制格式的响应则只能够用这种方法）

getWriter()：返回一个 PrintWriter

- 永远不要在同一个响应中同时使用以上两个方法，否则会报IllegalStateException异常



setContentType()

setCharacterEncoding()

设置相关的参数

- 需要在 getWriter之前调用以上两个方法。



setHeader()

setIntHeader()

setDateHeader()

- 以上三个方法都会覆盖原有的设置

addHeader()

addIntHeader()

addDateHeader()

- 会添加额外的值

getHeader()

getHeaders()

getHeaderNames()

containsHeader()

- 可以用来判断是否在响应中添加了某个响应头



setStatus()：设置 HTTP 响应状态码

getStatus()：判断当前响应的状态

sendError()：设置状态码，表示将一条可选的错误信息输出到响应数据中，重定向到Web 容器为客户端提供的错误页面并清空缓存

sendRedirect()：将客户端重定向到另一个 URL



## 使用参数和接受表单提交

```java
package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(
        name = "helloServlet",
        urlPatterns = {"/greeting"},
        loadOnStartup = 1
) //这个位置已经配置了 Servlet 的相关内容，所以在 web.xml中相关的配置内容需要注释
public class HelloServlet extends HttpServlet {

    private static final String DEFAULT_USER = "Guest";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().print("Hello World");

        String user = req.getParameter("user");
        if(null == user){
            user = HelloServlet.DEFAULT_USER;
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                    .append("<header>\r\n")
                        .append("<title>Hello User Application</title>\r\n")
                    .append("</header>\r\n")
                    .append("<body>\r\n")
                        .append("Hello, ") .append(user).append("! <br/><br/>\r\n")
                        .append("<form action=\"/greeting\"> method=\"post\">\r\n")
                            .append("Enter your name: <br/>\r\n")
                            .append("<input type=\"text\" name=\"user\" /><br/>\r\n")
                            .append("<input type=\"submit\" value=\"提交\" />\r\n")
                        .append("</form>\r\n")
                .append("</body>\r\n")
            .append("</html>\r\n");



    }

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet " + this.getServletName() + " is started....");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " is stopped....");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

}
```



接受多个参数

```java
package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "multiValueParameterServlet",
        urlPatterns = {"/checkedBoxes"},
        loadOnStartup = 1
)
public class MultiValueParameterServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                    .append("<header>\r\n")
                        .append("<title>Hello User Application</title>\r\n")
                    .append("</header>\r\n")
                    .append("<body>\r\n")
                        .append("<form action=\"checkedBoxes\" method=\"post\">\r\n")
                            .append("Select the food you like: <br/>\r\n")
                                .append("<input type=\"checkbox\" name=\"fruit\" value=\"banana\" /><br/>\r\n")
                                .append("banana<br/>")
                                .append("<input type=\"checkbox\" name=\"fruit\" value=\"apple\" /><br/>\r\n")
                                .append("apple<br/>")
                                .append("<input type=\"checkbox\" name=\"fruit\" value=\"grape\" /><br/>\r\n")
                                .append("grape<br/>")
                                .append("<input type=\"checkbox\" name=\"fruit\" value=\"orange\" /><br/>\r\n")
                                .append("orange<br/>")
                                .append("<input type=\"checkbox\" name=\"fruit\" value=\"pear\" /><br/>\r\n")
                                .append("pear<br/>")
                                .append("<input type=\"submit\" value=\"提交\" />\r\n")
                        .append("</form>\r\n")
                    .append("</body>\r\n")
                .append("</html>\r\n");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] fruits = req.getParameterValues("fruit");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                    .append("<header>\r\n")
                        .append("<title>Hello User Application</title>\r\n")
                    .append("</header>\r\n")
                .append("<body>\r\n")
                    .append("<h2>Your selections</h2>");

        if(fruits == null){
            writer.append(" You didn't select any fruits! ");
        }else {
            writer.append("<ul>\r\n");
            for(String fruit: fruits){
                writer.append("<li>").append(fruit).append("</li>\r\n");
            }
            writer.append("</ul>\r\n");
        }
        writer.append("</body>\r\n")
                .append("</html>\r\n");
    }
}
```



### 使用初始化参数配合应用程序

#### 使用上下文初始化参数

- 在 web.xml中使用<context-param>标签声明上下文初始化参数

  ```xml
  <!--使用上下文初始化参数-->
  <context-param>
  	<param-name>settingOne</param-name>
  	<param-value>foo</param-value>
  </context-param>
  
  <context-param>
  	<param-name>settingTwo</param-name>
  	<param-value>bar</param-value>
  </context-param>
  ```

  上面的配置可以在 Servlet 的任何位置使用

  ```java
  @WebServlet(
  	name = "contextParameterServlet",
      urlPatterns = {"/contextParameterServlet"}
  )
  public class ContextParameterServlet extends HttpServlet
  {
  	@Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      	ServletContext c = this.getServletContext();
      	PrintWriter writer = resp.getWriter();
      	
      	writer.append("settingOne: ").append(c.getInitParameter("settingOne"))
      		.append(", settingTwo: ").append(c.getInitParameter("settingTwo"));
      }
  }
  ```

  应用程序中的所有 servlet 都将共享这些初始化参数，在所有的 servlet 中这些值都是相同的；有时需要使某一个设置只作用于某一个 servlet，那么就需要配置这些初始化参数。



#### 使用 Servlet 初始化参数

 在 web.xml中进行相关配置

```xml
<!--使用 Servlet 初始化参数-->
    <servlet>
        <servlet-name>servletParameterServlet</servlet-name>
        <servlet-class>servlet.ServletParameterServlet</servlet-class>
        <init-param>
            <param-name>database</param-name>
            <param-value>CustomSupport</param-value>
        </init-param>
        <init-param>
            <param-name>server</param-name>
            <param-value>10.13.1995</param-value>
        </init-param>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>servletParameterServlet</servlet-name>
        <url-pattern>/servletParameterServlet</url-pattern>
    </servlet-mapping>
```



servlet 的写法

```java
package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletParameterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig config = this.getServletConfig();
        PrintWriter writer = resp.getWriter();

        writer.append("database: ").append(config.getInitParameter("database"))
                .append(", server: " + config.getInitParameter("server"));
    }
}
```



使用注解的方式完成 servlet 配置初始化参数

```java
package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//使用注解的方式完成 servlet 配置初始化参数
@WebServlet(
        name = "servletParameterServlet",
        urlPatterns = {"/servletParameterServlet"},
        initParams = {
                @WebInitParam(name = "database", value = "CustomSupport"),
                @WebInitParam(name = "server", value = "10.13.1995")
        }
)
public class ServletParameterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig config = this.getServletConfig();
        PrintWriter writer = resp.getWriter();

        writer.append("database: ").append(config.getInitParameter("database"))
                .append(", server: " + config.getInitParameter("server"));
    }
}
```



### 通过表单上传文件

#### 配置Servlet 支持文件上传

```java
package servlet;


import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(
        name = "ticketServlet",
        urlPatterns = {"/ticketServlet"},
        loadOnStartup = 1
)
@MultipartConfig(
        fileSizeThreshold = 5242880, //5MB
        maxFileSize = 20971520L, //20MB
        maxRequestSize = 41943040L //40MB
)
public class TicketServlet extends HttpServlet {
    private volatile int TICKET_ID_SEQUENCE = 1;
    private Map<String,Ticket> ticketDataBase = new LinkedHashMap<String, Ticket>();
}

```

@MultipartConfig：告诉 Web 容器为 servlet 添加文件上传支持

特性：

 1. location：告诉浏览器在哪里储存临时文件

 2. fileSizeThreshold：告诉 Web 容器必须达到多大的容量才能够被写入到临时目录中

    本例中，小于5MB 的文件将会被保存在内存中，直到请求完成后，由垃圾回收器回收

    对于超过5MB 的文件，会被保存在location 指定的文件目录中，在请求完成后，容器将会从硬盘上删除

3. maxFileSize：设置禁止上传超过20MB 的文件
4. maxRequestSize：设置禁止超过40MB的请求



处理客户端浏览器的下载请求：

```java
protected void downloadAttachment(HttpServletRequest req, HttpServletResponse resp, Attachment attachment) throws IOException {
        resp.setHeader("Content-Desposition", "attachment; filename=" + attachment.getName());
        resp.setContentType("application/octet-stream");

        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(attachment.getContents());
    }
```

​	响应中设置的头Content-Desposition，将强制询问是保存文件还是下载文件，而不是在线打开文件

​	对于大文件的处理，该代码可能出现内存出现。如果要下载大文件的话，不应该是保存在内存中，而是应该将数据从文件的 InputStream 中复制到ResponseOutputStream 中，且应该经常刷新，这样数据才能够不断地发到客户浏览器，而不是全部缓存在内存中。



#### 接收文件上传

```java
// POJO
package pojo;

public class Ticket {
    private String customName;
    private String subject;
    private String body;


    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}


package pojo;

public class Attachment {
    private String name;
    private byte[] contents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}

```

servlet类：

```java
package servlet;


import pojo.Attachment;
import pojo.Ticket;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(
        name = "ticketServlet",
        urlPatterns = {"/ticketServlet"},
        loadOnStartup = 1
)
@MultipartConfig(
        fileSizeThreshold = 5242880, //5MB
        maxFileSize = 20971520L, //20MB
        maxRequestSize = 41943040L //40MB
)
public class TicketServlet extends HttpServlet {
    private volatile int TICKET_ID_SEQUENCE = 1;
    private Map<Integer,Ticket> ticketDataBase = new LinkedHashMap<Integer, Ticket>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action == null){
            action = "list";
        }

        switch(action){
            case "create":
                this.showTicketForm(resp);
                break;
            case "view":
                this.viewTicket(req,resp);
                break;
            case "download":
                this.downloadAttachment(req,resp);
                break;
            default:
                this.listTickets(resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action == null){
            action = "list";
        }

        switch(action){
            case "create":
                this.createTicket(req,resp);
                break;
            case "download":
            default:
                resp.sendRedirect("tickets");
                break;
        }
    }


    private void downloadAttachment(HttpServletRequest req, HttpServletResponse resp, Attachment attachment)
            throws IOException, ServletException {
        resp.setHeader("Content-Desposition", "attachment; filename=" + attachment.getName());
        resp.setContentType("application/octet-stream");

        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(attachment.getContents());
    }

    private void createTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        Ticket ticket = new Ticket();

        ticket.setCustomName(req.getParameter("customName"));
        ticket.setSubject(req.getParameter("subject"));
        ticket.setBody(req.getParameter("body"));

        Part filePart = req.getPart("file1");

        if(filePart != null){
            Attachment attachment = this.processAttachment(filePart);
            if(attachment != null){
                this.addAttachment(attachment);
            }
        }

        int id;
        synchronized (this){
            id = this.TICKET_ID_SEQUENCE++;
            this.ticketDataBase.put(id, ticket);
        }

        resp.sendRedirect("tickets?action=view&ticketId=" + id);

    }

    private Attachment processAttachment(Part filePart) throws IOException{
        InputStream inputStream = filePart.getInputStream();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int read;
        final byte[] bytes = new byte[1024];

        while((read = inputStream.read(bytes)) != -1){
            byteArrayOutputStream.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();

        attachment.setName(filePart.getSubmittedFileName());
        attachment.setContents(byteArrayOutputStream.toByteArray());

        return attachment;


    }


}
```



### 编写多线程安全的应用程序

#### 线程池

​	当容器接受请求时，将会在池中寻找可用的线程。如果找不到并且线程池中的连接数已经达到最大值，那么该请求会被放入一个队列中（先进先出），等待获得可用的线程。

​	Tomcat中最大线程池大小默认为200。

#### 保护共享资源

​	1. 用 volatile 关键字来保证其他线程时刻都可以读取变量修改后的最终值。

​	2. 使用同步代码块（synchronized）





## 使用 JSP 显示页面内容

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello World Application</title>
    </head>
    <body>
        Hello, World!
    </body>
</html>
```



### 指令，声明，脚本和表达式

```jsp
<%@ 这是一个指令 %>
<%! 这是一个声明 %>
<% 这是一个脚本 %>
<%= 这是一个表达式 %>
```



1. 使用指令

   指令用于只是 JSP 解释器执行某个操作（例如设置内容类型）或者对文件作出假设（例如使用的是那种脚本语言）、导入类、在转换时包含其他 JSP 或者包含JSP标签库

2. 使用声明

   声明用于在 JSP Servlet 类的范围内声明一些东西，例如可以定义实例变量、方法或者声明标签中的类

3. 使用脚本

4. 使用表达式



```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
private final int five = 0;

protected String cowboy = "rodeo"
    //下面是赋值语句而不是声明语句，如果未注释的话会出现语法错误
    //cowboy = "test";

    public long addFive(long number){
    return number + 5L;
}

public class MyInnerClass{
    
}

MyInnerClass instanceVariable = new MyInnerClass();
//WeirdClassWithinMethod在方法作用域内，所以如果未注释的话，下面的声明将出现语法错误
//WeirdClassWithinMethod bad = new WeirdClassWithinMethod();
%>
<%
	class WeirdClassWithinMethod
    {
        
    }
	WeirdClassWithinMethod weirdClass = new WeirdClassWithinMethod();
	MyInnerClass innerClass = new MyInnerClass();
	int seven;
	seven = 7;
%>
<%= "Hello World" %>
<%= addFive(12L) %>
```



### 注释代码

`<!-- -->`

`//`

`/**/`

`<%-- --%>`



### 在 JSP 中导入类

`<%@ page import="java.util.*, java.io.IOException" %>`



### 使用指令

1. 修改页面属性

   - pageEncoding：

     指定 JSP 所使用的字符编码，等同于 HttpServletResponse 中的setCharacterEncoding 方法。可以在 page 指令中使用 contentType=“texr/html” pageEncoding="UTF-8"

   - session:

     值只能为 true 或者 false 中的一种，表示 JSP 是否参与 HTTP 会话。默认为 true。

   - isELIgnored：

     表示 JSP 编译器是否将解析和转换JSP 中的表达式语言（EL）

   - buffered 和 autoFlush：

     二者紧密相连，默认值为“8KB”和 true。决定了 JSP 的输出方式，是在生成之后立即发送到浏览器中，还是先将输出缓存起来，在按批次发送到浏览器。buffer指定了 JSP 缓存的大小，或者为“none”（不会缓存任何输出）；autoFlush表示是否在它达到大小限制之后自动刷新缓存。

   - errorPage：

     指定执行错误之后需要将请求转发到哪个 JSP

   - isErrorPage：

     指定当前的 JSP 是否被用作错误页面（默认为 false），如果设置为 true，在该 JSP 中可以使用隐式的 exception 变量

   - isThreadSafe：

     默认为 true，表示当前的 JSP 可以安全的同时处理多个请求

   - extends：

     指定当前 JSP Servlet 的父类（不要使用）



2. 包含其他 JSP

   `<%@ include file="/path/to/some/file.jsp" %>`  (该方式是静态（转换时）的方式包含)

   `<jsp:include page="/path/to/some/file.jsp" />`（该方式是动态（运行时）的方式包含）

3. 包含标签库

   taglib 指令

   `<% taglib url="http://java.sun.com/jsp/jstl/core" prefix="c" %>`


### 使用\<jsp>标签

\<jsp:forward>：使用该标签可以将当前 JSP 正在处理的一些请求转发值其他 JSP。与\<jsp:include>不同的是，被转发的请求不会再返回到原始 JSP 中

`<% jsp:forward page="/path/to/some/file.jsp" %>`

在此标签之后的代码都将会被忽略。



`<jsp:useBean>`： 在页面中声明一个 JavaBean

`<jsp:getProperty>` ： 从上面声明的 JavaBean 获取属性值（通过 getter 方法）

`<jsp:setProperty>` ： 设置该实例的属性



这种声明bean 方式的好处是，对其他的 jsp 标签都是可见的



### 在 JSP 中使用 Java 代码（以及不鼓励使用 Java 的原因）

#### 使用 JSP 中隐式的变量

1. request 和 response

   request 是 HttpServletRequest 的实例，而 response 是 HttpServletResponse的实例。

2. session

   session 是 HttpSession 的实例

3. out

   out 是 JspWriter 的一个实例，在所有 JSP 中都可以使用。

4. application

   是 ServletContext 接口的一个实例。该接口提供了对 Web 应用程序配置的访问，包括上下文初始化参数。

5. config

   是 ServletConfig 接口的一个实例，可以使用该对象访问 JSP Servlet 的配置（Servlet 的初始化参数）

6. pageContext

   是 PageContext 类的一个实例，提供了获取请求特性和会话特性值、访问请求和响应、包含其他文件、转发请求的几个便利的方法（在自定义 JSP标签时使用）

7. page

   是 java.lang.Object的一个实例，代表了 JSP Servlet 对象的 this 变量。可以将它强转为 Servlet 接口对象，也可以强转为 JspPage 和 HttpJspPage 接口的对象。

8. exception

   处理异常的隐式变量，创建一个 isErrorPage 被设置成 true 的 JSP，就会在页面中自动定义一个隐式变量 exception，类型为 Throwable。

9. 使用隐式变量

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private static final String DEFAULT_USER = "Guest";
%>
<%
    String user = request.getParameter("user");
    if(user == null)
        user = DEFAULT_USER;
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello User Application</title>
    </head>
    <body>
        Hello, <%= user %>!<br /><br />
        <form action="greeting.jsp" method="POST">
            Enter your name:<br />
            <input type="text" name="user" /><br />
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
```



```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello User Application</title>
    </head>
    <body>
        <form action="checkboxesSubmit.jsp" method="POST">
            Select the fruits you like to eat:<br />
            <input type="checkbox" name="fruit" value="Banana" /> Banana<br />
            <input type="checkbox" name="fruit" value="Apple" /> Apple<br />
            <input type="checkbox" name="fruit" value="Orange" /> Orange<br />
            <input type="checkbox" name="fruit" value="Guava" /> Guava<br />
            <input type="checkbox" name="fruit" value="Kiwi" /> Kiwi<br />
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>

```



```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String[] fruits = request.getParameterValues("fruit");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello User Application</title>
    </head>
    <body>
        <h2>Your Selections</h2>
        <%
            if(fruits == null)
            {
        %>You did not select any fruits.<%
            }
            else
            {
        %><ul><%
                for(String fruit : fruits)
                {
                    out.println("<li>" + fruit + "</li>");
                }
        %></ul><%
            }
        %>
    </body>
</html>

```



```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello User Application</title>
    </head>
    <body>
        settingOne: <%= application.getInitParameter("settingOne") %>,
        settingTwo: <%= application.getInitParameter("settingTwo") %>
    </body>
</html>

```



### 结合使用 servlet 和 jsp

看代码



#### 

# 杂记

@SuppressWarning()注解：消除编辑器产生的警告信息



```java
public void doGet(HttpServletRequest request, HttpServletResponse respones) throws ServletException, IOException {
    //检查用户是否已经登录(username 属性是否存在)
    HttpSession session = request.getSession();
    if(session.getAttribute("username") ！= null){
        //如果已登录，就重定向到票据页面
        response.sendRedirect("tickets");
        return;
    }
    //如果未登录，就将请求属性 loginFailed 设置为 false，然后将请求转发至登录jsp。
    request.setAttribute("loginFailed", false);
    request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
}

//当登录 jsp 的登录表单被提交时，请求被发送至 doPost()方法
public void doPost(HttpServletRequest request, HttpServletResponse respones) throws ServletException, IOException {
    //检查用户是否已经登录(username 属性是否存在)
    HttpSession session = request.getSession();
    if(session.getAttribute("username") ！= null){
        //如果已登录，就重定向到票据页面
        response.sendRedirect("tickets");
        return;
    }
    
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    
    if(username == null || password == null || !LoginServlet.equals(LoginServlet.userDataBase.containsKey(username)) || !password.equals(LoginServlet.equals(LoginServlet.userDataBase.get(username))) {
        request.setAttribute("loginFailed", true);
    	request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
    }
       else{
           session.setAttribute("username",username);
           request.changeSessionId();
           response.sendRedirect("tickets");
       }
}
```





## EL 表达式：

1. 立即执行

   `${expr} ` : 格式

   如果通过该语法向响应中输出一些内容，则需要将美元符号转义：

   `\${not a EL expression}` 

2. 延迟执行

   `#{expr}` : 格式

   在 JSP中，#{}延迟执行语法只是一个有效的JSP 标签属性，用于将 EL 表达式的执行推迟到标签的渲染过程中。

3. 访问属性

   `${shirt["size"]}`



## Java 标准标签库

1. 核心（c，core）
2. 格式化（fmt）
3. 函数（fn）
4. SQL（sql）
5. XML（x）



### 使用核心标签库

`<$@ taglib prefix="c" url="http://java.sun.com/jsp/jstl/core">`

1. \<c:out> 

```jsp
<c:out value="${someVariable}" />
```

2. \<c:url>

```jsp
<c:url value="127.0.0.1/example/hello.jsp">
    <c:param name="story" value="${storyId}" />
</c:url>

<a href="/view.jsp?formId=12">Product Id</a>
<!-- 等价于 -->
<a href="<c:url value="/view.jsp">
    <c:param name="formId" value="12" />
</c:url>">Product Id</a>


```

3. \<c:if>

```jsp
<c:if test="${someComplexExpressionIsTrue}" var="itWasTrue"/>
...
<c:if test="${itWasTrue}">
	do something
</c:if>
...
<c:if test="${itWasTrue}">
	do something else
</c:if>
```

4. \<c:choose>/\<c:when>/\<c:otherwise>

​	\<c:when>中有一个属性 test，代表了进行测试的条件；

​		如果为 true，就执行\<c:when>中内嵌的内容

```jsp
<c:choose>
	<c:when test="${something}">
    	"if"
    </c:when>
    <c:when test="${somethingElse}">
    	"else if"
    </c:when>
    ...
    <c:otherwise>
    	"else"
    </c:otherwise>
</c:choose>
```

5. \<c:forEach>

```jsp
for(int i=0; i < 100; i++)
{
    out.println("Line " + i "<br />);
}
    
    //等价于
<c:forEach var="i" begin="0" end="100" step="3">
    Line ${i}<br />
</c:forEach>
```

 	6. \<c:redirect>

