package com.example.springboottesting.service;

import com.example.springboottesting.exception.DemoApplicationException;
import com.example.springboottesting.model.Demo;
import com.example.springboottesting.repository.DemoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


class DemoServiceTest {

    @InjectMocks
    private DemoService sut;

    @Mock
    private DemoRepository demoRepository;

    @Captor
    private ArgumentCaptor<Demo> demoCaptor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getDemo called repository")
    void should_get_demo_by_id() {
        String id = "someId";

        Demo result = new Demo();
        result.setValue("someValue");

        Mockito.when(demoRepository.findById(id)).thenReturn(Optional.of(result));

        sut.getDemo(id);

        verify(demoRepository).findById(id);
    }

    @Test
    @DisplayName("Test getDemo throws an exception")
    void should_throw_exception_get_demo() {
        String id = "someId";

        Demo result = new Demo();
        result.setValue("someValue");

        Mockito.when(demoRepository.findById(id)).thenReturn(Optional.empty());

        DemoApplicationException ex = assertThrows(DemoApplicationException.class, () -> {
            sut.getDemo(id);
        });

        assertEquals("Exception occurred while get demo by id", ex.getMessage());

    }

    @Test
    @DisplayName("Test create demo")
    void should_create_demo() {
        String demoValue = "someDemoValue";

        sut.createDemo(demoValue);

        Mockito.verify(demoRepository).save(demoCaptor.capture());

        Demo capturedDemo = demoCaptor.getValue();

        assertNotNull(capturedDemo);
        assertNotNull(capturedDemo.getId());
        assertEquals(demoValue, capturedDemo.getValue());
    }

    @Test
    @DisplayName("Test get demos as list")
    void should_return_demos_as_list() {
        String demoValue = "someDemoValue";

        sut.getDemos(demoValue);

        verify(demoRepository).findByValue(demoValue);
    }
}