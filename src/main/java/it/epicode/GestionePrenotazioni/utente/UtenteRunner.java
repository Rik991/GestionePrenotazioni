package it.epicode.GestionePrenotazioni.utente;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UtenteRunner implements ApplicationRunner {

    private final UtenteRepository utenteRepo;

    private final ObjectProvider<Utente> utenteProvider;



    @Override
    public void run(ApplicationArguments args) throws Exception {

        {
            if (utenteRepo.count() == 0){
            for (int i = 0; i < 10; i++) {
                Utente utente = utenteProvider.getObject();
                utenteRepo.save(utente);
            }}


        }
    }
}
