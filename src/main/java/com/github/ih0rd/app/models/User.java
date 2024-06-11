package com.github.ih0rd.app.models;

import lombok.Builder;

@Builder
public record User(
        int id, String name, String username, String email, Address address, String phone, String website,
        Company company) {
    @Builder
    public record Address(String street, String suite, String city, String zipcode, Geo geo) {
    }

    @Builder
    public record Geo(String lat, String lng) {
    }

    @Builder
    public record Company(String name, String catchPhrase, String bs) {
    }
}
