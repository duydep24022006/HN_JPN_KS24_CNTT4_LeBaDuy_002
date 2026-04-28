package com.example.cntt4_lebaduy.service.impl;

import com.example.cntt4_lebaduy.model.Artifact;
import com.example.cntt4_lebaduy.repository.IArtifactRepository;
import com.example.cntt4_lebaduy.service.IArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ArtifactSertviceImpl implements IArtifactService {
    private final IArtifactRepository artifactRepository;

    @Autowired
    public ArtifactSertviceImpl(IArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Artifact> getAllArtifacts(Pageable pageable) {
        return artifactRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Artifact> searchArtifacts(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return artifactRepository.findAll(pageable);
        }
        return artifactRepository.searchByKeyword(keyword.trim(), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Artifact> findById(Long id) {
        return artifactRepository.findById(id);
    }

    @Override
    public Artifact save(Artifact artifact) {
        return artifactRepository.save(artifact);
    }

    @Override
    public void deleteById(Long id) {
        artifactRepository.deleteById(id);
    }
}
