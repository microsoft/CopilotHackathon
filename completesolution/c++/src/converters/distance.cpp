#include "distance.h"
#include <iostream>
#include <unordered_map>

namespace /* private */ {
  std::string getDistanceUnitSign(DistanceUnit unit)
  {
    std::unordered_map<DistanceUnit, std::string> unitSigns = {
      {DistanceUnit::Meters, "m"},
      {DistanceUnit::Feet, "ft"},
      {DistanceUnit::Yards, "yd"}
    };

    return unitSigns.at(unit);
  }
} // namespace private

namespace DistanceConversion
{
  void startFlow()
  {
    double sourceValue = getSourceValue();
    DistanceUnit from = getDistanceUnit("source");
    DistanceUnit to = getDistanceUnit("target");

    double targetValue = convertDistance(sourceValue, from, to);

    std::cout << sourceValue << getDistanceUnitSign(from) << " is " << targetValue << getDistanceUnitSign(to) << std::endl;
  }

  double getSourceValue()
  {
    double value;
    std::cout << "Enter the value to be converted: ";
    std::cin >> value;
    return value;
  }

  DistanceUnit getDistanceUnit(const std::string_view &sourceOrTarget)
  {
    int choice;
    std::cout << "Select " << sourceOrTarget << " distance unit:\n";
    std::cout << "[1] Meters\n";
    std::cout << "[2] Feet\n";
    std::cout << "[3] Yards\n";
    std::cout << "Enter choice: ";
    std::cin >> choice;

    switch (choice)
    {
    case 1:
      return DistanceUnit::Meters;
    case 2:
      return DistanceUnit::Feet;
    case 3:
      return DistanceUnit::Yards;
    default:
      return DistanceUnit::Meters; // Default to Meters
    }
  }

  double convertDistance(double value, DistanceUnit from, DistanceUnit to)
  {
    if (from == to)
    {
      return value;
    }

    if (from == DistanceUnit::Meters)
    {
      if (to == DistanceUnit::Feet)
      {
        return value * 3.28084;
      }
      else if (to == DistanceUnit::Yards)
      {
        return value * 1.09361;
      }
    }
    else if (from == DistanceUnit::Feet)
    {
      if (to == DistanceUnit::Meters)
      {
        return value / 3.28084;
      }
      else if (to == DistanceUnit::Yards)
      {
        return value / 3.0;
      }
    }
    else if (from == DistanceUnit::Yards)
    {
      if (to == DistanceUnit::Meters)
      {
        return value / 1.09361;
      }
      else if (to == DistanceUnit::Feet)
      {
        return value * 3.0;
      }
    }

    return 0;
  }
}