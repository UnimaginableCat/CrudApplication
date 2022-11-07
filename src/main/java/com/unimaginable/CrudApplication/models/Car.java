package com.unimaginable.CrudApplication.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "Cars")
public class Car {

  public Car(Long id, String manufacturer, String model, Integer mileage) {
    this.id = id;
    this.manufacturer = manufacturer;
    this.model = model;
    this.mileage = mileage;
  }

  public Car(String manufacturer, String model, Integer mileage) {
    this.manufacturer = manufacturer;
    this.model = model;
    this.mileage = mileage;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  private Long id;

  @Getter
  @Setter
  private String manufacturer;

  @Getter
  @Setter
  private String model;

  @Getter
  @Setter
  private Integer mileage;
}
