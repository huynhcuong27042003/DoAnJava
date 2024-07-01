function formatDate(date) {
    const day = date.getDate();
    const month = date.getMonth() + 1; // Months are zero-indexed
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}

// Calculate the times
const now = new Date();
const tomorrow = new Date(now);

tomorrow.setDate(now.getDate() + 1);

const dayAfterTomorrow = new Date(now);

dayAfterTomorrow.setDate(now.getDate() + 2);

const rentalTimeElement = document.getElementById('rentalTime');

rentalTimeElement.textContent = `${formatDate(tomorrow)} 20:00 -> ${formatDate(dayAfterTomorrow)} 22:00`;


