package com.dairy.Controller;


import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.CollectionTypeMaster;
import com.dairy.Services.CollectionTypeMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collection")
public class CollectionTypeMasterController {
    @Autowired
    private CollectionTypeMasterService collectionTypeMasterService;
    @GetMapping("/getAll/admin")
    public ResponseDTO getActiveDeactve(){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=collectionTypeMasterService.getActiveDeactive();
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Not get data...";
            response.success=false;
            response.status=500l;

        }
        return response;
    }
    @GetMapping("/getAll")

    public ResponseDTO getAll(){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=collectionTypeMasterService.getAllCollection();
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Not get data...";
            response.success=false;
            response.status=500l;

        }
        return response;
    }
    @GetMapping("/get/{id}")
    public ResponseDTO getById(@PathVariable int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= collectionTypeMasterService.getById(id);
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Not get data...";
            response.success=false;
            response.status=500l;

        }
        return response;

    }
    @PostMapping("/add")
    public ResponseDTO addCollection(@RequestBody CollectionTypeMaster collectionTypeMaster) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= collectionTypeMasterService.addCollection(collectionTypeMaster);
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Not get data...";
            response.success=false;
            response.status=500l;

        }
        return response;
    }
    @PutMapping("/activate/{id}")
    public ResponseDTO activateCollectiontype(@PathVariable  int id){
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= collectionTypeMasterService.activateCollection(id);
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Not get data...";
            response.success=false;
            response.status=500l;

        }
        return response;
    }
    @PutMapping("/deleted/{id}")
    public ResponseDTO deletedCollection(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= collectionTypeMasterService.deletedCollection(id);
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Not get data...";
            response.success=false;
            response.status=500l;

        }
        return response;
    }
    @PutMapping("/deactivate/{id}")
    public ResponseDTO deactivateCollection(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data= collectionTypeMasterService.deactivateCollection(id);
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Not get data...";
            response.success=false;
            response.status=500l;

        }
        return response;
    }
    @PutMapping("/update/{id}")
    public ResponseDTO updateCollection(
            @PathVariable int id,
            @RequestParam String newCollectionName) {
        ResponseDTO response=new ResponseDTO();
        try {
            response.data=collectionTypeMasterService.updateCollection(id,newCollectionName);
            response.message="Success";
            response.success=true;
            response.status=200l;

        }catch (Exception e){
            response.message="Not get data...";
            response.success=false;
            response.status=500l;

        }
        return response;
    }
}
