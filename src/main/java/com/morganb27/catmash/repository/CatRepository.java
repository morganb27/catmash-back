package com.morganb27.catmash.repository;

import com.morganb27.catmash.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {

}
