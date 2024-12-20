package it.epicode.GestionePrenotazioni.edificio;


import com.github.javafaker.Faker;
import it.epicode.GestionePrenotazioni.postazione.PostazioneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
@RequiredArgsConstructor
public class EdificioConfiguration {

    private final Faker faker;

    private final PostazioneRepository postazioneRepo;

    @Bean(name = "edificio")
    @Scope("prototype")
    public Edificio edificio() {
        Edificio edificio = new Edificio();
        edificio.setNome(faker.company().name());
        edificio.setIndirizzo(faker.address().streetAddress());
        edificio.setCitta(faker.address().city());
        return edificio;
    }






}
