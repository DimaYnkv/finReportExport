package com.dims.finReportExport.converter;

import com.dims.finReportExport.model.FinRow;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class XlsConverter {


    public List<FinRow> extractFinRows(String reportPath){
        List<FinRow> allRows = new ArrayList<>();

        try {
            Workbook workbook = getFile(reportPath);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            rowIterator.forEachRemaining(r -> {
                if(r.getCell(1) != null && r.getCell(1).getCellTypeEnum() == CellType.NUMERIC){
                    int i = 1;
                    Date dealDate = r.getCell(i++).getDateCellValue();
                    if (dealDate != null) {
                        FinRow finRow = new FinRow();
                        finRow.setDealDate(dealDate);
                        finRow.setBusinessName(r.getCell(i++).getStringCellValue());
                        finRow.setChargeSum(getNumericWithPossibleSpaces(r.getCell(i++)));
                        //change
                        if(r.getCell(i).getCellTypeEnum() != CellType.NUMERIC){
                            finRow.setOriginalCurrency(r.getCell(i++).getStringCellValue());
                            finRow.setDealSum(getNumericWithPossibleSpaces(r.getCell(i++)));
                        }else {
                            finRow.setDealSum(getNumericWithPossibleSpaces(r.getCell(i++)));
                            finRow.setOriginalCurrency("NIS");
                        }

                        finRow.setInfo(r.getCell(i).getStringCellValue());
                        allRows.add(finRow);
                    }
                }
            });
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return allRows;
    }

    public Workbook getFile(String filePath) throws IOException, InvalidFormatException {
        return WorkbookFactory.create(new File(filePath));
    }

    private Double getNumericWithPossibleSpaces(Cell cell) {
        double sum = Double.NaN;
        if(cell.getCellTypeEnum() == CellType.STRING){
            String stringCellValue = cell.getStringCellValue();
            stringCellValue = stringCellValue.replace(" ", "");
            sum = Double.parseDouble(stringCellValue);
        }else if(cell.getCellTypeEnum() == CellType.NUMERIC){
            sum = cell.getNumericCellValue();
        }
        return sum;
    }
}
