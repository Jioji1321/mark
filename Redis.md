## Redis

### 数据类型

支持字符串(String)、哈希(hash)、列表(list)、集合(set)和有序集合(zset, sorted set)



#### String

key-value 的形式，是二进制安全的，可以包含任何数据(jpg图片和序列化对象)

最基本的数据类型，最大可以存储 512MB(一个键最大能存储 512MB)

set 命令和 get 命令：

`set 用于设置，get 用于获取`



#### Hash

是一个键值(key=>value)对集合

是一个 String 类型的 field 和 value 的映射表，特别适合存储对象

**DEL runoob** 用于删除前面测试用过的 key，不然会报错：**(error) WRONGTYPE Operation against a key holding the wrong kind of value**

`del 命令用于删除 del key `



`HMSET:用来设置field=>键值对`

`HGET：用来获取相应 field 的 value`

每个 hash 都可以存储 2e32-1 键值对



#### List

是简单的字符串列表，按照插入顺序排序。可以添加一个元素到列表的头部(左边)或者尾部(右边)

`lpush key value :从左边插入`

`rpush key value ：从右边插入`

`lrange key fromIndex toIndex：遍历列表`

列表最大可以存储 2e32-1 个元素



#### Set

是 String 类型的无序集合

通过哈希表实现的，添加、删除、查找的复杂度都是 O(1)

sadd 命令：添加一个 String 元素到 key 对应的 set 集合中，成功返回 1；如果元素已经在集合中返回 0，如果不存在则返回错误

`sadd key member`

`smembers key`:遍历 set

集合中最大的成员数为 2e32-1



#### zset

和 set 一样是 String 类型的集合，且不允许重复的成员

不同的是每个元素都会关联一个 double 类型的分数，redis 通过这个分数来为集合中的成员进行从小到大的排序

zset 的成员是唯一的，但是分数可以重复

zadd 指令：添加元素到集合，元素在集合中存在则更新对应的分数 score

`zadd key score member`



| 类型                 | 简介                                                   | 特性                                                         | 场景                                                         |
| :------------------- | :----------------------------------------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| String(字符串)       | 二进制安全                                             | 可以包含任何数据,比如jpg图片或者序列化的对象,一个键最大能存储512M | ---                                                          |
| Hash(字典)           | 键值对集合,即编程语言中的Map类型                       | 适合存储对象,并且可以像数据库中update一个属性一样只修改某一项属性值(Memcached中需要取出整个字符串反序列化成对象修改完再序列化存回去) | 存储、读取、修改用户属性                                     |
| List(列表)           | 链表(双向链表)                                         | 增删快,提供了操作某一段元素的API                             | 1,最新消息排行等功能(比如朋友圈的时间线) 2,消息队列          |
| Set(集合)            | 哈希表实现,元素不重复                                  | 1、添加、删除,查找的复杂度都是O(1) 2、为集合提供了求交集、并集、差集等操作 | 1、共同好友 2、利用唯一性,统计访问网站的所有独立ip 3、好友推荐时,根据tag求交集,大于某个阈值就可以推荐 |
| Sorted Set(有序集合) | 将Set中的元素增加一个权重参数score,元素按score有序排列 | 数据插入集合时,已经进行天然排序                              | 1、排行榜 2、带权重的消息队列                                |



### Redis命令

#### redis-cli 指令

连接指令： `redis-cli -h 127.0.0.1 -p 6379 -a "mypass"(密码)`

测试连接：`ping`

中文乱码： `redis-cli —raw`





#### redis键(key)

基本语法：`command key_name`

添加：`set key_name value`

删除：`del key_name`

删除指令执行成功输出 (integer)1，否则输出 (integer)0



| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [DEL key](http://www.runoob.com/redis/keys-del.html) 该命令用于在 key 存在时删除 key。不存在的 key 将会被忽略 |
| 2    | [DUMP key](http://www.runoob.com/redis/keys-dump.html)  序列化给定 key ，并返回被序列化的值。 |
| 3    | [EXISTS key](http://www.runoob.com/redis/keys-exists.html)  检查给定 key 是否存在。 |
| 4    | [EXPIRE key](http://www.runoob.com/redis/keys-expire.html) seconds 为给定 key 设置过期时间，以秒计。 |
| 5    | [EXPIREAT key timestamp](http://www.runoob.com/redis/keys-expireat.html)  EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。 |
| 6    | [PEXPIRE key milliseconds](http://www.runoob.com/redis/keys-pexpire.html)  设置 key 的过期时间以毫秒计。 |
| 7    | [PEXPIREAT key milliseconds-timestamp](http://www.runoob.com/redis/keys-pexpireat.html)  设置 key 过期时间的时间戳(unix timestamp) 以毫秒计 |
| 8    | [KEYS pattern](http://www.runoob.com/redis/keys-keys.html)  查找所有符合给定模式( pattern)的 key 。 |
| 9    | [MOVE key db](http://www.runoob.com/redis/keys-move.html)  将当前数据库的 key 移动到给定的数据库 db 当中。 |
| 10   | [PERSIST key](http://www.runoob.com/redis/keys-persist.html)  移除 key 的过期时间，key 将持久保持。 |
| 11   | [PTTL key](http://www.runoob.com/redis/keys-pttl.html)  以毫秒为单位返回 key 的剩余的过期时间。 |
| 12   | [TTL key](http://www.runoob.com/redis/keys-ttl.html)  以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。 |
| 13   | [RANDOMKEY](http://www.runoob.com/redis/keys-randomkey.html)  从当前数据库中随机返回一个 key 。 |
| 14   | [RENAME key newkey](http://www.runoob.com/redis/keys-rename.html)  修改 key 的名称 |
| 15   | [RENAMENX key newkey](http://www.runoob.com/redis/keys-renamenx.html)  仅当 newkey 不存在时，将 key 改名为 newkey 。 |
| 16   | [TYPE key](http://www.runoob.com/redis/keys-type.html)  返回 key 所储存的值的类型。 |



##### DEL 命令

用于删除已存在的键

`DEL KEY_NAME`



##### DUMP命令

用于序列化给定 key，并返回序列化的值

`DUMP KEY_NAME`

如果 key 不存在则返回 nil，存在则返回序列化之后的值



##### EXISTS命令

用于检查给定 key 是否存在

`EXISTS KEY_NAME`

如果存在返回 1，否则返回0



##### EXPIRE命令

用于设置 key 的过期时间，key 过期之后将不再可用，单位为秒

`EXPIRE KEY_NAME TIME_IN_SECOND`

设置成功返回 1，当 key 不存在或者不能为 key 设置过期时间时返回0



##### EXPIREAT命令

用于以 UNIX 时间戳格式设置 key 的过期时间，和 EXPIRE 类似

`EXPIREAT KEY_NAME TIME_IN_UNIX_TIMESTAMP`

设置成功返回 1，当 key 不存在或者不能为 key 设置过期时间时返回0



##### PEXPIRE 命令

和 EXPIRE 命令作用类似，但是是以毫秒为单位设置 key 的生存时间

`PEXPIRE KEY_NAME TIME_IN_MILESECONDS`

设置成功返回 1，key 不存在或者设置失败返回 0



##### PEXPIREAT 命令

和 EXPIREAT 命令相似，但是是以毫秒为单位设置 key 的生存时间

`PEXPIREAT KEY_NAME TIME_IN_MILESECONDS`



##### PERSIST 命令

移除给定key 的过期时间，使得 key 永不过期

`PERSIST KEY_NAME`

当过期时间移除成功时返回 1，如果 key 不存在或 key 没有设置过期时间则返回 0





##### KEYS 命令

用于查找所有符合给定模式 pattern 的 key

`KEYS PATTERN`

返回符合给定模式的 key 列表(Array)



##### MOVE命令

用于将当前数据库的 key 移动到给定的数据库 db 中

`MOVE KEY_NAME DESTINATION_DATABASE`

移动成功返回 1，失败返回 0

`SELECT DB_NAME`: 选择数据库，默认是数据库 0

如果两个数据库有相同的 key 时，move 指令将会失效



##### TTL命令(Time to live)

返回 key 的生存时间剩余数(秒)

`TTL KEY_NAME`



##### PTTL 命令

返回 key 的生存时间剩余数(毫秒)

`PTTL KEY_NAME`



##### RANDOMKEY 命令

从当前数据库随机返回一个 key

`RAMDOMKEY`

当数据库不为空时，返回一个 key；当数据库为空时，返回 nil(windows 返回 null)



##### FLUSHDB命令

删除当前数据库的所有 key

`FLUSHDB`



##### RENAME 命令

用于修改 key 的名称

`RENAME OLD_KEY_NAME NEW_KEY_NAME`

改名成功提示 OK，失败时返回一个错误

当 OLD_KEY_NAME 和 NEW_KEY_NAME 相同，或者 OLD_KEY_NAME不存在时，返回一个错误；当 NEW_KEY_NAME 已经存在时，RENAME 命令将覆盖旧值



##### RENAMENX命令

用于在新的 key 不存在时修改 key 的名称(做一次检查操作防止覆盖)

`RENAMENX OLD_KEY_NAME NEW_KEY_NAME`

修改成功时返回 1，如果 NEW_KEY_NAME已经存在返回 0



##### TYPE命令

用于返回 key 所储存的值的类型

`TYPE KEY_NAME`

返回 key 的数据类型，数据类型有：

- none(key 不存在)
- string(字符串)
- list(列表)
- set(集合)
- zset(有序集合)
- hash(哈希表)





#### Redis 字符串(String)

`COMMAND KEY_NAME`



| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [SET key value](http://www.runoob.com/redis/strings-set.html)  设置指定 key 的值 |
| 2    | [GET key](http://www.runoob.com/redis/strings-get.html)  获取指定 key 的值。 |
| 3    | [GETRANGE key start end](http://www.runoob.com/redis/strings-getrange.html)  返回 key 中字符串值的子字符 |
| 4    | [GETSET key value](http://www.runoob.com/redis/strings-getset.html) 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。 |
| 5    | [GETBIT key offset](http://www.runoob.com/redis/strings-getbit.html) 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。 |
| 6    | [MGET key1 [key2..\]](http://www.runoob.com/redis/strings-mget.html) 获取所有(一个或多个)给定 key 的值。 |
| 7    | [SETBIT key offset value](http://www.runoob.com/redis/strings-setbit.html) 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。 |
| 8    | [SETEX key seconds value](http://www.runoob.com/redis/strings-setex.html) 将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。 |
| 9    | [SETNX key value](http://www.runoob.com/redis/strings-setnx.html) 只有在 key 不存在时设置 key 的值。 |
| 10   | [SETRANGE key offset value](http://www.runoob.com/redis/strings-setrange.html) 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。 |
| 11   | [STRLEN key](http://www.runoob.com/redis/strings-strlen.html) 返回 key 所储存的字符串值的长度。 |
| 12   | [MSET key value [key value ...\]](http://www.runoob.com/redis/strings-mset.html) 同时设置一个或多个 key-value 对。 |
| 13   | [MSETNX key value [key value ...\]](http://www.runoob.com/redis/strings-msetnx.html)  同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。 |
| 14   | [PSETEX key milliseconds value](http://www.runoob.com/redis/strings-psetex.html) 这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位。 |
| 15   | [INCR key](http://www.runoob.com/redis/strings-incr.html) 将 key 中储存的数字值增一。 |
| 16   | [INCRBY key increment](http://www.runoob.com/redis/strings-incrby.html) 将 key 所储存的值加上给定的增量值（increment） 。 |
| 17   | [INCRBYFLOAT key increment](http://www.runoob.com/redis/strings-incrbyfloat.html) 将 key 所储存的值加上给定的浮点增量值（increment） 。 |
| 18   | [DECR key](http://www.runoob.com/redis/strings-decr.html) 将 key 中储存的数字值减一。 |
| 19   | [DECRBY key decrement](http://www.runoob.com/redis/strings-decrby.html) key 所储存的值减去给定的减量值（decrement） 。 |
| 20   | [APPEND key value](http://www.runoob.com/redis/strings-append.html) 如果 key 已经存在并且是一个字符串， APPEND 命令将指定的 value 追加到该 key 原来值（value）的末尾。 |



##### SET key value

用于设置给定 key 的值。如果 key 已经存在就会覆写旧值且无视类型

`SET KEY_NAME VALUE`



##### GET key

用于获取指定 key 的值，如果 key 不存在返回 nil；如果 key 储存的值不是字符串类型则返回一个错误

`GET KEY_NAME`



##### GETRANGE key start end

用于获取存储在指定 key 中字符串的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)

`GETRANGE KEY_NAME start end`

返回截取得到的子字符串

获取整个字符串： `getrange key_name 0 -1`



##### GETSET key value

用于设置指定 key 的值，并返回 key 的旧值

`GETSET KEY_NAME VALUE`

返回给定 key 的旧值。当 key 没有旧值时(即 key 不存在)返回 nil；当 key 存储的不是一个字符串时返回一个错误



##### GETBIT key offset

用于对 key 所储存的字符串值，获取指定偏移量上的位(bit)

`GETBIT NEY_NAME OFFSET`

返回字符串值指定偏移量上的位；当偏移量 OFFSET 比字符串值的长度大，获取 key 不存在时，返回 0



##### MGET key1 [key2 ...]

返回所有(一个或多个)给定 key 的值。如果给定的 key 中有某个key 不存在，则这个 key 返回特殊值 nil

`MGET KEY1 KEY2 … KEYN`

返回一个包含所有给定 key 的值的列表



##### SETBIT key offset value

用于对 key 所储存的字符串值设置或清除指定偏移量上的位(bit)

`SETBIT KEY_NAME OFFSET value` 

返回指定偏移量原来储存的位



##### SETEX key seconds value

用于为指定的 key 设置值及过期时间，如果 key 已存在，则会替换旧值

`SETEX KEY_NAME TIMEOUT VALUE`

设置成功返回 ok



##### SETNX key value(SET if Not eXists)

用于在指定 key 不存在时为 key 设置指定的值

`SETNX KEY_NAME VALUE`

设置成功返回 1，否则返回 0



##### SETRANGE key offset value

用于使用指定的字符串覆盖给定 key 所储存的字符串值，覆盖的位置从偏移量 offset 开始

`SETRANGE KEY_NAME OFFSET VALUE`

返回被修改后的字符串长度



##### STRLEN key

用于获取指定 key 所储存的字符串值的长度，当 key 储存的不是字符串值时，返回一个错误

`STRLEN KEY_NAME`

返回字符串值的长度，当 key 不存在时返回 0



##### MSET key value [key value ...]

用于同时设置一个或多个 key-value 对

`MSET key1 value1 key2 value2 … keyn valuen`



##### MSETNX key value [key value ...]

用于当所有给定 key 都不存在的时候同时设定一个或多个 key-value 对

`MSETNX key1 value1 key2 value2 … keyn valuen`

当所有 key 都设置成功则返回 1，如果有一个 key 设置失败则返回 0



##### PSETNX key millseconds value

以毫秒为单位设置 key 的生存时间

`PSETEX key1 EXPIRE_IN_MILLSECONDS value1`

设置成功返回 ok



##### INCR key

用于将 key 中存储的数字增加 1

如果 key 不存在，key 值将会被初始化为 0，然后在执行 incr 操作

如果只包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误

本操作的值限制在 64 bit 有符号数字表示范围内

`INCR KEY_NAME`

返回执行 incr 之后 key 的值



##### INCRBY key increment

用于将 key 中储存的数字加上指定的增量值

如果 key 不存在，那么 key 的值将会先被初始化为 0，然后在执行 incrby 指令

如果值包含错误的类型，或者字符串类型的值不能表示为数字，那么返回错误

本操作的值限制在 64 bit 有符号数字表示范围内

`INCRBY KEY_NAME INCR_AMOUNT`

返回加上指定的增量值之后 key 的值



##### INCRBYFLOAT key increment

用于为 key 中所储存的值加上指定的浮点数增量值

如果 key 不存在，则会先将 key 初始化为 0，在执行 incrbyfloat 加法操作

`INCRBYFLOAT KEY_NAME INCR_AMOUNT`

返回执行命令之后 key 的值

- 用 set 设置的值可以是指数符号，但是执行 incrbyfloat 之后格式会被改成非指数符号
- 用 set 设置的值小数部分可以是 0，但是执行 incrbyfloat 之后会将无用的0 忽略，有需要的话，会将浮点数变为整数



##### DECR key

用于将 key 中存储的数字减少 1

如果 key 不存在，key 值将会被初始化为 0，然后在执行 incr 操作

如果只包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误

本操作的值限制在 64 bit 有符号数字表示范围内

`DECR KEY_NAME`

返回执行 incr 之后 key 的值



##### DECRBY key decrement

用于将 key 中储存的数字减去指定的增量值

如果 key 不存在，那么 key 的值将会先被初始化为 0，然后在执行 incrby 指令

如果值包含错误的类型，或者字符串类型的值不能表示为数字，那么返回错误

本操作的值限制在 64 bit 有符号数字表示范围内

`DECRBY KEY_NAME INCR_AMOUNT`

返回减去指定的增量值之后 key 的值



##### APPEND key value

用于为指定的 key 追加值

如果 key 已存在并且是一个字符串，APPEND 命令将 value 追加到可以原来的值的末尾；如果 key 不存在，APPEND 就简单的将给定 key 设为 value，就像执行了 set key value 一样

`APPEND KEY_NAME NEW_VALUE`

返回追加完指定值之后 key 中字符串的长度



#### Redis 哈希(Hash)

hash 是一个 String类型的 field 和 value 的映射表，适合储存对象

每个 hash 可以储存 2e32 - 1 键值对



| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [HDEL key field1 [field2\]](https://www.runoob.com/redis/hashes-hdel.html)  删除一个或多个哈希表字段 |
| 2    | [HEXISTS key field](https://www.runoob.com/redis/hashes-hexists.html)  查看哈希表 key 中，指定的字段是否存在。 |
| 3    | [HGET key field](https://www.runoob.com/redis/hashes-hget.html)  获取存储在哈希表中指定字段的值。 |
| 4    | [HGETALL key](https://www.runoob.com/redis/hashes-hgetall.html)  获取在哈希表中指定 key 的所有字段和值 |
| 5    | [HINCRBY key field increment](https://www.runoob.com/redis/hashes-hincrby.html)  为哈希表 key 中的指定字段的整数值加上增量 increment 。 |
| 6    | [HINCRBYFLOAT key field increment](https://www.runoob.com/redis/hashes-hincrbyfloat.html)  为哈希表 key 中的指定字段的浮点数值加上增量 increment 。 |
| 7    | [HKEYS key](https://www.runoob.com/redis/hashes-hkeys.html)  获取所有哈希表中的字段 |
| 8    | [HLEN key](https://www.runoob.com/redis/hashes-hlen.html)  获取哈希表中字段的数量 |
| 9    | [HMGET key field1 [field2\]](https://www.runoob.com/redis/hashes-hmget.html)  获取所有给定字段的值 |
| 10   | [HMSET key field1 value1 [field2 value2 \]](https://www.runoob.com/redis/hashes-hmset.html)  同时将多个 field-value (域-值)对设置到哈希表 key 中。 |
| 11   | [HSET key field value](https://www.runoob.com/redis/hashes-hset.html)  将哈希表 key 中的字段 field 的值设为 value 。 |
| 12   | [HSETNX key field value](https://www.runoob.com/redis/hashes-hsetnx.html)  只有在字段 field 不存在时，设置哈希表字段的值。 |
| 13   | [HVALS key](https://www.runoob.com/redis/hashes-hvals.html)  获取哈希表中所有值 |
| 14   | HSCAN key cursor [MATCH pattern] [COUNT count]  迭代哈希表中的键值对。 |



##### HDEL key field1 field2 ...

用于删除哈希表 key 中的一个或多个指定字段，不存在的字段被忽略

`HDEL KEY_NAME FIELD1 … FIELDN`

返回被成功删除字段的数量，不包括被忽略的字段



##### HEXISTS key field

用于查看哈希表的指定字段是否存在

`HEXISTS KEY_NAME FIELD_NAME`

如果哈希表含有指定字段返回 1，如果不含有或者 key 不存在返回 0



##### HGET key field

用于返回哈希表中指定字段的值

`HGET KEY_NAME FIELD_NAME`

返回给定字段的值，如果给定的字段或 key 不存在时返回 nil



##### HGETALL key

用于返回哈希表中所有字段和值

在返回值里，紧跟每个字段名(field)之后的是字段的值(value)，所以**返回值的长度是哈希表大小的两倍**

`HGETALL KEY_NAME`

返回值以列表的形式返回哈希表的字段和字段值，如果 key 不存在则返回空列表



##### HINCRBY key field increment

用于为哈希表中的字段值加上指定增量值

增量可以为负数，相当于做减法操作

如果哈希表的 key 值不存在，一个新的哈希表被创建并执行 HINCRBY 命令

如果指定的字段不存在，那么在执行命令前字段的值被初始化为 0

对一个储存字符串值的字段执行 HINCRBY 指令将造成一个错误

本操作的值被限制在 64 位(bit)有符号数字的表示范围之内

`HINCRBY KEY_NAME FIELD_NAME INCR_BY_NUMBER`

返回执行 HINCRBY 指令之后哈希表中字段的值



##### HINCRBYFLOAT key field increment

用于为哈希表中的字段值加上指定浮点数增量值

如果指定的字段不存在，那么在执行命令前，字段的值被初始化为 0

`HINCRBYFLOAT key field increment`

返回执行 HINCRBYFLOAT 命令之后哈希表中字段的值



##### HKEYS key

用于获取哈希表中的所有域(field)

`HKEYS key`

返回包含哈希表中所有域(field)的列表，当 key 不存在时返回一个空列表



##### HLEN key

用于获取哈希表中字段的数量

`HLEN key_name`

返回哈希表中字段的数量，如果 key 不存在返回 0



##### HMGET key field1 field2 ...

用于返回哈希表中一个或多个给定字段的值

如果指定字段不存在返回一个 nil 值

`HMGET KEY_NAME FIELD1 … FIELDN`

返回一个包含多个给定字段关联值的表，表值的排列顺序和指定字段的请求顺序一样



##### HMSET key field1 value1 field2 value2 ...

用于同时将多个 field-value 对设置到哈希表中

此命令会覆盖哈希表中已存在的字段

如果哈希表不存在会创建一个空哈希表并执行 HMSET 操作

`HMSET key_name field1 value1 field2 value2 … fieldn valuen`

执行成功提示 ok



##### HSET key field value

用于为哈希表中的字段赋值

如果哈希表不存在就先新建一个新的哈希表并进行 HSET 操作

如果字段已经存在哈希表中，旧值将会被覆盖

`HSET key_name field value`

如果字段是哈希表中一个新建字段，并且值设置成功，返回 1，如果哈希表中域(field)字段已经存在且旧值已经被新值覆盖，返回 0



##### HSETNX key field value

用于为哈希表中不存在的字段赋值

如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作

如果字段已经存在于哈希表中，则操作无效

如果 key 不存在，一个新哈希表被创建并执行 HSETNX 操作

`HSETNX key_name field value`

设置成功返回 1，如果给定字段已经存在并没有操作被执行返回 0



##### HVALS key

用于返回哈希表中所有的值(value)

`HVALS key_name`

返回一个包含哈希表中所有域(field)的值(value)。当 key 不存在时返回一个空表



##### HSCAN key cursor [MATCH pattern] [COUNT count]

迭代哈希表中的键值对



#### Redis 列表(List)

简单的字符串列表，按照插入顺序排序，可以添加一个元素到列表的头部(左边)或者尾部(右边)。一个列表最多可以包含 2e32 - 1 个元素

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [BLPOP key1 [key2 \] timeout](https://www.runoob.com/redis/lists-blpop.html)  移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 2    | [BRPOP key1 [key2 \] timeout](https://www.runoob.com/redis/lists-brpop.html)  移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 3    | [BRPOPLPUSH source destination timeout](https://www.runoob.com/redis/lists-brpoplpush.html)  从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 4    | [LINDEX key index](https://www.runoob.com/redis/lists-lindex.html)  通过索引获取列表中的元素 |
| 5    | [LINSERT key BEFORE\|AFTER pivot value](https://www.runoob.com/redis/lists-linsert.html)  在列表的元素前或者后插入元素 |
| 6    | [LLEN key](https://www.runoob.com/redis/lists-llen.html)  获取列表长度 |
| 7    | [LPOP key](https://www.runoob.com/redis/lists-lpop.html)  移出并获取列表的第一个元素 |
| 8    | [LPUSH key value1 [value2\]](https://www.runoob.com/redis/lists-lpush.html)  将一个或多个值插入到列表头部 |
| 9    | [LPUSHX key value](https://www.runoob.com/redis/lists-lpushx.html)  将一个值插入到已存在的列表头部 |
| 10   | [LRANGE key start stop](https://www.runoob.com/redis/lists-lrange.html)  获取列表指定范围内的元素 |
| 11   | [LREM key count value](https://www.runoob.com/redis/lists-lrem.html)  移除列表元素 |
| 12   | [LSET key index value](https://www.runoob.com/redis/lists-lset.html)  通过索引设置列表元素的值 |
| 13   | [LTRIM key start stop](https://www.runoob.com/redis/lists-ltrim.html)  对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。 |
| 14   | [RPOP key](https://www.runoob.com/redis/lists-rpop.html)  移除列表的最后一个元素，返回值为移除的元素。 |
| 15   | [RPOPLPUSH source destination](https://www.runoob.com/redis/lists-rpoplpush.html)  移除列表的最后一个元素，并将该元素添加到另一个列表并返回 |
| 16   | [RPUSH key value1 [value2\]](https://www.runoob.com/redis/lists-rpush.html)  在列表中添加一个或多个值 |
| 17   | [RPUSHX key value](https://www.runoob.com/redis/lists-rpushx.html)  为已存在的列表添加值 |











