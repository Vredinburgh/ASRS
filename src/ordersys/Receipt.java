/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import ordersys.xmlReader.Product;

/**
 *
 * @author gerri
 */
public class Receipt {

    private ArrayList<Product> products;

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;

    public Receipt(ArrayList<Product> products) {
        this.products = products;
    }

    public void createPDF(String pdfFilename) {

        Document doc = new Document();
        PdfWriter docWriter = null;
        initializeFonts();

        try {
            String path = "C:/Users/gerri/Desktop/" + pdfFilename;
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.addAuthor("Roboboyz");
            doc.addCreationDate();
            doc.addProducer();
            doc.addTitle("Pakbon");
            doc.setPageSize(PageSize.LETTER);

            doc.open();
            PdfContentByte cb = docWriter.getDirectContent();

            boolean beginPage = true;
            int y = 0;

            for (int i = 0; i < products.size(); i++) {
                if (beginPage) {
                    beginPage = false;
                    generateLayout(doc, cb);
                    generateHeader(doc, cb);
                    y = 615;
                }
                generateDetail(doc, cb, i, y);
                y = y - 15;
                if (y < 50) {
                    printPageNumber(cb);
                    doc.newPage();
                    beginPage = true;
                }
            }
            printPageNumber(cb);

        } catch (DocumentException dex) {
            dex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (docWriter != null) {
                docWriter.close();
            }
        }
    }

    private void generateLayout(Document doc, PdfContentByte cb) {

        try {

            cb.setLineWidth(1f);

            // Invoice Header box layout
            cb.rectangle(420, 700, 150, 60);
            cb.moveTo(420, 720);
            cb.lineTo(570, 720);
            cb.moveTo(420, 740);
            cb.lineTo(570, 740);
            cb.moveTo(480, 700);
            cb.lineTo(480, 760);
            cb.stroke();

            // Invoice Header box Text Headings 
            createHeadings(cb, 422, 743, "Klant naam");
            createHeadings(cb, 422, 723, "Pakbon Nr.");
            createHeadings(cb, 422, 703, "Pakbon Datum");

            // Invoice Detail box layout 
            cb.rectangle(20, 50, 550, 600);
            cb.moveTo(20, 630);
            cb.lineTo(570, 630);
            cb.moveTo(50, 50);
            cb.lineTo(50, 650);
            cb.moveTo(150, 50);
            cb.lineTo(150, 650);
            cb.moveTo(430, 50);
            cb.lineTo(430, 650);
            cb.moveTo(500, 50);
            cb.lineTo(500, 650);
            cb.stroke();

            // Invoice Detail box Text Headings 
            createHeadings(cb, 22, 633, "Aantal");
            createHeadings(cb, 52, 633, "Artikel nummer");
            createHeadings(cb, 152, 633, "Artikel omschrijving");
            createHeadings(cb, 432, 633, "Prijs");
            createHeadings(cb, 502, 633, "Prijs ex btw");

            //add the images
            Image companyLogo = Image.getInstance("images/logo.jpg");
            companyLogo.setAbsolutePosition(25, 700);
            companyLogo.scalePercent(25);
            doc.add(companyLogo);

        } catch (DocumentException dex) {
            dex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateHeader(Document doc, PdfContentByte cb) {

        try {

            createHeadings(cb, 200, 750, "Roboboyz: Reloaded");
            createHeadings(cb, 200, 735, "Campus 2");
            createHeadings(cb, 200, 720, "Zwolle");
            createHeadings(cb, 200, 705, "Nederland");

            createHeadings(cb, 482, 743, "1234");
            createHeadings(cb, 482, 723, "23");
            createHeadings(cb, 482, 703, "17-05-2017");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateDetail(Document doc, PdfContentByte cb, int index, int y) {
        DecimalFormat df = new DecimalFormat("0.00");

        try {
            createContent(cb, 48, y, String.valueOf(1), PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 52, y, String.valueOf(products.get(index).getProductNr()), PdfContentByte.ALIGN_LEFT);
            createContent(cb, 152, y, products.get(index).getName(), PdfContentByte.ALIGN_LEFT);

            createContent(cb, 498, y, String.valueOf(df.format(products.get(index).getPrice())), PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 568, y, String.valueOf(df.format(products.get(index).getPrice() * 0.79)), PdfContentByte.ALIGN_RIGHT);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void createHeadings(PdfContentByte cb, float x, float y, String text) {

        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.setTextMatrix(x, y);
        cb.showText(text.trim());
        cb.endText();

    }

    private void printPageNumber(PdfContentByte cb) {

        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "" + (pageNumber + 1), 570, 25, 0);
        cb.endText();

        pageNumber++;

    }

    private void createContent(PdfContentByte cb, float x, float y, String text, int align) {

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();

    }

    private void initializeFonts() {

        try {
            bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
