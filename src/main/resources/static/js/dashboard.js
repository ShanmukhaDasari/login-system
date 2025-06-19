// Simulate logged-in state (in real app, this should be set during login)
const userEmail = localStorage.getItem('email');
const userRole = localStorage.getItem('role');

// Show user email
document.addEventListener("DOMContentLoaded", function () {
  if (!userEmail || !userRole) {
    alert("Unauthorized access. Please login.");
    window.location.href = "login.html";
    return;
  }

  document.getElementById("userEmail").innerText = `Logged in as: ${userEmail}`;

  // Redirect if wrong role opens the page
  const isAdminPage = window.location.pathname.includes("admin-dashboard");
  if (isAdminPage && userRole !== "ADMIN") {
    alert("Access denied. Admins only.");
    window.location.href = "login.html";
  }
});

function logout() {
  sessionStorage.clear();
  window.location.href = "login.html";
}
