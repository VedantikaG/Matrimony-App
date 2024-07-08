package com.yaksha.training.matrimony.controller;

import com.yaksha.training.matrimony.entity.Matrimony;
import com.yaksha.training.matrimony.service.MatrimonyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = {"/matrimony", "/"})
public class MatrimonyController {

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    private MatrimonyService matrimonyService;

    @GetMapping(value = {"/list", "/"})
    public String listMatrimonys(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<Matrimony> matrimonys = matrimonyService.getMatrimonys(pageable);
        model.addAttribute("matrimonys", matrimonys.getContent());
        model.addAttribute("theSearchName", "");
        model.addAttribute("totalPage", matrimonys.getTotalPages());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
        return "list-matrimonys";
    }

    @GetMapping("/addMatrimonyForm")
    public String showFormForAdd(Model model) {
        Matrimony matrimony = new Matrimony();
        model.addAttribute("matrimony", matrimony);
        return "add-matrimony-form";
    }

    @PostMapping("/saveMatrimony")
    public String saveMatrimony(@Valid @ModelAttribute("matrimony") Matrimony matrimony, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (matrimony.getId() != null) {
                return "update-matrimony-form";
            }
            return "add-matrimony-form";
        } else {
            matrimonyService.saveMatrimony(matrimony);
            return "redirect:/matrimony/list";
        }
    }

    @GetMapping("/updateMatrimonyForm")
    public String showFormForUpdate(@RequestParam("matrimonyId") Long id, Model model) {
        Matrimony matrimony = matrimonyService.getMatrimony(id);
        model.addAttribute("matrimony", matrimony);
        return "update-matrimony-form";
    }

    @GetMapping("/delete")
    public String deleteMatrimony(@RequestParam("matrimonyId") Long id) {
        matrimonyService.deleteMatrimony(id);
        return "redirect:/matrimony/list";
    }

    @RequestMapping("/search")
    public String searchMatrimonys(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                                   @PageableDefault(size = 5) Pageable pageable,
                                   Model theModel) {

        Page<Matrimony> theMatrimonys = matrimonyService.searchMatrimonys(theSearchName, pageable);
        theModel.addAttribute("matrimonys", theMatrimonys.getContent());
        theModel.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        theModel.addAttribute("totalPage", theMatrimonys.getTotalPages());
        theModel.addAttribute("page", pageable.getPageNumber());
        theModel.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");

        return "list-matrimonys";
    }

    @GetMapping("/updateMatchFound")
    public String updateStatus(@RequestParam("id") Long id) {
        matrimonyService.updateMatrimonyMatchFound(id);
        return "redirect:/matrimony/list";
    }
}
