package mx.edu.ito.estres.domain.model;

public record Student(
        Long id,
        String email,
        String password,
        Integer semester,
        String origin,
        Integer sleepHours) {

    public Student {
        if (email == null || !email.endsWith("@itoaxaca.edu.mx")) {
            throw new IllegalArgumentException("Only institutional emails are allowed");
        }
        if (semester == null || semester < 1 || semester > 4) {
            throw new IllegalArgumentException("Target population semester is 1st to 4th semester");
        }
    }
}
