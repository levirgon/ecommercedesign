package com.tutexpsoft.ecommercedev.utils;

/**
 * Created by noushad on 12/10/17.
 */

public class URLManager {

    public static final String BASE_URL = "https://www.sectorbazar.com/wp-json/wc/v2/";
    public static final String SECRET_KEY = "consumer_key=ck_613ebcda090fbb06efc22bd1ba73a4116f58e7ea&" +
            "consumer_secret=cs_e47fd0a6fc9b54042b1201246ddefe77ecb90630";


    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }
}
