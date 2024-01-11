package service.core;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface for defining the behaviours of the broker service
 * @author Rem
 *
 */
public interface BrokerService extends Remote{
	public List<Quotation> getQuotations(ClientInfo info)throws RemoteException;
	public void registerService(String name, QuotationService service) throws AlreadyBoundException, RemoteException;
}
