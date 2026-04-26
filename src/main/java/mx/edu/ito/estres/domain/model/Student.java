package mx.edu.ito.estres.domain.model;

public record Student(
        Long id,
        String email,
        String password,
        Integer semester,
        String origin,
        Integer sleepHours,
        Boolean privacyAccepted) {

    public Student {
        if (email == null || !email.endsWith("@itoaxaca.edu.mx")) {
            throw new IllegalArgumentException("Solo correos institucionales permitidos");
        }

        if (semester == null || semester < 1 || semester > 4) {
            throw new IllegalArgumentException("El semestre debe estar entre 1 y 4");
        }

        if (sleepHours == null || sleepHours < 0 || sleepHours > 20) {
            throw new IllegalArgumentException("Horas de sueño inválidas (0 - 20)");
        }

        if (privacyAccepted == null || !privacyAccepted) {
            throw new IllegalArgumentException("Debes aceptar el aviso de privacidad");
        }
    }
}
