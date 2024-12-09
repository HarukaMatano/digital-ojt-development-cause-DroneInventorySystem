package com.digitalojt.web.util;

import java.util.ResourceBundle;

public class MessageUtil 
{
	private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public static String getMessage(String key) {
        return bundle.getString(key);
    }

}
