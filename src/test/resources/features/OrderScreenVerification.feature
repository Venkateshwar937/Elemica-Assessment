Feature: Elemica Task

  Background:
    Given User launches Elemica application
    And User logs into the application with credentials as "testAntony" and "GuestPassword@1"
    Then User verifies login is successful
  @test
  Scenario Outline: Verify the Orders screen
    Given User navigates to Orders screen
    When User filter the order with order number <OrderNumber>
    Then User should be able to view the results based on <OrderNumber>

      Examples:
        | OrderNumber |
        | QA_Antony   |

  Scenario Outline: Upload and download an attachment
    Given User navigates to Orders screen
    When User clicks on the order number
    And User clicks the "Attachments" tab
    And User uploads an attachment with <FileName>
    Then User downloads the same file and verify the <FileName>

    Examples:
      | FileName       |
      | TestUpload.txt |





