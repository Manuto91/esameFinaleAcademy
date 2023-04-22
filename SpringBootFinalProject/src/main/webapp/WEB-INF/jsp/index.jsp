<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Lista Libri</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
/* Reset CSS per uniformare la visualizzazione tra i diversi browser */
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

/* Stile generale del body */
body {
	font-family: Arial, sans-serif;
	font-size: 16px;
	line-height: 1.5;
	color: #333;
}

/* Stile dell'header */
header {
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

/* Stile del titolo */
h1 {
	font-size: 36px;
	margin-bottom: 20px;
}

/* Stile del form per l'aggiunta di un libro */
form#formAggiungiLibro {
	margin-top: 40px;
}

/* Stile della tabella dei libri */
table {
	width: 100%;
	border-collapse: collapse;
}

/* Stile della riga dell'intestazione della tabella */
thead tr {
	background-color: #eee;
}

/* Stile delle celle della tabella */
td, th {
	padding: 10px;
	border: 1px solid #ccc;
}

/* Stile del footer */
footer {
	background-color: #f5f5f5;
	padding: 20px;
	text-align: center;
}
</style>

</head>
<body>
	<header>
		<h1>Lista Libri</h1>
		<form>
			<label for="filtroGenere">Filtra per categoria:</label> <select
				name="filtroGenere" id="filtroGenere"
				onChange="filtraLibriCategoria()">
				<option value="">Tutti i generi</option>
				<option value="horror">Horror</option>
				<option value="fantasy">Fantasy</option>
				<option value="thriller">Thriller</option>
				<option value="storico">Storico</option>
			</select> <label for="filtroAutore">Filtra per autore:</label> <input
				type="text" id="filtroAutore" name="filtroAutore">
			<button type="button" onclick="filtraLibriAutore()">Filtra</button>

		</form>
		<h1>Libri</h1>

		<!-- Form per aggiungere un nuovo libro -->
		<form id="formAggiungiLibro">
			<h2>Aggiungi un nuovo libro</h2>

			<label for="titolo">Titolo:</label> <input type="text" id="titolo"
				name="titolo" required><br> <label for="idAutore">Id
				Autore:</label> <input type="number" id="idAutore" name="idAutore" required><br>

			<label for="nomeAutore">Nome Autore:</label> <input type="text"
				id="nomeAutore" name="nomeAutore" required><br> <label
				for="cognomeAutore">Cognome Autore:</label> <input type="text"
				id="cognomeAutore" name="cognomeAutore" required><br> <label
				for="nazioneResidenza">Nazionalità:</label> <input type="text"
				id="nazioneResidenza" name="nazioneResidenza" required><br>

			<label for="categoria">Categoria:</label> <input type="text"
				id="categoria" name="categoria" required><br> <label
				for="isbn">ISBN:</label> <input type="number" id="isbn" name="isbn"
				required><br> <label for="nCopie">Numero di
				copie:</label> <input type="number" id="nCopie" name="nCopie" required><br>

			<label for="descrizione">Descrizione:</label> <input type="text"
				id="descrizione" name="descrizione" required><br> <label
				for="prezzo">Prezzo:</label> <input type="number" id="prezzo"
				name="prezzo" required><br> <input type="button"
				onClick="aggiungiLibro()" value='aggiungi'>
		</form>


	</header>
	<main>
		<table>
			<thead>
				<tr>
					<th>ISBN</th>
					<th>Titolo</th>
					<th>Descrizione</th>
					<th>Autore</th>
					<th>Categoria</th>
					<th>Copie</th>
					<th>prezzo</th>
					<th>azioni
						<table id="rootTabellaRiordini" style="display:none">
							<thead>
								<tr>
									<th>ID</th>
									<th>Quantità</th>
									<th>Stato Stock</th>
									<th></th>
									

								</tr>
							</thead>
							<tbody id="tabellaRiordini">
								<!-- qui vengono inseriti dinamicamente i dati dei riordini -->


							</tbody>
						</table>
					</th>

				</tr>
			</thead>
			<tbody id="tabellaLibri">
				<!-- qui vengono inseriti dinamicamente i dati dei libri -->
			</tbody>
		</table>
	</main>

	<footer>

		<!-- eventuali informazioni aggiuntive o link a pagine correlate -->
	</footer>
</body>
<script>
		/* JavaScript per gestire le interazioni con la pagina e le chiamate ai servizi REST */

		
			
			
		// Funzione per caricare i dati dei libri e aggiornare la tabella
		function caricaLibriAll(){
			caricaLibri('/demo/libro/books')
		}
		function filtraLibriCategoria(){
			const categoria=document.getElementById("filtroGenere").value;
			
			if (categoria!==''){
				const url="/demo/libro/books/categoria/"+ categoria
				
				caricaLibri(url);
				
			} else caricaLibriAll();
		}
		
		function filtraLibriAutore(){
			const autore=document.getElementById("filtroAutore").value;
			
			if(autore!==''){
				
				const url="/demo/libro/books/autore/"+autore
				caricaLibri(url);
				
			}
			else caricaLibriAll();
		}
		function caricaLibri(url) {
			fetch(url)
				.then(response =>{
					const jsonList = response.json();
					
					return jsonList;
					})
				
				.then(libri => {
					const tabellaLibri = document.getElementById('tabellaLibri');
					tabellaLibri.innerHTML = ''; //svuota il corpo della tabella

					libri.forEach(libro => { 
						const riga = document.createElement('tr');
						const titolo = document.createElement('td');
						titolo.textContent = libro.titolo;
						const autore = document.createElement('td');
						autore.textContent = libro.autore.nome+" "+libro.autore.cognome;
						const categoria = document.createElement('td');
						categoria.textContent = libro.categoria.categoria;
						const isbn = document.createElement('td');
						isbn.textContent = libro.isbn;
						const nCopie = document.createElement('td');
						nCopie.textContent = libro.nCopie;
						const descrizione = document.createElement('td');
						descrizione.textContent = libro.descrizione;
						const prezzo = document.createElement('td');
						prezzo.textContent = libro.prezzo;
						
						//pulsanti nella cella "azioni"
						const elimina = document.createElement("button");
					    elimina.innerHTML = "Elimina";
					    const statoRiordini = document.createElement("button");
					    statoRiordini.innerHTML = "Stato Riordini";
					    
					    

					 // Aggiunta degli event listener ai pulsanti
					 
					 

					 elimina.addEventListener("click", function () {
							eliminaLibro(libro.isbn);
						});
					    
				
					 
					 statoRiordini.addEventListener("click",function() {
						 mostraTabellaRiordini(libro.isbn);
					 })
					    
					    //aggiungi alle celle
					    

			riga.appendChild(isbn)
            riga.appendChild(titolo)
            riga.appendChild(descrizione)
            riga.appendChild(autore)
            riga.appendChild(categoria)
            riga.appendChild(nCopie)
            riga.appendChild(prezzo)
            riga.appendChild(elimina);
			riga.appendChild(statoRiordini);
            
            
            tabellaLibri.appendChild(riga)

						
					})
				})
				
		}


		// funzione per aggiungere un nuovo libro
		function aggiungiLibro() {
			const titolo=document.getElementById("titolo").value
			const idAutore=document.getElementById("idAutore").value
			const nomeAutore=document.getElementById("nomeAutore").value
			const cognomeAutore=document.getElementById("cognomeAutore").value
			const nazioneResidenza=document.getElementById("nazioneResidenza").value
			const descrizione=document.getElementById("descrizione").value
			const isbn=document.getElementById("isbn").value
			const nCopie=document.getElementById("nCopie").value
			const categoria=document.getElementById("categoria").value
			
			//costruisco oggetto con chiave-valore: a sx java, a dx js
				
			const nuovoLibro={
					titolo:titolo,
					isbn:isbn,
					descrizione:descrizione,
					nCopie:nCopie,
					categoria:{
						categoria:categoria
					},
					autore:{
						id:idAutore,
						nome:nomeAutore,
						cognome:cognomeAutore,
						nazioneResidenza:nazioneResidenza
					}
					
			}
			//di default è get il method
			
			fetch("/demo/libro/books",{
				method:'POST',
				headers:{
					"Content-Type": "application/json"
			
				}, //trasforma l'oggetto java in oggetto json (stringify)
				body:JSON.stringify(nuovoLibro)
					
				
				
			})//dopo l'inserimento del libro ricarico la lista dei libri
			.then(response=>{
				caricaLibriAll()
				
			})
			
			
			
			
		}
		function eliminaLibro(isbn) {
			  const urlElimina = "/demo/libro/books/" + isbn;

			  fetch(urlElimina, {
			    method: 'DELETE'
			  })
			  .then(response => {
			    if (response.ok) {
			      console.log('Libro eliminato con successo');
			      caricaLibriAll();
			    } else {
			      console.error("Errore durante l'eliminazione del libro");
			    }
			  })
			  .catch(error => {
			    console.error('Si è verificato un errore:', error);
			  });
			}
		
		function nascondiTabellaRiordini() {
			 
			  const root= document.getElementById('rootTabellaRiordini')
		 		root.style.display="none";
			  
		}
		
		 function mostraTabellaRiordini(isbn){
			 	const urlStato = "/demo/libro/books/"+isbn+"/warehouse";

			 	  fetch(urlStato, {
			 	    method: 'GET'
			 	  })
			 	  .then(response =>{
			 			const jsonList = response.json();
						
			 			return jsonList;
			 			})
					
			 		.then(riordini => {
			 			const tabellaRiordini = document.getElementById('tabellaRiordini');
			 			tabellaRiordini.innerHTML = ''; //svuota il corpo della tabella

			 			riordini.forEach(riordine => { 
			 				const riga = document.createElement('tr');
							
			 				const id = document.createElement('td');
			 				id.textContent = riordine.id;
							
			 				const quantita = document.createElement('td');
			 				quantita.textContent = riordine.quantita;
							
			 				const statoStock = document.createElement('td');
			 				statoStock.textContent = riordine.statoStock;
			 				
			 				const nascondiRiordiniButton= document.createElement("button");
			 				nascondiRiordiniButton.innerHTML = "chiudi";
			 				
			 				nascondiRiordiniButton.addEventListener("click", function(){
			 					nascondiTabellaRiordini()
			 				})
			 				
			 				const cellaBottone=document.createElement("td")
					 		cellaBottone.appendChild(nascondiRiordiniButton);
			 				
							
							riga.appendChild(id);
							riga.appendChild(quantita);
							riga.appendChild(statoStock);
							riga.appendChild(cellaBottone);
							tabellaRiordini.appendChild(riga);
							
			 			})
			 		})
			 		
			 		const root= document.getElementById('rootTabellaRiordini')
			 		root.style.display="block";
			 		
			}
						
			  
			  
			  
		caricaLibriAll()
		</script>

</html>