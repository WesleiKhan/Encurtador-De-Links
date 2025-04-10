package com.example.linkShortener.repositorie;

import com.example.linkShortener.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, String> {

    Optional<Link> findByShortCode(String code);

    boolean existsByShortCode(String shortCode);
}
