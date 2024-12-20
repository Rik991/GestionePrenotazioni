// EdificioRunner.java
package it.epicode.GestionePrenotazioni.edificio;

import it.epicode.GestionePrenotazioni.services.EdificioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EdificioRunner implements ApplicationRunner {

    private final EdificioService edificioService;

    private final EdificioRepository edificioRepo;

    private final ObjectProvider<Edificio> edificioProvider;


    @Override
    public void run(ApplicationArguments args) throws Exception {


            if (edificioRepo.count() == 0) {
                for (int i = 0; i < 5; i++) {
                    Edificio edificio = edificioProvider.getObject();
                    edificioService.creaEdificioConPostazioni(edificio);
                //creo 2 edifici nella stessa città per testare il metodo findDistinctCitta
                Edificio edificio1 = edificioProvider.getObject();
                edificio1.setNome("Plazzo dei Congressi");
                edificio1.setIndirizzo("Via Roma 1");
                edificio1.setCitta("Roma");
                edificioService.creaEdificioConPostazioni(edificio1);

                Edificio edificio2 = edificioProvider.getObject();
                edificio2.setNome("Parlamento");
                edificio2.setIndirizzo("Via Nazionale 2");
                edificio2.setCitta("Roma");
                edificioService.creaEdificioConPostazioni(edificio2);
                }

            }




    }
}