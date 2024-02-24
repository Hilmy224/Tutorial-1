# Weekly Tutorials
link to [Tutorial Website](https://tutorial-advpro24-hilmy224.koyeb.app)

## Week 01

### Reflection 1
After reviewing my code, I found that I've applied several good coding standards learned during this week's lectures and tried to implement them in this tutorial.
For example:
* Using good naming conventions
* Ensuring functions that are short and do only one thing
* Letting the code explain itself while also minimizing unnecessary comments
* Implementing some error handling
* Getting used to git branching
* Reading the documentation

There are a lot of coding standards i did not implement that makes it flawed, The ones I have noticed is:
* There are some part of the code that could be written better and easiser for other readers to digest
* Several security risk(example the id for the products are not encrypted in the url pathing)
* Lack of error handling

### Reflection 2
==First Part==
* After creating and utilizing unit tests, I found them to be highly convenient compared to manual testing. They provide a structured way to verify the functionality of individual components, leading to increased confidence in the code's reliability.

* The number of unit tests required for a class can vary depending on factors such as the complexity of the class, the number of methods,etch . In general, strive to create enough tests to cover all possible code paths and edge cases. However, it's important to prioritize quality over quantity, ensuring that each test case provides meaningful coverage.

* Achieving 100% code coverage through unit tests does not guarantee that bugs or errors won't appear. While high code coverage indicates that most of the source code has been implemented during testing, it doesn't account for all possible scenarios or ensure correctness.

== Second Part==
* Creating a new Java class with identical setup procedures and instance variables may lead to redundancy and a decline in code quality.
* This duplication in the new functional test suite can compromise code cleanliness, making it less organized and harder to maintain.
* To improve code cleanliness, you could make abstract common setup procedures into reusable methods or parent classes to avoid duplication while ensuring that each test method focuses on a specific aspect of functionality.

## Week 02

### Reflection 
==First Part==
* One of them is clumsily setting the generic type of a Set when compiler can infer it causing a redundancy. The fix was to just remove the type.
* in the controller methods for retrieving path variables or request parameters can make it difficult to test those endpoints and can also lead to ambiguity in the request mappings. Adding @RequestParam annotations clarifies the purpose of each parameter and improves the readability of the code
* Lastly, I realized that we put different responsibilities into the same controller class.Combining multiple controller responsibilities within a single controller class can lead to poor code organization and maintenance difficulties as the application grows. Separating controllers based on functionality, such as having a separate HomepageController and ProductController

==Second Part==

* Yes, for the Continuous Integration is effectively implemented through the ci.yml file, facilitating automated unit testing upon code push or pull requests. Additionally, the Sonar and Scorecard tools bolster this process by automatically checking for code cleanliness and identifying any issues after each push. For Continuous Delivery, the deployment service Koyeb features an integrated CI/CD system, aiding in issue testing during the deployment phase. 

## week 03

### Reflection 

=== Applied SOLID Principles ===
* SRP : I applied the Single Responsibility Principle by Seperating the controller for `CarController` out of `ProductController` and making it into its own java file thereby `CarController` is responsible for managing Car related stuff , `ProductController` is responsible for managing Product related things and `HomePageController` is rensposible for managing the homepage. I also tweaked the `update` function inside the `CarRepository` so that it uses the `findById` method rather than repetitively making it inside the function.

* DIP : I applied the Dependency Inversion Principle by changing the `CarController` class to extend `CarService` rather than `CarServiceImp` so that I use the abstraction rather than the Implementations.

* ISP :  The Interface Segregation Principle has already been applied, the example being `CarService` and `ProductService` are split instead of being bundled up. This is inline with ISP where the goal is to have multiple interfaces that caters to specific responsibilities.

=== Advantages of Applying SOLID Princples ===
* SRP : By separating responsibilities into distinct classes like `CarController`, `ProductController`, and `HomePageController`, the code becomes more modular and easier to maintain. Each class is focused on a single responsibility, leading to better organization and readability. Additionally, updating the CarRepository to use the findById method improves code reusability and reduces duplication.

* DIP : Extending the `CarController` class from `CarService` rather than `CarServiceImpl` adheres to DIP by depending on abstractions rather than concrete implementations. This makes the code more flexible and easier to extend or modify in the future. It also promotes loose coupling between classes, facilitating easier unit testing and overall system maintenance.

* ISP : Splitting interfaces like CarService and ProductService aligns with ISP's goal of having multiple interfaces catering to specific responsibilities. This prevents clients from being forced to depend on interfaces they don't use, leading to more cohesive and maintainable code. It also allows for easier customization and extension of functionality without affecting unrelated parts of the system.

Good link for SOLID Princple advantages: [Link](https://mmmake.com/en/blog/solid-principles-easily-explained/)

=== Disadvantages of Not Applying SOLID Principles === 
* Violating the Single Responsibility Principle (SRP) makes it so that classes tend to have multiple responsibilities, leading to code entanglement. This makes it difficult to understand, modify, and maintain the codebase over time.

* Violating the Dependency Inversion Principle (DIP) results in rigid code that is tightly coupled to concrete implementations. This makes it challenging to introduce changes or swap out implementations without affecting other parts of the system.

* Violating the Interface Segregation Principle (ISP) leads to interfaces that are overly large and contain methods that are not relevant to all clients. This results in interface pollution and forces clients to depend on methods they don't need, leading to unnecessary complexity and potential bugs.

