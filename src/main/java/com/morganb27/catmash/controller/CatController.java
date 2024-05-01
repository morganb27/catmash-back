package com.morganb27.catmash.controller;

import com.morganb27.catmash.domain.Cat;
import com.morganb27.catmash.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {

    @Autowired
    private CatService catService;

    @GetMapping
    public ResponseEntity<List<Cat>> getAllCats() {
        List<Cat> cats = catService.findAllCats();
        return ResponseEntity.ok(cats);
    }

    @PatchMapping("/{id}/vote")
    public ResponseEntity<Cat> incrementVote(@PathVariable Long id) {
        Cat cat = catService.incrementVote(id);
        if (cat != null) {
            return ResponseEntity.ok(cat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
