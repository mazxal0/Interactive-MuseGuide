package com.interactive_museguide.dto;

public record RouteRequest(
    Long startLocation,
    Long endLocation
) {
}
