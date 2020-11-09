package be.vdab.retrovideo.controllers;


import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.exceptions.ReservatieException;
import be.vdab.retrovideo.services.KlantService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.LinkedHashSet;

@Controller
@RequestMapping("reservatie")
class ReservatieController {
    private final KlantService klantService;
    private final Mandje mandje;
    private final ReservatieService reservatieService;

    ReservatieController(KlantService klantService, Mandje mandje, ReservatieService reservatieService) {
        this.klantService = klantService;
        this.mandje = mandje;
        this.reservatieService = reservatieService;
    }


    @PostMapping("{id}/bevestigen/bevestiging")
    public String update(@PathVariable long id, RedirectAttributes redirect) {
        var mislukteIds = new LinkedHashSet<>();
        var ids = mandje.getIds();
        if (ids != null) {
            for (long item : ids) {
                try {
                    var res = new Reservatie(id, item, LocalDate.now());
                    reservatieService.create(res);
                }catch (ReservatieException ex) {
                    mislukteIds.add(id);
                }
            }
            if (!mislukteIds.isEmpty()) {
                redirect.addAttribute("mislukt", mislukteIds);
            }
        }
        for (long item : ids) {
        mandje.delete(item);}
        return "redirect:/rapport";
    }
}
