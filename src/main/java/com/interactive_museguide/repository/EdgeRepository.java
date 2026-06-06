package com.interactive_museguide.repository;

import com.interactive_museguide.model.Edge;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdgeRepository extends JpaRepository<Edge, Long> {

  List<Edge> findAll();

}