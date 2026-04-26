document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("studentForm");
    const messageDiv = document.getElementById("message");

    form.addEventListener("submit", async function (e) {
        e.preventDefault();

        const semester = parseInt(document.getElementById("semester").value);
        const sleepHours = parseInt(document.getElementById("sleepHours").value);

        // 🔥 Validaciones frontend
        if (semester < 1 || semester > 4) {
            showError("El semestre debe estar entre 1 y 4");
            return;
        }

        if (sleepHours < 0 || sleepHours > 20) {
            showError("Horas de sueño inválidas (0 - 20)");
            return;
        }

        const data = {
            email: document.getElementById("email").value,
            password: document.getElementById("password").value,
            semester: semester,
            origin: document.getElementById("origin").value,
            sleepHours: sleepHours,
            privacyAccepted: document.getElementById("privacy").checked
        };

        try {
            const response = await fetch("/api/v1/students/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            });

            const text = await response.text();

            if (response.ok) {
                showSuccess(text);
                form.reset();
            } else {
                showError(text);
            }

        } catch (error) {
            showError("Error al conectar con el servidor");
        }
    });

    function showSuccess(msg) {
        messageDiv.innerHTML = `<div class="alert alert-success">${msg}</div>`;
    }

    function showError(msg) {
        messageDiv.innerHTML = `<div class="alert alert-danger">${msg}</div>`;
    }

});