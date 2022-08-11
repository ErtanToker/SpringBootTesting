package com.example.springboottesting.service;

import com.example.springboottesting.exception.DemoApplicationException;
import com.example.springboottesting.model.Demo;
import com.example.springboottesting.repository.DemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DemoService {

    private final DemoRepository demoRepository;

    public Demo getDemo(String id) {
        return demoRepository.findById(id)
                .orElseThrow(() -> new DemoApplicationException("Exception occurred while get demo by id"));
    }

    public Demo createDemo(String demoValue) {
        Demo demo = new Demo();
        demo.setId(UUID.randomUUID().toString());
        demo.setValue(demoValue);

        return demoRepository.save(demo);
    }

    public List<Demo> getDemos(String value) {
        return demoRepository.findByValue(value);
    }

    public List<Demo> getDemosBySpringData(String value) {
        return demoRepository.findByValueLikeIgnoreCase(value);
    }
}
