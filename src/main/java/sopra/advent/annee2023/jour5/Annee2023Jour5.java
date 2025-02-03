package sopra.advent.annee2023.jour5;

import org.apache.commons.lang3.StringUtils;
import sopra.advent.Jour;
import sopra.advent.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class Annee2023Jour5 extends Jour {
    // (?'sourceName'[a-z]+)-to-(?'destinationName'[a-z]+) map:\n((\d+)\s(\d+)\s(\d+)\n)*
    private Map<String, PhaseMapper> mapCorrespondancesSeeds(List<String> lignes) {
        Map<String, PhaseMapper> map = new HashMap<>();
        String regex = "(?<sourceName>[a-z]+)-to-(?<destinationName>[a-z]+) map:(?<mapping>[^a-z]*)";
        Matcher m = Utils.genererMatcher(regex, StringUtils.join(lignes, "\n"));
        while (m.find()) {
            String sourceName = m.group("sourceName");
            String destinationName = m.group("destinationName");
            String mapping = m.group("mapping");
            Matcher m2 = Utils.genererMatcher("(?<destination>\\d+) (?<source>\\d+) (?<taille>[0-9]+)*", mapping);
            List<LigneMapper> ligneMappers = new ArrayList<>();
            while (m2.find()) {
                String source = m2.group("source");
                String destination = m2.group("destination");
                String taille = m2.group("taille");
                ligneMappers.add(new LigneMapper(source, destination, taille));
            }
            map.put(sourceName, new PhaseMapper(destinationName, ligneMappers));
        }
        return map;
    }

    private List<Intervalle> mapSeeds(List<String> in) {
        Matcher m = Utils.genererMatcherAndFind("seeds: (.*)", in.get(0));
        return Utils.split(m.group(1), " ").stream()
                .map(Long::parseLong)
                .map(Intervalle::new)
                .collect(Collectors.toList());
    }

    private List<Intervalle> mapSeedsIntervalle(List<String> in) {
        List<Intervalle> list = new ArrayList<>();
        Matcher m = Utils.genererMatcher("(\\d+) (\\d+)", in.get(0));
        while (m.find()) {
            long debutIntervalle = Long.parseLong(m.group(1));
            long tailleIntervalle = Long.parseLong(m.group(2)) - 1;
            list.add(new Intervalle(debutIntervalle, debutIntervalle + tailleIntervalle));
        }
        return list;
    }


    public Object executePartie1(List<String> in) {
        return this.exec(in, this.mapSeeds(in));
    }


    public Object executePartie2(List<String> in) {
        return exec(in, this.mapSeedsIntervalle(in));
    }

    private Object exec(List<String> in, List<Intervalle> intervalles) {
        Map<String, PhaseMapper> mappingMap = this.mapCorrespondancesSeeds(in.subList(1, in.size()));
        PhaseMapper phaseMapper = mappingMap.get("seed");
        List<Intervalle> newIntervalles = new ArrayList<>(intervalles);

        while (phaseMapper != null) {
            newIntervalles = phaseMapper.calculerNouveauxIntervalles(newIntervalles);
            phaseMapper = mappingMap.get(phaseMapper.getDestinationName());
        }
        return newIntervalles.stream()
                .min(Intervalle::compare)
                .map(Intervalle::getMin)
                .orElse(Long.MAX_VALUE);
    }


}
