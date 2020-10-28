package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.forms.BeginletterForm;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;
import be.vdab.retrovideo.services.KlantService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Set;

@Controller
@RequestMapping("klanten")
 class KlantController {
    private final KlantService klantService;
    private final Mandje mandje;
    private final GenreService genreService;
    private final FilmService filmService;
    private final ReservatieService reservatieService;

    KlantController(KlantService klantService, Mandje mandje, GenreService genreService, FilmService filmservice, ReservatieService reservatieService) {
        this.klantService = klantService;
        this.mandje = mandje;
        this.genreService = genreService;
        this.filmService = filmservice;
        this.reservatieService = reservatieService;
    }

    @GetMapping("beginletter/form")
    public ModelAndView beginletterForm() {
        return new ModelAndView("beginletter")
                .addObject(new BeginletterForm(null));
    }

    @GetMapping("beginletter")
    public ModelAndView beginLetter(@Valid BeginletterForm form, Errors errors) {
        var modelAndView = new ModelAndView("beginletter");
        if (errors.hasErrors()) {
            return modelAndView;
        }

        modelAndView.addObject("genres", genreService.findAll());
        return modelAndView.addObject("klanten",
                klantService.findByBeginNaam(form.getBeginletter()));

    }

    @GetMapping("{id}/wijzigKlant")
    public ModelAndView wijzigenForm(@PathVariable long id) {
        var modelAndView = new ModelAndView("wijzigKlant");
        klantService.findById(id).ifPresent(klant -> modelAndView.addObject(klant));
        Set<Long> filmIds = mandje.getIds();
        modelAndView.addObject("aantalFilms", filmIds.size());
        return modelAndView;
    }

    @PostMapping("{id}/wijzigKlant/wijzigen")
    public void update() {
        var ids = mandje.getIds();
        if (ids != null) {
            for (long item : ids) {
                filmService.update(item);

            }
        }
    }
}
//
//    @PostMapping("{id}/wijzigKlant/wijzigen")
//    public void update(@PathVariable long id) {
//        var ids = mandje.getIds();
//        if (ids != null) {
//            for (long item : ids) {
//                filmService.update(item);
//                int idInt = (int) id;
//                int itemInt = (int) item;
//                var res = new Reservatie(idInt,itemInt,LocalDate.now());
//                reservatieService.create(res);
//
//            }
//        }
//    }
