package priv.howard.dubbospringboot.service;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

/**
 * 一、通过group指定服务的分组，消费者使用时也需要添加组名来使用指定的服务
 *
 * 二、通过loadbalance属性指定使用的负载均衡策略:
 * 1、Random Loadbalance (默认) 基于权重的随机负载均衡
 * 2、RoundRobin Loadbanlance 基于权重的轮询负载均衡，即按照权重来决定轮询时访问的次数
 * 3、LeastActive LoadBalance 最少活跃调用数负载均衡，即按照访问响应速度来决定负载权重
 * 4、ConsistentHash Loadbalance 一致性Hash，相同参数的请求分发给同一个提供者
 * 权重可以通过weight属性指定，也可以在Dubbo-Admin控制台中指定权重
 */
@Service(group = "MyService", interfaceName="priv.howard.dubbospringboot.service.MyService",
        version = "1.0.0", loadbalance = "roundrobin")
public class MyServiceImpl implements MyService {
    @Override
    public String doServe(String name) {

//        可以获得消费者在调用时传入的隐式参数，单次调用有效
        String param = RpcContext.getContext().getAttachment("param");

        return "Hello, " + name + "!" + "(param:" + param + ")";
    }
}
