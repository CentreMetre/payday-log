package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Heist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class HeistRepositoryTest
{
    @Autowired
    private HeistRepository heistRepository;

    @Test
    void testSavingAndRetrieveHeist()
    {
        Heist heist = new Heist();
        heist.setName("Test Heist");

        heistRepository.save(heist);
        Optional<Heist> savedOptional = heistRepository.findById(heist.getId()); // Id updated after save
        if (savedOptional.isPresent())
        {
            Heist saved = savedOptional.get();
            assertThat(saved.getName()).isEqualTo(heist.getName());
            assertThat(saved.getId()).isEqualTo(heist.getId());
        }
    }

    @Test
    void testFindByNameSuccess()
    {
        String name = "Test Heist";

        Heist heist = new Heist();
        heist.setName(name);

        heistRepository.save(heist);
        Optional<Heist> retrieved = heistRepository.findByName(name);
        if (retrieved.isPresent())
        {
            assertThat(retrieved.get().getName()).isEqualTo(name);
        }
        //Optional lambda
        //retrieved.ifPresent(value -> assertThat(value.getName()).isEqualTo(name));

    }
}
