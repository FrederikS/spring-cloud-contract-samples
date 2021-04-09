package codes.fdk.sample.contract.pside.producer;

import javax.validation.constraints.NotBlank;

public record PostBookRequest(@NotBlank String title, String description) {}
