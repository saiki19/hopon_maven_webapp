package com.hopon.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
		// TODO Auto-generated method stub
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
			
			/*PaymentDTO payment=new PaymentDTO();
			ArrayList<String> parameterValue = new ArrayList<String>();
			payment.setTransaction_id("TXN1234");
			payment.setOrder_id("ORDS1234");
			
			parameterValue.add(payment.getTransaction_id());
			parameterValue.add(payment.getOrder_id());
			
			
			request.setAttribute("list", parameterValue);
			*/
			
			/*
			 * for(String s:parameterNames) { out.print(s + "=>" +
			 * request.getParameter(s)+"<br>"); }
			 */

			/*
			 * 
			 * Fetch ORDER_ID Now change status in payment request table.
			 * 
			 * Now if status is success then update balance in users
			 * table(users->totalCredits). Make this code but for now comment
			 * it.
			 * 
			 * There is slight change. If admin manually changes payment status
			 * "D" (done), then only update balance in users
			 * table(users->totalCredits).
			 */

			if (parameterNames.size() == 0) {

				// retuen error
			} else if (request.getParameter("STATUS") != null
					&& request.getParameter("STATUS").equals("TXN_SUCCESS")) {
				// transaction success

				float amount = Float.parseFloat(request
						.getParameter("TXNAMOUNT"));
				
				float amountNew = (float) ((amount * 2.5) / 100);
				float credit = Math.round(amount - amountNew);

				PaymentRequestDTO dto = new PaymentRequestDTO();
				dto.setOrderId(request.getParameter("ORDERID"));

				dto = ListOfValuesManager.fetchPaymentRequestByOrderId(dto);
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
				
				String htmlOpt ="";
				try {
					htmlOpt = ""+ ApplicationUtil.dateFormat4
							.format(ApplicationUtil.dateFormat20
									.parse(request
											.getParameter("TXNDATE")))+"";
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					pw.println(request.getParameter("TXNDATE")+"final date time"+htmlOpt);
					pw.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'xmlns:h='http://java.sun.com/jsf/html'xmlns:f='http://java.sun.com/jsf/core'xmlns:c='http://java.sun.com/jsp/jstl/core'xmlns:ui='http://java.sun.com/jsf/facelets'xmlns:p='http://primefaces.org/ui'xmlns:pm='http://primefaces.org/mobile' contentType='text/html'renderKitId='PRIMEFACES_MOBILE'><f:view><pm:page><pm:view><ui:include src='http://118.139.161.91/corp/pages/common/headerScript.xhtml'></ui:include><link href='http://118.139.161.91/corp/pages/resources/css/excite-bike/jquery-ui-1.10.4.custom.css'rel='stylesheet' /><link rel='stylesheet'href='//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css' /><h:head></h:head><h:body><ui:include src='http://118.139.161.91/corp/pages/common/headerContent.xhtml'></ui:include><div class='content'><div class='container'><div class='page-header'><center><h2>Thank You! Your payment transaction is successful.</h2></center></div><div class='row'><div class='span6 offset3'><div class='well'><div id='myTabContent' class='tab-content'><div class='tab-pane active in' ><fieldset><div class='control-group'><label class='control-label' for='taxnid'>TransactionID :"
							+ request.getParameter("TXNID")
							+ " </label></div><br /><div class='control-group'><label class='control-label' for='orderid'>OrderID :"
							+ request.getParameter("ORDERID")
							+ "</label></div><br /><div class='control-group'><label class='control-label' for='amount'>Amount:"
							+ request.getParameter("TXNAMOUNT")
							+ " </label></div><br /><div class='control-group'><label class='control-label' for='datetime'>Date and Time : "
							+ htmlOpt
							+ " </label></div><br /><p style='font-size: 17px; text-align: center; font-style: bold; padding: inherit;'><b>Need help? Write to us at <a href='http://www.hopon.co.in/contactus' style='text-decoration: underline'> HopOn Care </a></b</p></fieldset></div></div></div></div></div></div></div><ui:include src='http://118.139.161.91/corp/pages/common/footer-content.xhtml'></ui:include></h:body></pm:view></pm:page></f:view></html>");
				

			} else {

				String errorMessage = "";
				if (request.getParameter("RESPCODE") != null) {
					if (request.getParameter("RESPCODE").equals("141")) {
						errorMessage = "Sorry! Your payment transaction has been cancelled.";
						PaymentRequestDTO dto = new PaymentRequestDTO();
						dto.setOrderId(request.getParameter("ORDERID"));

						dto = ListOfValuesManager
								.fetchPaymentRequestByOrderId(dto);
						dto.setStatus("C");
						try {
							ListOfValuesManager
									.updatePaymentRequestEntryStatusByOrderId(dto);
						} catch (ConfigurationException e1) {

						}
						pw.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml' xmlns:h='http://java.sun.com/jsf/html' xmlns:f='http://java.sun.com/jsf/core' xmlns:c='http://java.sun.com/jsp/jstl/core' xmlns:ui='http://java.sun.com/jsf/facelets' xmlns:p='http://primefaces.org/ui' xmlns:pm='http://primefaces.org/mobile' contentType='text/html' renderKitId='PRIMEFACES_MOBILE'><f:view><pm:page><pm:view><ui:include src='http://118.139.161.91/corp/pages/common/headerScript.xhtml'></ui:include><link href='http://118.139.161.91/corp/pages/resources/css/excite-bike/jquery-ui-1.10.4.custom.css' rel='stylesheet' /><link rel='stylesheet' href='//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css' /><h:head>"
								+ "<META http-equiv='refresh' content='10';URL='http://118.139.161.91/corp/pages/settings/master-gr-my-msg.xhtml'></h:head><h:body><ui:include src='http://118.139.161.91/corp/pages/common/headerContent.xhtml'></ui:include><div class='content'><div class='container'><div class='page-header'><center><h2>Sorry! Your payment transaction has been cancelled.</h2></center></div><div class='row'><div class='span6 offset3'><div class='well'><div id='myTabContent' class='tab-content'><div class='tab-pane active in'><fieldset><div class='control-group'><label class='control-label' for='taxnid'>TransactionID : "
								+ request.getParameter("TXNID")
								+ "</label></div><br /><div class='control-group'><label class='control-label' for='orderid'>Order ID: "
								+ request.getParameter("ORDERID")
								+ "</label></div><br /><p>Please try doing your transaction again. Need help? Write to us at <a href='http://www.hopon.co.in/contactus'style='text-decoration: underline'> HopOn Care </a></p></fieldset></div></div></div></div></div></div></div><ui:include src='http://118.139.161.91/corp/pages/common/footer-content.xhtml'></ui:include></h:body></pm:view></pm:page></f:view></html>");
					
						
						/*String htmlOpt ="";
						try {
							htmlOpt = ""+ ApplicationUtil.dateFormat4
									.format(ApplicationUtil.dateFormat20
											.parse(request
													.getParameter("TXNDATE")))+"";
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						pw.println("<html>List of parameterValue:"+parameterValue+"</html>");
						pw.println("<html>Date and time:"+htmlOpt+"</html>");
						
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
/*						response.sendRedirect("http://118.139.161.91/corp/settings/master-gr-my-msg.xhtml");
*/
/*						request.getRequestDispatcher("cancel.xhtml").include(request, response);
*/						
					} else {
						errorMessage = "Sorry! Your payment transaction has failed.";
						PaymentRequestDTO dto = new PaymentRequestDTO();
						dto.setOrderId(request.getParameter("ORDERID"));

						dto = ListOfValuesManager
								.fetchPaymentRequestByOrderId(dto);
						dto.setStatus("F");
						try {
							ListOfValuesManager
									.updatePaymentRequestEntryStatusByOrderId(dto);
						} catch (ConfigurationException e1) {
						}
						/*String htmlOpt ="";
						try {
							htmlOpt = ""+ ApplicationUtil.dateFormat4
									.format(ApplicationUtil.dateFormat20
											.parse(request
													.getParameter("TXNDATE")))+"";
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/
						pw.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'xmlns:h='http://java.sun.com/jsf/html'xmlns:f='http://java.sun.com/jsf/core'xmlns:c='http://java.sun.com/jsp/jstl/core'xmlns:ui=http://java.sun.com/jsf/facelets'xmlns:p='http://primefaces.org/ui'xmlns:pm='http://primefaces.org/mobile' contentType='text/html'renderKitId='PRIMEFACES_MOBILE'><f:view><pm:page><pm:view><ui:include src='http://118.139.161.91/corp/pages/common/headerScript.xhtml'></ui:include><link href='http://118.139.161.91/corp/pages/resources/css/excite-bike/jquery-ui-1.10.4.custom.css'rel='stylesheet' /><link rel='stylesheet'href='//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css' /><h:head></h:head><h:body><ui:include src='http://118.139.161.91/corp/pages/common/headerContent.xhtml'></ui:include><div class='content'><div class='container'><div class='page-header'><center><h2>Sorry! Your payment transaction has failed.</h2></center></div><div class='row'><div class='span6 offset3'><div class='well'><div id='myTabContent' class='tab-content'><div class='tab-pane active in' ><fieldset><div class='control-group'><label class='control-label' for='taxnid'>TransactionID :"
								+ request.getParameter("TXNID")
								+ " </label></div><br /><div class='control-group'><label class='control-label' for='orderid'>Order ID: "
								+ request.getParameter("ORDERID")
								+ "</label></div><br />	<p>Please try doing your transaction again. Need help? Write to us at <a href='http://www.hopon.co.in/contactus' style='text-decoration: underline'> HopOn Care </a></p></fieldset></div></div>	</div></div></div></div></div><ui:include src='http://118.139.161.91/corp/pages/common/footer-content.xhtml'></ui:include></h:body></pm:view></pm:page></f:view></html>");
						
						/*pw.println("<html>Date and time:"+htmlOpt+"</html>");*/
						/*try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						response.sendRedirect("http://118.139.161.91/corp/pages/settings/master-gr-my-msg.xhtml");
*/						/*request.getRequestDispatcher("failure.xhtml").include(request, response);*/

					}
				}
			}
		} else {
			// not authenticated.
		}
	}

}
