# springCloud-demo
这是关于spring的一些demo

# 20190728
这个项目做了一些基本的服务关联 并没有实现业务 整体结构是：一个服务eureka 两个client 两个负载均衡feign，ribbon 一个网关zuul 一个监控工具turbine 
还有另外的rabbitmq (只有rabbitmq没有和服务做关联 因为主要实现服务者，消费者观念。抽出来我比较好再去添加一些其它功能在mq里面，在其它项目里面用mq还是一样的代码 so 我把mq抽离出来只是为了更好地了解它)
