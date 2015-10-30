import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.lang.storage.google.GoogleAuth;
import org.lang.storage.google.GoogleDriveStorage;
import org.lang.storage.google.GoogleSearchObject;


public class SyncFiles {
	public static void main(String argv[]) {
		
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
	    String centralFolder = "buf";
		GoogleDriveStorage gd;
		try {
			gd = new GoogleDriveStorage(accessToken, centralFolder);
		} catch (IOException e) {
			System.out.println("Failed to initialize GD");
			System.exit(1);
			return;
		}
		
		List<String> localFiles = new ArrayList<String>();
		List<String> remoteFiles = null;
		// Put the list of local files
		localFiles.add("C:/Users/l/Downloads/test.csv");
		localFiles.add("C:/Users/l/Downloads/test2.csv");
		try {	// Get the list of files in GoogleDrive central folder
			remoteFiles = new ArrayList<String>(gd.list().keySet());
		} catch (GoogleSearchObject.Error e) {
			System.out.println("Failed to search central folder");
			System.exit(1);
		}
		
		System.out.println(localFiles);
		System.out.println(remoteFiles);
		
		Map<String, Boolean> success = gd.sync(localFiles, remoteFiles);
		for(String pathKey : success.keySet()) {
			boolean isSuccess = success.get(pathKey);
			System.out.println(pathKey + " success: " + isSuccess);
		}
	}
}
