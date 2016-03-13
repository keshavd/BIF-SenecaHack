package biohacks.biohacksArtifact;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressFileGzip {

	public static void main(String[] args) {

		String source_filepath = "C:\\Users\\nikos7\\Desktop\\files\\test.txt";
		String gzip_filepath = "C:\\Users\\nikos7\\Desktop\\files\\test.gzip";
		String decopressed_filepath = "C:\\Users\\nikos7\\Desktop\\files\\test2.txt";

		CompressFileGzip gZipFile = new CompressFileGzip();
		gZipFile.gzipFile(source_filepath, gzip_filepath);
		gZipFile.unGunzipFile(gzip_filepath, decopressed_filepath);
	}

	public static void gzipFile(String source_filepath, String destinaton_zip_filepath) {

		byte[] buffer = new byte[1024];

		try {

			FileOutputStream fileOutputStream = new FileOutputStream(
					destinaton_zip_filepath);

			GZIPOutputStream gzipOuputStream = new GZIPOutputStream(
					fileOutputStream);

			FileInputStream fileInput = new FileInputStream(source_filepath);

			int bytes_read;

			while ((bytes_read = fileInput.read(buffer)) > 0) {
				gzipOuputStream.write(buffer, 0, bytes_read);
			}

			fileInput.close();

			gzipOuputStream.finish();
			gzipOuputStream.close();

			System.out.println("The file was compressed successfully!");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void unGunzipFile(String compressedFile, String decompressedFile) {

		byte[] buffer = new byte[1024];

		try {

			FileInputStream fileIn = new FileInputStream(compressedFile);

			GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);

			FileOutputStream fileOutputStream = new FileOutputStream(decompressedFile);

			int bytes_read;

			while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {

				fileOutputStream.write(buffer, 0, bytes_read);
			}

			gZIPInputStream.close();
			fileOutputStream.close();

			System.out.println("The file was decompressed successfully!");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}