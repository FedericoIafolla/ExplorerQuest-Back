# ExplorerQuest

**ExplorerQuest** è un'applicazione completa sviluppata con un'architettura frontend-backend per semplificare la pianificazione dei viaggi e migliorare l'esperienza degli utenti.

---

## ✨ Caratteristiche Principali

- **Creazione di Itinerari**: Personalizza itinerari di viaggio con pochi clic.
- **Informazioni Meteo**: Visualizza previsioni in tempo reale per le destinazioni.
- **Attrazioni Turistiche**: Esplora le mete più popolari in base alla località scelta.
- **Salvataggio e PDF**: Salva i tuoi itinerari e stampali in formato PDF.

### 🚀 Funzionalità Future

- **Community**: Condividi itinerari, commentali, votali e personalizzali con facilità.
- **Gamification**: Guadagna trofei per attività come creare itinerari, votare o modificare.

---

## 🛠️ Struttura del Progetto

Il progetto è suddiviso in due repository principali:

1. **Frontend**:
   - Gestisce l'interfaccia utente.
   - Repository: [ExplorerQuest-Front](https://github.com/FedericoIafolla/ExplorerQuest-Front)
   - Tecnologie: JavaScript, CSS, HTML.

2. **Backend**:
   - Fornisce le API e la logica server-side.
   - Repository: [ExplorerQuest-Back](https://github.com/FedericoIafolla/ExplorerQuest-Back)
   - Tecnologie: Node.js, Express, e un database (specificare il tipo).

---

## 📋 Requisiti di Sistema

- **Node.js** (>= 16)
- **npm** (>= 7)
- Un database (es. MySQL, PostgreSQL, MongoDB, ecc.)

---

## ⚙️ Installazione e Configurazione

### 1️⃣ Clona le repository

```bash
# Clona il frontend
git clone https://github.com/FedericoIafolla/ExplorerQuest-Front.git

# Clona il backend
git clone https://github.com/FedericoIafolla/ExplorerQuest-Back.git
```

### 2️⃣ Configura il Backend

1. Accedi alla directory del backend:
   ```bash
   cd ExplorerQuest-Back
   ```
2. Installa le dipendenze:
   ```bash
   npm install
   ```
3. Configura il file `.env`:
   ```env
   PORT=5000
   DATABASE_URL=<URL_DEL_DATABASE>
   JWT_SECRET=<SEGRETO_PER_TOKEN>
   ```
4. Avvia il server:
   ```bash
   npm start
   ```

### 3️⃣ Configura il Frontend

1. Accedi alla directory del frontend:
   ```bash
   cd ExplorerQuest-Front
   ```
2. Installa le dipendenze:
   ```bash
   npm install
   ```
3. Configura eventuali URL API nel codice (es. l'indirizzo del backend).
4. Avvia il server di sviluppo:
   ```bash
   npm start
   ```

---

## 🖥️ Come Utilizzare

1. Apri l'applicazione nel browser (es. `http://localhost:3000`).
2. Crea e personalizza un itinerario di viaggio.
3. Salva l'itinerario e stampalo in formato PDF per condividerlo.

---

## 🤝 Contribuire

1. Fai un fork della repository.
2. Crea un branch per la modifica:
   ```bash
   git checkout -b nome-branch
   ```
3. Implementa le modifiche e committale:
   ```bash
   git commit -m "Descrizione delle modifiche"
   ```
4. Apri una pull request.

---

## 📧 Contatti

- **Autore**: Federico Iafolla
- **Email**: iafollafederico@gmail.com

Grazie per aver scelto ExplorerQuest! 🌍

