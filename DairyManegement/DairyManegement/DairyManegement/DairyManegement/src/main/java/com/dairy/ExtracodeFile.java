package com.dairy;

import com.dairy.Dto.ResponseDTO;
import com.dairy.Entity.DairyNotice;
import com.dairy.Services.DairyNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

public class ExtracodeFile {
    //Update All Record code


//    public String registerDairy(DairyRegistration dairyRegistration){
//        DairyRegistration savedDairy = dairyRepository.save(dairyRegistration);
//        return "Save all Data";
//    }

//
//    public String dairyUpdate(DairyRegistration dairyRegistration){
//
//        if (dairyRegistration.getdId()==0){
//            return "Invalid Id: Update failed";
//        }
//        Optional<DairyRegistration> updatedata=dairyRepository.findById(dairyRegistration.getdId());
//        if(updatedata.isPresent()){
//            DairyRegistration updateRecord=updatedata.get();
//
//            updateRecord.setFirstName(dairyRegistration.getFirstName());
//            updateRecord.setLastName(dairyRegistration.getLastName());
//            updateRecord.setPassword(dairyRegistration.getPassword());
//            updateRecord.setDairyName(dairyRegistration.getDairyName());
//            updateRecord.setCollectionType(dairyRegistration.getCollectionType());
//            updateRecord.setCollectionShift(dairyRegistration.getCollectionShift());
//            updateRecord.setDistract(dairyRegistration.getDistract());
//            updateRecord.setMilkType(dairyRegistration.getMilkType());
//            updateRecord.setPaymentPeriod(dairyRegistration.getPaymentPeriod());
//            updateRecord.setTaluka(dairyRegistration.getTaluka());
//            updateRecord.setState(dairyRegistration.getState());
//            updateRecord.setUpdateDate(dairyRegistration.getUpdateDate());
//
//            updateRecord.setUpdateDate(LocalDateTime.now());
//            dairyRepository.saveAndFlush(updateRecord);
//            return "Your Data Update Successfully....";
//        }
//        else
//        {
//            return "Record Not Found For Update.....";
//        }
//    }

    //******************************************************************
    //genreate only otp and save otp code


//
//    public String generateAndSendOtp(String mobile){
//        String otp = generateOtp();
//
//        LocalDateTime otpExpiryTime=LocalDateTime.now().plusMinutes(5);
//
//        DairyRegistration dairyRegistration=dairyRepository.findByMobile(mobile)
//                .orElse(new DairyRegistration());
//
//        dairyRegistration.setMobile(mobile);
//        dairyRegistration.setOtp(otp);
//        dairyRegistration.setOtpExpiryTime(otpExpiryTime);
//
//
//        dairyRepository.save(dairyRegistration);
//        boolean smsSent=smsService.sendOtp(mobile,otp);
//
//        if (smsSent){
//            return "Otp sent successfully to" +mobile;
//
//        }else {
//            return "Failed to send Otp.please try again later..";
//        }
//    }

    //**************************************************************************************************
  //  top genretate controller

//    @PostMapping("/generate-otp")
//    public ResponseEntity<String> generateOtp(@RequestParam String mobile) {
//        String response = dairyService.generateAndSendOtp(mobile);
//        return ResponseEntity.ok(response);
//    }

    // Get data Id throw method
//    public Optional<FarmerMilkCollectionDto> getById(int id) {
//        Optional<FarmerMilkCollection> milkCollectionOpt = farmerMilkCollectionRepository.findById(id);
//
//        if (milkCollectionOpt.isPresent()) {
//            FarmerMilkCollection milkCollection = milkCollectionOpt.get();
//            Optional<FarmerRegistration> farmerOpt = addFarmerRepository.findByCode(milkCollection.getFarmerCode());
//
//            if (farmerOpt.isPresent()) {
//                FarmerRegistration farmer = farmerOpt.get();
//                return Optional.of(new FarmerMilkCollectionDto(
//                        milkCollection.getFarmerCode(),
//                        farmer.getFirstName(),
//                        farmer.getLastName(),
//                        milkCollection.getTotalPrice()
//                ));
//            }
//        }
//        return Optional.empty();
//    }


    //Get Id Throw
//    @GetMapping("/id/{id}")
//    public ResponseEntity<FarmerMilkCollectionDto> getFarmerMilkCollectionById(@PathVariable int id) {
//        FarmerMilkCollectionDto dto = farmerMilkCollectionService.getById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FarmerMilkCollection not found"));
//        return ResponseEntity.ok(dto);
//    }


//
//    public class FarmerMilkCollectionDto {
//
//        private Integer farmerId;
//        private String firstName;
//        private String lastName;
//        private String milkType;
//        private BigDecimal totalLiters;
//        private BigDecimal totalPrice;
//
//        // Constructor
//        public FarmerMilkCollectionDto(Integer farmerId, String firstName, String lastName, String milkType, BigDecimal totalLiters, BigDecimal totalPrice) {
//            this.farmerId = farmerId;
//            this.firstName = firstName;
//            this.lastName = lastName;
//            this.milkType = milkType;
//            this.totalLiters = totalLiters;
//            this.totalPrice = totalPrice;
//        }
//
//        public FarmerMilkCollectionDto(String farmerCode, String firstName, String lastName, String totalPrice) {
//
//            this.firstName = firstName;
//            this.lastName = lastName;
//
//        }
//
//
//
//        public Integer getFarmerId() {
//
//            return farmerId;
//        }
//
//        public void setFarmerId(Integer farmerId) {
//            this.farmerId = farmerId;
//        }
//
//        public String getFirstName() {
//            return firstName;
//        }
//
//        public void setFirstName(String firstName) {
//            this.firstName = firstName;
//        }
//
//        public String getLastName() {
//            return lastName;
//        }
//
//        public void setLastName(String lastName) {
//            this.lastName = lastName;
//        }
//
//        public String getMilkType() {
//            return milkType;
//        }
//
//        public void setMilkType(String milkType) {
//            this.milkType = milkType;
//        }
//
//        public BigDecimal getTotalLiters() {
//            return totalLiters;
//        }
//
//        public void setTotalLiters(BigDecimal totalLiters) {
//            this.totalLiters = totalLiters;
//        }
//
//        public BigDecimal getTotalPrice() {
//            return totalPrice;
//        }
//
//        public void setTotalPrice(BigDecimal totalPrice) {
//            this.totalPrice = totalPrice;
//        }
//    }
//******************************************************************************************************

//    @Query(value = """
//        SELECT f.farmer_id,
//               r.first_name,
//               r.last_name,
//               f.milk_type,
//               CAST(COALESCE(SUM(f.liter), 0) AS DECIMAL(10,2)) AS total_liters,
//               CAST(COALESCE(SUM(f.total_price), 0) AS DECIMAL(10,2)) AS total_price
//        FROM farmer_milk_collection f
//        JOIN farmer_registration r
//            ON f.farmer_id = r.id
//        WHERE f.farmer_id = :farmerId
//          AND f.milk_type = :milkType
//          AND f.created_date BETWEEN :startDate AND :endDate
//        GROUP BY f.farmer_id, r.first_name, r.last_name, f.milk_type
//        """, nativeQuery = true)
//    List<Object[]> findMilkCollectionSummary(
//            @Param("farmerId") Integer farmerId,
//            @Param("milkType") String milkType,
//            @Param("startDate") LocalDateTime startDate,
//            @Param("endDate") LocalDateTime endDate
//    );
//************************************************************************
    //*****************SAve Dairy Registration Code
    //
//    public String registerOrSendOtp(DairyRegistration dairyRegistration) {
//
//        Optional<DairyRegistration> existingDairy = dairyRepository.findByMobile(dairyRegistration.getMobile());
//        if (existingDairy.isPresent()) {
//            return "This mobile number is already registered!";
//        }
//
//        // Check if only firstName, lastName, and mobile are provided
//        boolean isPartialData = dairyRegistration.getDairyName() == null || dairyRegistration.getPassword() == null
//                || dairyRegistration.getTaluka() == null || dairyRegistration.getDistrict() == null
//                || dairyRegistration.getStateId() == 0 || dairyRegistration.getCollectionTypeId() == 0
//                || dairyRegistration.getMilkTypeId() == null || dairyRegistration.getCollectionShiftId() == 0
//                || dairyRegistration.getPaymentPeriodId() == 0;
//
//        String otp = generateOtp();
//        dairyRegistration.setOtp(otp);
//
//        if (isPartialData) {
//            System.out.println("OTP Sent to Mobile: " + otp);
//            return otp;
//        } else {
//            // Case 2: Full data (Save to DB)
//            dairyRegistration.setCreatedDate(LocalDateTime.now());
//            dairyRegistration.setUpdateDate(LocalDateTime.now());
//            dairyRepository.save(dairyRegistration);
//            return "Registration successful!";
//        }
//    }
    //******************************* secound Method save REgistrion
    //    public String registerDairy(DairyRegistration dairyRegistration){
//        // Generate OTP
//        String otp = generateOtp();
////        LocalDateTime otpExpiryTime = LocalDateTime.now().plusMinutes(5);
//
//        // Set OTP and expiry time
//        dairyRegistration.setOtp(otp);
////        dairyRegistration.setOtpExpiryTime(otpExpiryTime);
//
//
//        dairyRegistration.setUpdateDate(LocalDateTime.now());
//        dairyRegistration.setCreatedDate(LocalDateTime.now());
//        DairyRegistration savedDairy = dairyRepository.save(dairyRegistration);
//
//        // Send OTP via SMS
//        boolean smsSent = smsService.sendOtp(savedDairy.getMobile(), otp);
//
//
//        if (smsSent) {
//            return "OTP sent successfully to " + savedDairy.getMobile();
//        } else {
//            return "Failed to send OTP. Please try again later.";
//        }
//    }
    //*************** controller code to save **************

//    @PostMapping("/registerOrSendOtp")
//    public ResponseDTO registerOrSendOtp(@RequestBody DairyRegistration dairyRegistration) {
//        ResponseDTO response=new ResponseDTO();
//        try{
//            System.out.println("FirstName: "+dairyRegistration.getFirstName());
//            response.data = dairyService.registerOrSendOtp(dairyRegistration);
//            response.message="success";
//            response.success=true;
//            response.status=200l;
//
//        }catch (Exception e){
//            response.message="Not send to data";
//            response.success=false;
//            response.status=500l;
//        }
//
//        return response;
//    }
    ///***************** secound controller *****************
    //@PostMapping("/dairyRegister")
//public ResponseEntity<String> registerDairy(@RequestBody DairyRegistration dairyRegistration) {
//    String response = dairyService.registerDairy(dairyRegistration);
//    return ResponseEntity.ok(response);
//}

    //************************************************ ******************************************************
//Farmer Mollk Collection Addd code all

//    public FarmerMilkCollection generateBill(FarmerMilkCollection bill) {
//        bill.setCreatedDate(LocalDateTime.now());
//        bill.setUpdateDate(LocalDateTime.now());
//
//        try {
//            // Fetch DairyRegistration
//            DairyRegistration dairyRegistration = dairyRepository.findById(bill.getDairyRegistration().getdId())
//                    .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));
//
//            int collectionTypeId = dairyRegistration.getCollectionTypeId();
//
//            // Validate and retain only necessary fields
//            FarmerMilkCollection filteredBill = new FarmerMilkCollection();
//            filteredBill.setCreatedDate(bill.getCreatedDate());
//            filteredBill.setUpdateDate(bill.getUpdateDate());
//            filteredBill.setFarmerRegistration(bill.getFarmerRegistration());
//            filteredBill.setMilkTypeId(bill.getMilkTypeId());
//            filteredBill.setCollectionShiftId(bill.getCollectionShiftId());
//            filteredBill.setLiter(bill.getLiter());
//            filteredBill.setRate(bill.getRate());
//            filteredBill.setDairyRegistration(bill.getDairyRegistration());
//
//            if (collectionTypeId == 1) { // "FAT Only"
//                filteredBill.setFat(bill.getFat());
//                filteredBill.setLiter(bill.getLiter());
//            } else if (collectionTypeId == 2) { // "CLR Only"
//                filteredBill.setClr(bill.getClr());
//                filteredBill.setLiter(bill.getLiter());
//
//            } else if (collectionTypeId == 3) { // "Liter Only"
//                filteredBill.setLiter(bill.getLiter());
//            } else if (collectionTypeId == 4) { // "FAT+CLR"
//                filteredBill.setFat(bill.getFat());
//                filteredBill.setClr(bill.getClr());
//                filteredBill.setLiter(bill.getLiter());
//            } else if (collectionTypeId == 5) { // "FAT+SNF"
//                filteredBill.setFat(bill.getFat());
//                filteredBill.setSnf(bill.getSnf());
//                filteredBill.setLiter(bill.getLiter());
//            } else if (collectionTypeId == 6) { // "FAT+SNF+Auto SNF"
//                filteredBill.setFat(bill.getFat());
//                filteredBill.setSnf(bill.getSnf());
//                filteredBill.setClr(bill.getClr());
//                filteredBill.setLiter(bill.getLiter());
//                // Assuming Auto SNF is stored in SNF field
//            }
//
//            // Calculate total price
//            double liter = Double.parseDouble(filteredBill.getLiter());
//            double rate = Double.parseDouble(filteredBill.getRate());
//            double totalPrice = liter * rate;
//            filteredBill.setTotalPrice(totalPrice);
//
//            FarmerMilkCollection savedBill = farmerMilkCollectionRepository.save(filteredBill);
//            updateFarmerAccount(savedBill);
//            return savedBill;
//
//        } catch (Exception e) {
//            System.out.println("Error parsing data or fetching dairy registration: " + e.getMessage());
//            return null; // Handle failure case properly in real scenarios
//        }
//    }



//    public FarmerMilkCollection generateBill(FarmerMilkCollection bill) {
//        bill.setCreatedDate(LocalDateTime.now());
//        bill.setUpdateDate(LocalDateTime.now());
//
//        try {
//            // Fetch DairyRegistration by dId
//            DairyRegistration dairyRegistration = dairyRepository.findById(bill.getDairyRegistration().getdId())
//                    .orElseThrow(() -> new RuntimeException("Dairy Registration not found"));
//
//            int collectionTypeId = dairyRegistration.getCollectionTypeId(); // Get collectionTypeId
//
//            // Allowed fields for each collectionTypeId
//            Map<Integer, Set<String>> allowedFieldsMap = new HashMap<>();
//            allowedFieldsMap.put(1, new HashSet<>(Arrays.asList("milkTypeId", "collectionShiftId",  "fat")));
//            allowedFieldsMap.put(2, new HashSet<>(Arrays.asList("milkTypeId", "collectionShiftId",  "clr")));
//            allowedFieldsMap.put(3, new HashSet<>(Arrays.asList("milkTypeId", "collectionShiftId" )));
//            allowedFieldsMap.put(4, new HashSet<>(Arrays.asList("milkTypeId", "collectionShiftId", "fat", "clr")));
//            allowedFieldsMap.put(5, new HashSet<>(Arrays.asList("milkTypeId", "collectionShiftId",  "fat", "snf")));
//            allowedFieldsMap.put(6, new HashSet<>(Arrays.asList("milkTypeId", "collectionShiftId","fat", "snf")));
//
//            // Get allowed fields for current collectionTypeId
//            Set<String> allowedFields = allowedFieldsMap.getOrDefault(collectionTypeId, new HashSet<>());
//
//            // ✅ Ensure 'liter' and 'rate' are always provided
//            if (bill.getLiter() == null || bill.getLiter().trim().isEmpty()) {
//                throw new RuntimeException("Liter value is required and cannot be null or empty.");
//            }
//            if (bill.getRate() == null || bill.getRate().trim().isEmpty()) {
//                throw new RuntimeException("Rate value is required and cannot be null or empty.");
//            }
//
//            // Create a new object to store only allowed fields
//            FarmerMilkCollection filteredBill = new FarmerMilkCollection();
//            filteredBill.setCreatedDate(bill.getCreatedDate());
//            filteredBill.setUpdateDate(bill.getUpdateDate());
//            filteredBill.setFarmerRegistration(bill.getFarmerRegistration());
//            filteredBill.setMilkTypeId(bill.getMilkTypeId());
//            filteredBill.setCollectionShiftId(bill.getCollectionShiftId());
//            filteredBill.setLiter(bill.getLiter()); // ✅ Always save liter
//            filteredBill.setRate(bill.getRate());
//            filteredBill.setDairyRegistration(bill.getDairyRegistration());
//
//            // Set only allowed fields based on collectionTypeId
//            if (allowedFields.contains("fat")) {
//                filteredBill.setFat(bill.getFat());
//            }
//            if (allowedFields.contains("clr")) {
//                filteredBill.setClr(bill.getClr());
//            }
//            if (allowedFields.contains("snf")) {
//                filteredBill.setSnf(bill.getSnf());
//            }
//
//            // ✅ Prevent NullPointerException by handling null values in liter and rate
//            double liter = (filteredBill.getLiter() != null && !filteredBill.getLiter().trim().isEmpty()) ? Double.parseDouble(filteredBill.getLiter().trim()) : 0.0;
//            double rate = (filteredBill.getRate() != null && !filteredBill.getRate().trim().isEmpty()) ? Double.parseDouble(filteredBill.getRate().trim()) : 0.0;
//            double totalPrice = liter * rate;
//            filteredBill.setTotalPrice(totalPrice);
//
//            // Save filtered data
//            FarmerMilkCollection savedBill = farmerMilkCollectionRepository.save(filteredBill);
//            updateFarmerAccount(savedBill);
//
//            return savedBill;
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error processing bill: " + e.getMessage());
//        }
//    }

//    public FarmerMilkCollection saveFarmerMilk(FarmerMilkCollection milkCollection ,int dId){
//        DairyRegistration dairy=dairyRepository.findById(dId)
//                .orElseThrow(() -> new RuntimeException("Dairy not found"));
//        List<Integer>dairyMilkType=dairy.getMilkTypeList();
//        List<Integer>collectionMilk= Collections.singletonList(milkCollection.getMilkTypeId());
//
//        List<String>dairyShiftId= Collections.singletonList(dairy.getCollectionShiftId());
//        List<Integer>collectionShiftMilk= Collections.singletonList(milkCollection.getCollectionShiftId());
//
//        List<Integer>dairyCollectionType= Collections.singletonList(dairy.getCollectionTypeId());
//
//        if (!dairyMilkType.containsAll(collectionMilk)){
//            throw new IllegalArgumentException("Invalid milk type! This dairy only allows " + dairyMilkType);
//        }
//        if (!dairyShiftId.containsAll(collectionShiftMilk)){
//            throw  new IllegalArgumentException("Invalid collection shift Type! This dairy Only allow"+dairyShiftId);
//        }
//    }


    ////////////////////***************************************************///////////////////////////////////

//    package com.dairy.Controller;
//
//import com.dairy.Dto.ResponseDTO;
//import com.dairy.Entity.DairyNotice;
//import com.dairy.Entity.DairyRegistration;
//import com.dairy.Entity.StateMaster;
//import com.dairy.Services.DairyNoticeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//    @RestController
//    @RequestMapping("/api/Notice")
//    public class DairyNoticeController {
//        @Autowired
//        private DairyNoticeService dairyNoticeService;
//
//
//        @PostMapping("/saveNotice")
//        public ResponseDTO saveNotice(@RequestBody DairyNotice dairyNotice){
//            ResponseDTO response=new ResponseDTO();
//            try {
//                response.data=dairyNoticeService.saveNotice(dairyNotice);
//                response.message="success";
//                response.status=200L;
//                response.success=true;
//            }catch (Exception e){
//                response.message="filed...";
//                response.status=500L;
//                response.success=false;
//            }
//            return response;
//        }
//
//
//        @GetMapping("/getNotice")
//        public ResponseDTO getNotice(){
//            ResponseDTO response=new ResponseDTO();
//            try {
//                response.data=dairyNoticeService.getAllNotice();
//                response.message="success";
//                response.status=200L;
//                response.success=true;
//            }catch (Exception e){
//                response.message="filed...";
//                response.status=500L;
//                response.success=false;
//            }
//            return response;
//        }
//
//        @GetMapping("/getNoticeId/{id}")
//        public ResponseDTO getNoticeById(@PathVariable("id")  Integer id){
//            ResponseDTO response=new ResponseDTO();
//            try {
//                response.data=dairyNoticeService.getAllNoticeByID(id);
//                response.message="success";
//                response.status=200L;
//                response.success=true;
//            }catch (Exception e){
//                response.message="filed...";
//                response.status=500L;
//                response.success=false;
//            }
//            return response;
//        }
//
//        @PutMapping("/updateNotice")
//        public ResponseDTO updateNotice(@RequestBody DairyNotice dairyNotice){
//            ResponseDTO response=new ResponseDTO();
//            try {
//                response.data=dairyNoticeService.UpdateNotice(dairyNotice);
//                response.message="success";
//                response.success=true;
//                response.status=200L;
//            }catch (Exception e){
//                response.message=e.getMessage();
//                response.status=500L;
//                response.success=false;
//
//            }
//
//            return response;
//        }
//
//        @PutMapping("/deactivate/{id}")
//        public ResponseDTO deactivateNotice(@PathVariable int id) {
////        try {
////            DairyNotice deactive = dairyNoticeService.deactivateNotice(id);
////            return ResponseEntity.status(HttpStatus.OK).body("Deactivated successfully");
////        } catch (Exception e) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Found !");
////        }
//            ResponseDTO response=new ResponseDTO();
//            try {
//                response.data=dairyNoticeService.deactivateNotice(id);
//                response.message="success";
//                response.status=200L;
//                response.success=true;
//            }catch (Exception e){
//                response.message="filed...";
//                response.status=500L;
//                response.success=false;
//            }
//            return response;
//        }
//        @PutMapping("/activate/{id}")
//        public ResponseDTO activeNotice(@PathVariable  int id){
////        return dairyNoticeService.activateNotice(id);
//            ResponseDTO response=new ResponseDTO();
//            try {
//                response.data=dairyNoticeService.activateNotice(id);
//                response.message="success";
//                response.status=200L;
//                response.success=true;
//            }catch (Exception e){
//                response.message="filed...";
//                response.status=500L;
//                response.success=false;
//            }
//            return response;
//        }
//
//
//    }



}
