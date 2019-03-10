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

​	







