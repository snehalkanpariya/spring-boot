# gvp-spring-boot-unit4 — GVP Book Store API (baseline, no security)

A plain Spring Boot CRUD API for a book store — no database (in-memory, reseeded on every
restart), no authentication, no authorization. Every endpoint is open.

This is deliberately the **starting point** for a teaching project. The full Unit 4
syllabus (Password Encoding, JWT, RBAC, OAuth2, Testing, Docker, Kubernetes) is taught as a
step-by-step build-up from this exact baseline — see
[`docs/unit4-guide.html`](docs/unit4-guide.html). Each step there gives you copy-paste-ready
code, the exact `pom.xml` dependency to add (if any), and curl/Postman commands to verify
that one step before moving to the next.

Postman collection (matches this baseline exactly): [`docs/GVP-Spring-Boot-Unit4.postman_collection.json`](docs/GVP-Spring-Boot-Unit4.postman_collection.json)

## Run it

```bash
mvn spring-boot:run
```

- App: http://localhost:8081 (different port from unit2's 8080, so both can run together)
- Swagger UI: http://localhost:8081/swagger-ui.html

## Try it

```bash
# Public reads
curl http://localhost:8081/api/v1/books
curl http://localhost:8081/api/v1/categories

# Writes - no token needed, nothing is protected yet
curl -X POST http://localhost:8081/api/v1/categories -H "Content-Type: application/json" \
  -d '{"name": "History", "description": "Historical non-fiction"}'

curl -X POST http://localhost:8081/api/v1/books -H "Content-Type: application/json" \
  -d '{"title": "Effective Java", "author": "Joshua Bloch", "categoryId": 1, "price": 45.00, "stock": 10}'

curl -X PUT http://localhost:8081/api/v1/books/1 -H "Content-Type: application/json" \
  -d '{"title": "Effective Java (3rd Ed)", "author": "Joshua Bloch", "categoryId": 1, "price": 49.99, "stock": 15}'

curl -i -X DELETE http://localhost:8081/api/v1/books/1
```

## What's here

| Package | Contents |
|---|---|
| `controller/` | `BookController`, `CategoryController` — plain `@RestController`s, no `@PreAuthorize` |
| `service/` | `BookService`/`Impl`, `CategoryService`/`Impl` — business logic, no method security |
| `model/` | `Book`, `Category` |
| `repository/` | `BookRepository`, `CategoryRepository` — `ConcurrentHashMap`-backed in-memory stores |
| `dto/` | `ApiResponse`, `ApiError`, request/response DTOs |
| `exception/` | `GlobalExceptionHandler`, `ResourceNotFoundException` |
| `runner/` | `DataSeeder` (seeds 2 categories + 3 books on startup), `StartupInfoLogger` |
| `config/` | `RequestLoggingFilter` (logs every API call), `SwaggerConfig` |

## Where the syllabus goes from here

Everything below is **not in this project yet** — it's added incrementally, one concept at
a time, by following `docs/unit4-guide.html`:

| Topic | What you'll add |
|---|---|
| Password Encoding | User registration + BCrypt hashing |
| Security Filters / Spring Security Architecture | `SecurityConfig`, a JWT filter |
| JWT Authentication (Basics) | Login endpoint issuing a signed token |
| Role-Based Access Control | Lock down writes to an ADMIN role, two ways |
| OAuth2 Basics | Google login as a second, session-based auth flow |
| JUnit 5 / Mockito / Spring Boot Testing | A test suite added back on top of the finished app |
| Packaging / Docker / Basic Kubernetes Concepts | `Dockerfile`, `docker-compose.yml`, `k8s/` manifests |

## Notes

- No database: `repository/*Repository.java` are `ConcurrentHashMap`-backed in-memory stores, seeded by `DataSeeder` on startup.
- Every request is logged once via `RequestLoggingFilter` before it reaches a controller — check the console.
