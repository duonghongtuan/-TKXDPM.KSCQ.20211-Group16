package api.bank;

import com.ecb.bean.CreditCard;

public interface IBankApi {
	public double plusAmount(String cardId, double money) ;
	public CreditCard getCard(String cardId) ;
	public double minusAmount(String cardId, double money);
	
}
