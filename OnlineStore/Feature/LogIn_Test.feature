#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.(Defines what feature you will be testing in the tests below)
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step(Tells the pre-condition of the test)
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps(Defines additional conditions of the test)
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Login Action

@tag1
Scenario: Successful Login with Valid Credentials
Given User is on Home Page
When User Navigate to LogIn Page
#And And User enters "testuser_1" and "Test@123"
#And User enters "<username>" and "<password>"
And User enters Credentials to LogIn
    | Username   | Password |
    | testuser_1 | Test@153 |
    | testuser_2 | Test@154 |
Then Message displayed Login Successfully

@tag2 
Scenario: Successful LogOut
When User LogOut from the Application
Then Message displayed LogOut Successfully

