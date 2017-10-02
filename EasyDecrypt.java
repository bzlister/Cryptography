import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

//Ben Lister
//Takes 8-character hex string as key to decode ciphertext file 'abc123.txt'
public class EasyDecrypt {
	public static void main(String[] args) throws IOException {

		File file = new File("C:\\Users\\bzlis\\Documents\\abc123.txt");
		// init array with file length
		byte[] bytesArray = new byte[(int) file.length()];

		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray); // read file into bytes[]
		fis.close();

		
 		String key = (String)args[0];
		int n = key.length()/2;
		byte[] keybyte = DatatypeConverter.parseHexBinary(key);
		for (int i = 0; i < bytesArray.length; i++){
				if (keybyte[i%n] > 0)
					bytesArray[i] = (byte) (bytesArray[i]-keybyte[i%n]);	
				else
					bytesArray[i] = (byte) (bytesArray[i]+keybyte[i%n]);	
		}
		FileOutputStream fos = new FileOutputStream("C:\\Users\\bzlis\\Documents\\abc123.txt");
		fos.write(bytesArray);
		fos.close();

	}	
}
