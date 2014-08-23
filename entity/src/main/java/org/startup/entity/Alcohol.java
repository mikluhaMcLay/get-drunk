package org.startup.entity;

public class Alcohol {
    private final double degree;
    private final String name;
    private final AlkoCategory category;
    private final Brand brand;

    public Alcohol( double degree, String name, AlkoCategory category, Brand brand ) {
        this.degree = degree;
        this.name = name;
        this.category = category;
        this.brand = brand;
    }

    public double getDegree() {
        return degree;
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

        if ( Double.compare( alcohol.degree, degree ) != 0 ) return false;
        if ( brand != alcohol.brand ) return false;
        if ( category != alcohol.category ) return false;
        if ( name != null ? !name.equals( alcohol.name ) : alcohol.name != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits( degree );
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Alcohol{" +
                "degree=" + degree +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", brand=" + brand +
                '}';
    }
}
