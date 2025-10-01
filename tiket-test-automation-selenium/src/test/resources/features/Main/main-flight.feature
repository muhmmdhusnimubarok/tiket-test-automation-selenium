Feature: TKC Book flight Main Flow

  Background:
    Given open TKC home page

  @tkc @bookflight-module @solo @economy
  Scenario Outline: <TCID>: As an user, I can book solo flight economy class
    Given navigate to the flight page
    When choose <Departure> and <Destination> flight detail
    And selects an available airline
    And fills personal information
    Then redirected to payment page
    And validate payment page
    Examples:
      | TCID          | Departure | Destination |
      | M-U-BFM-C-01  | Batam     | Bali        |