package com.morganb27.catmash.service;

import com.morganb27.catmash.domain.Cat;
import com.morganb27.catmash.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class CatServiceImpl implements CatService {

    @Autowired
    private CatRepository catRepository;


    @Override
    public List<Cat> findAllCats() {
        return catRepository.findAll();
    }

    @Override
    public Cat incrementVote(Long id) {
        Optional<Cat> catOptional = catRepository.findById(id);
        if(catOptional.isPresent()) {
            Cat cat = catOptional.get();
            cat.setVotes(cat.getVotes() + 1);
            return catRepository.save(cat);
        }
        return null;
    }
}
