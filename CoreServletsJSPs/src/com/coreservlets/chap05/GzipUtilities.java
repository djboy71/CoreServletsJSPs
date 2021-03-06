package com.coreservlets.chap05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Three small static utilities to assist with gzip encoding.
 * <UL>
 * 	  <LI>isGzipSupported: does the browser support gzip?
 * 	  <LI>isGzipDisabled: has the user passed in a flag
 * 		  saying that gzip encoding should be disabled for
 * 		  this request?  (Useful so that you can measure
 * 		  results with and without gzip on the same browser).
 * 	  <LI>getGzipWriter: return a gzipping PrintWriter
 */

public class GzipUtilities {

	/** Does the client support gzip? */
	public static boolean isGzipSupported(HttpServletRequest request) {
		
		String encodings = request.getHeader("Accept-Encoding");
		return ((encodings != null) && (encodings.indexOf("gzip") != -1));
		
	} // end of isGzipSupported()
	
	
	/** Has user disable gzip (e.g., for benchmarking)? */
	public static boolean isGzipDisabled(HttpServletRequest request) {
		
		String flag = request.getParameter("disabledGzip");
		return ((flag != null) && (!flag.equalsIgnoreCase("false")));
		
	} // end of isGzipDisabled()
	
	
	/** Return gzipping PrintWriter for response */
	public static PrintWriter getGzipWriter(HttpServletResponse response)
		throws IOException {
		
		return (new PrintWriter(new 
				GZIPOutputStream(response.getOutputStream())));
		
	} // end getGzipWriter()
	
} // end GzipUtilities class
