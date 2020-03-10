package com.dims.finReportExport.converter;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.File;
import java.io.IOError;
import java.io.IOException;

public class PdfConverter {

    public void generateHTMLOFromPdf(String fileName) throws IOException{
        PDDocument pdf = PDDocument.load(new File(fileName));

    }


    public String generateTxtFromPDF(String fileName) throws IOException{
        File f = new File(fileName);
        PDFParser parser = new PDFParser(new RandomAccessFile(f, "r"));
        parser.parse();

        COSDocument cosDoc = parser.getDocument();
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setSortByPosition(true);
        PDDocument pdfDoc = new PDDocument(cosDoc);

        String parsedText = stripper.getText(pdfDoc);
        System.out.printf(parsedText);

        return parsedText;
    }
}
