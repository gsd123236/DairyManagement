package com.dairy.Services;

import com.dairy.Entity.FarmerAccount;
import com.dairy.Entity.FarmerBalanceSheet;
import com.dairy.Entity.PaymentMethodMaster;
import com.dairy.Repository.PaymentMethodMasterRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DatabasePDFService {

    @Autowired
    private PaymentMethodMasterRepository paymentMethodMasterRepository;
    public ByteArrayInputStream farmerAccountPDFReport(List<FarmerAccount> farmerAccounts, double totalBalance) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();


            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
            Paragraph para = new Paragraph("Transaction Report", fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(7); // 7 Columns
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 5, 3, 3, 3, 3, 3});
            Stream.of("Date", "Payment Type", "Payment Details", "Debit", "Credit", "Advance", "Balance").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
                header.setBackgroundColor(Color.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);

                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (FarmerAccount farmerAccount : farmerAccounts) {
                String formattedDate = farmerAccount.getDate().format(formatter);

                table.addCell(createCell(formattedDate));
                table.addCell(createCell(getPaymentMethodName(farmerAccount.getPaymentMethod())));
                table.addCell(createCell(farmerAccount.getNumber()));
                table.addCell(createCell(String.valueOf(farmerAccount.getDebit())));
                table.addCell(createCell(String.valueOf(farmerAccount.getCredit())));
                table.addCell(createCell(String.valueOf(farmerAccount.getAdvance())));
                table.addCell(createCell(String.valueOf(farmerAccount.getBalance())));
            }
            document.add(table);
            PdfPTable balanceTable = new PdfPTable(1);
            balanceTable.setWidthPercentage(30);
            balanceTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell balanceCell = new PdfPCell(new Phrase("Total Balance: ₹" + String.format("%.2f", totalBalance)));
            balanceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            balanceCell.setBorder(Rectangle.NO_BORDER);
            balanceCell.setBorder(1);
            balanceCell.setBackgroundColor(Color.WHITE);
            balanceCell.setPadding(5);
            balanceTable.addCell(balanceCell);
            document.add(balanceTable);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    private PdfPCell createCell(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value));
        cell.setPaddingLeft(4);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
    private String getPaymentMethodName(int paymentMethodId) {
        return paymentMethodMasterRepository.findById(paymentMethodId)
                .map(PaymentMethodMaster::getPaymentMethod)
                .orElse("Unknown");
    }
}
