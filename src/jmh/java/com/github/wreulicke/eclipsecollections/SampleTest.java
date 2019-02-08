package com.github.wreulicke.eclipsecollections;

import java.util.HashMap;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class SampleTest {

  private int n;

  private Object ref;

  private HashMap<Object, Object> jdkHashMap;

  private MutableMap<Object, Object> eclipseCollectionsMap;

  private IntObjectHashMap eclipseCollectionPrimitiveMap;


  @Setup(Level.Iteration)
  public void setupInIteration() {
    n = 0;
    ref = new Object();
    jdkHashMap = new HashMap<>(135);
    eclipseCollectionsMap = Maps.mutable.withInitialCapacity(135);
    eclipseCollectionPrimitiveMap = new IntObjectHashMap(135);
  }

  @Benchmark
  public void test1() {
    for (int i = 0; i < 100; i++)
      jdkHashMap.put(this.n++, ref);
  }

  @Benchmark
  public void test2() {
    for (int i = 0; i < 100; i++)
      eclipseCollectionsMap.put(this.n++, ref);
  }

  @Benchmark
  public void test3() {
    for (int i = 0; i < 100; i++)
      eclipseCollectionPrimitiveMap.put(this.n++, ref);
  }

  @Benchmark
  public void test4() {
    for (int i = 0; i < 10000; i++)
      jdkHashMap.put(this.n++, ref);
  }

  @Benchmark
  public void test5() {
    for (int i = 0; i < 10000; i++)
      eclipseCollectionsMap.put(this.n++, ref);
  }

  @Benchmark
  public void test6() {
    for (int i = 0; i < 10000; i++)
      eclipseCollectionPrimitiveMap.put(this.n++, ref);
  }
}
