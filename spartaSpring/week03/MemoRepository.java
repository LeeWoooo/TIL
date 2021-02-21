package com.sparta.week03_prac.repository;

import com.sparta.week03_prac.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo,Long> {
        List<Memo> findAllByOrderByModifiedAtDesc();
}
