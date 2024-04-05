Selenium WebDriver Framework for MakeMyTrip Hotel Booking Scenario

Functionality:
1. Open the MMT site and move to the hotels section.
2. Choose the location and dates based on user input.
3. Give necessary details like rooms and adults count (If Children are included then their age).
4. Then search and get the results of hotels in that location.

 Output:
- Hotel Booking Scenario:
 1. Get the hotel's name and price from the list shown on the page without scrolling down (Initially only 10 hotels came from the search result, if we scroll down another 10 will appear).
 2. Compute the hotel list and get the cheapest hotel available.

- List of prices computing
  1. Get the last digit from the list of prices.
  2. Perform AND operation on the list between any 2 elements from the list for K time and sum up to the Score, the remove those elements from the list.
  3. Print the score and list from each iteration.
  4. Print the final score of computation.

PageObjectModel/PageFactory Implementation :
- Created page object classes for each scenario
   1. Home Page
   2. Pop-UP Ad Handling(Appears when opening the site)
   3. Search Result Page

- Created BaseTest for browser instantiation Reporting with Listeners and Retry Mechanism.
  1. Integrated Extent Reports with ITestListeners of TestNG
  2. Retry mechanism using IRetryAnalyzer for retrying the failed test cases.
  3. Browser Invoking and managing were separated from the test using Base Test.

Test Methods only contain the actual testing scenario and all other methods and functions were encapsulated.

OOPS methodology Used:
1. Inheritance
2. Polymorphism
3. Abstraction
4. Encapsulation

Framework Uses:
TestNG with Maven 
    
