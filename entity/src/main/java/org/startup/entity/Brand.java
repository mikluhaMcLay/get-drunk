package org.startup.entity;

public enum Brand {
    SMIRNOFF("Smirnoff"),
    BACARDI("Bacardi"),
    MARTINI("Martini"),
    JOHNNIE_WALKER("Johnnie Walker"),
    JAEGERMEISTER("Jаеgermeister"),
    BALLANTINES("Ballantines"),
    JACK_DANIELS("Jack Daniels"),
    GUINNESS("Guinness"),
    SCOTLAND_BEER( "Scotland Beer" );

    private final String name;

    Brand( String name ) {
        this.name = name;
    }
}
