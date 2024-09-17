package training.springboot.testinginspringboot.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockitoAnnotationsTests {

    /**
     * Use of Mockito without @Mock annotation.
     */
    @Test
    public void whenNotUseMockAnnotation_thenCorrect() {
        List mockList = Mockito.mock(ArrayList.class);

        mockList.add("foo");
        Mockito.verify(mockList).add("foo");
        assertEquals(0, mockList.size());
        //assertEquals(100, mockList.size());

    }

    @Mock
    List<String> mockedList;

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        mockedList.add("one");

        Mockito.verify(mockedList).add("one");
        assertEquals(0, mockedList.size());

        Mockito.when(mockedList.size()).thenReturn(0);
        assertEquals(0, mockedList.size());
    }
}
