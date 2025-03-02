package com.MyGreetingApp.backend.repository;

import com.MyGreetingApp.backend.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting,Long> {
}
