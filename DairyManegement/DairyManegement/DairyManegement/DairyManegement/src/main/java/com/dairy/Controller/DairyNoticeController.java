package com.dairy.Controller;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.DairyNotice;
import com.dairy.Entity.DairyRegistration;
import com.dairy.Entity.StateMaster;
import com.dairy.Services.DairyNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Notice")
public class DairyNoticeController {
    @Autowired
    private DairyNoticeService dairyNoticeService;


    @PostMapping("/saveNotice/{dairyId}")
    public ResponseDTO saveNotice(@PathVariable Integer dairyId,@RequestBody DairyNotice dairyNotice) {
        ResponseDTO response =new ResponseDTO();
        try {
            DairyNotice savedNotice = dairyNoticeService.saveNotice(dairyId,dairyNotice);
            response.data = savedNotice;
            response.message = "Success";
            response.status = 200L;
            response.success = true;
        } catch (Exception e) {
            response.message = "Failed: " + e.getMessage();
            response.status = 500L;
            response.success = false;
        }
        return response;
    }


    @GetMapping("/getNotice")
    public ResponseDTO getNotice(){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=dairyNoticeService.getAllNotice();
            response.message="success";
            response.status=200L;
            response.success=true;
        }catch (Exception e){
            response.message="filed...";
            response.status=500L;
            response.success=false;
        }
        return response;
    }

    @GetMapping("/getNoticeId/{id}")
    public ResponseDTO getNoticeById(@PathVariable("id")  Integer id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=dairyNoticeService.getAllNoticeByID(id);
            response.message="success";
            response.status=200L;
            response.success=true;
        }catch (Exception e){
            response.message="filed...";
            response.status=500L;
            response.success=false;
        }
        return response;
    }

    @PutMapping("/updateNotice")
    public ResponseDTO updateNotice(@RequestBody DairyNotice dairyNotice){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=dairyNoticeService.UpdateNotice(dairyNotice);
            response.message="success";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message=e.getMessage();
            response.status=500L;
            response.success=false;

        }

        return response;
    }

    @PutMapping("/deactivate/{id}")
    public ResponseDTO deactivateNotice(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=dairyNoticeService.deactivateNotice(id);
            response.message="success";
            response.status=200L;
            response.success=true;
        }catch (Exception e){
            response.message="filed...";
            response.status=500L;
            response.success=false;
        }
        return response;
    }
    @PutMapping("/activate/{id}")
    public ResponseDTO activeNotice(@PathVariable  int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=dairyNoticeService.activateNotice(id);
            response.message="success";
            response.status=200L;
            response.success=true;
        }catch (Exception e){
            response.message="filed...";
            response.status=500L;
            response.success=false;
        }
        return response;
    }

    @GetMapping("/getByDairyId/{dId}")
    public ResponseDTO DairyIdByNotice2(@PathVariable("dId")int dId){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=dairyNoticeService.getByDairyId(dId);
            response.message="success";
            response.status=200L;
            response.success=true;
        }catch (Exception e){
            response.message="filed...";
            response.status=500L;
            response.success=false;
        }
        return response;


    }

    @GetMapping("/dairyId/{dId}")
    public ResponseDTO DairyIdByNotice(@PathVariable("dId")int dId,@RequestBody DairyNotice dairyNotice){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=dairyNoticeService.findByDairyId(dId,dairyNotice);
            response.message="success";
            response.status=200L;
            response.success=true;
        }catch (Exception e){
            response.message="filed...";
            response.status=500L;
            response.success=false;
        }
        return response;


    }


}
