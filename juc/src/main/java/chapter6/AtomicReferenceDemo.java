package chapter6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    String userName;
    int age;

}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User z3 = new User("z3", 22);
        User li4 = new User("li4", 25);
        atomicReference.set(z3);
        boolean setOk = atomicReference.compareAndSet(z3, li4);
        System.out.println("更新："+setOk+",新值："+atomicReference.get());
        setOk = atomicReference.compareAndSet(z3, li4);
        System.out.println("更新："+setOk+",新值："+atomicReference.get());
    }
}
