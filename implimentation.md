API Details:
1. /v1/bfs/booking  - API to fetch all the bookings.
2. /v1/bfs/booking.  - API to create booking.

Swagger UI is available at : http://localhost:8080/swagger-ui.html#/

Expected POST response with following JSON:
{
  "address": {
    "city": "string",
    "line1": "string",
    "line2": "string",
    "state": "string",
    "zipcode": 0
  },
  "checkin_datetime": "2021-10-09T04:46:22.264Z",
  "checkout_datetime": "2021-10-09T04:46:22.264Z",
  "date_of_birth": "1990-08-08",
  "deposit": 0,
  "first_name": "string",
  "last_name": "string",
  "total_price": 0
}
