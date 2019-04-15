# 基本结构

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
				
      	<!-- 导包 -->
        <script src="lib/vue.js"></script> 
    </head>

    <body>
      	<!-- 创建容器 -->
        <div id="app">
            <p>{{ msg }}</p>
        </div>

        <script>
          	// 创建 Vue 实例
            var vm = new Vue({
                el: '#app', //指的是 Vue 实例控制的元素区域
                data: { //储存 Vue 实例操作的数据
                    msg: '12345'
                },
              	methods: {
                		//事件方法
              	},
            })
        </script>
    </body>
</html>
```



# 常用指令

插值表达式和v-text 的区别：

- 默认 v-text 指令不会存在闪烁问题 

- 插值表达式可以在标签中加上任意内容而不会被覆盖，即只会替换这个占位符中的内容；v-text 会覆盖元素中原有的内容



v-cloak：解决插值表达式的闪烁问题

v-bind：Vue 的属性绑定机制，缩写为"："

v-on：Vue 的事件绑定机制，缩写为"@"

v-html：将文本解析为 html 格式



```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>

        <script src="lib/vue.js"></script>

        <style>
            [v-cloak]{
                display: none;
            }
        </style>
    </head>

    <body>
        <div id="app">
            <!-- v-cloak是一个指令能够解决插值表达式因加载速度问题而闪烁的问题 -->
            <p v-cloak>{{ msg }}</p> 

            <!-- v-text和插值表达式的作用相似 -->
            <h4 v-text="msg"></h4>

            <!-- 默认 v-text 指令不会存在闪烁问题 -->
            <!-- 插值表达式可以在标签中加上任意内容而不会被覆盖，即只会替换这个占位符中的内容；v-text 会覆盖元素中原有的内容 -->
            <div>_______{{msg}}------------</div>
            <div v-text="msg">+++++++++++++</div>

            <!-- v-html 指令可以将内容解析成 html 格式输出到页面上 -->
            <div v-html="msg2"></div>

            <!-- v-bind 指令是用来提供属性绑定的指令 -->
            <!-- v-bind 指令会将引号中的内容解析为 JS表达式 -->
            <input type="button" value="按钮" title="mytitle">

            <input type="button" value="按钮" v-bind:title="mytitle + '123'">
            <input type="button" value="按钮" :title="mytitle + '123'"> <!-- 简写形式 -->

            <br><br>

            <!-- v-on : 事件绑定机制 -->
            <input type="button" value="按钮" :title="mytitle2" v-on:click="show">
            <input type="button" value="按钮" :title="mytitle2" v-on:mouseover="show">



        </div>

        <script>
            var vm = new Vue({
                el: '#app',
                data: {
                    msg: '12345',
                    msg2: '<h1>suousouosuosusousosuosus</h1>',
                    mytitle: '这是一个自定义的标题',
                    mytitle2: '点击弹框'
                },
                methods: { //这个 methods 属性里定义了当前 Vue 实例所有可用的方法
                    show: function(){
                        alert('hello!')
                    }
                }
            })

            // 使用 DOM 操作
            // document.getElementById("btn").onclick = function(){
            //     alert("hello");
            // }
        </script>
    </body>
</html>
```



## 点击事件案例：跑马灯

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>

        <script src="lib/vue.js"></script>
    </head>

    <body>
        <div id="app">
            <input type="button" value="开启效果" @click="turnOn">
            <input type="button" value="关闭效果" @click="turnOff">
            <input type="button" value="重置效果" @click="reset">
            <h4>{{ msg }}</h4>
        </div>

        <script>
            // 如果需要使用 vm 实例中 data 属性里的数据，或者调用 methods 中的方法，必须通过 this.属性名 或者 this.方法名来访问。
            // 这里的 this 指的就是我们创建出来的 Vue 实例
            var vm = new Vue({
                el: '#app',
                data: {
                    msg: '猥琐发育，别浪!',
                    IntervalId: null //在 data 中赋值 IntervalId 定时器 Id
                },
                methods: {
                    turnOn: function(){
                        // console.log(this.msg)

                        //判断此时是否存在定时器，如果存在则返回
                        if(this.IntervalId != null){
                            return
                        }
                        
                        // var start = this.msg.substring(0,1)
                        // var end = this.msg.substring(1)
                        // this.msg = end + start

                        // // 添加定时器
                        // var _this = this // this 对象不明确，需要复制一份以作区分
                        // setInterval(function(){
                        //     var start = _this.msg.substring(0,1)
                        //     var end = _this.msg.substring(1)
                        //     _this.msg = end + start
                        // }, 400)

                        // 箭头表达式写法（避免了 this 对象的不明确）
                        this.IntervalId = setInterval( () => {
                            var start = this.msg.substring(0,1)
                            var end = this.msg.substring(1)
                            this.msg = end + start
                        }, 400)
                    },
                    turnOff() {
                        // 清除定时器
                        clearInterval(this.IntervalId)
                        //将定时器 id 重新赋值为 null
                        this.IntervalId = null
                    },
                    reset() {
                        this.turnOff()
                        this.msg = '猥琐发育，别浪!'
                    }
                    
                }
            })
        </script>
    </body>
</html>
```



## 事件修饰符

事件修饰符可以串联使用

- .stop：阻止冒泡
  - 冒泡：指的是如果外层也有相同的事件时，先调用自身的事件之后也会调用外部的事件，称为"冒泡"
- .prevent：阻止默认事件
- .capture：添加事件监听器时使用事件捕获模式
  - 事件捕获模式：按照文本的解析顺序进行，先捕获的事件先运行
- .self：只当事件在该元素本身(比如不是子元素)触发时触发事件
  - 使用 self 修饰符之后，就不会存在冒泡或者捕获的问题
- .once：只触发一次



.stop和.self的区别：

​	.self只会阻止自身事件的冒泡行为，不会真正阻止冒泡行为



```html
<!DOCTYPE html>
<!-- 
    事件修饰符
    .stop:阻止冒泡
    .prevent:阻止默认事件
    .capture:添加事件侦听器时使用事件捕获模式
    .self:只当事情在该元素本身（比如不是子元素）触发时触发回调
    .once:时间只能触发一次
 -->
<html>
    <head>
        <meta charset='utf-8'>
        <title></title>
        <!-- 引入vue.js -->
        <script src='https://cdn.jsdelivr.net/npm/vue/dist/vue.js'></script>
        <style>
            .inner{
                width: 100%;
                height: 150px;
                background: lawngreen;
            }
        </style>
    </head>
    <body>
        <div id='app'>
            <h2>原来的（有冒泡）</h2>
            <div class="inner" @click="div1Handler">
                <input type="button" value="点我" @click="btnHandler">
            </div>

            <h2>.stop阻止冒泡</h2>
            <div class="inner" @click="div1Handler">
                <!-- 使用.stop阻止冒泡 -->
                <input type="button" value="点我" @click.stop="btnHandler">
            </div>

            <h2>.prevent阻止默认行为</h2>
            <a href="http://baidu.com" @click.prevent="linkClick">百度一下</a>

            <h2>.capture:添加事件侦听器时使用事件捕获模式</h2>
            <!-- 先外层后内层 -->
            <div class="inner" @click.capture="div1Handler">
                <input type="button" value="点我" @click="btnHandler">
            </div>

            <h2>.self:只当事情在该元素本身（比如不是子元素）触发时触发回调</h2>
            <div class="inner" @click.self="div1Handler">
                <input type="button" value="点我" @click="btnHandler">
            </div>

            <h2>.once:时间只能触发一次</h2>
            <div class="inner" @click.once="div1Handler">
                <input type="button" value="点我" @click="btnHandler">
            </div>
            <a href="http://baidu.com" @click.prevent.once="linkClick">百度一下</a>

        </div>
    </body>
    <script>
        // 实例化vue对象
        let vm = new Vue({
            // 绑定对象
            el:'#app',
            data:{
                
            },
            methods:{
                div1Handler(){
                    console.log("这是触发了div1的click事件")
                },
                btnHandler(){
                    console.log("这是触发了btn的click事件")
                },
                linkClick(){
                    console.log("点击了百度一下")
                }
            }
        })
    </script>
</html>
```





## v-model和双向数据绑定

双向数据绑定，当 data 改变时，视图也会相应发生改变，反之亦然。

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>


    <script src="lib/vue.js"></script>
  </head>


  <body>
    <div id="app">
      <h4>{{ msg }}</h4>

      <!-- v-bind 只能实现数据的单向绑定，从 M 到 V -->
      <!-- <input type="text" v-bind:value="msg" style="width: 90%;"> -->

      <!-- 使用 v-model 可以实现数据的双向绑定 -->
      <!-- v-model 只能运用在表单元素中 -->
      <!-- input(radio, text, address, email...), select, textarea, checkbox -->
      <input type="text" v-model:value="msg" style="width: 90%;">
    </div>


    <script>
      var vm = new Vue({
        el: '#app',
        data: {
          msg: '测试1',

        },
        methods: {


        },
      })
    </script>
  </body>
</html>
```



### 实例：简易计算器

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        

        <script src="lib/vue.js"></script>
    </head>
    

    <body>
        <div id="app">
            <input type="text"  id="firstNum" v-model:value="num1">

            <select v-model="opt">
                <option value="+">+</option>
                <option value="-">-</option>
                <option value="*">*</option>
                <option value="/">/</option>
            </select>

            <input type="text" id="secondNum" v-model:value="num2">
            <input type="button" value="=" @click="calc">
            <input type="text" id="result" v-model:value="result">


        </div>
        

        <script>
            var vm = new Vue({
                el: '#app',
                data: {
                    num1: 0,
                    num2: 0,
                    result: 0,
                    opt: '+'
                },
                methods: {
                    calc(){
                        // if(this.opt == '+'){
                        //     this.result = this.add()
                        // }
                        // else if(this.opt == '-'){
                        //     this.result = this.subtract()
                        // }
                        // else if(this.opt == '*'){
                        //     this.result = this.multiply()
                        // }
                        // else {
                        //     this.result = this.divide()
                        // }
                        switch (this.opt){
                            case '+':
                                this.result = this.add()
                                break
                            case '-':
                                this.result = this.subtract()
                                break
                            case '*':
                                this.result = this.multiply()
                                break
                            case '/':
                                this.result = this.divide()
                                break
                        }
                    },
                    add(){
                        return parseInt(this.num1) + parseInt(this.num2);
                    },
                    subtract(){
                        return parseInt(this.num1) - parseInt(this.num2);
                    },
                    multiply(){
                        return parseInt(this.num1) * parseInt(this.num2);
                    },
                    divide(){
                        if(this.num2 == 0){
                            return 'error';
                        }
                        return parseInt(this.num1) / parseInt(this.num2);
                    },
                },
            })
        </script>
    </body>
</html>
```



## v-for 指令和 key 属性

1. 迭代数组

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <script src="lib/vue.js"></script>
    </head>
    <body>
        <div id="app">
            <!-- 使用 v-for 指令遍历普通数组 -->
            <p v-for="item in list">{{ item }}</p>
            <!-- 添加索引 -->
            <p v-for="(item, i) in list">索引值{{ i }} --- {{ item }}</p>

        </div>
        <script>
            var vm = new Vue({
                el: '#app',
                data: {
                    list: [1,2,3,4,5,6]
                },
                methods: {
                },
            })
        </script>
    </body>
</html>
```



2. 迭代对象数组

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="lib/vue.js"></script>
</head>

<body>
    <div id="app">
        <p v-for="user in list">用户 id 为{{ user.id }} --- 用户名为{{ user.name }}</p>
        <!-- 添加索引 -->
        <p v-for="(user, i) in list">用户 id 为{{ user.id }} --- 用户名为{{ user.name }} --- 索引值为{{ i  }}</p>
    </div>
    <script>
        var vm = new Vue({
            el: '#app',
            data: {
                list: [
                    {
                        id: 1,
                        name: "zs1"
                    },
                    {
                        id: 2,
                        name: "zs2"
                    },
                    {
                        id: 3,
                        name: "zs3"
                    },
                    {
                        id: 4,
                        name: "zs4"
                    },
                ]
            },
            methods: {
            },
        })
    </script>
</body>

</html>
```



3. 迭代对象中的属性

<!-- 在遍历对象身上的键值对时，除了有 val 和 key，在第三个位置还有索引值 -->



```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="lib/vue.js"></script>
</head>

<body>
    <div id="app">
        <!-- 在遍历对象身上的键值对时，除了有 val 和 key，在第三个位置还有索引值 -->
        <p v-for="(val, key) in user">键是： {{ key }} --- 值是： {{ val }}</p>

        <p v-for="(val, key, i) in user">键是： {{ key }} --- 值是： {{ val }} --- 索引为： {{ i }}</p>
    </div>
    <script>
        var vm = new Vue({
            el: '#app',
            data: {
                user: {
                    id: 1,
                    name: '李大雯',
                    age: '18',
                    gender: '女'
                }
            },
            methods: {
            },
        })
    </script>
</body>

</html>
```



4. 迭代数字

<!-- 如果 in 后面接的是普通数字，则 count 的值从 1 开始 -->



```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="lib/vue.js"></script>
</head>

<body>
    <div id="app">
        <!-- 如果 in 后面接的是普通数字，则 count 的值从 1 开始 -->
        <p v-for="count in 10">{{ count }}</p>
    </div>
    <script>
        var vm = new Vue({
            el: '#app',
            data: {
            },
            methods: {
            },
        })
    </script>
</body>

</html>
```



### key属性的使用

<!-- 使用 v-for 循环，key属性只能使用 numbere 和 string -->
<!-- 使用 key 时，必须使用 v-bind 绑定属性，指定 key 的值 -->
<!-- 在组件中使用 v-for 循环的时候，或者在一些特殊的情况下，如果出现唯一性问题，则需要在使用 v-for 的同时，指定唯一的数字/字符串类型的 :key 值 -->



```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="lib/vue.js"></script>
</head>

<body>
    <div id="app">
        <div>
            <input type="text" v-model="id">
            ---
            <input type="text" v-model="name">
            <input type="button" value="添加" @click="add">
        </div>

        <!-- 使用 v-for 循环，key属性只能使用 numbere 和 string -->
        <!-- 使用 key 时，必须使用 v-bind 绑定属性，指定 key 的值 -->
        <!-- 在组件中使用 v-for 循环的时候，或者在一些特殊的情况下，如果出现唯一性问题，则需要在使用 v-for 的同时，指定唯一的数字/字符串类型的 :key 值 -->
        <p v-for="item in list" :key="item.id">
            <input type="checkbox">
            {{ item.id }} --- {{ item.name }}
        </p>
    </div>
    <script>
        var vm = new Vue({
            el: '#app',
            data: {
                id: '',
                name: '',

                list: [
                    {id: 1, name: "小赵"},   
                    {id: 2, name: "小钱"},
                    {id: 3, name: "小孙"},
                    {id: 4, name: "小李"}                                 
                ],
            },
            methods: {
                add() {
                    // this.list.push({ id: this.id, name: this.name })

                    this.list.unshift({ id: this.id, name: this.name })                    
                }
            },
        })
    </script>
</body>

</html>
```



## v-if 和 v-show 指令的使用

<!-- v-if 的特点：每次都会对元素进行删除或者添加操作 -->
<!-- v-show 的特点：每次不会都 DOM 进行删除或者添加操作，只会改变 display：none 的样式 -->
<!-- v-if 有较高的切换性能消耗 -->
<!-- v-show 有较高的页面渲染消耗 -->
<!-- 如果元素的显示与否被频繁改变，则推荐使用 v-show -->



```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="lib/vue.js"></script>
</head>

<body>
    <div id="app">
        <!-- <input type="button" value="点击切换显示标题" @click="isShow"> -->
        <input type="button" value="点击切换显示标题" @click="flag = !flag"> <!-- 如果只有一句的函数可以将函数写到标签中 -->

        <!-- v-if 的特点：每次都会对元素进行删除或者添加操作 -->
        <!-- v-show 的特点：每次不会都 DOM 进行删除或者添加操作，只会改变 display：none 的样式 -->
        <!-- v-if 有较高的切换性能消耗 -->
        <!-- v-show 有较高的页面渲染消耗 -->
        <!-- 如果元素的显示与否被频繁改变，则推荐使用 v-show -->
        <h1 v-if="flag">这是一个标题</h1>
        <h1 v-show="flag">这是一个标题</h1>
        
    </div>
    <script>
        var vm = new Vue({
            el: '#app',
            data: {
                flag: true,
            },
            methods: {
                isShow() {
                    // if(this.flag){
                    //     this.flag = false
                    // }
                    // else{
                    //     this.flag = true;
                    // }
                    this.flag = !this.flag
                    
                }
            },
        })
    </script>
</body>

</html>
```







# 在 Vue 中使用样式

## 使用 class 样式

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        

        <script src="lib/vue.js"></script>

        <style>
        .red{
            color: red;
        }
        
        .thin{
            font-weight: 200;
        }

        .italic{
            font-style: italic;
        }

        .active{
            letter-spacing: 5em;
        }

        </style>
    </head>
    

    <body>
        <div id="app">
            <!-- 不使用 vue 的形式 -->
            <h1 class="red thin">标题1</h1>

            <!-- 第一种方式： 直接传递一个数组，class 需要使用 v-bind 做数据绑定 -->
            <h1 :class="['thin', 'red']">标题1</h1>

            <!-- 第二种方式： 使用三元表达式 -->
            <h1 :class="['thin', flag?'red':'']">标题1</h1>

            <!-- 第三种方式： 在数组中使用对象代替三元表达式，提高可读性 -->
            <h1 :class="['thin', {'red': flag}]">标题1</h1>

            <!-- 第四种方式： 直接使用对象 -->
            <h1 :class="[{'thin': false}, {'red': flag}]">标题1</h1>

        </div>
        

        <script>
            var vm = new Vue({
                el: '#app',
                data: {
                    flag: true,

                },
                methods: {
                

                },
            })
        </script>
    </body>
</html>
```



## 使用内联样式

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        

        <script src="lib/vue.js"></script>
    </head>
    

    <body>
        <div id="app">
            <!-- 不使用 vue 的形式 -->
            <h1 style="color: red;">这是一个标题</h1>

            <!-- 使用 v-bind 属性绑定对象的形式 -->
            <!-- 对象就是无序键值对的集合 -->
            <h1 :style="{'color': 'red', 'font-weight': '200'}">这是一个标题</h1>

            <!-- 引用放在 data 中的属性 -->
            <h1 :style="styleObj1">这是一个标题</h1>

            <!-- 对象数组 -->
            <h1 :style="[styleObj1, styleObj2]">这是一个标题</h1>

        </div>
        

        <script>
            var vm = new Vue({
                el: '#app',
                data: {
                    styleObj1: {
                        'color': 'red', 
                        'font-weight': '200'
                    },
                    styleObj2: {
                        'font-style': 'italic'
                    }

                },
                methods: {
                

                },
            })
        </script>
    </body>
</html>
```



# Vue 中的过滤器

- vue 允许用自定义的过滤器，来做一些文本的格式化， 可以使用在两个地方：mustache 表达式和 v-bind 属性绑定表达式。添加在表达式的末尾，用管道符表示

- -使用格式 `{{ name | nameope }}` 

  - |：管道符
  - nameope：自定义过滤器的名称

- 过滤器的定义语法：`Vue.filter('过滤器的名称', function(){})`

  - function(){}中的第一个参数已经被规定为管道符前传递过来的数据

    ```javascript
    Vue.filter('过滤器的名称', function(data){
    	return data + '123'
    })
    ```



```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="lib/vue.js"></script>
</head>

<body>
    <div id="app">
        <!-- <p>{{ msg | msgFormat }}</p> -->
        <!-- 可以给过滤器传递参数 -->
        <!-- 可以多个过滤器同时使用 -->
        <p>{{ msg | msgFormat('Holy shit', ' this time') | msgFormat2('===') }}</p>
    </div>
    <script>
        // 定义一个过滤器

        // Vue.filter('msgFormat',function(msg){
        //     // return msg.replace('hello','goodbye')
        //     // replace()的第一个参数除了可以传入一个字符串之外，也可以传入一个正则表达式
        //     return msg.replace(/hello/g,'goodbye')
        // })

        Vue.filter('msgFormat', function (msg, arg1, arg2) {
            // return msg.replace('hello','goodbye')
            // replace()的第一个参数除了可以传入一个字符串之外，也可以传入一个正则表达式
            return msg.replace(/hello/g, arg1 + arg2)
        })

        Vue.filter('msgFormat2', function (msg, arg1) {
            return msg + arg1
        })


        var vm = new Vue({
            el: '#app',
            data: {
                msg: 'hello spring, hello summer, hello autumn, hello winter.'
            },
            methods: {
            },
        })
    </script>
</body>

</html>
```



## Vue生命周期函数



```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="lib/vue.js"></script>
</head>

<body>
    <div id="app">
    </div>
    <script>
        var vm = new Vue({
            el: '#app',
            data: {
                msg: '你好~'
            },
            methods: {
                show() {
                    console.log(this.msg)
                }
            },

            //生命周期函数

            // 实例创建阶段
            beforeCreate() {
                // 在 beforeCreate 生命周期函数执行的时候，data 和 methods 的内容都还没有被初始化
            },
            created() {
                // 在 created 生命周期函数执行的时候，data 和 methods 的内容已经被初始化好了
            },
            beforeMount() {
                // 在 beforeMount 生命周期函数执行的时候，模板已经在内存中渲染好了，但是还尚未渲染到页面中，
                // 页面上的元素还没有真正替换，只是之前写过的模板字符串
            },
            mounted() {
                // 在 mounted 生命周期函数执行的时候，表示内存中的模块已经渲染到了页面上
                // mounted 函数是实例创建的最后一个生命周期函数，执行完 mounted 之后，就表示实例已经完全被创建好了
            },

            // 运行中的两个事件
            beforeUpdate() {
                // 这个时候界面还没有被更新，但是 data 已经被更新了
                // 当执行 beforeUpdate 的时候，页面上的数据还没有被更新，但是内存中的 data 数据已经被更新过，只是还没有更新到页面上
            },
            updated() {
                // updated()执行的时候，页面上的数据已经被更新了，且和 data 数据已经保持同步
            },

            // 销毁过程
            beforeDestroy() {
                
            },
            destroyed() {
                
            },
        })
    </script>
</body>

</html>
```



## Vue发送 AJAX 请求-axios

官方文档：<https://github.com/axios/axios>



示例：

Performing a `GET` request

- 传参的方式有两种：
  - 通过 url 的形式传参
  - 通过 param 参数进行传参

```javascript
const axios = require('axios');

// Make a request for a user with a given ID
axios.get('/user?ID=12345')
  .then(function (response) {
    // handle success
    console.log(response);
  })
  .catch(function (error) {
    // handle error
    console.log(error);
  })
  .then(function () {
    // always executed
  });

// Optionally the request above could also be done as
axios.get('/user', {
    params: {
      ID: 12345
    }
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  })
  .then(function () {
    // always executed
  });  

// Want to use async/await? Add the `async` keyword to your outer function/method.
async function getUser() {
  try {
    const response = await axios.get('/user?ID=12345');
    console.log(response);
  } catch (error) {
    console.error(error);
  }
}
```



Performing a `POST` request

axios 以 post 形式发送请求时，默认的数据格式是 Request Payload 的形式，而不是常见的 Form Data 表单数据的形式，所以参数必须要以键值对的形式发送

```javascript
axios.post('/user', {
    firstName: 'Fred',
    lastName: 'Flintstone'
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
```



Performing multiple concurrent requests

```javascript
function getUserAccount() {
  return axios.get('/user/12345');
}

function getUserPermissions() {
  return axios.get('/user/12345/permissions');
}

axios.all([getUserAccount(), getUserPermissions()])
  .then(axios.spread(function (acct, perms) {
    // Both requests are now complete
  }));
```



```html

```



