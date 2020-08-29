package org.psk.practice.converter;

/**
 * The Enum Unit for scaling units.
 *
 * @author pkabiraj
 */
public enum Unit {
    Quadrillion(15),
    Trillion(12),
    Billion(9),
    Million(6),
    Thousand(3),
    Hundred(2);

    private final int exponent;
    private final String name;

    /**
     * Instantiates a new unit.
     *
     * @param exponent the exponent
     */
    Unit(int exponent) {
        this.exponent = exponent;
        this.name = this.name();
    }

    /**
     * Gets the exponent.
     *
     * @return the exponent
     */
    public int getExponent() {
        return exponent;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}