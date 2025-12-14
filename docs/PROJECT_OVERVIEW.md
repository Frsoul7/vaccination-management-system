# SNS COVID-19 Vaccination Management System

## Project Context

This project was developed during the 2021-2022 academic year at [Instituto Superior de Engenharia do Porto (ISEP)](http://www.isep.ipp.pt) as part of the **Informatics Engineering (LEI)** degree program. It addresses the real-world challenge of managing mass vaccination campaigns during the COVID-19 pandemic.

The system digitalizes and optimizes the vaccination process for Portugal's **Serviço Nacional de Saúde (SNS)**, covering the complete workflow from user registration and appointment scheduling to vaccine administration, adverse reaction monitoring, and performance analysis of vaccination centers.

### Business Domain

The application manages multiple actors and workflows in the vaccination ecosystem:

- **SNS Users**: Citizens registering for vaccination, scheduling appointments, checking in at centers, and obtaining digital vaccination certificates (EU COVID Digital Certificate)
- **Nurses**: Healthcare professionals administering vaccines and recording dosage information
- **Receptionists**: Staff managing user check-ins and waiting room flow
- **Center Coordinators**: Personnel monitoring vaccination center performance and analyzing operational efficiency
- **Administrators**: System managers configuring centers, vaccines, employees, and user data

The domain model encompasses complex entities including vaccination centers (healthcare centers and mass vaccination centers), vaccine types and brands (Pfizer, Moderna, AstraZeneca), age-based dosage administration, multi-dose scheduling with time intervals, recovery periods, adverse reaction tracking, and performance analytics.

---

## Technical Architecture

### Technology Stack

**Core Technologies:**
- **Java 11** - Primary programming language
- **JavaFX 18.0.1** - Rich graphical user interface framework with FXML layouts
- **Maven 3.x** - Build automation and dependency management

**Testing & Quality:**
- **JUnit 5.8.2** - Unit testing framework
- **JaCoCo** - Code coverage analysis
- **PlantUML** - UML diagram generation for documentation

**Libraries & Frameworks:**
- **Apache Commons Lang 3.12.0** - Utility functions for strings, arrays, and validation
- **Passay 1.6.1** - Password generation and validation
- **AuthLib** - Custom authentication and authorization component with role-based access control

**Build & CI/CD:**
- **Bitbucket Pipelines** - Continuous integration and automated testing
- **Maven Shade Plugin** - Creating executable JARs with dependencies

### Architectural Patterns

#### 1. **Layered Architecture**
The application follows a clear separation of concerns across multiple layers:

```
Presentation Layer (UI)
    ↓
Controller Layer (Application Logic)
    ↓
Domain Layer (Business Logic)
    ↓
Persistence Layer (Data Management)
```

**Presentation Layer**: JavaFX GUI components (`.fxml` files) and console interfaces for user interaction

**Controller Layer**: Controller classes implementing the **Controller Pattern**, mediating between UI and domain logic while maintaining decoupling

**Domain Layer**: Core business entities (`SnsUser`, `Vaccine`, `VaccinationCenter`, `Appointment`, `VaccineAdministration`) with rich domain logic

**Persistence Layer**: Java serialization for data persistence (`.ser` files), with file-based I/O operations

#### 2. **Design Patterns**

**Singleton Pattern**
- `App` class ensuring single instance of the application
- Thread-safe lazy initialization with synchronized blocks
- Centralized access to `Company` and `AuthFacade`

**Repository/Store Pattern**
- Store classes managing collections of domain entities: `EmployeeStore`, `SnsUserStore`, `VaccineStore`, `VaccinationCenterStore`
- Encapsulation of data access logic with CRUD operations
- Support for filtering, searching, and validation

**Data Transfer Object (DTO) Pattern**
- DTOs like `PerformanceAnalysisDTO`, `VaccineAdministrationDTO` separating presentation data from domain objects
- Reducing coupling between layers
- Optimizing data transfer across architectural boundaries

**Model-View-Controller (MVC)**
- JavaFX controllers managing view logic and user interactions
- FXML files defining declarative UI layouts
- Domain models containing business rules independent of presentation

**Factory Pattern**
- Object creation logic centralized in store classes
- Validation during instantiation
- Consistent object construction across the application

**Strategy Pattern**
- Algorithm configuration via `config.properties` (BubbleSort vs MergeSort, BruteForce vs Benchmark)
- Dynamic algorithm selection at runtime
- Adherence to Open/Closed Principle

#### 3. **Object-Oriented Programming Principles**

**Encapsulation**
- Private attributes with controlled access via getters/setters
- Validation logic embedded in domain classes
- Information hiding through well-defined interfaces

**Inheritance & Polymorphism**
- `VaccinationCenter` as parent class with specializations (`HealthCareCenter`, `MassVaccinationCenter`)
- `Person` superclass for common user attributes (`SnsUser`, `Employee`)
- Abstract algorithms interface for sorting and searching implementations

**Composition & Aggregation**
- `Company` aggregates multiple stores
- `VaccinationCenter` composes schedules, appointments, and vaccine administrations
- Loose coupling through composition over inheritance

**SOLID Principles**
- **Single Responsibility**: Each class has one clear purpose (e.g., `Validations` for validation logic, `FileReadAndSave` for persistence)
- **Open/Closed**: Extensible design through configuration (algorithm selection, vaccine types)
- **Liskov Substitution**: Polymorphic vaccine administration across vaccine types
- **Interface Segregation**: Role-specific interfaces in authentication system
- **Dependency Inversion**: High-level modules depend on abstractions (AuthFacade interface)

---

## Project Management Methodology

### Agile Development - Scrum Framework

The project was executed across **four sprints** (Sprint A through D), following Scrum methodology:

#### **Sprint A: Project Analysis**
- Requirements elicitation and analysis
- Domain modeling with UML class diagrams
- Glossary definition with 40+ domain terms
- FURPS+ specification (Functionality, Usability, Reliability, Performance, Supportability)
- Use Case diagram and detailed Use Case descriptions

#### **Sprint B: Core Features** (Console UI)
- US001: Schedule vaccination appointment
- US002: Schedule vaccination for SNS User (as receptionist)
- US003: Register SNS User
- US004: Register arrival of SNS User at vaccination center
- US005: Consult users in waiting room
- US008: Record vaccine administration
- US009: Register vaccination center
- US010: Register employee
- US011: List employees with specific role
- US012: Specify new vaccine type
- US013: Specify new vaccine and administration process

#### **Sprint C: Advanced Features** (Console UI)
- US014: Load users from CSV file
- US015: Export vaccination center statistics to CSV
- US016: Analyze vaccination center performance (brute-force algorithm)

#### **Sprint D: GUI Implementation & Legacy Integration**
- US017: Import data from legacy systems (CSV)
- Graphical User Interface (JavaFX) for all previous user stories
- Implementation of sorting algorithms (MergeSort, BubbleSort)
- Performance benchmarking capabilities

### Documentation Standards

Each User Story includes comprehensive documentation:
- **User Story Description**: Acceptance criteria and definition of done
- **Customer Specifications**: Requirements clarifications
- **Acceptance Criteria**: Testable conditions for completion
- **Dependencies**: Relationships with other user stories
- **System Sequence Diagrams (SSD)**: User interaction flows
- **Domain Model**: Class diagrams with relationships
- **Sequence Diagrams (SD)**: Detailed object interactions and message flows
- **Class Diagrams (CD)**: Static structure and relationships

All diagrams created using **PlantUML** (.puml files), version-controlled with evolution tracked across sprints (historic folders).

### Team Collaboration Tools

- **Git/Bitbucket**: Version control with branching strategy
- **Bitbucket Pipelines**: Automated builds and test execution
- **Maven**: Dependency management and build standardization
- **JaCoCo**: Continuous quality monitoring with coverage reports

---

## Key Features Implemented

### 1. **User Registration & Authentication**
- Role-based access control with 6 user roles (Administrator, SNS User, Nurse, Receptionist, Center Coordinator, DGS)
- User registration with validation (SNS number, email, phone, citizen card)
- Secure login system with AuthLib integration

### 2. **Vaccination Scheduling**
- SNS Users schedule appointments selecting center, date, time, and vaccine type
- Availability checking based on center capacity and time slots
- SMS/Email notifications for appointment confirmation
- Age group-based vaccine eligibility

### 3. **Vaccination Center Management**
- Registration of healthcare centers and mass vaccination centers
- Center configuration: name, address, phone, email, fax, website, opening hours, slot duration, maximum vaccines per slot
- Center coordinator assignment

### 4. **Vaccine Administration Workflow**
- User check-in at reception with QR code or SNS number
- Waiting room management with real-time tracking
- Nurse interface for vaccine administration recording
- Lot number, dosage, and administration date/time capture
- Recovery period monitoring
- Adverse reaction tracking

### 5. **Data Import & Export**
- CSV file import for bulk user registration (US014)
- Legacy system data import with sorting algorithms (US017)
- Vaccination statistics export to CSV (US015)

### 6. **Performance Analysis** (US016)
- Analyze vaccination center efficiency over time periods
- Brute-force algorithm finding contiguous time intervals with maximum difference between arrivals and departures
- Identification of "worst performance" periods for operational improvements
- Configurable time interval analysis (e.g., 30-minute slots)
- JavaFX GUI with date/interval input and results visualization

### 7. **Digital Vaccination Certificate**
- EU COVID Digital Certificate generation
- Includes vaccination date, vaccine type, lot number, and expiration date
- QR code or unique identifier for verification

---

## Testing & Quality Assurance

### Testing Strategy

- **Unit Tests**: JUnit 5 test suite with 219 tests covering core domain logic
- **Test Coverage**: JaCoCo plugin tracks coverage, excluding UI and I/O operations from metrics
- **Test Files**: 11 test classes focusing on critical business logic (validation, domain models, algorithms)

### Code Quality Standards

- **Coding Conventions**: CamelCase naming, Java coding standards
- **Code Style**: Enforced via `javaCodeStyle.xml` configuration
- **Documentation**: Javadoc comments for public APIs
- **UML Diagrams**: SVG format for compatibility and scalability

### Continuous Integration

- **Bitbucket Pipelines**: Automated build on every commit
- **Maven Lifecycle**: `mvn clean package` compiles 133 source files and runs tests
- **Artifact Generation**: Executable JAR with dependencies (`Sem2App-1.0-SNAPSHOT-jar-with-dependencies.jar`)

---

## Data Management

### Persistence Strategy

The application uses **Java Object Serialization** for data persistence:

**Serialized Entities:**
- `Employee.ser` - Employee records with roles
- `SnsUser.ser` - SNS user profiles
- `SnsUsersEmailsAndPasswords.ser` - Authentication credentials
- `VaccinationCenter.ser` - Vaccination center configurations
- `Vaccine.ser` - Vaccine definitions
- `VaccineType.ser` - Vaccine type classifications

**File I/O Operations:**
- `FileReadAndSave` class handles serialization/deserialization
- Data persists between application runs
- No external database required for academic demonstration

### Configuration Management

**config.properties**
- Algorithm selection (sorting, performance analysis)
- File delimiters for CSV import
- Application behavior configuration without code changes

---

## Algorithms & Data Structures

### Sorting Algorithms (US017)
- **MergeSort**: O(n log n) time complexity, stable sorting
- **BubbleSort**: O(n²) comparison-based sorting (educational implementation)
- Configurable selection via properties file
- Used for sorting vaccination data by arrival/departure times

### Performance Analysis Algorithm (US016)
- **Brute-Force Maximum Subarray**: Finds contiguous time interval with maximum sum of (arrivals - departures)
- O(n²) time complexity algorithm
- Identifies worst-performing periods for center optimization
- Configurable time interval granularity (e.g., 30-minute slots)

### Validation Patterns
- Regular expressions for email, phone, SNS number, citizen card validation
- Custom validation rules for age groups, vaccine dosages, appointment conflicts
- Centralized `Validations` class with reusable validation logic

---

## Running the Application

### Prerequisites
- Java 11 or higher
- Maven 3.x

### Build & Execution

**Build the application:**
```bash
mvn clean package
```

**Run console version:**
```bash
java -jar target/Sem2App-1.0-SNAPSHOT-jar-with-dependencies.jar --console
```

**Run graphical version:**
```bash
java -jar target/Sem2App-1.0-SNAPSHOT-jar-with-dependencies.jar --graphic
```

**Run tests:**
```bash
mvn test
```

**Generate coverage report:**
```bash
mvn clean test jacoco:report
```
Report available at: `target/site/jacoco/index.html`

---

## Project Structure

```
ersmoreira-lei-22-s2-1na-g074/
│
├── docs/                          # Documentation artifacts
│   ├── SprintA/                   # Requirements & domain modeling
│   ├── SprintB/                   # Console features documentation
│   ├── SprintC/                   # Advanced features documentation
│   ├── SprintD/                   # GUI & legacy integration docs
│   ├── Glossary.md               # Domain terminology
│   └── FURPS.md                  # Non-functional requirements
│
├── src/
│   ├── main/
│   │   ├── java/                 # Source code
│   │   │   └── app/
│   │   │       ├── controller/   # Application controllers
│   │   │       ├── domain/       # Domain models & business logic
│   │   │       │   ├── model/    # Entity classes
│   │   │       │   ├── store/    # Repository pattern stores
│   │   │       │   └── algorithms/ # Sorting & analysis algorithms
│   │   │       ├── dto/          # Data Transfer Objects
│   │   │       ├── service/      # Business services
│   │   │       ├── ui/           # Console UI
│   │   │       └── ui.gui/       # JavaFX GUI controllers
│   │   └── resources/
│   │       └── fxml/             # JavaFX FXML layouts
│   │
│   └── test/
│       └── java/                 # JUnit test classes
│
├── serFiles/                      # Serialized data files
├── csvFiles/                      # Sample CSV imports
├── pom.xml                       # Maven configuration
├── config.properties             # Application configuration
└── bitbucket-pipelines.yml       # CI/CD pipeline
```

---

## Learning Outcomes

This project demonstrates proficiency in:

✅ **Object-Oriented Programming**: Design patterns, SOLID principles, inheritance, polymorphism, encapsulation

✅ **Software Engineering**: Requirements analysis, UML modeling, architectural design, layered architecture

✅ **Agile Methodology**: Scrum framework, sprint planning, incremental development, iterative refinement

✅ **Java Ecosystem**: JavaFX GUI development, Maven build automation, JUnit testing, file I/O, serialization

✅ **Version Control**: Git workflows, branching strategies, CI/CD pipelines

✅ **Algorithm Design**: Sorting algorithms, performance analysis, algorithm complexity understanding

✅ **Domain Modeling**: Complex business rules, entity relationships, data validation

✅ **Quality Assurance**: Unit testing, code coverage, continuous integration, coding standards

✅ **Documentation**: Technical writing, UML diagrams, user story specifications, architectural documentation

---

## Academic Context

**Institution**: Instituto Superior de Engenharia do Porto (ISEP)  
**Program**: Degree in Informatics Engineering (LEI)  
**Academic Year**: 2021-2022 (2nd Semester)  
**Course**: Laboratório de Projeto e Análise de Requisitos 2 (LAPR2)  

This project represents the culmination of 4 sprints of work, demonstrating the ability to develop a complex, multi-user enterprise application addressing a real-world healthcare challenge during the COVID-19 pandemic.

---

## Acknowledgments

Developed as part of the academic curriculum at ISEP, applying software engineering best practices, object-oriented design principles, and agile methodologies to solve a meaningful problem in public health management.
