package com.interactive_museguide.service;

import com.interactive_museguide.dto.EdgeRequest;
import com.interactive_museguide.model.Edge;
import com.interactive_museguide.model.Location;
import com.interactive_museguide.repository.EdgeRepository;
import com.interactive_museguide.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EdgeServiceImpl implements EdgeService {

  private final EdgeRepository edgeRepository;
  private final LocationRepository locationRepository;

  @Override
  public Edge create(EdgeRequest request) {

    Location fromLocation =
        locationRepository.findById(
                request.fromLocationId())
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Start location not found"));

    Location toLocation =
        locationRepository.findById(
                request.toLocationId())
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "End location not found"));

    Edge edge = new Edge();

    edge.setFromLocation(fromLocation);
    edge.setToLocation(toLocation);
    edge.setDistance(request.distance());
    edge.setInstruction(request.instruction());

    return edgeRepository.save(edge);
  }

  @Override
  public Edge update(
      Long id,
      EdgeRequest request) {

    Edge edge =
        edgeRepository.findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Edge not found"));

    Location fromLocation =
        locationRepository.findById(
                request.fromLocationId())
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Start location not found"));

    Location toLocation =
        locationRepository.findById(
                request.toLocationId())
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "End location not found"));

    edge.setFromLocation(fromLocation);
    edge.setToLocation(toLocation);
    edge.setDistance(request.distance());
    edge.setInstruction(request.instruction());

    return edgeRepository.save(edge);
  }

  @Override
  public void delete(Long id) {

    if (!edgeRepository.existsById(id)) {
      throw new EntityNotFoundException(
          "Edge not found");
    }

    edgeRepository.deleteById(id);
  }

  @Override
  public Edge getById(Long id) {

    return edgeRepository.findById(id)
        .orElseThrow(() ->
            new EntityNotFoundException(
                "Edge not found"));
  }

  @Override
  public List<Edge> getAll() {

    return edgeRepository.findAll();
  }
}
