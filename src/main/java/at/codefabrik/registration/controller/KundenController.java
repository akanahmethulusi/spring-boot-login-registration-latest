package at.codefabrik.registration.controller;

import at.codefabrik.registration.exception.NotFoundIdException;
import at.codefabrik.registration.model.Kunde;
import at.codefabrik.registration.service.KundenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
//@RestController
@RequestMapping
@AllArgsConstructor
public class KundenController {

    @Autowired
    private KundenService kundenService;

    @GetMapping("/kunden")
    public String showKundenList(Model model){
        List<Kunde> listedKunden = kundenService.showKundenList();
        model.addAttribute("listedKunden", listedKunden);
        model.addAttribute("titlePage","Kunden Management System");
        return "kunden";
    }

    @GetMapping("/kunden/new")
    public String showNewForm(Model model){
        model.addAttribute("users", new Kunde());
        model.addAttribute("pageTitle","Neue Kunde hinzufügen");
        return "kunden_form";
    }

    @PostMapping("/kunden/save")
    public String saveKunde(Kunde kunde, RedirectAttributes redirectAttributes){
        kundenService.saveKunde(kunde);
        //redirect message
        redirectAttributes.addFlashAttribute("message","Kunde wurde erfolgreich gespeichert!");
        System.out.println(kunde);
        return "redirect:/kunden";
    }

    @GetMapping("/kunden/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Kunde showEdited = kundenService.showEditForm(id);
            model.addAttribute("users", showEdited);
            model.addAttribute("pageTitle", "Edit Kunde (ID " + id + " )");
            return "kunden_form";
        }catch (NotFoundIdException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/kunden";
        }
    }

    @GetMapping("/kunden/delete/{id}")
    public String deleteKunde(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        try {
            kundenService.deleteKunde(id);
            redirectAttributes.addFlashAttribute("message","Die Kunde ID " + id+ " wurde erfolgreich gelöscht!");
        }catch (NotFoundIdException ex){
            redirectAttributes.addFlashAttribute("message",ex.getMessage());
        }
        return "redirect:/kunden";
    }


}
