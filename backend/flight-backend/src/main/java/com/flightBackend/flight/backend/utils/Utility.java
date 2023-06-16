package com.flightBackend.flight.backend.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.sql.Date;

public class Utility {
    public static void createPdf(String flightId, Date departureDate, String departureTime, String source,
                                 String destination, Double totalFare, String passengerName, ByteArrayOutputStream byteArrayOutputStream) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            // Add ticket details to the PDF
            Paragraph paragraph = new Paragraph();
            paragraph.add("Flight Id: " + flightId + "\n");
            paragraph.add("Departure Date: " + departureDate + "\n");
            paragraph.add("Departure Time: " + departureTime + "\n");
            paragraph.add("Source: " + source + "\n");
            paragraph.add("Departure Date: " + departureDate + "\n");
            paragraph.add("Destination: " + destination + "\n");
            paragraph.add("total Fare: " + totalFare + "\n");
            paragraph.add("Passenger Name: " + passengerName + "\n");
            paragraph.setAlignment(Element.ALIGN_CENTER);

            document.add(paragraph);
            document.close();
            System.out.println("PDF generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}