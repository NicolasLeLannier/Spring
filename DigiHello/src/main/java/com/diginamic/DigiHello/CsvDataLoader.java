/**
 *
 */
package com.diginamic.DigiHello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.diginamic.DigiHello.model.Departement;
import com.diginamic.DigiHello.model.Ville;
import com.diginamic.DigiHello.repository.DepartementRepository;
import com.diginamic.DigiHello.repository.VilleRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvDataLoader implements CommandLineRunner {

    private final ResourceLoader resourceLoader;
    private final VilleRepository villeRepository;
    private final DepartementRepository departementRepository;
    private final EntityManager entityManager;

    @Autowired
    public CsvDataLoader(ResourceLoader resourceLoader, VilleRepository villeRepository, DepartementRepository departementRepository, EntityManager entityManager) {
        this.resourceLoader = resourceLoader;
        this.villeRepository = villeRepository;
        this.departementRepository = departementRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        Resource resource = resourceLoader.getResource("file:C:/Users/azorg/Downloads/cities.csv");
//        File file = resource.getFile();
//        if (!file.exists()) {
//            throw new RuntimeException("Le fichier CSV n'existe pas");
//        }
//        Path path = Paths.get("C:/Users/azorg/Downloads/cities.csv");
//        try (BufferedReader reader = Files.newBufferedReader(path)) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] values = line.split(",");
//                String villeNom = values[3];
//                int villeNbHabitant = 10000;
//                String departementNom = values[6];
//                String departementNumero = values[7];
//
//                Departement departement = departementRepository.findByNom(departementNom);
//                if (departement == null) {
//                    departement = new Departement();
//                    departement.setNom(departementNom);
//                    departement.setNumero(departementNumero);
//                    departement = departementRepository.save(departement);
//                }
//                
//                System.out.println(values[9] + " | Insee : " + values[0]);
//
//                Ville ville = new Ville();
//                ville.setNom(villeNom);
//                ville.setNbHabitant(villeNbHabitant);
//                ville.setDepartement(departement);
//
//                Departement mergedDepartement = mergeDepartement(departement);
//                ville.setDepartement(mergedDepartement);
//                villeRepository.save(ville);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Erreur lors de la lecture du fichier CSV", e);
//        }
    }
    
    @Transactional
    public Departement mergeDepartement(Departement departement) {
        return entityManager.merge(departement);
    }

}

