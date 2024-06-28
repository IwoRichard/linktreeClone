package com.richard.linktreeClone.repositories;

import com.richard.linktreeClone.entities.SocialLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialLinkRepository extends JpaRepository<SocialLink, Long> {
}
