package com.dairy.Services;



import com.dairy.Dto.DairyAdminDTO;
import com.dairy.Dto.DairyLoginResponseDto;
import com.dairy.Dto.RegistrationDTO;
import com.dairy.Entity.*;
import com.dairy.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DairyService {

    @Autowired
    private DairyRepository dairyRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private StateMasterRepository stateMasterRepository;

    @Autowired
    private MilkTypeMasterRepository milkTypeMasterRepository;

    @Autowired
    private CollectionTypeMasterRepository collectionTypeMasterRepository;

    @Autowired
    private CollectionShiftMasterRepository collectionShiftMasterRepository;

    @Autowired
    private PaymentPeriodMasterRepository paymentPeriodMasterRepository;


    // this method save to all data


    public DairyRegistration registerDairy1(DairyRegistration dairyRegistration) {
        Optional<DairyRegistration> existingDairy = dairyRepository.findByMobile(dairyRegistration.getMobile());

//        String otp=generateOtp();
//        dairyRegistration.setOtp(otp);
        dairyRegistration.setUpdateDate(LocalDateTime.now());
        dairyRegistration.setCreatedDate(LocalDateTime.now());

        if ("3".equals(dairyRegistration.getCollectionShiftId())) {
            dairyRegistration.setCollectionShiftId("1,2");
        }

        if ("3".equals(dairyRegistration.getMilkTypeId())) {
            dairyRegistration.setMilkTypeId("1,2");
        }
        if (existingDairy.isPresent()) {
            throw new RuntimeException("Mobile Number Already Exist");
        }
        else{
            dairyRegistration= dairyRepository.save(dairyRegistration);
        }

        return dairyRegistration;
    }

    public String registerDairyOtp(DairyRegistration dairyRegistration) {
        Optional<DairyRegistration> existingDairy = dairyRepository.findByMobile(dairyRegistration.getMobile());
        String message="";
        if (existingDairy.isPresent()) {
            message="Mobile Number Already Exist";
        }
        else{
            message=generateOtp();
        }

        return message;
    }
    private String generateOtp() {
        Random random=new Random();
        int otp=1000 +random.nextInt(9000);
        return String.valueOf(otp);
    }

    public Optional<DairyRegistration> loginDairy(String mobile, String password) {
        Optional<DairyRegistration> user = dairyRepository.findByMobile(mobile);
        if (user.isPresent()) {
            DairyRegistration dairy = user.get();
//            int stateid= Integer.parseInt(dairy.getMilkTypeId());
//            System.out.println(  stateMasterRepository.findNameById(stateid));

            if (dairy.getPassword().equals(password)) {
                return user;
            }
        }
        return Optional.empty();
    }




    public DairyRegistration dairyUpdate(DairyRegistration dairyRegistration,Integer dId) {
        Optional<DairyRegistration> updateData = dairyRepository.findById(dId);

//        if (dairyRegistration.getdId() == 0) {
//            return "Invalid Id: Update failed";
//        }

        if (updateData.isPresent()) {
            DairyRegistration updateRecord = updateData.get();
            System.out.println("update Data...."+dairyRegistration);


            if (dairyRegistration.getFirstName() != null) {
                updateRecord.setFirstName(dairyRegistration.getFirstName());
            }
            if (dairyRegistration.getLastName() != null) {
                updateRecord.setLastName(dairyRegistration.getLastName());
            }
            if (dairyRegistration.getDairyName() != null) {
                updateRecord.setDairyName(dairyRegistration.getDairyName());
            }
            if (dairyRegistration.getDistrict() != null) {
                updateRecord.setDistrict(dairyRegistration.getDistrict());
            }
            if(dairyRegistration.getCollectionShiftId() != null){
                updateRecord.setCollectionShiftId(dairyRegistration.getCollectionShiftId());
            }
            if(dairyRegistration.getCollectionTypeId() != null){
                updateRecord.setCollectionTypeId(dairyRegistration.getCollectionTypeId());
            }

            if(dairyRegistration.getMilkTypeId()!= null){
                updateRecord.setMilkTypeId(dairyRegistration.getMilkTypeId());
            }

            if(dairyRegistration.getPaymentPeriodId()!=null){
                updateRecord.setPaymentPeriodId(dairyRegistration.getPaymentPeriodId());
            }

            if(dairyRegistration.getPassword()!=null){
                updateRecord.setPassword(dairyRegistration.getPassword());
            }

            if(dairyRegistration.getStateId()!=null){
                updateRecord.setStateId(dairyRegistration.getStateId());
            }

            if(dairyRegistration.getTaluka()!=null){
                updateRecord.setTaluka(dairyRegistration.getTaluka());
            }

            if (dairyRegistration.getVillage()!=null){
                updateRecord.setVillage(dairyRegistration.getVillage());
            }


            updateRecord.setUpdateDate(LocalDateTime.now());


           return dairyRepository.save(updateRecord);

        } else {
           throw new RuntimeException("Dairy with Id "+dId+" not found");
        }
    }

    //get data method all

    public List<Object[]>getProfileData(Integer dId){
        return dairyRepository.findDairyDetailsByDId(dId);
    }

    public List<Object[]> getCollectionProfile(Integer dId) {
        return dairyRepository.getMilkCollection(dId);
    }


//    public List<DairyRegistration>getAllData(){
//        return dairyRepository.findAll();
//    }



    public List<DairyAdminDTO> getAllData() {
        List<DairyRegistration> dairyRegistrations = dairyRepository.findAll();

        Map<String, String> stateMap = stateMasterRepository.findAll().stream()
                .collect(Collectors.toMap(
                        s -> String.valueOf(s.getId()),
                        StateMaster::getStateName
                ));

        Map<String, String> collectionTypeMap = collectionTypeMasterRepository.findAll().stream()
                .collect(Collectors.toMap(
                        c -> String.valueOf(c.getId()),
                        CollectionTypeMaster::getCollectionName
                ));

        Map<String, String> milkTypeMap = milkTypeMasterRepository.findAll().stream()
                .collect(Collectors.toMap(
                        m -> String.valueOf(m.getId()),
                        MilkTypeMaster::getAnimalName
                ));

        Map<String, String> collectionShiftMap = collectionShiftMasterRepository.findAll().stream()
                .collect(Collectors.toMap(
                        c -> String.valueOf(c.getId()),
                        CollectionShiftMaster::getShift
                ));

        Map<String, String> paymentPeriodMap = paymentPeriodMasterRepository.findAll().stream()
                .collect(Collectors.toMap(
                        p -> String.valueOf(p.getId()),
                        PaymentPeriodMaster::getPaymentPeriodName
                ));

        List<DairyAdminDTO> dtoList = new ArrayList<>();

        for (DairyRegistration dairy : dairyRegistrations) {


            // Handle possible multiple IDs
            String milkTypeNames = Arrays.stream(dairy.getMilkTypeId().split(","))
                    .map(id -> milkTypeMap.getOrDefault(id.trim(), "Unknown"))
                    .collect(Collectors.joining(", "));

            String shiftNames = Arrays.stream(dairy.getCollectionShiftId().split(","))
                    .map(id -> collectionShiftMap.getOrDefault(id.trim(), "Unknown"))
                    .collect(Collectors.joining(", "));

            DairyAdminDTO dto = new DairyAdminDTO(
                    dairy.getdId(),
                    dairy.getFirstName(),
                    dairy.getLastName(),
                    dairy.getDairyName(),
                    dairy.getPassword(),
                    dairy.getMobile(),
                    dairy.getVillage(),
                    dairy.getTaluka(),
                    dairy.getDistrict(),
                    stateMap.getOrDefault(dairy.getStateId(), "Unknown"),
                    collectionTypeMap.getOrDefault(dairy.getCollectionTypeId(), "Unknown"),
                    milkTypeNames,
                    shiftNames,
                    paymentPeriodMap.getOrDefault(dairy.getPaymentPeriodId(), "Unknown"),
                    dairy.getCreatedDate().toLocalDate(),
                    dairy.getUpdateDate()
            );

            dtoList.add(dto);
        }

        return dtoList;
    }



    public Optional<DairyRegistration>findId(int dId){
        return dairyRepository.findById(dId);
    }

    public List<DairyRegistration> getActivedata(){
        return dairyRepository.getAllActiveOrDeactive();
    }


    // get master data

    public List<RegistrationDTO> getAllCategories() {
        List<DairyRegistration> dairyRegistrations = dairyRepository.findAll();

        // Fetch all related data once and store in Maps
        Map<Integer, String> stateMap = stateMasterRepository.findAll()
                .stream()
                .collect(Collectors.toMap(StateMaster::getId, StateMaster::getStateName));

        Map<Integer, String> collectionTypeMap = collectionTypeMasterRepository.findAll()
                .stream()
                .collect(Collectors.toMap(CollectionTypeMaster::getId, CollectionTypeMaster::getCollectionName));


        Map<Integer, String> paymentTypeMap = paymentPeriodMasterRepository.findAll()
                .stream()
                .collect(Collectors.toMap(PaymentPeriodMaster::getId, PaymentPeriodMaster::getPaymentPeriodName));

        return dairyRegistrations.stream()
                .map(dairy -> {
                    String state = stateMap.getOrDefault(dairy.getStateId(), "Unknown");
                    String collectionType = collectionTypeMap.getOrDefault(dairy.getCollectionTypeId(), "Unknown");
//                    String shiftType = collectionShiftTypeMap.getOrDefault(dairy.getCollectionShiftId(), "Unknown");
                    String paymentType = paymentTypeMap.getOrDefault(dairy.getPaymentPeriodId(), "Unknown");


                    List<Map<String, Object>> milkNames = new ArrayList<>();
                    if (dairy.getMilkTypeId() != null && !dairy.getMilkTypeId().isEmpty()) {
                        milkNames = Arrays.stream(dairy.getMilkTypeId().split("[/,|]"))
                                .map(milkTypeId -> {
                                    try {
                                        int parsedMilkTypeId = Integer.parseInt(milkTypeId.trim());
                                        return milkTypeMasterRepository.findById(parsedMilkTypeId)
                                                .map(milkType -> {
                                                    Map<String, Object> milkData = new HashMap<>();
                                                    milkData.put("id", parsedMilkTypeId);
                                                    milkData.put("name", milkType.getAnimalName());
                                                    return milkData;
                                                })
                                                .orElse(null);
                                    } catch (NumberFormatException e) {
                                        return null; // Ignore invalid IDs
                                    }
                                })
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList());
                    }

                    List<Map<String, Object>> collectionShift = new ArrayList<>();
                    if (dairy.getCollectionShiftId() != null && !dairy.getCollectionShiftId().isEmpty()) {
                        collectionShift = Arrays.stream(dairy.getCollectionShiftId().split("[/,|]")) // Supports multiple delimiters
                                .map(collectionShiftId -> {
                                    try {
                                        int parsedCollectionShiftId = Integer.parseInt(collectionShiftId.trim());
                                        return collectionShiftMasterRepository.findById(parsedCollectionShiftId)
                                                .map(collectionShiftMaster -> {
                                                    Map<String, Object> collectionData = new HashMap<>();
                                                    collectionData.put("id", parsedCollectionShiftId);
                                                    collectionData.put("name",collectionShiftMaster.getShift());
                                                    return collectionData ;
                                                })
                                                .orElse(null);
                                    } catch (NumberFormatException e) {
                                        return null; // Ignore invalid IDs
                                    }
                                })
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList());
                    }

                    return new RegistrationDTO(state, milkNames, collectionType,collectionShift, paymentType);
                })
                .collect(Collectors.toList());
    }

    public RegistrationDTO findByAllId(int id) {
        DairyRegistration dairy = dairyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dairy Registration not found for ID: " + id));


        String state = stateMasterRepository.findById(Integer.valueOf(dairy.getStateId().toString()))
                .map(StateMaster::getStateName)
                .orElse("Unknown");


        String collectionType = collectionTypeMasterRepository.findById(Integer.valueOf(dairy.getCollectionTypeId().toString()))
                .map(CollectionTypeMaster::getCollectionName)
                .orElse("Unknown");


        String paymentType = paymentPeriodMasterRepository.findById(Integer.valueOf(dairy.getPaymentPeriodId().toString()))
                .map(PaymentPeriodMaster::getPaymentPeriodName)
                .orElse("Unknown");



        List<Map<String, Object>> milkNames = new ArrayList<>();

        if (dairy.getMilkTypeId() != null && !dairy.getMilkTypeId().isEmpty()) {
            milkNames = Arrays.stream(dairy.getMilkTypeId().split("[/,|]")) // Supports multiple delimiters
                    .map(milkTypeId -> {
                        try {
                            int parsedMilkTypeId = Integer.parseInt(milkTypeId.trim());
                            return milkTypeMasterRepository.findById(parsedMilkTypeId)
                                    .map(milkType -> {
                                        Map<String, Object> milkData = new HashMap<>();
                                        milkData.put("id", parsedMilkTypeId);
                                        milkData.put("name", milkType.getAnimalName());
                                        return milkData;
                                    })
                                    .orElse(null);
                        } catch (NumberFormatException e) {
                            return null; // Ignore invalid IDs
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        List<Map<String, Object>> collectionShift = new ArrayList<>();
        if (dairy.getCollectionShiftId() != null && !dairy.getCollectionShiftId().isEmpty()) {
            collectionShift = Arrays.stream(dairy.getCollectionShiftId().split("[/,|]"))
                    .map(collectionShiftId -> {
                        try {
                            int parsedCollectionShiftId = Integer.parseInt(collectionShiftId.trim());
                            return collectionShiftMasterRepository.findById(parsedCollectionShiftId)
                                    .map(collectionShiftMaster -> {
                                        Map<String, Object> collectionData = new HashMap<>();
                                        collectionData.put("id", parsedCollectionShiftId);
                                        collectionData.put("name",collectionShiftMaster.getShift());
                                        return collectionData ;
                                    })
                                    .orElse(null);
                        } catch (NumberFormatException e) {
                            return null; // Ignore invalid IDs
                        }
                    })
                    .filter(Objects::nonNull) // Remove null values
                    .collect(Collectors.toList());
        }


        return new RegistrationDTO(state, milkNames, collectionType, collectionShift, paymentType);
    }

    public DairyRegistration deactivateDairy(int id) {
        Optional<DairyRegistration> dairyId = dairyRepository.findById(id);
        if(dairyId.isPresent()){
            DairyRegistration dairy=dairyId.get();
            dairy.setActive(false);
            dairy.setDeactive(true);
            return dairyRepository.save(dairy);
        }
        return null;
    }

    public  DairyRegistration activeDairy(int id){
        Optional<DairyRegistration> dairyId=dairyRepository.findById(id);
            if (dairyId.isPresent()) {
                DairyRegistration dairy = dairyId.get();
                dairy.setActive(true);
                dairy.setDeactive(false);
                return dairyRepository.save(dairy);
            }

        return null;
    }

    public DairyRegistration isDeleted(int id){
        Optional<DairyRegistration> dairyId=dairyRepository.findById(id);

        if(dairyId.isPresent()){
            DairyRegistration dairy=dairyId.get();
            dairy.setActive(false);
            dairy.setDeactive(false);
            dairy.setDeleted(true);
        }
        return null;
    }
}
