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
Yes, for the Continuous Integration is effectively implemented through the ci.yml file, facilitating automated unit testing upon code push or pull requests. Additionally, the Sonar and Scorecard tools bolster this process by automatically checking for code cleanliness and identifying any issues after each push. For Continuous Delivery, the deployment service Koyeb features an integrated CI/CD system, aiding in issue testing during the deployment phase. 