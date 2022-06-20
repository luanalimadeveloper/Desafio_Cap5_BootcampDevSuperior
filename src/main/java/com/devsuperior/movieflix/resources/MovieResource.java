package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieChosenDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    MovieService service;

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findByGenre(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {
        Page<MovieDTO> page = service.findByGenre(genreId, pageable);

        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieChosenDTO> findById(@PathVariable Long id) {
        MovieChosenDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviews(@PathVariable Long id) {
        List<ReviewDTO> list = service.findReviews(id);
        return ResponseEntity.ok().body(list);
    }


}
