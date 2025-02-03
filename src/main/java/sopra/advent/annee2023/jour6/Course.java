package sopra.advent.annee2023.jour6;

class Course {
    private final long time;
    private final long record;

    public Course(long time, long record) {
        this.time = time;
        this.record = record;
    }

    public long calculerNombreVictoiresPossibles() {
        long result = 0;
        long currentSpeed = 0;
        for (long i = 0; i < time; i++) {

            if (currentSpeed * (time - i) > record)
                result++;
            currentSpeed++;
        }
        return result;
    }
}
