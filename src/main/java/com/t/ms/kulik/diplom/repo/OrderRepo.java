package com.t.ms.kulik.diplom.repo;

import com.t.ms.kulik.diplom.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Long> {
}
