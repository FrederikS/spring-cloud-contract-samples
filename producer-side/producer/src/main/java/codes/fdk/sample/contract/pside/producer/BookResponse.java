package codes.fdk.sample.contract.pside.producer;

import java.util.UUID;

public record BookResponse(UUID isbn, String title, String description) {}
