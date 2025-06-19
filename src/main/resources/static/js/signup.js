$(document).ready(function () {
  $('#signupForm').submit(function (e) {
    e.preventDefault();

    const email = $('#email').val();
    const password = $('#password').val();
    const confirmPassword = $('#confirmPassword').val();

    if (!email || !password || !confirmPassword) {
      $('#signupMessage').css('color', 'red').text('Please fill in all fields.');
      return;
    }

    if (password !== confirmPassword) {
      $('#signupMessage').css('color', 'red').text('Passwords do not match.');
      return;
    }

    // 🔁 Send data to Spring Boot backend
    $.ajax({
      url: 'http://localhost:8008/api/signup', // ✅ Replace with your actual API endpoint
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({
        email: email,
        password: password
      }),
      success: function (response) {
        $('#signupMessage').css('color', 'green').text('Signup successful!');
        document.getElementById('signupForm').reset();
      },
      error: function () {
        $('#signupMessage').css('color', 'red').text('Signup failed. Email may already exist.');
      }
    });
  });
});
