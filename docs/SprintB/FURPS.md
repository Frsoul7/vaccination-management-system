Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._

List:
* The user manual **(including FAQ)** must be delivered with the application (help)
* Security: Authentication **using AuthFacade and AuthLib of Java**
* **Hide the password when user is typing it with JPasswordField**
* **Provide different formats for printing, like pdf and jpeg**
* **Provide services that allow application to send emails with javax.mail.jar**

## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

List:
* **Search option, through a search bar**
* **Implementation of the “3-click-rule”**
* **High user experience through easy legible typeface (e.g. Helvetica)**
* **Validation of data inserted by the user**
* **Accessibility API using javax.accessibility**


## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

List:
* The application should use object serialization to ensure data persistence between two runs of the application (integrity)
* **Recovery through frequent back-up of data to cloud server**
* **System overload - system capacity (lot of users scheduling vaccines, the system will prioritize nurses)**

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

List:
* The application should run on Microsoft Windows, macOS and several Unix-like OSs.
* **Side-by-side co-existence of different versions: zero-downtime deployments for all components** (maturity)


## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, instability, scalability and more._


List:
* All the images/figures produced during the software development process should be recorded in SVG format (compatibility, adaptability)
* Implement unit tests for all methods, except for methods that implement Input/Output operations. The unit tests should be implemented using the JUnit 5 framework. The JaCoCo plugin should be used to generate the coverage report. (testability)
* The application must support, at least, the Portuguese and the English languages. (adaptability, configurability)
* Adopt recognized coding standards (e.g., CamelCase) (conformity)

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._


List:
* The application must be developed in Java language using the IntelliJ IDE or NetBeans. (interface)
* The application graphical interface is to be developed in JavaFX 11. (interface)



### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

List:
* In the case of a working day, with a center open from 8 a.m. until 8 p.m., a list with 144 integers is obtained, where a positive integer means that in such a five-minute slot more clients arrive at the center for vaccination than clients leave with the vaccination process completed. A negative integer means the opposite. (code implementation)
* For any time interval on one day the difference between the number of new clients arrivel and the number of cliente leaving the center every five minuteperiod is computed; (routines, implementation)
* Use Javadoc to generate useful documentation for Java code (implementation)


### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

List:
* **Possibility to connect to input systems, such as card readers, bar code or QR code readers**

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

List:
* n/a