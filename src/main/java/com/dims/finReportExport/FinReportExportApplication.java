package com.dims.finReportExport;

import com.dims.finReportExport.converter.PdfConverter;
import com.dims.finReportExport.converter.XlsConverter;
import com.dims.finReportExport.model.FinRow;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

//@SpringBootApplication
public class FinReportExportApplication {


	private static PdfConverter converter = new PdfConverter();

	public static void main(String[] args) {
//		SpringApplication.run(FinReportExportApplication.class, args);
		XlsConverter converter = new XlsConverter();

		List<FinRow> finRows = converter.extractFinRows("c:\\fin\\report.xls");

		MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "fin");
		if(mongoOps.count(new Query(), FinRow.class) == 0){
			mongoOps.insertAll(finRows);
		}
		//TODO: category classification by mongo


		Double totalMonthlyExpanseSum = 0D;

		totalMonthlyExpanseSum = finRows.stream().map(FinRow::getChargeSum).reduce(Double::sum).get();


		//TODO: connect to google drive API
		//TODO: store the results to the right columns


	}


	public static void saveDataToGExcel(List<FinRow> finRows){

	}





//
//	public static void extractDataFromRows(String[] rows){
//		System.out.println("rows: " + rows);
//		String dealDate;
//		String businessName;
//		String dealSum;
//		String chargeSum;
//		String info;
//
//		for (String row : rows){
//			dealDate = row.substring(0, row.indexOf("/",5) + 5);
//			String temp = row.substring(0, row.indexOf("."));
//			businessName = row.substring(row.indexOf(dealDate) + 2, temp.lastIndexOf(" "));
//			dealSum = row.substring(row.indexOf(businessName + 2, row.indexOf(" ", row.indexOf(businessName +2))));
//		}
//	}
//
//
//	public static void pdfCoverter(){
//
//		try {
//			String report = converter.generateTxtFromPDF("c:\\fin\\eng_report1.pdf");
////			System.out.println(report);
//
//			List<String> rows = extractRows(report);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static List<String> extractRows(String report) {
//
//		List<String> rows = new ArrayList<>();
//
//
//		String[] rs = report.split("\n");
//
//		int i = 8;
//		while(i < rs.length){
//			String r = rs[i++];
//			if(Character.isDigit(r.charAt(0)) && r.length() > 10){
//				rows.add(r);
//			}
//
////			if(Character.isAlphabetic(r.charAt(0)) && Character.isUpperCase(r.charAt(0))){
////				rows.add(r);
////			}
//
//		}
//
//	 return rows;
//	}


}
