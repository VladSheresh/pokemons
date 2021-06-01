package by.grsu.edu.importer;

import com.google.gson.annotations.SerializedName;

public class Type {
    @SerializedName("type")
    private TypeName typeName;

    public TypeName getTypeName() {
        return typeName;
    }

    public void setTypeName(TypeName typeName) {
        this.typeName = typeName;
    }
}
