package org.example.algorithmegenetique.classapps;

public class Ville {
    private double lon;
    private double lat;
    private String nom;

    public Ville(double lon, double lat, String nom) {
        this.lon = lon;
        this.lat = lat;
        this.nom = nom;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getNom() {
        return nom;
    }

    public double distance(Ville ville) {
        double distanceX = (ville.getLon() - this.lon) * 40000 * Math.cos((this.lat + ville.getLat()) * Math.PI / 360) / 360;
        double distanceY = (this.lat - ville.getLat()) * 40000 / 360;
        return Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
    }
}
