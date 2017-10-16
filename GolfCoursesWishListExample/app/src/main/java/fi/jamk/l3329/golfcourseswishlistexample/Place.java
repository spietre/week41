package fi.jamk.l3329.golfcourseswishlistexample;

import android.content.Context;

/**
 * Created by peter on 15.10.2017.
 */

public class Place {
    public String name;
    public String imageName;

    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(this.imageName, "drawable", context.getPackageName());
    }
}
