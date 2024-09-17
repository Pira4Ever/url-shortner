[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[MARIA_DB_BADGE]: https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white
# URL Shortener
![spring][SPRING_BADGE]
![java][JAVA_BADGE]
![mariadb][MARIA_DB_BADGE]

This is a URL shortener project built using Java and the Spring framework.

## Features
- Shorten long URLs
- Redirect to the original URL via the shortened link

## Tech Stack
- Java
- Spring Boot
- Maven

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+

### Running the Project
1. Clone the repository:
    ```bash
   git clone https://github.com/Pira4Ever/url-shortner.git
    ```
2. Navigate to the project directory: 
    ```bash
   cd url-shortner
    ```
3. Build and run the project:
    ```bash
   mvn spring-boot:run
    ```

## Usage
After starting the app, you can shorten URLs by accessing: http://localhost:8080

## 📍 API Endpoints
| route                         | description                              |                                      
|-------------------------------|------------------------------------------|
| <kbd>GET /{id}</kbd>          | redirect to the original url based on id |
| <kbd>POST /authenticate</kbd> | create and return the short url          |

### GET /{id}
### POST /
**REQUEST**
```json
{
  "urlLong": "https://example.com/"
}
```
**RESPONSE**
```json
{
  "urlLong": "https://example.com/",
  "id": "VjMD6"
}
```

## License
Url shortner is [MIT licensed](./LICENSE)