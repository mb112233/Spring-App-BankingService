package utcn.labs.sd.bankingservice.domain.service;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class CSV implements ActivityReportService {


	@Override
	public byte[] generate(List<String> list)   {
		// TODO Auto-generated method stub

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		Writer writer = new OutputStreamWriter(os);//= Files.newBufferedWriter(Paths.get(fileName));

		CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

		
		for (String s:list) {
			csvWriter.writeNext(new String[] {s});
		}
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return os.toByteArray();
	}

	
}
