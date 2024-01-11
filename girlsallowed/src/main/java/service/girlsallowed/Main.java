package service.girlsallowed;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import service.core.BrokerService;
import service.core.Constants;
import service.core.QuotationService;

public class Main {
	public static void main(String[] args) {
		QuotationService gaqService = new GAQService();
		try {
			// Connect to the RMI Registry - creating the registry will be the
			// responsibility of the broker.

			Registry registry = null;
			if (args.length == 0) {
				registry = LocateRegistry.createRegistry(1099);
			} else {
				registry = LocateRegistry.getRegistry(args[0], 1099);
			}

			// Create the Remote Object
			QuotationService quotationService = (QuotationService) UnicastRemoteObject.exportObject(gaqService, 0);
			
			BrokerService brokerService = (BrokerService) registry.lookup(Constants.BROKER_SERVICE);

			// Register the object with the RMI Registry
			//registry.bind(Constants.GIRLS_ALLOWED_SERVICE, quotationService);
			
			brokerService.registerService(Constants.GIRLS_ALLOWED_SERVICE, quotationService);

			System.out.println("STOPPING SERVER SHUTDOWN");
			while (true) {
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}
}