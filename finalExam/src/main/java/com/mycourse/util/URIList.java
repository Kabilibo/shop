package com.mycourse.util;

import java.util.ArrayList;

public final class URIList {

	private static ArrayList<String> buyerURI = new ArrayList<String>();
	private static ArrayList<String> sellerURI = new ArrayList<String>();
	
	public static ArrayList<String> getBuyerURI() {
		buyerURI.add("/account");
		return buyerURI;
	}
	public static ArrayList<String> getSellerURI() {
		sellerURI.add("/public");
		sellerURI.add("/publicSubmit");
		sellerURI.add("/edit");
		sellerURI.add("/editSubmit");
		return sellerURI;
	}
	
}
