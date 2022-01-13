package com.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.bean.CreditCard;
import com.db.IMediaDatabase;
import com.db.JsonMediaDatabase;

@Path("/cards")
public class CardService {

	private IMediaDatabase cardDatabase;

	public CardService() {
		cardDatabase = JsonMediaDatabase.singleton();
	}

	@GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public CreditCard getAllCard(String cardId) {
	     CreditCard res = cardDatabase.searchCard("12345678");
	     return res;
	 }
	 
	@GET
	@Path("/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public CreditCard getCard(@PathParam("id") String cardId) {
	     CreditCard res = cardDatabase.searchCard(cardId);
	     return res;
	 }
	 
	@GET
	@Path("/plus/{id}/{money}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public double plusAmount(@PathParam("id") String cardId,@PathParam("money") double money) {
	     return cardDatabase.plusAmount(cardId, money);
	 }
	
	@GET
	@Path("/minus/{id}/{money}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public double minusAmount(@PathParam("id") String cardId,@PathParam("money") double money) {
	     return cardDatabase.minusAmount(cardId, money);
	 }
}