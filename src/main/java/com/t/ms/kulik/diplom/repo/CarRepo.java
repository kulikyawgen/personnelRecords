package com.t.ms.kulik.diplom.repo;

import com.t.ms.kulik.diplom.domain.auto.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepo extends CrudRepository<Car, Long> {
}
