
package com.scb.pttfx.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.prowidesoftware.swift.io.parser.SwiftParser;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.mt.mt3xx.MT300;

/**
 * The Class SwiftMT300Parser.
 */
public class SwiftMT300Parser {

	/** The Constant logger. */
	private static final Logger LOGGER = Logger.getLogger(SwiftMT300Parser.class);

	
	/**
	 * @param arg
	 */
	public static void main(String[] arg) {
		List<MT300> mt300List = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
				mt300List = parseRawMT300(arg[0]+"RAWMT300");
				if(mt300List.size() != 0){
					for (MT300 mt300Obj : mt300List) {
						System.out.println(" Field 20 : "+mt300Obj.getField20().getValue());
						System.out.println(" Field 22A : "+mt300Obj.getField22A().getValue());
						System.out.println(" Field 82A : "+mt300Obj.getField82A().getValue());
						System.out.println(" Field 87A : "+mt300Obj.getField87A().getValue());
						System.out.println(" Field 30T : "+format.parse(mt300Obj.getField30T().getValue()));
						System.out.println(" Field 30V : "+format.parse(mt300Obj.getField30V().getValue()));
						System.out.println(" Field 36 : "+new BigDecimal(mt300Obj.getField36().getValue().replace(",", ".")));
						System.out.println(" Field 32B : "+mt300Obj.getSwiftMessage().getBlock4().getFieldByName("32B").getValue());
						System.out.println(" Field 33B Currency : "+mt300Obj.getField33B().getCurrency());
						System.out.println(" Field 33B Amount : "+new BigDecimal(mt300Obj.getField33B().getAmount()));
					}
				}
		} catch (Exception e) {
			LOGGER.error("FXParser parseMT300Messages: Exception  " + e.getMessage());
			e.printStackTrace();	
		}
	}

	
	/**
	 * @param rawMT300Str
	 * @return
	 */
	public static List<MT300> parseRawMT300(String rawMT300Str) {

		List<MT300> mt300List = null;
		SwiftParser parser = null;
		SwiftMessage swiftMsg = null;
		try {
			mt300List = new ArrayList<MT300>();
			// Pass raw MT300 message to create tokenizer
			StringTokenizer st = new StringTokenizer(rawMT300Str, "-");
			while (st.hasMoreTokens()) {
				String mt300Str = st.nextToken();
				// Read each token and populate MT300 object using SwiftParser
				if (mt300Str.contains("{1:")) {
					parser = new SwiftParser(mt300Str);
					swiftMsg = parser.message();
					MT300 mt300 = new MT300(swiftMsg);
					mt300List.add(mt300);
				}
			}
		} catch (Exception e) {
			LOGGER.error("parseRawMT300 : Exception  " + e.getMessage());
			e.printStackTrace();
		}
		return mt300List;
	}

}
