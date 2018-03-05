package pl.jp.bsj.util;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CollectionUtilTest {

    @Test
    public void shouldReturnZeroForEmptyList() {
        List<Integer> list = ImmutableList.of();

        long result = CollectionUtil.sumAsLongs(list);

        assertEquals(0, result);
    }

    @Test
    public void shouldSumIntegers() {
        List<Integer> list = ImmutableList.of(1, 10, 100);

        long result = CollectionUtil.sumAsLongs(list);

        assertEquals(111, result);
    }

    @Test
    public void shouldSumLongs() {
        List<Long> list = ImmutableList.of(1L, 10L, 100L);

        long result = CollectionUtil.sumAsLongs(list);

        assertEquals(111, result);
    }
}