# 学习笔记

## 心得

干货满满，要跟着视频做一遍才能深刻体会jvm的博大精深

## jvm 参数

-Xms4096m //初始堆大小

-Xmx4096m //最大堆大小

-Xmn1536m //新生代大小 eden + from + to

-Xss512K //线程大小

-XX:NewRatio=2 //新生代和老年代的比例

-XX:MaxPermSize=64m  //持久代最大值

-XX:PermSize=16m //持久代初始值

-XX:SurvivorRatio=8 // eden 区和survivor区的比例

-verbose:gc 

-Xloggc:gc.log //输出gc日志文件

-XX:+UseGCLogFileRotation //使用log文件循环输出

-XX:NumberOfGCLogFiles=1 //循环输出文件数量

-XX:GCLogFileSize=8k //日志文件大小限制

-XX:+PrintGCDateStamps //gc日志打印时间

-XX:+PrintTenuringDistribution      //查看每次minor GC后新的存活周期的阈值

-XX:+PrintGCDetails //输出gc明细

-XX:+PrintGCApplicationStoppedTime //输出gc造成应用停顿的时间

-XX:+PrintReferenceGC //输出堆内对象引用收集时间

-XX:+PrintHeapAtGC //输出gc前后堆占用情况

-XX:+UseParallelGC //年轻代并行GC，标记-清除

-XX:+UseParallelOldGC //老年代并行GC，标记-清除

-XX:ParallelGCThreads=23 //并行GC线程数， cpu<=8?cpu:5*cpu/8+3

-XX:+UseAdaptiveSizePolicy //默认，自动调整年轻代各区大小及晋升年龄

-XX:MaxGCPauseMillis=15 //每次GC最大停顿时间,单位为毫秒

-XX:+UseParNewGC //Serial多线程版

-XX:+UseConcMarkSweepGC //CMS old gc

-XX:+UseCMSCompactAtFullCollection //FullGC后进行内存碎片整理压缩

-XX:CMSFullGCsBeforeCompaction=n //n次FullGC后执行内存整理

-XX:+CMSParallelRemarkEnabled //启用并行重新标记,只适用ParNewGC

-XX:CMSInitiatingOccupancyFraction=80       //cms作为垃圾回收是，回收比例80%

-XX:ParallelGCThreads=23 //并行GC线程数，cpu<=8?cpu:5*cpu/8+3

-XX:-UseSerialGC //默认不启用，client使用时启用

-XX:+UseG1GC //启用G1收集器

-XX:-UseAdaptiveSizePolicy //默认，不自动调整各区大小及晋升年龄

-XX:PretenureSizeThreshold=2097152 //直接晋升到老年代的对象大小

-XX:MaxTenuringThreshold=15(default) //晋升到老年代的对象年龄，PSGen无效

-XX:-DisableExplicitGC //禁止在运行期显式地调用?System.gc()

-XX:+HeapDumpOnOutOfMemoryError //在OOM时输出堆内存快照

-XX:HeapDumpPath=./java_pid<pid>.hprof //堆内存快照的存储路径

-XX:+CMSScavengeBeforeRemark //执行CMS重新标记之前，尝试执行一此MinorGC

-XX:+CMSPermGenSweepingEnabled //开启永久代的并发垃圾收集」

\- - - - - - - - - - - - - - -