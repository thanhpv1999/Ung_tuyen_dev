package com.nhom20.cimeemappserver.restController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nhom20.cimeemappserver.authen.UserPrincipal;
import com.nhom20.cimeemappserver.entity.Address;
import com.nhom20.cimeemappserver.entity.Barcode;
import com.nhom20.cimeemappserver.entity.CustomerPassword;
import com.nhom20.cimeemappserver.entity.ForgotPassword;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Member;
import com.nhom20.cimeemappserver.entity.MemberType;
import com.nhom20.cimeemappserver.entity.MemberUser;
import com.nhom20.cimeemappserver.entity.Roles;
import com.nhom20.cimeemappserver.entity.Token;
import com.nhom20.cimeemappserver.entity.Users;
import com.nhom20.cimeemappserver.generator.BarcodeGenerator;
import com.nhom20.cimeemappserver.service.AddressService;
import com.nhom20.cimeemappserver.service.AmazonUploadService;
import com.nhom20.cimeemappserver.service.CustomerPasswordService;
import com.nhom20.cimeemappserver.service.ForgotPasswordService;
import com.nhom20.cimeemappserver.service.LocationService;
import com.nhom20.cimeemappserver.service.MemberService;
import com.nhom20.cimeemappserver.service.MemberUserService;
import com.nhom20.cimeemappserver.service.TokenService;
import com.nhom20.cimeemappserver.service.UserService;
import com.nhom20.cimeemappserver.util.JwtUtil;
import com.paypal.base.rest.JSONFormatter;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginRestController {
	@Autowired
	private UserService userService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	@Autowired
	private AmazonUploadService amazonUploadService;
	@Autowired
	private CustomerPasswordService customerPasswordService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private MemberUserService memberUserService;
	@Autowired
	private MemberService memberService;

	@PostMapping("/login")
	public void login(@RequestBody Users user, @Param(value = "pass") String pass,HttpServletResponse response) throws IOException {
		String paymentUrl = "dang nhap thanh cong";
		com.google.gson.JsonObject job = new JsonObject();
		job.addProperty("message", paymentUrl);
		
		Gson gson = new Gson();
		Users userPrincipal = userService.getUsersByGmail(user.getEmail());
		if (null == user || !new BCryptPasswordEncoder().matches(pass, customerPasswordService.getPassByUserId(userPrincipal.getUserId()).getPassword())) {
			 paymentUrl = "mat khau khong dung";
			job.addProperty("message", paymentUrl);
			response.getWriter().write(gson.toJson(job));
		}else {
			 UserPrincipal userPrincipal1 =
		                userService.findByUsername(user.getEmail());
			Token token = new Token();
			token.setToken(jwtUtil.generateToken(userPrincipal1));
	//
			token.setTokenExpDate(jwtUtil.generateExpirationDate());
			token.setCreatedBy(userPrincipal.getUserId());
			tokenService.createToken(token);
			job.addProperty("token", token.getToken());
				response.getWriter().write(gson.toJson(job));
		}
	
	}

	@GetMapping("/hello")
	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public ResponseEntity hello() {
		return ResponseEntity.ok("hello");
	}

	@GetMapping("/user-by-email/{email}")
	public Users showUser(@PathVariable("email") String bookId) {
		Users books = userService.getUsersByGmail(bookId);
//		System.out.println(books.getAddresss().getPhone());
		return books;
	}

	@PostMapping("/register")
	public void register(@RequestBody Users theUsers, HttpServletRequest request, HttpServletResponse response,
			@Param(value = "password") String password, @Param(value = "confirmpassword") String confirmpassword)
			throws IOException {
		String paymentUrl = "dang ky thanh cong";
		com.google.gson.JsonObject job = new JsonObject();
		job.addProperty("message", "success");
		job.addProperty("data", paymentUrl);
		Gson gson = new Gson();
		String errMsg = "";
		HttpSession session = request.getSession();
		if (password.equals(confirmpassword)) {
			try {
				theUsers.setAddresss(addressService.getPassById(theUsers.getAddresss().getAddressId()));
				userService.addNewUser(theUsers);
				/////
				Member member=new Member();
				member.setType(MemberType.Member);
				member.setExpirationDate(new Date());
				Barcode barcode=new Barcode();
				barcode.setExpirationDate(new Date());
				member.setBarcode(barcode);
				memberService.addMember(member);
				MemberUser memberUser=new MemberUser();
				memberUser.setMember(memberService.getMemberById(member.getGroupId()));
				memberUser.setUser(userService.getUsersById(theUsers.getUserId()));
				memberUser.setParticipationDate(new Date());
				memberUserService.addMember(memberUser);
				/////
				CustomerPassword customerPass = new CustomerPassword();
				customerPass.setPassword(password);
				customerPass.setCreatedAt(new Date());
//				customerPass.setUsers(theUsers);
				customerPass.setUsers(userService.getUsersById(theUsers.getUserId()));
				///////////////

				customerPasswordService.addCodeEmail(customerPass);
				//////////
				session.setAttribute("acc", theUsers);

				/////////////
				final String usernameMail = "animewebvuighe@gmail.com";
				final String passwordMail = "jbivwdwhhlbbegto";
				Properties properties = new Properties();
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				Session session_mail = Session.getInstance(properties, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						// TODO Auto-generated method stub
						return new PasswordAuthentication(usernameMail, passwordMail);
					}
				});

				ForgotPassword forgot = new ForgotPassword();
				forgotPasswordService.addCodeEmail(forgot);
				// xac nhan password qua mail
				String emailTo = theUsers.getEmail();
				String emailSubject = "Congratulations on becoming a member !";
				String content = request.getParameter("exampleFirstName") + " "
						+ request.getParameter("exampleLastName");
				String emailContent = "Thanks you " + content + ", Congratulations on becoming a member of Cimeem";
				String emailContent01 = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
						+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\r\n"
						+ "<head>\r\n" + "<!--[if gte mso 9]>\r\n" + "<xml>\r\n" + "  <o:OfficeDocumentSettings>\r\n"
						+ "    <o:AllowPNG/>\r\n" + "    <o:PixelsPerInch>96</o:PixelsPerInch>\r\n"
						+ "  </o:OfficeDocumentSettings>\r\n" + "</xml>\r\n" + "<![endif]-->\r\n"
						+ "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n"
						+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
						+ "  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\r\n"
						+ "  <title></title>\r\n" + "  \r\n" + "    <style type=\"text/css\">\r\n"
						+ "      table, td { color: #000000; } a { color: #0000ee; text-decoration: underline; } @media (max-width: 480px) { #u_content_image_2 .v-src-width { width: auto !important; } #u_content_image_2 .v-src-max-width { max-width: 60% !important; } #u_content_text_1 .v-container-padding-padding { padding: 30px 30px 30px 20px !important; } #u_content_button_1 .v-container-padding-padding { padding: 10px 20px !important; } #u_content_button_1 .v-size-width { width: 100% !important; } #u_content_button_1 .v-text-align { text-align: left !important; } #u_content_button_1 .v-padding { padding: 15px 40px !important; } #u_content_text_3 .v-container-padding-padding { padding: 30px 30px 80px 20px !important; } #u_content_text_5 .v-text-align { text-align: center !important; } }\r\n"
						+ "@media only screen and (min-width: 570px) {\r\n" + "  .u-row {\r\n"
						+ "    width: 550px !important;\r\n" + "  }\r\n" + "  .u-row .u-col {\r\n"
						+ "    vertical-align: top;\r\n" + "  }\r\n" + "\r\n" + "  .u-row .u-col-50 {\r\n"
						+ "    width: 275px !important;\r\n" + "  }\r\n" + "\r\n" + "  .u-row .u-col-100 {\r\n"
						+ "    width: 550px !important;\r\n" + "  }\r\n" + "\r\n" + "}\r\n" + "\r\n"
						+ "@media (max-width: 570px) {\r\n" + "  .u-row-container {\r\n"
						+ "    max-width: 100% !important;\r\n" + "    padding-left: 0px !important;\r\n"
						+ "    padding-right: 0px !important;\r\n" + "  }\r\n" + "  .u-row .u-col {\r\n"
						+ "    min-width: 320px !important;\r\n" + "    max-width: 100% !important;\r\n"
						+ "    display: block !important;\r\n" + "  }\r\n" + "  .u-row {\r\n"
						+ "    width: calc(100% - 40px) !important;\r\n" + "  }\r\n" + "  .u-col {\r\n"
						+ "    width: 100% !important;\r\n" + "  }\r\n" + "  .u-col > div {\r\n"
						+ "    margin: 0 auto;\r\n" + "  }\r\n" + "}\r\n" + "body {\r\n" + "  margin: 0;\r\n"
						+ "  padding: 0;\r\n" + "}\r\n" + "\r\n" + "table,\r\n" + "tr,\r\n" + "td {\r\n"
						+ "  vertical-align: top;\r\n" + "  border-collapse: collapse;\r\n" + "}\r\n" + "\r\n"
						+ "p {\r\n" + "  margin: 0;\r\n" + "}\r\n" + "\r\n" + ".ie-container table,\r\n"
						+ ".mso-container table {\r\n" + "  table-layout: fixed;\r\n" + "}\r\n" + "\r\n" + "* {\r\n"
						+ "  line-height: inherit;\r\n" + "}\r\n" + "\r\n" + "a[x-apple-data-detectors='true'] {\r\n"
						+ "  color: inherit !important;\r\n" + "  text-decoration: none !important;\r\n" + "}\r\n"
						+ "\r\n" + "</style>\r\n" + "  \r\n" + "  \r\n" + "\r\n"
						+ "<!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Crimson+Text:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\r\n"
						+ "\r\n" + "</head>\r\n" + "\r\n"
						+ "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #fbeeb8;color: #000000\">\r\n"
						+ "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\r\n"
						+ "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\r\n"
						+ "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #fbeeb8;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
						+ "  <tbody>\r\n" + "  <tr style=\"vertical-align: top\">\r\n"
						+ "    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n"
						+ "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #fbeeb8;\"><![endif]-->\r\n"
						+ "    \r\n" + "\r\n"
						+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: #ffffff\">\r\n"
						+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\r\n"
						+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n"
						+ "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #ffffff;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:550px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\r\n"
						+ "      \r\n"
						+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"550\" style=\"width: 550px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\r\n"
						+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n"
						+ "  <div style=\"width: 100% !important;\">\r\n"
						+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n"
						+ "  \r\n" + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + "  </div>\r\n"
						+ "</div>\r\n" + "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
						+ "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "    </div>\r\n"
						+ "  </div>\r\n" + "</div>\r\n" + "\r\n" + "\r\n" + "\r\n"
						+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
						+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\r\n"
						+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n"
						+ "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:550px;\"><tr style=\"background-color: transparent;\"><![endif]-->\r\n"
						+ "      \r\n"
						+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"550\" style=\"width: 550px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\r\n"
						+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n"
						+ "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
						+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\r\n"
						+ "  \r\n"
						+ "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
						+ "  <tbody>\r\n" + "    <tr>\r\n"
						+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:20px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
						+ "        \r\n"
						+ "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 0px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
						+ "    <tbody>\r\n" + "      <tr style=\"vertical-align: top\">\r\n"
						+ "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
						+ "          <span>&#160;</span>\r\n" + "        </td>\r\n" + "      </tr>\r\n"
						+ "    </tbody>\r\n" + "  </table>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n"
						+ "  </tbody>\r\n" + "</table>\r\n" + "\r\n"
						+ "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + "  </div>\r\n" + "</div>\r\n"
						+ "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
						+ "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "    </div>\r\n"
						+ "  </div>\r\n" + "</div>\r\n" + "\r\n" + "\r\n" + "\r\n"
						+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
						+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\r\n"
						+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n"
						+ "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:550px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\r\n"
						+ "      \r\n"
						+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"542\" style=\"width: 542px;padding: 0px;border-top: 4px solid #d9d8d8;border-left: 4px solid #d9d8d8;border-right: 4px solid #d9d8d8;border-bottom: 4px solid #d9d8d8;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\r\n"
						+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n"
						+ "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
						+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 4px solid #d9d8d8;border-left: 4px solid #d9d8d8;border-right: 4px solid #d9d8d8;border-bottom: 4px solid #d9d8d8;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\r\n"
						+ "  \r\n"
						+ "<table id=\"u_content_image_2\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
						+ "  <tbody>\r\n" + "    <tr>\r\n"
						+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
						+ "        \r\n" + "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n"
						+ "  <tr>\r\n"
						+ "    <td class=\"v-text-align\" style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\r\n"
						+ "      \r\n"
						+ "      <img align=\"center\" border=\"0\" src=\"https://i.pinimg.com/564x/c8/e4/7d/c8e47d9dc35e300778dc76ac00fbbfb2.jpg\" alt=\"Wrong Email\" title=\"Wrong Email\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 530px;\" width=\"530\" class=\"v-src-width v-src-max-width\"/>\r\n"
						+ "      \r\n" + "    </td>\r\n" + "  </tr>\r\n" + "</table>\r\n" + "\r\n" + "      </td>\r\n"
						+ "    </tr>\r\n" + "  </tbody>\r\n" + "</table>\r\n" + "\r\n"
						+ "<table id=\"u_content_text_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
						+ "  <tbody>\r\n" + "    <tr>\r\n"
						+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 30px 30px 40px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
						+ "        \r\n"
						+ "  <div class=\"v-text-align\" style=\"color: #333333; line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n"
						+ "    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-family: 'Crimson Text', serif; font-size: 14px; line-height: 19.6px;\"><strong><span style=\"font-size: 22px; line-height: 30.8px;\">💋 Are you ready to find love? Take a second to complete registration and verify your email.📩</span></strong></span></p>\r\n"
						+ "<p style=\"font-size: 14px; line-height: 140%;\">&nbsp;</p>\r\n"
						+ "<p style=\"font-size: 14px; line-height: 140%;\">&nbsp;</p>\r\n" + "  </div>\r\n" + "\r\n"
						+ "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n" + "</table>\r\n" + "\r\n"
						+ "<table id=\"u_content_button_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
						+ "  <tbody>\r\n" + "    <tr>\r\n"
						+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 40px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
						+ "        \r\n" + "<div class=\"v-text-align\" align=\"left\">\r\n"
						+ "  <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;font-family:arial,helvetica,sans-serif;\"><tr><td class=\"v-text-align\" style=\"font-family:arial,helvetica,sans-serif;\" align=\"left\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://localhost:8080/WebBanHangQuanAo/home\" style=\"height:47px; v-text-anchor:middle; width:456px;\" arcsize=\"6.5%\" strokecolor=\"#ced4d9\" strokeweight=\"3px\" fillcolor=\"#91a5e2\"><w:anchorlock/><center style=\"color:#000000;font-family:arial,helvetica,sans-serif;\"><![endif]-->\r\n"
						+ "    <a href=\"http://localhost:8085/updateactive/" + emailTo + "/" + forgot.getId() + ""
						+ "\" target=\"_blank\" class=\"v-size-width\" style=\"box-sizing: border-box;display: inline-block;font-family:arial,helvetica,sans-serif;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #000000; background-color: #91a5e2; border-radius: 3px;-webkit-border-radius: 3px; -moz-border-radius: 3px; width:100%; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;border-top-color: #ced4d9; border-top-style: solid; border-top-width: 3px; border-left-color: #ced4d9; border-left-style: solid; border-left-width: 3px; border-right-color: #ced4d9; border-right-style: solid; border-right-width: 3px; border-bottom-color: #ced4d9; border-bottom-style: solid; border-bottom-width: 3px;\">\r\n"
						+ "      <span class=\"v-padding\" style=\"display:block;padding:15px 40px;line-height:120%;\"><span style=\"font-size: 14px; line-height: 16.8px;\">\r\n"
						+ "Finish &nbsp; &nbsp;registration</span></span>\r\n"
						+ "    </a>\r\n" + "  <!--[if mso]></center></v:roundrect></td></tr></table><![endif]-->\r\n"
						+ "</div>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n"
						+ "</table>\r\n" + "\r\n"
						+ "<table id=\"u_content_text_3\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
						+ "  <tbody>\r\n" + "    <tr>\r\n"
						+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 30px 80px 40px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
						+ "        \r\n"
						+ "  <div class=\"v-text-align\" style=\"color: #333333; line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n"
						+ "    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; font-family: 'Crimson Text', serif;\">Feel free to reach out to us with any questions.</span></p>\r\n"
						+ "<p style=\"font-size: 14px; line-height: 140%;\">&nbsp;</p>\r\n"
						+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 22px; line-height: 30.8px;\"><strong><span style=\"line-height: 30.8px; font-family: 'Crimson Text', serif; font-size: 22px;\">Thanks.</span></strong></span><br /><span style=\"font-size: 18px; line-height: 25.2px; font-family: 'Crimson Text', serif;\">ANTi&nbsp; Customer Care Team</span></p>\r\n"
						+ "  </div>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n"
						+ "</table>\r\n" + "\r\n" + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n"
						+ "  </div>\r\n" + "</div>\r\n" + "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
						+ "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "    </div>\r\n"
						+ "  </div>\r\n" + "</div>\r\n" + "\r\n" + "\r\n" + "\r\n"
						+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
						+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\r\n"
						+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n"
						+ "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:550px;\"><tr style=\"background-color: transparent;\"><![endif]-->\r\n"
						+ "      \r\n"
						+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"550\" style=\"width: 550px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\r\n"
						+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n"
						+ "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
						+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\r\n"
						+ "  \r\n"
						+ "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
						+ "  <tbody>\r\n" + "    <tr>\r\n"
						+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
						+ "        \r\n" + "<div align=\"center\">\r\n"
						+ "  <div style=\"display: table; max-width:170px;\">\r\n"
						+ "  <!--[if (mso)|(IE)]><table width=\"170\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"border-collapse:collapse;\" align=\"center\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse; mso-table-lspace: 0pt;mso-table-rspace: 0pt; width:170px;\"><tr><![endif]-->\r\n"
						+ "  \r\n" + "    \r\n"
						+ "    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 25px;\" valign=\"top\"><![endif]-->\r\n"
						+ "    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 25px\">\r\n"
						+ "      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n"
						+ "        <a href=\"https://facebook.com/\" title=\"Facebook\" target=\"_blank\">\r\n"
						+ "          <img src=\"https://i.pinimg.com/564x/df/d9/f4/dfd9f4969e92f30198cbfd8f5ecc127f.jpg\" alt=\"Facebook\" title=\"Facebook\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\r\n"
						+ "        </a>\r\n" + "      </td></tr>\r\n" + "    </tbody></table>\r\n"
						+ "    <!--[if (mso)|(IE)]></td><![endif]-->\r\n" + "    \r\n"
						+ "    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 25px;\" valign=\"top\"><![endif]-->\r\n"
						+ "    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 25px\">\r\n"
						+ "      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n"
						+ "        <a href=\"https://twitter.com/\" title=\"Twitter\" target=\"_blank\">\r\n"
						+ "          <img src=\"https://i.pinimg.com/564x/a6/d5/55/a6d555266824307026e70706659ff785.jpg\" alt=\"Twitter\" title=\"Twitter\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\r\n"
						+ "        </a>\r\n" + "      </td></tr>\r\n" + "    </tbody></table>\r\n"
						+ "    <!--[if (mso)|(IE)]></td><![endif]-->\r\n" + "    \r\n"
						+ "    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 0px;\" valign=\"top\"><![endif]-->\r\n"
						+ "    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 0px\">\r\n"
						+ "      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n"
						+ "        <a href=\"https://linkedin.com/\" title=\"LinkedIn\" target=\"_blank\">\r\n"
						+ "          <img src=\"https://i.pinimg.com/564x/2b/e7/ce/2be7cee4fe404b8fa86d31d139fab757.jpg\" alt=\"LinkedIn\" title=\"LinkedIn\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\r\n"
						+ "        </a>\r\n" + "      </td></tr>\r\n" + "    </tbody></table>\r\n"
						+ "    <!--[if (mso)|(IE)]></td><![endif]-->\r\n" + "    \r\n" + "    \r\n"
						+ "    <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "  </div>\r\n"
						+ "</div>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n"
						+ "</table>\r\n" + "\r\n"
						+ "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
						+ "  <tbody>\r\n" + "    <tr>\r\n"
						+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 0px 21px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
						+ "        \r\n"
						+ "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 3px solid #000000;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
						+ "    <tbody>\r\n" + "      <tr style=\"vertical-align: top\">\r\n"
						+ "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
						+ "          <span>&#160;</span>\r\n" + "        </td>\r\n" + "      </tr>\r\n"
						+ "    </tbody>\r\n" + "  </table>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n"
						+ "  </tbody>\r\n" + "</table>\r\n" + "\r\n"
						+ "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + "  </div>\r\n" + "</div>\r\n"
						+ "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
						+ "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "    </div>\r\n"
						+ "  </div>\r\n" + "</div>\r\n" + "\r\n" + "\r\n" + "\r\n"
						+ "<div class=\"u-row-container\" style=\"padding: 0px 0px 11px;background-color: transparent\">\r\n"
						+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\r\n"
						+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n"
						+ "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px 0px 11px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:550px;\"><tr style=\"background-color: transparent;\"><![endif]-->\r\n"
						+ "      \r\n"
						+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"275\" style=\"width: 275px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\r\n"
						+ "<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 275px;display: table-cell;vertical-align: top;\">\r\n"
						+ "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
						+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\r\n"
						+ "  \r\n"
						+ "<table id=\"u_content_text_5\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
						+ "  <tbody>\r\n" + "    <tr>\r\n"
						+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
						+ "        \r\n"
						+ "  <div class=\"v-text-align\" style=\"color: #34495e; line-height: 180%; text-align: left; word-wrap: break-word;\">\r\n"
						+ "    <p style=\"font-size: 14px; line-height: 180%;\"><span style=\"font-family: 'Crimson Text', serif; font-size: 18px; line-height: 32.4px;\">093-290-4529 Thu Duc city</span><br /><span style=\"font-family: 'Crimson Text', serif; font-size: 18px; line-height: 32.4px;\">Terms of use | <span style=\"text-decoration: underline; font-size: 18px; line-height: 32.4px;\">Privacy Policy</span>&nbsp;</span></p>\r\n"
						+ "  </div>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n"
						+ "</table>\r\n" + "\r\n" + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n"
						+ "  </div>\r\n" + "</div>\r\n" + "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
						+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"275\" style=\"width: 275px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\r\n"
						+ "<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 275px;display: table-cell;vertical-align: top;\">\r\n"
						+ "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
						+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\r\n"
						+ "  \r\n" + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + "  </div>\r\n"
						+ "</div>\r\n" + "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
						+ "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "    </div>\r\n"
						+ "  </div>\r\n" + "</div>\r\n" + "\r\n" + "\r\n"
						+ "    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + "    </td>\r\n" + "  </tr>\r\n"
						+ "  </tbody>\r\n" + "  </table>\r\n" + "  <!--[if mso]></div><![endif]-->\r\n"
						+ "  <!--[if IE]></div><![endif]-->\r\n" + "</body>\r\n" + "\r\n" + "</html>\r\n" + "";
				try {
					Message message = new MimeMessage(session_mail);
					message.setFrom(new InternetAddress(usernameMail));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
					message.setSubject(emailSubject);
					message.setContent(emailContent01, "text/html");
//								message.setText(emailContent);
//								message.setText(emailContent01);
//					Multipart pdf = new MimeMultipart();
					//
//					MimeBodyPart textBodyPart = new MimeBodyPart();
//					textBodyPart.attachFile("E:\\130201.png");

//					pdf.addBodyPart(textBodyPart);
//					message.setContent(pdf);
					Transport.send(message);
					errMsg = "Done";
					System.out.println("Done");
				} catch (Exception e) {
					// TODO: handle exception
					paymentUrl = "Error Email";
					job.addProperty("message", "no-success");
					job.addProperty("data", paymentUrl);
				}
			} catch (Exception e) {
				paymentUrl = "Da xay ra loi";
				job.addProperty("message", "no-success");
				job.addProperty("data", paymentUrl);

			}
		} else {
			paymentUrl = "Mat khau khong khop";
			job.addProperty("message", "no-success");
			job.addProperty("data", paymentUrl);
		}
		response.getWriter().write(gson.toJson(job));
	}

	@GetMapping({ "/updateactive/{email}/{code}" })
	public void updateactive(Model model, HttpServletResponse response, HttpServletRequest request,
			@PathVariable(value = "email") String email, @PathVariable(value = "code") String code) throws IOException {
		String paymentUrl = "";
		Gson gson = new Gson();
		com.google.gson.JsonObject job = new JsonObject();
		try {
			paymentUrl = "da active account user";
			job.addProperty("message", "success");
			job.addProperty("data", paymentUrl);

			Users user = userService.getUsersByGmail(email);
			user.setActive(true);
			userService.updateActiveUser("true", email);
		} catch (Exception e) {
			// TODO: handle exception
			paymentUrl = "da xay ra loi: " + e.getMessage();
			job.addProperty("message", "no-success");
			job.addProperty("data", paymentUrl);

		}
		response.getWriter().write(gson.toJson(job));
	}

	@PostMapping({ "/reset" })
	public void reset(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String paymentUrl = "da gui mail !";
		com.google.gson.JsonObject job = new JsonObject();
		job.addProperty("message", "success");
		job.addProperty("data", paymentUrl);
		Gson gson = new Gson();
		String errMsg = "";
		final String usernameMail = "animewebvuighe@gmail.com";
		final String passwordMail = "jbivwdwhhlbbegto";
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(usernameMail, passwordMail);
			}
		});

		ForgotPassword forgot = new ForgotPassword();
		forgotPasswordService.addCodeEmail(forgot);
		// xac nhan password qua mail
		String emailTo = request.getParameter("email");
		String emailSubject = "Forgot password !";
		String content = request.getParameter("exampleFirstName") + " " + request.getParameter("exampleLastName");
		String emailContent = "Thanks you " + content + ", Congratulations on becoming a member of Ellent handmade";
		String emailContent01 = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
				+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\r\n"
				+ "<head>\r\n" + "<!--[if gte mso 9]>\r\n" + "<xml>\r\n" + "  <o:OfficeDocumentSettings>\r\n"
				+ "    <o:AllowPNG/>\r\n" + "    <o:PixelsPerInch>96</o:PixelsPerInch>\r\n"
				+ "  </o:OfficeDocumentSettings>\r\n" + "</xml>\r\n" + "<![endif]-->\r\n"
				+ "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
				+ "  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\r\n"
				+ "  <title></title>\r\n" + "  \r\n" + "    <style type=\"text/css\">\r\n"
				+ "      table, td { color: #000000; } a { color: #0000ee; text-decoration: underline; } @media (max-width: 480px) { #u_content_image_2 .v-src-width { width: auto !important; } #u_content_image_2 .v-src-max-width { max-width: 60% !important; } #u_content_text_1 .v-container-padding-padding { padding: 30px 30px 30px 20px !important; } #u_content_button_1 .v-container-padding-padding { padding: 10px 20px !important; } #u_content_button_1 .v-size-width { width: 100% !important; } #u_content_button_1 .v-text-align { text-align: left !important; } #u_content_button_1 .v-padding { padding: 15px 40px !important; } #u_content_text_3 .v-container-padding-padding { padding: 30px 30px 80px 20px !important; } #u_content_text_5 .v-text-align { text-align: center !important; } }\r\n"
				+ "@media only screen and (min-width: 570px) {\r\n" + "  .u-row {\r\n"
				+ "    width: 550px !important;\r\n" + "  }\r\n" + "  .u-row .u-col {\r\n"
				+ "    vertical-align: top;\r\n" + "  }\r\n" + "\r\n" + "  .u-row .u-col-50 {\r\n"
				+ "    width: 275px !important;\r\n" + "  }\r\n" + "\r\n" + "  .u-row .u-col-100 {\r\n"
				+ "    width: 550px !important;\r\n" + "  }\r\n" + "\r\n" + "}\r\n" + "\r\n"
				+ "@media (max-width: 570px) {\r\n" + "  .u-row-container {\r\n" + "    max-width: 100% !important;\r\n"
				+ "    padding-left: 0px !important;\r\n" + "    padding-right: 0px !important;\r\n" + "  }\r\n"
				+ "  .u-row .u-col {\r\n" + "    min-width: 320px !important;\r\n"
				+ "    max-width: 100% !important;\r\n" + "    display: block !important;\r\n" + "  }\r\n"
				+ "  .u-row {\r\n" + "    width: calc(100% - 40px) !important;\r\n" + "  }\r\n" + "  .u-col {\r\n"
				+ "    width: 100% !important;\r\n" + "  }\r\n" + "  .u-col > div {\r\n" + "    margin: 0 auto;\r\n"
				+ "  }\r\n" + "}\r\n" + "body {\r\n" + "  margin: 0;\r\n" + "  padding: 0;\r\n" + "}\r\n" + "\r\n"
				+ "table,\r\n" + "tr,\r\n" + "td {\r\n" + "  vertical-align: top;\r\n"
				+ "  border-collapse: collapse;\r\n" + "}\r\n" + "\r\n" + "p {\r\n" + "  margin: 0;\r\n" + "}\r\n"
				+ "\r\n" + ".ie-container table,\r\n" + ".mso-container table {\r\n" + "  table-layout: fixed;\r\n"
				+ "}\r\n" + "\r\n" + "* {\r\n" + "  line-height: inherit;\r\n" + "}\r\n" + "\r\n"
				+ "a[x-apple-data-detectors='true'] {\r\n" + "  color: inherit !important;\r\n"
				+ "  text-decoration: none !important;\r\n" + "}\r\n" + "\r\n" + "</style>\r\n" + "  \r\n" + "  \r\n"
				+ "\r\n"
				+ "<!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Crimson+Text:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\r\n"
				+ "\r\n" + "</head>\r\n" + "\r\n"
				+ "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #fbeeb8;color: #000000\">\r\n"
				+ "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\r\n"
				+ "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\r\n"
				+ "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #fbeeb8;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "  <tbody>\r\n" + "  <tr style=\"vertical-align: top\">\r\n"
				+ "    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n"
				+ "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #fbeeb8;\"><![endif]-->\r\n"
				+ "    \r\n" + "\r\n"
				+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: #ffffff\">\r\n"
				+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\r\n"
				+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n"
				+ "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #ffffff;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:550px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\r\n"
				+ "      \r\n"
				+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"550\" style=\"width: 550px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\r\n"
				+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n"
				+ "  <div style=\"width: 100% !important;\">\r\n"
				+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n"
				+ "  \r\n" + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + "  </div>\r\n" + "</div>\r\n"
				+ "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
				+ "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "    </div>\r\n"
				+ "  </div>\r\n" + "</div>\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
				+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\r\n"
				+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n"
				+ "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:550px;\"><tr style=\"background-color: transparent;\"><![endif]-->\r\n"
				+ "      \r\n"
				+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"550\" style=\"width: 550px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\r\n"
				+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n"
				+ "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
				+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\r\n"
				+ "  \r\n"
				+ "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
				+ "  <tbody>\r\n" + "    <tr>\r\n"
				+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:20px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
				+ "        \r\n"
				+ "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 0px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
				+ "    <tbody>\r\n" + "      <tr style=\"vertical-align: top\">\r\n"
				+ "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
				+ "          <span>&#160;</span>\r\n" + "        </td>\r\n" + "      </tr>\r\n" + "    </tbody>\r\n"
				+ "  </table>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n" + "</table>\r\n"
				+ "\r\n" + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + "  </div>\r\n" + "</div>\r\n"
				+ "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
				+ "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "    </div>\r\n"
				+ "  </div>\r\n" + "</div>\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
				+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\r\n"
				+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n"
				+ "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:550px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\r\n"
				+ "      \r\n"
				+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"542\" style=\"width: 542px;padding: 0px;border-top: 4px solid #d9d8d8;border-left: 4px solid #d9d8d8;border-right: 4px solid #d9d8d8;border-bottom: 4px solid #d9d8d8;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\r\n"
				+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n"
				+ "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
				+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 4px solid #d9d8d8;border-left: 4px solid #d9d8d8;border-right: 4px solid #d9d8d8;border-bottom: 4px solid #d9d8d8;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\r\n"
				+ "  \r\n"
				+ "<table id=\"u_content_image_2\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
				+ "  <tbody>\r\n" + "    <tr>\r\n"
				+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
				+ "        \r\n" + "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n"
				+ "  <tr>\r\n"
				+ "    <td class=\"v-text-align\" style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\r\n"
				+ "      \r\n"
				+ "      <img align=\"center\" border=\"0\" src=\"https://i.pinimg.com/564x/c8/e4/7d/c8e47d9dc35e300778dc76ac00fbbfb2.jpg\" alt=\"Wrong Email\" title=\"Wrong Email\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 530px;\" width=\"530\" class=\"v-src-width v-src-max-width\"/>\r\n"
				+ "      \r\n" + "    </td>\r\n" + "  </tr>\r\n" + "</table>\r\n" + "\r\n" + "      </td>\r\n"
				+ "    </tr>\r\n" + "  </tbody>\r\n" + "</table>\r\n" + "\r\n"
				+ "<table id=\"u_content_text_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
				+ "  <tbody>\r\n" + "    <tr>\r\n"
				+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 30px 30px 40px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
				+ "        \r\n"
				+ "  <div class=\"v-text-align\" style=\"color: #333333; line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n"
				+ "    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-family: 'Crimson Text', serif; font-size: 14px; line-height: 19.6px;\"><strong><span style=\"font-size: 22px; line-height: 30.8px;\">Hello!</span></strong></span></p>\r\n"
				+ "<p style=\"font-size: 14px; line-height: 140%;\">&nbsp;</p>\r\n"
				+ "<p style=\"font-size: 14px; line-height: 140%;\">&nbsp;</p>\r\n" + "  </div>\r\n" + "\r\n"
				+ "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n" + "</table>\r\n" + "\r\n"
				+ "<table id=\"u_content_button_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
				+ "  <tbody>\r\n" + "    <tr>\r\n"
				+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 40px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
				+ "        \r\n" + "<div class=\"v-text-align\" align=\"left\">\r\n"
				+ "  <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;font-family:arial,helvetica,sans-serif;\"><tr><td class=\"v-text-align\" style=\"font-family:arial,helvetica,sans-serif;\" align=\"left\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://localhost:8080/updateactive/"
				+ "\" style=\"height:47px; v-text-anchor:middle; width:456px;\" arcsize=\"6.5%\" strokecolor=\"#ced4d9\" strokeweight=\"3px\" fillcolor=\"#91a5e2\"><w:anchorlock/><center style=\"color:#000000;font-family:arial,helvetica,sans-serif;\"><![endif]-->\r\n"
				+ "    <a href=\"http://localhost:8085/updatepass/" + emailTo + "/" + forgot.getId() + ""
				+ "\" target=\"_blank\" class=\"v-size-width\" style=\"box-sizing: border-box;display: inline-block;font-family:arial,helvetica,sans-serif;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #000000; background-color: #91a5e2; border-radius: 3px;-webkit-border-radius: 3px; -moz-border-radius: 3px; width:100%; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;border-top-color: #ced4d9; border-top-style: solid; border-top-width: 3px; border-left-color: #ced4d9; border-left-style: solid; border-left-width: 3px; border-right-color: #ced4d9; border-right-style: solid; border-right-width: 3px; border-bottom-color: #ced4d9; border-bottom-style: solid; border-bottom-width: 3px;\">\r\n"
				+ "      <span class=\"v-padding\" style=\"display:block;padding:15px 40px;line-height:120%;\"><span style=\"font-size: 14px; line-height: 16.8px;\">C L I C K&nbsp; &nbsp;H E R E</span></span>\r\n"
				+ "    </a>\r\n" + "  <!--[if mso]></center></v:roundrect></td></tr></table><![endif]-->\r\n"
				+ "</div>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n" + "</table>\r\n"
				+ "\r\n"
				+ "<table id=\"u_content_text_3\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
				+ "  <tbody>\r\n" + "    <tr>\r\n"
				+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 30px 80px 40px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
				+ "        \r\n"
				+ "  <div class=\"v-text-align\" style=\"color: #333333; line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n"
				+ "    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; font-family: 'Crimson Text', serif;\">Feel free to reach out to us with any questions.</span></p>\r\n"
				+ "<p style=\"font-size: 14px; line-height: 140%;\">&nbsp;</p>\r\n"
				+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 22px; line-height: 30.8px;\"><strong><span style=\"line-height: 30.8px; font-family: 'Crimson Text', serif; font-size: 22px;\">Thanks.</span></strong></span><br /><span style=\"font-size: 18px; line-height: 25.2px; font-family: 'Crimson Text', serif;\">ANTi&nbsp; Customer Care Team</span></p>\r\n"
				+ "  </div>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n" + "</table>\r\n"
				+ "\r\n" + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + "  </div>\r\n" + "</div>\r\n"
				+ "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
				+ "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "    </div>\r\n"
				+ "  </div>\r\n" + "</div>\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
				+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\r\n"
				+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n"
				+ "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:550px;\"><tr style=\"background-color: transparent;\"><![endif]-->\r\n"
				+ "      \r\n"
				+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"550\" style=\"width: 550px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\r\n"
				+ "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n"
				+ "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
				+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\r\n"
				+ "  \r\n"
				+ "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
				+ "  <tbody>\r\n" + "    <tr>\r\n"
				+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
				+ "        \r\n" + "<div align=\"center\">\r\n"
				+ "  <div style=\"display: table; max-width:170px;\">\r\n"
				+ "  <!--[if (mso)|(IE)]><table width=\"170\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"border-collapse:collapse;\" align=\"center\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse; mso-table-lspace: 0pt;mso-table-rspace: 0pt; width:170px;\"><tr><![endif]-->\r\n"
				+ "  \r\n" + "    \r\n"
				+ "    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 25px;\" valign=\"top\"><![endif]-->\r\n"
				+ "    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 25px\">\r\n"
				+ "      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n"
				+ "        <a href=\"https://facebook.com/\" title=\"Facebook\" target=\"_blank\">\r\n"
				+ "          <img src=\"https://i.pinimg.com/564x/df/d9/f4/dfd9f4969e92f30198cbfd8f5ecc127f.jpg\" alt=\"Facebook\" title=\"Facebook\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\r\n"
				+ "        </a>\r\n" + "      </td></tr>\r\n" + "    </tbody></table>\r\n"
				+ "    <!--[if (mso)|(IE)]></td><![endif]-->\r\n" + "    \r\n"
				+ "    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 25px;\" valign=\"top\"><![endif]-->\r\n"
				+ "    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 25px\">\r\n"
				+ "      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n"
				+ "        <a href=\"https://twitter.com/\" title=\"Twitter\" target=\"_blank\">\r\n"
				+ "          <img src=\"https://i.pinimg.com/564x/a6/d5/55/a6d555266824307026e70706659ff785.jpg\" alt=\"Twitter\" title=\"Twitter\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\r\n"
				+ "        </a>\r\n" + "      </td></tr>\r\n" + "    </tbody></table>\r\n"
				+ "    <!--[if (mso)|(IE)]></td><![endif]-->\r\n" + "    \r\n"
				+ "    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 0px;\" valign=\"top\"><![endif]-->\r\n"
				+ "    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 0px\">\r\n"
				+ "      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n"
				+ "        <a href=\"https://linkedin.com/\" title=\"LinkedIn\" target=\"_blank\">\r\n"
				+ "          <img src=\"https://i.pinimg.com/564x/2b/e7/ce/2be7cee4fe404b8fa86d31d139fab757.jpg\" alt=\"LinkedIn\" title=\"LinkedIn\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\r\n"
				+ "        </a>\r\n" + "      </td></tr>\r\n" + "    </tbody></table>\r\n"
				+ "    <!--[if (mso)|(IE)]></td><![endif]-->\r\n" + "    \r\n" + "    \r\n"
				+ "    <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "  </div>\r\n"
				+ "</div>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n" + "</table>\r\n"
				+ "\r\n"
				+ "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
				+ "  <tbody>\r\n" + "    <tr>\r\n"
				+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 0px 21px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
				+ "        \r\n"
				+ "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 3px solid #000000;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
				+ "    <tbody>\r\n" + "      <tr style=\"vertical-align: top\">\r\n"
				+ "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n"
				+ "          <span>&#160;</span>\r\n" + "        </td>\r\n" + "      </tr>\r\n" + "    </tbody>\r\n"
				+ "  </table>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n" + "</table>\r\n"
				+ "\r\n" + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + "  </div>\r\n" + "</div>\r\n"
				+ "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
				+ "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "    </div>\r\n"
				+ "  </div>\r\n" + "</div>\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "<div class=\"u-row-container\" style=\"padding: 0px 0px 11px;background-color: transparent\">\r\n"
				+ "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\r\n"
				+ "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n"
				+ "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px 0px 11px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:550px;\"><tr style=\"background-color: transparent;\"><![endif]-->\r\n"
				+ "      \r\n"
				+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"275\" style=\"width: 275px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\r\n"
				+ "<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 275px;display: table-cell;vertical-align: top;\">\r\n"
				+ "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
				+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\r\n"
				+ "  \r\n"
				+ "<table id=\"u_content_text_5\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
				+ "  <tbody>\r\n" + "    <tr>\r\n"
				+ "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n"
				+ "        \r\n"
				+ "  <div class=\"v-text-align\" style=\"color: #34495e; line-height: 180%; text-align: left; word-wrap: break-word;\">\r\n"
				+ "    <p style=\"font-size: 14px; line-height: 180%;\"><span style=\"font-family: 'Crimson Text', serif; font-size: 18px; line-height: 32.4px;\">093-290-4529 Thu Duc city</span><br /><span style=\"font-family: 'Crimson Text', serif; font-size: 18px; line-height: 32.4px;\">Terms of use | <span style=\"text-decoration: underline; font-size: 18px; line-height: 32.4px;\">Privacy Policy</span>&nbsp;</span></p>\r\n"
				+ "  </div>\r\n" + "\r\n" + "      </td>\r\n" + "    </tr>\r\n" + "  </tbody>\r\n" + "</table>\r\n"
				+ "\r\n" + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + "  </div>\r\n" + "</div>\r\n"
				+ "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
				+ "<!--[if (mso)|(IE)]><td align=\"center\" width=\"275\" style=\"width: 275px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\r\n"
				+ "<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 275px;display: table-cell;vertical-align: top;\">\r\n"
				+ "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
				+ "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\r\n"
				+ "  \r\n" + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" + "  </div>\r\n" + "</div>\r\n"
				+ "<!--[if (mso)|(IE)]></td><![endif]-->\r\n"
				+ "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" + "    </div>\r\n"
				+ "  </div>\r\n" + "</div>\r\n" + "\r\n" + "\r\n"
				+ "    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" + "    </td>\r\n" + "  </tr>\r\n"
				+ "  </tbody>\r\n" + "  </table>\r\n" + "  <!--[if mso]></div><![endif]-->\r\n"
				+ "  <!--[if IE]></div><![endif]-->\r\n" + "</body>\r\n" + "\r\n" + "</html>\r\n" + "";
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(usernameMail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(emailSubject);
			message.setContent(emailContent01, "text/html");
//						message.setText(emailContent);
//						message.setText(emailContent01);
			Transport.send(message);
			errMsg = "Done";
			System.out.println("Done");

			paymentUrl = "http://localhost:8085/updatepass/" + emailTo + "/" + forgot.getId() + "";
			job.addProperty("message", errMsg);
			job.addProperty("data", paymentUrl);

		} catch (Exception e) {
			// TODO: handle exception
			paymentUrl = e.getMessage();
			job.addProperty("message", "no-success");
			job.addProperty("data", paymentUrl);
		}
		response.getWriter().write(gson.toJson(job));
	}

	@PostMapping({ "/update" })
	public void update(Model model, HttpServletResponse response, HttpServletRequest request,
			@Param(value = "password") String password, @Param(value = "confirmpassword") String confimlpassword,
			@Param(value = "email") String email, @Param(value = "code") String code) throws IOException {
		String paymentUrl = "da cap nhat mat khau thanh cong";
		com.google.gson.JsonObject job = new JsonObject();
		job.addProperty("message", "success");
		job.addProperty("data", paymentUrl);
		Gson gson = new Gson();
		String errMsg = "";
		String errMsgCode = "";
		ForgotPassword f = forgotPasswordService.find(code);
		String value = forgotPasswordService.find(code).getValue();
		if (value == null || !value.equals("update")) {
			if (password.equals(confimlpassword)) {
				try {
					Users user = userService.getUsersByGmail(email);
					CustomerPassword customerPassword = customerPasswordService.getPassByUserId(user.getUserId());
					customerPassword.setPassword(password);
					customerPassword.setUpdatedAt(new Date());
					customerPasswordService.addCodeEmail(customerPassword);
					f.setValue("update");
					forgotPasswordService.addNewMail(f);
				} catch (Exception e) {
					errMsg = "Da xay ra loi";
					paymentUrl = "da xay ra loi: " + e.getMessage();
					job.addProperty("message", errMsg);
					job.addProperty("data", paymentUrl);

				}
			} else {
				errMsg = "Mat khau khong khop";
				paymentUrl = "da xay ra loi !";
				job.addProperty("message", errMsg);
				job.addProperty("data", paymentUrl);
			}
		} else {
			errMsgCode = "Connect Error";
			paymentUrl = "da xay ra loi !";
			job.addProperty("message", errMsgCode);
			job.addProperty("data", paymentUrl);
		}
		response.getWriter().write(gson.toJson(job));
	}

	@PostMapping("/save")
	public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody Users theUsers)
			throws IOException {

		theUsers.setActive(true);
		userService.updateUser(theUsers.getFirstName(), theUsers.getLastName(), theUsers.getAddresss().getAddress(),
				theUsers.getEmail(), theUsers.getAddresss().getPhone(), theUsers.getUserId());
		String paymentUrl = "da cap nhat tai khoan thanh cong";
		com.google.gson.JsonObject job = new JsonObject();
		job.addProperty("message", "success");
		job.addProperty("data", JSONFormatter.toJSON(theUsers));
		Gson gson = new Gson();
		response.getWriter().write(gson.toJson(job));

	}

	@PostMapping({ "/uploadimg" })
	public void upload(@RequestParam(value = "user_id") String id, Model model, HttpServletResponse response,
			HttpServletRequest request, @RequestParam(value = "img[]") MultipartFile multipartFile) throws IOException {

		com.google.gson.JsonObject job = new JsonObject();
		Gson gson = new Gson();
		try {
			String linkDownload = this.amazonUploadService.uploadFile(multipartFile);
			Users users = userService.getUsersById(id);
			users.setAvatar(linkDownload);
			userService.updateAvaUser(linkDownload, id);
			String paymentUrl = "da cap nhat hnh anh thanh cong";
			job.addProperty("message", "success");
			job.addProperty("data", users.getAvatar());
			response.getWriter().write(gson.toJson(job));
		} catch (Exception e) {
			// TODO: handle exception
			job.addProperty("message", e.getMessage());
			response.getWriter().write(gson.toJson(job));
		}

	}
	//http://localhost:8080/barcodes/CODE128/130201
	@GetMapping(value = "/barcodes/{type}/{barcode}", produces = IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> generate(@PathVariable("type") final String type,
			@PathVariable("barcode") final String barcodeText, @Autowired BarcodeGenerator barcodeGenerator) {

		try {
			switch (type) {
			case "EAN13":
				// 978020137962
				return ok(barcodeGenerator.generateEAN13BarcodeImage(barcodeText));
			case "UPC":
				// 12345678901
				return ok(barcodeGenerator.generateUPCBarcodeImage(barcodeText));
			case "EAN128":
				// 0101234567890128TEC
				return ok(barcodeGenerator.generateEAN128BarCodeImage(barcodeText));
			case "CODE128":
				// any-string
				return ok(barcodeGenerator.generateCode128BarCodeImage(barcodeText));
			case "USPS":
				// 123456789
				return ok(barcodeGenerator.generateUSPSBarcodeImage(barcodeText));
			case "SCC14":
				return ok(barcodeGenerator.generateSCC14ShippingCodeBarcodeImage(barcodeText));
			case "CODE39":
				return ok(barcodeGenerator.generateCode39BarcodeImage(barcodeText));
			case "GTIN":
				return ok(barcodeGenerator.generateGlobalTradeItemNumberBarcodeImage(barcodeText));
			case "PDF417":
				return ok(barcodeGenerator.generatePDF417BarcodeImage(barcodeText));
			default:
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		}
	}
	private ResponseEntity<BufferedImage> ok(final BufferedImage bufferedImage) {
		return new ResponseEntity<>(bufferedImage, OK);
	}


}
