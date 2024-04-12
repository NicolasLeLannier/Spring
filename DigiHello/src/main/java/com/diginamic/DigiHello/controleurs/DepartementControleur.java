package com.diginamic.DigiHello.controleurs;

import com.diginamic.DigiHello.dto.DepartementDTO;
import com.diginamic.DigiHello.exception.FunctionalException;
import com.diginamic.DigiHello.mappers.DepartementMapper;
import com.diginamic.DigiHello.model.Departement;
import com.diginamic.DigiHello.model.Ville;
import com.diginamic.DigiHello.repository.DepartementRepository;
import com.diginamic.DigiHello.service.DepartementService;
import com.diginamic.DigiHello.service.VilleService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {
	
	@Value("${digihello.pdf}")
	private String file;
	
	@Autowired
    private DepartementService departementService;
	@Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private DepartementMapper departementMapper;

    public DepartementControleur(DepartementService departementService, DepartementMapper departementMapper) {
        this.departementService = departementService;
        this.departementMapper = departementMapper;
    }

//    @GetMapping
//    public List<DepartementDTO> findAll() {
//    	return departementRepository.findAll().stream()
//                .map(departementMapper::toDto)
//                .collect(Collectors.toList());
//    }
    
    @GetMapping
    public ResponseEntity<List<Departement>> getAllDepartements() {
        List<Departement> departements = departementService.getAllDepartements();
        return new ResponseEntity<>(departements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable Long id) {
        Departement departement = departementService.getDepartementById(id);
        if (departement != null) {
            return new ResponseEntity<>(departement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{id}/file")
	public void getVilleFile(@PathVariable Long id, HttpServletResponse response) throws FunctionalException, IOException, DocumentException {
		Departement departement = departementService.getDepartementById(id);
		
		Document docPdf = new Document(PageSize.A4);
		
		 response.setHeader("Content-Disposition", "attachment; filename=\"" + id + "-" + file + "\"");
		 
		 PdfWriter.getInstance(docPdf, response.getOutputStream());
		 
		 docPdf.open();
		 docPdf.addAuthor("Nicolas");
		 docPdf.addTitle("Fiche Departement - " + departement.getNom());
		 docPdf.newPage();
		 BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		 Phrase p1 = new Phrase("Nom du departement : " + departement.getNom() + "\n", new Font(baseFont, 32.0f, 1, new BaseColor(0, 51, 80)));
		 Phrase p2 = new Phrase("Code : " + departement.getNumero() + "\n", new Font(baseFont, 32.0f, 1, new BaseColor(0, 51, 80)));
		 docPdf.add(p1);
		 docPdf.add(p2);
		 docPdf.close();
		 
		 response.flushBuffer();
	}
    
    @PostMapping
    public ResponseEntity<Departement> insertDepartement(@RequestBody Departement departement) throws FunctionalException {
        if (departement.getNumero().length() < 2 || departement.getNumero().length() > 3) {
            throw new FunctionalException("Le code du département doit contenir entre 2 et 3 caractères.");
        }
        
        if (departement.getNom().length() < 3) {
            throw new FunctionalException("Le nom du département doit contenir au moins 3 lettres.");
        }
        
        Departement existingDepartement = departementRepository.findByNumero(departement.getNumero());
        if (existingDepartement != null) {
            throw new FunctionalException("Un département avec ce code existe déjà.");
        }
        
        Departement savedDepartement = departementRepository.save(departement);
        return new ResponseEntity<>(savedDepartement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Departement>> modifierDepartement(@PathVariable Long id, @RequestBody Departement villeModifiee) {
        List<Departement> departement = departementService.modifierDepartement(id, villeModifiee);
        if (departement != null) {
            return new ResponseEntity<>(departement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Departement>> supprimerDepartement(@PathVariable Long id) {
        List<Departement> departement = departementService.supprimerDepartement(id);
        if (departement != null) {
            return new ResponseEntity<>(departement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    


    @GetMapping("/{num}/{nb}")
    public ResponseEntity<List<Ville>> getNBiggestCities(@PathVariable Long num, @PathVariable int n) {
        List<Ville> villes = departementService.getNBiggestCities(num, n);
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }

    @GetMapping("/{num}/{minPopulation}/{maxPopulation}")
    public ResponseEntity<List<Ville>> getCitiesByPopulationRange(@PathVariable Long num, @PathVariable int minPopulation, @PathVariable int maxPopulation) {
        List<Ville> villes = departementService.getCitiesByPopulationRange(num, minPopulation, maxPopulation);
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }
}
