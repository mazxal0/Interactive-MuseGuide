package com.interactive_museguide.controller;

import com.interactive_museguide.dto.EdgeRequest;
import com.interactive_museguide.model.Edge;
import com.interactive_museguide.service.EdgeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/api/edges")
public class EdgeController {

  private final EdgeService edgeService;

  @GetMapping
  public List<Edge> getAll() {

    return edgeService.getAll();

  }

  @GetMapping("/{id}")
  public Edge getById(
      @PathVariable Long id) {

    return edgeService.getById(id);
  }

  @PostMapping
  public ResponseEntity<Edge> create(
      @RequestBody EdgeRequest request) {

    Edge edge = edgeService.create(request);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(edge);
  }

  @PutMapping("/{id}")
  public Edge update(
      @PathVariable Long id,
      @RequestBody EdgeRequest request) {

    return edgeService.update(id, request);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @PathVariable Long id) {

    edgeService.delete(id);

    return ResponseEntity.noContent().build();
  }
}