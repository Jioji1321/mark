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



