package hmo.passcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PasscheckApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(PasscheckApplication.class, args);
		} catch (Throwable t) {
			new Exception(t).printStackTrace();
		}
	}

}
