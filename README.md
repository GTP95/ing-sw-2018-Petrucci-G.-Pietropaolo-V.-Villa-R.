# ing-sw-2018-Petrucci-G.-Pietropaolo-V.-Villa-R.
Progetto Ingegneria del Software 2018 - Sviluppo del videogioco Sagrada a cura di Giacomo Tommaso Petrucci, Vincenzo Pietropaolo e Roberto Villa

Membri del Gruppo							Numero Matricola						Cod. Persona
Giacomo Tommaso Petrucci 			828781											10487523
Vincenzo Pietropaolo                      828345											10467327
Roberto Villa										826432											10486135

Funzionalità Implementate
Sono state implementate le seguenti funzionalità: 
- Connessione tramite Socket;
- Regole di gioco complete (incluse tutte le Tool Cards e le Public Objective Cards);
- GUI completa del Client con effetti sonori;
- Caricamento da file JSON di Tool Cards, Public Objective Cards, Griglie di gioco, impostazioni per Client e Server;
- Salvataggio su file delle impostazioni del Client;
- Grafica vettoriale per il rendering della griglia da gioco, delle Tool Card e Public Objective Cards;

Scelte di Design
- Per rendere più facile e diretto l'uso di alcune Tool Cards attraverso la GUI alcuni espedienti sono stati utilizzati, come ad esempio la scelta di immettere la riga e la colonna scelta in apposite caselle di testo;
- Per la comunicazione tra Client e Server si è optato per messaggi sottoforma di stringa, questo ha permesso di mandare oggetti sotto forma di JSON insieme a ulteriori informazioni sul loro utilizzo, a questo scopo è stato stabiliti tre formati per i messaggi: Action Message, JSON Message e Control Message con un unumero di campi variabili a seconda dell'informazione da inviare;
- Un brano musicale e altri suoni sono stati implementati, i suoni sono usati per notificare il giocatore di alcuni cambiamenti nel gioco (come il cambiamento di Round);
- Le fasi di gioco secondo come riportate nel manuale di istruzioni sono state divise in vari Stage per la creazione della GUI;
- Nell'UML lato Client tutte le classi collegate strettamente alla GUI non sono state descitte nei metodi e negli attributi al fine di garantire una maggior chiarezza e legibilità;
- Si è optato di lasciare nel codice delle print al fine di notificare tutti i passaggi che vengono compiuti da Client e da Server sia a scopo di debug sia a scopo informativo per l'utente;

Limitazioni
- Le Tool Card che portano ad un cambiamento di posizione di dadi già piazzati non aggiornano istantaneamente la grafica di gioco nonostante l'uso sia registrato correttamente sia dal Client che dal Server;
- In caso un giocatore perda la connessione e si riconnetta successivamente l'aggiornamento della sua view potrebbe non essere immediata, tuttavia non gli è comunque permesso fare mosse non valide

Per motivi di dimensioni (il file Jar pesa 70MB e github permette solo file da 25MB) il jar è reperibile a questo indirizzo: https://mega.nz/#!M9sCxaQL!pi5xf7AD8of8AjMDG3cH8TW9dmRCG57lYlRFrFBbZXU