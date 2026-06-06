package com.interactive_museguide.controller;

import com.interactive_museguide.dto.RouteRequest;
import com.interactive_museguide.dto.RouteResponse;
import com.interactive_museguide.service.NavigationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/navigation")
public class NavigationController {

  private final NavigationService navigationService;

  @PostMapping("/route")
  public ResponseEntity<RouteResponse> findRoute(
      @RequestBody RouteRequest request) {

    RouteResponse response =
        new RouteResponse(
            navigationService.findRoute(
                request.startLocation(),
                request.endLocation()
            )
        );

    return ResponseEntity.ok(response);
  }


}
