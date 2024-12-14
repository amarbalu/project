package com.tvmaze.project.repository;

import com.tvmaze.project.model.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TVShowRepository extends JpaRepository<TVShow, Long> {
     TVShow findByName(String name);
}