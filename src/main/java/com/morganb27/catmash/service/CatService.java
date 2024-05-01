package com.morganb27.catmash.service;

import com.morganb27.catmash.domain.Cat;

import java.util.List;

public interface CatService {

    List<Cat> findAllCats();

    Cat incrementVote(Long id);
}
