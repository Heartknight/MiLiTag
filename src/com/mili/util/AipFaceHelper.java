package com.mili.util;

import com.baidu.aip.face.AipFace;

public class AipFaceHelper {
	// 设置APPID/AK/SK
	private static final String APP_ID = "15831764";
	private static final String API_KEY = "pB0AZQqLB3jxjpkCeTnF4N1I";
	private static final String SECRET_KEY = "ZC9TcKdf9pdVpB6cuq8ti5ISZmTW9FzZ";
	private static AipFace client = null;

	private AipFaceHelper() {
	}

	public static AipFace getInstance() {
		if (client == null) {
			client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);
		}
		return client;
	}

}
