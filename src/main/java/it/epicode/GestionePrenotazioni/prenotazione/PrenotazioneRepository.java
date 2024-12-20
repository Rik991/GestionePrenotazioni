package it.epicode.GestionePrenotazioni.prenotazione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    @Query("SELECT p FROM Prenotazione p WHERE p.postazione.id = :postazioneId AND :dataPrenotazione BETWEEN p.dataPrenotazione AND p.dataFinePrenotazione")
    List<Prenotazione> findPrenotazioniByPostazioneAndData(@Param("postazioneId") Long postazioneId, @Param("dataPrenotazione") LocalDate dataPrenotazione);

    @Query("SELECT p FROM Prenotazione p WHERE p.utente.id = :utenteId AND :dataPrenotazione BETWEEN p.dataPrenotazione AND p.dataFinePrenotazione")
    List<Prenotazione> findPrenotazioniByUtenteAndData(@Param("utenteId") Long utenteId, @Param("dataPrenotazione") LocalDate dataPrenotazione);

    List<Prenotazione> findByUtenteId(Long utenteId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Prenotazione p WHERE p.id = :prenotazioneId AND p.utente.id = :utenteId")
    void deleteByIdAndUtenteId(@Param("prenotazioneId") Long prenotazioneId, @Param("utenteId") Long utenteId);
}
