package com.interactive_museguide.service;

import com.interactive_museguide.dto.LocationRequest;
import com.interactive_museguide.model.Location;
import com.interactive_museguide.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

  private final LocationRepository locationRepository;

  @Override
  public Location create(LocationRequest request) {

    Location location = new Location();

    location.setName(request.name());
    location.setFloor(request.floor());
    location.setDescription(request.description());
    location.setWarning(request.warning());

    return locationRepository.save(location);
  }

  @Override
  public Location update(
      Long id,
      LocationRequest request) {

    Location location =
        locationRepository.findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Location not found"));

    location.setName(request.name());
    location.setDescription(request.description());

    return locationRepository.save(location);
  }

  @Override
  public void delete(Long id) {

    if (!locationRepository.existsById(id)) {
      throw new EntityNotFoundException(
          "Location not found");
    }

    locationRepository.deleteById(id);
  }

  @Override
  public Location getById(Long id) {

    return locationRepository.findById(id)
        .orElseThrow(() ->
            new EntityNotFoundException(
                "Location not found"));
  }

  @Override
  public List<Location> getAll() {

    return locationRepository.findAll();

  }

}
