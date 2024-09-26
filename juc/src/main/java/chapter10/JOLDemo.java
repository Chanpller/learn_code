package chapter10;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
class Customer{
    //对象头8个字节，类型指针4个字节，int 4个字节，boolean类型1个字节，一共17个字节，再加上对齐，24字节；
    int id;
    boolean flag = false;
}
public class JOLDemo {
    public static void main(String[] args) {
        //打印jvm信息
        System.out.println(VM.current().details());
        //打印jvm对象对其字节数
        System.out.println(VM.current().objectAlignment());

        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());

        Object ob = new Object();
        System.out.println(ClassLayout.parseInstance(ob).toPrintable());
    }
}
