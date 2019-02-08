package com.github.wreulicke.eclipsecollections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;

public class SampleTest {
  
  @Test
  public void test1() {
    MutableList<String> mutableList = Lists.mutable.of("test");
    
    assertThat(mutableList).contains("test");
  }
  
  @Test
  public void test2() {
    MutableList<String> mutableList = Lists.mutable.of("test")
      .collect(str -> "test" + str);
    
    assertThat(mutableList).contains("testtest");
  }
  
  @Test
  public void test3() {
    MutableList<Integer> mutableList = Lists.mutable.of(10)
      .flatCollect(n -> IntStream.rangeClosed(0, n).mapToObj(v -> v).collect(Collectors.toList()));
    
    assertThat(mutableList).contains(7);
  }
  
  @Test
  public void test4() {
    MutableList<String> mutableList = Lists.mutable.of("test")
      .collectWith(String::split, "")
      .flatCollect(Lists.immutable::of);
    
    assertThat(mutableList).contains("t");
  }
  
  @Test
  public void test5() {
    MutableBag<String> mutableBag = Lists.mutable.of("test")
      .collectWith(String::split, "")
      .flatCollect(Lists.immutable::of)
      .toBag();
    
    assertThat(mutableBag.occurrencesOf("t"))
      .isEqualTo(2);
  }
  
  @Test
  public void test6() {
    String maxLengthStr = Lists.mutable
      .of("test", "test", "test1")
      .maxBy(String::length);
    
    assertThat(maxLengthStr)
      .isEqualTo("test1");
  }
  
  @Test
  public void test7() {
    String joined = Lists.mutable.of("a", "b", "c")
      .makeString(",");
    
    assertThat(joined)
      .isEqualTo("a,b,c");
  }
  
  @Test
  public void test8() {
    MutableList<String> mutableList = Lists.mutable.of("a", "b", "c")
      .select(each -> !each.equals("a"));
    
    assertThat(mutableList).doesNotContain("a");
  }
  
  @Test
  public void test9() {
    MutableList<String> mutableList = Lists.mutable.of("a", "b", "c")
      .reject(each -> each.equals("a"));
    
    assertThat(mutableList).doesNotContain("a");
  }
  
  @Test
  public void test10() {
    MutableList<String> mutableList = Lists.mutable.of("a", "b", "c")
      .rejectWith(Object::equals, "a");
    
    assertThat(mutableList).doesNotContain("a");
  }
  
  @Test
  public void test11() {
    String string = Lists.mutable.of("a", "b", "c", "d")
      .dropWhile(each -> !each.equals("b"))
      .makeString();
    
    assertThat(string).isEqualTo("b, c, d");
  }
  
  @Test
  public void test12() {
    String string = Lists.mutable.of("a", "bb")
      .detect(str -> str.length() == 2);
    
    assertThat(string).isEqualTo("bb");
  }
  
  @Test
  public void test13() {
    String string = Lists.mutable.of("a", "bb", "cc", "dd", "eee")
      .asLazy()
      .select(str -> str.length() == 2)
      .getFirst();
    
    assertThat(string).isEqualTo("bb");
  }
}
