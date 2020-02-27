package com.forstudy.oauth2.web;

import com.forstudy.oauth2.model.VerificationForm;
import com.forstudy.oauth2.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final VerificationTokenService verificationTokenService;

    @GetMapping("/")
    public String index() {
        return "redirect:/email-verification";
    }

    @GetMapping("/email-verification")
    public String formGet(Model model) {
        model.addAttribute("verificationForm", new VerificationForm());
        return "verification-form";
    }

    @PostMapping("/email-verification")
    public String formPost(@Valid VerificationForm verificationForm, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            model.addAttribute("noErrors", true);
        }
        model.addAttribute("verificationForm", verificationForm);

        verificationTokenService.createVerification(verificationForm.getEmail());
        return "verification-form";
    }

    @GetMapping("/verify-email")
    @ResponseBody
    public String verifyEmail(String code) {
        return verificationTokenService.verifyEmail(code).getBody();
    }

}
