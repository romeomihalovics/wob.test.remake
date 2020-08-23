package wob.test.remake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WobTestRemakeApplication {

	boolean currentlySyncing;

	@Autowired
	public WobTestRemakeApplication() {
		this.currentlySyncing = false;
	}

	public boolean isCurrentlySyncing() {
		return currentlySyncing;
	}

	public void setCurrentlySyncing(boolean currentlySyncing) {
		this.currentlySyncing = currentlySyncing;
	}

	public static void main(String[] args) {
		SpringApplication.run(WobTestRemakeApplication.class, args);
	}

}
