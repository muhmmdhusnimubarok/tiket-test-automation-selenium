Feature: TKC Book flight Main Flow

  Background:
    Given open TKC home page

  @tkc @bookflight-module @one-way @solo @economy @no-insurance
  Scenario Outline: <TCID>: As an user, I can book solo oneway flight economy
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select <Date> of flight
    And select an available airline
    And reject flight disturbance protection
    And submit personal information
    Then validate payment page
    Examples:
      | TCID          | Departure   | Destination          | Date                  |
      | M-U-BFM-C-01  | Jakarta     | Denpasar             | 25 Oktober 2025 Sabtu |


  @tkc @bookflight-module @one-way @solo @economy @no-insurance @identical-booking
  Scenario Outline: <TCID>: As a user, I can create a new solo one way economy booking even if an identical booking is already in progress
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select <Date> of flight
    And select an available airline
    And reject flight disturbance protection
    And submit personal information
    And confirm order
    Then validate payment page
    Examples:
      | TCID          | Departure   | Destination          | Date                  |
      | M-U-BFM-C-01  | Jakarta     | Denpasar             | 25 Oktober 2025 Sabtu |

  @tkc @bookflight-module @roundtrip @solo @economy @no-insurance @jami
  Scenario Outline: <TCID>: As an user, I can book solo round trip flight economy
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select round trip
    And set <DepartureDate> of flight and <ReturnDate> of flight
    And select an available airlines
    And reject flight disturbance protection
    And submit personal information
    Then validate payment page
    Examples:
      | TCID          | Departure   | Destination | DepartureDate         | ReturnDate            |
      | M-U-BFM-C-01  | Jakarta     | Denpasar    | 25 Oktober 2025 Sabtu | 31 Oktober 2025 Jumat |

  @tkc @bookflight-module @roundtrip @solo @economy @no-insurance @identical-booking @jamu
  Scenario Outline: <TCID>: As a user, I can create a new solo round trip flight economy booking with no insurance even if an identical booking is already in progress
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select round trip
    And set <DepartureDate> of flight and <ReturnDate> of flight
    And select an available airlines
    And reject flight disturbance protection
    And submit personal information
    And confirm order
    Then validate payment page
    Examples:
      | TCID          | Departure   | Destination | DepartureDate         | ReturnDate            |
      | M-U-BFM-C-01  | Jakarta     | Denpasar    | 25 Oktober 2025 Sabtu | 31 Oktober 2025 Jumat |