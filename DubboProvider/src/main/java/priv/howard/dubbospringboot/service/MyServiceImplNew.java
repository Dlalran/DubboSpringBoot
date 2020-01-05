package priv.howard.dubbospringboot.service;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

@Service(group = "MyService", interfaceName="priv.howard.dubbospringboot.service.MyService", version = "2.0.0")
public class MyServiceImplNew implements MyService {
    @Override
    public String doServe(String name) {
        return "Hello, " + name + "!"
                + "How are you?";
    }
}
