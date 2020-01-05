package priv.howard.dubbospringboot.service;

public class MyServiceMock implements MyService {
    /**
     * @Description 用于当服务访问失败或者超时时，代替服务真实实现的虚假实现
     * 一般用于服务降级，即对于不重要服务在网络繁忙时的临时忽略
     */
    @Override
    public String doServe(String name) {
        return "This is a mock message.";
    }
}
