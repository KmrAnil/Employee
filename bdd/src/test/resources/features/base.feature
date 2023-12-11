Feature: Employee crud operation testing

  Scenario Outline: client makes call to create employee
    When employee is created with name <name> department <department> and email <email>
    Then verify employee detail is added
    Then update employee department as "Mechanical"
    Then delete the employee detail
    Examples:
      | name   | department | email              |
      | "Anil" | "IT"       | "test12@gmail.com" |
      | "AK"   | "Civil"    | "test@gmail.com"   |