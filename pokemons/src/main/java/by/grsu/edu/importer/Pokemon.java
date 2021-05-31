package by.grsu.edu.importer;

import java.util.List;

public class Pokemon {
    private String name;
    private String url;
    private List<Type> types;
    private List<Stat> stats;

    public AllImage getSprites() {
        return sprites;
    }

    public void setSprites(AllImage sprites) {
        this.sprites = sprites;
    }

    private AllImage sprites;

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }
}
