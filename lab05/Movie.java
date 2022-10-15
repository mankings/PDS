/* comentando porque erros irritam-me

import java.util.List;

public class Movie {
    private final String title;
    private final int year;
    private final Person director;
    private final Person writer;
    private final String series;
    private final List<Person> cast;
    private final List<Place> locations;
    private final List<String> languages;
    private final List<String> genres;
    private final boolean isTelevision;
    private final boolean isNetflix;
    private final boolean isIndependent;

    private Movie(MovieBuilder builder) {
        title = builder.title;
        year = builder.year;
        director = builder.director;
        writer = builder.writer;
        series = builder.series;
        cast = builder.cast;
        locations = builder.locations;
        languages = builder.languages;
        genres = builder.genres;
        isTelevision = builder.isTelevision;
        isNetflix = builder.isNetflix;
        isIndependent = builder.isIndependent;
    }

    public static class MovieBuilder {
        // required
        private final String title;
        private final int year;
        private final Person director;
        private final Person writer;
        // optional
        private final String series = "";
        private final List<Person> cast = new List<Person>();
        private final List<Place> locations = new List<Place>();
        private final List<String> languages = new List<String>();
        private final List<String> genres = new List<String>();
        private final boolean isTelevision = false;
        private final boolean isNetflix = false;
        private final boolean isIndependent = false;

        public MovieBuilder(String title, int year, Person director, Person writer) {
            this.title = title;
            this.year = year;
            this.director = director;
            this.writer = writer;
        }

        public MovieBuilder series(String series) {
            this.series = series;
            return this;
        }

        public MovieBuilder cast(List<Person> cast) {
            this.cast = cast;
            return this;
        }

        public MovieBuilder locations(List<Place> locations) {
            this.locations = locations;
            return this;
        }

        public MovieBuilder languages(List<String> languages) {
            this.languages = languages;
            return this;
        }

        public MovieBuilder genres(List<String> genres) {
            this.genres = genres;
        }

        public MovieBuilder isTelevision(Boolean isTv) {
            this.isTelevision = isTv;
            return this;
        }

        public MovieBuilder isNetflix(Boolean isNf) {
            this.isNetflix = isNf;
            return this;
        }

        public MovieBuilder isIndependent(Boolean isIndie) {
            this.isIndependent = isIndie;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
*/