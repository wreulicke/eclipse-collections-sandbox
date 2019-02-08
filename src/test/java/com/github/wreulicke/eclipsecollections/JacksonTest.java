package com.github.wreulicke.eclipsecollections;

import java.io.IOException;

import org.eclipse.collections.api.map.primitive.IntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.tuple.primitive.IntIntPair;
import org.eclipse.collections.impl.factory.primitive.IntObjectMaps;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.eclipsecollections.EclipseCollectionsModule;

public class JacksonTest {
  
  private ObjectMapper mapper = new ObjectMapper().registerModule(new EclipseCollectionsModule());
  
  @Test
  public void test1() throws IOException {
    MutableIntObjectMap<Object> empty = IntObjectMaps.mutable.empty();
    MutableIntObjectMap<Object> objects = readAndWrite(empty, new TypeReference<IntObjectMap<String>>() {
    });
    objects.put(1, "");
  }
  
  public <T> T readAndWrite(T object, Class<T> clazz) throws IOException {
    return mapper.readValue(mapper.writeValueAsBytes(object), clazz);
  }
  public <T> T readAndWrite(T object, TypeReference<?> clazz) throws IOException {
    return mapper.readValue(mapper.writeValueAsBytes(object), clazz);
  }
}
