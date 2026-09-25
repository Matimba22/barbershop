\# Manyika Hi Mbita's Haircut Barbershop



A full-stack barbershop booking website built with Java, Spring Boot, HTML, CSS, JavaScript, and a relational database.



\## Live Website



https://manyika-himbita-barbershop.onrender.com



\## Project Overview



Manyika Hi Mbita's Haircut Barbershop is a responsive web application that allows customers to explore barbershop services, learn about the barbers, view contact information, and book appointments online.



The application includes a complete booking journey and calendar integration so customers can add their appointments to their preferred calendar application.



\## Features



\* Responsive homepage and navigation

\* Services and pricing page

\* Barber profiles

\* Online appointment booking

\* Service, barber, date, and time selection

\* Customer contact details

\* Booking confirmation

\* Google Calendar integration

\* Apple / Other Calendar integration using `.ics`

\* Welcome popup/modal

\* Contact information and business hours

\* Terms \& Conditions page

\* Responsive design for desktop, tablet, and mobile devices



\## Technologies Used



\### Backend



\* Java 17

\* Spring Boot

\* Spring MVC

\* Spring Data JPA

\* Hibernate



\### Frontend



\* HTML5

\* CSS3

\* JavaScript



\### Database



\* H2 Database



\### Build \& Deployment



\* Maven

\* Docker

\* Git

\* GitHub

\* Render



\## Booking \& Calendar Integration



After a customer submits a booking, the application saves the appointment and displays the booking details.



Customers can then:



\* Add the appointment to Google Calendar

\* Download an `.ics` calendar event compatible with Apple Calendar and other calendar applications



The calendar event includes the appointment date, start time, end time, service, barber, duration, and location.



\## Running the Project Locally



\### Requirements



\* Java 17 or later

\* Maven

\* Git



\### Run with Maven Wrapper



On Windows:



```bash

mvnw.cmd spring-boot:run

```



Or build the application:



```bash

mvnw.cmd clean package

```



Then run the generated JAR file:



```bash

java -jar target/barbershop-0.0.1-SNAPSHOT.jar

```



The application will be available at:



```text

http://localhost:8080

```



\## Project Structure



```text

barbershop/

├── src/

│   └── main/

│       ├── java/

│       │   └── com/manyikahimbita/barbershop/

│       │       ├── BarbershopApplication.java

│       │       ├── PageController.java

│       │       ├── Booking.java

│       │       ├── BookingRepository.java

│       │       ├── BookingController.java

│       │       └── CalendarController.java

│       │

│       └── resources/

│           ├── static/

│           │   ├── style.css

│           │   ├── booking.js

│           │   └── modal.js

│           ├── templates/

│           │   ├── index.html

│           │   ├── services.html

│           │   ├── about.html

│           │   ├── booking.html

│           │   ├── contact.html

│           │   └── terms.html

│           └── application.properties

│

├── Dockerfile

├── pom.xml

└── README.md

```



\## Author



\*\*Matimba Manyiki\*\*



GitHub: https://github.com/Matimba22



LinkedIn: https://www.linkedin.com/in/owen-matimba



