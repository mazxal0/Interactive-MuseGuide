package com.interactive_museguide.controller;

import com.interactive_museguide.dto.LocationRequest;
import com.interactive_museguide.model.Location;
import com.interactive_museguide.service.LocationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {

  private final LocationService locationService;

  @GetMapping
  public List<Location> getAllLocations() {
    return locationService.getAll();
  }

  @GetMapping("/{id}")
  public Location getLocation(
      @PathVariable Long id) {

    return locationService.getById(id);
  }

  @PostMapping
  public Location createLocation(
      @RequestBody LocationRequest request) {

    return locationService.create(request);
  }

  @PutMapping("/{id}")
  public Location updateLocation(
      @PathVariable Long id,
      @RequestBody LocationRequest request) {

    return locationService.update(id, request);
  }

  @DeleteMapping("/{id}")
  public void deleteLocation(
      @PathVariable Long id) {

    locationService.delete(id);
  }
}