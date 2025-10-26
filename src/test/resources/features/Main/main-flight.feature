Feature: TKC Book flight Main Flow

  Background:
    Given open TKC homepage

  @tkc @bookflight-module @one-way @solo
  Scenario Outline: <TCID>: As a user, I can book solo one-way flight and choose class
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select <Date> of flight
    And set flight class to <FlightClass>
    And select an available airline
    And submit passenger personal informations
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure | Destination | Date                    | FlightClass     |
      | M-U-BFM-C-01 | Batam     | Singapore   | 30 November 2025 Minggu | Ekonomi         |
      | M-U-BFM-C-02 | Jakarta   | Singapore   | 30 November 2025 Minggu | Premium Ekonomi |
      | M-U-BFM-C-03 | Jakarta   | Denpasar    | 30 November 2025 Minggu | Bisnis          |
      | M-U-BFM-C-04 | Jakarta   | Denpasar    | 30 November 2025 Minggu | First           |

  @tkc @bookflight-module @one-way @solo @identical-booking
  Scenario Outline: <TCID>: As a user, I can create a new solo one-way flight even if an identical booking is already in progress
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select <Date> of flight
    And set flight class to <FlightClass>
    And select an available airline
    And submit passenger personal informations
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | Date                    | FlightClass     |
      | M-U-BFM-C-05 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Ekonomi         |
      | M-U-BFM-C-06 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Premium Ekonomi |
      | M-U-BFM-C-07 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Bisnis          |
      | M-U-BFM-C-08 | Jakarta     | Denpasar    | 30 November 2025 Minggu | First           |

  @tkc @bookflight-module @one-way @solo @full-data
  Scenario Outline: <TCID>: As a user, I can book solo one-way flight and choose class full data
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select <Date> of flight
    And set flight class to <FlightClass>
    And select an available airline
    And submit passenger personal informations
    And add extra luggage
    And add food
    And choose seat
    And add insurance
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | Date                    | FlightClass     |
      | M-U-BFM-C-09 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Ekonomi         |
      | M-U-BFM-C-10 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Premium Ekonomi |
      | M-U-BFM-C-11 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Bisnis          |
      | M-U-BFM-C-12 | Jakarta     | Denpasar    | 30 November 2025 Minggu | First           |

  @tkc @bookflight-module @one-way @solo @full-data @identical-booking
  Scenario Outline: <TCID>: As a user, I can create a new solo one-way flight booking full data even if an identical booking is already in progress
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select <Date> of flight
    And set flight class to <FlightClass>
    And select an available airline
    And submit passenger personal informations
    And add extra luggage
    And add food
    And choose seat
    And add insurance
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | Date                    | FlightClass     |
      | M-U-BFM-C-13 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Ekonomi         |
      | M-U-BFM-C-14 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Premium Ekonomi |
      | M-U-BFM-C-15 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Bisnis          |
      | M-U-BFM-C-16 | Jakarta     | Denpasar    | 30 November 2025 Minggu | First           |

  @tkc @bookflight-module @round-trip @solo
  Scenario Outline: <TCID>: As a user, I can book solo round-trip flight and choose class
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select round trip
    And set <DepartureDate> of flight and <ReturnDate> of flight
    And set flight class to <FlightClass>
    And select an available airlines
    And submit passenger personal informations
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | DepartureDate           | ReturnDate            | FlightClass     |
      | M-U-BFM-C-17 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Ekonomi         |
      | M-U-BFM-C-18 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Premium Ekonomi |
      | M-U-BFM-C-19 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Bisnis          |
      | M-U-BFM-C-20 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | First           |

  @tkc @bookflight-module @round-trip @solo @identical-booking
  Scenario Outline: <TCID>: As a user, I can create a new solo round-trip flight booking even if an identical booking is already in progress
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select round trip
    And set <DepartureDate> of flight and <ReturnDate> of flight
    And set flight class to <FlightClass>
    And select an available airlines
    And submit passenger personal informations
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | DepartureDate           | ReturnDate            | FlightClass     |
      | M-U-BFM-C-21 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Ekonomi         |
      | M-U-BFM-C-22 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Premium Ekonomi |
      | M-U-BFM-C-23 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Bisnis          |
      | M-U-BFM-C-24 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | First           |

  @tkc @bookflight-module @round-trip @solo @full-data
  Scenario Outline: <TCID>: As a user, I can book solo round-trip flight and choose class full data
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select round trip
    And set <DepartureDate> of flight and <ReturnDate> of flight
    And set flight class to <FlightClass>
    And select an available airlines
    And submit passenger personal informations
    And add extra luggage
    And add food
    And choose seat
    And add insurance
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | DepartureDate           | ReturnDate            | FlightClass     |
      | M-U-BFM-C-25 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Ekonomi         |
      | M-U-BFM-C-26 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Premium Ekonomi |
      | M-U-BFM-C-27 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Bisnis          |
      | M-U-BFM-C-28 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | First           |

  @tkc @bookflight-module @round-trip @solo @full-data @identical-booking
  Scenario Outline: <TCID>: As a user, I can create a new solo round-trip flight booking full data even if an identical booking is already in progress
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select round trip
    And set <DepartureDate> of flight and <ReturnDate> of flight
    And set flight class to <FlightClass>
    And select an available airlines
    And submit passenger personal informations
    And add extra luggage
    And add food
    And choose seat
    And add insurance
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | DepartureDate           | ReturnDate            | FlightClass     |
      | M-U-BFM-C-29 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Ekonomi         |
      | M-U-BFM-C-30 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Premium Ekonomi |
      | M-U-BFM-C-31 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Bisnis          |
      | M-U-BFM-C-32 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | First           |

  @tkc @bookflight-module @one-way @group
  Scenario Outline: <TCID>: As a user, I can book group one-way flight with adult, child, and infant, and choose class
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select <Date> of flight
    And set number of passengers and flight class to <FlightClass>
    And select an available airline
    And submit personal informations for all passengers
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure | Destination | Date                    | FlightClass     |
      | M-U-BFM-C-01 | Batam     | Singapore   | 30 November 2025 Minggu | Ekonomi         |
      | M-U-BFM-C-02 | Jakarta   | Singapore   | 30 November 2025 Minggu | Premium Ekonomi |
      | M-U-BFM-C-03 | Jakarta   | Denpasar    | 30 November 2025 Minggu | Bisnis          |
      | M-U-BFM-C-04 | Jakarta   | Denpasar    | 30 November 2025 Minggu | First           |

  @tkc @bookflight-module @one-way @group @identical-booking
  Scenario Outline: <TCID>: As a user, I can book group one-way flight with adult, child, and infant, and choose class even if an identical booking is already in progress
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select <Date> of flight
    And set number of passengers and flight class to <FlightClass>
    And select an available airline
    And submit personal informations for all passengers
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure | Destination | Date                    | FlightClass     |
      | M-U-BFM-C-01 | Batam     | Singapore   | 30 November 2025 Minggu | Ekonomi         |
      | M-U-BFM-C-02 | Jakarta   | Singapore   | 30 November 2025 Minggu | Premium Ekonomi |
      | M-U-BFM-C-03 | Jakarta   | Denpasar    | 30 November 2025 Minggu | Bisnis          |
      | M-U-BFM-C-04 | Jakarta   | Denpasar    | 30 November 2025 Minggu | First           |

  @tkc @bookflight-module @one-way @group @full-data
  Scenario Outline: <TCID>: As a user, I can book group one-way flight with adult, child, and infant, and choose class full data
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select <Date> of flight
    And set number of passengers and flight class to <FlightClass>
    And select an available airline
    And submit personal informations for all passengers
    And add extra luggage for all passengers
    And add food for all passengers
    And choose seat for all passengers
    And add insurance
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | Date                    | FlightClass     |
      | M-U-BFM-C-09 | Jakarta     | Singapore   | 30 November 2025 Minggu | Ekonomi         |
      | M-U-BFM-C-10 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Premium Ekonomi |
      | M-U-BFM-C-11 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Bisnis          |
      | M-U-BFM-C-12 | Jakarta     | Denpasar    | 30 November 2025 Minggu | First           |

  @tkc @bookflight-module @one-way @group @full-data @identical-booking
  Scenario Outline: <TCID>: As a user, I can book group one-way flight with adult, child, and infant, and choose class full data even if an identical booking is already in progress
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select <Date> of flight
    And set number of passengers and flight class to <FlightClass>
    And select an available airline
    And submit personal informations for all passengers
    And add extra luggage for all passengers
    And add food for all passengers
    And choose seat for all passengers
    And add insurance
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | Date                    | FlightClass     |
      | M-U-BFM-C-09 | Jakarta     | Singapore   | 30 November 2025 Minggu | Ekonomi         |
      | M-U-BFM-C-10 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Premium Ekonomi |
      | M-U-BFM-C-11 | Jakarta     | Denpasar    | 30 November 2025 Minggu | Bisnis          |
      | M-U-BFM-C-12 | Jakarta     | Denpasar    | 30 November 2025 Minggu | First           |

  @tkc @bookflight-module @round-trip @group
  Scenario Outline: <TCID>: As a user, I can book group round-trip flight with adult, child, and infant, and choose class
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select round trip
    And set <DepartureDate> of flight and <ReturnDate> of flight
    And set number of passengers and flight class to <FlightClass>
    And select an available airlines
    And submit personal informations for all passengers
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | DepartureDate           | ReturnDate            | FlightClass     |
      | M-U-BFM-C-17 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Ekonomi         |
      | M-U-BFM-C-18 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Premium Ekonomi |
      | M-U-BFM-C-19 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Bisnis          |
      | M-U-BFM-C-20 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | First           |

  @tkc @bookflight-module @round-trip @group @identical-booking
  Scenario Outline: <TCID>: As a user, I can book group round-trip flight with adult, child, and infant, and choose class full data even if an identical booking is already in progress
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select round trip
    And set <DepartureDate> of flight and <ReturnDate> of flight
    And set number of passengers and flight class to <FlightClass>
    And select an available airlines
    And submit personal informations for all passengers
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | DepartureDate           | ReturnDate            | FlightClass     |
      | M-U-BFM-C-17 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Ekonomi         |
      | M-U-BFM-C-18 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Premium Ekonomi |
      | M-U-BFM-C-19 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Bisnis          |
      | M-U-BFM-C-20 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | First           |

  @tkc @bookflight-module @round-trip @group @full-data
  Scenario Outline: <TCID>: As a user, I can book group round-trip flight with adult, child, and infant, and choose class full data
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select round trip
    And set <DepartureDate> of flight and <ReturnDate> of flight
    And set number of passengers and flight class to <FlightClass>
    And select an available airlines
    And submit personal informations for all passengers
    And add extra luggage for all passengers
    And add food for all passengers
    And choose seat for all passengers
    And add insurance
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | DepartureDate           | ReturnDate            | FlightClass     |
      | M-U-BFM-C-17 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Ekonomi         |
      | M-U-BFM-C-18 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Premium Ekonomi |
      | M-U-BFM-C-19 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Bisnis          |
      | M-U-BFM-C-20 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | First           |

  @tkc @bookflight-module @round-trip @group @full-data @identical-booking
  Scenario Outline: <TCID>: As a user, I can book group round-trip flight with adult, child, and infant, and choose class full data even if an identical booking is already in progress
    Given navigate to the flight page
    When choose <Departure> and <Destination>
    And select round trip
    And set <DepartureDate> of flight and <ReturnDate> of flight
    And set number of passengers and flight class to <FlightClass>
    And select an available airlines
    And submit personal informations for all passengers
    And add extra luggage for all passengers
    And add food for all passengers
    And choose seat for all passengers
    And add insurance
    And confirm book flight order
    Then validate payment page
    Examples:
      | TCID         | Departure   | Destination | DepartureDate           | ReturnDate            | FlightClass     |
      | M-U-BFM-C-17 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Ekonomi         |
      | M-U-BFM-C-18 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Premium Ekonomi |
      | M-U-BFM-C-19 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | Bisnis          |
      | M-U-BFM-C-20 | Jakarta     | Denpasar    | 30 November 2025 Minggu | 31 Desember 2025 Rabu | First           |