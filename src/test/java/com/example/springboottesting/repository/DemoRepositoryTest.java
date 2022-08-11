package com.example.springboottesting.repository;

import com.example.springboottesting.model.Demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DemoRepositoryTest {

    @Autowired
    private DemoRepository demoRepository;

    @BeforeEach
    public void setup() {
        demoRepository.deleteAll();
    }

    @Test
    @DisplayName("JPA - Test demos like native query")
    void should_test_like_native_query() {

        Demo demo = new Demo();
        demo.setId(UUID.randomUUID().toString());
        demo.setValue("someValue 123");

        Demo demo2 = new Demo();
        demo2.setId(UUID.randomUUID().toString());
        demo2.setValue("someValue 1234");

        demoRepository.saveAll(List.of(demo, demo2));

        List<Demo> demoList = demoRepository.findByValue("Value");

        assertEquals(2, demoList.size());
        assertEquals("someValue 123", demoList.get(0).getValue());
    }

}