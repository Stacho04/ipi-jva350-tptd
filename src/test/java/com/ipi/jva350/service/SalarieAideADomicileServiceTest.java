package com.ipi.jva350.service;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SalarieAideADomicileServiceTest {

    @Autowired
    private SalarieAideADomicileService salarieService;

    @Autowired
    private SalarieAideADomicileRepository salarieRepository;

    @BeforeEach
    public void setup() {
        salarieRepository.deleteAll();
    }

    @Test
    public void testCalculeLimiteEntrepriseCongesPermis() {
        // Given :
        SalarieAideADomicile s1 = new SalarieAideADomicile();
        s1.setNom("Paul");
        s1.setCongesPayesAcquisAnneeNMoins1(30);
        s1.setCongesPayesPrisAnneeNMoins1(15);
        salarieRepository.save(s1);

        LocalDate moisEnCours = LocalDate.of(2023, 7, 1);
        double congesAcquis = 25.0;
        LocalDate moisDebutContrat = LocalDate.of(2020, 1, 1);
        LocalDate premierJourConge = LocalDate.of(2023, 7, 15);
        LocalDate dernierJourConge = LocalDate.of(2023, 7, 30);

        // When :
        long limite = salarieService.calculeLimiteEntrepriseCongesPermis(
                moisEnCours, congesAcquis, moisDebutContrat, premierJourConge, dernierJourConge);

        // Then :
        Assertions.assertTrue(limite > 0);
    }
}