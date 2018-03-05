package pl.jp.bsj.util;

import java.util.Collection;

public class CollectionUtil {
    /**
     * This method will cast every number passed to it into long and then count their sum
     */
    public static long sumAsLongs(Collection<? extends Number> numbers) {
        return numbers.stream().mapToLong(Number::longValue).sum();
    }
}
