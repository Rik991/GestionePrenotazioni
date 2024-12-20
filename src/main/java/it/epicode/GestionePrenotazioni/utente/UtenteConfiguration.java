package it.epicode.GestionePrenotazioni.utente;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

@RequiredArgsConstructor
@Configuration
@Order(1)
public class UtenteConfiguration {

    private final Faker faker;

    @Bean(name = "utente")
    @Scope("prototype")
    public Utente utente() {
        Utente utente = new Utente();
        utente.setUsername(faker.name().username());
        utente.setNomeCompleto(faker.name().fullName());
        utente.setEmail(faker.internet().emailAddress());
        return utente;
    }
}