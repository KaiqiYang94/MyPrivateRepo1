import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class getAttributes {

	public static void main(String args[]) {
		try {
			Scanner scan = new Scanner(new File("dev-tweets.txt"));
			int i = 10;
			while (scan.hasNextLine()) {
				if (i -- <= 0) break;
				String line = scan.nextLine();
				String[] array = line.split("\t");
				System.out.println(line);
				System.out.println("the id is " + array[0] + "content is " + array[1]);
				//Here you can manipulate the string the way you want
			}


		} catch (Exception e) {
			e.printStackTrace();
			//error handling code
		}
	}
}
