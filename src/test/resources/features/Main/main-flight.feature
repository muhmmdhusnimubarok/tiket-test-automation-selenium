Feature: TKC Book flight Main Flow

  Background:
    Given open TKC home page

  @tkc @bookflight-module @oneway @solo @economy @no-insurance
  Scenario Outline: <TCID>: As an user, I can book solo oneway flight economy class
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select <Date> of flight
    And select an available airline
    And fills personal information
#    Then redirected to payment page
#    And validate payment page
    Examples:
      | TCID          | Departure | Destination          | Date                  |
      | M-U-BFM-C-01  | Batam     | Denpasar             | 25 Oktober 2025 Sabtu |