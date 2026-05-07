# Lego REST Project

A Java RESTful web service and Lego EV3 robot application that communicate in real time through a network.

The system allows a user to control a Lego robot from a web browser while the robot also sends sensor data and movement data back to the server. All data is stored in a MySQL database running in Docker.

---

# Project Overview

The project contains two main applications:

## 1. RESTful Web Service (`legorest2-main`)

The backend web service built with:

- Java
- Jakarta REST (JAX-RS)
- JPA
- MySQL
- Docker
- Tomcat

This application:

- Receives commands from the browser
- Saves robot settings into the database
- Receives sensor data from the Lego robot
- Provides statistics and sensor event history
- Sends the latest settings back to the robot

---

## 2. Lego Robot Application (`TWOPROPERTIES`)

The Java application running on the Lego EV3 robot using LeJOS.

This application:

- Reads commands from the REST service
- Controls the motors
- Detects walls using the ultrasonic sensor
- Detects black lines using the color sensor
- Sends live sensor data back to the REST service

---

# Main Features

- Control robot movement from browser
- Increase/decrease speed
- Turn left/right
- Start and stop robot
- Wall detection using ultrasonic sensor
- Black line detection using color sensor
- Automatic obstacle avoidance
- Real-time statistics dashboard
- Sensor event history table
- Database persistence using MySQL
- Multi-threaded Lego robot application

---

# Sensors Used

## Ultrasonic Sensor
Used to detect walls or obstacles in front of the robot.

Behaviour:
- Robot stops near obstacle
- Robot turns around
- Robot continues moving

## Color Sensor
Used to detect black lines on the floor.

Behaviour:
- Robot detects dark surface
- Robot reacts automatically

---

# REST Endpoints

| Endpoint | Method | Purpose |
| `/rest/lego/getlego` | GET | Test service |
| `/rest/lego/setvalues` | POST | Save robot settings |
| `/rest/lego/getvalues` | GET | Robot reads latest settings |
| `/rest/lego/setsensordata` | POST | Save sensor data |
| `/rest/lego/getsensordata` | GET | Get recent sensor events |
| `/rest/lego/getstats` | GET | Get dashboard statistics |

---

# Technologies Used

- Java
- HTML
- CSS
- JavaScript
- JAX-RS
- JPA
- MySQL
- Docker
- Apache Tomcat
- Lego EV3
- LeJOS
- Git & GitHub

---

# Database Tables

## lego
Stores robot movement commands and settings.

Fields:
- id
- run
- speed
- turn
- aika

## sensordata
Stores robot sensor events.

Fields:
- id
- sensorType
- sensorValue
- speed
- turn
- action
- aika

---

# Running the Project

## Backend

1. Start Docker Desktop
2. Open project folder in VS Code
3. Run: docker-compose up --build
