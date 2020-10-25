package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("/")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) { this.filmService = filmService;}

    @GetMapping("genre")
    public ModelAndView films() {
        return new ModelAndView("genre", "films", filmService.findAll());
    }

    @GetMapping("{id}")
    public ModelAndView film(@PathVariable long id) {
        var modelAndView = new ModelAndView("genre");
        filmService.findByGenreId(id).ifPresent(film -> modelAndView.addObject(film));
        return modelAndView;
    }

}
