package com.interactive_museguide.controller;

import com.interactive_museguide.repository.LocationRepository;
import com.interactive_museguide.service.NavigationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/navigation")
public class NavigationController {

  private final LocationRepository locationRepository;
  private final NavigationService navigationService;

  @GetMapping
  public String page(Model model) {

    model.addAttribute(
        "locations",
        locationRepository.findAll());

    return "navigation";
  }

  @PostMapping
  public String findRoute(
      Long startLocation,
      Long endLocation,
      Model model) {

    List<String> route =
        navigationService.findRoute(
            startLocation,
            endLocation);

    model.addAttribute("route", route);

    model.addAttribute(
        "locations",
        locationRepository.findAll());

    return "navigation";
  }
}
