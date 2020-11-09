package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;

@Controller
@RequestMapping("rapport")
 class RapportController {
    private final FilmService filmService;

    RapportController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ModelAndView rapport (long[]mislukt) {
        var modelAndVieuw = new ModelAndView("rapport");
        if (mislukt != null) {
    var ids = new HashSet<Long>(mislukt.length);
    for (var id:mislukt) {
        ids.add(id);
    }
    modelAndVieuw.addObject("films", filmService.findByIds(ids));
        }
        return modelAndVieuw;
    }
}
