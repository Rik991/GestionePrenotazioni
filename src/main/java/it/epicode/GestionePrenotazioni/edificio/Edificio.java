package it.epicode.GestionePrenotazioni.edificio;

import it.epicode.GestionePrenotazioni.postazione.Postazione;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "edifici")
public class Edificio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column()
    private String nome;

    @Column()
    private String indirizzo;

    @Column()
    private String citta;

    @OneToMany(mappedBy = "edificio")
    @ToString.Exclude
    private List<Postazione> postazioni = new ArrayList<>();


}