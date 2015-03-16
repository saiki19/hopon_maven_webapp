package com.hopon.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.collect.Multiset.Entry;
import com.hopon.dto.HoponAccountDTO;
import com.hopon.dto.PaymentRequestDTO;
import com.hopon.utils.ConfigurationException;
import com.hopon.utils.ListOfValuesManager;
import com.hopon.utils.LoggerSingleton;
import com.hopon.utils.Validator;

/**
 * Servlet implementation class PurchaseCredit
 */
@WebServlet("/PurchaseCredit")
public class PurchaseCredit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String userId = (String) session.getAttribute("userId");

		if (session != null && !Validator.isEmpty(userId)) {
			float amount = 0;
			String amtStr = request.getParameter("amount");
			amount = Float.parseFloat(amtStr);
			if (amount > 0) {
				Connection con = ListOfValuesManager.getLocalConnection();
				boolean rollbackTest = false;
				String orderId = "ORDS"
						+ Math.round((Math.random() * 100000000));
				try{
					
				// Add record into payment request table.

				PaymentRequestDTO dto = new PaymentRequestDTO();
				dto.setUserId(Integer.parseInt(userId));
				dto.setOrder_id(orderId);
				dto.setAmount(amount);
				dto.setStatus("I");
				dto.setCreditDebit("credit");
				dto.setCreatedBy(Integer.parseInt(userId));
				try {
					dto = ListOfValuesManager.addPaymentRequestEntry(con,dto);
				} catch (ConfigurationException e1) {
					response.sendRedirect("/");
				}
				
				HoponAccountDTO hoponAccountDTO=new HoponAccountDTO();
				int id = 101;
				
				hoponAccountDTO=ListOfValuesManager.fetchHoponAccountBalancebyId(hoponAccountDTO,id);	
				float hoponAccount_balance=hoponAccountDTO.getBalance();
				hoponAccount_balance=hoponAccount_balance+dto.getAmount();
				hoponAccountDTO.setBalance(hoponAccount_balance);
				
				ListOfValuesManager.updateHoponAccountBalanceById(con,hoponAccountDTO,id);
				}catch (Exception e) {
					LoggerSingleton.getInstance().error(
							e.getStackTrace()[0].getClassName() + "->"
									+ e.getStackTrace()[0].getMethodName() + "() : "
									+ e.getStackTrace()[0].getLineNumber() + " :: "
									+ e.getMessage());
					rollbackTest = true;
				}finally {
					if (rollbackTest) {
						try {
							con.rollback();
							response.sendRedirect("/");
							
						} catch (SQLException e) {
							LoggerSingleton.getInstance().error(
									e.getStackTrace()[0].getClassName() + "->"
											+ e.getStackTrace()[0].getMethodName()
											+ "() : "
											+ e.getStackTrace()[0].getLineNumber()
											+ " :: " + e.getMessage());
						}
						ListOfValuesManager.releaseConnection(con);
					} else {
						try {
							con.commit();
							
							// redirecting to payment gateway
							com.paytm.merchant.CheckSumServiceHelper checkSumServiceHelper = com.paytm.merchant.CheckSumServiceHelper
									.getCheckSumServiceHelper();

							ResourceBundle bundle = ResourceBundle
									.getBundle("resource.paytm");

							TreeMap<String, String> parameters = new TreeMap<String, String>();
							String merchantKey = bundle.getString("paytm.merchantKey"); // Key
																						// provided
																						// by
																						// Paytm
							parameters.put("MID", bundle.getString("paytm.MID")); // Merchant
																					// ID
																					// (MID)
																					// provided
																					// by
																					// Paytm
							parameters.put("ORDER_ID", orderId); // Merchant’s order id
							parameters.put("CUST_ID",
									"CUST" + session.getAttribute("userId")); // Customer ID
																				// registered
																				// with
																				// merchant
							parameters.put("TXN_AMOUNT", String.valueOf(amount));

							parameters.put("CHANNEL_ID",
									bundle.getString("paytm.CHANNEL_ID"));
							parameters.put("INDUSTRY_TYPE_ID",
									bundle.getString("paytm.INDUSTRY_TYPE_ID")); // Provided
																					// by
																					// Paytm
							parameters.put("WEBSITE", bundle.getString("paytm.WEBSITE")); // Provided
																							// by
																							// Paytm

							String checkSum = "";
							// Note: Above mentioned parameters are not complete list of
							// parameters. Please refer integration document for additional
							// parameters which need to be passed.
							try {
								checkSum = checkSumServiceHelper.genrateCheckSum(
										merchantKey, parameters);
							} catch (Exception e) {
								
								e.printStackTrace();
							}

							out.print("<html><head><title>Merchant Check Out Page</title></head><body><center><h1>Please do not refresh this page...</h1></center>");
							out.print("<form method='post' action='"+bundle.getString("paytm.PAYTM_TXN_URL")+"' name='f1'>");
							// out.print("<form method='post' action='http://localhost/hopdwld/captureImage/pgRedirect' name='f1'>");
							out.print("<table><tbody>");

							for (java.util.Map.Entry<String, String> entry : parameters
									.entrySet()) {
								String key = entry.getKey();
								String value = entry.getValue();
								out.print("<input type='hidden' name='" + key + "' value='"
										+ value + "'>");
							}
							out.print("<input type='hidden' name='CHECKSUMHASH' value='"
									+ checkSum + "'>");
							out.print("</tbody></table><script type='text/javascript'>document.f1.submit();</script></form></body></html>");
						
						}
						 catch (SQLException e) {
							LoggerSingleton.getInstance().error(
									e.getStackTrace()[0].getClassName() + "->"
											+ e.getStackTrace()[0].getMethodName()
											+ "() : "
											+ e.getStackTrace()[0].getLineNumber()
											+ " :: " + e.getMessage());
						}
						ListOfValuesManager.releaseConnection(con);
					}
					rollbackTest = false;
				}
				
			}		
		} else {
			response.sendRedirect("/");
		}
	}

}
