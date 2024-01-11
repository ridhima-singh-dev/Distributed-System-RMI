package dodgygeezers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.junit.BeforeClass;
import org.junit.Test;

import service.core.ClientInfo;
import service.core.Constants;
import service.core.Quotation;
import service.core.QuotationService;
import service.dodgygeezers.DGQService;

public class DodgygeezersUnitTest {

	private static Registry registry;

	@BeforeClass
	public static void setup() {
		QuotationService dgqService = new DGQService();
		try {
			registry = LocateRegistry.createRegistry(1099);
			QuotationService quotationService = (QuotationService) UnicastRemoteObject.exportObject(dgqService, 0);
			registry.bind(Constants.DODGY_GEEZERS_SERVICE, quotationService);
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}

	@Test
	public void connectionTest() throws Exception {
		QuotationService service = (QuotationService) registry.lookup(Constants.DODGY_GEEZERS_SERVICE);
		assertNotNull(service);
	}

	@Test
	public void testGenerateQuotation() throws Exception {
		// Create a sample client
		ClientInfo client = new ClientInfo("Hannah Montana", ClientInfo.FEMALE, 21, 1.78, 65, false, false);

		// Obtain the QuotationService from the registry
		QuotationService service = (QuotationService) registry.lookup(Constants.DODGY_GEEZERS_SERVICE);

		// Ensure that the generated result is an instance of Quotation
		assertTrue(service.generateQuotation(client) instanceof Quotation);
	}
}
