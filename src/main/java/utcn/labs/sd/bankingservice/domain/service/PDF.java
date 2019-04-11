package utcn.labs.sd.bankingservice.domain.service;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDF implements ActivityReportService {

	@Override
	public byte[] generate(List<String> reportList) {
		// TODO Auto-generated method stub

		ByteArrayOutputStream outs = new ByteArrayOutputStream();
		Document document = new Document();
        try {
			PdfWriter.getInstance(document, outs);
		} catch (com.itextpdf.text.DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        document.open();
        document.addTitle("Employee Activity Report");
       
        for (String i:reportList) {
        	Paragraph paragraph = new Paragraph(i);
        	paragraph.setAlignment(Element.ALIGN_CENTER);
        	try {
				document.add(paragraph);
			} catch (com.itextpdf.text.DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        document.close();
		return outs.toByteArray();
	}

	

}
