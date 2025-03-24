import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class main {
	public static void main(String[] args) {
		try {
			UserManagerView managerView = new UserManagerView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("File initialization error, see info below:");
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
