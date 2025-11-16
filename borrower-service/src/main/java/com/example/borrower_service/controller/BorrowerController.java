package com.example.borrower_service.controller;

import com.example.base_domains.dto.BorrowRequest;
import com.example.base_domains.dto.BorrowerEvent;
import com.example.borrower_service.dto.BorrowerRequest;
import com.example.borrower_service.dto.BorrowerResponse;
import com.example.borrower_service.entity.Borrower;
import com.example.borrower_service.kafka.BorrowerProducer;
import com.example.borrower_service.service.BorrowerService;
import com.example.product_service.entity.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;
    private BorrowerProducer borrowerProducer;

//    public BorrowerController(BorrowerService borrowerService, BorrowerProducer borrowerProducer) {
//        this.borrowerService = borrowerService;
//        this.borrowerProducer = borrowerProducer;
//    }

    @GetMapping
    public String listBorrowers(Model model) {
        List<BorrowerResponse> borrowers = borrowerService.getAllActiveBorrowers();
        model.addAttribute("borrowers", borrowers);
        return "list";  // templates/borrower/list.html
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("borrower", new BorrowerRequest());
        return "form";
    }

    @PostMapping("/add")
    public String addBorrower(@Valid @ModelAttribute("borrower") BorrowerRequest borrowerRequest,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "form";
        }
        borrowerService.createBorrower(borrowerRequest);
        return "redirect:/borrowers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        BorrowerResponse borrower = borrowerService.getBorrower(id);
        BorrowerRequest request = new BorrowerRequest();
        request.setBorrowerLastName(borrower.getBorrowerLastName());
        request.setBorrowerFirstName(borrower.getBorrowerFirstName());
        request.setBorrowerEmail(borrower.getBorrowerEmail());
        request.setBorrowerContactNumber(borrower.getBorrowerContactNumber());
        request.setBorrowerAddress(borrower.getBorrowerAddress());
        model.addAttribute("borrower", request);
        model.addAttribute("borrowerId", id);
        return "edit-form";
    }

    @PostMapping("/update/{id}")
    public String updateBorrower(@PathVariable Long id,
                                 @Valid @ModelAttribute("borrower") BorrowerRequest request,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-form";
        }
        borrowerService.updateBorrower(request, id);
        return "redirect:/borrowers";
    }

    @GetMapping("/delete/{id}")
    public String deleteBorrower(@PathVariable Long id) {
        borrowerService.deleteBorrower(id);
        return "redirect:/borrowers";
    }

    @PostMapping("/kafka")
    public String placeBorrower(@RequestBody BorrowRequest request){

        String borrower = request.getBorrowerID();
        String product = request.getProductID();
        int quantity = request.getQuantity();

        BorrowerEvent borrowerEvent = new BorrowerEvent();
        borrowerEvent.setBorrowerID(borrower);
        borrowerEvent.setProductID(product);
        borrowerEvent.setQuantity(quantity);
        borrowerEvent.setMessage("borrower is sending event");

        borrowerProducer.sendMessage(borrowerEvent);

        return "Borrower placed successfully";

    }
}
