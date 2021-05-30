package project.green.shop.helper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUpLoadHelper {
	public static void SaveFile(String UploadDir,String filename, MultipartFile multi) throws IOException
	{
		Path uploadPath = Paths.get(UploadDir);
		System.out.println("FileUploadHelper: " + uploadPath.toString());
		if (Files.notExists(uploadPath, LinkOption.NOFOLLOW_LINKS)) {
			Files.createDirectories(uploadPath);
		}
		
		InputStream inputStream = multi.getInputStream();
		Path filePath = uploadPath.resolve(filename);		
		System.out.println("FileUploadHelper: " + filePath.toString());		
		try {
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {		
			e.printStackTrace();
		}
	}

	
}
