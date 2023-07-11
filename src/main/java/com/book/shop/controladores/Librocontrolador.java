package com.book.shop.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.book.shop.entidades.Libro;
import com.book.shop.entidades.Genero;
import com.book.shop.repositorios.EditorialLibroRepositorio;
import com.book.shop.repositorios.GeneroLibroRepositorio;
import com.book.shop.repositorios.LibroRepositorio;
import com.book.shop.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/libros")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
public class Librocontrolador {

	@Autowired
	UsuarioRepositorio usuRep;
	@Autowired
	LibroRepositorio libroRep;
	@Autowired
	GeneroLibroRepositorio generoRep;
	@Autowired
	GeneroLibroRepositorio libroGenero;
	@Autowired
	EditorialLibroRepositorio libroEditorial;

	@GetMapping("/obtener")
	public List<DTO> getLibros() {
		List<DTO> listaLibrosDto = new ArrayList<DTO>();
		List<Libro> libros = libroRep.findAll();
		for (Libro l : libros) {
			DTO dtoLibros = new DTO();
			dtoLibros.put("id", l.getId());		
			dtoLibros.put("titulo", l.getTitulo());
			dtoLibros.put("saga", l.getSaga());
			dtoLibros.put("autor", l.getAutor());
			dtoLibros.put("ISBN", l.getIsbn());
			dtoLibros.put("fecha_publi", l.getFechaPubli().toString());
			dtoLibros.put("id_genero", l.getGenero().getId());
			dtoLibros.put("genero", l.getGenero().getNombre());
			dtoLibros.put("id_editorial", l.getEditoriale().getId());
			dtoLibros.put("editorial", l.getEditoriale().getNombre());
			dtoLibros.put("descripcion", l.getDescripcion());
			dtoLibros.put("sinopsis", l.getSinopsis());
			dtoLibros.put("precio", l.getPrecio());
			dtoLibros.put("tipo", l.getTipo());
			dtoLibros.put("portada", l.getPortada());
			dtoLibros.put("rating", l.getRating());
			dtoLibros.put("stock", l.getStock());
			listaLibrosDto.add(dtoLibros);
		}
		return listaLibrosDto;
	}
	
	@PostMapping(path="/getByAutor",consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<DTO> getByAutor(@RequestBody DTO autor) {
		//Creo una lista de hashmap para devolver un json
		
		//leo de repositorio todos los registros
		List<Libro> libros = libroRep.findByAutor((String) autor.get("autor"));
		//Los voy cargar en el DTO
		List<DTO> listaLibrosDto = new ArrayList<DTO>();
		if(libros!=null) {
			for (Libro l : libros) {
				DTO dtoLibros = new DTO();
				dtoLibros.put("id", l.getId());		
				dtoLibros.put("titulo", l.getTitulo());
				dtoLibros.put("saga", l.getSaga());
				dtoLibros.put("autor", l.getAutor());
				dtoLibros.put("ISBN", l.getIsbn());
				dtoLibros.put("fecha_publi", l.getFechaPubli().toString());
				dtoLibros.put("id_genero", l.getGenero().getId());
				dtoLibros.put("genero", l.getGenero().getNombre());
				dtoLibros.put("id_editorial", l.getEditoriale().getId());
				dtoLibros.put("editorial", l.getEditoriale().getNombre());
				dtoLibros.put("descripcion", l.getDescripcion());
				dtoLibros.put("sinopsis", l.getSinopsis());
				dtoLibros.put("precio", l.getPrecio());
				dtoLibros.put("tipo", l.getTipo());
				dtoLibros.put("portada", l.getPortada());
				dtoLibros.put("rating", l.getRating());
				dtoLibros.put("stock", l.getStock());
				listaLibrosDto.add(dtoLibros);
			}
			if (listaLibrosDto.isEmpty()) {
				DTO dtoLibros = new DTO();
				dtoLibros.put("result","not found");
				listaLibrosDto.add(dtoLibros);
			}
		} else {
			DTO dtoLibros = new DTO();
			dtoLibros.put("result","fail");
			listaLibrosDto.add(dtoLibros);
		}

		return listaLibrosDto;
	}
	
	@PostMapping(path="/getByGenero",consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<DTO> getByGenero(@RequestBody DTO genre) {
		//Creo una lista de hashmap para devolver un json
		
		//leo de repositorio todos los registros
		Genero genero = generoRep.findById((int) genre.get("genero"));
		List<Libro> libros = libroRep.findAll();
		//Los voy cargar en el DTO
		List<DTO> listaLibrosDto = new ArrayList<DTO>();
		if(libros != null && genero != null) {
			for (Libro l : libros) {
				if (l.getGenero().getId() == genero.getId()) {
					DTO dtoLibros = new DTO();
					dtoLibros.put("id", l.getId());		
					dtoLibros.put("titulo", l.getTitulo());
					dtoLibros.put("saga", l.getSaga());
					dtoLibros.put("autor", l.getAutor());
					dtoLibros.put("ISBN", l.getIsbn());
					dtoLibros.put("fecha_publi", l.getFechaPubli().toString());
					dtoLibros.put("id_genero", l.getGenero().getId());
					dtoLibros.put("genero", l.getGenero().getNombre());
					dtoLibros.put("id_editorial", l.getEditoriale().getId());
					dtoLibros.put("editorial", l.getEditoriale().getNombre());
					dtoLibros.put("descripcion", l.getDescripcion());
					dtoLibros.put("sinopsis", l.getSinopsis());
					dtoLibros.put("precio", l.getPrecio());
					dtoLibros.put("tipo", l.getTipo());
					dtoLibros.put("portada", l.getPortada());
					dtoLibros.put("rating", l.getRating());
					dtoLibros.put("stock", l.getStock());
					listaLibrosDto.add(dtoLibros);
				}
			}
			if (listaLibrosDto.isEmpty()) {
				DTO dtoLibros = new DTO();
				dtoLibros.put("result","not found");
				listaLibrosDto.add(dtoLibros);
			}
		} else {
			DTO dtoLibros = new DTO();
			dtoLibros.put("result","not found");
			listaLibrosDto.add(dtoLibros);
		}

		return listaLibrosDto;
	}
	
	
	//Obtener un registron con findById

		@PostMapping(path="/obtener1",consumes=MediaType.APPLICATION_JSON_VALUE)
		public DTO getLibros(@RequestBody DTO solouno) {
			//Creo una lista de hashmap para devolver un json
			
			//leo de repositorio todos los registros
			Libro l = libroRep.findById(Integer.parseInt(solouno.get("id").toString()));
			//Los voy cargar en el DTO
					
				DTO dtoLibros=new DTO();
				if(l!=null) {
					dtoLibros.put("id", l.getId());		
					dtoLibros.put("titulo", l.getTitulo());
					dtoLibros.put("saga", l.getSaga());
					dtoLibros.put("autor", l.getAutor());
					dtoLibros.put("ISBN", l.getIsbn());
					dtoLibros.put("fecha_publi", l.getFechaPubli().toString());
					dtoLibros.put("id_genero", l.getGenero().getId());
					dtoLibros.put("genero", l.getGenero().getNombre());
					dtoLibros.put("id_editorial", l.getEditoriale().getId());
					dtoLibros.put("editorial", l.getEditoriale().getNombre());
					dtoLibros.put("descripcion", l.getDescripcion());
					dtoLibros.put("sinopsis", l.getSinopsis());
					dtoLibros.put("precio", l.getPrecio());
					dtoLibros.put("tipo", l.getTipo());
					dtoLibros.put("portada", l.getPortada());
					dtoLibros.put("rating", l.getRating());
					dtoLibros.put("stock", l.getStock());
				} else dtoLibros.put("result","fail");
		
			return dtoLibros;}	
	//fin Obtener un registron con findById
	
		// PUT
				@PutMapping(path="/modificar",consumes=MediaType.APPLICATION_JSON_VALUE)
				public void modificarLibro(@RequestBody DatosAltaLibro l, HttpServletRequest request) {
				    Optional<Libro> optionalLibro = Optional.ofNullable(libroRep.findById(l.id));
				    Libro libro;
				    if (optionalLibro.isPresent()) {
				        libro = optionalLibro.get();
				    } else {
				        libro = new Libro();
				        libro.setId(l.id);
				    }
				    libro.setTitulo(l.titulo);
				    libro.setAutor(l.autor);
				    libro.setSaga(l.saga);
				    libro.setIsbn(l.isbn);
				    libro.setTipo(l.tipo);
				    libro.setPrecio(l.precio);
				    libro.setDescripcion(l.descripcion);
				    libro.setSinopsis(l.sinopsis);
				    libro.setRating(l.rating);
				    libro.setStock(l.stock);
				    libro.setEditoriale(libroEditorial.findById(l.id_editorial));
				    libro.setPortada(l.portada);
				    libro.setFechaPubli(l.fecha_publi);
				    libro.setGenero(libroGenero.findById(l.id_genero));
				    libroRep.save(libro);
				}
				
				static class DatosAltaLibro{
					int id;
					String titulo;
					String autor;
					String saga;
					String isbn;
					String tipo;
					String sinopsis;
					String descripcion;
					String portada;
					float precio;
					float rating;
					int id_editorial;
					Date fecha_publi;
					int id_genero;
					int stock;
					
					public DatosAltaLibro(int id, String titulo,String autor,
							int id_editorial, Date fecha_publi, int id_genero, 
							float precio, float rating, String portada, int stock, String ISBN,
							String saga, String tipo, String sinopsis, String descripcion) {
						super();
						this.id = id;
						this.titulo = titulo;
						this.autor = autor;
						this.id_editorial=id_editorial;
						this.fecha_publi=fecha_publi;
						this.id_genero=id_genero;
						this.precio = precio;
						this.rating = rating;
						this.stock = stock;
						this.isbn = ISBN;
						this.saga = saga;
						this.portada = portada;
						this.tipo = tipo;
						this.sinopsis = sinopsis;
						this.descripcion = descripcion;
					}
				}
	
	//Borra un registro de la base de datos

		@PostMapping(path="/borrar",consumes=MediaType.APPLICATION_JSON_VALUE)
		public DTO borrarLibro(@RequestBody DTO solouno) {
			//Creo una lista de hashmap para devolver un json
			
			//leo de repositorio todos los registros
			Libro l = libroRep.findById(Integer.parseInt(solouno.get("id").toString()));
			//Los voy cargar en el DTO
			
				DTO dtoLibros=new DTO();

				if(l!=null) {libroRep.delete(l);
				dtoLibros.put("delete","ok");
				}
				else dtoLibros.put("delete","fail");
		
			return dtoLibros;}
		
	// Fin Borra un registro de la base de datos

		/*@PutMapping(path="/modificar1",consumes=MediaType.APPLICATION_JSON_VALUE)
		public void modificarLibro(@RequestBody DatosAltaLibro l, HttpServletRequest request) {
		    Optional<Libro> optionalLibro = Optional.ofNullable(libroRep.findById(l.id));
		    Libro libro;
		    if (optionalLibro.isPresent()) {
		        libro = optionalLibro.get();
		    } else {
		        libro = new Libro();
		        libro.setId(l.id);
		    }
		    libro.setTitulo(l.titulo);
		    libro.setAutor(l.autor);
		    libro.setEditoriale(l.editorial);
		    libro.setFechaPublicacion(l.fecha_publicacion);
		    libro.setLibroGenero(libroGenero.findById(l.libroGenero));
		    libro.setFechaElim(l.fechaElim);
		    libroRep.save(libro);
		}*/

		//Controlador para aÃ±adir un registro nuevo


		@PostMapping(path="/nuevo",consumes=MediaType.APPLICATION_JSON_VALUE)
		public void nuevoLibro(@RequestBody
				DatosAltaLibro l,HttpServletRequest request) {
			Libro libro = new Libro();
			libro.setTitulo(l.titulo);
		    libro.setAutor(l.autor);
		    libro.setSaga(l.saga);
		    libro.setIsbn(l.isbn);
		    libro.setTipo(l.tipo);
		    libro.setPrecio(l.precio);
		    libro.setDescripcion(l.descripcion);
		    libro.setSinopsis(l.sinopsis);
		    libro.setRating(l.rating);
		    libro.setPortada(l.portada);
		    libro.setStock(l.stock);
		    libro.setEditoriale(libroEditorial.findById(l.id_editorial));
		    libro.setFechaPubli(l.fecha_publi);
		    libro.setGenero(libroGenero.findById(l.id_genero));
			libroRep.save(libro);
		}

	//fin controlador aÃ±adir registro
	
	@DeleteMapping(path="/borrar",consumes=MediaType.APPLICATION_JSON_VALUE)
	public DTO borrarUsuario(@RequestBody DTO solouno) {
		//Creo una lista de hashmap para devolver un json
		
		//leo de repositorio todos los registros
		Libro l = libroRep.findById(Integer.parseInt(solouno.get("id").toString()));
		//Los voy cargar en el DTO
			DTO dtoLibro=new DTO();

			if(l!=null) {libroRep.delete(l);
			dtoLibro.put("delete","ok");
			}
			else dtoLibro.put("delete","fail");
	
		return dtoLibro;}
}
