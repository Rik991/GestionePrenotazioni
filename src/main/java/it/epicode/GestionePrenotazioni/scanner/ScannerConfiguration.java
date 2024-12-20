package it.epicode.GestionePrenotazioni.scanner;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ScannerConfiguration {


    @Bean
    public Scanner getScanner() {
       Scanner scanner = new Scanner(System.in);
        return scanner;
    }

}
