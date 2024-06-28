package com.richard.linktreeClone.repositories;

import com.richard.linktreeClone.entities.CustomLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomLinkRepository extends JpaRepository<CustomLink, Long> {
}
