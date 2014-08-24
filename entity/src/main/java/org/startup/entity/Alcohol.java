package org.startup.entity;

public class Alcohol {
    private final String name;
    private final AlkoCategory category;
    private final Brand brand;

    public Alcohol(String name, AlkoCategory category, Brand brand ) {
        this.name = name;
        this.category = category;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public AlkoCategory getCategory() {
        return category;
    }

    public Brand getBrand() {
        return brand;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Alcohol alcohol = (Alcohol) o;

        if ( brand != alcohol.brand ) return false;
        if ( category != alcohol.category ) return false;
        if ( name != null ? !name.equals( alcohol.name ) : alcohol.name != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        long temp = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Alcohol{" +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", brand=" + brand +
                '}';
    }
}
