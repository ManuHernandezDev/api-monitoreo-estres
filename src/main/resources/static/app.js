document.getElementById('registerForm').addEventListener('submit', function(e) {
       e.preventDefault();

       const requestData = {
           email: document.getElementById('email').value,
           password: document.getElementById('password').value,
           semester: parseInt(document.getElementById('semester').value),
           origin: document.getElementById('origin').value,
           sleepHours: parseInt(document.getElementById('sleepHours').value)
       };

       // Reemplaza la URL por la ruta exacta de tu StudentController
       fetch('/api/v1/students/register', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json'
           },
           body: JSON.stringify(requestData)
       })
       .then(response => {
           if (!response.ok) {
               throw new Error('Error al registrar estudiante en ATENEA');
           }
           return response.json();
       })
       .then(data => {
           const alertBox = document.getElementById('alertMessage');
           alertBox.innerHTML = `<div class="alert alert-success">Perfil creado. Preparando entorno...</div>`;

           setTimeout(() => {
               window.location.href = '/login.html';
           }, 2000);
       })
       .catch(error => {
           const alertBox = document.getElementById('alertMessage');
           alertBox.innerHTML = `<div class="alert alert-danger">${error.message}</div>`;
           console.error('Error:', error);
       });
   });