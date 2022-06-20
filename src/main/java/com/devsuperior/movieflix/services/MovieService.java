package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieChosenDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private GenreRepository genreRepository;


    @Transactional(readOnly = true)
    public Page<MovieDTO> findByGenre(Long genreId, Pageable pageable) {
        Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
        Page<Movie> page =  repository.findByGenre(genre, pageable);
        Page<MovieDTO> pageDto = page.map(x -> new MovieDTO(x));
        return pageDto;
    }

    @Transactional(readOnly = true)
    public MovieChosenDTO findById(Long id) {
        Optional<Movie> obj = repository.findById(id);
        Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Sorry, Entity not found !"));
        return new MovieChosenDTO(entity, entity.getGenre());
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findReviews(Long id) {
        Movie movie = (id == 0) ? (null) : (repository.getOne(id));
        List<Review> reviews = movie.getReviews();

        List<ReviewDTO> list= reviews.stream().map(x-> new ReviewDTO(x, x.getUser())).collect(Collectors.toList());
        return list;
    }
}
