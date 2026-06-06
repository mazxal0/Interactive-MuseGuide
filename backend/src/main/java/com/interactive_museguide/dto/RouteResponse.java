package com.interactive_museguide.dto;

import java.util.List;

public record RouteResponse(
    List<String> instructions
) {
}
