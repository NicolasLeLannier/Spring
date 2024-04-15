package com.diginamic.DigiHello.controleurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.DigiHello.dto.DepartementDTO;
import com.diginamic.DigiHello.dto.VilleDTO;
import com.diginamic.DigiHello.exception.FunctionalException;
import com.diginamic.DigiHello.mappers.DepartementMapper;
import com.diginamic.DigiHello.model.Departement;
import com.diginamic.DigiHello.model.Ville;
import com.diginamic.DigiHello.repository.DepartementRepository;
import com.diginamic.DigiHello.repository.VilleRepository;
import com.diginamic.DigiHello.service.VilleService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

	@Value("${digihello.pdf}")
	private String file;
	
	private VilleRepository villeRepository;
	private VilleService villeService;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public VilleControleur(VilleRepository villeRepository, ModelMapper modelMapper, VilleService villeService) {
		this.villeRepository = villeRepository;
		this.modelMapper = modelMapper;
		this.villeService = villeService;
	}

	@GetMapping
	public List<VilleDTO> getAllVille() {
		return StreamSupport.stream(villeRepository.findAll().spliterator(), false)
				.map(ville -> modelMapper.map(ville, VilleDTO.class)).collect(Collectors.toList());
	}

//	@PostConstruct
//	public void init() {
//		// first time we create some towns because db is empty...
//		try {
//			insertVille(new Ville("Paris", 2133111, new Departement("75", "Paris")));
//			insertVille(new Ville("Marseille", 873076, new Departement("13", "Bouches-du-Rhône")));
//			insertVille(new Ville("Lyon", 522250, new Departement("69", "Rhône")));
//			insertVille(new Ville("Tours", 150000, new Departement("37", "Indre-et-Loire")));
//		} catch (FunctionalException e) {
//			e.printStackTrace();
//		}
//	}

//    @GetMapping
//    public List<Ville> getListeVilles() {
//        return (List<Ville>) villeRepository.findAll();
//    }

	@GetMapping("/{id}")
	public ResponseEntity<Ville> getVilleById(@PathVariable int id) {
		Ville ville = villeRepository.findById(id).orElse(null);
		if (ville != null) {
			return new ResponseEntity<>(ville, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}/file")
	public void getVilleFile(@PathVariable int id, HttpServletResponse response) throws FunctionalException, IOException, DocumentException {
		Ville ville = villeService.extractVille(id);
		
		Document docPdf = new Document(PageSize.A4);
		
		 response.setHeader("Content-Disposition", "attachment; filename=\"" + id + "-" + file + "\"");
		 
		 PdfWriter.getInstance(docPdf, response.getOutputStream());
		 
		 docPdf.open();
		 docPdf.addAuthor("Nicolas");
		 docPdf.addTitle("Fiche Ville - " + ville.getNom());
		 docPdf.newPage();
		 BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		 Phrase p1 = new Phrase("Nom de la ville : " + ville.getNom() + "\n", new Font(baseFont, 32.0f, 1, new BaseColor(0, 51, 80)));
		 Phrase p2 = new Phrase("Nombre d'habitants : " + ville.getNbHabitant() + "\n", new Font(baseFont, 32.0f, 1, new BaseColor(0, 51, 80)));
		 Phrase p3 = new Phrase("Nom département : " + ville.getDepartement().getNom() + " (" + ville.getDepartement().getNumero() + ")", new Font(baseFont, 32.0f, 1, new BaseColor(0, 51, 80)));
		 docPdf.add(p1);
		 docPdf.add(p2);
		 docPdf.add(p3);
		 docPdf.close();
		 
		 response.flushBuffer();
	}
	
	@GetMapping("/export/villes")
    public ResponseEntity<ByteArrayResource> exportVillesCSV() {
        List<Ville> villes = villeService.extractVilles();

        try (
            StringWriter stringWriter = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(stringWriter)
        ) {
            csvWriter.writeNext(new String[]{"Nom de la ville", "Nombre d'habitants", "Code département", "Nom du département"});
            for (Ville ville : villes) {
                csvWriter.writeNext(new String[]{ville.getNom(), String.valueOf(ville.getNbHabitant()), ville.getDepartement().getNumero(), ville.getDepartement().getNom()});
            }
            csvWriter.flush();
            byte[] bytes = stringWriter.toString().getBytes();
            ByteArrayResource resource = new ByteArrayResource(bytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=villes.csv");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(bytes.length)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@GetMapping("/nom/{nom}")
	public ResponseEntity<List<Ville>> getVillesByNom(@PathVariable String nom) {
		List<Ville> villes = villeRepository.findByNomStartingWith(nom);
		if (!villes.isEmpty()) {
			return new ResponseEntity<>(villes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --------------------------------------------------- Repository Methods
	// -----------------------------------
	@GetMapping("/nom-commencant-par/{nom}")
	public ResponseEntity<?> getVillesByNomStartingWith(@PathVariable String nom) {
	    List<Ville> villes = villeRepository.findByNomStartingWith(nom);
	    if (!villes.isEmpty()) {
	        return new ResponseEntity<>(villes, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Aucune ville dont le nom commence par " + nom + " n'a été trouvée", HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/population-superieure-a/{minPopulation}")
	public ResponseEntity<?> getVillesByPopulationGreaterThan(@PathVariable int minPopulation) {
	    List<Ville> villes = villeRepository.findByNbHabitantGreaterThan(minPopulation);
	    if (!villes.isEmpty()) {
	        return new ResponseEntity<>(villes, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Aucune ville n'a une population supérieure à " + minPopulation, HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/population-entre/{minPopulation}/{maxPopulation}")
	public ResponseEntity<?> getVillesByPopulationBetween(@PathVariable int minPopulation, @PathVariable int maxPopulation) {
	    List<Ville> villes = villeRepository.findByNbHabitantBetween(minPopulation, maxPopulation);
	    if (!villes.isEmpty()) {
	        return new ResponseEntity<>(villes, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Aucune ville n'a une population comprise entre " + minPopulation + " et " + maxPopulation, HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/population-departement-superieure-a/{departementNumero}/{minPopulation}")
	public ResponseEntity<?> getVillesByDepartementAndPopulationGreaterThan(@PathVariable String departementNumero, @PathVariable int minPopulation) {
	    List<Ville> villes = villeRepository.findByDepartementNumeroAndNbHabitantGreaterThan(departementNumero, minPopulation);
	    if (!villes.isEmpty()) {
	        return new ResponseEntity<>(villes, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Aucune ville n'a une population supérieure à " + minPopulation + " dans le département " + departementNumero, HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/population-departement-entre/{departementNumero}/{minPopulation}/{maxPopulation}")
	public ResponseEntity<?> getVillesByDepartementAndPopulationBetween(@PathVariable String departementNumero, @PathVariable int minPopulation, @PathVariable int maxPopulation) {
	    List<Ville> villes = villeRepository.findByDepartementNumeroAndNbHabitantBetween(departementNumero, minPopulation, maxPopulation);
	    if (!villes.isEmpty()) {
	        return new ResponseEntity<>(villes, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Aucune ville n'a une population comprise entre " + minPopulation + " et " + maxPopulation + " dans le département " + departementNumero, HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/plus-peuples/{departementNumero}/{size}")
	public ResponseEntity<?> getMostPopulousByDepartement(@PathVariable String departementNumero, @PathVariable int size) {
	    List<Ville> villes = villeRepository.findByDepartementNumeroOrderByNbHabitantDesc(departementNumero, Pageable.ofSize(size)).getContent();
	    if (!villes.isEmpty()) {
	        return new ResponseEntity<>(villes, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Aucune ville n'a été trouvée dans le département " + departementNumero, HttpStatus.NOT_FOUND);
	    }
	}
	
	
	
	@GetMapping("/exportToCsvOnDisk")
	public String exportToCsvOnDisk() {
		villeService.exportToCsvOnDisk();
		return "ok";
	}
	


	@PostMapping
	public ResponseEntity<Ville> insertVille(@RequestBody Ville ville) throws FunctionalException {
		if (ville.getNbHabitant() < 10) {
			throw new FunctionalException("La ville doit avoir au moins 10 habitants.");
		}

		if (ville.getNom().length() < 2) {
			throw new FunctionalException("Le nom de la ville doit contenir au moins 2 lettres.");
		}

		if (ville.getDepartement().getNumero().length() != 2) {
			throw new FunctionalException("Le code du département doit contenir exactement 2 caractères.");
		}

//		Ville existingVille = villeRepository.findByNomAndDepartement(ville.getNom(), ville.getDepartement());
//		if (existingVille != null) {
//			throw new FunctionalException("Le nom de la ville doit être unique pour un département donné.");
//		}

		Ville savedVille = villeService.saveVilleWithDepartement(ville);
		return new ResponseEntity<>(savedVille, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ville> modifierVille(@PathVariable int id, @RequestBody Ville villeModifiee) {
		Ville existingVille = villeRepository.findById(id).orElse(null);
		if (existingVille != null) {
			villeModifiee.setId(id); // Ensure ID consistency
			Ville updatedVille = villeRepository.save(villeModifiee);
			return new ResponseEntity<>(updatedVille, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> supprimerVille(@PathVariable int id) {
		Ville existingVille = villeRepository.findById(id).orElse(null);
		if (existingVille != null) {
			villeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
