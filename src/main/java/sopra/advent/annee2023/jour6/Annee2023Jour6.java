package sopra.advent.annee2023.jour6;

import sopra.advent.Jour;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Annee2023Jour6 extends Jour {

    public Object executePartie1(List<String> in) {
        return this.mapperCourses(in)
                .stream()
                .map(Course::calculerNombreVictoiresPossibles)
                .reduce(1L, Math::multiplyExact);
    }

    private List<Course> mapperCourses(List<String> in) {
        Matcher m1 = Pattern.compile("(\\d+)").matcher(in.get(0));
        Matcher m2 = Pattern.compile("(\\d+)").matcher(in.get(1));
        List<Course> courses = new ArrayList<>();
        while (m1.find() && m2.find()) {
            courses.add(new Course(Integer.parseInt(m1.group(1)), Integer.parseInt(m2.group(1))));
        }
        return courses;
    }

    private Course mapperCourses2(List<String> in) {
        Matcher m1 = Pattern.compile("(\\d+)").matcher(in.get(0));
        Matcher m2 = Pattern.compile("(\\d+)").matcher(in.get(1));
        StringBuilder time = new StringBuilder();
        StringBuilder distance = new StringBuilder();
        List<Course> courses = new ArrayList<>();
        while (m1.find() && m2.find()) {
           time.append(m1.group(1));
           distance.append(m2.group(1));
        }
        return new Course(Long.parseLong(String.valueOf(time)),Long.parseLong(String.valueOf(distance)));
    }

    public Object executePartie2(List<String> in) {
        return this.mapperCourses2(in).calculerNombreVictoiresPossibles();
    }
}
