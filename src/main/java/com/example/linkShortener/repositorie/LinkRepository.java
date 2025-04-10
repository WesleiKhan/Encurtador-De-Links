package com.example.linkShortener.repositorie;

import com.example.linkShortener.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, String> {

    boolean existsByShortCode(String shortCode);
}
