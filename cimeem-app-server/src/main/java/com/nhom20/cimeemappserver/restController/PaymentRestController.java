package com.nhom20.cimeemappserver.restController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nhom20.cimeemappserver.entity.Config;
import com.nhom20.cimeemappserver.entity.OrderDetail;
import com.nhom20.cimeemappserver.entity.OrderService;
import com.nhom20.cimeemappserver.entity.Payment;
import com.nhom20.cimeemappserver.entity.Product;
import com.nhom20.cimeemappserver.entity.Transaction;
import com.nhom20.cimeemappserver.entity.Users;
import com.nhom20.cimeemappserver.service.EPaymentDetailService;
import com.nhom20.cimeemappserver.service.EPaymentService;
import com.nhom20.cimeemappserver.service.OrdersDetailService;
import com.nhom20.cimeemappserver.service.PaymentService;
import com.nhom20.cimeemappserver.service.UserService;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.base.rest.PayPalRESTException;

import groovyjarjarpicocli.CommandLine.Parameters;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PaymentRestController {
	@Autowired
	private EPaymentService ePaymentService;
	@Autowired
	private UserService userService;
	@PostMapping("/vnpay_payment/{total}")
	public void vnpayPayment(HttpServletResponse resp, HttpServletRequest req, @PathVariable double total)
			throws PayPalRESTException, IOException, ServletException {
		int value = (int) total;
		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
		// String vnp_Command = "genqr";
		String vnp_OrderInfo = "mua do ty hoy";
		String orderType = "589256";
		String vnp_TxnRef = Config.getRandomNumber(8);
		int mcId = Integer.parseInt(Config.getRandomNumber(8));
		int amountmc = value;
		String vnp_IpAddr = Config.getIpAddress(req);
		String vnp_TmnCode = Config.vnp_TmnCode;
//        
		int amount = value * 100;
		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");
		String bank_code = req.getParameter("bankcode");
		if (bank_code != null && !bank_code.isEmpty()) {
			vnp_Params.put("vnp_BankCode", bank_code);
		}
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
		vnp_Params.put("vnp_OrderType", orderType);

		String locate = "vn";
		if (locate != null && !locate.isEmpty()) {
			vnp_Params.put("vnp_Locale", locate);
		} else {
			vnp_Params.put("vnp_Locale", "vn");
		}
		vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

		Date dt = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(dt);
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		Calendar cldvnp_ExpireDate = Calendar.getInstance();
		cldvnp_ExpireDate.add(Calendar.SECOND, 30);
		Date vnp_ExpireDateD = cldvnp_ExpireDate.getTime();
		String vnp_ExpireDate = formatter.format(vnp_ExpireDateD);

		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				// hashData.append(fieldValue); //sử dụng và 2.0.0 và 2.0.1 checksum sha256
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString())); // sử dụng v2.1.0
																										// check sum
																										// sha512
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		// String vnp_SecureHash = Config.Sha256(Config.vnp_HashSecret +
		// hashData.toString());
		String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
		com.google.gson.JsonObject job = new JsonObject();
		job.addProperty("code", "00");
		job.addProperty("message", "success");
		job.addProperty("data", paymentUrl);
		Gson gson = new Gson();
		resp.getWriter().write(gson.toJson(job));
	}

	@PostMapping("/order/{total}")
	public void order(HttpServletResponse response, HttpServletRequest request, @Parameters String paypal,
			@RequestBody Users user, @PathVariable double total)
			throws PayPalRESTException, IOException, ServletException {
		//////////

		///// paypal
		Payment payment = new Payment();
		if (paypal != null) {

			payment.setTotal(total);
			payment.setPayDate(new Date());
			payment.setUsers(userService.getUsersByGmail(user.getEmail()));

			ePaymentService.addPayment(payment);
			PaymentService paymentService = new PaymentService();
			String approvalLink;

			approvalLink = paymentService.authorizePayment(payment);
			com.google.gson.JsonObject job = new JsonObject();
			job.addProperty("code", "00");
			job.addProperty("message", "success");
			job.addProperty("data", approvalLink);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(job));

		}

		//////
//		OrderService order_add = ordersService.findOrderId(order.getOrderId());
//
//		Map<Product, Integer> map = new HashMap<>();
//		for (Product product : list) {
//			map.put(product, map.getOrDefault(product, 0) + product.getAmount());
//		}
//		map.forEach((key, value) -> {
//			Product pro_add = bookService.listBookById(key.getProductId());
//			OrderDetail orderDetail = new OrderDetail(pro_add, order_add, value, key.getAmount(), new Date(), null, 0,
//					bookPriceService.priceByBookId(key.getBookId()), status);
//			ordersDetailService.addOrdersDetail(orderDetail);
//			com.webshop.shopbook.entity.Payment payment_add = ePaymentService.findPaymentId(payment.getPaymentId());
//			Transaction paymentDetail = new Transaction(pro_add, payment_add, 0d, false, _total, "Book Shop", "");
//			ePaymentDetailService.addPayment(paymentDetail);
//		});
	}
	@GetMapping("/review_payment")
	public void getReviewPayment(HttpServletResponse response, HttpServletRequest request,
			@Parameters String paypal) throws PayPalRESTException, IOException, ServletException {
		HttpSession session01 = request.getSession();
		String paymentId = request.getParameter("paymentId");
		com.google.gson.JsonObject job = new JsonObject();
		job.addProperty("code", "00");
		
		Gson gson = new Gson();
		
		String payerId = request.getParameter("PayerID");

		try {
			PaymentService paymentServices = new PaymentService();
			com.paypal.api.payments.Payment payment = paymentServices.getPaymentDetails(paymentId);

			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			com.paypal.api.payments.Transaction transaction = payment.getTransactions().get(0);
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();

			request.setAttribute("payer", payerInfo);
			request.setAttribute("transaction", transaction);
			request.setAttribute("shippingAddress", shippingAddress);
			session01.setAttribute("paymentId", paymentId);
			session01.setAttribute("PayerID", payerId);
			String url = "customer/review";
			job.addProperty("data", url);
			job.addProperty("message", "success");
			response.getWriter().write(gson.toJson(job));
		} catch (PayPalRESTException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
			ex.printStackTrace();
			job.addProperty("message", "no- success");
			job.addProperty("data", ex.getMessage());
			response.getWriter().write(gson.toJson(job));
//			modelAndView.setViewName("customer/error");
		}
	}

	@PostMapping("/execute_payment")
	public void getExecutePayment(HttpServletResponse response, HttpServletRequest request,
			@Parameters String paypal) throws PayPalRESTException, IOException, ServletException {
		HttpSession session = request.getSession();
		String paymentId = (String) session.getAttribute("paymentId");
		String payerId = (String) session.getAttribute("PayerID");
		com.google.gson.JsonObject job = new JsonObject();
		job.addProperty("code", "00");
		Gson gson = new Gson();
		
		System.out.println(paymentId);

		try {
			PaymentService paymentServices = new PaymentService();
			com.paypal.api.payments.Payment payment = paymentServices.executePayment(paymentId, payerId);

			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			com.paypal.api.payments.Transaction transaction = payment.getTransactions().get(0);

			request.setAttribute("payer", payerInfo);
			request.setAttribute("transaction", transaction);
//			modelAndView.setViewName("customer/receipt");
			job.addProperty("message", "success");
			job.addProperty("data", "customer/receipt");
			response.getWriter().write(gson.toJson(job));
		} catch (PayPalRESTException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
			ex.printStackTrace();
			job.addProperty("message", "no- success");
			job.addProperty("data", "customer/error");
			response.getWriter().write(gson.toJson(job));
//			modelAndView.setViewName("customer/error");
		}
	}
}
