File-structure
--------------

* src/  Demo scripts of the GoogleDrive-library(upload, download, sync function examples)
* libs/ GoogleDrive-library


Befor using the library
-------------------
Access_token is necessary to use GoogleDrive API functions.
Access_token can be obtained by following the steps below (client_id, client_secret and refresh_token are needed)

1. Login to Google with central email account
2. Go to Google Developer console(https://console.developers.google.com/)
3. In the left panel, Apis & auth -> APIs, enable Drive API and SDK
4. In the left panel, Apis & auth -> Credentials, Add credentails of oauth2.0-client-ID and select the adequate application type(Select other for the demo scripts)
5. Put the client_id and client_secret of the created credential into demo codes
6. Get refresh token by executing GetAccessToken and putting it into the other demo codes.
7. Get access token by using GoogleAuth.getAccessToken() with client_id, client_secret and refresh_token
8. Pass the access token to GoogleDriveStorage functions(upload/download/sync)


GoogleDrive library functions
-----------------------------
GoogleDriveStorage(accessToken, centralFolderName)
  * Constructor of the GoogleDrive class, `centralFolderName` will be created and used for all GoogleDrive related functions
  
GoogleDriveStorage.download(cloudFileName)
  * Download the file having the `cloudFileName`. This name will be the local file's unique canonical pathname.
  * Retruns True if download succeeds

GoogleDriveStorage.upload(filePath)
  * Upload the file to the central-folder(`centralFolderName`). The file's unique canonical pathname will be the uploaded file's name
  * Returns True if upload succeeds
  
GoogleDriveStorage.sync(uploadFilePathList, downloadCloudFileNameList)
  * Upload local files and download cloud files
  * Returns Map of filePaths/CloudFileNames and Booleans 
    (If a file in `downloadCloudFileNameList` doesn't exist in cloud, it will be omitted in the Map structure)
    
GoogleDriveStorage.list()
  * Returns a list the cloudFileNames of the files in central folder
