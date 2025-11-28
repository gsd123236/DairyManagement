package com.dairy.Controller;

import com.dairy.Dto.FarmerMilkCollectionDto;
import com.dairy.Dto.FarmerMilkCollectionTotalDto;
import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.FarmerMilkCollection;
import com.dairy.Services.FarmerMilkCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/farmerMilkCollection")
public class FarmerMilkCollectionController {


    @Autowired
    private final FarmerMilkCollectionService farmerMilkCollectionService;

    public FarmerMilkCollectionController(FarmerMilkCollectionService farmerMilkCollectionService) {
        this.farmerMilkCollectionService = farmerMilkCollectionService;
    }
    @PostMapping("/save/{dId}")
    public ResponseDTO saveFarmerMilkCollection(@RequestBody FarmerMilkCollection collection,@PathVariable Integer dId) {
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=farmerMilkCollectionService.saveFarmerMilkCollection(collection,dId);
            response.message="Success..";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Milk already exist this frame...";
            response.success=false;
            response.status=500L;
        }

        return response;

    }

    @GetMapping("/farmerID/{id}")
    public ResponseDTO getFarmerMilkCollectionId(@PathVariable String id) {
//        return farmerMilkCollectionService.getInformationFarmerMilkID(id);
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=farmerMilkCollectionService.getInformationFarmerMilkID(id);
            response.message="Success..";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Rate Not Add....";
            response.success=true;
            response.status=500L;
        }

        return response;
    }




    // Date throw get data
    @GetMapping("/date/{dairyId}")
    public ResponseDTO getAllByDate(@PathVariable int dairyId, @RequestParam String createdDate) {
        ResponseDTO response = new ResponseDTO();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate start = LocalDate.parse(createdDate, formatter);
            List<Map<String, Object>> data = farmerMilkCollectionService.getMilkCollectionByDateAndDId(dairyId, start);
            response.data = data;
            response.message = "Success..";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = "Failed...";
            response.success = false;
            response.status = 500L;
        }
        return response;
    }




    //get data to startDte to endDate All Summery Generate 10-20
    @GetMapping("/datetodate/{startDate}/{endDate}/{farmer_Id}/{d_Id}/{milk_type}")
    public ResponseDTO getByDateAndFarmerId(
            @PathVariable String startDate,
            @PathVariable String endDate,
            @PathVariable Integer farmer_Id,
            @PathVariable Integer d_Id,
            @PathVariable Integer milk_type) {
        ResponseDTO response=new ResponseDTO();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
            LocalDate endLocalDate = LocalDate.parse(endDate, formatter);

            LocalDateTime startDateTime = startLocalDate.atStartOfDay();
            LocalDateTime endDateTime = endLocalDate.plusDays(1).atStartOfDay();

            response.data=farmerMilkCollectionService.getMilkDataWithTotal(startDateTime, endDateTime, farmer_Id,d_Id, milk_type);
            response.message="Success..";
            response.success=true;
            response.status=200L;
        } catch (Exception e) {
            response.message=e.getMessage();
            response.success=false;
            response.status=500L;
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", e);
        }
        return  response;
    }


    //get data to startDte to endDate All Bill Generate
    @GetMapping("/DateToDateBill/{startDate}/{endDate}/{farmer_Id}/{d_Id}/{milk_type}")
    public ResponseDTO getByDateAndFarmerIdBill(
            @PathVariable String startDate,
            @PathVariable String endDate,
            @PathVariable Integer farmer_Id,
            @PathVariable Integer d_Id,
            @PathVariable Integer milk_type) {
        ResponseDTO response = new ResponseDTO();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
            LocalDate endLocalDate = LocalDate.parse(endDate, formatter);

            LocalDateTime startDateTime = startLocalDate.atStartOfDay();
            LocalDateTime endDateTime = endLocalDate.plusDays(1).atStartOfDay();

            response.data = farmerMilkCollectionService.getMilkCollectionSummary(startDateTime, endDateTime, farmer_Id, d_Id, milk_type).toString();
            System.out.println("controller"+farmerMilkCollectionService);
            response.message = "Success..";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            response.message = e.getMessage();
            response.success = false;
            response.status = 500L;
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", e);
        }
        return response;

    }




    //Update Data in id throw
    @PutMapping("/update")
    public ResponseDTO milkUpdate(@RequestBody FarmerMilkCollection farmerMilkCollection){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=farmerMilkCollectionService.milkUpdate(farmerMilkCollection);
            response.message="Success..";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Rate Not Add....";
            response.success=false;
            response.status=500L;
        }
        return response;
    }

    //Delete Data Active / Deactivate
    @PutMapping("/activate/{id}")
    public ResponseDTO activateMilkType(@PathVariable  int id){
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=farmerMilkCollectionService.activeMilkCollection(id);
            response.message="Success..";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Rate Not Add....";
            response.success=false;
            response.status=500L;
        }

        return response;
    }


    @PutMapping("/deactivate/{id}")
    public ResponseDTO deactivateMilkType(@PathVariable int id) {
        ResponseDTO response=new ResponseDTO();
        try{
            response.data=farmerMilkCollectionService.deactivateMilkCollection(id);
            response.message="Success..";
            response.success=true;
            response.status=200L;
        }catch (Exception e){
            response.message="Rate Not Add....";
            response.success=false;
            response.status=500L;
        }

        return response;

    }
    //************************ Farmer Invoice ************************
    @GetMapping("/getInvoice/{dId}")
    public ResponseDTO getByDate(@PathVariable Integer dId,
                                 @RequestParam String startDate,
                                 @RequestParam String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedStart = LocalDate.parse(startDate, formatter);
        LocalDate parsedEnd = LocalDate.parse(endDate, formatter);

        ResponseDTO response = new ResponseDTO();
        try {
            List<Map<String, Object>> data = farmerMilkCollectionService.getByDate(parsedStart, parsedEnd, dId);
            response.data = data;
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            e.printStackTrace();
            response.message = "Failed: " + e.getMessage();
            response.success = false;
            response.status = 500L;
        }
        return response;
    }

    @GetMapping("/getReport/{dId}")
    public ResponseDTO getByDateReport(@PathVariable Integer dId,
                                       @RequestParam String startDate,
                                       @RequestParam String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedStart = LocalDate.parse(startDate, formatter);
        LocalDate parsedEnd = LocalDate.parse(endDate, formatter);

        ResponseDTO response = new ResponseDTO();
        try {
            List<Map<String, Object>> data = farmerMilkCollectionService.getByReportsDate(parsedStart, parsedEnd, dId);
            response.data = data;
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            e.printStackTrace();
            response.message = "Failed: " + e.getMessage();
            response.success = false;
                  response.status = 500L;
        }
        return response;
    }

    @GetMapping("/getReportDate/{dId}")
    public ResponseDTO getByReportDate(@PathVariable Integer dId,
                                       @RequestParam String startDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedStart = LocalDate.parse(startDate, formatter);

        ResponseDTO response = new ResponseDTO();
        try {
            List<Map<String, Object>> data = farmerMilkCollectionService.getReportData(parsedStart, dId);
            response.data = data;
            response.message = "Success";
            response.success = true;
            response.status = 200L;
        } catch (Exception e) {
            e.printStackTrace();
            response.message = "Failed: " + e.getMessage();
            response.success = false;
            response.status = 500L;
        }
        return response;
    }



}
