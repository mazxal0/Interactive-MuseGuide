package com.interactive_museguide.dto;

public record EdgeRequest(

    Long fromLocationId,

    Long toLocationId,

    Integer distance,

    String instruction
) {
}
