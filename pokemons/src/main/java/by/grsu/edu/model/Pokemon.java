package by.grsu.edu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pokemon {
    private String name;
    private List<String> types = new ArrayList<>();
    private String pngImageUrl;
    private String svgImageUrl;
    private Map<String, Integer> stats;
    private String  evolution;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getPngImageUrl() {
        return pngImageUrl;
    }

    public void setPngImageUrl(String pngImageUrl) {
        this.pngImageUrl = pngImageUrl;
    }

    public String getSvgImageUrl() {
        return svgImageUrl;
    }

    public void setSvgImageUrl(String svgImageUrl) {
        this.svgImageUrl = svgImageUrl;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public String getEvolution() {
        return evolution;
    }

    public void setEvolution(String evolution) {
        this.evolution = evolution;
    }
}
