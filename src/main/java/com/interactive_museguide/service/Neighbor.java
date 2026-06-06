package com.interactive_museguide.service;

public record Neighbor(
    Long targetId,
    int distance,
    String instruction
) {}
