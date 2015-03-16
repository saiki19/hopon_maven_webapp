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
@WebServlet("/RoutePreview")
public class RoutePreview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("output");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		System.out.println("Response:" + response.getContentType());
		String passengers = request.getParameter("passengers");
		String route = request.getParameter("route");
		String destination = request.getParameter("destination");
		String drivername  =  request.getParameter("drivername");
		String drivercontact = request.getParameter("drivercontact");
		String cabregno = request.getParameter("cabregno");
		String vendor = request.getParameter("vendor");
		String cabid = request.getParameter("cabid");
		System.out.println(passengers);
		System.out.println(route);
		System.out.println(destination);
		System.out.println(drivername);
		System.out.println(drivercontact);
		System.out.println(cabregno);
		System.out.println(vendor);
		System.out.println(cabid);
		
	//	out.print("<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'><head><title>HopOn Route Preview</title><meta http-equiv='content-type' content='text/html;charset=utf-8' /><meta name='generator' content='Geany 1.23.1' /><link href='https://api.tiles.mapbox.com/mapbox.js/v2.1.4/mapbox.css' rel='stylesheet' /><link href='./resources/css/mapStyle.css' rel='stylesheet'/><link href='./resources/css/bootstrap.css' rel='stylesheet'/><script src='https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script src='https://api.tiles.mapbox.com/mapbox.js/v2.1.4/mapbox.js'></script><script type='text/javascript' src='./resources/js/bootstrap.min.js'></script> <script type='text/javascript' src='./resources/js/transition.js'></script><style>body { margin:0; padding:0; } #map { position:absolute; top:0; bottom:0; width:100%; }{ position:absolute; top:0; bottom:0; width:100%; }</style></head><body><div id='map'><div class='modal fade' id='myModal' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'> <div class='modal-dialog on-top'> <div class='modal-content'> <div class='modal-header'> <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <h4 class='modal-title' id='myModalLabel'>Driver Details</h4> </div> <div class='modal-body'> <div class='row'><div class='col-md-6 col-sm-6 col-xs-6'> <h3>DRIVER NAME</h3></div> <div class='col-md-6 col-sm-6 col-xs-6'> <h3 id='drivername'></h3></div></div> <div class='row'><div class='col-md-6 col-sm-6 col-xs-6'> <h3>VENDOR</h3></div> <div class='col-md-6 col-sm-6 col-xs-6'> <h3 id='vendor'></h3></div></div> <div class='row'><div class='col-md-6 col-sm-6 col-xs-6'> <h3>CONTACT</h3></div> <div class='col-md-6 col-sm-6 col-xs-6'> <h3 id='contact'></h3></div></div> <div class='row'><div class='col-md-6 col-sm-6 col-xs-6'> <h3>VEHICLE REG.No.</h3></div> <div class='col-md-6 col-sm-6 col-xs-6'> <h3 id='regno'></h3></div></div> </div> <div class='modal-footer'> <button type='button' class='btn' data-dismiss='modal'>Close</button> </div> </div> </div></div><div class='logo'><img src='./resources/img/Hopon_logo_w.png'></div><button type='button' class='cab_icon on-top bottom-right' data-toggle='modal' data-target='#myModal'><img src='./resources/img/driverdetails_whiteBG.png'></button></button></div><script>L.mapbox.accessToken = 'pk.eyJ1Ijoic2Fpa2kxOSIsImEiOiJuNmJHWFBnIn0.ZpwTBmfVCliisoYv0Ga1dw'; var map = L.mapbox.map('map', 'saiki19.ke41f9nb',{zoomControl:false}); map.setView([12.973, 77.571], 12); new L.Control.Zoom({position:'topright'}).addTo(map); var routeOptions={color: '#11B609',opacity:1,weight:10,fillColor: '#11B609',fillOpacity: 0.7}; var cabCurrentLocation = new Array(0,0); var cabMarkerFeature = { 'properties': {'title': 'cab', 'description':'Harish', 'marker-color': '#000', 'marker-size':'medium','marker-symbol':'car'}}; var cabMarker = L.marker(cabCurrentLocation, {title:'cab',icon: L.mapbox.marker.icon(cabMarkerFeature.properties)});cabMarker.addTo(map); var i=1; function run(){console.log (i); var cab_id = 'KA1234_196'; var apiQuery= 'https://www.hopon.co.in/tracking/api/index.php/cablocation?callback=geoloc&cabid='+cab_id; $(document).ready(function(){ $.ajax({ url: apiQuery, dataType: 'jsonp', success: function(data){var cabCurrentLocation = new Array(data.location[0],data.location[1]); var speed = data.vehSpeed; console.log(cabCurrentLocation); console.log(speed); cabMarker.setLatLng(cabCurrentLocation);}});}); i=i+1; window.setTimeout(run,2000);} run();</script></body></html>");
	//out.print("<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'><head><title>HopOn Route Preview</title><meta http-equiv='content-type' content='text/html;charset=utf-8' /><meta name='generator' content='Geany 1.23.1' /><link href='https://api.tiles.mapbox.com/mapbox.js/v2.1.4/mapbox.css' rel='stylesheet' /><link href='./resources/css/mapStyle.css' rel='stylesheet'/><link href='./resources/css/bootstrap.css' rel='stylesheet'/><script src='https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script src='https://api.tiles.mapbox.com/mapbox.js/v2.1.4/mapbox.js'></script><script type='text/javascript' src='./resources/js/bootstrap.min.js'></script> <script type='text/javascript' src='./resources/js/transition.js'></script><style>body { margin:0; padding:0; } #map { position:absolute; top:0; bottom:0; width:100%; }{ position:absolute; top:0; bottom:0; width:100%; }</style></head><body><div id='map'><div class='logo'><img src='./resources/img/Hopon_logo_w.png'></div><button type='button' class='cab_icon on-top bottom-right' data-toggle='modal' data-target='#myModal'><img src='./resources/img/driverdetails_whiteBG.png'></button></button></div><script>L.mapbox.accessToken = 'pk.eyJ1Ijoic2Fpa2kxOSIsImEiOiJuNmJHWFBnIn0.ZpwTBmfVCliisoYv0Ga1dw'; var map = L.mapbox.map('map', 'saiki19.ke41f9nb',{zoomControl:false}); map.setView([12.973, 77.571], 12); new L.Control.Zoom({position:'topright'}).addTo(map);var passengers="+passengers+";var destination="+destination+";var routeArray ="+route+";var cab_id ="+cabid+";var markersGeoJSON=passengers;var markerdestinationGeoJSON= destination;var passengersLayer = L.mapbox.featureLayer().addTo(map);passengersLayer.setGeoJSON(markersGeoJSON);var destinationLayer = L.mapbox.featureLayer().addTo(map);destinationLayer.setGeoJSON(markerdestinationGeoJSON);var routeOptions={color: '#11B609',opacity:1,weight:10,fillColor: '#11B609',fillOpacity: 0.7};var routePreview = L.polyline(routeArray,routeOptions).addTo(map);var cabCurrentLocation = new Array(0,0);var cabMarkerFeature = { 'properties': {'title': 'cab', 'description':'Harish', 'marker-color': '#000', 'marker-size':'medium','marker-symbol':'car'}};var cabMarker = L.marker(cabCurrentLocation, {title:'cab',icon: L.mapbox.marker.icon(cabMarkerFeature.properties)});cabMarker.addTo(map);var i=1; function run(){console.log (i);var cab_id = cab_id;var apiQuery= 'https://www.hopon.co.in/tracking/api/index.php/cablocation?callback=geoloc&cabid='+cab_id;$(document).ready(function(){ $.ajax({ url: apiQuery, dataType: 'jsonp', success: function(data){var cabCurrentLocation = new Array(data.location[0],data.location[1]); var speed = data.vehSpeed; console.log(cabCurrentLocation);console.log(speed); cabMarker.setLatLng(cabCurrentLocation);}});}); i=i+1;window.setTimeout(run,2000);}run();</script><script>alert(drivername);</script></body></html>");
	out.print("<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'><head><title>HopOn Route Preview</title><meta http-equiv='content-type' content='text/html;charset=utf-8' /><meta name='generator' content='Geany 1.23.1' /><link href='https://api.tiles.mapbox.com/mapbox.js/v2.1.4/mapbox.css' rel='stylesheet' /><link href='./resources/css/mapStyle.css' rel='stylesheet'/><script src='https://api.tiles.mapbox.com/mapbox.js/v2.1.4/mapbox.js'></script><script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js'></script><style>body { margin:0; padding:0; } #map { position:absolute; top:0; bottom:0; width:100%; }</style></head><body><div id='map'><div class='logo'><img src='./resources/img/Hopon_logo_w.png'></div></div><script>L.mapbox.accessToken = 'pk.eyJ1Ijoic2Fpa2kxOSIsImEiOiJuNmJHWFBnIn0.ZpwTBmfVCliisoYv0Ga1dw'; var map = L.mapbox.map('map', 'saiki19.ke41f9nb',{zoomControl:false}); map.setView([12.973, 77.571], 12); new L.Control.Zoom({position:'topright'}).addTo(map);var passengers="+passengers+";var destination="+destination+";var routeArray ="+route+";var cab_id ='"+cabid+"';var markersGeoJSON=passengers;var markerdestinationGeoJSON= destination;var passengersLayer = L.mapbox.featureLayer().addTo(map);passengersLayer.setGeoJSON(markersGeoJSON);var destinationLayer = L.mapbox.featureLayer().addTo(map);destinationLayer.setGeoJSON(markerdestinationGeoJSON);var routeOptions={color: '#11B609',opacity:1,weight:10,fillColor: '#11B609',fillOpacity: 0.7};var routePreview = L.polyline(routeArray,routeOptions).addTo(map);var cabCurrentLocation = new Array(0,0); var cabMarkerFeature = { 'properties': {'title': 'cab', 'description':'Harish', 'marker-color': '#000', 'marker-size':'medium','marker-symbol':'car'}};var cabMarker = L.marker(cabCurrentLocation, {title:'cab',icon: L.mapbox.marker.icon(cabMarkerFeature.properties)});cabMarker.addTo(map);var i=1;function run(){console.log (i);var $cab_id = cab_id;  var apiQuery='https://www.hopon.co.in/tracking/api/index.php/cablocation?callback=geoloc&cabid='+$cab_id;$(document).ready(function(){ $.ajax({ url: apiQuery, dataType: 'jsonp', success: function(data){var cabCurrentLocation = new Array(data.location[0],data.location[1]); var speed = data.vehSpeed; console.log(cabCurrentLocation);console.log(speed); cabMarker.setLatLng(cabCurrentLocation); } }); }); i=i+1;window.setTimeout(run,2000);} run();</script></body></html>");
	}

}

