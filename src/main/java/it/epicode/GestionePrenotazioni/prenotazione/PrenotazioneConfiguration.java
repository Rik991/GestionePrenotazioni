package it.epicode.GestionePrenotazioni.prenotazione;

import it.epicode.GestionePrenotazioni.postazione.PostazioneRepository;
import it.epicode.GestionePrenotazioni.utente.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
@Order(10)
public class PrenotazioneConfiguration {

    private final PrenotazioneRepository prenotazioneRepository;
    private final UtenteRepository utenteRepository;
    private final PostazioneRepository postazioneRepository;

    @Bean
    @Scope("prototype")
    public Prenotazione prenotazione(Long utenteId, Long postazioneId, LocalDate dataPrenotazione) {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utenteRepository.findById(utenteId).orElseThrow());
        prenotazione.setPostazione(postazioneRepository.findById(postazioneId).orElseThrow());
        prenotazione.setDataPrenotazione(dataPrenotazione);
        prenotazione.setDataFinePrenotazione(prenotazione.getDataPrenotazione().plusDays(1));
        return prenotazioneRepository.save(prenotazione);
    }

}
