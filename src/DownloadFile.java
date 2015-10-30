import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;

import org.lang.storage.google.GoogleAuth;
import org.lang.storage.google.GoogleDriveStorage;


public class DownloadFile {
	public static void main(String argv[]) {
//		if (argv.length != 2) {
//			System.out.println("Usage: DownloadFile <file-path> <central-folder>");
//			System.exit(1);
//		}
		
//		Properties config = DemoUtils.readProps();
//		String accessToken = config.getProperty("access_token");
//		String rootId = config.getProperty("root_id");
//		String email = config.getProperty("central_drive_email");

		String clientId = ""; // "(digits)-(alphanumerics).apps.googleusercontent.com";
	    String clientSecret = ""; // "(alphanumerics);"
	    String refreshToken = ""; // "1/(alphanumerics)"
	    GoogleAuth auth = new GoogleAuth(clientId, clientSecret);
	    if (auth.refreshAccessToken(refreshToken)) {
	      System.out.println("Access token: " + auth.getAccessToken());
	      //config.put("access_token", auth.getAccessToken());
	      //DemoUtils.writeProps(config);
	    }
				
	    String accessToken = auth.getAccessToken();
	    String path = "C:/Users/l/Downloads/test3.csv";//argv[0]
	    String centralFolder = "buf";//argv[1]
		GoogleDriveStorage gd;
		try {
			gd = new GoogleDriveStorage(accessToken, centralFolder);
		} catch (IOException e) {
			System.out.println("Failed to initialize GD");
			System.exit(1);
			return;
		}
		
		boolean success = gd.download(path);
		if(success) {
			File f = new File(path);
			System.out.println("success: " + f.getAbsolutePath() + ", " + f.getTotalSpace());
		}
	}
}
