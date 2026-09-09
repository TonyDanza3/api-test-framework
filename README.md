# trading-platform-tests

Black-box API tests for the trading-platform microservices (`../trading-platform`). Requests go through the API gateway; some market-data checks also read Postgres.


## Tech stack

| Piece | Version / lib |
|---|---|
| Language | Java 21 |
| Build | Maven |
| Runner | JUnit Jupiter 6 |
| HTTP | REST Assured 6 |
| JSON | Jackson 2.22 (Java time module) |
| Assertions | AssertJ 3.27, JUnit `Assertions` |
| Logging | Log4j2 2.26 (console, INFO) |
| Database | PostgreSQL JDBC 42.7 |

## What it covers

| Service | Port (direct) | How tests hit it |
|---|---|---|
| api-gateway | 8080 | Public entry. All HTTP calls use `BASE_URL` (gateway). |
| auth-service | 8082 | Via gateway `/api/v1/auth/*` |
| user-service | 8083 | Via gateway `/api/v1/users/*` |
| market-data-service | 8084 | Via gateway `/api/v1/instruments*` |
| postgres | 5432 | Direct JDBC from market-data tests |


## How to run

Start the platform first (`docker compose -f deployments/docker-compose.yml up --build` from trading-platform). Then from `trading-platform-tests`:

```bash
mvn test
mvn -Dtest=AuthServiceTest test
mvn -Dtest=MarketDataServiceTest#addInstrument test
```
