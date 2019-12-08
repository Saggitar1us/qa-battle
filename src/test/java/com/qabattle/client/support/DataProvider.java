package com.qabattle.client.support;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

/**
 * @author Aleksei Stepanov
 */

public class DataProvider {

    public static Stream<Arguments> getAmountArticles() {
        return Stream.of(
                of("Advertisers", 2),
                of("Publishers", 2),
                of("Top level clients", 10)
        );
    }

    public static Stream<Arguments> getArticles() {
        return Stream.of(
                of("Advertisers", "Test Advertiser"),
                of("Advertisers", "Adidas"),
                of("Publishers", "Youtube"),
                of("Publishers", "Instagram"),
                of("Top level clients", "Jon Snow"),
                of("Top level clients", "Artur Fleck"),
                of("Top level clients", "Tim Cook"),
                of("Top level clients", "Bugs Bunny"),
                of("Top level clients", "Sasha Grey"),
                of("Top level clients", "You"),
                of("Top level clients", "Leonel Messi"),
                of("Top level clients", "Tony Stark"),
                of("Top level clients", "Elon Musk"),
                of("Top level clients", "Darth Vader")
        );
    }

    public static Stream<Arguments> getCard() {
        return Stream.of(
                of("Test Advertiser"),
                of("Adidas"),
                of("Youtube"),
                of("Instagram"),
                of("Jon Snow"),
                of("Artur Fleck"),
                of("Tim Cook"),
                of("Bugs Bunny"),
                of("Sasha Grey"),
                of("You"),
                of("Leonel Messi"),
                of("Tony Stark"),
                of("Elon Musk"),
                of("Darth Vader")
        );
    }

}
