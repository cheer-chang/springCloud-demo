# springCloud-demo
这是关于spring的一些demo :wink:

# 20190728
这个项目做了一些基本的服务关联 并没有实现业务 整体结构是：一个服务eureka 两个client 两个负载均衡feign，ribbon 一个网关zuul 一个监控工具turbine 
还有另外的rabbitmq (:exclamation:只有rabbitmq没有和服务做关联 因为主要实现服务者，消费者观念。抽出来我比较好再去添加一些其它功能在mq里面，在其它项目里面用mq还是一样的代码 so 我把mq抽离出来只是为了更好地了解它:smile:)

# 20200603
此次主要修改了rabbitmq的两个项目 __rabbitmq项目名实现的的是Sender__ __rabbitmq2项目名实现的的是（Receiver）__  ，先来说说这个版本增加和修改了什么。 :star2:

rabbitmq（Sender）：
1. 增加RabbitmqConfig配置类
2. 增加entity
3. 增加ConfirmCallback回调和它的配置
4. 增加ReturnCallback回调和它的配置
5. 增加部分注释

rabbitmq2（Receiver）：
1. 增加rabbitmq-config参数
2. 增加entity与sender项目同步
3. 修改RabbitListener绑定参数从配置文件获取
4. 增加部分注释
