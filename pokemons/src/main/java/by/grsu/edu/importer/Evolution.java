package by.grsu.edu.importer;

import com.google.gson.annotations.SerializedName;

public class Evolution {
    @SerializedName("chain")
    private Evol evol;
    private String url;

    public Evol getEvol() {
        return evol;
    }

    public void setEvol(Evol evol) {
        this.evol = evol;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
