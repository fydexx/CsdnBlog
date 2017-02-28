package com.free.blog.domain.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtils {

	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm",
				Locale.CHINA);
		return sdf.format(new java.util.Date());
	}
}
