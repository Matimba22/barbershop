const bookingForm = document.getElementById("bookingForm");
const bookingMessage = document.getElementById("bookingMessage");

bookingForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const service = document.getElementById("service").value;

    const durations = {
        "Classic Haircut": 30,
        "Fade Haircut": 45,
        "Haircut & Beard": 60,
        "Beard Trim": 30,
        "Kids Haircut": 30
    };

    const booking = {
        service: service,
        barber: document.getElementById("barber").value,
        date: document.getElementById("date").value,
        time: document.getElementById("time").value,
        duration: durations[service],
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value
    };

    try {
        const response = await fetch("/api/bookings", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(booking)
        });

        if (!response.ok) {
            throw new Error("Booking could not be completed.");
        }

        const savedBooking = await response.json();

        const googleCalendarUrl =
            `/api/calendar/google?service=${encodeURIComponent(savedBooking.service)}` +
            `&barber=${encodeURIComponent(savedBooking.barber)}` +
            `&date=${savedBooking.date}` +
            `&time=${savedBooking.time}` +
            `&duration=${savedBooking.duration}`;

        const appleCalendarUrl =
            `/api/calendar/ics?service=${encodeURIComponent(savedBooking.service)}` +
            `&barber=${encodeURIComponent(savedBooking.barber)}` +
            `&date=${savedBooking.date}` +
            `&time=${savedBooking.time}` +
            `&duration=${savedBooking.duration}`;

        bookingMessage.innerHTML = `
            <div class="booking-success">
                <h3>Booking confirmed!</h3>

                <p>
                    Thank you, ${savedBooking.name}.
                    Your ${savedBooking.service} with ${savedBooking.barber}
                    is booked for ${savedBooking.date} at ${savedBooking.time}.
                </p>

                <p>
                    Duration: ${savedBooking.duration} minutes
                </p>

                <div class="calendar-buttons">
                    <a href="${googleCalendarUrl}" target="_blank">
                        Add to Google Calendar
                    </a>

                    <a href="${appleCalendarUrl}">
                        Add to Apple / Other Calendar
                    </a>
                </div>
            </div>
        `;

        bookingForm.reset();

    } catch (error) {
        bookingMessage.innerHTML = `
            <p>
                <strong>Sorry, your booking could not be completed.</strong>
                Please try again.
            </p>
        `;
    }
});