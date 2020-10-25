package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.forms.BeginletterForm;
import be.vdab.retrovideo.services.KlantService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("klanten")
 class KlantController {
    private final KlantService klantService;

    KlantController(KlantService klantService) { this.klantService = klantService;}

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
        return modelAndView.addObject("klanten",
                klantService.findByBeginNaam(form.getBeginletter()));
    }
}
