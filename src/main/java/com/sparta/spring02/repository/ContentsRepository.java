package com.sparta.spring02.repository;

import com.sparta.spring02.model.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContentsRepository extends JpaRepository<Contents, Long> {
    List<Contents> findAllByOrderByModifiedAtDesc();
}