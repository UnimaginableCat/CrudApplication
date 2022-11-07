package com.unimaginable.CrudApplication.controllers;

import com.unimaginable.CrudApplication.models.Car;
import com.unimaginable.CrudApplication.repos.CarsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CarController {

  @Autowired
  CarsRepository carsRepository;

  @PostMapping("/cars")
  public ResponseEntity<Car> createCar(@RequestBody Car car) {
    try {
      Car createdCar = carsRepository.save(
          new Car(car.getManufacturer(), car.getModel(), car.getMileage()));
      return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/cars/{id}")
  public ResponseEntity<Car> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
    Optional<Car> optionalCar = carsRepository.findById(id);
    if (optionalCar.isPresent()) {
      Car tempCar = optionalCar.get();
      tempCar.setManufacturer(car.getManufacturer());
      tempCar.setMileage(car.getMileage());
      tempCar.setModel(car.getModel());
      return new ResponseEntity<>(carsRepository.save(tempCar), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/cars")
  public ResponseEntity<List<Car>> getAllCars() {
    final List<Car> carsList = carsRepository.findAll();
    if (!carsList.isEmpty()) {
      return new ResponseEntity<>(carsList, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/cars/{id}")
  public ResponseEntity<Car> getCarById(@PathVariable("id") long id) {
    final Optional<Car> car = carsRepository.findById(id);
    return car.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/cars/{id}")
  public ResponseEntity<String> deleteCar(@PathVariable("id") long id) {
    try {
      carsRepository.deleteById(id);
      return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Something went wrong:(", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
