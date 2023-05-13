package com.foodpurchasingsystem.foodpurchasingsystem.util;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.DeliveredItem;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.DeliveredItemRepo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelUtil {

    private final DeliveredItemRepo deliveredItemRepo;

    public ExcelUtil(DeliveredItemRepo deliveredItemRepo) {
        this.deliveredItemRepo = deliveredItemRepo;
    }

     public void generateExcelFile(HttpServletResponse response) throws IOException {

        List<DeliveredItem> deliveredItemList = deliveredItemRepo.findAll();

         HSSFWorkbook workbook = new HSSFWorkbook();
         HSSFSheet sheet = workbook.createSheet("Delivered Items");
         HSSFRow row = sheet.createRow(0);

         row.createCell(0).setCellValue("ID");
         row.createCell(1).setCellValue("Deliver date");
         row.createCell(2).setCellValue("Is active");
         row.createCell(3).setCellValue("Quantity");
         row.createCell(4).setCellValue("Order ID");
         row.createCell(5).setCellValue("Product ID");
         row.createCell(6).setCellValue("User ID");

         int dataRowIndex = 1;
         for(DeliveredItem deliveredItem : deliveredItemList){
             HSSFRow dataRow = sheet.createRow(dataRowIndex);
             dataRow.createCell(0).setCellValue(deliveredItem.getId());
             dataRow.createCell(1).setCellValue(deliveredItem.getDeliverDate());
             dataRow.createCell(2).setCellValue(deliveredItem.isActive());
             dataRow.createCell(3).setCellValue(deliveredItem.getQuantity());
             dataRow.createCell(4).setCellValue(deliveredItem.getOrder().getOrderId());
             dataRow.createCell(5).setCellValue(deliveredItem.getProduct().getProductName());
             dataRow.createCell(6).setCellValue(deliveredItem.getUser().getUsername());
             dataRowIndex++;
         }

         ServletOutputStream ops = response.getOutputStream();
         workbook.write(ops);
         workbook.close();
         ops.close();


     }
}
