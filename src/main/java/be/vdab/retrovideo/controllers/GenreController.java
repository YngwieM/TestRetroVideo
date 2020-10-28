package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("genre")
public class GenreController {
    private final GenreService genreService;
    private final FilmService filmService;


    public GenreController(GenreService genreService, FilmService filmService) {
        this.genreService = genreService;

        this.filmService = filmService;
    }

    @GetMapping
    public ModelAndView genres() {
        return new ModelAndView("index", "genres", genreService.findAll());
    }


    @GetMapping("{id}")
    public ModelAndView genre(@PathVariable long id) {
        var modelAndView = new ModelAndView("genre");


        List<Film> films = filmService.findByGenreId(id);
        modelAndView.addObject("films", films);
        modelAndView.addObject("genres", genreService.findAll());

        return modelAndView;
    }

}


