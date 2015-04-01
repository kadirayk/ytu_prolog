import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Run {

	public static void main(String[] args) {


		System.out.println("works...");
		
		List<String> ipAdresses;
		List<String> rawDatas = new ArrayList<String>();
		List<String> ipBlocks = new ArrayList<String>();
		List<String> orgNames = new ArrayList<String>();
		
		
		ipAdresses = getIPAdressesFromFile("data/ipadres.txt");
		
		
		for(int i=0;i<ipAdresses.size();i++){
			rawDatas.add(getRawDataFromIPAdress(ipAdresses.get(i)));
		}
		
		for(int i=0; i<rawDatas.size(); i++){
			ipBlocks.add(getIpBlock(rawDatas.get(i), ipAdresses.get(i)));
			orgNames.add(getOrganizationName(rawDatas.get(i)));
			System.out.println(ipAdresses.get(i));
		}
		
		
		writeIpBlocksToFile(ipAdresses, ipBlocks, orgNames);

	}
	
	
	/*
	 * 
	 * 
	 * 
	 * */
	
	
	/*
	 * read file containing ip adresses
	 */
	
	public static List<String> getIPAdressesFromFile(String fileURL){
		
		List<String> ipAdresses;
		
		ipAdresses = new ArrayList<String>();
		
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(fileURL);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		try {
			while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  //System.out.println (strLine);
			  ipAdresses.add(strLine);
			  
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//Close the input stream
		try {
			br.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return ipAdresses;
	}
	
	
	/*
	 * 
	 * 
	 */
	public static String getRawDataFromIPAdress(String ipAdress){
		
		Document doc = null;
		
		
		System.setProperty("http.proxyHost", "192.168.249.10"); // set proxy server 46.31.112.92
		System.setProperty("http.proxyPort", "8080");
		
		ipAdress = ipAdress.trim();
		
		try {
			doc = Jsoup.connect("http://who.is/whois-ip/ip-address/" + ipAdress)
					.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.timeout(5000).get();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Elements attr = doc.getElementsByAttribute("data-bind-ip");
		
		String rawString = null;
		
		for(Element e: attr){
			rawString= e.text();
		}
		
		return rawString;
	}
	
	public static String getIpBlock(String rawData, String IpAdress){
		String IpBlock = null;
		
		if(rawData==null){
			return "null";
		}
		
		int beginIndexNoIP = 0;
		int endIndexNoIP = 0;
		
		if(rawData != null && rawData.contains(")")){
			beginIndexNoIP = rawData.indexOf(")");
			endIndexNoIP = rawData.indexOf("\n");
		}
		
		
		int indexVultr;
		int beginIndex = 0;
		int endIndex = 0;
		if(rawData != null && rawData.contains("inetnum")){
			beginIndex = rawData.indexOf("inetnum:");
			endIndex = rawData.indexOf("netname:");
		}
		
		beginIndex = beginIndex + 16;
		
		
		int beginIndexNetRange = 0;
		int endIndexNetRange = 0;
		
		if(rawData != null && rawData.contains("NetRange:")){
			beginIndexNetRange = rawData.indexOf("NetRange:");
			endIndexNetRange = rawData.indexOf("CIDR:");
		}
		
		beginIndexNetRange = beginIndexNetRange + 7;
		
		if(beginIndex>0 && endIndex>0){
			
			IpBlock = rawData.substring(beginIndex, endIndex);
			
		}else if(beginIndexNetRange>0 && endIndexNetRange>0){
			
			IpBlock = rawData.substring(beginIndexNetRange, endIndexNetRange);
		
		}else if(rawData.contains("Vultr")){
			//return "no ip block: " + IpAdress;
//			IpBlock = "no ip" + rawData.substring(beginIndexNoIP, beginIndexNoIP) + "ip : " + IpAdress;
			indexVultr = rawData.indexOf("Vultr");
			if(indexVultr<32){
				IpBlock = rawData;
				return IpBlock;
			}
			IpBlock = rawData.substring(indexVultr-32, indexVultr);
			if(IpBlock.contains(")")){
				int ndx = IpBlock.indexOf(")");
				IpBlock = IpBlock.substring(ndx+1, IpBlock.length());
			}
		}else if(rawData.contains("GameServers")){
			//return "no ip block: " + IpAdress;
//			IpBlock = "no ip" + rawData.substring(beginIndexNoIP, beginIndexNoIP) + "ip : " + IpAdress;
			indexVultr = rawData.indexOf("GameServers");
			IpBlock = rawData.substring(indexVultr-32, indexVultr);
			if(IpBlock.contains(")")){
				int ndx = IpBlock.indexOf(")");
				IpBlock = IpBlock.substring(ndx+1, IpBlock.length());
			}
		}	
//		}else if(rawData.contains("Atlantic.net")){
//			indexVultr = rawData.indexOf("Atlantic.net", rawData.indexOf("Atlantic.net") + 1);
//			IpBlock = rawData.substring(indexVultr-32, indexVultr);
//			if(IpBlock.contains(")")){
//				int ndx = IpBlock.indexOf(")");
//				IpBlock = IpBlock.substring(ndx+1, IpBlock.length());
//			}
//		}		
		else if(rawData.contains("Choopa CDN NET")){
			//return "no ip block: " + IpAdress;
//			IpBlock = "no ip" + rawData.substring(beginIndexNoIP, beginIndexNoIP) + "ip : " + IpAdress;
			indexVultr = rawData.indexOf("Choopa CDN NET");
			IpBlock = rawData.substring(indexVultr-32, indexVultr);
			if(IpBlock.contains(")")){
				int ndx = IpBlock.indexOf(")");
				IpBlock = IpBlock.substring(ndx+1, IpBlock.length());
			}
		
		}else if(rawData.contains("Choopa LLC NET")){
			indexVultr = rawData.indexOf("Choopa LLC NET");
			IpBlock = rawData.substring(indexVultr-32, indexVultr);
			if(IpBlock.contains(")")){
				int ndx = IpBlock.indexOf(")");
				IpBlock = IpBlock.substring(ndx+1, IpBlock.length());
			}			
		}else if(rawData.contains("London Trust")){
			indexVultr = rawData.indexOf("London Trust");
			IpBlock = rawData.substring(indexVultr-32, indexVultr);
			if(IpBlock.contains(")")){
				int ndx = IpBlock.indexOf(")");
				IpBlock = IpBlock.substring(ndx+1, IpBlock.length());
			}			
		}else if(beginIndexNoIP>0 && endIndexNoIP>0){
			//return "no ip block: " + IpAdress;
//			IpBlock = "no ip" + rawData.substring(beginIndexNoIP, beginIndexNoIP) + "ip : " + IpAdress;
			return rawData;
		}else{
			return rawData;
		}
		
		
		
//		Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
//		Matcher m = p.matcher(rawData);
//		while (m.find()) {
//		  IpBlock = m.group(1);
//		}
		
		return IpBlock;
	}

	
	public static String getOrganizationName(String rawData){
		
		String organizationName = null;
		
	//	int beginIndexNoIP = rawData.indexOf("Vultr");
		
		if(rawData==null){
			return "";
		}
		
		int beginIndex = rawData.indexOf("netname:");
		int endIndex = rawData.indexOf("descr:");
		beginIndex = beginIndex + 10;
		
		
		int beginIndexOrgName = rawData.indexOf("OrgName:");
		int endIndexOrgName = rawData.indexOf("OrgId:");
		beginIndexOrgName = beginIndexOrgName + 3;
		
		if(beginIndex>0 && endIndex>0){
			
			organizationName = rawData.substring(beginIndex, endIndex);
			
		}else if(beginIndexOrgName>0 && endIndexOrgName>0){
			
			organizationName = rawData.substring(beginIndexOrgName, endIndexOrgName);
		
		}else if(rawData.contains("Vultr")){
			//return "no ip block: " + IpAdress;
//			IpBlock = "no ip" + rawData.substring(beginIndexNoIP, beginIndexNoIP) + "ip : " + IpAdress;
			organizationName = "Chopa LCC, Vultr Holdings";
		
		}else if(rawData.contains("GameServer")){
			organizationName = "Chopa LCC, GameServers.com";
		}else if(rawData.contains("London Trust")){
			organizationName = "Choopa, London Trust Media Inc";
		}else if(rawData.contains("Choopa")){
			organizationName = "Choopa, LCC CHOOPA";
		}else if(rawData.contains("Atlantic.net")){
			organizationName = "Atlantic.net, Inc.";
		}
		else{
			return rawData;
		}
		
		return organizationName;
	}
	
	public static void writeIpBlocksToFile(List<String> IpAdresses , List<String> IpBlocks, List<String> orgNames){
		
		
		String htmlbegin = "<!DOCTYPE html><html><body>";
		String htmlend = "</body></html>";
		String tablebegin ="<table border=\"1\">";
		String tableend = "</table>";
		
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("data/result_ipblocks.html", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.print(htmlbegin);
		writer.print(tablebegin);
		
		for(int i = 0; i<IpBlocks.size(); i++){
			writer.println("<tr><td>"+ IpAdresses.get(i) + "</td>" + "<td>" + IpBlocks.get(i) + "</td>" + "<td>" + orgNames.get(i) + "</td></tr>");

		}
		
		writer.print(tableend);
		writer.println(htmlend);
		
		writer.close();
		
	}
	
}
