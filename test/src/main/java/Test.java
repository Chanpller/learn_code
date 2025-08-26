import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        System.out.println("Aa".hashCode());
        System.out.println("BB".hashCode());

        System.out.println("柳柴".hashCode());
        System.out.println("柴柕".hashCode());

        Set<Integer> sets = new HashSet<>();
        int hashCode;
        for(int i = 0; i < 200000; i++) {
            hashCode = new Object().hashCode();
            if(sets.contains(hashCode)) {
                System.out.println("运行到第:"+i+"次出现hash冲突,hashcode: " + hashCode);
                continue;
            }
            sets.add(hashCode);
        }
    }
}
