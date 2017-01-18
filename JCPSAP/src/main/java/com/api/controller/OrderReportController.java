package com.api.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;





import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.utils.XLS2HTMLParser;

@RestController
public class OrderReportController {
	
	private XLS2HTMLParser parser = new XLS2HTMLParser();
	
	@RequestMapping("/orderReport")
    @ResponseBody
	public String current() {
		Date yourDate = new Date();

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DDMMYYDD_HH");
		String date = DATE_FORMAT.format(yourDate);
	
		return getOrderreport(date);
	}
	
	@RequestMapping(value="/orderReport/prev/{date}/{hour}", method = RequestMethod.GET )
    @ResponseBody
	public String previous(@PathVariable("date") String date,@PathVariable("hour") String hour) {
		Date yourDate = new Date();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMYY");
		String formatdate = DATE_FORMAT.format(yourDate);
	
		return getOrderreport(date+formatdate+date+"_"+hour);
	}
	
	public String getOrderreport(String date) {
		
		try {
			 return parser.parse(new File("C:\\Reporting\\Peak\\Order\\OrderReport_" + date  + ".xlsx"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}

