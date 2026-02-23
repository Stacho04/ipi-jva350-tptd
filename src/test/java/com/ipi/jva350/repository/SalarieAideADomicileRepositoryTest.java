package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SalarieAideADomicileRepositoryTest {

    @Autowired
    private SalarieAideADomicileRepository salarieAideADomicileRepository;

    @BeforeEach
    public void before() {
    	salarieAideADomicileRepository.deleteAll();
    }
    
    @Test
    public void testFindNomPasPresent() {
        // Given
    	
        // When
        SalarieAideADomicile VivianRes = salarieAideADomicileRepository.findByNom("Vivian");
        // Then
        Assertions.assertNull(VivianRes);
    }
    
    @Test
    public void testFindByNomPresent() {
        // Given
    	SalarieAideADomicile VivianExistant = new SalarieAideADomicile();
    	VivianExistant.setNom("Vivian");
    	salarieAideADomicileRepository.save(VivianExistant);
        // When
        SalarieAideADomicile VivianRes = salarieAideADomicileRepository.findByNom(VivianExistant.getNom());
        // Then
        Assertions.assertEquals(VivianExistant.getNom(), VivianRes.getNom());
    }
    
    @Test
    public void testPartCongesPrisTotauxAnneeNMoins1() {
        // Given :
        SalarieAideADomicile s1 = new SalarieAideADomicile();
        s1.setNom("Jean");
        s1.setCongesPayesAcquisAnneeNMoins1(20);
        s1.setCongesPayesPrisAnneeNMoins1(5);
        salarieAideADomicileRepository.save(s1);

        SalarieAideADomicile s2 = new SalarieAideADomicile();
        s2.setNom("Marc");
        s2.setCongesPayesAcquisAnneeNMoins1(30);
        s2.setCongesPayesPrisAnneeNMoins1(15);
        salarieAideADomicileRepository.save(s2);

        // When :
        Double part = salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1();

        // Then :
        Assertions.assertEquals(0.4, part, 0.001);
    }
}