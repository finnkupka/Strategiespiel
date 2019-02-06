package firebase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FirebaseManager {
	
	public static void updateFirebaseData() {
		String command = "python Resources/update_firebase_data.py";
		try {
			Process p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getFirebaseData() {
		String fileName = "Resources/firebase_data.txt";
        try {
            return new BufferedReader(new FileReader(fileName)).readLine();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return "";
	}
	
	public static void printFirebaseDataFile() {
        String fileName = "Resources/firebase_data.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
	}
	
	public static int rgb(String s, int index) {
		int comma1 = 0, comma2 = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ',') {
				if (comma1 == 0) comma1 = i;
				else comma2 = i;
			}
		}
		switch(index) {
		case 0:
			return Integer.parseInt(s.substring(0, comma1));
		case 1:
			return Integer.parseInt(s.substring(comma1+1, comma2));
		case 2:
			return Integer.parseInt(s.substring(comma2+1, s.length()));
		default:
			return 0;
		}
	}
	
}
