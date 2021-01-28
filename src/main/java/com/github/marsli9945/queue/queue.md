# Queue队列

### 阻塞队列
在多线程领域，所谓阻塞，在某些情况下会挂起线程（即阻塞），一旦条件满足，被挂起的线程又会自动被唤醒

#### 为什么需要BlockingQueue
  好处是我们不需要关系什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切为什么需要BlockingQueue都一手包办了

  在concurrent宝发布以前，在多线程环境下，我们每个程序员都必须自己控制这些细节，尤其还要兼顾效率和线程安全，
而这会给我们都程序带来不小的复杂度

> 重点前三个
- ArrayBlockingQueue 由数组结构的有界阻塞队列
- LinkedBlockingQueue 由链表结构的有界（但大小默认值为Integer.MAX_VALUE）阻塞队列
- SynchronousQueue 不存储元素但阻塞队列，也即单个元素但队列
- PriorityBlockingQueue 支持优先级排序但无界阻塞队列
- DelayQueue 使用优先级队列实现但延迟无界阻塞队列
- LinkedTransferQueue 由链表结构组成的无界阻塞队列
- LinkedBlockingDeque 由链表结构组成的双向阻塞队列 

|  方法类型   | 抛出异常  | 特殊值  | 阻塞  | 超时  |
|  :----:  | :----:  | :----:  | :----:  | :----:  |
| 插入  | add(e) | offer(e) | put(e) | offer(e,time,unit) |
| 移除  | remove() | poll() | take() | poll(time,unit) |
| 检查  | element() | peek() | 不可用 | 不可用 |

抛出异常
当阻塞队列满时，再往队列里add插入元素会抛出IllegalStateException: Queue full
当阻塞队列空时，再往队列里remove移除元素会抛出NoSuchElementException

特殊值
插入方法，成功true失败false
移除方法，成功返回出队列元素，队列里面没有就返回null

一直阻塞
当阻塞队列满时，生产者线程继续往队列里put元素，队列会一直阻塞生产线程知道put数据or响应中断退出
当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用

超时退出
当队列满时，队列会阻塞生产者线程一定时间，超时后生产者线程会退出

#### SynchronousQueue没有容量
与其他BlockingQueue不同，SynchronousQueue是一个不存储元素的BlockingQueue
每一个put操作必须等待一个take操作，否则不能继续添加元素，反之亦然
