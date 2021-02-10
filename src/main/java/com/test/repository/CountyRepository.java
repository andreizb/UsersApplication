package com.test.repository;

import com.test.model.County;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyRepository extends JpaRepository<County, Long> {
}
