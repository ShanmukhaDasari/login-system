$(document).ready(function () {
  $('#forgotForm').submit(function (e) {
    e.preventDefault();

    const email = $('#forgotEmail').val();

    if (!email) {
      $('#forgotMessage').css('color', 'red').text('Please enter your email.');
      return;
    }

    // 🔁 Send email to backend for password retrieval
    $.ajax({
      url: 'http://localhost:8008/api/forgot-password', // Replace with your backend endpoint
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({ email: email }),
      success: function (response) {
        $('#forgotMessage').css('color', 'green').text(' Reset Password Link has been sent to your email.');
      },
      error: function () {
        $('#forgotMessage').css('color', 'red').text('Email not found or server error.');
      }
    });
  });
});
