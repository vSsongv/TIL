package com.songforyou.Controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class MainController {
    @GetMapping(value = "/")
    public String main() {
        return "main";
    }

    @GetMapping(value = "/write")
    public String write() {
        return "write";
    }
}
