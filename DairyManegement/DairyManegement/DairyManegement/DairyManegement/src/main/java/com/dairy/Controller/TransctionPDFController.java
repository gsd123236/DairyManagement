package com.dairy.Controller;

import com.dairy.Entity.FarmerAccount;
import com.dairy.Entity.FarmerBalanceSheet;
import com.dairy.Repository.FarmerAccountRepository;
import com.dairy.Repository.FarmerBalanceSheetRepository;
import com.dairy.Services.DatabasePDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pdf")
public class TransctionPDFController {

    @Autowired
    private FarmerAccountRepository farmerAccountRepository;

    @Autowired
    private DatabasePDFService databasePDFService;
    @Autowired
    private FarmerBalanceSheetRepository farmerBalanceSheet;
    @GetMapping(value = "/farmerTransaction/{farmerId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> farmerTransaction(@PathVariable("farmerId") int farmerId) throws IOException {

            List<FarmerAccount> farmerTransactions = farmerAccountRepository.findTransactionByFarmer(farmerId);
            double totalBalance = farmerBalanceSheet.getByFarmerId(farmerId);
            ByteArrayInputStream bis = databasePDFService.farmerAccountPDFReport(farmerTransactions, totalBalance);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=transaction.pdf");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));


    }

    @GetMapping(value = "/dateBy/{farmerId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> farmerTransactionByDate(
            @PathVariable("farmerId") int farmerId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        List<FarmerAccount> allTransactions = farmerAccountRepository.findTransactionByFarmer(farmerId);
        List<FarmerAccount> filteredTransactions = allTransactions.stream()
                .filter(t -> !t.getDate().isBefore(start) && !t.getDate().isAfter(end))
                .collect(Collectors.toList());
        double totalBalance =farmerBalanceSheet.getByFarmerId(farmerId);
        ByteArrayInputStream bis = databasePDFService.farmerAccountPDFReport(filteredTransactions, totalBalance);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=transaction.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
