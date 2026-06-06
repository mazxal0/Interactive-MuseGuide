package com.interactive_museguide.service;

import com.interactive_museguide.dto.LocationRequest;
import com.interactive_museguide.model.Location;
import java.util.List;

public interface LocationService {

  Location create(LocationRequest request);

  Location update(Long id, LocationRequest request);

  void delete(Long id);

  Location getById(Long id);

  List<Location> getAll();

}
