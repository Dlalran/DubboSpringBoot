package priv.howard.dubbospringboot.controller;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.howard.dubbospringboot.service.MyService;

@Controller
@RequestMapping("MyController")
public class MyController {
    /**
     * 一、Zookeeper注册中心宕机后，消费者可以通过Dubbo本地缓存来继续调用提供者的服务，也可以通过指定url属性进行直连
     * 二、mock属性指定提供方宕机或者响应时间超过timeout时使用{@link priv.howard.dubbospringboot.service.MyServiceMock}的实现代替真实实现来实现服务降级
     *    其可用属性值有false(不使用)、true/default(服务调用失败时使用)、force(所有调用强制使用)
     * 三、cluster属性指定集群容错模式
     *    1.FailOver Cluster(默认):失败时自动切换服务器，通过retries属性指定重试(切换服务器)的次数
     *    2.Faiifast Cluster(快速失败):只要失败就立即报错，适用于非幂等操作(如新增)
     *    3.Failsafe Cluster(失败安全):失败时直接忽略
     *    4.Failback Cluster(失败重发):失败后在后台记录，定时重发
     *    5.Forking Cluster(并行调用):并行调用多个服务器，只要一个成功就返回，通过属性forks指定最大并行数
     *    6.Broadcast Cluster(广播调用):逐个调用所有提供者，只要一个失败就报错
     */
    @Reference(group = "MyService", interfaceName = "priv.howard.dubbospringboot.service.MyService",
            version = "1.0.0", mock = "true", cluster = "failover")
    private MyService myService;


    @ResponseBody
    @RequestMapping("getMessage")
    public String getMessage() {
//        可以在RPC调用前通过上下文信息给提供方服务添加隐式参数
        RpcContext.getContext().setAttachment("param", "1");

        return myService.doServe("Dubbo");
    }
}
