package at.ac.fhcampuswien.fhmdb.DataLayer;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "MovieEntity")
public class MovieEntity {
    @DatabaseField(id = true)
    private long id;
    @DatabaseField
    private String apiId;
    @DatabaseField
    private String title;
    @DatabaseField
    private String description;
    @DatabaseField
    private String genres;
    @DatabaseField
    private int releaseYear;
    @DatabaseField
    private String imgUrl;
    @DatabaseField
    private int lengthInMinutes;
    @DatabaseField
    private double rating;

//    public String genresToString(<Genres> genres) {
//
//
//        for (int i = 0; i < genres.length(); i++){
//
//        }
//    }



}
