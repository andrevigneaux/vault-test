package com.andre.vaulttest.repository;

import com.andre.vaulttest.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
