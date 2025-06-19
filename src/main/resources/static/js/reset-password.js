$('#resetForm').submit(function (e) {
  e.preventDefault();

  const email = $('#email').val();
  const newPassword = $('#newPassword').val();
  const confirmPassword = $('#confirmPassword').val();

  if (!email || !newPassword || !confirmPassword) {
    $('#resetMessage').css('color', 'red').text('Please fill out all fields.');
    return;
  }

  if (newPassword !== confirmPassword) {
    $('#resetMessage').css('color', 'red').text('Passwords do not match.');
    return;
  }

  // Proceed with AJAX request
  $.ajax({
    url: 'http://localhost:8008/api/reset-password',
    method: 'POST',
    contentType: 'application/json',
    data: JSON.stringify({ email: email, newPassword: newPassword }),
    success: function () {
      alert('✅ Password reset successful! This window will now close.');
      window.close();
      setTimeout(() => {
        window.location.href = 'login.html';
      }, 2000);
    },
    error: function () {
      $('#resetMessage').css('color', 'red').text('Error resetting password. Try again.');
    }
  });
});
