package com.dairy.Services;

import com.dairy.Entity.Admin;
import com.dairy.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean validateAdmin(String username, String password) {
        // Check if count > 0
        return adminRepository.countAdmin(username, password) > 0;
    }
//    public boolean validAdmin(String Username,String password){
//        Admin admin =new Admin();
//        if(admin.getUsername().equals(Username)&& admin.getPassword().equals(password)){
//         return adminRepository.countAdmin(Username,password)>0;
//        }
//        else  return false;
//    }
}
