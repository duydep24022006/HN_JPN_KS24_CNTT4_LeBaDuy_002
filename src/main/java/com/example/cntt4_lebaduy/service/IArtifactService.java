package com.example.cntt4_lebaduy.service;
import com.example.cntt4_lebaduy.model.Artifact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

public interface IArtifactService {

    Page<Artifact> getAllArtifacts(Pageable pageable);

    Page<Artifact> searchArtifacts(String keyword, Pageable pageable);

    Optional<Artifact> findById(Long id);

    Artifact save(Artifact artifact);

    void deleteById(Long id);
}
