package it.epicode.GestionePrenotazioni.postazione;

import com.github.javafaker.Faker;
import it.epicode.GestionePrenotazioni.edificio.Edificio;

import it.epicode.GestionePrenotazioni.edificio.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PostazioneConfiguration {

    @Autowired
    private  Faker faker;



    @Bean(name ="openspace")
    @Scope("prototype")
    public Postazione postazione() {
        Postazione postazione = new Postazione();
        postazione.setDescrizione(faker.lorem().sentence());
        postazione.setTipo(TipoPostazione.OPENSPACE);
        postazione.setPostiDisponibili(faker.number().numberBetween(10, 100));

        return postazione;
    }

    @Bean(name ="salaRiunione")
    @Scope("prototype")
    public Postazione postazione2() {
        Postazione postazione = new Postazione();
        postazione.setDescrizione(faker.lorem().sentence());
        postazione.setTipo(TipoPostazione.SALA_RIUNIONI);
        postazione.setPostiDisponibili(faker.number().numberBetween(1, 100));

        return postazione;
    }

    @Bean(name = "privato")
    @Scope("prototype")
    public Postazione postazione3() {
        Postazione postazione = new Postazione();
        postazione.setDescrizione(faker.lorem().sentence());
        postazione.setTipo(TipoPostazione.PRIVATO);
        postazione.setPostiDisponibili(faker.number().numberBetween(1, 10));

        return postazione;
    }




}
