package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    MovieService service;

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAll(Pageable pageable) {
        Page<MovieDTO> page = service.findAllPaged(pageable);

        return ResponseEntity.ok().body(page);
    }


}
