package it.epicode.GestionePrenotazioni.edificio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {



    @Query("SELECT DISTINCT e.citta FROM Edificio e")
    List<String> findDistinctCitta();


}
