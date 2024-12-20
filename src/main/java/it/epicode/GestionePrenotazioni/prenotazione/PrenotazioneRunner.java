package it.epicode.GestionePrenotazioni.prenotazione;

import it.epicode.GestionePrenotazioni.edificio.Edificio;
import it.epicode.GestionePrenotazioni.edificio.EdificioRepository;
import it.epicode.GestionePrenotazioni.postazione.Postazione;
import it.epicode.GestionePrenotazioni.postazione.PostazioneRepository;
import it.epicode.GestionePrenotazioni.utente.Utente;
import it.epicode.GestionePrenotazioni.utente.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


@RequiredArgsConstructor
@Component
public class PrenotazioneRunner implements ApplicationRunner {

    private final UtenteRepository utenteRepo;
    private final PrenotazioneRepository prenotazioneRepo;
    private final EdificioRepository edificioRepo;
    private final PostazioneRepository postazioneRepo;
    private final Scanner scanner;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Benvenuto utente! Digita il tuo id per accedere al sistema");
        List<Utente> utenti = utenteRepo.findAll();
        for (Utente utente : utenti) {
            System.out.println("ID Utente: " + utente.getId() + " --Nome: " + utente.getNomeCompleto() + " --Username: " + utente.getUsername() + " --Email: " + utente.getEmail());
        }
        Long sceltaUtente = scanner.nextLong();
        System.out.println("Benvenuto " + utenteRepo.findById(sceltaUtente).get().getNomeCompleto() + "!");

        boolean exit = false;
        while (!exit) {
            System.out.println("Cosa vuoi fare? \n1- Per prenotare una postazione \n2- Per cercare postazioni tramite città \n3- Per visualizzare le tue prenotazioni \n4- Per cancellare una prenotazione \n5- Per uscire");
            int scelta = scanner.nextInt();
            switch (scelta) {
                case 1:
                    System.out.println("Ecco l'elenco degli edifici disponibili: ");
                    List<Edificio> edifici = edificioRepo.findAll();
                    for (Edificio edificio : edifici) {
                        System.out.println("ID Edificio: " + edificio.getId() + " --Nome: " + edificio.getNome() + " --Indirizzo: " + edificio.getIndirizzo() + " --Città: " + edificio.getCitta());
                    }
                    System.out.println("Inserisci l'id dell'edificio scelto per vedere le postazioni disponibili");
                    Long sceltaEdificio = scanner.nextLong();
                    scanner.nextLine();
                    List<Postazione> postazioniInEdificio = postazioneRepo.findByEdificioId(sceltaEdificio);
                    System.out.println("Ecco l'elenco delle postazioni disponibili nell'edificio " + edificioRepo.findById(sceltaEdificio).get().getNome());
                    for (Postazione postazione : postazioniInEdificio) {
                        System.out.println("ID Postazione: " + postazione.getId() + " --TIPO-->  " + postazione.getTipo() + " --Descrizione: " + postazione.getDescrizione() );
                    }
                    System.out.println("Inserisci l'id della postazione che vuoi prenotare");
                    Long sceltaPostazione = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Inserisci la data della prenotazione (yyyy-mm-dd)");
                    String dataPrenotazione = scanner.nextLine();
                    LocalDate dataPrenotazioneParsed = java.time.LocalDate.parse(dataPrenotazione);

                    List<Prenotazione> prenotazioniEsistenti = prenotazioneRepo.findPrenotazioniByPostazioneAndData(sceltaPostazione, dataPrenotazioneParsed);
                    List<Prenotazione> prenotazioniUtente = prenotazioneRepo.findPrenotazioniByUtenteAndData(sceltaUtente, dataPrenotazioneParsed);
                    if (!prenotazioniEsistenti.isEmpty()) {
                        System.out.println("La postazione è già prenotata per la data selezionata. Scegli un'altra data o postazione.");
                    } else if (!prenotazioniUtente.isEmpty()) {
                        System.out.println("Hai già una prenotazione per la data selezionata. Non puoi prenotare più di una postazione per la stessa data.");
                    } else {
                        Prenotazione prenotazione = new Prenotazione();
                        prenotazione.setUtente(utenteRepo.findById(sceltaUtente).get());
                        prenotazione.setPostazione(postazioneRepo.findById(sceltaPostazione).get());
                        prenotazione.setDataPrenotazione(dataPrenotazioneParsed);
                        prenotazione.setDataFinePrenotazione(dataPrenotazioneParsed.plusDays(1));
                        prenotazioneRepo.save(prenotazione);
                        System.out.println("Prenotazione effettuata con successo!");
                    }
                    break;
                case 2:
                    System.out.println("Ecco la lista delle città con postazioni disponibili: ");
                    List<Edificio> edificiCitta = edificioRepo.findAll();
                    for (Edificio edificio : edificiCitta) {
                        System.out.println(edificio.getId() + " " + edificio.getCitta() + " --> " + edificio.getNome());
                    }
                    System.out.println("Seleziona la tua preferenza");
                    Long sceltaCitta = scanner.nextLong();
                    scanner.nextLine();
                    List<Postazione> postazioniInCitta = postazioneRepo.findByEdificioId(sceltaCitta);
                    System.out.println("Ecco l'elenco delle postazioni disponibili nella città di " + edificioRepo.findById(sceltaCitta).get().getCitta());
                    for (Postazione postazione : postazioniInCitta) {
                        System.out.println(postazione.getId() + " " + postazione.getTipo() + " Edificio: " + postazione.getDescrizione());
                    }
                    System.out.println("Seleziona una postazione tra quelle disponibili");
                    Long sceltaPostazioneCitta = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Inserisci la data della prenotazione (yyyy-mm-dd)");
                    String dataPrenotazione1 = scanner.nextLine();
                    LocalDate dataPrenotazioneParsed1 = java.time.LocalDate.parse(dataPrenotazione1);

                    List<Prenotazione> prenotazioniEsistenti1 = prenotazioneRepo.findPrenotazioniByPostazioneAndData(sceltaPostazioneCitta, dataPrenotazioneParsed1);
                    List<Prenotazione> prenotazioniUtente1 = prenotazioneRepo.findPrenotazioniByUtenteAndData(sceltaUtente, dataPrenotazioneParsed1);
                    if (!prenotazioniEsistenti1.isEmpty()) {
                        System.out.println("La postazione è già prenotata per la data selezionata. Scegli un'altra data o postazione.");
                    } else if (!prenotazioniUtente1.isEmpty()) {
                        System.out.println("Hai già una prenotazione per la data selezionata. Non puoi prenotare più di una postazione per la stessa data.");
                    } else {
                        Prenotazione prenotazione = new Prenotazione();
                        prenotazione.setUtente(utenteRepo.findById(sceltaUtente).get());
                        prenotazione.setPostazione(postazioneRepo.findById(sceltaPostazioneCitta).get());
                        prenotazione.setDataPrenotazione(dataPrenotazioneParsed1);
                        prenotazione.setDataFinePrenotazione(dataPrenotazioneParsed1.plusDays(1));
                        prenotazioneRepo.save(prenotazione);
                        System.out.println("Prenotazione effettuata con successo!");
                    }
                    break;
                case 3:
                    System.out.println("Ecco le tue prenotazioni in dettaglio");
                    List<Prenotazione> prenotazioniUtentecorrente = prenotazioneRepo.findByUtenteId(sceltaUtente);
                    for (Prenotazione prenotazione : prenotazioniUtentecorrente) {
                        System.out.println(prenotazione);
                    }
                    break;
                case 4:
                    System.out.println("Sccegli quale prenotazione cancellare");
                    List<Prenotazione> prenotazioniDaCancellare = prenotazioneRepo.findByUtenteId(sceltaUtente);
                    for (Prenotazione prenotazione : prenotazioniDaCancellare) {
                        System.out.println(prenotazione);
                    }
                    System.out.println("Inserisci l'id della prenotazione che vuoi cancellare");
                    Long sceltaPrenotazione = scanner.nextLong();
                    scanner.nextLine();
                    prenotazioneRepo.deleteByIdAndUtenteId(sceltaPrenotazione, sceltaUtente);
                    System.out.println("Prenotazione cancellata con successo!");
                    break;
                case 5:
                    exit = true;
                    System.out.println("Grazie e Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida");
            }
        }
    }
}
