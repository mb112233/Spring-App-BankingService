package utcn.labs.sd.bankingservice.domain.service;

public class FactoryCreator {
	
		private FactoryCreator() {
			
		}

		public static ActivityReportService factoryMethod(String mimeType) {
			if (mimeType.equals("application/pdf")) {
				return new PDF();
			}
			else if (mimeType.equals("text/csv")) {
				return new CSV();
			}
			else return null;
		}
}
