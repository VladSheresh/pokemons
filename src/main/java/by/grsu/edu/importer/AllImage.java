package by.grsu.edu.importer;

import com.google.gson.annotations.SerializedName;

public class AllImage {
    @SerializedName("other")
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
