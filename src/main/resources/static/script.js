let currentSessionId = "local-session-" + Math.random().toString(36).substring(2, 9);
let accumulatedText = "";

async function sendUssdRequest(currentInput = "") {
    const display = document.getElementById("display");

    if (currentInput.trim() !== "") {
        accumulatedText = accumulatedText ? accumulatedText + "*" + currentInput.trim() : currentInput.trim();
    }

    display.innerHTML = "Processing...";

    const formData = new FormData();
    formData.append("sessionId", currentSessionId);
    formData.append("phoneNumber", "251911223344");
    formData.append("text", accumulatedText);

    try {
        const response = await fetch("/ussd", {
            method: "POST",
            body: formData
        });

        const result = await response.text();

        let displayText = result;

        if (result.startsWith("CON ")) {
            displayText = result.substring(4);
        } else if (result.startsWith("END ")) {
            displayText = result.substring(4);
        }

        // Replace \n with <br> for proper line breaks
        display.innerHTML = displayText.replace(/\n/g, "<br>");

    } catch (err) {
        display.innerHTML = "Connection error.<br>Is Spring Boot running?";
        console.error(err);
    }

    // Auto clear input
    document.getElementById("inputField").value = "";
}

// Append from keypad
function appendToInput(digit) {
    document.getElementById("inputField").value += digit;
}

function dial() {
    const inputField = document.getElementById("inputField");
    sendUssdRequest(inputField.value);
}

function clearInput() {
    document.getElementById("inputField").value = "";
    accumulatedText = "";
    document.getElementById("display").innerHTML = "Session cleared.<br>Press DIAL to start again";
}

// Enter key support
document.addEventListener("DOMContentLoaded", () => {
    const inputField = document.getElementById("inputField");
    inputField.addEventListener("keypress", (e) => {
        if (e.key === "Enter") {
            e.preventDefault();
            dial();
        }
    });
});