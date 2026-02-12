package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
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
}