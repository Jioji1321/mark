# 流

## 关闭流

```java

```





## 缓冲流

### BufferedInputStream/BufferedOutputStream构造函数

```java
public BufferedInputStream(InputStream in)
public BufferedInputStream(InputStream in, int bufferSize) 

public BufferedOutputStream(OutputStream out)
public BufferedOutputStream(OutputStream out, int bufferSize) 
```



第一个参数是底层流，可以从中读取未缓冲的数据，或者向其写入缓冲的数据

如果指定第二个参数，他会指定缓冲区中的字节数

如果不指定第二个参数，输入流的缓冲区大小设置为2048个字节，输出流的缓冲区大小设置为512个字节



​	