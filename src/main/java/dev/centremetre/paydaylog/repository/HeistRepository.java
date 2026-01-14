package dev.centremetre.paydaylog.repository;

import dev.centremetre.paydaylog.model.Heist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for {@link Heist}.
 */
@Repository
public interface HeistRepository extends JpaRepository<Heist, Integer>
{
    // Heist findById(int id); // Not needed, already provided by JpaRepository

    Optional<Heist> findByName(String name);

    // List<Heist> findAll() // Not needed, already provided by JpaRepository
}
