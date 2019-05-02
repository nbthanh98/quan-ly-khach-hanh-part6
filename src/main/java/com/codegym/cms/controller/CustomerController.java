package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.Province;
import com.codegym.cms.service.CustomerService;
import com.codegym.cms.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    ProvinceService provinceService;

    @ModelAttribute("listProvince")
    public Iterable<Province> provinces(){
        return  provinceService.findAll();
    }

    @GetMapping("/create-customer")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("customer/createFormCustomer");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/createFormCustomer");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    // show list customer

    @GetMapping("/list-customer")
    public ModelAndView showListCustomer(Pageable pageable){
        Page<Customer> listCustomer =  customerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("customer/listCustomer", "listCustomer", listCustomer);
        return modelAndView;
    }

    // xu li viec edit customer
    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Customer customer =  customerService.findCustomerById(id);
        ModelAndView modelAndView = new ModelAndView("customer/editCustomerForm", "customer", customer);
        return modelAndView;
    }

    @PostMapping("/edit-customer")
    public String editCustomer(@ModelAttribute("customer") Customer customer , RedirectAttributes redirect){
        customerService.save(customer);
        return "redirect:/list-customer";
    }

    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirect){
        customerService.remoteCustomer(id);
        return "redirect:/list-customer";
    }

}
