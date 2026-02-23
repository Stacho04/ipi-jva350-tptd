package com.ipi.jva350.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class EntrepriseTest {

    @Test
    public void testEstDansPlage() {
        // Given
        LocalDate debut = LocalDate.of(2023, 1, 1);
        LocalDate fin = LocalDate.of(2023, 12, 31);
        LocalDate dateDansPlage = LocalDate.of(2023, 6, 15);
        LocalDate dateHorsPlage = LocalDate.of(2024, 1, 1);

        Assertions.assertTrue(Entreprise.estDansPlage(dateDansPlage, debut, fin));
        Assertions.assertFalse(Entreprise.estDansPlage(dateHorsPlage, debut, fin));
        
        Assertions.assertTrue(Entreprise.estDansPlage(debut, debut, fin));
        Assertions.assertTrue(Entreprise.estDansPlage(fin, debut, fin));
    }
    
    @ParameterizedTest
    @CsvSource({
        "2023-01-01, true", 
        "2023-05-01, true",  
        "2023-12-25, true", 
        "2023-06-15, false", 
        "2024-03-31, false"  
    })
    public void testEstJourFerie(String dateStr, boolean expected) {
        // Given
        LocalDate date = LocalDate.parse(dateStr);
        // When
        boolean result = Entreprise.estJourFerie(date);
        // Then
        Assertions.assertEquals(expected, result);
    }
}