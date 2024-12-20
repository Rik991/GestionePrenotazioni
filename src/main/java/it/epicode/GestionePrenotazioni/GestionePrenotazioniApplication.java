package it.epicode.GestionePrenotazioni;

import it.epicode.GestionePrenotazioni.utente.Utente;
import it.epicode.GestionePrenotazioni.utente.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication

public class GestionePrenotazioniApplication {


	public static void main(String[] args) {

		SpringApplication.run(GestionePrenotazioniApplication.class, args);


	}

}
