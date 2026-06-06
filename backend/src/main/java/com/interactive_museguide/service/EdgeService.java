package com.interactive_museguide.service;

import com.interactive_museguide.dto.EdgeRequest;
import com.interactive_museguide.model.Edge;
import java.util.List;

public interface EdgeService {

  Edge create(EdgeRequest request);

  Edge update(Long id, EdgeRequest request);

  void delete(Long id);

  Edge getById(Long id);

  List<Edge> getAll();

}
