document.addEventListener("DOMContentLoaded", () => {
    const registerForm = document.getElementById("register-form");
    const loginForm = document.getElementById("login-form");
    const showLogin = document.getElementById("show-login");
    const showRegister = document.getElementById("show-register");

    // Переключение между формами
    showLogin.addEventListener("click", (e) => {
        e.preventDefault();
        registerForm.style.display = "none";
        loginForm.style.display = "block";
    });

    showRegister.addEventListener("click", (e) => {
        e.preventDefault();
        loginForm.style.display = "none";
        registerForm.style.display = "block";
    });

    // Обработка формы регистрации
    registerForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const username = document.getElementById("register-username").value;
        const email = document.getElementById("register-email").value;
        const password = document.getElementById("register-password").value;
        const terms = document.getElementById("terms").checked;

        if (!terms) {
            alert("You must accept the terms and conditions!");
            return;
        }

        const response = await fetch("/auth/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, email, password })
        });

        const data = await response.json();
        if (response.ok) {
            alert("Registration successful! Please log in.");
            registerForm.style.display = "none";
            loginForm.style.display = "block";
        } else {
            alert(data);
        }
    });

    // Обработка формы логина
    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const username = document.getElementById("login-username").value;
        const password = document.getElementById("login-password").value;

        const response = await fetch("/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, password })
        });

        if (response.ok) {
            alert("Login successful!");
            window.location.href = "/";
        } else {
            alert("Invalid username or password.");
        }
    });
});
