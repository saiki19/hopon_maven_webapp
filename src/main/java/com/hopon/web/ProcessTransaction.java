package com.hopon.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hopon.dto.MessageBoardDTO;
import com.hopon.dto.PaymentDTO;
import com.hopon.dto.PaymentRequestDTO;
import com.hopon.utils.ApplicationUtil;
import com.hopon.utils.ConfigurationException;
import com.hopon.utils.ListOfValuesManager;
import com.hopon.utils.Validator;

/**
 * Servlet implementation class ProcessTransaction
 */
@WebServlet("/pages/processTransaction")
public class ProcessTransaction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();	
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		if (session != null
				&& !Validator.isEmpty(session.getAttribute("userId"))) {

			ArrayList<String> parameterNames = new ArrayList<String>();
			Enumeration enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String parameterName = (String) enumeration.nextElement();
				parameterNames.add(parameterName);
			}
			PaymentRequestDTO payment = new PaymentRequestDTO();
		
			if (parameterNames.size() == 0) {

				// return error
			} else if (request.getParameter("STATUS") != null
					&& request.getParameter("STATUS").equals("TXN_SUCCESS")) {
				
				// transaction success
				float amount = Float.parseFloat(request
						.getParameter("TXNAMOUNT"));

				float amountNew = (float) ((amount * 2.5) / 100);
				float credit = Math.round(amount - amountNew);

				PaymentRequestDTO dto = new PaymentRequestDTO();
				dto.setOrder_id(request.getParameter("ORDERID"));
				
				dto = ListOfValuesManager.fetchPaymentRequestByOrderId(dto);
				
				dto.setTransaction_id(request.getParameter("TXNID"));
				dto.setStatus("S");
				try {
					ListOfValuesManager
							.updatePaymentRequestEntryStatusByOrderId(dto);
				} catch (ConfigurationException e1) {
				}

				String html = "";
				html += "<p>Hi There,</p>";
				html += "<p>Thank you for purchasing HopOn Credits. Your order details are mentioned below.</p>";
				html += "<br/>";
				html += "<p>Order ID :" + request.getParameter("ORDERID")
						+ "</p>";
				html += "<p>Payment Transaction ID : "
						+ request.getParameter("TXNID") + "</p>";
				try {
					html += "<p>Date &amp; time of order : "
							+ ApplicationUtil.dateFormat4
									.format(ApplicationUtil.dateFormat20
											.parse(request
													.getParameter("TXNDATE")))
							+ "</p>";
				} catch (ParseException e) {

				}
				html += "<p>Order Amount : Rs. "
						+ request.getParameter("TXNAMOUNT") + "/- Only</p>";
				html += "<p>HopOn Credits : " + credit + "</p>";
				html += "<p>Transaction status : "
						+ request.getParameter("STATUS") + "</p>";
				html += "<br/>";
				html += "<p>In case of failure of the transaction the amount will be credited in your respective bank / credit card account within 7 working days from the date of transaction.</p>";
				html += "<br/>";
				html += "<p>Happy to help,</p>";
				html += "<p>Team HopOn</p>";
				html += "<p>Email: info@hopon.co.in</p>";
				html += "<p>WebSite: http://www.hopon.co.in</p>";

				MessageBoardDTO msgDto = new MessageBoardDTO();
				msgDto.setEmailSubject("HopOn order Notification "
						+ request.getParameter("ORDERID"));
				msgDto.setMessage(html);
				msgDto.setToMember(Integer.parseInt((String) session
						.getAttribute("userId")));
				msgDto.setMessageChannel("E");
				msgDto = ListOfValuesManager.getInsertedMessage(msgDto);

				msgDto.setCreatedBy(Integer.parseInt((String) session
						.getAttribute("userId")));

				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss.S");
				SimpleDateFormat formatter1 = new SimpleDateFormat(
						ApplicationUtil.datePattern4);
				String transactiondate = request.getParameter("TXNDATE");

				java.util.Date date;
				try {
					date = dateFormat.parse(transactiondate);
					payment.setTransactiondate(formatter1.format(date));
				} catch (ParseException e) {

					e.printStackTrace();
				}
				
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("transactionid",
						payment.getTransaction_id());
				httpSession.setAttribute("orderid", payment.getOrder_id());
				httpSession.setAttribute("transferamount",
						payment.getTransferamount());
				httpSession.setAttribute("transactiondate",
						payment.getTransactiondate());
				request.getRequestDispatcher("success.xhtml").include(request,
						response);

			} else {

				String errorMessage = "";
				if (request.getParameter("RESPCODE") != null) {
					if (request.getParameter("RESPCODE").equals("141")) {
						errorMessage = "Sorry! Your payment transaction has been cancelled.";
						PaymentRequestDTO dto = new PaymentRequestDTO();
						dto.setOrder_id(request.getParameter("ORDERID"));

						dto = ListOfValuesManager
								.fetchPaymentRequestByOrderId(dto);
						dto.setStatus("C");
						try {
							ListOfValuesManager
									.updatePaymentRequestEntryStatusByOrderId(dto);
						} catch (ConfigurationException e1) {

						}

						HttpSession httpSession = request.getSession();
						httpSession.setAttribute("transactionid",
								payment.getTransaction_id());
						httpSession.setAttribute("orderid",
								payment.getOrder_id());

						request.getRequestDispatcher("cancel.xhtml").include(
								request, response);

					} else {
						errorMessage = "Sorry! Your payment transaction has failed.";
						PaymentRequestDTO dto = new PaymentRequestDTO();
						dto.setOrder_id(request.getParameter("ORDERID"));

						dto = ListOfValuesManager
								.fetchPaymentRequestByOrderId(dto);
						dto.setStatus("F");
						try {
							ListOfValuesManager
									.updatePaymentRequestEntryStatusByOrderId(dto);
						} catch (ConfigurationException e1) {
						}

						HttpSession httpSession = request.getSession();
						httpSession.setAttribute("transactionid",
								payment.getTransaction_id());
						httpSession.setAttribute("orderid",
								payment.getOrder_id());

						request.getRequestDispatcher("failure.xhtml").include(
								request, response);

					}
				}
			}
		} else {
			// not authenticated.
		}
	}

}
