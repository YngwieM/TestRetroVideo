package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("film")
    public ModelAndView films() {
        return new ModelAndView("genre", "films", filmService.findAll());
    }

    @GetMapping("{id}")
    public ModelAndView film(@PathVariable long id) {
        var modelAndView = new ModelAndView("genre");
        List<Film> films = filmService.findByGenreId(id);
        modelAndView.addObject("films", films);

        return modelAndView;
    }

    @GetMapping("film/{id}")
    public ModelAndView filmId(@PathVariable long id) {
        var modelAndView = new ModelAndView("film");
        Film film = filmService.findById(id).get();
        modelAndView.addObject("film", film);

        return modelAndView;
    }

}

//
//     filmService.findByGenreId(id)
//
//             (film -> modelAndView.addObject(film));