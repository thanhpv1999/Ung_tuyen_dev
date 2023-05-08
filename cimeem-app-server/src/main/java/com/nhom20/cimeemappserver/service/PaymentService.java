package com.nhom20.cimeemappserver.service;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaymentService {

	private static final String CLIENT_ID = "AeVRC0t66TX-4nCEFR4h9fowv74DPF2bcWudLXhT0QPVHwGcnD8m0IRx6KYOEdtPCB1MRhYOTtA2XCrU";
	private static final String CLIENT_SECRET = "EPN0UjQNEQT7WdvPhZeVl8pgjDNfUesaNLkXfuH38OTeWpMzKF56E3WJUiRodtWx8m4pFDqiR-wCMtHA";
	private static final String MODE = "sandbox";

	public String authorizePayment(com.nhom20.cimeemappserver.entity.Payment orderDetail) throws PayPalRESTException {
		Payer payer = getPayerInformation();
		RedirectUrls redirectUrls=getRedirectURLs();
		
		List<Transaction> listTransactions=getTransactionInformation(orderDetail);
		Payment requestPayment= new Payment();
		requestPayment.setTransactions(listTransactions)
						.setRedirectUrls(redirectUrls)
						.setPayer(payer)
						.setIntent("authorize");
		APIContext apiContext=new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		Payment approvedPayment= requestPayment.create(apiContext);
		
		System.out.println(approvedPayment);
		
		return getApprovalLink(approvedPayment);
	}

	private String getApprovalLink(Payment apprPayment) {
		List<Links> links= apprPayment.getLinks();
		String approlink=null;
		for (Links link : links) {
			if (link.getRel().equalsIgnoreCase("approval_url")) {
				approlink=link.getHref();
			}
		}
		return approlink;
	}
	private List<Transaction> getTransactionInformation(com.nhom20.cimeemappserver.entity.Payment orderDetail) {
		
		
		ItemList itemList= new ItemList();
		List<Item> items=new ArrayList<Item>();
		List<Transaction> listTransactions= new ArrayList<Transaction>();
		Transaction transaction=new Transaction();
		Amount amount=new Amount();
		Item item=new Item();
		Details details=new Details();
		
			details.setSubtotal(orderDetail.getTotal());
			
			amount.setCurrency("USD");
			amount.setTotal(orderDetail.getTotal());
			
			transaction.setDescription("Demo");
			String price=String.valueOf(orderDetail.getTotal());
			String amount_pr=String.valueOf(1);
		
			item.setCurrency("USD")
				.setName("Demo")
				.setPrice(price)
				.setTax(orderDetail.getTotal())
				.setQuantity(amount_pr);
			
			items.add(item);
			itemList.setItems(items);
			amount.setDetails(details);
			transaction.setAmount(amount);
			transaction.setItemList(itemList);
			listTransactions.add(transaction);
	
		
		return listTransactions;
	}
	private RedirectUrls getRedirectURLs() {
		RedirectUrls redirectUrls=new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:8080/cancel.html");
		redirectUrls.setReturnUrl("http://localhost:8080/review_payment");
		return redirectUrls;
	}

	private Payer getPayerInformation() {
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		PayerInfo payerInfo = new PayerInfo();
//		payerInfo.setFirstName("Tran").setLastName("Thu").setEmail("porkoe3443334@gmail.com");
		payer.setPayerInfo(payerInfo);
		return payer;
	}
	public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
	    APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
	    return Payment.get(apiContext, paymentId);
	}
	public Payment executePayment(String paymentId, String payerId)
	        throws PayPalRESTException {
	    PaymentExecution paymentExecution = new PaymentExecution();
	    paymentExecution.setPayerId(payerId);
	 
	    Payment payment = new Payment().setId(paymentId);
	 
	    APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
	 
	    return payment.execute(apiContext, paymentExecution);
	}
}
