package com.example.cntt4_lebaduy.repository;

import com.example.cntt4_lebaduy.model.Artifact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IArtifactRepository extends JpaRepository<Artifact, Long> {
    @Query("SELECT a FROM Artifact a WHERE " +
            "LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.origin) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Artifact> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
