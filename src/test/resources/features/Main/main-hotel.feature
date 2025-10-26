Feature: TKC Book hotel Main Flow

  Background:
    Given open TKC homepage

    @tkc @bookhotel-module @room-1 @guest-1 @haha
    Scenario Outline: <TCID>: As a user, I can book room with one guest
      Given navigate to hotel page
      When choose <Region> to stay
      And select <CheckIn> and <CheckOut> date
      And select an available hotel
      And submit guest personal informations
      And confirm book room order
      Examples:
        | TCID         | Region       | CheckIn                 | CheckOut                |
        | M-U-BHM-C-01 | Yogyakarta   | 2 Februari 2026 Senin   | 4 Februari 2026 Rabu    |

  @tkc @bookhotel-module @room-1 @guest-1 @haha
  Scenario Outline: <TCID>: As a user, I can book room with one guest
    Given navigate to hotel page
    When choose <Region> to stay
    And select <CheckIn> and <CheckOut> date
    And select an available hotel
    And submit guest personal informations
    And confirm book room order
    Examples:
      | TCID         | Region       | CheckIn                 | CheckOut                |
      | M-U-BHM-C-01 | Yogyakarta   | 2 Februari 2026 Senin   | 4 Februari 2026 Rabu    |
