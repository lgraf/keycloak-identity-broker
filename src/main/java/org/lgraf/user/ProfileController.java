package org.lgraf.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class ProfileController {

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("user", "$user");
        return "profile";
    }

}
