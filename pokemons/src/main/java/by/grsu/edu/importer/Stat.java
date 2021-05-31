import com.google.gson.annotations.SerializedName;

public class Stat {
   private int base_stat;
    @SerializedName("stat")
   private StatName statName;

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public StatName getStatName() {
        return statName;
    }

    public void setStatName(StatName statName) {
        this.statName = statName;
    }
}
