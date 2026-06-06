package com.interactive_museguide.dto;


public record LocationRequest(

    // @NotBlank
    String name,

    String description
) {
}
