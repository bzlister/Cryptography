import java.math.BigDecimal;

//Few random base conversion functions
public class CryptoFunctions {

	public static void main(String[] args) {
	        CryptoFunctions.hexToBaseN(
		 "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d",
		 10);
		//CryptoFunctions.hexToBaseN("9AF", 2);
		//CryptoFunctions.binToDec("111000");
		//CryptoFunctions.BufferXOR("1c0111001f010100061a024b53535009181c", "686974207468652062756c6c2773206579650000000");
	}

	public static String hexToBaseN(String s, int base) {
		String converted = "";
		int length = 4 * s.length();

		if (base == 64) {
			while (length % 6 != 0)
				length++;
		}
		int[] b = new int[length];
		for (int i = 0; i < s.length(); i++) {
			int n = 0;
			if (Character.getNumericValue(s.charAt(i)) >= 97)
				n = Character.getNumericValue(s.charAt(i)) - 87;
			else
				n = Character.getNumericValue(s.charAt(i));
			b[4 * i] = (n / 8);
			b[4 * i + 1] = ((n % 8) / 4);
			b[4 * i + 2] = (((n % 8) % 4) / 2);
			b[4 * i + 3] = (n % 2);
		}

		if (base == 2) {
			for (int k = 0; k < b.length; k++) {
				converted += b[k];
			}
		}
		
		if (base == 64) {
			for (int j = 0; j < b.length; j++) {
				int n = 0;
				if (j % 6 == 0) {
					n = 32 * b[j] + 16 * b[j + 1] + 8 * b[j + 2] + 4 * b[j + 3] + 2 * b[j + 4] + b[j + 5];
					if (n < 26)
						converted += (char) (n + 65);
					if ((n >= 26) && (n < 52))
						converted += (char) (n + 71);
					if ((n >= 52) && (n < 62))
						converted += (char) (n - 4);
					if (n == 62)
						converted += '+';
					if (n == 63)
						converted += '/';
				}
			}
		}

		if (base == 10) {
			double sum = 0.0;
			for (int m = b.length - 1; m >= 0; m--) {
				sum += b[m] * Math.pow(2, b.length - 1 - m);
			}
			converted = new BigDecimal(sum).toPlainString();
		}

		System.out.println(converted);
		return converted;
	}
	
	public static String decToBin(String s){
		String bin = "";
		int n = Integer.parseInt(s);
		while(n != 0){
			bin = Integer.toString(n%2)+bin;
			n = n/2;
		}
    	System.out.println(bin);
		return bin;
	}
	
	public static int binToDec(String s){
		int sum = 0;
		for (int m = s.length() - 1; m >= 0; m--) 
			sum += ((int)s.charAt(m)-48) * (int) Math.pow(2, s.length() - 1 - m);
		System.out.println(sum);
		return sum;
	}

	public static String BufferXOR(String s1, String s2) {
		//s1 = CryptoFunctions.hexToBaseN(s1, 2);
		/*
		s2 = CryptoFunctions.decToBin(s2);
		String s3 = "";
		for (int i = 0; i < s1.length(); i++){
			s3 += Math.abs((int)s1.charAt(i) - (int)s2.charAt(i));
		}
		return CryptoFunctions.binToDec(s3);
		*/
		s1 = CryptoFunctions.hexToBaseN(s1, 10);
		String s3 = "";
		for (int j = 0; j < s1.length(); j++){
			s3 += ((int)s1.charAt(j) - 48)^((int)s2.charAt(j));
		}
		System.out.println(s3);
		return s3;
	}
	
	public static byte[] XORAddition(byte[] b1, byte b2){
		byte[] result = new byte[b1.length];
		for (int i = 0; i < b1.length; i++){
			result[i] = (byte) (b1[i]^b2);
		}
		return result;
	}
	
	public static byte[] XORAddition2(byte[] b3){
		byte[] result = new byte[b3.length];
		for (int j = 0; j < b3.length-1; j++){
			result[j] = (byte)(b3[j]^b3[j+1]);
		}
		return result;
	}
	
	public static byte[] XORAddition3(byte[] b1, byte[] b2){
		byte[] result = new byte[b1.length];
		for (int i = 0; i < b1.length; i++){
			result[i] = (byte) (b1[i]^b2[i]);
		}
		return result;
	}
	
	/*
	public static boolean possibleByte(byte b){
		if ((b == 10) || (b == 13) || (b == 32) || (b == 38) || (b == 44) || (b == 46) || (b == 47) || ((b > 48) && (b < 58)) || ((b > 64) && (b < 91)))
			return true;
		else
			return false;
	}
	*/

}
