package it.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.demo.dto.AutoreDTO;
import it.demo.dto.LibroDTO;
import it.demo.entity.Autore;
import it.demo.entity.Categoria;
import it.demo.entity.CategoriaDTO;
import it.demo.entity.Libro;
import it.demo.entity.StockElement;
import it.demo.repository.AutoreDAO;
import it.demo.repository.CategoriaDAO;
import it.demo.repository.LibroDAO;
import it.demo.repository.StockElementDAO;

@Service
@Transactional
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroDAO dao;
	@Autowired
	private StockElementDAO daoS;
	@Autowired
	private AutoreDAO daoA;
	@Autowired
	private CategoriaDAO daoC;


	@Override
	public Libro registraNuovoLibro(Libro l) throws Exception {
		if(l.getAutore()==null) {
			Exception ex = new Exception("inserire un autore");
			throw ex;
		}
		if(l.getCategoria()==null) {
			throw new Exception("inserire una categoria");
		}
		int inputId= l.getAutore().getId();
		Autore autore= daoA.selectAutorebyId(inputId);
		int inputIsbn= l.getIsbn();
		Libro libro = dao.selectByisbn(inputIsbn);
		if(libro!=null) {
			Exception ex=new Exception("il libro con questo isbn già esiste");
			throw ex;
		}
		if(autore==null) {
			Exception ex= new Exception("id autore inesistente");
			throw ex;
		}
		else if 
		(!(l.getAutore().getNome().equals(autore.getNome())
				&& l.getAutore().getCognome().equals(autore.getCognome())
				&& l.getAutore().getNazioneResidenza().equals(autore.getNazioneResidenza()))){
			Exception ex2=new Exception("dati autore errati");
			throw ex2;
		}
		String inputCategoria=l.getCategoria().getCategoria();
		Categoria categoria= daoC.selectCategoriaById(inputCategoria);

		if(categoria==null) {
			Exception ex= new Exception("categoria inesistente");
			throw ex;
		}
		l.setnCopie(50);
		dao.insert(l);


		//		apriRichiesta(l.getIsbn(),50);


		return l;
	}

	@Override
	public Libro modificaDatiLibro(Libro l) throws Exception {

		int codiceLibro = l.getIsbn();

		Libro libroDaModificare= dao.selectByisbn(codiceLibro);

		libroDaModificare.setTitolo(l.getTitolo());
		libroDaModificare.setTitolo(l.getTitolo());
		libroDaModificare.setDescrizione(l.getDescrizione());
		libroDaModificare.setPrezzo(l.getPrezzo());
		libroDaModificare.setnCopie(l.getnCopie());

		if(l.getnCopie()<10) {
			apriRichiesta(l.getIsbn(),50);
		}


		return l;
	}


	@Override
	public void eliminaLibro(int isbn) throws Exception {
		Libro libroDaEliminare= dao.selectByisbn(isbn);
		for(StockElement stock : libroDaEliminare.getRiordini()) {
			if("disponibile".equals(stock.getStatoStock())|| "processato".equals(stock.getStatoStock())) {
				Exception e= new Exception("non puoi elimiare un libro in stato processato o disponibile: "+ "lo stato attuale è: "+stock.getStatoStock());
				throw e;

			}
		}
		dao.cancellaLibro(isbn);
		
		// se non si può fare il cascade ALL ecc:
//		for (StockElement stock : libroDaEliminare.getRiordini()) {
//			if ("richiesto".equals(stock.getStatoStock())){
//				daoS.delete(stock.getId());
//
//			}
//		}
//		dao.cancellaLibro(isbn);
	}


	@Override
	public void modificaNumeroCopieLibro(int isbn, int nCopie) throws Exception {
		Libro l = dao.selectByisbn(isbn);
		if(l==null) {
			throw new Exception("il libro indicato non esiste");
		}
		else {
			l.setnCopie(nCopie);

		}
	}

	@Override
	public Libro leggiDatiLibro(int isbn) throws Exception {
		Libro libro = dao.selectByisbn(isbn);
		if(libro==null) {
			throw new Exception("il libro non esiste");
		} else
			return dao.selectByisbn(isbn);
	}
	
	@Override
	public Autore getAutore(int isbn) {
		Libro l= dao.selectByisbn(isbn);
		Autore a= l.getAutore();
		return a;
	}

	


	@Override
	public LibroDTO travasoDaNormADTO(Libro libro) {
		LibroDTO nuovoLibro = new LibroDTO();
		nuovoLibro.setIsbn(libro.getIsbn());
		nuovoLibro.setAutore(libro.getAutore());
		nuovoLibro.setCategoria(libro.getCategoria());
		nuovoLibro.setnCopie(libro.getnCopie());
		nuovoLibro.setPrezzo(libro.getPrezzo());
		nuovoLibro.setTitolo(libro.getTitolo());
		nuovoLibro.setRiordini(libro.getRiordini());
		nuovoLibro.setDescrizione(libro.getDescrizione());
		return nuovoLibro;
	}

	@Override
	public Libro travasoDaDTOANorm(LibroDTO librodto) {
		Libro nuovoLibro = new Libro();
		nuovoLibro.setIsbn(librodto.getIsbn());
		nuovoLibro.setDescrizione(librodto.getDescrizione());
		nuovoLibro.setnCopie(librodto.getnCopie());
		nuovoLibro.setPrezzo(librodto.getPrezzo());
		nuovoLibro.setTitolo(librodto.getTitolo());
		return nuovoLibro;
	}


	@Override
	public AutoreDTO travasoDaNormaleADTOAutore(Autore autore) {
		AutoreDTO autoredto = new AutoreDTO();
		autoredto.setId(autore.getId());
		autoredto.setNome(autore.getNome());
		autoredto.setCognome(autore.getCognome());
		autoredto.setNazioneResidenza(autore.getNazioneResidenza());
		return autoredto;
	}

	@Override
	public Autore travasoDaDTOANormaleAutore(AutoreDTO autoreDTO) {
		Autore autore = new Autore();
		autore.setId(autoreDTO.getId());
		autore.setNome(autoreDTO.getNome());
		autore.setCognome(autoreDTO.getCognome());
		autore.setNazioneResidenza(autoreDTO.getNazioneResidenza());
		return autore;
	}

	@Override
	public CategoriaDTO travasoDaNormaleADTOCategoria(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setCategoria(categoria.getCategoria());
		return categoriaDTO;
	}

	@Override
	public Categoria travasoDaDTOANormaleCategoria(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		categoria.setCategoria(categoriaDTO.getCategoria());
		return categoria;
	}


	/*apriRichiesta --> consente fare una richiesta di approvvigionamento rispetto ad un certo libro. 
	PARAM  isbn del libro soggetto ad approvvigionamento e il numero di copie richieste
	RETURN   la lista di tutti gli StockElements per quel libro dopo l’approvvigionamento
	THROWS  nel caso il il libro non sia già presente sul DB o vi sia già un riordino nello stato richiesto o processato per quel libro (e per qualsiasi altro problema)*/

	@Override
	public List<StockElement> apriRichiesta(int isbn, int nCopie) throws Exception {
		Libro libro = dao.selectByisbn(isbn);
		for(StockElement stock : libro.getRiordini()) {
			if("richiesto".equals(stock.getStatoStock())|| "processato".equals(stock.getStatoStock())) {
				Exception e= new Exception("esiste già una richiesta in corso: "+ "si trova nello stato: "+stock.getStatoStock());
				throw e;
			} 
		}
		StockElement nuovoStock = new StockElement(nCopie,"richiesto");

		//		nuovoStock.setStatoStock("richiesto");
		//		nuovoStock.setQuantita(nCopie);

		libro.getRiordini().add(nuovoStock);

		return null;
	}

	@Override
	public List<StockElement> cambiaStatoRichiesta(int idStock) throws Exception{

		StockElement s= daoS.select(idStock);
		Libro l = dao.getLibroByStockId(idStock);
		boolean disponibile=false;

		if(s==null) {
			throw new Exception("lo stock non esiste");
		}
		else {
			if("richiesto".equals(s.getStatoStock())) {
				s.setStatoStock("processato");
			}
			else 
			if("processato".equals(s.getStatoStock())) {
				for (StockElement stock: l.getRiordini()){
					if("disponibile".equals(stock.getStatoStock())){
						int aggiungi= s.getQuantita();
						stock.setQuantita(stock.getQuantita()+aggiungi);
						daoS.delete(s.getId());
						disponibile=true;
						break;
					}

				}
				if(!disponibile) {
					s.setStatoStock("disponibile");


				}
			}

		}
		return l.getRiordini();
	}



	@Override
	public List<StockElement> leggiInfoStock(int isbn) throws Exception {
		Libro l=dao.selectByisbn(isbn);
		if(l==null) {
			throw new Exception("il libro non esiste");
		}
		return l.getRiordini();

	}

	@Override
	public List<StockElement> chiudiRichiesta(int isbn) throws Exception {
		Libro l= dao.selectByisbn(isbn);
		boolean disponibile=false;
		StockElement stockToRemove=null;
		for(StockElement stock : l.getRiordini()) {
			if ("disponibile".equals(stock.getStatoStock())) {
				int aumenta= stock.getQuantita();
				l.setnCopie(l.getnCopie()+aumenta);
				
				stockToRemove=stock;
				
				
				disponibile=true;
				break;
			}


		}daoS.delete(stockToRemove.getId());
		l.getRiordini().remove(stockToRemove);
		if (!disponibile) {		
			throw new Exception("il libro non si trova nello stato di riordino 'disponibile' ");


		}
		return l.getRiordini();
	}


//	@Override
//	public List<StockElement> modificaCopie(int idStock, int nCopie) {
//		StockElement stock = daoS.select(idStock);
//		if("disponibile".equals(stock.getStatoStock())|| "processato".equals(stock.getStatoStock())) {
//			stock.setQuantita(nCopie);
//
//		}
//		return null;
//	}
	public List<StockElement> modificaCopie(int idStock, int nCopie) throws Exception {
		Libro l= dao.getLibroByStockId(idStock);
		StockElement stock = daoS.select(idStock);
		if(stock == null){
			throw new Exception("Stock non presente");

		}
		if(stock.getStatoStock().equals("processato")){

			throw  new Exception ("stock processato");
		}

		List<StockElement> listaStock = l.getRiordini();
		if(stock.getStatoStock().equals("richiesto") || stock.getStatoStock().equals("disponibile")){
			if(nCopie <= 0){

				throw new Exception("La cifra inserita deve essere superiore a 0");
			}
			stock.setQuantita(nCopie);
			listaStock.add(stock);
		}
		return listaStock;


	}
	
	//metodi aggiuntivi

	@Override
	public List<Libro> getAll() {
		return	dao.selectAll();
		
	}

	@Override
	public List<Autore> getAllAutori() {
		return dao.getAllAutori();
		
	}

	@Override
	public List<Libro> getAllLibriCategoria(String categoria) {
		return dao.getAllLibriCategoria(categoria);
		
	}

//	@Override
//	public List<Libro> getAllLibriPerNomeAutore(String nome, String cognome) throws Exception {
//if(nome==null && cognome==null) {
//	throw new Exception("inserisci un autore");
//}
//		return dao.getAllLibriPerNomeAutore(nome, cognome);
//
//		
//	}
	
	public List<Libro> getAllLibriPerNomeAutore(String nomeCognome) throws Exception {
 
		return dao.getAllLibriPerNomeAutore(nomeCognome);

		
	}

	@Override
	public List<Categoria> getAllCategorie() {
		return dao.getAllCategorie();
		
	}
	

	
}

