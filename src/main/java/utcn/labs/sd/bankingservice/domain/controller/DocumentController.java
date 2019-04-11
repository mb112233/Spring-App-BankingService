package utcn.labs.sd.bankingservice.domain.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import utcn.labs.sd.bankingservice.core.configuration.SwaggerTags;
import utcn.labs.sd.bankingservice.domain.dto.UserDTO;
import utcn.labs.sd.bankingservice.domain.service.ActivityReportService;
import utcn.labs.sd.bankingservice.domain.service.ClientService;
import utcn.labs.sd.bankingservice.domain.service.FactoryCreator;
import utcn.labs.sd.bankingservice.domain.service.PDF;
import utcn.labs.sd.bankingservice.domain.service.UserService;

@Api(tags = { SwaggerTags.BANKING_SERVICE_TAG })
@RestController
@RequestMapping("bank/admin/download")
@CrossOrigin
public class DocumentController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "pdf", tags = SwaggerTags.EMPLOYEE_TAG)
	@GetMapping(value = "/{userId}/pdf", produces = "application/pdf")
	public ResponseEntity<?> generatePDF(@PathVariable("userId") int userId) throws Exception {

		List<String> reportList = userService.convertListToString(userId);
		
		System.out.println(reportList.toString());

		ActivityReportService pdf = FactoryCreator.factoryMethod("application/pdf");

		return new ResponseEntity<>(pdf.generate(reportList), HttpStatus.OK);
	}

	@ApiOperation(value = "csv", tags = SwaggerTags.EMPLOYEE_TAG)
	@GetMapping(value = "/{userId}/csv", produces = "text/csv")
	public ResponseEntity<?> generateCSV(@PathVariable("userId") int userId) throws Exception {

		
		List<String> reportList = userService.convertListToString(userId);

		ActivityReportService csv = FactoryCreator.factoryMethod("text/csv");

		return new ResponseEntity<>(csv.generate(reportList), HttpStatus.OK);
	}
}
