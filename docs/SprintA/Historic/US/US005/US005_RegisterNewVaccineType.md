# US 005 - Register a new vaccine type

## 1. Requirements Engineering

### 1.1. User Story Description

*As an administrator, I intend to specify a new vaccine type.*

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> _"An Administrator is responsible for properly configuring and managing the core information (e.g.:
  type of vaccines[...]"_

>  _"[...]the community mass vaccination centers are facilities specifically created to administer vaccines of a single type as[...]"_

> _"[...]healthcare center is associated with a given ARS (Administração Regional de Saúde) and
AGES (Agrupamentos de Centros de Saúde), and it can administer any type of vaccines (e.g.:
Covid-19, Dengue, Tetanus, smallpox)."_

> _"[...]it is worth noticing that for each type of vaccine, several vaccines might exist, each one
demanding a distinct administration process."_

> _"[...]wants to be vaccinated as well as the type of vaccine to be administered (by default, the system
suggests the one related to the ongoing outbreak)."_

> _"[...]The nurse checks the user info and health conditions in the system and in accordance with
the scheduled vaccine type, and the SNS user vaccination history[...]"_

> _"[...]nurse registers the event in the system, more precisely, registers the vaccine type (e.g.: Covid-19)[...]"_

**From the client clarifications:**

> **Question**: To define the type of vaccine, is it necessary more attributes than the name?
>
>  **Answer**:
>

> **Question**: What happens if the administrator specify one vaccine type that already was registered in system?
>
>  **Answer**:
>

> **Question**: What happens if the administrator makes a mistake when adding a new vaccine type?
>
>  **Answer**:
>

### 1.3. Acceptance Criteria

* **AC1:** Administrator users should be able to register a new type of vaccine.
* **AC2:** All attributes should be filled in to register a new type of vaccine.
* **AC3:** Other users than administrators shouldn't be able to register a new type of vaccine.

### 1.4. Found out Dependencies

* There is a dependency to "US026 Authentication" since a user is logged in as an administrator.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a name.


* Selected data:
    * n/a
    
**Output Data:**

* (In)Success of the operation,
* List of all vaccine types registered in system.

### 1.6. System Sequence Diagram (SSD)

*Insert here a SSD depicting the envisioned Actor-System interactions and throughout which data is inputted and outputted to fulfill the requirement. All interactions must be numbered.*

![USXXX-SSD](USXXX-SSD.svg)


### 1.7 Other Relevant Remarks

TBD [to be done]

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 
*In this section, it is suggested to present an excerpt of the domain model that is seen as relevant to fulfill this requirement.* 

![USXXX-MD](USXXX-MD.svg)

### 2.2. Other Remarks

*Use this section to capture some additional notes/remarks that must be taken into consideration into the design activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams).* 



## 3. Design - User Story Realization 

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1  		 |							 |             |                              |
| Step 2  		 |							 |             |                              |
| Step 3  		 |							 |             |                              |
| Step 4  		 |							 |             |                              |
| Step 5  		 |							 |             |                              |
| Step 6  		 |							 |             |                              |              
| Step 7  		 |							 |             |                              |
| Step 8  		 |							 |             |                              |
| Step 9  		 |							 |             |                              |
| Step 10  		 |							 |             |                              |  


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Class1
 * Class2
 * Class3

Other software classes (i.e. Pure Fabrication) identified: 
 * xxxxUI  
 * xxxxController

## 3.2. Sequence Diagram (SD)

*In this section, it is suggested to present an UML dynamic view stating the sequence of domain related software objects' interactions that allows to fulfill the requirement.* 

![USXXX-SD](USXXX-SD.svg)

## 3.3. Class Diagram (CD)

*In this section, it is suggested to present an UML static view representing the main domain related software classes that are involved in fulfilling the requirement as well as and their relations, attributes and methods.*

![USXXX-CD](USXXX-CD.svg)

# 4. Tests 
*In this section, it is suggested to systematize how the tests were designed to allow a correct measurement of requirements fulfilling.* 

**_DO NOT COPY ALL DEVELOPED TESTS HERE_**

**Test 1:** Check that it is not possible to create an instance of the Example class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}

*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)

*In this section, it is suggested to provide, if necessary, some evidence that the construction/implementation is in accordance with the previously carried out design. Furthermore, it is recommeded to mention/describe the existence of other relevant (e.g. configuration) files and highlight relevant commits.*

*It is also recommended to organize this content by subsections.* 

# 6. Integration and Demo 

*In this section, it is suggested to describe the efforts made to integrate this functionality with the other features of the system.*


# 7. Observations

*In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work.*





