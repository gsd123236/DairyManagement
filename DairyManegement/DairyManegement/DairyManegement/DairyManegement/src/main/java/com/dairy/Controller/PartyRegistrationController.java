package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.PartyRegistration;
import com.dairy.Repository.PartyRegistrationRepository;
import com.dairy.Services.PartyRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/api/partyRegistration")
@RestController
public class PartyRegistrationController {
    @Autowired
    public PartyRegistrationService service;

    @PostMapping("/add/{dairyId}")
    public ResponseDTO add(@RequestBody PartyRegistration partyRegistration,@PathVariable int dairyId){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.addParty(partyRegistration,dairyId);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="UnSuccess";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @GetMapping("/gatAllActive/{dairyId}")
    public ResponseDTO getAllActive(@PathVariable int dairyId){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.getAllActive(dairyId);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="UnSuccess";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @GetMapping("/gatAllInactive/{dairyId}")
    public ResponseDTO getAllInactive(@PathVariable int dairyId){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.getAllInactive(dairyId);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="UnSuccess";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @GetMapping("/gatAllDairyId/{dairyId}")
    public ResponseDTO getAllByDairyId(@PathVariable int dairyId){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.getAllByDairyId(dairyId);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="UnSuccess";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @GetMapping("/getAll")
    public ResponseDTO getAll(){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.getAll();
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="UnSuccess";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @PutMapping("/Update/{id}")
    public ResponseDTO UpdateParty(@PathVariable int id, @RequestBody PartyRegistration partyRegistration){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.updateParty(id,partyRegistration);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="UnSuccess";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @PutMapping("/Active/{id}")
    public ResponseDTO isActive(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.activateParty(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="UnSuccess";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
    @PutMapping("/Inactive/{id}")
    public ResponseDTO isInactive(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=service.deactivateParty(id);
            response.message="Success";
            response.success=true;
            response.status=200l;
        }catch (Exception e){
            response.message="UnSuccess";
            response.success=false;
            response.status=500L;
        }
        return response;
    }
}
