package com.factotum.plexbackend.repository;

import com.factotum.plexbackend.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {

    @Query(value = "SELECT max(t.orderAdded) FROM Title t")
    Integer findMaxOrderAdded();

}
