
package it.epicode.GestionePrenotazioni.services;

import it.epicode.GestionePrenotazioni.edificio.Edificio;
import it.epicode.GestionePrenotazioni.edificio.EdificioRepository;
import it.epicode.GestionePrenotazioni.postazione.Postazione;
import it.epicode.GestionePrenotazioni.postazione.PostazioneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EdificioService {

    private final EdificioRepository edificioRepo;
    private final PostazioneRepository postazioneRepo;
    private final ApplicationContext context;

    @Transactional
    public Edificio creaEdificioConPostazioni(Edificio edificio) {
        edificioRepo.save(edificio);

        Postazione openspace = context.getBean("openspace", Postazione.class);
        Postazione salaRiunione = context.getBean("salaRiunione", Postazione.class);
        Postazione privato = context.getBean("privato", Postazione.class);

        openspace.setEdificio(edificio);
        salaRiunione.setEdificio(edificio);
        privato.setEdificio(edificio);

        postazioneRepo.save(openspace);
        postazioneRepo.save(salaRiunione);
        postazioneRepo.save(privato);

        edificio.getPostazioni().add(openspace);
        edificio.getPostazioni().add(salaRiunione);
        edificio.getPostazioni().add(privato);

        return edificio;
    }
}