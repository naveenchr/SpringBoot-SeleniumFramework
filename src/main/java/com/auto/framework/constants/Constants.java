package com.auto.framework.constants;

/************************************************************************************************************************
 * @Date : Oct. 4, 2023
 * @Author : naveenchr
 * @Description : Final class to maintain constants
 * @Version : 1.0
 ************************************************************************************************************************/
public final class Constants {

	private Constants() {
		throw new IllegalStateException("Constants Class");
	}

	public static final String UIELEMENT_ERROR_TEXT = "No element with locator [%s] containing text [%s] found";

//	URLs
	public static final String ELEMENTS_PAGE = "elements";
	public static final String TEXTBOX_PAGE = "text-box";
	public static final String CHECKBOX_PAGE = "checkbox";
	public static final String RADIOBUTTON_PAGE = "radio-button";
	public static final String WEBTABLES_PAGE = "webtables";

	/* API Details */

	public static final String API_PORT = "8081";
	public static final String API_HOST = "localhost";
	public static final String API_BASE_URI = "http://" + API_HOST + ":" + API_PORT;

	public static final String TESTDATA_URI = API_BASE_URI + "/testuserdata";
	
	/* Rest Assured Helper */

	public static final String CONTENT_TYPE = "Content-Type";
	public static final String APPLICATION_JSON = "application/json";
}
