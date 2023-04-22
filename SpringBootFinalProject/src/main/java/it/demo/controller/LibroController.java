package it.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.demo.entity.Autore;
import it.demo.entity.Categoria;
import it.demo.entity.Libro;
import it.demo.entity.StockElement;
import it.demo.service.LibroService;


@RestController
@RequestMapping("/libro")
public class LibroController {
	@Autowired
	private LibroService service;

	@PostMapping(path="/books", consumes="application/json")
	public ResponseEntity<String> registraNuovoLibro(@RequestBody Libro l) {
		
		ResponseEntity<String> entity;
		
		try {
			service.registraNuovoLibro(l);
			entity= new ResponseEntity<>("libro inserito correttamente",HttpStatus.CREATED);
		}

		catch (Exception e) {
	
			e.printStackTrace();
			entity= new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		return entity;
	}


	@GetMapping(path="/books/{isbn}", produces="application/json")
	public ResponseEntity<String> getLibro(@PathVariable int isbn) {
		ResponseEntity<String> entity;
		try {
			service.leggiDatiLibro(isbn);
			entity=new ResponseEntity<>("dai libro letti",HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	@DeleteMapping(path="/books/{isbn}")
	public ResponseEntity<String> eliminaLibro(@PathVariable int isbn) {
		ResponseEntity<String> entity;
		try {
			service.eliminaLibro(isbn);
			entity=new ResponseEntity<>("libro eliminato correttamente",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}

	@PatchMapping("/books/{isbn}")
	public ResponseEntity<String> modificaDatiLibro(@PathVariable int isbn, @RequestBody Libro l) {
		ResponseEntity<String> entity;
		try {
			service.modificaDatiLibro(l);
			entity=new ResponseEntity<>("libro modificato correttamente",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;


	}
	@PatchMapping(path="/copie/{isbn}", produces = "application/json")
	public ResponseEntity<String> modificaNumeroCopieLibro(@PathVariable int isbn, int nCopie) {
		ResponseEntity<String> entity;
		try {
			service.modificaNumeroCopieLibro(isbn, nCopie);
			entity=new ResponseEntity<>("copie libro modificate correttamente",HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;

	}
	@GetMapping(path="/books/{isbn}/author")
	public ResponseEntity<String>getAutoreLibro(@PathVariable int isbn){
		ResponseEntity<String> entity;
		try {
			service.getAutore(isbn);
			entity=new ResponseEntity<>("autore letto",HttpStatus.OK);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);


		}
		return entity;
	}
	@PostMapping(path="/books/{isbn}/warehouse")
	public ResponseEntity<String> apriRichiesta(@PathVariable int isbn, int nCopie) {
		ResponseEntity<String> entity;
		try {
			service.apriRichiesta(isbn, nCopie);
			entity=new ResponseEntity<>("richiesta aperta",HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	@PatchMapping(path="/books/{id}/warehouse")
	public ResponseEntity<String> cambiaStatoRichiesta(@PathVariable int id){
		ResponseEntity<String> entity;
		try {
			service.cambiaStatoRichiesta(id);
			entity=new ResponseEntity<>("stato richiesta cambiato correttamente",HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;


	}
	@GetMapping(path="/books/{isbn}/warehouse")
	public ResponseEntity <List<StockElement>> leggiInfoStock(@PathVariable int isbn) {
		ResponseEntity<List<StockElement>> entity;
		try {
			
			entity = new ResponseEntity<>(service.leggiInfoStock(isbn),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity=new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;	
	}
	@PatchMapping(path="/books/{isbn}/warehouse")
	public ResponseEntity<String> chiudiRichiesta(@PathVariable Integer isbn){
		ResponseEntity<String> entity;
		try {
			service.chiudiRichiesta(isbn);
			entity = new ResponseEntity<>("richiesta chiusa correttamente",HttpStatus.OK);

		} catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;

	}
	@PatchMapping("/books/{id}/warehouse/Update")
	public ResponseEntity<String> modificaCopie(@PathVariable int id, @RequestParam ("nCopie") int nCopie){
		ResponseEntity<String> entity;
		try {
			service.modificaCopie(id, nCopie);
			entity = new ResponseEntity<>("modifica copie stock avvenuta correttamente",HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	//filta libri per categoria
	@GetMapping(path="books/categoria/{categoria}" , produces="application/json")
	public @ResponseBody ResponseEntity<List<Libro>> getAllLibriCategoria(@PathVariable String categoria) {

		return new ResponseEntity<List<Libro>>(service.getAllLibriCategoria(categoria),HttpStatus.OK);


	}
	
	//filta libri per autore:
	@GetMapping(path="books/autore/{nomeCognome}", produces="application/json")
	public @ResponseBody ResponseEntity<List<Libro>> getaAllLibriByAutore(@PathVariable String nomeCognome){

		ResponseEntity<List<Libro>> entity;
		try {
			List<Libro> lista= service.getAllLibriPerNomeAutore(nomeCognome);
			entity= new ResponseEntity<List<Libro>>(lista,HttpStatus.OK);
		} catch (Exception e) {

			e.printStackTrace();
			entity= new ResponseEntity<List<Libro>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;



	}
	
	
//	@GetMapping(path="/libri/autore", produces="application/json")
//	public @ResponseBody ResponseEntity<List<Libro>> getaAllLibriByAutore(
//
//			@RequestParam(value="nome",required=false)String nome,
//			@RequestParam (value="cognome",required=false)String cognome){
//
//		ResponseEntity<List<Libro>> entity;
//		try {
//			List<Libro> lista= service.getAllLibriPerNomeAutore(nome, cognome);
//			entity= new ResponseEntity<List<Libro>>(lista,HttpStatus.OK);
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			entity= new ResponseEntity<List<Libro>>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		return entity;
//
//
//
//	}
	
	//metodi aggiuntivi:
	@GetMapping(path="/books",produces="application/json")
	public ResponseEntity<List<Libro>> getAll() {
		
		
		 return new ResponseEntity<List<Libro>>(service.getAll(),HttpStatus.OK);
		
		
	}

	
	public List<Autore> getAllAutori() {
		service.getAllAutori();
		return null;
	}



	public List<Categoria> getAllCategorie() {
		service.getAllCategorie();
		return null;
	}
	

	
	
}
