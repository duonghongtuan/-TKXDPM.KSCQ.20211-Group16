package api.bank;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ecb.bean.CreditCard;

public class BankApiImpl implements IBankApi{
	
	public static final String PATH = "http://localhost:8080/";
	
	private Client client;
	
	public BankApiImpl() {
		client = ClientBuilder.newClient();
	}
	

	public double plusAmount(String cardId, double money) {
		 WebTarget webTarget = client.target(PATH).path("cards").path("plus").path(cardId).path(String.valueOf(money));
			
			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();
			
			double res = response.readEntity(Double.class);
			return res;
	}
	
	
	 public CreditCard getCard(String cardId) {
	        WebTarget webTarget = client.target(PATH).path("cards").path(cardId);
			
			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();
			
			CreditCard res = response.readEntity(CreditCard.class);
			return res;
	}
	 
		public double minusAmount(String cardId, double money) {
			 WebTarget webTarget = client.target(PATH).path("cards").path("minus").path(cardId).path(String.valueOf(money));
				
				Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
				Response response = invocationBuilder.get();
				
				double res = response.readEntity(Double.class);
//				System.out.println(res);
				return res;
		}
	 
 }
