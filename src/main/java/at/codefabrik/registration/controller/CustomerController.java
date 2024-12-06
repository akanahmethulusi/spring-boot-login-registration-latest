package at.codefabrik.registration.controller;

import at.codefabrik.registration.exception.NotFoundIdException;
import at.codefabrik.registration.model.Customer;
import at.codefabrik.registration.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public String showCustomerList(Model model){
        List<Customer> listedKunden = customerService.showKundenList();
        model.addAttribute("listedKunden", listedKunden);
        model.addAttribute("titlePage","Kunden Management System");
        return "customer";
    }

    @GetMapping("/customer/new")
    public String showNewForm(Model model){
        model.addAttribute("users", new Customer());
        model.addAttribute("pageTitle","Neue Kunde hinzufügen");
        return "kunden_form";
    }

    @PostMapping("/customer/save")
    public String saveCustomer(Customer customer, RedirectAttributes redirectAttributes){
        customerService.saveCustomer(customer);
        //redirect message
        redirectAttributes.addFlashAttribute("message","Kunde wurde erfolgreich gespeichert!");
        System.out.println(customer);
        return "redirect:/customer";
    }

    @GetMapping("/customer/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Customer showEdited = customerService.showEditForm(id);
            model.addAttribute("users", showEdited);
            model.addAttribute("pageTitle", "Edit Kunde (ID " + id + " )");
            return "customer_form";
        }catch (NotFoundIdException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/customer";
        }
    }

    @GetMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        try {
            customerService.deleteCustomer(id);
            redirectAttributes.addFlashAttribute("message","Die Kunden ID " + id + " wurde erfolgreich gelöscht!");
        }catch (NotFoundIdException ex){
            redirectAttributes.addFlashAttribute("message",ex.getMessage());
        }
        return "redirect:/customer";
    }


}
