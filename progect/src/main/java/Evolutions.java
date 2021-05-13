import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Evolutions {
    private int count;
    private String next;
    @SerializedName("results")
    private List<Evolution> evolutionList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<Evolution> getEvolutionList() {
        return evolutionList;
    }

    public void setEvolutionList(List<Evolution> evolutionList) {
        this.evolutionList = evolutionList;
    }
}
