# Webflux and RabbitMQ
Version : 3.0.6, Java : 17, JUnit : 5  

## RabbitMQ assumption
- It is assumed that RabbitMQ is running on `localhost:5672`  

Once started, application will start listening on port : 8181

## REST endpoint for single request
-  http://localhost:8181/user/publisher/create
- JSON object will be as follows:-
- ```json
  {
  "id": 12,
  "name": "Maratib Ali Khan",
  "dob": "1981-02-04"
  }
  ```

## Parallel Test
- Test class `WebClientTest.java` contains the parallel test.