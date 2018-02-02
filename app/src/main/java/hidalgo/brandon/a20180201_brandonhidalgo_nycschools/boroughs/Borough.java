package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs;

import android.graphics.drawable.Drawable;

/**
 * A class containing borough information
 */
public class Borough {
    private Drawable photo;

    private String name;

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
