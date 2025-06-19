$(document).ready(function () {
  // ✅ Toggle password visibility
  $('#togglePassword').on('change', function () {
    const passwordField = $('#password');
    const type = $(this).is(':checked') ? 'text' : 'password';
    passwordField.attr('type', type);
  });

  // ✅ Login form submission
  $('#loginForm').submit(function (e) {
    e.preventDefault();

    const email = $('#email').val();
    const password = $('#password').val();

    if (!email || !password) {
      $('#loginMessage').css('color', 'red').text('Please fill all fields.');
      return;
    }

    $.ajax({
      url: 'http://localhost:8008/api/login',
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({ email: email, password: password }),
      success: function (response) {
		// Store login flag and email
		 localStorage.setItem("loggedIn", "true");
		 localStorage.setItem("email", response.email);
		 localStorage.setItem("role", response.role);
        if (response.role === 'ADMIN') {
          window.location.href = 'admin-dashboard.html';
        } else if (response.role === 'USER') {
          window.location.href = 'user-dashboard.html';
        } else {
          $('#loginMessage').css('color', 'red').text('Unknown role.');
        }
      },
      error: function () {
        $('#loginMessage').css('color', 'red').text('Invalid credentials or server error.');
      }
    });
  });
});
