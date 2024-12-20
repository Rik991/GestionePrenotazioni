package it.epicode.GestionePrenotazioni.postazione;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

    List<Postazione> findByEdificioId(Long edificioId);




}

