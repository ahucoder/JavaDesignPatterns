# Java Design Patterns

一个全面的 Java 设计模式实现集合，包含 23 种 GoF 设计模式以及现代开发中常用的其他模式。每个模式都配有详细说明、UML 图和真实场景的示例代码。

## 为什么学习设计模式？

设计模式是软件开发中常见问题的可重用解决方案。它们提供了：
- **经过验证的解决方案**：解决特定问题的成熟方法
- **代码可重用性**：减少重复代码
- **可维护性**：使代码更易于理解和修改
- **团队协作**：提供共享的设计词汇表

## 模式分类

### 创建型模式
| 模式                                                   | 描述 | 示例 |
|------------------------------------------------------|------|------|
| [单例 (Singleton)](src/main/java/creational/singleton)            | 确保一个类只有一个实例 | 数据库连接池 |
| [工厂方法 (Factory Method)]()                        | 创建对象而不指定具体类 | UI 跨平台组件创建 |
| [抽象工厂 (Abstract Factory)]()                      | 创建相关对象族 | 跨平台 UI 组件套件 |
| [建造者 (Builder)]()    | 分步构建复杂对象 | SQL 查询构建器 |
| [原型 (Prototype)]() | 通过克隆创建对象 | 游戏角色复制 |

### 结构型模式
| 模式 | 描述 | 示例 |
|------|------|------|
| [适配器 (Adapter)]() | 转换接口兼容性 | 日志系统集成 |
| [桥接 (Bridge)]() | 分离抽象与实现 | 图形渲染引擎 |
| [组合 (Composite)]() | 树形结构处理 | 文件系统表示 |
| [装饰器 (Decorator)]() | 动态添加职责 | Java I/O 流 |
| [外观 (Facade)](src/main/java/structural/facade) | 简化复杂系统接口 | 智能家居控制中心 |
| [享元 (Flyweight)]() | 高效共享小对象 | 文本编辑器字符处理 |
| [代理 (Proxy)](src/main/java/structural/proxy) | 控制对象访问 | 图片懒加载 |

### 行为型模式
| 模式 | 描述 | 示例 |
|------|------|------|
| [责任链 (Chain of Responsibility)](src/main/java/behavioral/chain) | 请求处理链 | 订单审批流程 |
| [命令 (Command)](src/main/java/behavioral/command) | 封装操作为对象 | 事务系统、宏命令 |
| [迭代器 (Iterator)]() | 遍历集合元素 | 自定义集合遍历 |
| [中介者 (Mediator)]() | 减少对象间依赖 | 聊天室系统 |
| [备忘录 (Memento)]() | 捕获并恢复状态 | 文档历史记录 |
| [观察者 (Observer)](src/main/java/behavioral/observer) | 对象状态变化通知 | 事件驱动系统 |
| [状态 (State)]() | 封装状态行为 | 订单状态机 |
| [策略 (Strategy)](src/main/java/behavioral/strategy) | 封装算法族 | 支付方式选择 |
| [模板方法 (Template Method)]() | 定义算法骨架 | 构建流水线 |
| [访问者 (Visitor)]() | 分离算法与对象 | 文档导出系统 |
| [解释器 (Interpreter)]() | 定义语言语法 | SQL 解析器 |

### 并发模式
| 模式                                                                                      | 描述                                     | 示例          |
|-----------------------------------------------------------------------------------------|----------------------------------------|-------------|
| [生产者-消费者 (Producer-Consumer)](src/main/java/concurrency/producerconsumer)                | 线程间任务协调                                | 消息队列系统      |
| [线程池 (Thread Pool)]()                                                                   | 重用线程提高性能                               | Web 服务器请求处理 |
| [读写锁 (Read-Write Lock)]()                                                               | 优化并发访问                                 | 缓存系统实现      |
| [两阶段终止 (Two-Phase-Stop)](src/main/java/concurrency/twophasestop)                         | 优雅的终止线程                                | 线程间同步       |
| [保护性暂停 (Guarded-Suspension)](src/main/java/concurrency/guardedsuspension)                | 在一个线程中等待另一个线程的执行结果                     | 线程间同步       |
| [固定线程的运行顺序 (Fixed-Operating-Sequence)](src/main/java/concurrency/fixedoperatingsequence) | 用于控制线程的执行顺序（如先运行t1再运行t2），面试常问          | 线程间同步       |
| [线程交替运行 (Thread-Alternate-Running)](src/main/java/concurrency/threadalternaterunning)    | 用于控制线程的执行顺序（t1 t2 t3 t1 t2 t3...），面试常问 | 线程间同步       |

## 项目结构

```bash
src/main/java/
├── behavioral/            # 行为型模式
├── concurrency/           # 并发模式
├── creational/            # 创建型模式
├── structural/            # 结构型模式
└── utils/                 # 公共工具类

src/test/java/             # 单元测试
```

## 快速开始

### 前提条件
- Java 21
- Maven 3.6+

### 运行示例
1. 克隆仓库：
   ```bash
   git clone git@github.com:ahucoder/JavaDesignPatterns.git
   cd JavaDesignPatterns
   ```

## 设计原则

本项目尽可能遵循 SOLID 设计原则：
1. **单一职责原则 (SRP)**：每个类只有一个职责
2. **开闭原则 (OCP)**：对扩展开放，对修改关闭
3. **里氏替换原则 (LSP)**：子类可替换父类
4. **接口隔离原则 (ISP)**：特定客户端专用接口
5. **依赖倒置原则 (DIP)**：依赖抽象而非实现

## 相关资源

- [Refactoring.Guru 设计模式](https://refactoring.guru/design-patterns)
- [Java 设计模式实战](https://java-design-patterns.com/)
- [大佬的BiliBili视频课程](https://space.bilibili.com/7968519/upload/video)

---

**Happy Coding!** 🚀  
通过掌握设计模式，编写更优雅、可维护的 Java 代码。
