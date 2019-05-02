package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.Province;
import com.codegym.cms.repository.ProvinceRepository;
import com.codegym.cms.service.CustomerService;
import com.codegym.cms.service.ProvinceService;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/create-province")
    public ModelAndView createFormProvince() {
        ModelAndView modelAndView = new ModelAndView("province/createFormProvince");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView createProvince(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("province/createFormProvince");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @GetMapping("/list-province")
    public ModelAndView showListProvince() {
        Iterable<Province> listProvince = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("province/listProvince", "listProvince", listProvince);
        return modelAndView;
    }


    // edit province
    @GetMapping("/edit-province/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("province/editProvince", "province", province);
        return modelAndView;
    }


    @PostMapping("/edit-province")
    public String editProvince(@ModelAttribute("province") Province province, RedirectAttributes redirect) {
        provinceService.save(province);
        return "redirect:/list-province";
    }

    // view list customer in province
    @GetMapping("/view-province/{id}")
    public ModelAndView viewListCustomerInProvince(@PathVariable Long id) {
        ModelAndView modelAndView;
        Province province = provinceService.findById(id);
        if (province == null) {
            modelAndView = new ModelAndView("province/error404");
            return modelAndView;
        }
        Iterable<Customer> listCustomer = customerService.findAllByProvince(province);
        modelAndView = new ModelAndView("province/viewCustomer");
        //modelAndView.addObject(province);
        modelAndView.addObject("lisCustomer" , listCustomer);
        return modelAndView;
    }

}
