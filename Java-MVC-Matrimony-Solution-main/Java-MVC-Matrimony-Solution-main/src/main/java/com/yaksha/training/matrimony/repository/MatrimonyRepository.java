package com.yaksha.training.matrimony.repository;

import com.yaksha.training.matrimony.entity.Matrimony;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatrimonyRepository extends JpaRepository<Matrimony, Long> {

    @Query("SELECT m FROM Matrimony m")
    Page<Matrimony> findAllMatrimony(Pageable pageable);

    @Query(value = "Select m from Matrimony m where lower(fullName) like %:keyword% " +
            "or lower(religion) like %:keyword% " +
            "or lower(occupation) like %:keyword%")
    Page<Matrimony> findByNameOrReligionOrOccupation(@Param("keyword") String keyword, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE matrimony SET is_match_found = 1 where id = :id", nativeQuery = true)
    void updateMatchFound(@Param("id") Long id);

}
