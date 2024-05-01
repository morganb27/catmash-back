package com.morganb27.catmash.repository;

import com.morganb27.catmash.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
