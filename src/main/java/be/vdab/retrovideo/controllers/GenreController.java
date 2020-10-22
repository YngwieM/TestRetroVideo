package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ModelAndView genres() {
        return new ModelAndView("index", "genres", genreService.findAll());
    }



    @GetMapping("id")
    public ModelAndView genre( long id) {
        var modelAndView = new ModelAndView("genre");
        genreService.findById(id).ifPresent(genre -> modelAndView.addObject(genre));
        return modelAndView;
    }

}


