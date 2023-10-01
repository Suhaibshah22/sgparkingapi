# Singapore Parking Lot API - Suhaib Shah

### Introduction

This is a demonstration of an API that will query for parking lots (in Singapore) by GEO location and provide the nearest parking lots that have lots available. the dataset is provided by data.gov.sg.

This Project is built using Java Spring boot and some of the more common java packages are being used, like Spring Data JPA, Spring Web and Lombok (helps reduce writing boilerplate code).

#### Important Notes

1. As stated in the requirement document, emphasis is not on how the data is being loaded therefore in the interest of saving time, the dataset retreived from data.gov.sg is stored in csv format and is being parsed and loaded into the database in order to seed the database. Implementing an API call to data.gov.sg should be a straight forward affair.

### Setup Instructions

1. Clone this repo and cd into it.

```
$ git clone git@github.com:Suhaibshah22/sgparkingapi.git
$ cd ./sgparkingapi
```

2. Setup the database. You need to have docker installed.

```
$ docker compose up -d
```

3. Using your query editor of choice (dbeaver, pgAdmin), create the database schema.

```
CREATE TABLE parking_lots (id varchar, address varchar, geom geometry(Point, 3414), available_lots int, total_lots int);
```

4. **Important** | Create the GIST index on geometry column in order to perform proper geo queries (K-Nearest neighbor).

```
CREATE INDEX geom_idx ON parking_lots USING gist(geom);
```

5. Start the service using maven. It should start on port 8980.

```
$ mvn spring-boot:run
```

6. Seed the database with the two following api calls.

```
$ curl --request GET \
  --url http://localhost:8980/carparks/load-parking-lots
```

```
$ curl --request GET \
  --url http://localhost:8980/carparks/update-lot-availability
```

The parking lot API should now correctly return the nearest parking spaces when querying by location.

```
$ curl --request GET \
  --url 'http://localhost:8980/carparks/nearest?latitude=1.37326&longitude=103.897&page=1&pageSize=5'
```
