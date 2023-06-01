package io.github.fvarrui.helloworld;

import java.io.IOException;
import java.lang.module.ModuleDescriptor;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.WString;

public class Main {

	private static native NativeLong SetCurrentProcessExplicitAppUserModelID(WString appID);

	static {
	    if (System.getProperty("os.name").startsWith("Windows")) {
	    	Native.register("shell32");
	    }
	}

	public static void main(String[] args) throws IOException {
		
		if (args.length > 0) {
			String argument = args[0];
			switch (argument) {
			case "--version":
				version();
				return;
			case "--module-info":
				moduleInfo();
				return;
			case "--issue-228":
				issue228();
				return;
			case "--issue-320":
			    issue320();
			    break;
			}
		}
		HelloWorldFrame.main(args);
	}

	private static void issue320() {
		if (System.getProperty("os.name").startsWith("Windows")) {
			String appID = Main.class.getName();
			if (SetCurrentProcessExplicitAppUserModelID(new WString(appID)).longValue() != 0) {
				throw new RuntimeException("Unable to set current process explicit AppUserModelID to: " + appID);
			}
		}
	}

	private static void version() {
		System.out.println("HelloWorld 1.0.0");
	}

	private static void moduleInfo() {
		ModuleDescriptor descriptor = Main.class.getModule().getDescriptor();
		if (descriptor != null) {
			System.out.println("Modular version of HelloWorldMaven:");
			System.out.println("- Module name : " + descriptor.name());
			System.out.println("- Requires    : " + descriptor.requires());
			System.out.println("- Exports     : " + descriptor.exports());
		} else {
			System.out.println("Non modular version of HelloWorldMaven!");
		}
	}

	private static void issue228() {
		try {
			String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl=zh-CNdt=t&q="
					+ URLEncoder.encode("word", "UTF-8");
			URL serverUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			conn.setInstanceFollowRedirects(false);
			conn.connect();
		} catch (Exception e) {
			// javax.net.ssl.SSLHandshakeException: Received fatal alert: handshake_failure
			e.printStackTrace();
		}
		System.out.println("Enter any character to end.");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
	}

}
