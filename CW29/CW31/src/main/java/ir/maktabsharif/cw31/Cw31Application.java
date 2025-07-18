package ir.maktabsharif.cw31;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Base64;

@SpringBootApplication
public class Cw31Application {

    public static void main(String[] args) {
        SpringApplication.run(Cw31Application.class, args);
    }

}
