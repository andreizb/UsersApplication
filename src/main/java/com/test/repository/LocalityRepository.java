package com.test.repository;

import com.test.dto.response.LocalityResponseDto;
import com.test.model.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalityRepository extends JpaRepository<Locality, Long> {

    @Query("select new com.test.dto.response.LocalityResponseDto(locality.id, " +
            "locality.localityName, locality.county.countyName) from Locality locality " +
            "where locality.county.countyCode = :countyCode")
    List<LocalityResponseDto> findAllByCountyCode(String countyCode);

}
