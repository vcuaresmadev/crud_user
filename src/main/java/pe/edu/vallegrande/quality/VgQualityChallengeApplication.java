
package pe.edu.vallegrande.quality;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VgQualityChallengeApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(VgQualityChallengeApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(VgQualityChallengeApplication.class, args);
        logger.info("VG Quality Challenge Application started successfully");
    }
}
