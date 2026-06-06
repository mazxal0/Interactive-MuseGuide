package com.interactive_museguide.dto;


public record LocationRequest(

    // @NotBlank
    String name,
    Integer floor,
    String description
) {
}
