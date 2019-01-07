
# Interview calendar API

This repository contains a REST API for an interview calendar implemented with Jersey and Spring Boot.

## How to run 

Make sure you have installed Maven command line tool and Java with version at least 1.8.

To run the application locally, clone the repository, navigate to the folder and type:

```
mvn spring-boot:run
```

The server will be available at http://localhost:8080

## Endpoints
 
Swagger documentation of the API can be found here: https://app.swaggerhub.com/apis-docs/monochronique/Interview_Calendar/1.0.0#/

**Important:** `Content-Type: application/json` header must be present to use API.

The most common HTTP status codes are returned when there is an error.

### Add an interviewer

```
/interviewers [POST]

Content-Type: application/json

{
	"name": "Mihail",
	"availability": [{
			"from": 9,
			"to": 14,
			"days": ["15/09/2019", "16/09/2019"]
		},
		{
			"from": 9,
			"to": 12,
			"days": ["17/09/2019"]
		}
	]
}
```

The "availability" object specifies a list of available times for the interviewer. In this example, the interviewer "Mihail" is available from 9 to 14 on "15/09/2019" and "16/09/2019" and from 9 to 12 on "17/09/2019". The "days" parameter should be a list of dates with format "dd/mm/yyyy".

Upon successfull creation, the server will respond with 201: Created and there will be a "Location" HTTP header, specifying the URL to the newly created interviewer.

### Update an interviewer

```
/interviewers/{id} [POST]

Content-Type: application/json

{
	"name": "Mihail",
	"availability": [{
			"from": 9,
			"to": 14,
			"days": ["15/09/2019", "16/09/2019"]
		}]
}
```

### List all interviewers

```
/interviewers [GET]
```

Lists all available interviewers known to the system, including their IDs.

### Get an interviewer

```
/interviewers/{id} [GET]
```

Gets an interviewer with given id.

### Add a candidate

```
/candidates [POST]

Content-Type: application/json

{
	"name": "John",
	"availability": [{
			"from": 9,
			"to": 19,
			"days": ["15/09/2019", "16/09/2019", "17/09/2019"]
		}]
}
```
The request has the same structure as creating an interviewer.

Upon successfull creation, the server will respond with 201: Created and there will be a "Location" HTTP header, specifying the URL to the newly created candidate.

### Update a candidate

```
/candidates/{id} [POST]

Content-Type: application/json

{
	"name": "John",
	"availability": [{
			"from": 9,
			"to": 17,
			"days": ["15/09/2019", "16/09/2019"]
		}]
}
```

### List all candidates

```
/candidates [GET]
```

Lists all available candidates known to the system, including their IDs.

### Get a candidate

```
/candidates/{id} [GET]
```

Gets a candidate with given id.


### Calculate possible interviewing hours

```
/availability [POST]

Content-Type: application/json

{
    "candidateId": 1,
    "interviewerIds": [1, 2]
}
```

Provide the ID of the candidate and a list of IDs of interviewers. 