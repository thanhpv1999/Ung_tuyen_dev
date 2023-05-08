package com.nhom20.cimeemappserver.restController;

import static org.springframework.http.HttpStatus.OK;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Booking;
import com.nhom20.cimeemappserver.entity.Cast;
import com.nhom20.cimeemappserver.entity.Cinemas;
import com.nhom20.cimeemappserver.entity.Director;
import com.nhom20.cimeemappserver.entity.Employee;
import com.nhom20.cimeemappserver.entity.Genres;
import com.nhom20.cimeemappserver.entity.HeaderPromotion;
import com.nhom20.cimeemappserver.entity.Languagess;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Movie;
import com.nhom20.cimeemappserver.entity.Order;
import com.nhom20.cimeemappserver.entity.OrderDetail;
import com.nhom20.cimeemappserver.entity.OrderService;
import com.nhom20.cimeemappserver.entity.PaymentType;
import com.nhom20.cimeemappserver.entity.Price;
import com.nhom20.cimeemappserver.entity.Product;
import com.nhom20.cimeemappserver.entity.ProductType;
import com.nhom20.cimeemappserver.entity.Promotion;
import com.nhom20.cimeemappserver.entity.Rate;
import com.nhom20.cimeemappserver.entity.Roles;
import com.nhom20.cimeemappserver.entity.Room;
import com.nhom20.cimeemappserver.entity.SeatType;
import com.nhom20.cimeemappserver.entity.ShowTimings;
import com.nhom20.cimeemappserver.entity.Slides;
import com.nhom20.cimeemappserver.entity.TheatreShowTimes;
import com.nhom20.cimeemappserver.entity.TheatreShowTimesPk;
import com.nhom20.cimeemappserver.entity.Theatres;
import com.nhom20.cimeemappserver.entity.TicketRate;
import com.nhom20.cimeemappserver.entity.Users;
import com.nhom20.cimeemappserver.service.AboutService;
import com.nhom20.cimeemappserver.service.AmazonUploadService;
import com.nhom20.cimeemappserver.service.BookingService;
import com.nhom20.cimeemappserver.service.CastService;
import com.nhom20.cimeemappserver.service.CinemasService;
import com.nhom20.cimeemappserver.service.DirectorService;
import com.nhom20.cimeemappserver.service.EmployeeService;
import com.nhom20.cimeemappserver.service.GenresService;
import com.nhom20.cimeemappserver.service.HeaderPromotionService;
import com.nhom20.cimeemappserver.service.LanguageService;
import com.nhom20.cimeemappserver.service.LocationService;
import com.nhom20.cimeemappserver.service.MenuService;
import com.nhom20.cimeemappserver.service.MoviesService;
import com.nhom20.cimeemappserver.service.OrdersDetailService;
import com.nhom20.cimeemappserver.service.OrdersService;
import com.nhom20.cimeemappserver.service.PaymentTypeService;
import com.nhom20.cimeemappserver.service.PriceService;
import com.nhom20.cimeemappserver.service.ProductPriceService;
import com.nhom20.cimeemappserver.service.ProductService;
import com.nhom20.cimeemappserver.service.PromotionService;
import com.nhom20.cimeemappserver.service.RateService;
import com.nhom20.cimeemappserver.service.RolesService;
import com.nhom20.cimeemappserver.service.RoomService;
import com.nhom20.cimeemappserver.service.SeatTypeService;
import com.nhom20.cimeemappserver.service.ShowTimingService;
import com.nhom20.cimeemappserver.service.SlideService;
import com.nhom20.cimeemappserver.service.StatusService;
import com.nhom20.cimeemappserver.service.TheatreService;
import com.nhom20.cimeemappserver.service.TheatreShowTimeService;
import com.nhom20.cimeemappserver.service.TicketRateService;
import com.nhom20.cimeemappserver.service.UserService;

import groovyjarjarpicocli.CommandLine.Parameters;
import io.swagger.v3.oas.annotations.Parameter;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class AdminRestController {
//	private static final String URL = "http://34.68.175.32/";
	private static final String URL = "http://localhost:8085/";
	private Image image = null;
	private Image image2 = null;
	private String orderId = null;
	private String createdAt = null;
	private String name = null;
	private double total = 0;
	private List<OrderDetail> listOrders = new ArrayList<>();

	@Autowired
	private BookingService bookingService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrdersDetailService ordersDetailService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private MoviesService moviesService;
	@Autowired
	private GenresService genresService;
	@Autowired
	private DirectorService directorService;
	@Autowired
	private CastService castService;
	@Autowired
	private LanguageService languageService;
	@Autowired
	private RateService rateService;
	@Autowired
	private AmazonUploadService amazonUploadService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private TheatreShowTimeService theatreShowTimeService;
	@Autowired
	private TheatreService theatreService;
	@Autowired
	private ShowTimingService showTimingService;
	@Autowired
	private CinemasService cinemasService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private UserService userService;
	@Autowired
	private HeaderPromotionService headerPromotionService;
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PriceService priceService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private ProductPriceService productPriceService;
	@Autowired
	private TicketRateService ticketRateService;
	@Autowired
	private SeatTypeService seatTypeService;
	@Autowired
	private PaymentTypeService paymentTypeService;

	@PostMapping("/inventory")
//	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public List<Object[]> home(HttpServletRequest req, @RequestParam(value = "salesman_id") String salesman_id) {
		return bookingService.inventoryByCategory(salesman_id);

	}

	@PostMapping("/revenue")
//	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public List<Object[]> revenue(HttpServletRequest req, @RequestParam(value = "salesman_id") String salesman_id) {
		return bookingService.revenueByCategory(salesman_id);

	}

	@PostMapping("/revenue-year")
//	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public List<Object[]> revenueByYear(HttpServletRequest req,
			@RequestParam(value = "salesman_id") String salesman_id) {
		return bookingService.revenueByYear(salesman_id);

	}

	@PostMapping("/revenue-month")
//	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public List<Object[]> revenueByMonth(HttpServletRequest req,
			@RequestParam(value = "salesman_id") String salesman_id) {
		return bookingService.revenueByMONTH(salesman_id);

	}

	@PostMapping("/revenue-quater")
//	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public List<Object[]> quater(HttpServletRequest req, @RequestParam(value = "salesman_id") String salesman_id) {
		return bookingService.revenueByQuater(salesman_id);

	}

	@GetMapping("/count-product")
//	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public int count(HttpServletRequest req) {
		return bookingService.countQuantityProduct();

	}

	@GetMapping("/count-order")
//	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public int countOrder(HttpServletRequest req, @RequestParam(value = "salesman_id") String salesman_id) {
		return bookingService.countOrder(salesman_id);

	}

	@GetMapping("/sales-order")
//	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public int salesOrder(HttpServletRequest req, @RequestParam(value = "salesman_id") String salesman_id) {
		return bookingService.salesOrderDetail(salesman_id);

	}

	@CrossOrigin(origins = "http://localhost:3002", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
	@GetMapping("/list-booking")
	public List<Booking> home(HttpServletRequest req) {
		return bookingService.listBookings();

	}

	@PostMapping("/adorder")
	public Page<Order> order(HttpServletRequest req,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
			HttpServletRequest request,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort,
			@RequestParam(value = "salesman_id") String salesman_id) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("order_date").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("order_date").descending();
		}
		Pageable pageable = PageRequest.of(page - 1, size, sortable);
		Page<Order> orderDetails = ordersService.getDsOrder(pageable, salesman_id);
		List<OrderDetail> liOrderDetails = new ArrayList<>();
		for (Order orders2 : orderDetails) {
			orders2.setPrice(ordersService.getSumPriceByOrderid(orders2.getOrderId()));
			liOrderDetails = ordersService.orderById(orders2.getOrderId());
			for (OrderDetail double1 : liOrderDetails) {
				orders2.setOrderDetail(double1);
			}
		}

		return orderDetails;
	}

	@GetMapping("/confirm")
	private ResponseEntity confirm(HttpServletRequest request) {

		try {
			String userId = request.getParameter("userId");
			statusService.confirm(userId);
			return ResponseEntity.ok("succes");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		}
	}

	@GetMapping("/cancel")
	private ResponseEntity cancel(HttpServletRequest request) {
		try {
			String userId = request.getParameter("userId");
			statusService.cancel(userId);
			return ResponseEntity.ok("succes");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		}
	}

	@GetMapping("/list-order")
	private Order order(HttpServletRequest request) {
		String userId = request.getParameter("userId");

		return ordersService.findOrderId(userId);
	}

	@GetMapping("/print")
	private ResponseEntity print(HttpServletRequest request) throws MalformedURLException, IOException {

		String userId = request.getParameter("userId");
		orderId = userId;
		image = ImageIO.read(new URL("" + URL + "barcodes/CODE128/" + userId + ""));
		image2 = ImageIO.read(new URL("https://i.pinimg.com/564x/61/d7/18/61d71876e574c6a277c7f64ce3af44f1.jpg"));
		listOrders.addAll(ordersService.orderById(userId));
		total = ordersService.getSumPriceByOrderid(userId);
		name = ordersService.findOrderId(userId).getUsers().getFirstName() + " "
				+ ordersService.findOrderId(userId).getUsers().getLastName();
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPrintable(new BillPrintable(), getPageFormat(pj));
		try {
			pj.print();
		} catch (PrinterException ex) {
			ex.printStackTrace();
		}

		return ResponseEntity.ok("succes");
	}

	// *************************************************************************************************************************************
//		Phần In Bill
	//
	// *************************************************************************************************************************************
	ImageIcon icon = null;
	ImageIcon icon2 = null;

	public PageFormat getPageFormat(PrinterJob pj) {

		PageFormat pf = pj.defaultPage();
		Paper paper = pf.getPaper();

		double bodyHeight = bHeight;
		double headerHeight = 5.0;
		double footerHeight = 5.0;
		double width = cm_to_pp(50);
		double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
		paper.setSize(width, height);
		paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

		pf.setOrientation(PageFormat.PORTRAIT);
		pf.setPaper(paper);

		return pf;
	}

	protected static double cm_to_pp(double cm) {
		return toPPI(cm * 0.393600787);
	}

	protected static double toPPI(double inch) {
		return inch * 722d;
	}

	Double totalAmount = 0.0;
	Double cash = 0.0;
	Double balance = 0.0;
	Double bHeight = 0.0;

	ArrayList<String> itemName = new ArrayList<>();
	ArrayList<String> quantity = new ArrayList<>();
	ArrayList<String> itemPrice = new ArrayList<>();
	ArrayList<String> subtotal = new ArrayList<>();

	public class BillPrintable implements Printable {

		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

			int r = itemName.size();

			icon = new ImageIcon(image);
			icon2 = new ImageIcon(image2);

			int result = NO_SUCH_PAGE;
			if (pageIndex == 0) {

				Graphics2D g2d = (Graphics2D) graphics;
				double width = pageFormat.getImageableWidth();
				g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

				FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 7));

				try {
					int y = 20;
					int yShift = 10;
					int headerRectHeight = 15;
					g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
					g2d.drawImage(icon2.getImage(), 50, 20, 90, 60, null);
					y += yShift + 50;
					g2d.drawString("-------------------------------------", 12, y);
					y += yShift;
					g2d.drawString("             VÉ XEM PHIM              ", 12, y);
					y += yShift;
					g2d.drawString("Liên 2: Khách hàng  ", 12, y);
					y += yShift;
					g2d.drawString("Mẫu số: 01/VE2/002; Ký hiệu: BT/16T ", 12, y);
					y += yShift;
					g2d.drawString("Số 002256           (MST:2568852222) ", 12, y);
					y += yShift;
					g2d.drawString("CÔNG TY TNHH CJ CGV - CHI NHÁNH THỦ ĐỨC", 12, y);
					y += yShift;
					g2d.drawString("Trung tâm thương mại dịch vụ AEON Thủ Đức", 12, y);
					y += yShift;
					g2d.drawString(" Thành phố Hồ Chí Minh, Việt Nam", 12, y);
					y += yShift;
					g2d.drawString("-------------------------------------", 12, y);
					y += headerRectHeight;
					g2d.drawString("Đến:  " + name + " / ", 12, y);
					y += yShift;
					g2d.drawString("Ngày đặt hàng:       " + createdAt, 12, y);
					y += yShift;
//						g2d.drawString("Nhân viên bán hàng:   " + txtTenNV.getText(), 12, y);
//						y += headerRectHeight;
//						g2d.drawString("Họ tên khách hàng:   " + txtTenKH.getText(), 12, y);
//						y += headerRectHeight;

					g2d.drawString("Nội dung hàng                  Số lượng   ", 12, y);
					y += yShift;
					for (OrderDetail orderDetail : listOrders) {
//							for (int k = 0; k < tb.getColumnCount(); k++) {
						g2d.drawString(" " + orderDetail.getProduct().getName() + "\t" + "                            ",
								10, y);
						y += yShift;
//							g2d.drawString(" " + model.getValueAt(j, 2) + " * " + model.getValueAt(j, 3), 10, y);
						g2d.drawString(" " + orderDetail.getAmount(), 160, y);
						y += yShift;
//							}
						g2d.drawString("\n", 0, y);
						y += yShift;
						y += headerRectHeight;
					}
					//
					g2d.drawString("", 10, y);
					y += yShift;
					g2d.drawString("-------------------------------------", 10, y);
					y += yShift;
					g2d.drawString(" Tổng tiền thu người nhận:    " + total + "   ", 10, y);
					y += yShift;
//						g2d.drawString("-------------------------------------", 10, y);
//						y += yShift;
//						g2d.drawString(" Khách đưa:               " + txtTienKhachDua.getText() + "   ", 10, y);
//						y += yShift;
//						g2d.drawString("-------------------------------------", 10, y);
//						y += yShift;
//						g2d.drawString(" Tiền thừa:               " + lblTongTienThoi.getText() + "   ", 10, y);
					y += yShift;
					g2d.drawString("*************************************", 10, y);
					y += yShift;
					g2d.drawString("       THANK YOU & COME AGAIN            ", 10, y);
					y += yShift;
					g2d.drawString("*************************************", 10, y);
					y += yShift;
					g2d.drawString("       SOFTWARE BY:          ", 10, y);
					y += yShift;
					g2d.drawString("   CONTACT: 0932904529       ", 10, y);
					y += yShift;

					g2d.drawString("-------------------------------------", 12, y);
					g2d.drawImage(icon.getImage(), 50, 350, 90, 30, null);
					y += yShift + 30;

				} catch (Exception e) {
					e.printStackTrace();
				}

				result = PAGE_EXISTS;
			}
			return result;
		}
	}

	@GetMapping("/movie")
	public Page<Movie> product(HttpServletRequest req,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
			HttpServletRequest request,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		try {
			Sort sortable = null;
			if (sort.equals("ASC")) {
				sortable = Sort.by("title").ascending();
			}
			if (sort.equals("DESC")) {
				sortable = Sort.by("title").descending();
			}

			Pageable pageable = PageRequest.of(page - 1, size, sortable);

			Page<Movie> orderDetails = moviesService.getListMovies(pageable);

			return orderDetails;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		}
	}

	@GetMapping("/booking")
	public Page<Booking> booking(HttpServletRequest req,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
			HttpServletRequest request,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		try {
			Sort sortable = null;
			if (sort.equals("ASC")) {
				sortable = Sort.by("createdAt").ascending();
			}
			if (sort.equals("DESC")) {
				sortable = Sort.by("createdAt").descending();
			}

			Pageable pageable = PageRequest.of(page - 1, size, sortable);

			Page<Booking> orderDetails = bookingService.listBookingsOnPage(pageable);

			return orderDetails;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		}
	}

	@GetMapping("/genres")
	public Page<Genres> genres(HttpServletRequest req,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
			HttpServletRequest request,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		try {
			Sort sortable = null;
			if (sort.equals("ASC")) {
				sortable = Sort.by("genres").ascending();
			}
			if (sort.equals("DESC")) {
				sortable = Sort.by("genres").descending();
			}

			Pageable pageable = PageRequest.of(page - 1, size, sortable);

			Page<Genres> orderDetails = genresService.listGenresOnPage(pageable);

			return orderDetails;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		}
	}

	@GetMapping("/director")
	public Page<Director> director(HttpServletRequest req,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
			HttpServletRequest request,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		try {
			Sort sortable = null;
			if (sort.equals("ASC")) {
				sortable = Sort.by("name").ascending();
			}
			if (sort.equals("DESC")) {
				sortable = Sort.by("name").descending();
			}

			Pageable pageable = PageRequest.of(page - 1, size, sortable);

			Page<Director> orderDetails = directorService.lisDirector(pageable);

			return orderDetails;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		}
	}

	@GetMapping("/cast")
	public Page<Cast> cast(HttpServletRequest req,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
			HttpServletRequest request,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		try {
			Sort sortable = null;
			if (sort.equals("ASC")) {
				sortable = Sort.by("name").ascending();
			}
			if (sort.equals("DESC")) {
				sortable = Sort.by("name").descending();
			}

			Pageable pageable = PageRequest.of(page - 1, size, sortable);

			Page<Cast> orderDetails = castService.listMenus(pageable);

			return orderDetails;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		}
	}

	@GetMapping("/languge")
	public Page<Languagess> languge(HttpServletRequest req,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
			HttpServletRequest request,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		try {
			Sort sortable = null;
			if (sort.equals("ASC")) {
				sortable = Sort.by("decripstion").ascending();
			}
			if (sort.equals("DESC")) {
				sortable = Sort.by("decripstion").descending();
			}

			Pageable pageable = PageRequest.of(page - 1, size, sortable);

			Page<Languagess> orderDetails = languageService.listLanguagess(pageable);

			return orderDetails;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		}
	}

	@GetMapping("/rate")
	public Page<Rate> rate(HttpServletRequest req,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
			HttpServletRequest request,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		try {
			Sort sortable = null;
			if (sort.equals("ASC")) {
				sortable = Sort.by("status").ascending();
			}
			if (sort.equals("DESC")) {
				sortable = Sort.by("status").descending();
			}

			Pageable pageable = PageRequest.of(page - 1, size, sortable);

			Page<Rate> orderDetails = rateService.listRates(pageable);

			return orderDetails;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		}
	}

	@PostMapping({ "/uploadimg" })
	public ResponseEntity upload(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "img[]") MultipartFile multipartFile) throws IOException {
		try {
			String linkDownload = this.amazonUploadService.uploadFile(multipartFile);
			System.out.println(linkDownload);

			return new ResponseEntity<>(linkDownload, OK);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@PostMapping("/saveProduct")
	private Movie luu(HttpServletRequest request, HttpServletResponse response, @RequestBody Movie theMovie,
			@Parameters String values, @Parameters String actor, @Parameters String director,
			@Parameters String location) {
		try {
			moviesService.saveMovie(theMovie);
			//// save genres
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			genresService.saveGenAndMovie(values, theMovie.getMovieId());
			//// save cast
			castService.saveCasAndBook(actor, theMovie.getMovieId());
			//// save director
			directorService.saveDirAndBook(director, theMovie.getMovieId());
			//// save location
			locationService.saveLocAndBook(location, theMovie.getMovieId());
		}

		return theMovie;

	}

	@GetMapping("/list-director")
	private List<Director> director(HttpServletRequest request) {

		return directorService.listDirector();
	}

	@GetMapping("/list-genres")
	private List<Genres> genres(HttpServletRequest request) {

		return genresService.listGenres();
	}

	@GetMapping("/list-location")
	private List<Location> location(HttpServletRequest request) {

		return locationService.getListLocation();
	}

	@GetMapping("/list-cast")
	private List<Cast> cast(HttpServletRequest request) {

		return castService.listCast();
	}

	@GetMapping("/list-rate")
	private List<Rate> rate(HttpServletRequest request) {

		return rateService.listRates();
	}

	@PostMapping("/deleteProduct")
	public ResponseEntity deleteProduct(@Parameters String id) {
		try {
			moviesService.deleteMovie(id);
			return new ResponseEntity<>("succes", OK);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@GetMapping("/list-threate-show-time")
	private List<TheatreShowTimes> threateShow(HttpServletRequest request) {

		return theatreShowTimeService.getListTimes();
	}

	@GetMapping("/list-threate")
	private List<Theatres> threate(HttpServletRequest request) {

		return theatreService.listTheatres();
	}

	@GetMapping("/list-time")
	private List<ShowTimings> time(HttpServletRequest request, @Parameters String idThe, @Parameters int idRoom) {

		return showTimingService.getTimeByIdRoomAndIdThre(idThe, idRoom);
	}

	@GetMapping("/list-cinemas-id")
	private List<Theatres> cinemas(HttpServletRequest request) {

		List<Theatres> theatres = theatreService.listTheatres();
		for (Theatres theatres2 : theatres) {
			List<Cinemas> cinemas = cinemasService.listCinemaByThearte(theatres2.getTheatreId());
			for (Cinemas theatres3 : cinemas) {
				List<Room> room = roomService.listRoomByCinema(theatres3.getCinemaId());
				theatres2.setRoom(room);
			}
			theatres2.setCinemas(cinemas);

		}
		return theatres;
	}
	@GetMapping("/list-cinemas-id-theatre")
	private List<Cinemas> theatre(HttpServletRequest request, @Parameters String id) {

		
		return cinemasService.listCinemaByThearte(id);
	}

	@GetMapping("/list-room-by-cinema")
	private List<Room> roomByCinemas(HttpServletRequest request, @Parameters String name) {

		return roomService.listRoomByCinema(name);
	}

	@RequestMapping(value = "/saveTheToTime", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	private ResponseEntity saveTheatreShowTime(HttpServletRequest request, HttpServletResponse response,
			@Parameters String idThe, @Parameters int idThe2, @Parameters String idThe3, @Parameters String idThe4,
			@Parameters String idThe5, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date idThe6,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date idThe7, @Parameters boolean idThe8) {

		Theatres theatres = new Theatres();
		theatres.setTheatreId(idThe);
		Room room = new Room();
		room.setRoomId(idThe2);

		ShowTimings showTiming = new ShowTimings();
		showTiming.setShowTimeId(idThe3);
		Cinemas cinema = new Cinemas();
		cinema.setCinemaId(idThe4);
		Movie movie = new Movie();
		movie.setMovieId(idThe5);
		TheatreShowTimes theMovie = new TheatreShowTimes(theatres, showTiming, room, movie, cinema, idThe6, idThe7,
				idThe8);
		try {
			theatreShowTimeService.saveShow(theMovie);
			return ResponseEntity.ok("succes");
			//// save genres
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}

	@PostMapping("/saveTime")
	private ShowTimings saveTime(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ShowTimings theMovie) {
		try {
			showTimingService.saveTiming(theMovie);
			//// save genres
		} catch (Exception e) {
			// TODO: handle exception
		}

		return theMovie;

	}

	@GetMapping("/listTime")
	private Page<ShowTimings> listTime(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("time").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("time").descending();
		}

		Pageable pageable = PageRequest.of(page - 1, size, sortable);

		return showTimingService.getList(pageable);

	}

	@GetMapping("/list-employee")
	public Page<Employee> employee(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("lastName").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("lastName").descending();
		}

		Pageable pageable = PageRequest.of(page - 1, size, sortable);

		return employeeService.listEmployee(pageable);

	}

	@GetMapping("/list-users")
	public Page<Users> users(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("lastName").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("lastName").descending();
		}

		Pageable pageable = PageRequest.of(page - 1, size, sortable);

		return userService.listUser(pageable);

	}

	@GetMapping("/listheader")
	public List<HeaderPromotion> listheader(HttpServletRequest req, HttpServletRequest request) {
		List<HeaderPromotion> headerPromotions = headerPromotionService.getListHeaderPromotion();
		for (HeaderPromotion headerPromotion : headerPromotions) {
			List<Promotion> promotions = promotionService.listPromotionByHeader(headerPromotion.getId());
			headerPromotion.setPromotions(promotions);
//			for (Promotion promotion : promotions) {
//				headerPromotion.setStatus(promotion.isStatus());
//			}

		}
		return headerPromotions;

	}

	@GetMapping("/listPromo")
	public List<Promotion> voucher(HttpServletRequest req, HttpServletRequest request) {

		return promotionService.listPromotionByHeaderEpx();

	}

	@PostMapping({ "/filterVoucher" })
	public List<Promotion> filterVoucher(@RequestParam("headerId") String theId, HttpServletRequest req) {

		return promotionService.listPromotionByHeader(theId);

	}

	@GetMapping("/voucher-header")
	public List<Promotion> voucherDetail(HttpServletRequest req, HttpServletRequest request) {

		List<HeaderPromotion> headerPromotions = headerPromotionService.getListHeaderPromotion();
		List<Promotion> promotions = new ArrayList<>();
		for (HeaderPromotion headerPromotion : headerPromotions) {
			promotions = promotionService.listPromotionByHeader(headerPromotion.getId());
		}
		return promotions;

	}

	@RequestMapping(value = "/saveHeader", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	private HeaderPromotion saveHeader(HttpServletRequest request, HttpServletResponse response,
			@Parameters String idThe, @Parameters String idThe2,
			@Parameters @DateTimeFormat(pattern = "yyyy-MM-dd") Date idThe6,
			@Parameters @DateTimeFormat(pattern = "yyyy-MM-dd") Date idThe7, @Parameters String idThe8) {
		HeaderPromotion theMovie = new HeaderPromotion(idThe, idThe2, idThe6, idThe7, idThe8);
		try {

			headerPromotionService.saveHeader(theMovie);
			//// save genres
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return theMovie;

	}

	@PostMapping("/updateHeader")
	private String updateHeader(@Parameters String gen, @Parameters String gen2, @Parameters String gen3,
			@Parameters String id) throws IOException {

		headerPromotionService.updateHeaderPromotion(gen, gen2, gen3, id);

		return "ok";

	}

	@PostMapping("/deleteHeader")
	public ResponseEntity deleteHeader(@Parameters String id) {
		try {
			headerPromotionService.deleteHeader(id);
			return new ResponseEntity<>("succes", OK);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	

	@PostMapping("/deletePromotion")
	public ResponseEntity deletePrômtion(@Parameters String id) {
		try {
			promotionService.deletePromotion(id);
			return new ResponseEntity<>("succes", OK);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@GetMapping("/list-services")
	public Page<Product> services(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("amount").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("amount").descending();
		}

		Pageable pageable = PageRequest.of(page - 1, size, sortable);
		Page<Product> orderDetails = productService.listProduct(pageable);
		for (Product order : orderDetails) {

			order.setPrice(priceService.priceByBookId(order.getProductId()));
			order.setPrices(priceService.listPriceByProductId(order.getProductId()));
		}
		return orderDetails;

	}

	@PostMapping("/updateService")
	private String updateService(@Parameters String gen, @Parameters String gen2, @Parameters String gen3,
			@Parameters String gen4, @Parameters String id) throws IOException {

		productService.updateProduct(gen, gen2, gen3, gen4, id);

		return "ok";

	}

	@PostMapping("/saveService")
	private String saveService(@Parameters String name, @Parameters int amount, @Parameters String decs,
			@Parameters String img, @Parameters double value,
			@Parameters @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@Parameters @DateTimeFormat(pattern = "yyyy-MM-dd") Date exp, @Parameters double imputPrice,
			@Parameters double retailPrice, @Parameters boolean active) throws IOException {
		ProductType type = new ProductType();
		type.setProductTypeId("40eac5bb-8992-4583-aedf-998a0b029213");
		Product product = new Product(name, amount, type, decs, img);
		Price price = new Price();
		try {

			// lưu dịch vụ
			productService.save(product);

			/////////////////////////////
			
			price.setDecs("Giá chung");
			price.setCurrencyUnit("VND");
			price.setActive(active);
			price.setTitle("Giá chung");
			price.setStartDay(date);
			price.setEndDay(exp);
			price.setImportPrice(imputPrice);
			price.setRetailPrice(retailPrice);
			price.setValue(value);
			// lưu giá nhập
			priceService.save(price);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			productPriceService.savePriceAndMovie(product.getProductId(), price.getPriceId(), active);
		}

		return "ok";

	}
	@PostMapping("/deleteService")
	public ResponseEntity deleteService(@Parameters String id) {
		try {
			productService.deleteService(id);
			return new ResponseEntity<>("succes", OK);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	@GetMapping("/listprice")
	public List<Price> listprice(HttpServletRequest req, HttpServletRequest request) {
		List<Price> headerPromotions = priceService.listPrice();
		for (Price headerPromotion : headerPromotions) {
			List<Product> promotions = productService.listProductByHeader(headerPromotion.getPriceId());
			List<TicketRate> ticketRates = ticketRateService.listTicketRate(headerPromotion.getPriceId());
			List<SeatType> seatTypes =new ArrayList<>();
			for (TicketRate price : ticketRates) {
				seatTypes= seatTypeService.listSeatType(price.getTicketRateId());
			}
			headerPromotion.setProducts(promotions);
			for (Product promotion : promotions) {
				promotion.setPrice(headerPromotion.getValue());
				
			}
			headerPromotion.setSeatTypes(seatTypes);
			headerPromotion.setTicketRates(ticketRates);
		}
		return headerPromotions;

	}

	@GetMapping("/roles")
	public List<Roles> roles(HttpServletRequest req, HttpServletRequest request) {
		return rolesService.listRoles();

	}
	@PostMapping("/updateRoles")
	private String updateRoles(@Parameters String gen, @Parameters String id,@Parameters String idAcc) throws IOException {

		rolesService.updateRoleEmployee(gen, id);
		rolesService.updateRoleUser(gen, idAcc);

		return "ok";

	}
	@PostMapping("/deleteShowtime")
	public ResponseEntity deleteShowtime(@Parameters String id) {
		try {
			showTimingService.deleteshowTiming(id);
			return new ResponseEntity<>("succes", OK);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@GetMapping("/list-paymentType")
	public List<PaymentType> paymentType(HttpServletRequest req, HttpServletRequest request) {

		
		return paymentTypeService.listPaymentType();

	}
	
	@GetMapping("/list-seat-type")
	public List<SeatType> seatType() {
	
		List<SeatType> orderDetails = seatTypeService.listTypeOk();
		for (SeatType order : orderDetails) {

			order.setPrices(priceService.listPriceByType(order.getSeatTypeId()));
			
		}
		return orderDetails;

	}
	

	@GetMapping("/list-all")
	public List<Map<String,Object>> all() {
	
		List<Map<String,Object>> resultList = new ArrayList<>();
		List<SeatType> orderDetails = seatTypeService.listTypeOk();
		for (SeatType order : orderDetails) {

			order.setPrices(priceService.listPriceByType(order.getSeatTypeId()));
			
		}
		List<Product> orderProduct = productService.listProduct();
		for (Product order : orderProduct) {

			order.setPrice(priceService.priceByBookId(order.getProductId()));
			order.setPrices(priceService.listPriceByProductId(order.getProductId()));
		}
		
		Map<String, Object> newElement = new HashMap<String, Object>();
		

			// iterate over the arrays and add their values to the list
//			for (int i = 0; i < orderProduct.size(); i++) {
			    Map<String,Object> map = new HashMap<>();
			    map.put("value2", orderDetails);
			    map.put("value1", orderProduct);
			    resultList.add(map);
//			}
			return resultList;

	}
	
	@GetMapping("/search")
	public List<Product> search( @Param("id") String id) {
		
		List<Product> pro= productService.listById(id);
		List<Price> headerPromotions = priceService.listPrice();
	
		for (Product order : pro) {

			order.setPrice(priceService.priceByBookId(order.getProductId()));
			order.setPrices(priceService.listPriceByProductId(order.getProductId()));
		}
		return pro;
	}

}
