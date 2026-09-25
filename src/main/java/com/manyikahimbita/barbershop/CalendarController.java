package com.manyikahimbita.barbershop;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    private static final String LOCATION =
            "123 Main Street, Johannesburg, Gauteng";

    @GetMapping("/api/calendar/google")
    public ResponseEntity<Void> googleCalendar(
            @RequestParam String service,
            @RequestParam String barber,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam int duration) {

        LocalDateTime start = LocalDateTime.parse(
                date + "T" + time);

        LocalDateTime end = start.plusMinutes(duration);

        String startUtc = start
                .atOffset(ZoneOffset.ofHours(2))
                .withOffsetSameInstant(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));

        String endUtc = end
                .atOffset(ZoneOffset.ofHours(2))
                .withOffsetSameInstant(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));

        String title = "Barbershop Appointment - " + service;
        String details = "Barber: " + barber
                + "\nService: " + service
                + "\nDuration: " + duration + " minutes";

        String calendarUrl = "https://calendar.google.com/calendar/render?action=TEMPLATE"
                + "&text=" + encode(title)
                + "&dates=" + startUtc + "/" + endUtc
                + "&details=" + encode(details)
                + "&location=" + encode(LOCATION);

        return ResponseEntity.status(302)
                .header(HttpHeaders.LOCATION, calendarUrl)
                .build();
    }

    @GetMapping("/api/calendar/ics")
    public ResponseEntity<String> appleCalendar(
            @RequestParam String service,
            @RequestParam String barber,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam int duration) {

        LocalDateTime start = LocalDateTime.parse(
                date + "T" + time);

        LocalDateTime end = start.plusMinutes(duration);

        String startUtc = start
                .atOffset(ZoneOffset.ofHours(2))
                .withOffsetSameInstant(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));

        String endUtc = end
                .atOffset(ZoneOffset.ofHours(2))
                .withOffsetSameInstant(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));

        String ics = """
                BEGIN:VCALENDAR
                VERSION:2.0
                PRODID:-//Manyika Hi Mbita's Haircut Barbershop//EN
                BEGIN:VEVENT
                UID:%s@manyikahimbita
                DTSTAMP:%s
                DTSTART:%s
                DTEND:%s
                SUMMARY:Barbershop Appointment - %s
                DESCRIPTION:Barber: %s\\nService: %s\\nDuration: %d minutes
                LOCATION:%s
                END:VEVENT
                END:VCALENDAR
                """.formatted(
                System.currentTimeMillis(),
                startUtc,
                startUtc,
                endUtc,
                escape(service),
                escape(barber),
                escape(service),
                duration,
                escape(LOCATION));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=barbershop-appointment.ics")
                .contentType(MediaType.parseMediaType("text/calendar"))
                .body(ics);
    }

    private String encode(String value) {
        return URLEncoder.encode(
                value,
                StandardCharsets.UTF_8);
    }

    private String escape(String value) {
        return value
                .replace("\\", "\\\\")
                .replace(",", "\\,")
                .replace(";", "\\;")
                .replace("\n", "\\n");
    }
}