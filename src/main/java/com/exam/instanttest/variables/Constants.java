package com.exam.instanttest.variables;

public enum Constants {

    PARKINGS_URL("https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilite-parkings-grand-poitiers-donnees-metiers&rows=1000&facet=nom_du_parking&facet=zone_tarifaire&facet=statut2&facet=statut3"),
    PARKING_SLOTS_URL("https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilites-stationnement-des-parkings-en-temps-reel&facet=nom");

    private String url;
 
    Constants(String apiUrl) {
        this.url = apiUrl;
    }
 
    public String getUrl() {
        return url;
    }
}
