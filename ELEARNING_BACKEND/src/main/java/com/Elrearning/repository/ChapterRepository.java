package com.Elrearning.repository;

import com.Elrearning.models.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository< Chapter, Integer> {


    @Query(value = "SELECT * FROM chapitres p WHERE p.id_course = ?1", nativeQuery = true)
    List<Chapter> findbyallbycourseid(long id );
}



