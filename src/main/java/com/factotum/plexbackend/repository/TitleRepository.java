package com.factotum.plexbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.factotum.plexbackend.entity.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {

    @Query(value = "SELECT max(t.orderAdded) FROM Title t")
    Integer findMaxOrderAdded();

}
