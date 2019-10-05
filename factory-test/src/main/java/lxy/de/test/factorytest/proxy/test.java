package lxy.de.test.factorytest.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class test {

    /**
     * 动态代理
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 第一次参数： 被代理类的类加载器
         * 第二个参数： 被代理类实现的接口
         * 第三个参数： 代理工作处理器对象
         */
        ShangPinDAO sp = new ShangPinDAO();
        ClassLoader classLoader = sp.getClass().getClassLoader();
        Class<?>[] interfaces = sp.getClass().getInterfaces();
        AbcInocationHandler abcInocationHandler = new AbcInocationHandler(sp);

        /*需要：Proxy：提供用于创建动态代理类和实例的静态方法，它还是由这些方法创建的所有动态代理类的超类。
         *
         *static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) */
        Object o = Proxy.newProxyInstance(classLoader, interfaces, abcInocationHandler);
        DBDAO spd = (DBDAO) o;
        spd.add();
        spd.select();
    }

}

class AbcInocationHandler implements InvocationHandler{

    private Object target;

    public AbcInocationHandler(Object target) {
        this.target = target;
    }

    /**
     *  这个invoke方法不是程序员调用，当代理类对象执行对应的代理方法时，自动调用的
     * @param proxy   代理类对象
     * @param method    被代理类和代理类要执行的方法
     * @param args  要执行方法的实参列表
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /*
        *  必须实现InvocationHandler
        * */
        long start = System.currentTimeMillis();
        //有了反射： 方法对象.invoke(被代理对象，agrs实参列表)
        Object returnValue = method.invoke(target, args);
        long end = System.currentTimeMillis();
        System.out.println(method.getName() + "方法运行时间：" + (end-start));
        return returnValue;
    }
}

//主题接口
interface DBDAO{
    void add();
    void update();
    void delete();
    void select();
}
//被代理类1
class ShangPinDAO implements DBDAO{
    public void add(){
        System.out.println("添加商品");
    }

    @Override
    public void update() {
        System.out.println("修改商品");
    }

    @Override
    public void delete() {
        System.out.println("删除商品");
    }

    @Override
    public void select() {
        System.out.println("查询商品");
    }
}
//被代理类2
class YongHuDAO implements DBDAO{
    public void add(){
        System.out.println("添加用户");
    }

    @Override
    public void update() {
        System.out.println("修改用户");
    }

    @Override
    public void delete() {
        System.out.println("删除用户");
    }

    @Override
    public void select() {
        System.out.println("查询用户");
    }
}
