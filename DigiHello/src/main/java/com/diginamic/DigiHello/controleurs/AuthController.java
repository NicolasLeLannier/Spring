/**
 *
 */
package com.diginamic.DigiHello.controleurs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        return "redirect:/"; // Rediriger vers la page principale après une connexion réussie
    }
}
