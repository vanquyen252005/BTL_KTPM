package com.example.vendor_service.controller;

import com.example.vendor_service.dto.VendorRequest;
import com.example.vendor_service.dto.VendorResponse;
import com.example.vendor_service.service.VendorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService vendorService;

    @GetMapping
    public String listVendors(Model model) {
        List<VendorResponse> vendors = vendorService.getAllActiveVendors();
        model.addAttribute("vendors", vendors);
        return "vendor/list"; // templates/vendor/list.html
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("vendor", new VendorRequest());
        return "vendor/form";
    }

    @PostMapping
    public String addVendor(@Valid @ModelAttribute("vendor") VendorRequest vendorRequest,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "vendor/form";
        }
        vendorService.createVendor(vendorRequest);
        return "redirect:/vendors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        VendorResponse vendor = vendorService.getVendor(id);
        VendorRequest request = new VendorRequest();
        request.setVendorName(vendor.getVendorName());
        request.setVendorContactNumber(vendor.getVendorContactNumber());
        request.setVendorCompany(vendor.getVendorCompany());
        request.setCreatedVendor(vendor.getCreatedVendor());
        request.setLastModifiedVendor(vendor.getLastModifiedVendor());
        model.addAttribute("vendor", request);
        model.addAttribute("vendorId", id);
        return "vendor/edit-form";
    }

    @PostMapping("/update/{id}")
    public String updateVendor(@PathVariable Long id,
                               @Valid @ModelAttribute("vendor") VendorRequest request,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "vendor/edit-form";
        }
        vendorService.updateVendor(request, id);
        return "redirect:/vendors";
    }

    @GetMapping("/delete/{id}")
    public String deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return "redirect:/vendors";
    }
}
