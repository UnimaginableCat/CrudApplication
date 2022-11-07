package com.unimaginable.CrudApplication.repos;

import com.unimaginable.CrudApplication.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Car, Long> {

}
