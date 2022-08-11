package com.example.springboottesting.repository;

import com.example.springboottesting.model.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemoRepository extends JpaRepository<Demo, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM TBL_DEMO WHERE val LIKE (%:val%)")
    List<Demo> findByValue(@Param("val") String value);

    List<Demo> findByValueLikeIgnoreCase( String value);

}
