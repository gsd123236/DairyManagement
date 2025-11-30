package com.dairy.Controller;

import com.dairy.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public String loginAdmin(@RequestParam String username, @RequestParam String password) {
        boolean isValid = adminService.validateAdmin(username, password);
        if (isValid) {
            return "Success";
        } else {
            return "Invalid";
        }
    }
}

