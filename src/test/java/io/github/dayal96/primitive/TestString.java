package io.github.dayal96.primitive;

import static org.junit.Assert.assertEquals;

import io.github.dayal96.primitive.string.MyString;
import java.util.List;
import org.junit.Test;

public class TestString extends TestPrimitive {
  public TestString() {
    super(new MyString(""));
  }

  @Test
  public void testComparisons() {
    List<String> strings = List.of("", "apple", "OrAnGeS", "oranges", "s'mores", "45536");

    for (var str1 : strings) {
      for (var str2 : strings) {
        assertEquals(str1.compareTo(str2), new MyString(str1).compareTo(new MyString(str2)));
      }
    }
  }
}
