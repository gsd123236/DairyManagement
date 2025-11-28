package com.dairy.Services;

import com.dairy.Entity.DairyNotice;
import com.dairy.Entity.DairyRegistration;
import com.dairy.Repository.DairyNoticeRepository;
import com.dairy.Repository.DairyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DairyNoticeService {
    @Autowired
    private DairyNoticeRepository dairyNoticeRepository;
    @Autowired
    private DairyRepository dairyRepository;

    public DairyNotice saveNotice( Integer dairyId,DairyNotice dairyNotice) {

        DairyRegistration dairyRegistration = dairyRepository.findById(dairyId)
                .orElseThrow(() -> new RuntimeException("DairyRegistration not found"));
        dairyNotice.setCreatedDate(LocalDateTime.now());
        dairyNotice.setDairyRegistration(dairyRegistration);
        return dairyNoticeRepository.save(dairyNotice);
    }

  public List<DairyNotice> getByDairyId(int did){
        return dairyNoticeRepository.getNoticeByDairyId(did);
  }
    public List<DairyNotice> getAllNotice(){
        return dairyNoticeRepository.findAll();
    }

    public Optional<DairyNotice> getAllNoticeByID(Integer id){
        return  dairyNoticeRepository.findById(id);
    }

    public DairyNotice UpdateNotice(DairyNotice dairyNotice){
        if (dairyNotice.getId() == 0) {
            throw new IllegalArgumentException("Invalid Id: Update failed");
        }
        Optional <DairyNotice> updateData=dairyNoticeRepository.findById(dairyNotice.getId() );
        System.out.println("Update Data........"+dairyNotice);

        if(updateData.isPresent()){
            DairyNotice updateRecord =updateData.get();
            if (dairyNotice.getNewNotice() != null) {
                updateRecord.setNewNotice(dairyNotice.getNewNotice());
            }
            if (dairyNotice.getDetails() != null) {
                updateRecord.setDetails(dairyNotice.getDetails());
            }

            updateRecord.setUpdateDate(LocalDateTime.now());



            return  dairyNoticeRepository.save(updateRecord);
        }else {
            throw new NoSuchElementException("Record Not Found For Update");
        }

    }

    public DairyNotice deactivateNotice(int id){
        Optional<DairyNotice> optionalNotice=dairyNoticeRepository.findById(id);
        if (optionalNotice.isPresent()){
            DairyNotice dairyNotice=optionalNotice.get();
            dairyNotice.setActive(false);
            dairyNotice.setDelete(true);
            return dairyNoticeRepository.save(dairyNotice);
        }else {
            throw  new RuntimeException("State with ID " + id + " not found");
        }
    }

    public DairyNotice activateNotice(int id){
        Optional<DairyNotice> notice=dairyNoticeRepository.findById(id);
        if(notice.isPresent()){
            DairyNotice s=notice.get();
            notice.get().setActive(true);
            notice.get().setDelete(false);
            return dairyNoticeRepository.save(s);
        }
        else {
            throw new RuntimeException("State with Id"+id+"not found");
        }
    }
    public List<DairyNotice>findByDairyId(int did,DairyNotice dairyNotice){
        return dairyNoticeRepository.getNoticeByDairyId(did);
    }
}
